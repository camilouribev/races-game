package com.example.demo.domain.game;

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
}
