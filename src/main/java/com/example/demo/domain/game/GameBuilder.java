package com.example.demo.domain.game;

import java.util.HashMap;
import java.util.Map;

public class GameBuilder {
    private final Map<String, Gamer> gamers;
    private Boolean inProgress;
    private Gamer winner;

    public GameBuilder(){
        this.gamers = new HashMap<>();
        this.inProgress = false;
    }

    public static GameBuilder builder(){
        return new GameBuilder();
    }

    public GameBuilder withWinner(Gamer winner){
        this.winner = winner;
        return this;
    }


    public GameBuilder withInProgress(Boolean inProgress){
        this.inProgress = inProgress;
        return this;
    }


    public GameBuilder addGamer(Gamer gamer){
        gamers.put(gamer.id(), gamer);
        return this;
    }

    public Boolean getInProgress() {
        return inProgress;
    }

    public Gamer getWinner() {
        return winner;
    }

    public Map<String, Gamer> getGamers() {
        return gamers;
    }

}
