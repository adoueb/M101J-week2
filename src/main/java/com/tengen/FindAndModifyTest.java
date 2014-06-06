package com.tengen;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


public class FindAndModifyTest {

	public static void main(String[] args) throws UnknownHostException {
		
		DBCollection collection = createCollection();
		
		final String counterId = "abc";
		int first;
		int numNeeded;
		
		numNeeded = 2;
		first = getRange(counterId, numNeeded, collection);
		System.out.println("Range: " + first + "-" + (first + numNeeded - 1));
		
		numNeeded = 3;
		first = getRange(counterId, numNeeded, collection);
		System.out.println("Range: " + first + "-" + (first + numNeeded - 1));
		
		numNeeded = 10;
		first = getRange(counterId, numNeeded, collection);
		System.out.println("Range: " + first + "-" + (first + numNeeded - 1));
		
		System.out.println();
		
		printCollection(collection);
	}
	
	private static int getRange(String id, int range, DBCollection collection) {
		DBObject doc = collection.findAndModify(new BasicDBObject("_id", id), 
												null /* fields back: we don't care */,
				                                null /* sort: not used here */,
				                                false /* don't remove the doc after modification */,
				                                new BasicDBObject("$inc", new BasicDBObject("counter", range)) /* update */,
				                                true /* value after modification */,
				                                true /* upsert */);
		return (Integer)doc.get("counter") - range + 1; // get return the value after the range.
	}
	
	private static DBCollection createCollection() throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("course");
		DBCollection collection = courseDB.getCollection("findAndModifyTest");
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

