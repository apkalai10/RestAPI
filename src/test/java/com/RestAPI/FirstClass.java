package com.RestAPI;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

class FirstClass {

	int id;
	
	@Test(priority = 0,enabled = true)
	 void getUsers()
	{
		given()
		.when()
		.get("https://reqres.in/api/users?page=2")
		.then()
		.statusCode(200)
		.body("page", equalTo(2))
		.log().all();
	}
	
	
	@Test(priority = 1,enabled = true)
	void createUser()
	{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", "aravinth");
		map.put("job","tech");
		id = given()
		.contentType("application/json")
		.body(map)
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		/*.then()
		.statusCode(201)
		.log().all();*/
		
	}
	 
	@Test(priority = 2,enabled = true,dependsOnMethods = {"createUser"})
	void updateuser()
	{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", "aravinth");
		map.put("job","gym");
		given()
		.contentType("application/json")
		.body(map)
		.when()
		.put("https://reqres.in/api/users/"+id)
		.then()
		.statusCode(200)
		.log().all();
		
		
		
	}
	
	@Test(priority = 3,enabled = true,dependsOnMethods = {"updateuser"})
	void deleteuser()
	{
		given()
		.when()
		.delete("https://reqres.in/api/users/"+id)
		.then()
		.statusCode(204)
		.log().all();
	}
	
}
