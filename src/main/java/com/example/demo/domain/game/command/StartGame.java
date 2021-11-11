package com.example.demo.domain.game.command;

import java.io.Serializable;
import java.util.Map;

public class StartGame implements Serializable {
    private String id;
    private Integer trackLength;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Integer getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(Integer trackLength) {
        this.trackLength = trackLength;
    }
}
