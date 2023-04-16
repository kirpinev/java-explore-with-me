package ru.practicum.ewm.compilations.dto;

import lombok.Value;
import ru.practicum.ewm.events.dto.EventDto;

import java.io.Serializable;
import java.util.List;

@Value
public class CompilationDto implements Serializable {
    Long id;
    Boolean pinned;
    String title;
    List<EventDto> events;
}
