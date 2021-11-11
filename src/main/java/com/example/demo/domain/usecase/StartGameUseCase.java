package com.example.demo.domain.usecase;

import com.example.demo.domain.game.Game;
import com.example.demo.domain.game.command.StartGame;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;

import java.util.function.Function;

@Component
public class StartGameUseCase implements Function<StartGame, Game> {

    private final GameRepository repository;

    public StartGameUseCase(GameRepository repository) throws NoSuchAlgorithmException {
        this.repository = repository;
    }

    @Override
    public Game apply(StartGame startGame) {

        var game = repository.findById(startGame.getId());
        if(!game.inProgress()){
            game.startGame(startGame.getTrackLength());
        }

        return  repository.save(game);
    }
}
