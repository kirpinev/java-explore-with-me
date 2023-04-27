package ru.practicum.ewm;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.ewm.controller.ServiceController;
import ru.practicum.ewm.dto.CreatedEndpointHitDto;
import ru.practicum.ewm.dto.EndpointHitDto;
import ru.practicum.ewm.dto.ViewStatDto;
import ru.practicum.ewm.service.EndpointHitService;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ServiceController.class)
public class ServiceControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private EndpointHitService endpointHitService;

    @Autowired
    private MockMvc mvc;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final String DATE = "2022-09-06 11:00:23";

    private final EndpointHitDto endpointHitDto = new EndpointHitDto(
            "ewm-main-service",
            "/events",
            "192.163.0.1",
            LocalDateTime.parse(DATE, DATE_TIME_FORMATTER));

    private final CreatedEndpointHitDto createdEndpointHitDto = new CreatedEndpointHitDto(
            1L,
            endpointHitDto.getApp(),
            endpointHitDto.getUri(),
            endpointHitDto.getIp(),
            endpointHitDto.getTimestamp());

    private final ViewStatDto viewStatDto = new ViewStatDto(
            createdEndpointHitDto.getApp(),
            createdEndpointHitDto.getUri(),
            1L);

    @Test
    void createNewHit() throws Exception {
        when(endpointHitService.create(any(EndpointHitDto.class)))
                .thenReturn(createdEndpointHitDto);

        mvc.perform(post("/hit")
                        .content(mapper.writeValueAsString(endpointHitDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.app", is(endpointHitDto.getApp()), String.class))
                .andExpect(jsonPath("$.uri", is(endpointHitDto.getUri()), String.class))
                .andExpect(jsonPath("$.ip", is(endpointHitDto.getIp()), String.class))
                .andExpect(jsonPath("$.timestamp", is(DATE), String.class));
    }

    @Test
    void getStats() throws Exception {
        when(endpointHitService.getAll(any(LocalDateTime.class), any(LocalDateTime.class), any(), any()))
                .thenReturn(List.of(viewStatDto));

        mvc.perform(get("/stats")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("start", "2020-05-05 00:00:00")
                        .param("end", "2023-05-05 00:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].app", is(viewStatDto.getApp()), String.class))
                .andExpect(jsonPath("$[0].uri", is(viewStatDto.getUri()), String.class))
                .andExpect(jsonPath("$[0].hits", is(viewStatDto.getHits()), Long.class));
    }
}
