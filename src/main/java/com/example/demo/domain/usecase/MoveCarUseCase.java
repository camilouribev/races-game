package com.example.demo.domain.usecase;

import com.example.demo.domain.game.Game;
import com.example.demo.domain.game.command.MoveCar;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Function;

@Component
public class MoveCarUseCase implements Function<MoveCar, Game> {

    private final GameRepository repository;
    private final Random rand = SecureRandom.getInstanceStrong();

    public MoveCarUseCase(GameRepository repository) throws NoSuchAlgorithmException {
        this.repository = repository;
    }

    @Override
    public Game apply(MoveCar moveCar) {
        var game = repository.findById(moveCar.getGameId());
        game.players().forEach((playerId, player)->{
            if(playerId.equals(moveCar.getPlayerId()) && player.carDrivenDistance()< game.trackLength() && game.inProgress()){
                player.move((rand.nextInt(6)+1)*100);
            }
        });
        return repository.save(game) ;
    }
}
