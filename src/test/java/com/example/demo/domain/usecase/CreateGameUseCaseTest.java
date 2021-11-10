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
        command.setPlayers(new HashSet<>());
        command.getPlayers().add("raul");
        command.getPlayers().add("andres");
        command.getPlayers().add("carlos");
        command.getPlayers().add("pedro");

        createGameUseCase.apply(command);
        Mockito.verify(gameRepository).save(captor.capture());

        var result = captor.getValue();
        var players = result.players().values();
        Assertions.assertEquals("xxxxxx", result.id());
        Assertions.assertEquals(false, result.inProgress());
        Assertions.assertEquals(4, players.size());

    }

}