package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;



public class StepdefLibrary extends Utils {
	RequestSpecification req2;
	Response response;
	RequestSpecification request;
	TestDataBuild data =new TestDataBuild();
	static String BookId;
	APIResources resourceApi;
	 String expectedName;
	    String expectedIsbn;
	    String expectedAisle;
	    String expectedAuthor;
	
	@Given("Add the book with the payload {string} {string} {string} and {string}")
	public void add_the_book_with_the_payload_and(String name, String isbn, String aisle, String author) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		 this.expectedName = name;
		    this.expectedIsbn = isbn;
		    this.expectedAisle = aisle;
		    this.expectedAuthor = author;
	   request=RestAssured.given().spec(requestSpecification2()).body(data.addbook(name, isbn, aisle, author));
	}
	@When("user calls {string} api with {string}")
	public void user_calls_api_with(String resourse, String method) {
	    // Write code here that turns the phrase above into concrete actions
		 resourceApi=APIResources.valueOf(resourse); 
		response=request.when().post(resourceApi.getResource());
		JsonPath jp= new JsonPath(response.asPrettyString());
		 BookId=jp.getString("ID");
		 System.out.println(BookId);
	}
	@Then("Api got success with status code {int}")
	public void api_got_success_with_status_code(int int1) {
	    assertEquals( response.getStatusCode(),int1);
	}

	@Given("get book api payload")
	public void get_book_api_payload() throws IOException {
	   req=RestAssured.given().spec(requestSpecification2()).queryParam("ID",BookId);
	}
	@When("user calls {string} with get http request")
	public void user_calls_with_get_http_request(String resourse) {
	 resourceApi=APIResources.valueOf(resourse); 
	    response=req.when().get(resourceApi.getResource());
	}
	@Then("verify {string} {string} {string} and {string}")
	public void verify_and(String name, String isbn, String aisle, String author) {
		  JsonPath js = new JsonPath(response.asString());
		    List<Map<String, String>> books = js.getList("$"); // Get the list of books

		    // Since the response is an array, check the first element
		    Map<String, String> firstBook = books.get(0);

		    // Extract values from the first book
		    String actName = firstBook.get("book_name");
		    String actIsbn = firstBook.get("isbn");
		    String actAisle = firstBook.get("aisle");
		    String actAuthor = firstBook.get("author");

		    // Assert each field
		    assertEquals(name, actName);
		    assertEquals(isbn, actIsbn);
		    assertEquals(aisle, actAisle);
		    assertEquals(author, actAuthor);
		}
	}


	
	
	

