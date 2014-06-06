package com.tengen;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class InsertTwiceTest {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB db = client.getDB("school");
		DBCollection people = db.getCollection("people");
		
		people.drop();
		
		DBObject doc = new BasicDBObject("name", "Andrew Erlichson")
					.append("company", "10gen");
		
		try {
			System.out.println(doc);	
			people.insert(doc);
			System.out.println(doc);	
			doc.removeField("_id");
			System.out.println(doc);	
			people.insert(doc);
			System.out.println(doc);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
