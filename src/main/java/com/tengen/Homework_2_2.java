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

public class Homework_2_2 {

	public static void main(String[] args) throws UnknownHostException {
		
		DBCollection collection = getCollection();
		
		DBCursor cur = collection.find(new BasicDBObject("type", "homework"))
				.sort(new BasicDBObject("student_id", 1)
				.append("score", 1));
		
		// Iterate through documents.
		int previousStudentId = -1;
		while (cur.hasNext()) {
			DBObject currentDoc = cur.next();
			int currentStudentId = (Integer)currentDoc.get("student_id");
			if (previousStudentId != currentStudentId) {
				// Remove the current doc.
				collection.remove(currentDoc);
				previousStudentId = currentStudentId;
			}
		}
		
		//printFromCursor(cur);
	}
	
	private static DBCollection getCollection() throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB courseDB = client.getDB("students");
		DBCollection collection = courseDB.getCollection("grades");
		return collection;
	}
	
	private static void printCollection(DBCollection collection) {
		DBCursor cur = collection.find();
		printFromCursor(cur);
	}
	
	private static void printFromCursor(DBCursor cur) {
		while (cur.hasNext()) {
			DBObject doc = cur.next();
			System.out.println(doc);
		}
	}

}