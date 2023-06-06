package com.example.gabinetrehabilitacji.WebTest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ManageUsersPage {
  private WebDriver driver;

  private WebElement delete;
  private WebElement logout;

  public ManageUsersPage(WebDriver driver, boolean full) {
    this.driver = driver;
    if (full) {
      delete = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[9]/td[4]/a[2]"));
    }
    logout = driver.findElement(By.id("logout"));
  }

  public ManageUsersPage clickOnDelete(){
    delete.click();
    driver.switchTo().alert().accept();
    return this;
  }

  public ManageUsersPage clickOnLogout(){
    logout.click();
    return this;
  }

}
