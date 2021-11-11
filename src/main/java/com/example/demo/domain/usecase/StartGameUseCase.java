package com.example.demo.domain.usecase;

import com.example.demo.domain.game.Game;
import com.example.demo.domain.game.command.StartGame;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Function;

@Component
public class StartGameUseCase implements Function<StartGame, Game> {

    private final GameRepository repository;
    private final Random rand = SecureRandom.getInstanceStrong();

    public StartGameUseCase(GameRepository repository) throws NoSuchAlgorithmException {
        this.repository = repository;
    }

    @Override
    public Game apply(StartGame startGame) {
        var game = repository.findById(startGame.getId());
        System.out.println("game = " + game);
        var value = rand.nextInt(6);
        game.startGame();
        System.out.println("value = " + value);

        System.out.println("is in progress = " + game.inProgress());
        System.out.println("getPlayerBet: " + startGame.getPlayerBet());
        System.out.println("bet = " + startGame.getPlayerBet());

        startGame.getPlayerBet().forEach((playerId, bet) -> {

                if(bet.equals(value)){
                    game.setWinner(playerId);
                    repository.save(game);
                }
            System.out.println(game.winner());
        });

        return game;
    }
}
