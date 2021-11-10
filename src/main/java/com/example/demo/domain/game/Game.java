package com.example.demo.domain.game;

import java.util.HashMap;
import java.util.Map;

public class Game {

    private final  String id;
    private  Map<String, Player> players;
    private Boolean inProgress;
    private Player winner;
    private Integer gamesWon;

    public Game(String id) {
        this.id = id;
        this.players = new HashMap<>();
        this.inProgress = false;
    }

    public static Game from(String id, GameBuilder factory){
        var game = new Game(id);
        game.winner = factory.getWinner();
        game.players = factory.getPlayers();
        game.inProgress = factory.getInProgress();
        return game;
    }

    public void addPlayer(String id, String name){
        this.players.put(id, new Player(id, name, gamesWon));
    }

    public void startGame(){
        this.inProgress = true;
    }

    public void setWinner(String playerId){
       this.winner = this.players.get(playerId);
        this.inProgress = false;
    }

    public String id() {
        return id;
    }

    public Boolean inProgress() {
        return inProgress;
    }

    public Player winner() {
        return winner;
    }

    public Map<String, Player> players() {
        return players;
    }
}
