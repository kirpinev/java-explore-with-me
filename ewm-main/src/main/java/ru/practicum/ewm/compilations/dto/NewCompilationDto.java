package ru.practicum.ewm.compilations.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Value
public class NewCompilationDto implements Serializable {
    @NotNull
    Boolean pinned;
    @NotBlank
    String title;
    List<Long> events;
}