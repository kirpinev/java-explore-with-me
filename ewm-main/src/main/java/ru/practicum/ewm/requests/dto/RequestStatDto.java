package ru.practicum.ewm.requests.dto;

import lombok.Value;

import java.io.Serializable;

@Value
public class RequestStatDto implements Serializable {
    Long eventId;
    Long requests;
}
