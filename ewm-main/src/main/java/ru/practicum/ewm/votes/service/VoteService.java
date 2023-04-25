package ru.practicum.ewm.votes.service;

import ru.practicum.ewm.votes.dto.VoteDto;

public interface VoteService {

    VoteDto create(VoteDto newVoteDto);

    void deleteVoteByUserIdAndEventId(Long userId, Long eventId);
}
