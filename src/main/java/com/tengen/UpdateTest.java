package com.tengen;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class UpdateTest {

	public static void main(String[] args) throws UnknownHostException {
		
		DBCollection collection = createCollection();
		
		List<String> names = Arrays.asList("Alice", "Bobby", "Cathy", "David", "Ethan");
		for (String name : names) {
			collection.insert(new BasicDBObject("_id", name));
		}
		
		// Replacement.
		collection.update(new BasicDBObject("_id", "Alice"),
				new BasicDBObject("age", 24));
		
		// 2nd form of update.
		collection.update(new BasicDBObject("_id", "Alice"), 
				new BasicDBObject("$set", new BasicDBObject("gender", "F")));
		
		// Upsert.
		collection.update(new BasicDBObject("_id", "Frank"), 
				new BasicDBObject("$set", new BasicDBObject("gender", "M")), true /* upsert */, false /* multi */);
		
		// Add a title field to all documents.
		collection.update(new BasicDBObject(), 
				new BasicDBObject("$set", new BasicDBObject("title", "Dr.")), false /* upsert */, true /* multi */);
		
		collection.remove(new BasicDBObject("_id", "Alice"));
		
		printCollection(collection);
	}
	
	private static DBCollection createCollection() throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("course");
		DBCollection collection = courseDB.getCollection("updateTest");
		collection.drop();
		return collection;		
	}
	
	private static void printCollection(DBCollection collection) {
		DBCursor cur = collection.find();
		while (cur.hasNext()) {
			DBObject doc = cur.next();
			System.out.println(doc);
		}
	}

}