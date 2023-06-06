package com.example.gabinetrehabilitacji.WebTest.pages;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {

  private WebDriver driver;

  private WebElement title;
  private WebElement login;
  private WebElement logout;
  private WebElement signIn;

  public MainPage(WebDriver driver, String wariant) {
    this.driver = driver;
    title = driver.findElement(By.id("title"));

    if(wariant.equals("login")){
      login = driver.findElement(By.id("login"));
      signIn = driver.findElement(By.id("signIn"));
    }
    else if(wariant.equals("logout")){
      logout = driver.findElement(By.id("logout"));
    }
  }

  public MainPage assertTitle(){
    Assertions.assertThat(title.getText()).isEqualTo("Welcome");
    return this;
  }

  public MainPage clickLogin(){
    login.click();
    return this;
  }

  public MainPage clickLogout(){
    logout.click();
    return this;
  }

  public MainPage clickSignIn(){
    signIn.click();
    return this;
  }
}
