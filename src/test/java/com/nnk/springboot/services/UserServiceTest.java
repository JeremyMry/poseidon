package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DirtiesContext(classMode=DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServiceImpl userService;

    @Test
    public void getSpecificUserTest() {
        User user = new User("john", "123", "john doe", "ADMIN");
        userRepository.save(user);

        Assertions.assertNotNull(userService.getSpecificUserById(1));
        Assertions.assertEquals(userService.getSpecificUserById(1).getUsername(), user.getUsername());
        Assertions.assertEquals(userService.getSpecificUserById(1).getRole(), user.getRole());
        Assertions.assertEquals(userService.getSpecificUserById(1).getFullname(), user.getFullname());
    }

    @Test
    public void getSpecificUserThatDoesntExistTest() {
        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> userService.getSpecificUserById(1));

        String expectedMessage = "Invalid User Id: 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void getUserByUsernameTest() {
        User user = new User("john", "123", "john doe", "ADMIN");
        userRepository.save(user);

        Assertions.assertNotNull(userService.findByUsername("john"));
        Assertions.assertEquals(userService.findByUsername("john").getUsername(), user.getUsername());
        Assertions.assertEquals(userService.findByUsername("john").getRole(), user.getRole());
        Assertions.assertEquals(userService.findByUsername("john").getFullname(), user.getFullname());
    }

    @Test
    public void getUserByUsernameWhenThereIsNoUserTest() {
        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> userService.findByUsername("john"));

        String expectedMessage = "Invalid User Username: john";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void getAllUserTest() {
        User user = new User("john", "123", "john doe", "ADMIN");
        User user1 = new User("johnny", "123", "johnny doe", "ADMIN");

        userRepository.save(user);
        userRepository.save(user1);

        Assertions.assertEquals(userService.getAllUser().size(), 2);
    }

    @Test
    public void getAllUsersWhenThereIsNone() {
        Assertions.assertEquals(userService.getAllUser().size(), 0);
        Assertions.assertNotNull(userService.getAllUser().size());
    }

    @Test
    public void createUserTest() {
        User user = new User("john", "123", "john doe", "ADMIN");

        userService.createUser(user);

        Assertions.assertEquals(userRepository.findById(1).get().getFullname(), user.getFullname());
        Assertions.assertEquals(userRepository.findById(1).get().getUsername(), user.getUsername());
        Assertions.assertEquals(userRepository.findById(1).get().getRole(), user.getRole());
    }

    @Test
    public void updateUserTest() {
        User user = new User("john", "123", "john doe", "ADMIN");
        userRepository.save(user);

        User newUser = new User("johnny", "123", "johnny doe", "USER");
        userService.updateUser(1, newUser);

        Assertions.assertEquals(userRepository.findById(1).get().getUsername(), "johnny");
        Assertions.assertEquals(userRepository.findById(1).get().getFullname(), "johnny doe");
        Assertions.assertEquals(userRepository.findById(1).get().getRole(), "USER");
        Assertions.assertEquals(userRepository.findAll().size(), 1);
    }

    @Test
    public void deleteUserTest() {
        User user = new User("john", "123", "john doe", "ADMIN");
        userRepository.save(user);

        userService.deleteUser(1);

        Assertions.assertFalse(userRepository.findById(1).isPresent());
    }
}
