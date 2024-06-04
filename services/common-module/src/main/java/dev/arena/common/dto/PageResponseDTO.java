package dev.arena.common.dto;

import java.util.List;

public record PageResponseDTO<T> (
        List<T> data,
        int pageNumber,
        int pageSize,
        int totalPages,
        String nextPageUrl,
        String previousUrl
){
}