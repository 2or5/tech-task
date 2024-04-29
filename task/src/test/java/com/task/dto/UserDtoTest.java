package com.task.dto;

import com.task.ModelUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.Test;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

public class UserDtoTest {

    @Test
    public void userDtoWithValidValuesTest() {
        UserDto dto = ModelUtils.getUserDto();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<UserDto>> constraintViolations =
                validator.validate(dto);

        assertThat(constraintViolations).isEmpty();
    }

    @Test
    public void userDtoWithInvalidValuesTest() {
        UserDto dto = new UserDto();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<UserDto>> constraintViolations =
                validator.validate(dto);

        assertThat(constraintViolations).hasSize(4);
    }
}
