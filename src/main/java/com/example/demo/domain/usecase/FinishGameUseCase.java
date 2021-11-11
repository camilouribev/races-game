package com.example.demo.domain.usecase;

import com.example.demo.domain.game.Game;
import com.example.demo.domain.game.command.MoveCar;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class FinishGameUseCase implements Function<MoveCar, Game> {
    private final GameRepository repository;

    public FinishGameUseCase(GameRepository repository) {
        this.repository = repository;
    }

    @Override
    public Game apply(MoveCar moveCar) {

        var game = repository.findById(moveCar.getGameId());
        game.endGame();
       game.setWinner(moveCar.getPlayerId());

        return  repository.save(game);
    }
}
