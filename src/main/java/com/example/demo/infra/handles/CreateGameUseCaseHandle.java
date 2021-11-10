package com.example.demo.infra.handles;

import com.example.demo.domain.game.command.CreateGame;
import com.example.demo.domain.usecase.CreateGameUseCase;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CreateGameUseCaseHandle  {
    private final CreateGameUseCase createGameUseCase;

    public CreateGameUseCaseHandle(CreateGameUseCase createGameUseCase){
        this.createGameUseCase = createGameUseCase;
    }

    @EventListener
    public void handle(CreateGame createGame) {
        createGameUseCase.apply(createGame);
    }
}
