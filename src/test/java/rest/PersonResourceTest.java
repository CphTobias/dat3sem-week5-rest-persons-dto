package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.person.PersonDTO;
import entities.person.Person;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

public class PersonResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Person person1, person2, person3;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        person1 = new Person("First1", "First2", "1111");
        person2 = new Person("Second1", "Second2", "2222");
        person3 = new Person("Third1", "Third2", "3333");
        try {
            em.getTransaction().begin();
            em.persist(person1);
            em.persist(person2);
            em.persist(person3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        given().when().get("/person").then().statusCode(200);
    }

    @Test
    public void testGetById() throws Exception {
        given()
            .contentType(ContentType.JSON)
            .pathParam("id", person1.getPersonId())
            .get("/person/{id}").then()
            .assertThat()
            .statusCode(HttpStatus.OK_200.getStatusCode())
            .body("id", equalTo(person1.getPersonId()));

    }

    @Test
    public void testGetAll() throws Exception {
        List<PersonDTO> foundPeople;

        foundPeople = given()
            .contentType(ContentType.JSON)
            .get("/person/").then()
            .assertThat()
            .statusCode(HttpStatus.OK_200.getStatusCode())
            .extract().body().jsonPath().getList("all", PersonDTO.class);

        assertThat(foundPeople, hasItems(
            new PersonDTO(person1),
            new PersonDTO(person2),
            new PersonDTO(person3)
        ));
    }

    @Test
    public void testAddPerson() {
        String requestBody = gson.toJson(new PersonDTO("Add", "Me", "1234"));

        given()
            .header("Content-type", ContentType.JSON)
            .and()
            .body(requestBody)
            .when()
            .post("/person/")
            .then()
            .assertThat()
            .statusCode(201)
            .body("id", notNullValue())
            .body("firstName", equalTo("Add"));
    }

    @Test
    public void testEditPerson() {
        //Editing person 1
        String requestBody = gson.toJson(new PersonDTO("Edit", "Me", "1234"));

        given()
            .header("Content-type", ContentType.JSON)
            .and()
            .body(requestBody)
            .when()
            .pathParam("id", person1.getPersonId())
            .put("/person/{id}")
            .then()
            .assertThat()
            .statusCode(200)
            .body("id", equalTo(person1.getPersonId()))
            .body("firstName", equalTo("Edit"));
    }

    @Test
    public void testDeletePerson() {
        given()
            .contentType(ContentType.JSON)
            .pathParam("id", person2.getPersonId())
            .delete("/person/{id}")
            .then()
            .statusCode(200)
            .body(notNullValue());
    }

}
