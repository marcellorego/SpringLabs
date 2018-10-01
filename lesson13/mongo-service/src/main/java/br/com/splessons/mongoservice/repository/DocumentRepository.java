package br.com.splessons.mongoservice.repository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentRepository.class);

    public static final String ID_FIELD = "_id";

    private final MongoClient mongoClient;


    public DocumentRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public Document storeDocument(String databaseName, String collectionName, Document document) {
        LOGGER.debug("storeDocument: database: {}, collection: {}", databaseName, collectionName);
        // remove any already set id, since this should be provided by Mongo DB on insert
        document.remove(ID_FIELD);
        getCollection(databaseName, collectionName).insertOne(document);
        return document;
    }

    private MongoCollection<Document> getCollection(String databaseName, String collectionName) {
        MongoDatabase db = mongoClient.getDatabase(databaseName);
        return db.getCollection(collectionName);
    }

}
