package ru.practicum.ewm.users.dto;

import lombok.Value;

import java.io.Serializable;

@Value
public class UserShortDto implements Serializable {
    Long id;
    String name;
}