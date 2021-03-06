package com.example.demo.domain.game;

import java.util.Objects;

public class Player {
    private String id;
    private String name;
    private Integer carDrivenDistance;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.carDrivenDistance = 0;

    }
    public Player(String id, String name, Integer carDrivenDistance) {
        this.id = id;
        this.name = name;
        this.carDrivenDistance = carDrivenDistance;
    }


    public static Player from(String id, String name, Integer carDrivenDistance){
        return new Player(id, name, carDrivenDistance);
    }

    public String name() {
        return name;
    }

    public String id(){
        return id;
    }

    public Integer carDrivenDistance() {
        return carDrivenDistance;
    }

    public void move(Integer kilometers){ this.carDrivenDistance += kilometers;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) && Objects.equals(name, player.name) && Objects.equals(carDrivenDistance, player.carDrivenDistance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, carDrivenDistance);
    }
}
