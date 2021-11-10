package com.example.demo.entrypoint;

import com.example.demo.infra.documents.GameDocument;
import com.example.demo.infra.documents.PlayerDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class GameQueryController {

    private final MongoTemplate mongoTemplate;

    public GameQueryController(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/game/{id}")
    public GameDocument game(@PathVariable("id") String id){
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, GameDocument.class, "game");
    }

    @GetMapping("/game/{id}/winner")
    public PlayerDocument winner(@PathVariable("id") String id){
        Query query = new Query(Criteria.where("_id").is(id));
        return Optional
                .ofNullable(mongoTemplate.findOne(query, GameDocument.class, "game"))
                .map(GameDocument::getWinner)
                .orElse(null);
    }

}
