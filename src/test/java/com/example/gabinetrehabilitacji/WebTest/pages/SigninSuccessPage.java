package com.example.gabinetrehabilitacji.WebTest.pages;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SigninSuccessPage {
  private WebDriver driver;

  private WebElement mainPageButton;

  @SneakyThrows
  public SigninSuccessPage(WebDriver driver){
    this.driver = driver;
    mainPageButton = driver.findElement(By.className("buttons"));
  }

  public SigninSuccessPage clickMainPageButton(){
    mainPageButton.click();
    return this;
  }
}
