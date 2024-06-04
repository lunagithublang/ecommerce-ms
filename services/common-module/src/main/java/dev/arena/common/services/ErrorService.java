package dev.arena.common.services;

import dev.arena.common.dto.ErrorResponseDTO;
import dev.arena.common.utils.FormatDateTime;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorService {

    public static ErrorResponseDTO createErrorResponse(Map<String, String> errorMaps, HttpStatus status, HttpServletRequest request){
        return new ErrorResponseDTO(
                FormatDateTime.format(LocalDateTime.now()),
                status.value(),
                status.getReasonPhrase(),
                request.getMethod(),
                errorMaps,
                request.getRequestURI()
        );
    }
}
