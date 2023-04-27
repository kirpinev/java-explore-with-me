package ru.practicum.ewm.events.service;

import ru.practicum.ewm.categories.dto.CategoryDto;
import ru.practicum.ewm.events.dto.*;
import ru.practicum.ewm.location.dto.LocationDto;
import ru.practicum.ewm.users.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    EventDto create(
            NewEventDto newEventDto,
            LocationDto locationDto,
            UserDto userDto,
            CategoryDto categoryDto);

    List<EventDto> getAllByUserId(Long userId, Integer from, Integer size);

    EventDto getByUserIdAndEventId(Long userId, Long eventId);

    EventDto updateByUserIdAndEventId(Long userId, Long eventId, NewEventDto newEventDto);

    EventDto updateByEventId(Long eventId, NewEventDto newEventDto);

    List<EventDto> getAll(LocalDateTime rangeStart, LocalDateTime rangeEnd, List<Long> users,
                          List<State> states, List<Long> categories, Integer from, Integer size);

    List<EventDto> getAllPublic(Integer from, Integer size, State state,
                                String text, List<Long> categories, Boolean paid, LocalDateTime rangeStart,
                                LocalDateTime rangeEnd, SortVariant sort, Boolean onlyAvailable, String ip, String uri);

    EventDto getPublicById(Long eventId, String ip, String url);
}
