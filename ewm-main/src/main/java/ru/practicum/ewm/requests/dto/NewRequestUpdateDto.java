package ru.practicum.ewm.requests.dto;

import lombok.Value;
import ru.practicum.ewm.events.dto.State;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Value
public class NewRequestUpdateDto implements Serializable {
    @NotNull
    List<Long> requestIds;
    @NotNull
    State status;
}
