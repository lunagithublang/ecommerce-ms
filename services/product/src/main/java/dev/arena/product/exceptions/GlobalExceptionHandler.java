package dev.arena.product.exceptions;

import dev.arena.common.dto.ErrorResponseDTO;
import dev.arena.common.exceptions.ErrorResponseBuilder;
import dev.arena.common.exceptions.NotFoundEntityException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Map<Class<? extends Exception>, HttpStatus> EXCEPTION_STATUS_MAP = Map.of(
            MethodArgumentNotValidException.class, HttpStatus.UNPROCESSABLE_ENTITY,
            MethodArgumentTypeMismatchException.class, HttpStatus.NOT_FOUND,
            NoResourceFoundException.class, HttpStatus.NOT_FOUND,
            HttpMessageNotReadableException.class, HttpStatus.UNPROCESSABLE_ENTITY,
            HttpRequestMethodNotSupportedException.class, HttpStatus.METHOD_NOT_ALLOWED,
            BeanCreationException.class, HttpStatus.NOT_FOUND,
            NotFoundEntityException.class, HttpStatus.NOT_FOUND,
            ProductPurchaseException.class, HttpStatus.NOT_FOUND
    );

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class,
            NoResourceFoundException.class,
            HttpMessageNotReadableException.class,
            HttpRequestMethodNotSupportedException.class,
            BeanCreationException.class,
            NotFoundEntityException.class,
            ProductPurchaseException.class
    })
    public ResponseEntity<ErrorResponseDTO> handleExceptions(Exception exception, HttpServletRequest request) {
        return ErrorResponseBuilder.build(EXCEPTION_STATUS_MAP, exception, request);
    }
}
