package ru.practicum.ewm.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Value
public class EndpointHitDto implements Serializable {
    @NotBlank
    String app;
    @NotBlank
    String uri;
    @NotBlank
    String ip;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime timestamp;

    @JsonCreator
    public EndpointHitDto(
            @JsonProperty("app") String app,
            @JsonProperty("uri") String uri,
            @JsonProperty("ip") String ip,
            @JsonProperty("timestamp") String timestamp) {
        this.app = app;
        this.uri = uri;
        this.ip = ip;
        this.timestamp = LocalDateTime.parse(timestamp,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
