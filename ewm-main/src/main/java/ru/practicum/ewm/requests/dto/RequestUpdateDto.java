package ru.practicum.ewm.requests.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
public class RequestUpdateDto implements Serializable {
    List<RequestDto> confirmedRequests;
    List<RequestDto> rejectedRequests;
}
