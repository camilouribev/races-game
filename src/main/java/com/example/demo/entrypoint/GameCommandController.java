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

import java.util.Optional;

@RestController
public class GameCommandController {

    private final StartGameUseCase startGameUseCase;
    private final MoveCarUseCase moveCarUseCase;
    private final ApplicationEventPublisher eventPublisher;
    private final FinishGameUseCase finishGameUseCase;
    private final WinnerUseCase winnerUseCase;
    private final FinishSecondUseCase finishSecondUseCase;

    @Autowired
    public GameCommandController(StartGameUseCase startGameUseCase, MoveCarUseCase moveCarUseCase, ApplicationEventPublisher eventPublisher, FinishGameUseCase finishGameUseCase, WinnerUseCase winnerUseCase, FinishSecondUseCase finishSecondUseCase) {
        this.startGameUseCase = startGameUseCase;
        this.moveCarUseCase = moveCarUseCase;
        this.eventPublisher = eventPublisher;
        this.finishGameUseCase = finishGameUseCase;
        this.winnerUseCase = winnerUseCase;

        this.finishSecondUseCase = finishSecondUseCase;
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

        Optional.ofNullable(game).ifPresent(g -> {

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