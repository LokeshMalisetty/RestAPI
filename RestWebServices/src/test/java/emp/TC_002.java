package emp;

import java.net.http.HttpRequest;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_002 extends TestBase{
	@BeforeClass
	void getUserBasedOnId() {
		logger.info("***** TC_002 is started *****");
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"employee/"+empID);
	}
	@Test
	void checkingResponseBody() {
		logger.info("***** Checking response Body *****");
		String responseBody = response.getBody().asString();
		logger.info("Response Body is:"+responseBody);
		Assert.assertEquals(responseBody.contains(empID),true);
	}
	@Test
	void checkingStatusCode1() {
		logger.info("***** Checking Status Code *****");
		int responseCode = response.getStatusCode();
		logger.info("Respnse Code is ==>"+responseCode);
		Assert.assertEquals(responseCode, 200);
	}
	@Test
	void checkingResponseTime() {
		logger.info("***** Checking Response Time *****");
		long responseTime = response.getTime();
		logger.info("Respnse Time is ==>"+responseTime);
		Assert.assertTrue(responseTime<3000);
	}
	@Test
	void checkingContentLength() {

		logger.info("***** Checking Content Length");
		String contentLength = response.getHeader("Content-Length");
		Assert.assertTrue(Integer.parseInt(contentLength)<1500);
	}
	@AfterClass
	void tearDown() {
		logger.info("***** Tc_002 is finished *****");
	}
}
