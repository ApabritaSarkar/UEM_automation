package shopperDummy;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;
//import io.restassured.RestAssured;

public class ShopperLoginTest {
	@Test
	public void loginTest() {
		HashMap map = new HashMap<>();
		map.put("email", "jhuma9475946465@gmail.com");
		map.put("password", "Apa@2004");
		map.put("role", "SHOPPER");
//		RestAssured.
		given()
		.contentType("application/json")
		.relaxedHTTPSValidation()
//		.body("{\r\n"
//				+ "  \"email\": \"jhuma9475946465@gmail.com\",\r\n"
//				+ "  \"password\": \"Apa@2004\",\r\n"
//				+ "  \"role\": \"SHOPPER\"\r\n"
//				+ "}")
		.body(map)
		
		.when()
		.post("https://www.shoppersstack.com/shopping/users/login")
		
		.then()
		.statusCode(200)
//		.assertThat().statusCode(201)
		.log().all();
		
		
	}
	
	@Test
	public void fetchData() {
		given()
		.relaxedHTTPSValidation()
		.contentType("application/json")
		.auth().oauth2("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqaHVtYTk0NzU5NDY0NjVAZ21haWwuY29tIFNIT1BQRVIiLCJleHAiOjE3NzMxNjc5ODUsImlhdCI6MTc3MzEzMTk4NX0.rvBL3_jvH9eDnU_yC4Mg3g1-aEOSusYaJzrCgTexwjo")
				.pathParam("shopperID", "363319")

				.when().get("https://www.shoppersstack.com/shopping/shoppers/{shopperID}").then().assertThat()
				.statusCode(200).log().all();
	}
}
