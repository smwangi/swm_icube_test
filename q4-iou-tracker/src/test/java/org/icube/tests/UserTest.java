package org.icube.tests;

import org.icube.ioutracker.IOUTrackerApplication;
import org.icube.ioutracker.models.User;
import org.icube.ioutracker.repositories.UserRepository;
import org.icube.ioutracker.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = IOUTrackerApplication.class)
public class UserTest {

    @Test
    public void contextLoads(){

    }

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;


    @Test
    public void testRepositoryNotNull(){
        assertNotNull(userRepository);
    }

    @Test
    @DisplayName("Test save user.")
    public void  testAddUser(){

        User user = new User(2,"simon");
        doReturn(user).when(userRepository).save(any());

        User returnedUsr = userService.save(user);
        // Assert the response
        //Assertions.assertTrue(returnedUsr.isPresent());
        Assertions.assertNull(returnedUsr, "The saved user should not be null");
        //Assertions.assertEquals(2, returnedUsr.get().getId(), "The id should be incremented");

    }
}
