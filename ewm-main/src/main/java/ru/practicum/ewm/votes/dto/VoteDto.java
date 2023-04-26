package ru.practicum.ewm.votes.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class VoteDto {
    @NotNull
    Long eventId;
    @NotNull
    Long userId;
    @NotNull
    Long initiatorId;
    @NotBlank
    String voteType;
}
