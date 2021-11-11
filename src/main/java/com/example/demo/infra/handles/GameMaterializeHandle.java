package com.example.demo.infra.handles;

import com.example.demo.domain.CarMoved;
import com.example.demo.domain.RaceStarted;
import com.example.demo.domain.WinnerFound;

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
    public void handle(WinnerFound winnerFound) {
        Query query = new Query(Criteria.where("_id").is(winnerFound.getGameId()));
        Update update = new Update();
        update.set("winner", new PlayerDocument(winnerFound.getId(), winnerFound.getName(), winnerFound.getCarDrivenDistance()));
        update.set("inProgress", false);


        mongoTemplate.updateFirst(query, update, GameDocument.class, "game").getMatchedCount();
    }

    @EventListener
    public void handle(RaceStarted raceStarted) {
        System.out.println(raceStarted.getGameId()+ " we're in event listener for started race");
        Query query = new Query(Criteria.where("_id").is(raceStarted.getGameId()));
        Update update = new Update();
        update.set("inProgress", true);
        update.set("trackLength", raceStarted.getTrackLength());


        mongoTemplate.updateFirst(query, update, GameDocument.class, "game").getMatchedCount();
    }

    @EventListener
    public void handle(CarMoved carMoved) {
        System.out.println(carMoved.getGameId()+ " we're in event listener for a car moving");
        Query query = new Query(Criteria.where("_id").is(carMoved.getGameId()));
        Update update = new Update();
        update.set("inProgress", true);
        update.set("players", carMoved.getPlayers());


        mongoTemplate.updateFirst(query, update, GameDocument.class, "game").getMatchedCount();
    }


}
