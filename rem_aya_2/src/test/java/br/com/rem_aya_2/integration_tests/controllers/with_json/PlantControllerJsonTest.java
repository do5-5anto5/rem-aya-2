package br.com.rem_aya_2.integration_tests.controllers.with_json;


import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rem_aya_2.configs.TestConfigs;
import br.com.rem_aya_2.integration_tests.testcontainers.AbstractIntegrationTest;
import br.com.rem_aya_2.integration_tests.vo.AccountCredentialsVO;
import br.com.rem_aya_2.integration_tests.vo.PlantVO;
import br.com.rem_aya_2.integration_tests.vo.TokenVO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class PlantControllerJsonTest extends AbstractIntegrationTest {
	
	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;
	private static PlantVO plant;
	
	@BeforeAll
	private static void setup() {
		
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		plant = new PlantVO();
	}

	@Test
	@Order(0)
	public void authorization() throws JsonMappingException, JsonProcessingException{
		
		var user = new AccountCredentialsVO("jonathan", "admin123");
		
		var accessToken = 
			given()
			.basePath("/auth/signin")
				.port(TestConfigs.SERVER_PORT)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
			.body(user)
				.when()
			.post()
				.then()
					.statusCode(200)
						.extract()
						.body()
							.as(TokenVO.class)
						.getAccessToken();
		
		specification =
			new RequestSpecBuilder()
			.addHeader(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + accessToken)
			.setBasePath("api/plant/v1")
			.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
			.build();
	}

	@Test
	@Order(1)
	public void testCreate() throws JsonMappingException, JsonProcessingException{
		mockPlant();
		
		var content =
			given().spec(specification)
			.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_DO55ANTO5)
				.body(plant)
					.when()
				.post()
					.then()
						.statusCode(200)
							.extract()
							.body()
								.asString();
		
		PlantVO persistedPlant = objectMapper.readValue(content, PlantVO.class);
		plant = persistedPlant;
		
		assertNotNull(plant.getId());
		assertNotNull(plant.getName());
		assertNotNull(plant.getPlantedDate());
		assertNotNull(plant.getInHouse());
		assertNotNull(plant.getAddress());
		
		assertTrue(plant.getId() > 0);
		
		assertEquals("Arnica", plant.getName());
		assertEquals(false, plant.getInHouse());
		assertEquals("Alameda dos Anjos, 1993", plant.getAddress());
	}
	
	@Test
	@Order(2)
	public void testUpdate() throws JsonMappingException, JsonProcessingException{
		plant.setName("Bambu");
		
		var content =
			given().spec(specification)
			.contentType(TestConfigs.CONTENT_TYPE_JSON)
			.body(plant)
				.when()
			.put()
				.then()
					.statusCode(200)
						.extract()
						.body()
							.asString();
		
		PlantVO persistedPlant = objectMapper.readValue(content, PlantVO.class);
		plant = persistedPlant;
		
		assertNotNull(plant.getId());
		assertNotNull(plant.getName());
		assertNotNull(plant.getPlantedDate());
		assertNotNull(plant.getInHouse());
		assertNotNull(plant.getAddress());
		
		assertTrue(plant.getId() > 0);
		
		assertEquals("Bambu", plant.getName());
		assertEquals(false, plant.getInHouse());
		assertEquals("Alameda dos Anjos, 1993", plant.getAddress());
	}
	
	@Test
	@Order(3)
	public void testFindById() throws JsonMappingException, JsonProcessingException{
		mockPlant();
		
		var content =
			given().spec(specification)
			.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.pathParam("id", plant.getId())
				.when()
			.get("{id}")
				.then()
					.statusCode(200)
						.extract()
						.body()
							.asString();
		
		PlantVO persistedPlant = objectMapper.readValue(content, PlantVO.class);
		plant = persistedPlant;
		
		assertNotNull(persistedPlant);
		
		assertNotNull(plant.getId());
		assertNotNull(plant.getName());
		assertNotNull(plant.getPlantedDate());
		assertNotNull(plant.getInHouse());
		assertNotNull(plant.getAddress());
		
		assertTrue(plant.getId() > 0);
		
		assertEquals("Bambu", plant.getName());
		assertEquals(false, plant.getInHouse());
		assertEquals("Alameda dos Anjos, 1993", plant.getAddress());
	}
	
	@Test
	@Order(4)
	public void tesDelete() throws JsonMappingException, JsonProcessingException{
		
		given().spec(specification)
		.contentType(TestConfigs.CONTENT_TYPE_JSON)
			.pathParam("id", plant.getId())
				.when()
			.delete("{id}")
				.then()
					.statusCode(204);
	}
	
	@Test
	@Order(5)
	public void testFindAll() throws JsonMappingException, JsonProcessingException{
		
		var content =
			given().spec(specification)
			.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.when()
			.get()
				.then()
					.statusCode(200)
						.extract()
						.body()
							.asString();
		
		List<PlantVO> plants = objectMapper.readValue(content, new TypeReference<List<PlantVO>>() {});
		
		PlantVO foundPlant1 = plants.get(0);
		
		assertNotNull(foundPlant1.getId());
		assertNotNull(foundPlant1.getName());
		assertNotNull(foundPlant1.getPlantedDate());
		assertNotNull(foundPlant1.getInHouse());
		assertNotNull(foundPlant1.getAddress());
		
		assertTrue(foundPlant1.getId() > 0);
		
		assertEquals("Camomila", foundPlant1.getName());
		assertEquals(true, foundPlant1.getInHouse());
		assertEquals("Rua Neves 543, Sao Paulo, Brazil", foundPlant1.getAddress());
		
		PlantVO foundPlant2 = plants.get(1);
		
		assertNotNull(foundPlant2.getId());
		assertNotNull(foundPlant2.getName());
		assertNotNull(foundPlant2.getPlantedDate());
		assertNotNull(foundPlant2.getInHouse());
		assertNotNull(foundPlant2.getAddress());
		
		assertTrue(foundPlant2.getId() > 0);
		
		assertEquals("Camomila", foundPlant2.getName());
		assertEquals(true, foundPlant2.getInHouse());
		assertEquals("Rua Suadi 100, Minas Gerais, Brazil", foundPlant2.getAddress());
		
	}
	
	@Test
	@Order(6)
	public void testFindAllWithoutToken() throws JsonMappingException, JsonProcessingException{
		
		RequestSpecification specificationWithoutToken = new RequestSpecBuilder()
			.setBasePath("/api/person/v1")
			.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
			.build();
		
		given().spec(specificationWithoutToken)
		.contentType(TestConfigs.CONTENT_TYPE_JSON)
			.when()
		.get()
			.then()
				.statusCode(403);
	}
	
	void mockPlant() {
		plant.setName("Arnica");
		plant.setPlantedDate(new Date(2024-01-13));
		plant.setInHouse(false);
		plant.setAddress("Alameda dos Anjos, 1993");
	}
}
