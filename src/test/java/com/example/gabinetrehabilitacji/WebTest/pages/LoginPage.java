package com.example.gabinetrehabilitacji.WebTest.pages;

import com.clinic.dbTables.User;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

  private WebDriver driver;

  private WebElement login;
  private WebElement password;
  private WebElement loginButton;

  private WebElement title;

  @SneakyThrows
  public LoginPage(WebDriver driver) {
    this.driver = driver;
    login = driver.findElement(By.id("login"));
    password = driver.findElement(By.id("password"));
    loginButton = driver.findElement(By.className("buttons"));
    title = driver.findElement(By.className("centerTitle"));
  }

  public LoginPage assertTitle(){
    Assertions.assertThat(title.getText()).isEqualTo("LOG IN");
    return this;
  }

  public LoginPage fillForm(User user){
    login.sendKeys(user.getUsername());
    password.sendKeys(user.getPassword());
    return this;
  }

  public LoginPage fillForm(String login, String password) {
    this.login.sendKeys(login);
    this.password.sendKeys(password);
    return this;
  }

  public LoginPage clickLoginButton() {
    loginButton.click();
    return this;
  }
}
