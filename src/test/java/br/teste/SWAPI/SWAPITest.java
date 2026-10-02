package br.teste.SWAPI;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class SWAPITest {

	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "https://swapi.dev/api/";
	}

	@Test
	public void consultaTodosPeople() {
		RestAssured.given().log().all().when().get("/people").then().statusCode(200).log().all();
	}

	@Test
	public void consultaPeople1() {
		RestAssured.given().log().all().when().get("/people/1").then().statusCode(200).log().all();
	}

	@Test
	public void consultarPeopleNaoExiste() {
		RestAssured.given().log().all().when().get("/people/8888").then().statusCode(404).log().all();
	}

}
