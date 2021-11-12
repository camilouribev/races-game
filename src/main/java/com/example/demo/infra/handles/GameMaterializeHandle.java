package com.example.demo.infra.handles;

import com.example.demo.domain.game.events.*;

import com.example.demo.infra.documents.GameDocument;
import com.example.demo.infra.documents.PlayerDocument;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class GameMaterializeHandle {
    private  final MongoTemplate mongoTemplate;

    public GameMaterializeHandle(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }



    @EventListener
    public void handle(RaceStarted raceStarted) {
        Query query = new Query(Criteria.where("_id").is(raceStarted.getGameId()));
        Update update = new Update();
        update.set("inProgress", true);
        update.set("trackLength", raceStarted.getTrackLength());


        mongoTemplate.updateFirst(query, update, GameDocument.class, "game").getMatchedCount();
    }

    @EventListener
    public void handle(CarMoved carMoved) {
        Query query = new Query(Criteria.where("_id").is(carMoved.getGameId()));
        Update update = new Update();
        update.set("players", carMoved.getPlayers());

        mongoTemplate.updateFirst(query, update, GameDocument.class, "game").getMatchedCount();
    }




}
