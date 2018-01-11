package com.myshop.products;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;
import com.myshop.products.model.Products;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ProductApp.class, webEnvironment=WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes=ProductApp.class) 
public class ProductServiceIT {

	@LocalServerPort
	private int port;

	@BeforeClass
	public static void initClass() {
		System.setProperty("ENCRYPT_KEY", "345345fsdgsf5345");
	}
	
	@Before
	public void init() {
		RestAssured.baseURI = "http://localhost/product";
        RestAssured.port = port;
	}
	
	@Test
    public void testCreateProductWrongURL() throws Exception {
		Products prod = new Products();
		prod.setId("1234");
		prod.setCategory("Dairy");
		prod.setImageUrl("www.abc.com");
		prod.setPrice(2.20);
		prod.setTitle("testProduct");
		
		List<Products> prods = new ArrayList<>();
		prods.add(prod);
		
		given().body(prods).contentType(ContentType.JSON).post("").then().statusCode(404);
    }
	
	@Test
    public void testCreateProduct() throws Exception {
		Products prod = new Products();
		prod.setId("1234");
		prod.setCategory("Dairy");
		prod.setImageUrl("www.abc.com");
		prod.setPrice(2.20);
		prod.setTitle("testProduct");
		
		List<Products> prods = new ArrayList<>();
		prods.add(prod);
		
		given().body(prods).contentType(ContentType.JSON).post("/").then().statusCode(200).assertThat().body(containsString("success"));
    }
	
	@Test
    public void testModifyProduct() throws InterruptedException {
		Products prod = new Products();
		prod.setId("1234");
		prod.setCategory("Dairy");
		prod.setImageUrl("www.abc.com");
		prod.setPrice(2.05);
		prod.setTitle("testProduct");
		
		List<Products> prods = new ArrayList<>();
		prods.add(prod);
		
        given().body(prods).contentType(ContentType.JSON).put("/").then().statusCode(200).assertThat().body(containsString("success"));
		
    }

	@Test
    public void testFetchProduct() throws InterruptedException {
		given().get("/-KrqgOLs07ZkbapP4EGi").then().statusCode(200).assertThat().body(containsString("-KrqgOLs07ZkbapP4EGi"));
    }
	
	@Test
    public void testDeleteProduct() throws InterruptedException {
        given().delete("/1234").then().statusCode(200).assertThat().body(containsString("success"));
    }

}
