package com.tengen;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class InsertTest {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("course");
		DBCollection collection = courseDB.getCollection("insertTest");
		
		collection.drop();
		
		DBObject doc = new BasicDBObject().append("x", 1);
		DBObject doc2 = new BasicDBObject().append("x", 2);	
		System.out.println(doc);		
		collection.insert(Arrays.asList(doc, doc2));		
		System.out.println(doc);
		
		DBObject docbis = new BasicDBObject("_id", new ObjectId()).append("x", 1);		
		System.out.println(docbis);		
		collection.insert(docbis);		
		System.out.println(docbis);
	}

}
