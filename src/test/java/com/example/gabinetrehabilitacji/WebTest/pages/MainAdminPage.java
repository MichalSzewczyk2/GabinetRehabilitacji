package com.example.gabinetrehabilitacji.WebTest.pages;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainAdminPage {

  private WebDriver driver;

  private WebElement title;
  private WebElement manageUsers;
  private WebElement logout;


  public MainAdminPage(WebDriver driver) {
    this.driver = driver;
    title = driver.findElement(By.className("centerTitle"));
    manageUsers = driver.findElement(By.id("manageUsers"));
    logout = driver.findElement(By.id("logout"));
  }

  public MainAdminPage assertTitle(){
    Assertions.assertThat(title.getText()).isEqualTo("Welcome in admin panel");
    return this;
  }

  public MainAdminPage clickOnManageUsers(){
    manageUsers.click();
    return this;
  }

  public MainAdminPage clickOnLogout(){
    logout.click();
    return this;
  }
}
