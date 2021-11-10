package com.example.demo.infra.handles;

import com.example.demo.domain.WinnerFound;

import com.example.demo.infra.documents.GameDocument;
import com.example.demo.infra.documents.GamerDocument;
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
        update.set("winner", new GamerDocument(winnerFound.getId(), winnerFound.getName()));
        update.set("inProgress", false);

        mongoTemplate.updateFirst(query, update, GameDocument.class, "game").getMatchedCount();
    }
}
