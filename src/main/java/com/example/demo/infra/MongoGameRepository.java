package com.example.demo.infra;

import com.example.demo.domain.game.Game;
import com.example.demo.domain.game.GameBuilder;
import com.example.demo.domain.game.Player;
import com.example.demo.domain.usecase.GameRepository;
import com.example.demo.infra.documents.GameDocument;
import com.example.demo.infra.documents.PlayerDocument;
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
            builder.withTrackLength(doc.getTrackLength());

            builder.withInProgress(doc.getInProgress());
            doc.getPlayers().forEach((key, gamerDocument) ->
                builder.addPlayer(Player.from(gamerDocument.getId(), gamerDocument.getName(), gamerDocument.getCarDrivenDistance()))
            );
            var winner = doc.getWinner();
            Optional.ofNullable(winner).ifPresent(w ->
                builder.addPlayer(Player.from(w.getId(), w.getName(), w.getCarDrivenDistance()))
            );
        });

        return builder;
    }

    @Override
    public Game save(Game game) {
        GameDocument gameDocument = new GameDocument();

        gameDocument.setId(game.id());
        gameDocument.setInProgress(game.inProgress());
        gameDocument.setTrackLength(game.trackLength());
        Optional.ofNullable(game.winner()).ifPresent(w -> {
            gameDocument.setWinner(new PlayerDocument(w.id(), w.name(), w.carDrivenDistance()));
        });
        game.players().forEach((key, gamer) -> {
            gameDocument.getPlayers().put(key, new PlayerDocument(gamer.id(), gamer.name(), gamer.carDrivenDistance()));
        });
        mongoTemplate.save(gameDocument, "game");

        return game;
    }
}
