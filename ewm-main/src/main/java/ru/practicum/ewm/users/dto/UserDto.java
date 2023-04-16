package ru.practicum.ewm.users.dto;

import lombok.Value;

import java.io.Serializable;

@Value
public class UserDto implements Serializable {
    Long id;
    String name;
    String email;
}
