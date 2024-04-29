package com.task.mapping;

import com.task.ModelUtils;
import com.task.dto.UserDto;
import com.task.entity.User;
import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;

public class UserMapperTest {

    private final UserMapper userMapper = new UserMapper();

    @Test
    public void updateEntityByDtoTest() {
        User user = ModelUtils.getUser();
        UserDto userDto = ModelUtils.getUserDto();

        userMapper.updateEntityByDto(user, userDto);

        assertEquals("test@mail.com", user.getEmail());
        assertEquals("test first name", user.getFirstName());
        assertEquals("test last name", user.getLastName());
        assertEquals(LocalDate.of(2000, 10, 10), user.getBirthDate());
        assertEquals("test address", user.getAddress());
        assertEquals("+50003445", user.getPhoneNumber());
    }

    @Test
    public void updateEntityByDtoWithNullValuesTest() {
        User user = ModelUtils.getUser();
        UserDto userDto = new UserDto();

        userMapper.updateEntityByDto(user, userDto);

        assertEquals("test@mail.com", user.getEmail());
        assertEquals("test first name", user.getFirstName());
        assertEquals("test last name", user.getLastName());
        assertEquals(LocalDate.of(2000, 10, 10), user.getBirthDate());
        assertEquals("test address", user.getAddress());
        assertEquals("+50003445", user.getPhoneNumber());
    }
}
