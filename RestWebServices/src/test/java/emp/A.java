package emp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class A extends TestBase{
	@BeforeClass
	void getAllEmployees() {
		logger.info("********started  A***********");
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/booking");
	}
	
	
	@Test
	void users() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		RequestSpecification httprequest = RestAssured.given();
		Response response = httprequest.request(Method.GET,"/booking");
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println(response.getBody().asString());
}
}
