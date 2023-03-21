package com.employeeapi.base;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	public static RequestSpecification httpRequest;
	public static Response response;
	public org.apache.log4j.Logger logger;
	public String  empID="1";

	@BeforeClass
	@Test
	public void setUp() {
		logger=org.apache.log4j.Logger.getLogger("EmployeesRestApi");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(org.apache.log4j.Level.DEBUG);
	}
}
