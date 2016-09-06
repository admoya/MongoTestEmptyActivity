package com.example.moyaa.mongotestemptyactivity;

/**
 * Created by TubbyLumpkins on 9/2/16.
 */

import android.util.Log;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.Set;

public class MongoStore {

    private MongoClient mc;
    private MongoDatabase db;
    private MongoCollection<Document> collection;

    MongoStore(){
        MongoClientURI mcURI = new MongoClientURI("mongodb://test:test@ds019946.mlab.com:19946/lunchbox");
        //MongoClient mongoClient = new MongoClient( "localhost" , 27107 );
        mc = new MongoClient(mcURI);

        db = mc.getDatabase("lunchbox");
        collection = db.getCollection("yum");
    }

    public boolean insert(){

        try {
            Document d = new Document("name", "Kyle");
            collection.insertOne(d);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public void retrieveOne(String key, Object Value){
        FindIterable<Document> i = collection.find(new Document(key,Value));
        Document d = i.first();
        System.out.println(d);
    }
    public void retrieveMany(String key, Object Value){
        FindIterable<Document> i = collection.find(new Document(key,Value));
        for(Document d : i){
            //d = i.first();
            System.out.println(d);
        }
    }
    public void closeConn(){
        mc.close();
    }
}