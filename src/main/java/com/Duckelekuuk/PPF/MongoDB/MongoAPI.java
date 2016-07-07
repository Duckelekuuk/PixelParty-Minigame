package com.Duckelekuuk.PPF.MongoDB;

import com.Duckelekuuk.PPF.PixelPartyFrame;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public class MongoAPI {

    private DBCollection collection;
    private BasicDBObject search_query;
    private DBObject result;

    public MongoAPI(String field, String value) {
        collection = PixelPartyFrame.getMongoDB().getDatabase().getCollection("statistics");

        search_query = new BasicDBObject();
        search_query.put(field, value);

        result = collection.findOne(search_query);
    }

    public Object get(String field) {
        if (result.get(field) != null) {
            return result.get(field);
        }
        return null;
    }

    public boolean exists() {
        return result != null;
    }

    public void update(String field, Object value) {
        BasicDBObject updated = new BasicDBObject();
        updated.putAll(result);
        updated.remove(field);
        updated.put(field, value);
        BasicDBObject fUpdate = new BasicDBObject();
        fUpdate.put("$set", updated);
        collection.update(search_query, fUpdate);
    }

    public void remove(String field) {
        BasicDBObject updated = new BasicDBObject();
        updated.putAll(result);
        updated.remove(field);
        BasicDBObject fUpdate = new BasicDBObject();
        fUpdate.put("$set", updated);
        collection.update(search_query, fUpdate);
    }

    public void add(String field, Object value) {
        BasicDBObject add = new BasicDBObject();
        add.put(field, value);
        collection.insert(add);
    }
}
