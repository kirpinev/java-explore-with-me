package ru.practicum.ewm.compilations.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Value
public class CompilationEventDto implements Serializable {
    @NotNull
    Long compilationId;
    @NotNull
    Long eventId;
}