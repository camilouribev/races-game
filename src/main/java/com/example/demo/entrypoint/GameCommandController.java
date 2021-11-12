package com.example.demo.entrypoint;

import com.example.demo.domain.game.events.*;
import com.example.demo.domain.game.Game;
import com.example.demo.domain.game.command.CreateGame;
import com.example.demo.domain.game.command.MoveCar;
import com.example.demo.domain.game.command.StartGame;
import com.example.demo.domain.usecase.*;
import com.example.demo.infra.MongoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
public class GameCommandController {

    private final StartGameUseCase startGameUseCase;
    private final MoveCarUseCase moveCarUseCase;
    private final ApplicationEventPublisher eventPublisher;
    private final FinishGameUseCase finishGameUseCase;
    private final WinnerUseCase winnerUseCase;
    private final FinishSecondUseCase finishSecondUseCase;
    private final MongoGameRepository repository;

    @Autowired
    public GameCommandController(StartGameUseCase startGameUseCase, MoveCarUseCase moveCarUseCase, ApplicationEventPublisher eventPublisher, FinishGameUseCase finishGameUseCase, WinnerUseCase winnerUseCase, FinishSecondUseCase finishSecondUseCase, MongoGameRepository repository) {
        this.startGameUseCase = startGameUseCase;
        this.moveCarUseCase = moveCarUseCase;
        this.eventPublisher = eventPublisher;
        this.finishGameUseCase = finishGameUseCase;
        this.winnerUseCase = winnerUseCase;

        this.finishSecondUseCase = finishSecondUseCase;
        this.repository = repository;
    }

    @PostMapping("/createGame")
    public void createGame(@RequestBody CreateGame createGame) {
        this.eventPublisher.publishEvent(createGame);
    }


    @PostMapping("/startGame")
    public String createGame(@RequestBody StartGame startGame) {
        var game = startGameUseCase.apply(startGame);
        Optional.ofNullable(game).map(Game::id).ifPresent(g -> {
            var event = new RaceStarted();
            event.setGameId(startGame.getId());
            event.setTrackLength(startGame.getTrackLength());

            this.eventPublisher.publishEvent(event);
        });
        return String.valueOf(Optional.ofNullable(game).map(Game::trackLength).orElse(999));
    }

    @PostMapping("/move")
    public String moveCar(@RequestBody MoveCar moveCar) {

        var game = moveCarUseCase.apply(moveCar);
        var repoGame = repository.findById(moveCar.getGameId());

        Optional.ofNullable(game).ifPresent(g -> {

            System.out.println("Winner es esto: "+ repoGame.winner());


                if (g.players().get(moveCar.getPlayerId()).carDrivenDistance() >= g.trackLength() && Objects.isNull(repoGame.winner())) {

                    winnerUseCase.apply(moveCar);
                    var winnerFoundEvent = new WinnerFound();
                    winnerFoundEvent.setGameId(moveCar.getGameId());
                    winnerFoundEvent.setId(moveCar.getPlayerId());
                    winnerFoundEvent.setName(g.players().get(moveCar.getPlayerId()).name());
                    winnerFoundEvent.setCarDrivenDistance(g.players().get(moveCar.getPlayerId()).carDrivenDistance());
                    this.eventPublisher.publishEvent(winnerFoundEvent);
                    System.out.println("winner event sent");
                    return;
                }
                if (g.players().get(moveCar.getPlayerId()).carDrivenDistance() >= g.trackLength() && Objects.nonNull(g.winner())  && Objects.isNull(g.secondPlace())){

                    finishSecondUseCase.apply(moveCar);
                    var secondPlaceEvent = new SecondPlaceFound();
                    secondPlaceEvent.setGameId(moveCar.getGameId());
                    secondPlaceEvent.setId(moveCar.getPlayerId());
                    secondPlaceEvent.setName(g.players().get(moveCar.getPlayerId()).name());
                    secondPlaceEvent.setCarDrivenDistance(g.players().get(moveCar.getPlayerId()).carDrivenDistance());
                    this.eventPublisher.publishEvent(secondPlaceEvent);
                    System.out.println("second place event sent ");
                    return;

                }
                if (Objects.nonNull(g.secondPlace())) {

                    finishGameUseCase.apply(moveCar);
                    var finishEvent = new GameFinished();

                    finishEvent.setGameId(moveCar.getGameId());
                    finishEvent.setPlayerId(moveCar.getPlayerId());
                    finishEvent.setPlayers(g.players());
                   this.eventPublisher.publishEvent(finishEvent);
                    System.out.println("finish event sent");

                }


            var event = new CarMoved();
            event.setGameId(moveCar.getGameId());
            event.setPlayerId(moveCar.getPlayerId());
            event.setDistanceMoved(g.players().get(moveCar.getPlayerId()).carDrivenDistance());
            event.setPlayers(g.players());

            this.eventPublisher.publishEvent(event);

        });

        return Optional.ofNullable(game).map(Game::id).orElse("Nothing moved");
    }
}