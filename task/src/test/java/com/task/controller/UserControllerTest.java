package com.task.controller;

import com.task.ModelUtils;
import com.task.dto.UserDto;
import com.task.entity.User;
import com.task.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void getAllUsersTest() throws Exception {

        User user = new User(1,
                "test@mail.com",
                "test first name",
                "test last name",
                LocalDate.of(2000, 10, 10),
                "test address",
                50003445);

        List<User> allUsers = List.of(user);

        when(userService.getAllUsers()).thenReturn(allUsers);

        mvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService).getAllUsers();
    }

    @Test
    public void getUserByIdTest() throws Exception {

        User user = new User(1,
                "test@mail.com",
                "test first name",
                "test last name",
                LocalDate.of(2000, 10, 10),
                "test address",
                50003445);

        when(userService.getUserById(user.getId())).thenReturn(user);

        mvc.perform(get("/users/user?id=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService).getUserById(user.getId());
    }

    @Test
    public void saveUserTest() throws Exception {

        UserDto userDto = ModelUtils.getUserDto();

        String json = """
                {
                 	"email": "test@mail.com",
                 	"firstName": "test first name",
                 	"lastName": "test last name",
                 	"birthDate": "2000-10-10",
                 	"address": "test address",
                 	"phoneNumber": 50003445
                }
                """;

        when(userService.saveUser(userDto)).thenReturn(userDto);

        mvc.perform(post("/users/saveUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(userService).saveUser(any());
    }

    @Test
    public void updateUserTest() throws Exception {

        UserDto userDto = ModelUtils.getUserDto();

        String json = """
                {
                	"email": "test@mail.com",
                	"firstName": "test first name",
                	"lastName": "test last name",
                	"birthDate": "2000-10-10",
                	"address": "test address",
                	"phoneNumber": 50003445
                }
                """;

        when(userService.updateUser(userDto, 1)).thenReturn(userDto);

        mvc.perform(put("/users/updateUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", "1")
                        .content(json))
                .andExpect(status().isOk());

        verify(userService).updateUser(any(), any());
    }

    @Test
    public void updateUserByFieldsTest() throws Exception {

        String json = """
                {
                	"email": "test@mail.com",
                	"firstName": "test first name",
                	"address": "test address"
                }
                """;

        mvc.perform(patch("/users/editUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", "1")
                        .content(json))
                .andExpect(status().isOk());

        verify(userService).updateUserByFields(any(), any());
    }

    @Test
    public void deleteUserTest() throws Exception {

        when(userService.deleteUser(1)).thenReturn("test message");

        mvc.perform(delete("/users/deleteUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", "1"))
                .andExpect(status().isOk());

        verify(userService).deleteUser(any());
    }
}
