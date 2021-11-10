package com.example.demo.entrypoint;

import com.example.demo.domain.WinnerFound;
import com.example.demo.domain.game.Gamer;
import com.example.demo.domain.game.command.CreateGame;
import com.example.demo.domain.game.command.StartGame;
import com.example.demo.domain.usecase.CreateGameUseCase;
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
        var event = new WinnerFound();
        var gamer = startGameUseCase.apply(startGame);
        Optional.ofNullable(gamer).ifPresent((g) -> {
            event.setName(g.name());
            event.setId(g.id());
            event.setGameId(startGame.getId());
            this.eventPublisher.publishEvent(event);
        });
        return Optional.ofNullable(gamer).map(Gamer::name).orElse("No Winner");
    }
}
