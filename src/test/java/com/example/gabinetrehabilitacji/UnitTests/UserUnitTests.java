package com.example.gabinetrehabilitacji.UnitTests;

import com.clinic.controller.UserController;
import com.clinic.dbTables.User;
import com.clinic.dbTables.UserType;
import com.clinic.pageController.SignInController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

@SpringBootConfiguration
@ComponentScan("com")
@SpringBootTest
public class UserUnitTests {

  @Autowired
  private UserController userController;

  @Test
  public void addUser(){
    User user = new User();
    user.setName("test");
    user.setSurname("test");
    user.setEmail("test@mail.com");
    user.setAddress("test");
    user.setPhoneNumber(999888777);
    user.setBirthDate(LocalDate.now());
    user.setUserType(UserType.CLIENT);
    user.setUsername("test");
    user.setPassword("test");

    userController.addUser(user);
    User addedUser = userController.getUserByUsername("test");
    Assertions.assertThat(addedUser).isNotNull();
  }

  @Test
  public void deleteUser(){
    User user = userController.getUserByUsername("test");
    userController.deleteUserWithId(user.getId());
    User deletedUser = userController.getUserByUsername("test");
    Assertions.assertThat(deletedUser).isNull();
  }

}
