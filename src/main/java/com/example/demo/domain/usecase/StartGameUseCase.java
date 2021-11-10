package com.example.demo.domain.usecase;

import com.example.demo.domain.game.Gamer;
import com.example.demo.domain.game.command.StartGame;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Function;

@Component
public class StartGameUseCase implements Function<StartGame, Gamer> {

    private final GameRepository repository;
    private final Random rand = SecureRandom.getInstanceStrong();

    public StartGameUseCase(GameRepository repository) throws NoSuchAlgorithmException {
        this.repository = repository;
    }

    @Override
    public Gamer apply(StartGame startGame) {
        var game = repository.findById(startGame.getId());
        var value = rand.nextInt(6);
        startGame.getGamerBet().forEach((gamerId, bet) -> {
                if(bet.equals(value)){
                    game.setWinner(gamerId);
                }
        });

        return game.winner();
    }
}
