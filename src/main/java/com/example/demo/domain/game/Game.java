package com.example.demo.domain.game;

import java.util.HashMap;
import java.util.Map;

public class Game {

    private final  String id;
    private  Map<String, Player> players;
    private Boolean inProgress;
    private Integer trackLength;
    private Player winner;
    private Player secondPlace;
    private Player thirdPlace;

    public Game(String id) {
        this.id = id;
        this.trackLength=100;
        this.players = new HashMap<>();
        this.inProgress = false;
    }

    public static Game from(String id, GameBuilder factory){
        var game = new Game(id);
        game.winner = factory.getWinner();
        game.players = factory.getPlayers();
        game.trackLength = factory.getTrackLength();
        game.inProgress = factory.getInProgress();
        game.secondPlace = factory.getSecondPlace();
        game.thirdPlace = factory.getThirdPlace();

        return game;
    }

    public void addPlayer(String id, String name){
        this.players.put(id, new Player(id, name));
    }

    public void startGame(Integer kilometers){
        this.inProgress = true;
        this.trackLength = kilometers;
    }

    public void endGame(){
        this.inProgress = false;
    }

    public void setWinner(String playerId){
       this.winner = this.players.get(playerId);
    }

    public void setTrackLength(Integer trackLength) {
        this.trackLength = trackLength;
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

    public Integer trackLength() {
        return trackLength;
    }

    public Map<String, Player> players() {
        return players;
    }

    public Player secondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(Player secondPlace) {
        this.secondPlace = secondPlace;
    }

    public Player thirdPlace() {
        return thirdPlace;
    }

    public void setThirdPlace(Player thirdPlace) {
        this.thirdPlace = thirdPlace;
    }
}
