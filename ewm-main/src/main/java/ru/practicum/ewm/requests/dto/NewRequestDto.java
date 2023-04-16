package ru.practicum.ewm.requests.dto;

import lombok.Value;
import ru.practicum.ewm.events.dto.State;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Value
public class NewRequestDto implements Serializable {
    @NotNull
    Long event;
    @NotNull
    Long requester;
    @NotNull
    State status;
}
