package com.example.demo.domain.game;

import java.util.HashMap;
import java.util.Map;

public class Game {

    private final  String id;
    private  Map<String, Gamer> gamers;
    private Boolean inProgress;
    private Gamer winner;

    public Game(String id) {
        this.id = id;
        this.gamers = new HashMap<>();
        this.inProgress = false;
    }

    public static Game from(String id, GameBuilder factory){
        var game = new Game(id);
        game.winner = factory.getWinner();
        game.gamers = factory.getGamers();
        game.inProgress = factory.getInProgress();
        return game;
    }

    public void addGamer(String id, String name){
        this.gamers.put(id, new Gamer(id, name));
    }

    public void startGame(){
        this.inProgress = true;
    }

    public void setWinner(String gamerId){
       this.winner = this.gamers.get(gamerId);
        this.inProgress = false;
    }

    public String id() {
        return id;
    }

    public Boolean inProgress() {
        return inProgress;
    }

    public Gamer winner() {
        return winner;
    }

    public Map<String, Gamer> gamers() {
        return gamers;
    }
}
