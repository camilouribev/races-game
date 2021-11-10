package com.example.demo.domain.game;

import java.util.Objects;

public class Player {
    private String id;
    private String name;
    private Integer gamesWon;

    public Player(String id, String name, Integer gamesWon) {
        this.id = id;
        this.name = name;
        this.gamesWon = gamesWon;
    }

    public static Player from(String id, String name, Integer gamesWon){
        return new Player(id, name, gamesWon);
    }

    public String name() {
        return name;
    }

    public String id(){
        return id;
    }

    public Integer gamesWon() {
        return gamesWon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) && Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
