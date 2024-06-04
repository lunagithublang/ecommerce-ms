package dev.arena.notification.exceptions;

import dev.arena.common.dto.ErrorResponseDTO;
import dev.arena.common.exceptions.BusinessException;
import dev.arena.common.exceptions.ErrorResponseBuilder;
import dev.arena.common.exceptions.NotFoundEntityException;
import jakarta.servlet.http.HttpServletRequest;
import org.eclipse.angus.mail.util.MailConnectException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Map<Class<? extends Exception>, HttpStatus> EXCEPTION_STATUS_MAP = Map.of(
            MethodArgumentNotValidException.class, HttpStatus.UNPROCESSABLE_ENTITY,
            MethodArgumentTypeMismatchException.class, HttpStatus.NOT_FOUND,
            HttpMessageNotReadableException.class, HttpStatus.UNPROCESSABLE_ENTITY,
            HttpRequestMethodNotSupportedException.class, HttpStatus.METHOD_NOT_ALLOWED,
            BeanCreationException.class, HttpStatus.NOT_FOUND,
            NotFoundEntityException.class, HttpStatus.NOT_FOUND,
            BusinessException.class, HttpStatus.NOT_FOUND,
            ListenerExecutionFailedException.class, HttpStatus.INTERNAL_SERVER_ERROR,
            MailConnectException.class, HttpStatus.INTERNAL_SERVER_ERROR
    );

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpRequestMethodNotSupportedException.class,
            BeanCreationException.class,
            NotFoundEntityException.class,
            BusinessException.class,
            HttpClientErrorException.class,
            ListenerExecutionFailedException.class,
            MailConnectException.class
    })
    public ResponseEntity<ErrorResponseDTO> handleExceptions(Exception exception, HttpServletRequest request) {
        return ErrorResponseBuilder.build(EXCEPTION_STATUS_MAP, exception, request);
    }
}
