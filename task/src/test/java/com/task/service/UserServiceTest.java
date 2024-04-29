package com.task.service;

import com.task.ModelUtils;
import com.task.dto.UserDto;
import com.task.entity.User;
import com.task.exception.exceptions.BadRequestException;
import com.task.exception.exceptions.IdNotFoundException;
import com.task.repository.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private ModelMapper modelMapper;

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

    @Test
    public void saveUserTest() {
        Integer yearToPass = 18;
        UserDto userDto = ModelUtils.getUserDto();
        User user = ModelUtils.getUser();
        userService = new UserService(userRepo, modelMapper, yearToPass);

        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(userRepo.save(user)).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        assertEquals(userDto, userService.saveUser(userDto));
        verify(userRepo, times(1)).save(user);
        verify(modelMapper, times(1)).map(userDto, User.class);
        verify(modelMapper, times(1)).map(user, UserDto.class);
    }

    @Test
    public void saveUserWithInvalidYearTest() {
        Integer yearToPass = 18;
        UserDto userDto = ModelUtils.getUserDto();
        userDto.setBirthDate(LocalDate.of(2020, 11, 17));
        userService = new UserService(userRepo, modelMapper, yearToPass);

        assertThrows(BadRequestException.class,
                () -> userService.saveUser(userDto));
    }

    @Test
    public void updateUserTest() {
        Integer yearToPass = 18;
        UserDto userDto = ModelUtils.getUserDto();
        User user = ModelUtils.getUser();
        userService = new UserService(userRepo, modelMapper, yearToPass);

        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(userRepo.save(user)).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        assertEquals(userDto, userService.updateUser(userDto, user.getId()));

        verify(userRepo, times(1)).findById(1);
        verify(userRepo, times(1)).save(user);
        verify(modelMapper, times(1)).map(userDto, user);
        verify(modelMapper, times(1)).map(user, UserDto.class);
    }

    @Test
    public void updateUserByFieldsTest() {
        Integer yearToPass = 18;
        UserDto userDto = ModelUtils.getUserDto();
        User user = ModelUtils.getUser();
        userService = new UserService(userRepo, modelMapper, yearToPass);

        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(userRepo.save(user)).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        assertEquals(userDto, userService.updateUserByFields(userDto, 1));

        verify(userRepo, times(1)).findById(1);
        verify(userRepo, times(1)).save(user);
        verify(modelMapper, times(1)).map(user, UserDto.class);
    }

    @Test
    public void updateUserByFieldsWithNullValuesTest() {
        Integer yearToPass = 18;
        UserDto userDto = new UserDto();
        User user = ModelUtils.getUser();
        userService = new UserService(userRepo, modelMapper, yearToPass);

        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(userRepo.save(user)).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        assertEquals(userDto, userService.updateUserByFields(userDto, 1));

        verify(userRepo, times(1)).findById(1);
        verify(userRepo, times(1)).save(user);
        verify(modelMapper, times(1)).map(user, UserDto.class);
    }


    @Test
    public void deleteUserTest() {
        User user = ModelUtils.getUser();
        when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));
        assertEquals("User was deleted by this id: " + user.getId(), userService.deleteUser(user.getId()));
        verify(userRepo, times(1)).findById(user.getId());
        verify(userRepo, times(1)).delete(user);
    }
}
