package ru.practicum.ewm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.ewm.dto.CreatedEndpointHitDto;
import ru.practicum.ewm.dto.EndpointHitDto;
import ru.practicum.ewm.dto.ViewStatDto;
import ru.practicum.ewm.model.EndpointHit;
import ru.practicum.ewm.model.ViewStat;
import ru.practicum.ewm.repository.EndpointHitRepository;
import ru.practicum.ewm.service.EndpointHitServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EndpointHitServiceImplTest {

    @InjectMocks
    private EndpointHitServiceImpl endpointHitService;

    @Mock
    private EndpointHitRepository endpointHitRepository;

    private final EndpointHitDto endpointHitDto = new EndpointHitDto(
            "ewm-main-service",
            "/events",
            "192.163.0.1",
            LocalDateTime.now());

    private final ViewStatDto viewStatDto = new ViewStatDto(
            endpointHitDto.getApp(),
            endpointHitDto.getUri(),
            1L);

    private final ViewStat viewStat = new ViewStat(
            endpointHitDto.getUri(),
            endpointHitDto.getApp(),
            1L);

    private final CreatedEndpointHitDto createdEndpointHitDto = new CreatedEndpointHitDto(
            1L,
            endpointHitDto.getApp(),
            endpointHitDto.getUri(),
            endpointHitDto.getIp(),
            endpointHitDto.getTimestamp());

    private final EndpointHit endpointHit = new EndpointHit(
            1L,
            endpointHitDto.getApp(),
            endpointHitDto.getUri(),
            endpointHitDto.getIp(),
            endpointHitDto.getTimestamp());

    @Test
    void createNewHit() {
        when(endpointHitRepository.save(any(EndpointHit.class))).thenReturn(endpointHit);

        CreatedEndpointHitDto created = endpointHitService.createdEndpointHitDto(endpointHitDto);

        Assertions.assertNotNull(created);
        Assertions.assertEquals(createdEndpointHitDto.getId(), created.getId());
        Assertions.assertEquals(createdEndpointHitDto.getApp(), created.getApp());
        Assertions.assertEquals(createdEndpointHitDto.getUri(), created.getUri());
        Assertions.assertEquals(createdEndpointHitDto.getIp(), created.getIp());
        Assertions.assertEquals(createdEndpointHitDto.getTimestamp(), created.getTimestamp());
    }

    @Test
    void getStats() {
        when(endpointHitRepository.getViewStats(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(List.of(viewStat));

        List<ViewStatDto> viewStatDtos = endpointHitService
                .getStats(LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>(), false);

        Assertions.assertNotNull(viewStatDtos);
        Assertions.assertEquals(viewStatDto.getApp(), viewStatDtos.get(0).getApp());
        Assertions.assertEquals(viewStatDto.getUri(), viewStatDtos.get(0).getUri());
        Assertions.assertEquals(viewStatDto.getHits(), viewStatDtos.get(0).getHits());

    }

    @Test
    void getStatsWithEmptyURIAndNotUniq() {
        when(endpointHitRepository.getViewStats(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(List.of(viewStat));

        List<ViewStatDto> viewStatDtos = endpointHitService
                .getStats(LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>(), false);

        Assertions.assertNotNull(viewStatDtos);
        Assertions.assertEquals(viewStatDto.getApp(), viewStatDtos.get(0).getApp());
        Assertions.assertEquals(viewStatDto.getUri(), viewStatDtos.get(0).getUri());
        Assertions.assertEquals(viewStatDto.getHits(), viewStatDtos.get(0).getHits());

    }

    @Test
    void getStatsWithURIAndUniq() {
        when(endpointHitRepository.getViewStats(any(), any(LocalDateTime.class), any(LocalDateTime.class), anyBoolean()))
                .thenReturn(List.of(viewStat));

        List<ViewStatDto> viewStatDtos = endpointHitService
                .getStats(LocalDateTime.now(), LocalDateTime.now(), List.of("/event"), true);

        Assertions.assertNotNull(viewStatDtos);
        Assertions.assertEquals(viewStatDto.getApp(), viewStatDtos.get(0).getApp());
        Assertions.assertEquals(viewStatDto.getUri(), viewStatDtos.get(0).getUri());
        Assertions.assertEquals(viewStatDto.getHits(), viewStatDtos.get(0).getHits());

    }
}
