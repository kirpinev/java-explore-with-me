package ru.practicum.ewm.service;

import ru.practicum.ewm.dto.CreatedEndpointHitDto;
import ru.practicum.ewm.dto.EndpointHitDto;
import ru.practicum.ewm.dto.ViewStatDto;

import java.time.LocalDateTime;
import java.util.List;

public interface EndpointHitService {
    CreatedEndpointHitDto create(EndpointHitDto endpointHitDto);

    List<ViewStatDto> getAll(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}
