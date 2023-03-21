package emp;

import java.net.http.HttpRequest;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_005 extends TestBase {
	RequestSpecification httpRequest;
	Response response;
	@BeforeClass
	void deleteEmployee() throws InterruptedException {
		logger.info("***** TC_005 is Started *****");
		RestAssured.baseURI="https://reqres.in";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/api/users?page=2");
		//First get the JSONPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		//Capture ID
		empID=jsonPathEvaluator.get("[0].id");
		response=httpRequest.request(Method.DELETE,"/api/users/"+empID);

		Thread.sleep(3000);
	}
//		@Test
//		void checkResponseBody() {
//			logger.info("***** checking Response Body *****");
//			String responseBody = response.getBody().asString();
//			logger.info("Response Body is ==>"+responseBody);
//	  		Assert.assertEquals(responseBody.contains("successfully deleted Records"),true);
//	}
	@Test
	void checkStatusCode() {
		logger.info("***** Checking Status Code *****");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 204);
	}
	@AfterClass
	void tearDown() {
		logger.info("***** TC_005 is finished *****");
	}
}
