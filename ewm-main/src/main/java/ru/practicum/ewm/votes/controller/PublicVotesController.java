package ru.practicum.ewm.votes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.votes.dto.VoteDto;
import ru.practicum.ewm.votes.service.VoteService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PublicVotesController {

    private final VoteService voteService;

    @PostMapping("/votes")
    @ResponseStatus(HttpStatus.CREATED)
    public VoteDto getEventById(@RequestBody @Valid VoteDto voteDto) {
        return voteService.create(voteDto);
    }

    @DeleteMapping("/votes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVoteByUserIdAndEventId(@RequestParam(value = "userId") Long userId,
                                             @RequestParam(value = "eventId") Long eventId) {
        voteService.deleteVoteByUserIdAndEventId(userId, eventId);
    }
}
