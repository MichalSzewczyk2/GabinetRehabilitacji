package com.example.gabinetrehabilitacji.RestTests;


import com.clinic.dbTables.User;
import com.clinic.dbTables.UserType;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.time.LocalDate;
import java.util.Random;;

public class UserRestTest {

  private User user;
  private final Faker faker = new Faker();

  @BeforeAll
  public static void setup() {
    baseURI = "http://localhost:8080";
  }


  public void prepareUser() {

    this.user = new User();
    user.setName("test");
    user.setSurname("test");
    user.setEmail("test@mail.com");
    user.setAddress("test");
    user.setPhoneNumber(999888777);
    user.setBirthDate(LocalDate.now());
    user.setUserType(UserType.CLIENT);
    user.setUsername("test");
    user.setPassword("test");
  }

  @Test
  public void signInLoginDeleteUser() {

    prepareUser();

    given()
      .contentType("multipart/form-data")
      .multiPart("firstName", user.getName())
      .multiPart("lastName", user.getSurname())
      .multiPart("email", user.getEmail())
      .multiPart("username", user.getUsername())
      .multiPart("address", user.getAddress())
      .multiPart("phoneNumber", user.getPhoneNumber())
      .multiPart("birthDate", user.getBirthDate().toString())
      .multiPart("password", user.getPassword())
      .multiPart("matchingPassword", user.getPassword())
      .when()
      .post("/signIn")
      .then()
      .statusCode(200);

    given()
      .contentType("multipart/form-data")
      .multiPart("username", user.getUsername())
      .multiPart("password", user.getPassword())
      .multiPart("submit", "Login")
      .when()
      .post("/logIn")
      .then()
      .statusCode(302);


    given()
      .pathParam("username", user.getUsername())
      .when()
      .post("/system/delete/{username}")
      .then()
      .statusCode(302);
  }

  public static int getRandomPhoneNumber() {
    Random rnd = new Random();
    return rnd.nextInt(999999999);

  }

}
