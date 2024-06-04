package dev.arena.common.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtils {
    public static Pageable setPageable(int page, int size, String sortedBy) {
        return PageRequest.of(page, size, Sort.by(sortedBy).descending());
    }
}
