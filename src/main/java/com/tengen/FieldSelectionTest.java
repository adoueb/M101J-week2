package com.tengen;

import java.net.UnknownHostException;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

public class FieldSelectionTest {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("course");
		DBCollection collection = courseDB.getCollection("fieldSelectionTest");
		
		collection.drop();
		Random rand = new Random();
		
		// insert 10 documents with three random integers
		for (int i = 0; i < 10; i++) {
			collection.insert(new BasicDBObject("x", rand.nextInt(2))		// random between 0 and 1
				.append("y", rand.nextInt(100))								// random between 0 and 99
				.append("z", rand.nextInt(1000)));							// random between 0 and 999
		}
		
		// Criteria (document).
		QueryBuilder builder = QueryBuilder.start("x").is(0)
				.and("y").greaterThan(10).lessThan(70);
		
		System.out.println("\nFind all:");
		DBCursor cursor = collection.find(builder.get(), new BasicDBObject("y", true).append("_id", false));
		try {
			while (cursor.hasNext()) {
				DBObject cur = cursor.next();
				System.out.println(cur);
			}
		} finally {
			cursor.close(); // DON'T FORGET TO CALL CLOSE 
		}
	}

}
