package com.example.demo.domain.usecase;

import com.example.demo.domain.game.Game;
import com.example.demo.domain.game.command.CreateGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;




@ExtendWith({MockitoExtension.class})
class CreateGameUseCaseTest {

    @InjectMocks
    CreateGameUseCase createGameUseCase;

    @Mock
    GameRepository gameRepository;

    @Captor
    ArgumentCaptor<Game> captor;

    @Test
     void createGame(){
        CreateGame command = new CreateGame();
        command.setId("xxxxxx");
        command.setType("gamer.command");
        command.setGamers(new HashSet<>());
        command.getGamers().add("raul");
        command.getGamers().add("andres");
        command.getGamers().add("carlos");
        command.getGamers().add("pedro");

        createGameUseCase.apply(command);
        Mockito.verify(gameRepository).save(captor.capture());

        var result = captor.getValue();
        var gamers = result.gamers().values();
        Assertions.assertEquals("xxxxxx", result.id());
        Assertions.assertEquals(false, result.inProgress());
        Assertions.assertEquals(4, gamers.size());

    }

}