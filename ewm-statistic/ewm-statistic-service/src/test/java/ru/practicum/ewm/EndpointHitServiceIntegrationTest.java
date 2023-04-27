package ru.practicum.ewm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.dto.CreatedEndpointHitDto;
import ru.practicum.ewm.dto.EndpointHitDto;
import ru.practicum.ewm.service.EndpointHitService;

import java.time.LocalDateTime;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EndpointHitServiceIntegrationTest {

    @Autowired
    private EndpointHitService endpointHitService;

    private final EndpointHitDto endpointHitDto = new EndpointHitDto(
            "ewm-main-service",
            "/events",
            "192.163.0.1",
            LocalDateTime.now());

    @Test
    void createNewHit() {
        CreatedEndpointHitDto createdEndpointHitDto = endpointHitService
                .create(endpointHitDto);

        Assertions.assertEquals(1L, createdEndpointHitDto.getId());
        Assertions.assertEquals(endpointHitDto.getApp(), createdEndpointHitDto.getApp());
        Assertions.assertEquals(endpointHitDto.getUri(), createdEndpointHitDto.getUri());
        Assertions.assertEquals(endpointHitDto.getIp(), createdEndpointHitDto.getIp());
        Assertions.assertTrue(endpointHitDto.getTimestamp().isEqual(createdEndpointHitDto.getTimestamp()));
    }
}
