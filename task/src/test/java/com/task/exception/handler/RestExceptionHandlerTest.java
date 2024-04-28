package com.task.exception.handler;

import com.task.ModelUtils;
import com.task.exception.exceptions.BadRequestException;
import com.task.exception.exceptions.IdNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class RestExceptionHandlerTest {

    @InjectMocks
    RestExceptionHandler restExceptionHandler;

    @Test
    public void handleIdNotFoundException() {
        ExceptionResponse exceptionResponse = ModelUtils.getExceptionResponse();
        exceptionResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        IdNotFoundException idNotFoundException = new IdNotFoundException("test");

        assertEquals(restExceptionHandler.handleIdNotFoundException(idNotFoundException),
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse));
    }

    @Test
    public void handleBadRequestExceptionTest() {
        ExceptionResponse exceptionResponse = ModelUtils.getExceptionResponse();
        BadRequestException badRequestException = new BadRequestException("test");

        assertEquals(restExceptionHandler.handleBadRequestException(badRequestException),
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse));
    }

    @Test
    public void handleMethodArgumentExceptionTest() {
        ExceptionResponse exceptionResponse = ModelUtils.getExceptionResponse();

        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);

        when(exception.getFieldError()).thenReturn(new FieldError("fieldName",
                "some error message", "test"));

        assertEquals(restExceptionHandler.handleMethodArgumentException(exception),
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse));
    }
}
