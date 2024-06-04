package dev.arena.common.dto;

import java.util.HashMap;
import java.util.Map;

public record ErrorResponseDTO (
        String timestamp,
        int status,
        String error,
        String method,
        Map<String, String> errors,
        String path
) {
}
