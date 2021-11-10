package com.example.demo.domain.usecase;

import com.example.demo.domain.game.command.CreateGame;
import com.example.demo.domain.game.Game;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Function;

@Component
public class CreateGameUseCase implements Function<CreateGame, Game> {
    private final GameRepository repository;

    public CreateGameUseCase(GameRepository repository)  {
        this.repository = repository;
    }
    @Override
    public Game apply(CreateGame createGame) {
        var game = new Game(createGame.getId());
        createGame.getGamers().forEach(name -> game.addGamer(UUID.randomUUID().toString(), name));
        return repository.save(game);
    }
}
