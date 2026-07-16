package com.tannx.swt;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DateCheckerApiControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void testDayInMonth_Valid() {
        given()
            .queryParam("month", "2")
            .queryParam("year", "2024")
        .when()
            .get("/api/dayInMonth")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("days", equalTo(29));
    }

    @Test
    public void testDayInMonth_InvalidMonth() {
        given()
            .queryParam("month", "13")
            .queryParam("year", "2024")
        .when()
            .get("/api/dayInMonth")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("error", equalTo("Input data for Month is out of range!"));
    }

    @Test
    public void testCheckDate_ValidDate() {
        given()
            .queryParam("day", "29")
            .queryParam("month", "2")
            .queryParam("year", "2024")
        .when()
            .get("/api/checkDate")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("message", equalTo("29/2/2024 is correct date time!"));
    }

    @Test
    public void testCheckDate_InvalidLogicalDate() {
        given()
            .queryParam("day", "29")
            .queryParam("month", "2")
            .queryParam("year", "2023")
        .when()
            .get("/api/checkDate")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("message", equalTo("29/2/2023 is invalid date time!"));
    }
}
