package ru.practicum.ewm.votes.service;

import ru.practicum.ewm.votes.dto.VoteDto;
import ru.practicum.ewm.votes.model.Vote;
import ru.practicum.ewm.votes.model.VoteType;

public class VoteMapper {

    private VoteMapper() {
    }

    public static VoteDto toVoteDto(Vote vote) {
        return new VoteDto(
                vote.getEventId(),
                vote.getUserId(),
                vote.getInitiatorId(),
                vote.getVoteType().name());
    }

    public static Vote toVote(VoteDto voteDto) {
        return new Vote(
                voteDto.getEventId(),
                voteDto.getUserId(),
                voteDto.getInitiatorId(),
                VoteType.valueOf(voteDto.getVoteType()));
    }
}
