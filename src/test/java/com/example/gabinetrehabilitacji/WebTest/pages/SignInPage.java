package com.example.gabinetrehabilitacji.WebTest.pages;


import com.clinic.dbTables.User;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.awaitility.core.ThrowingRunnable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.format.DateTimeFormatter;

public class SignInPage {

  private WebDriver driver;

  private WebElement title;
  private WebElement firstName;
  private WebElement lastName;
  private WebElement email;
  private WebElement address;
  private WebElement username;
  private WebElement phoneNumber;
  private WebElement birthDate;
  private WebElement password;
  private WebElement matchingPassword;
  private WebElement submitButton;


  @SneakyThrows
  public SignInPage(WebDriver driver) {

    this.driver = driver;
    firstName = driver.findElement(By.id("firstName"));
    lastName = driver.findElement(By.id("lastName"));
    email = driver.findElement(By.id("email"));
    address = driver.findElement(By.id("address"));
    username = driver.findElement(By.id("username"));
    phoneNumber = driver.findElement(By.id("phoneNumber"));
    birthDate = driver.findElement(By.id("birthDate"));
    password = driver.findElement(By.id("password"));
    matchingPassword = driver.findElement(By.id("confirm"));
    submitButton = driver.findElement(By.className("buttons"));
  }

  public SignInPage fillForm(User user) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    firstName.sendKeys(user.getName());
    lastName.sendKeys(user.getSurname());
    email.sendKeys(user.getEmail());
    address.sendKeys(user.getAddress());
    username.sendKeys(user.getUsername());
    phoneNumber.sendKeys(String.valueOf(user.getPhoneNumber()));
    birthDate.sendKeys(user.getBirthDate().format(dateTimeFormatter));
    password.sendKeys(user.getPassword());
    matchingPassword.sendKeys(user.getPassword());
    return this;
  }

  public SignInPage submitForm() {
    submitButton.click();
    return this;
  }

}
