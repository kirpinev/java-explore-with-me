package ru.practicum.ewm.events.dto;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public enum SortVariant {
    EVENT_DATE,
    VIEWS,
    LIKES,
    DISLIKES;

    public String toLowerCamelCase() {
        String name = name();
        AtomicInteger index = new AtomicInteger();

        return Arrays.stream(name.split("_")).map((value) -> {
            if (index.getAndIncrement() > 0) {
                return value.substring(0, 1).toUpperCase()
                        + value.substring(1).toLowerCase();
            }

            return value.toLowerCase();
        }).collect(Collectors.joining());
    }
}
