package com.example.demo.domain.game.command;

import java.io.Serializable;
import java.util.Map;

public class StartGame implements Serializable {
    private String id;
    private Map<String, Integer> gamerBet;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Integer> getGamerBet() {
        return gamerBet;
    }

    public void setGamerBet(Map<String, Integer> gamerBet) {
        this.gamerBet = gamerBet;
    }
}
