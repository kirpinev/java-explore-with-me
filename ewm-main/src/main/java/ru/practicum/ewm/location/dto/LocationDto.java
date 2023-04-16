package ru.practicum.ewm.location.dto;

import lombok.Value;

import java.io.Serializable;

@Value
public class LocationDto implements Serializable {
    Long id;
    Float lat;
    Float lon;
}
