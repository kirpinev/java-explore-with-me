package ru.practicum.ewm.categories.dto;

import lombok.Value;

import java.io.Serializable;

@Value
public class CategoryDto implements Serializable {
    Long id;
    String name;
}
