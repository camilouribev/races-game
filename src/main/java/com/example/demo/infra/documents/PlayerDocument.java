package com.example.demo.infra.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PlayerDocument {
    @Id
    private String id;
    private String name;
    private Integer carDrivenDistance;

    public PlayerDocument(){

    }

    public PlayerDocument(String id, String name, Integer carDrivenDistance){
        this.id = id;
        this.name = name;
        this.carDrivenDistance = carDrivenDistance;
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

    public Integer getCarDrivenDistance() {
        return carDrivenDistance;
    }

    public void setCarDrivenDistance(Integer carDrivenDistance) {
        this.carDrivenDistance = carDrivenDistance;
    }
}
