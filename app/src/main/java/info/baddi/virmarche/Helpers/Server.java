package info.baddi.virmarche.Helpers;

import android.util.Log;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

/**
 * Created by 5Baddi on 02-Mar-18.
 */

public class Server
{
    private MongoClient mongoClient;

    public MongoDatabase database;


    public Server(String host, int port, String user, String password, String db)
    {
        try{
            MongoCredential credential = MongoCredential.createCredential(user, db, password.toCharArray());
            mongoClient = new MongoClient(new ServerAddress(host, port), Arrays.asList(credential));
            database = mongoClient.getDatabase(db);
            Log.i("DB", database.getName());
        }catch(MongoException ex){
            ex.printStackTrace();
        }
    }

    public void insertData(String collection, BasicDBObject document)
    {
        try{
            MongoCollection<BasicDBObject> dbCollection = database.getCollection(collection, BasicDBObject.class);
            dbCollection.insertOne(document);
        }catch(MongoException ex){
            ex.printStackTrace();
        }
    }
}
