package com.example.demo.entrypoint;

import com.example.demo.domain.CarMoved;
import com.example.demo.domain.RaceStarted;
import com.example.demo.domain.game.Game;
import com.example.demo.domain.game.command.CreateGame;
import com.example.demo.domain.game.command.MoveCar;
import com.example.demo.domain.game.command.StartGame;
import com.example.demo.domain.usecase.MoveCarUseCase;
import com.example.demo.domain.usecase.StartGameUseCase;
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

    @Autowired
    public GameCommandController(StartGameUseCase startGameUseCase, MoveCarUseCase moveCarUseCase, ApplicationEventPublisher eventPublisher){
        this.startGameUseCase = startGameUseCase;
        this.moveCarUseCase = moveCarUseCase;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/createGame")
    public void createGame(@RequestBody CreateGame createGame){
        this.eventPublisher.publishEvent(createGame);
    }


    @PostMapping("/startGame")
    public String createGame(@RequestBody StartGame startGame){
        var game = startGameUseCase.apply(startGame);
        Optional.ofNullable(game).map(Game::winner).ifPresent(g -> {
            var event = new RaceStarted();
            event.setGameId(startGame.getId());
            event.setTrackLength(startGame.getTrackLength());

            this.eventPublisher.publishEvent(event);
        });
        return    String.valueOf(Optional.ofNullable(game).map( Game::trackLength).orElse(999)) ;
    }

    @PostMapping("/move")
    public String moveCar(@RequestBody MoveCar moveCar){

        var game = moveCarUseCase.apply(moveCar);
        Optional.ofNullable(game).map(Game::winner).ifPresent(g -> {
            var event = new CarMoved();
            event.setGameId(moveCar.getGameId());
            event.setPlayerId(moveCar.getPlayerId());
            event.setDistanceMoved(g.carDrivenDistance());

            this.eventPublisher.publishEvent(event);
        });
        return    Optional.ofNullable(game).map( Game::id).orElse("Nothing moved") ;
    }
}
