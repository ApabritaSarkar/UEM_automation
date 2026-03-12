package shopperDummy;

import java.util.HashMap;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

public class FetchDetails {
	String shopperId;
	String jwtToken;
	@Test
	public void loginTest() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("email", "jhuma9475946465@gmail.com");
		map.put("password", "Apa@2004");
		map.put("role", "SHOPPER");
		
		Response res = given()
		.contentType("application/json")
		.relaxedHTTPSValidation()
		.body(map)
		
		.when()
		.post("https://www.shoppersstack.com/shopping/users/login");
		
		
		shopperId = res.jsonPath().getString("data.userId");
		System.out.println("shopper Id : " + shopperId);
		
		jwtToken = res.jsonPath().getString("data.jwtToken");
		System.out.println("jwt token : " + jwtToken);
	}
	
	@Test (dependsOnMethods = "loginTest")
	public void fetchData() {
		Response res = given()
		.relaxedHTTPSValidation()
		.contentType("application/json")
		.auth().oauth2(jwtToken)
		.pathParam("shopperID", "363319")
		.body("{\r\n"
				+ "  \"email\": \"jhuma9475946465@gmail.com\",\r\n"
				+ "  \"password\": \"Apa@2004\",\r\n"
				+ "  \"role\": \"SHOPPER\"\r\n"
				+ "}")
				
				
		.baseUri("https://www.shoppersstack.com/shopping")
		.when().get("/shoppers/{shopperID}");
	}
}
