package ru.practicum.ewm.votes.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class VoteDto {
    @NotNull
    Long eventId;
    @NotNull

    Long userId;
    @NotNull

    Long initiatorId;
    @NotNull
    VoteType voteType;
}
