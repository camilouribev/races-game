package com.example.demo.entrypoint;

import com.example.demo.domain.game.events.CarMoved;
import com.example.demo.domain.game.events.GameFinished;
import com.example.demo.domain.game.events.RaceStarted;
import com.example.demo.domain.game.Game;
import com.example.demo.domain.game.Player;
import com.example.demo.domain.game.command.CreateGame;
import com.example.demo.domain.game.command.MoveCar;
import com.example.demo.domain.game.command.StartGame;
import com.example.demo.domain.usecase.FinishGameUseCase;
import com.example.demo.domain.usecase.MoveCarUseCase;
import com.example.demo.domain.usecase.StartGameUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
public class GameCommandController {

    private final StartGameUseCase startGameUseCase;
    private final MoveCarUseCase moveCarUseCase;
    private final ApplicationEventPublisher eventPublisher;
    private final FinishGameUseCase finishGameUseCase;

    @Autowired
    public GameCommandController(StartGameUseCase startGameUseCase, MoveCarUseCase moveCarUseCase, ApplicationEventPublisher eventPublisher, FinishGameUseCase finishGameUseCase){
        this.startGameUseCase = startGameUseCase;
        this.moveCarUseCase = moveCarUseCase;
        this.eventPublisher = eventPublisher;
        this.finishGameUseCase = finishGameUseCase;
    }

    @PostMapping("/createGame")
    public void createGame(@RequestBody CreateGame createGame){
        this.eventPublisher.publishEvent(createGame);
    }


    @PostMapping("/startGame")
    public String createGame(@RequestBody StartGame startGame){
        var game = startGameUseCase.apply(startGame);
        Optional.ofNullable(game).map(Game::id).ifPresent(g -> {
            var event = new RaceStarted();
            event.setGameId(startGame.getId());
            event.setTrackLength(startGame.getTrackLength());

            this.eventPublisher.publishEvent(event);
        });
        return String.valueOf(Optional.ofNullable(game).map( Game::trackLength).orElse(999)) ;
    }

    @PostMapping("/move")
    public String moveCar(@RequestBody MoveCar moveCar){

        var game = moveCarUseCase.apply(moveCar);
        final Player[] movedPlayer = new Player[1];
        Optional.ofNullable(game).ifPresent(g -> {

            g.players().forEach((playerId, player)->{
                if(playerId.equals(moveCar.getPlayerId())){
                    movedPlayer[0] = player;
                }

                if(player.carDrivenDistance()>= game.trackLength()){
                    if(Objects.isNull(game.winner()) ){
                        game.setWinner(playerId);
                    }
                    if(Objects.nonNull(game.winner())&& Objects.isNull(game.secondPlace())){
                        game.setSecondPlace(player);
                    }

                    if(Objects.nonNull(game.secondPlace())){
                        game.setThirdPlace(player);
                        finishGameUseCase.apply(moveCar);
                        var finishEvent = new GameFinished();

                        finishEvent.setGameId(moveCar.getGameId());
                        finishEvent.setPlayerId(playerId);
                        finishEvent.setPlayers(game.players());

                        this.eventPublisher.publishEvent(finishEvent);

                    }

                }
            });


            var event = new CarMoved();
            event.setGameId(moveCar.getGameId());
            event.setPlayerId(moveCar.getPlayerId());
            event.setDistanceMoved(movedPlayer[0].carDrivenDistance());
            event.setPlayers(game.players());

            this.eventPublisher.publishEvent(event);
        });
        return    Optional.ofNullable(game).map( Game::id).orElse("Nothing moved") ;
    }
}
