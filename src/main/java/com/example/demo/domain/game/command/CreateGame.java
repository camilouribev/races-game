package com.example.demo.domain.game.command;

import java.io.Serializable;
import java.util.Set;

public class CreateGame implements Serializable {
    private String id;
    private String type;
    private Set<String> gamers;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<String> getPlayers() {
        return gamers;
    }

    public void setPlayers(Set<String> gamers) {
        this.gamers = gamers;
    }
}
