package shopper_basedemo_with_Pojo;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import org.testng.annotations.BeforeClass;
import io.restassured.response.Response;

public class BaseClass {
	public static String shopperId;
	public static String jwtToken;
	public static int productId = 55;
	 
	@BeforeClass
	public void login() {
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
	
}
