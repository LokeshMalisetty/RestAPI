package emp;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.employee.utilities.RestUtils;
import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC_003 extends TestBase {
	RequestSpecification httpRequest;
	Response response;

	String empName=RestUtils.empName();
	String empSalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	@Test
	void createEmployee() throws InterruptedException {
		logger.info("***** TC_003 starts *****");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();

		JSONObject requestParams=new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age",empAge);

		//Add a header stating the request body is a json
		httpRequest.header("Content-Type", "application/json");

		//Add the JSON to the request
		httpRequest.body(requestParams.toJSONString());

		response=httpRequest.request(Method.POST,"/create");

		Thread.sleep(5000);

		logger.info("***** Checking Response Body *****");
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);

		logger.info("***** Checking Status Code *****");
		int statusCode=response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

		logger.info("***** Check Status Line *****");
		String statusLine = response.getStatusLine();
		Assert.assertEquals( statusLine,"HTTP/1.1 200 OK");

		logger.info("***** TC_003 is Finished *****");
	}
}
