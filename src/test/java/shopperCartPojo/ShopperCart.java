package shopperCartPojo;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ShopperCart extends BaseClass{
	int productId, quantity;
	@Test
	public void fetchAllProducts() {
		Response res = given()
				.relaxedHTTPSValidation()
				.contentType("application/json")
				.auth().oauth2(jwtToken)
				
				.baseUri("https://www.shoppersstack.com/shopping")
				
				.when().get("/products/alpha");


		List<Integer> productIds = res.jsonPath().getList("data.productId");
		List<Integer> quantities = res.jsonPath().getList("data.quantity");

		productId = productIds.get(0);
		quantity = quantities.get(0);
		System.out.println("product id is : " + productId);
		System.out.println("product quantity is : " + quantity);
	}
	
	@Test(dependsOnMethods = "fetchAllProducts")
	public void addToCart() {
		ShopperCartPojo content = new ShopperCartPojo(productId, quantity);
		Response res = given()
				.auth().oauth2(jwtToken)
				.contentType("application/json")
				.relaxedHTTPSValidation()
				.body(content)
				.pathParam("shopperId", shopperId)
				
				.when()
				.post("https://www.shoppersstack.com/shopping/shoppers/{shopperId}/carts");
		
		res.then().log().all();
	}
	
	@Test(dependsOnMethods = "addToCart")
	public void deleteCart() {
		Response res = given()
				.auth().oauth2(jwtToken)
				.contentType("application/json")
				.relaxedHTTPSValidation()
				.pathParam("shopperId", shopperId)
				.pathParam("productId", productId)
				
				.when()
				.delete("https://www.shoppersstack.com/shopping/shoppers/{shopperId}/carts/{productId}");
		
		res.then().log().all();
		
		System.out.println("this is second commit");
		System.out.println("this is third commit");
	}
}
