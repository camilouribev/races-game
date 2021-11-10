package com.example.demo.domain.game;

import java.util.Objects;

public class Gamer {
    private String id;
    private String name;

    public Gamer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Gamer from(String id, String name){
        return new Gamer(id, name);
    }

    public String name() {
        return name;
    }

    public String id(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gamer gamer = (Gamer) o;
        return Objects.equals(id, gamer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
