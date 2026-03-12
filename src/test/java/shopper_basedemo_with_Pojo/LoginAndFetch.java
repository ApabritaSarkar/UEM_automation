package shopper_basedemo_with_Pojo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import shopperCartPojo.BaseClass;

public class LoginAndFetch extends BaseClass{
	@Test
	public void loginTest() {
		ShopperLoginPojo data = new ShopperLoginPojo("jhuma9475946465@gmail.com", "Apa@2004", "SHOPPER");
		Response res = given()
				.contentType("application/json")
				.relaxedHTTPSValidation()
				.body(data)
				
				.when()
				.post("https://www.shoppersstack.com/shopping/users/login");
			System.out.println(res.prettyPrint());	
				
				shopperId = res.jsonPath().getString("data.userId");
				System.out.println("shopper Id : " + shopperId);
				
				jwtToken = res.jsonPath().getString("data.jwtToken");
				System.out.println("jwt token : " + jwtToken);
	}
}
