package com.Duckelekuuk.PPF.MongoDB;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

/**
 * @AUTHOR Duco.
 * Description
 */
public class MongoDB {

    private MongoClient mongo = null;
    private DB database = null;

    public MongoClient getMongo() {
        if (this.mongo == null) {
            this.mongo = new MongoClient(new ServerAddress("127.0.0.1", 22222));
        }
        return this.mongo;
    }

    public MongoDB(String host, int port) {
        this.mongo = new MongoClient(new ServerAddress(host, port));
    }

    public DB getDatabase() {
        if (this.database == null) {
            this.database = getMongo().getDB("PixelParty");
        }
        return this.database;
    }

    public void closeConnection() {
        if (this.mongo != null) {
            this.mongo.close();
        }
    }
}