package com.example.demo.infra;

import com.example.demo.domain.game.Game;
import com.example.demo.domain.game.GameBuilder;
import com.example.demo.domain.game.Gamer;
import com.example.demo.domain.usecase.GameRepository;
import com.example.demo.infra.documents.GameDocument;
import com.example.demo.infra.documents.GamerDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MongoGameRepository implements GameRepository {


    private  final MongoTemplate mongoTemplate;

    public MongoGameRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Game findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        GameDocument document =  mongoTemplate.findOne(query, GameDocument.class, "game");

        return Game.from(id, converterToBuilder(document));
    }

    private GameBuilder converterToBuilder(GameDocument document) {
        var builder = new GameBuilder();
        Optional.ofNullable(document).ifPresent(doc -> {
            builder.withInProgress(doc.getInProgress());
            doc.getGamers().forEach((key, gamerDocument) ->
                builder.addGamer(Gamer.from(gamerDocument.getId(), gamerDocument.getName()))
            );
            var winner = doc.getWinner();
            Optional.ofNullable(winner).ifPresent(w ->
                builder.addGamer(Gamer.from(w.getId(), w.getName()))
            );
        });

        return builder;
    }

    @Override
    public Game save(Game game) {
        GameDocument gameDocument = new GameDocument();

        gameDocument.setId(game.id());
        gameDocument.setInProgress(game.inProgress());
        Optional.ofNullable(game.winner()).ifPresent(w -> {
            gameDocument.setWinner(new GamerDocument(w.id(), w.name()));
        });
        game.gamers().forEach((key, gamer) -> {
            gameDocument.getGamers().put(key, new GamerDocument(gamer.id(), gamer.name()));
        });
        mongoTemplate.save(gameDocument, "game");

        return game;
    }
}
