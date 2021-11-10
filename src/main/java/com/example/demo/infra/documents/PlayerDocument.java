package com.example.demo.infra.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PlayerDocument {
    @Id
    private String id;
    private String name;
    private Integer gamesWon;

    public PlayerDocument(){

    }


    public PlayerDocument(String id, String name, Integer gamesWon){
        this.id = id;
        this.name = name;
        this.gamesWon = gamesWon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(Integer gamesWon) {
        this.gamesWon = gamesWon;
    }
}
