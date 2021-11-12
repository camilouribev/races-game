package com.example.demo.domain.usecase;

import com.example.demo.domain.game.Game;
import com.example.demo.domain.game.command.MoveCar;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;

@Component
public class MoveCarUseCase implements Function<MoveCar, Game> {

    private final GameRepository repository;
    private final Random rand = SecureRandom.getInstanceStrong();
    boolean winnerAssigned = false;
    boolean secondPlaceAssigned = false;


    public MoveCarUseCase(GameRepository repository) throws NoSuchAlgorithmException {
        this.repository = repository;
    }

    @Override
    public Game apply(MoveCar moveCar) {
        var game = repository.findById(moveCar.getGameId());
        var movingPlayer= game.players().get(moveCar.getPlayerId());


        if(movingPlayer.id().equals(moveCar.getPlayerId()) && movingPlayer.carDrivenDistance()< game.trackLength() && game.inProgress()){
                movingPlayer.move((rand.nextInt(6)+1)*100);
                System.out.println("Winner assigned :"+ winnerAssigned);
                System.out.println("Second place assigned :"+ secondPlaceAssigned);


                if(movingPlayer.carDrivenDistance()>= game.trackLength() && secondPlaceAssigned ){
                    game.setThirdPlace(moveCar.getPlayerId());
                     game.endGame();
                  }
                 if(movingPlayer.carDrivenDistance()>= game.trackLength() && winnerAssigned ){
                     game.setSecondPlace(moveCar.getPlayerId());
                      secondPlaceAssigned = true;
                 }

                if(movingPlayer.carDrivenDistance()>= game.trackLength() && !winnerAssigned ){
                     game.setWinner(moveCar.getPlayerId());
                     winnerAssigned = true;

                 }
            }
        return repository.save(game) ;


    }
}
