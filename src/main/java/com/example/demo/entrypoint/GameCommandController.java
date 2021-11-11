package com.example.demo.entrypoint;

import com.example.demo.domain.RaceStarted;
import com.example.demo.domain.WinnerFound;
import com.example.demo.domain.game.Game;
import com.example.demo.domain.game.Player;
import com.example.demo.domain.game.command.CreateGame;
import com.example.demo.domain.game.command.StartGame;
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
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public GameCommandController( StartGameUseCase startGameUseCase, ApplicationEventPublisher eventPublisher){
        this.startGameUseCase = startGameUseCase;
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
            var event = new WinnerFound();
            event.setName(g.name());
            event.setId(g.id());
            event.setGameId(startGame.getId());
            event.setCarDrivenDistance(startGame.getTrackLength());
            this.eventPublisher.publishEvent(event);
        });
        return Optional.ofNullable(game).map(Game::winner).map(Player::name).orElse("No Winner");
    }

    @PostMapping("/startGame2")
    public String createGame2(@RequestBody StartGame startGame){
        var game = startGameUseCase.apply(startGame);
        Optional.ofNullable(game).map(Game::winner).ifPresent(g -> {
            var event = new RaceStarted();
            event.setGameId(startGame.getId());
            event.setTrackLength(startGame.getTrackLength());

            this.eventPublisher.publishEvent(event);
        });
        return    String.valueOf(Optional.ofNullable(game).map( Game::trackLength).orElse(999)) ;
    }
}
