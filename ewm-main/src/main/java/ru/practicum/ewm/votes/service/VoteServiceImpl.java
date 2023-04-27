package ru.practicum.ewm.votes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.events.dto.State;
import ru.practicum.ewm.events.model.Event;
import ru.practicum.ewm.events.repository.EventRepository;
import ru.practicum.ewm.exception.NotFoundException;
import ru.practicum.ewm.exception.OperationException;
import ru.practicum.ewm.votes.dto.VoteDto;
import ru.practicum.ewm.votes.model.Vote;
import ru.practicum.ewm.votes.repository.VoteRepository;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    private final EventRepository eventRepository;

    private static final String EVENT_NOT_FOUND_EXCEPTION_MESSAGE =
            "Event with id=%s was not found";
    private static final String EVENT_WRONG_STATE_EXCEPTION_MESSAGE =
            "Cannot add like to event because of wrong state. Current state: %s";

    private static final String VOTE_NOT_FOUND_EXCEPTION_MESSAGE =
            "Cannot find vote by userId=%s and eventId=%s";

    @Override
    @Transactional
    public VoteDto create(VoteDto voteDto) {
        Vote vote = VoteMapper.toVote(voteDto);
        Event event = eventRepository.findEventById(voteDto.getEventId())
                .orElseThrow(() -> new NotFoundException(String.format(EVENT_NOT_FOUND_EXCEPTION_MESSAGE,
                        voteDto.getEventId())));

        if (!event.getState().equals(State.PUBLISHED)) {
            throw new OperationException(String.format(EVENT_WRONG_STATE_EXCEPTION_MESSAGE,
                    event.getState()));
        }

        return VoteMapper.toVoteDto(voteRepository.save(vote));
    }

    @Override
    @Transactional
    public void deleteByUserIdAndEventId(Long userId, Long eventId) {
        Integer integer = voteRepository.deleteVoteByUserIdAndEventId(userId, eventId);

        if (integer == 0) {
            throw new NotFoundException(String.format(VOTE_NOT_FOUND_EXCEPTION_MESSAGE,
                    userId, eventId));
        }
    }
}
