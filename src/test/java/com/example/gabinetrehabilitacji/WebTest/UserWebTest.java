package com.example.gabinetrehabilitacji.WebTest;

import com.clinic.dbTables.User;
import com.clinic.dbTables.UserType;
import com.example.gabinetrehabilitacji.WebTest.pages.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;

public class UserWebTest {

  private WebDriver driver;
  private SignInPage signInPage;
  private LoginPage loginPage;
  private SigninSuccessPage signinSuccessPage;
  private MainPage mainPage;
  private MainAdminPage mainAdminPage;
  private ManageUsersPage manageUsersPage;

  private User user;


  public void prepareEnvironment(){
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

    driver = new ChromeDriver();
    driver.get("http://localhost:8080/signIn");
    signInPage = new SignInPage(driver);
  }

  @Test
  @SneakyThrows
  public void userTest(){

    prepareEnvironment();
    Thread.sleep(1000);
    signInPage
      .fillForm(user)
      .submitForm();
    Thread.sleep(1000);
    signinSuccessPage = new SigninSuccessPage(driver);
    signinSuccessPage
      .clickMainPageButton();
    Thread.sleep(1000);
    mainPage = new MainPage(driver, "login");
    mainPage
      .assertTitle()
      .clickLogin();
    Thread.sleep(1000);
    loginPage = new LoginPage(driver);
    loginPage
      .fillForm(user)
      .clickLoginButton();
    Thread.sleep(1000);
    mainPage = new MainPage(driver, "logout");
    mainPage
      .assertTitle()
      .clickLogout();
    Thread.sleep(1000);
    loginPage = new LoginPage(driver);
    loginPage
      .fillForm("admin", "admin")
      .clickLoginButton();
    Thread.sleep(1000);
    mainAdminPage = new MainAdminPage(driver);
    mainAdminPage
      .assertTitle()
      .clickOnManageUsers();
    Thread.sleep(1000);
    manageUsersPage = new ManageUsersPage(driver, true);
    manageUsersPage
      .clickOnDelete();
    manageUsersPage = new ManageUsersPage(driver, false);
    manageUsersPage
      .clickOnLogout();
    Thread.sleep(1000);
    loginPage = new LoginPage(driver);
    loginPage
      .fillForm(user)
      .clickLoginButton();
    Thread.sleep(1000);
    loginPage = new LoginPage(driver);
    loginPage.assertTitle();

    driver.quit();
  }

}
