package com.example.demo.domain.usecase;

import com.example.demo.domain.game.Game;
import com.example.demo.domain.game.command.MoveCar;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

@Controller
public class WinnerUseCase  implements Function<MoveCar, Game> {
    private final GameRepository repository;

    public WinnerUseCase(GameRepository repository) {
        this.repository = repository;
    }

    @Override
    public Game apply(MoveCar moveCar) {
        var game = repository.findById(moveCar.getGameId());
        game.setWinner(moveCar.getPlayerId());

        return  repository.save(game);
    }
}
