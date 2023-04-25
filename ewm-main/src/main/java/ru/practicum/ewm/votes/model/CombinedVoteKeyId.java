package ru.practicum.ewm.votes.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class CombinedVoteKeyId implements Serializable {
    private Long eventId;
    private Long userId;
}