package com.task.service;

import com.task.ModelUtils;
import com.task.entity.User;
import com.task.exception.exceptions.IdNotFoundException;
import com.task.repository.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @Test
    public void getAllUsersTest() {
        User user = ModelUtils.getUser();
        List<User> allUsers = List.of(user);
        when(userRepo.findAll()).thenReturn(allUsers);
        assertEquals(allUsers, userService.getAllUsers());
        verify(userRepo, times(1)).findAll();
    }

    @Test
    public void getUserByIdTest() {
        User user = ModelUtils.getUser();
        when(userRepo.findById(1)).thenReturn(Optional.ofNullable(user));
        assertEquals(user, userService.getUserById(1));
        verify(userRepo, times(1)).findById(1);
    }

    @Test
    public void getUserByIdWithInvalidIdTest() {
        assertThrows(IdNotFoundException.class,
                () -> userService.getUserById(100));
        verify(userRepo, times(1)).findById(100);
    }
}
