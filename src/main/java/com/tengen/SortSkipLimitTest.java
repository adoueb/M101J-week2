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

public class SortSkipLimitTest {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB db = client.getDB("course");
		DBCollection lines = db.getCollection("DotNotationTest");
		
		lines.drop();
		Random rand = new Random();
		
		// insert 10 documents
		for (int i = 0; i < 10; i++) {
			lines.insert(new BasicDBObject("_id", i)
				.append("start", 
						new BasicDBObject("x", rand.nextInt(2))
									.append("y", rand.nextInt(90) + 10))	
				.append("end",
						new BasicDBObject("x", rand.nextInt(2))
									.append("y", rand.nextInt(90) + 10)));	
		}
		
		
		DBCursor cursor = lines.find()
				.sort(new BasicDBObject("start.x",1)
					.append("start.y",-1)).skip(2).limit(10);
		
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
