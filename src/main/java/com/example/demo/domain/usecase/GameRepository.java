package com.example.demo.domain.usecase;

import com.example.demo.domain.game.Game;

public interface GameRepository {
    Game findById(String id);

    Game save(Game game);
}
