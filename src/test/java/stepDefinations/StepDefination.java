package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data =new TestDataBuild();
	static String place_id;
	

//@Given("Add Place Payload with {string}  {string} {string}")
//public void add_Place_Payload_with(String name, String language, String address) throws IOException {
//	    // Write code here that turns the phrase above into concrete actions
//	
//		 
//		 res=given().spec(requestSpecification())
//		.body(data.addPlacePayLoad(name,language,address));
//	}
//
//@When("user calls {string} with {string} http request")
//public void user_calls_with_http_request(String resource, String method) {
//	    // Write code here that turns the phrase above into concrete actions
////constructor will be called with value of resource which you pass
//		APIResources resourceAPI=APIResources.valueOf(resource);
//		System.out.println(resourceAPI.getResource());
//		
//		
//		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
//		
//		if(method.equalsIgnoreCase("POST"))
//		 response =res.when().post(resourceAPI.getResource());
//		else if(method.equalsIgnoreCase("GET"))
//			 response =res.when().get(resourceAPI.getResource());
//				System.out.println(response);
//}
//
//	@Then("the API call got success with status code {int}")
//	public void the_API_call_got_success_with_status_code(Integer int1) {
//	    // Write code here that turns the phrase above into concrete actions
//		assertEquals(response.getStatusCode(),200);
//		
//	
//	}
//
//	@Then("{string} in response body is {string}")
//	public void in_response_body_is(String keyValue, String Expectedvalue) {
//	    // Write code here th.at turns the phrase above into concrete actions
//	  
//	 assertEquals(getJsonPath(response,keyValue),Expectedvalue);
//	}
//	
//	@Then("verify place_Id created maps to {string} using {string}")
//	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
//	
//	   // requestSpec
//	     place_id=getJsonPath(response,"place_id");
//		 res=given().spec(requestSpecification()).queryParam("place_id",place_id);
//		 user_calls_with_http_request(resource,"GET");
//		  String actualName=getJsonPath(response,"name");
//		  assertEquals(actualName,expectedName);
//		 
//	    
//	}
//	
//
//@Given("DeletePlace Payload")
//public void deleteplace_Payload() throws IOException {
//    // Write code here that turns the phrase above into concrete actions
//   
//	res =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
//}

	 @Given("Add Place Payload with {string}  {string} {string}")
	 public void add_place_payload_with(String name, String adress, String language) throws IOException {
		 req=RestAssured.given().spec(requestSpecification()).body(data.addPlacePayLoad(name,adress,language));
	 }
	 @When("user calls {string} with {string} http request")
	 public void user_calls_with_http_request(String resource, String method) {
		 APIResources resourceApi=APIResources.valueOf(resource);
		 //calls the constructor with resource name then variable value gets assigned
		 System.out.println(resourceApi.getResource());
		 // gets the variable value
		 
		if(method.equalsIgnoreCase("POST")) {
		   response = req.when()
	                .post(resourceApi.getResource());
		 
		}
		   else if(method.equalsIgnoreCase("GET"))
		   response = req.queryParam("place_id", place_id).when()
          .get(resourceApi.getResource());
	 }
	 @Then("the API call got success with status code {int}")
	 public void the_api_call_got_success_with_status_code(Integer int1) {
		//	System.out.println(response.asString());
			
			assertEquals(response.getStatusCode(),200);
	     
	 }
	 @Then("{string} in response body is {string}")
	 public void in_response_body_is(String keyValue, String Expectedvalue) {
	//	System.out.println(response.asString());
	//		String value=getJsonPath(response,keyValue);
		//	System.out.println(value);
				assertEquals(getJsonPath(response,keyValue),Expectedvalue);
	 }
	

	 @Then("verify place_Id created maps to {string} using {string}")
	 public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	     place_id=getJsonPath(response,"place_id");
		 res=given().spec(requestSpecification()).queryParam("place_id",place_id);
		
		 user_calls_with_http_request(resource,"GET");
		  String actualName=getJsonPath(response,"name");
		  assertEquals(actualName,expectedName);
		  System.out.println(actualName);
		  System.out.println(response.asString());
	 }





	 @Given("Delete Place Payload")
	 public void delete_place_payload() throws IOException {
		 System.out.println("Entered delete API");
		 req=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	//	 System.out.println(req);
	 }



}
