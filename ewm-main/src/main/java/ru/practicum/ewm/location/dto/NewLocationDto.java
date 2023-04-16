package ru.practicum.ewm.location.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Value
public class NewLocationDto implements Serializable {
    @NotNull
    Float lat;
    @NotNull
    Float lon;
}
