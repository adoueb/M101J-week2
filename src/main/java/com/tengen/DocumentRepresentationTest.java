package com.tengen;

import java.util.Arrays;
import java.util.Date;

import com.mongodb.BasicDBObject;

public class DocumentRepresentationTest {
	public static void main(String[] args) {
		
		BasicDBObject doc = new BasicDBObject();
		doc.put("userName", "adoueb");
		doc.put("birthDate", new Date(234832423));
		doc.put("programmer", true);
		doc.put("age", 8);
		doc.put("languages", Arrays.asList("Java","C++"));
		doc.put("address", new BasicDBObject("street", "20 main")	// Subdocument or nested document
			    .append("town", "Westfild")
			    .append("zip", "56789"));
		
		System.out.println(doc);
	}
}
