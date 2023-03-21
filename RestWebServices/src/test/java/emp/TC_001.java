package emp;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

public class TC_001 extends TestBase{

	@BeforeClass
	void getUsers() throws InterruptedException {
		logger.info("***** started TC_001 *****");
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
		RequestSpecification httpRequest = RestAssured.given();
		response=httpRequest.request(Method.GET,"/employees");
		Thread.sleep(7000);
	}
	@Test(priority = 1)
	void responseBody() {
		String responseBody = response.getBody().asString();
		logger.info("***** Checking response Body *****");
		logger.info("Response Body :"+responseBody);
		System.out.println(responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	@Test(priority = 2)
	void statusCode() {
		logger.info("***** Checking Status Code *****");
		logger.info(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority = 3)
	void responseTime() {
		logger.info("***** Checking Response Time *****");
		long responseTime=response.getTime();
		logger.info("Response Time is ==>"+responseTime);
		if(responseTime>2000) {
			logger.warn("response time is greater than 2000");
		}
		Assert.assertTrue(responseTime<3000);
	}
	@Test(priority = 4)
	void statusLine() {
		logger.info("***** Checking Status Line *****");
		String statusLine = response.getStatusLine();
		logger.info("Status Line is ==> "+statusLine);
		Assert.assertEquals( statusLine, "HTTP/1.1 200 OK");
	}
	@Test(priority = 5)
	void contentType() {
		logger.info("***** Checking Content Type *****");
		String contentType = response.getHeader("Content-Type");
		logger.info("Content Type is ==> "+ contentType);
		Assert.assertEquals(contentType, "application/json");
	}
	@Test(priority = 6)
	void serverType() {
		logger.info("***** Checking Server Type *****");
		String serverType = response.getHeader("Server");
		logger.info("Server Type ==> "+ serverType);
		Assert.assertEquals(serverType, "nginx/1.21.6");
	}
	@Test(priority = 7)
	void contentEncoding() {
		logger.info("***** Checking Content Encoding *****");
		String contentEncoding = response.getHeader("Content-Encoding");
		logger.info("Content Encoding is ==>"+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}
	@Test(priority = 8)
	void contentLength() {
		logger.info("***** Content Length *****");
		String contentLength = response.getHeader("Content-Length");
		logger.info("Content Length is ==>"+contentLength);
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
		if(Integer.parseInt(contentLength)<100) {
			logger.warn("Content length is less than 100");
		}
	}
	@Test(priority = 9)
	void cookies() {
		logger.info("***** Checking cookies *****");
		String cookie = response.getCookie("PHPSESSID");
		logger.info("Cookie generated is ==>"+cookie);
	}
	@AfterClass
	void tearDown() {

		logger.info("***** TC_001 is finished *****");
	}
}
