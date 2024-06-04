package dev.arena.common.utils;

import dev.arena.common.dto.PageResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.function.Function;

public class PaginationUtils {

    public static String getNextPageUrl(Page<?> page, UriComponentsBuilder uriComponentsBuilder) {

        if (page.hasNext()) {
            return uriComponentsBuilder
                    .replaceQueryParam("page", page.getNumber() + 1)
                    .replaceQueryParam("size", page.getSize())
                    .build()
                    .toUriString();
        }

        return null;
    }

    public static String getPreviousPageUrl(Page<?> page, UriComponentsBuilder uriComponentsBuilder) {

        if (page.hasPrevious()) {
            return uriComponentsBuilder
                    .replaceQueryParam("page", page.getNumber() - 1)
                    .replaceQueryParam("size", page.getSize())
                    .build()
                    .toUriString();
        }

        return null;
    }

    public static <T, U> PageResponseDTO<U> createPageResponse(Page<T> page, Function<T, U> mapper, UriComponentsBuilder uriComponentsBuilder) {

        List<U> dtoList = page.stream().map(mapper).toList();

        String nextPageUrl = getNextPageUrl(page, uriComponentsBuilder);
        String previousUrl = getPreviousPageUrl(page, uriComponentsBuilder);

        return new PageResponseDTO<>(
                dtoList,
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                nextPageUrl,
                previousUrl
        );
    }
}
