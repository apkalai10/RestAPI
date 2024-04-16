package com.RestAPI;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class PostTypes {
	
	@Test(priority = 0, enabled = false)
	void postUsingHashMap()
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", "arun");
		map.put("type", "TT");
		map.put("salary", 10000);
		String[] lang = {"html","css"};
		map.put("language", lang);
		
		given()
		.contentType("application/json")
		.body(map)
		.when()
		.post("http://localhost:3000/employee")
		.then()
		.statusCode(201)
		.body("name", equalTo("arun"))
		.body("language[0]", equalTo("html"))
		.header("Content-Type", "application/json") //"application/json; charset=utf-8"
		.log().all();
		}
	@Test(priority = 1,enabled = false)
	void UsingJson()
	{
		JSONObject obj = new JSONObject();
		obj.put("name", "kalai");
		obj.put("type", "FT");
		obj.put("salary", 67000);
		String[] langs = {"python","api"};
		obj.put("language", langs);
		given()
		.contentType("application/json")
		.body(obj.toString())
		.when()
		.post("http://localhost:3000/employee")
		.then()
		.statusCode(201)
		.body("name", equalTo("kalai"))
		.body("language[0]", equalTo("python"))
		.header("Content-Type", "application/json") //"application/json; charset=utf-8"
		.log().all();
	}
	
	
	@Test(priority = 2,enabled = false)
	void usingPOGO()
	{
		Pogo poo = new Pogo();
		poo.setName("mani");
		poo.setType("FT");
		poo.setSalary(12000);
		String[] arrs = {"c#","c++"};
		poo.setLanguage(arrs);
		given()
		.contentType("application/json")
		.body(poo)
		.when()
		.post("http://localhost:3000/employee")
		.then()
		.statusCode(201)
		.body("name", equalTo("mani"))
		.body("language[0]", equalTo("c#"))
		.header("Content-Type", "application/json") //"application/json; charset=utf-8"
		.log().all();
	}
	
	@Test(priority = 3)
	void usingExternalFile()
	{
		File file = new File(".\\body.json");
		try {
			FileReader read = new FileReader(file);
			JSONTokener jt = new JSONTokener(read);
			JSONObject datas = new JSONObject(jt);
			given()
			.contentType("application/json")
			.body(datas.toString())
			.when()
			.post("http://localhost:3000/employee")
			.then()
			.statusCode(201)
			.body("name", equalTo("murali"))
			.body("language[0]", equalTo("vb.net"))
			.header("Content-Type", "application/json") //"application/json; charset=utf-8"
			.log().all();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}

}
