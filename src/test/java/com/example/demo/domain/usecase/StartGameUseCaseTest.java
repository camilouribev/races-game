package com.example.demo.domain.usecase;

import com.example.demo.domain.game.Game;
import com.example.demo.domain.game.GameBuilder;
import com.example.demo.domain.game.Gamer;
import com.example.demo.domain.game.command.StartGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Objects;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class StartGameUseCaseTest {
    @InjectMocks
    StartGameUseCase startGameUseCase;

    @Mock
    GameRepository gameRepository;

    @Captor
    ArgumentCaptor<Game> captor;

    @Test
    void startGame(){
        StartGame command = new StartGame();
        command.setId("fff-fff");
        command.setGamerBet(new HashMap<>());
        command.getGamerBet().put("1", 1);
        command.getGamerBet().put("2", 2);
        command.getGamerBet().put("3", 3);
        command.getGamerBet().put("4", 4);
        command.getGamerBet().put("5", 5);
        command.getGamerBet().put("6", 6);
        Game game = Game.from("fff-fff", GameBuilder
                .builder()
                .addGamer(new Gamer("1", "raul"))
                .addGamer(new Gamer("2", "andres"))
                .addGamer(new Gamer("3", "gomez"))
                .addGamer(new Gamer("4", "pedro"))
                .addGamer(new Gamer("5", "santiago"))
                .addGamer(new Gamer("6", "felipe"))
                .withInProgress(false)
        );
        when(gameRepository.findById("fff-fff")).thenReturn(game);

        Game result = startGameUseCase.apply(command);

        Assertions.assertTrue(Objects.nonNull(result.winner()));
        Assertions.assertEquals(false, result.inProgress());
        verify(gameRepository).findById("fff-fff");
    }
}