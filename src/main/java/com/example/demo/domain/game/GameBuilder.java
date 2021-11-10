package com.example.demo.domain.game;

import java.util.HashMap;
import java.util.Map;

public class GameBuilder {
    private final Map<String, Player> players;
    private Boolean inProgress;
    private Player winner;

    public GameBuilder(){
        this.players = new HashMap<>();
        this.inProgress = false;
    }

    public static GameBuilder builder(){
        return new GameBuilder();
    }

    public GameBuilder withWinner(Player winner){
        this.winner = winner;
        return this;
    }


    public GameBuilder withInProgress(Boolean inProgress){
        this.inProgress = inProgress;
        return this;
    }


    public GameBuilder addPlayer(Player player){
        players.put(player.id(), player);
        return this;
    }

    public Boolean getInProgress() {
        return inProgress;
    }

    public Player getWinner() {
        return winner;
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

}
