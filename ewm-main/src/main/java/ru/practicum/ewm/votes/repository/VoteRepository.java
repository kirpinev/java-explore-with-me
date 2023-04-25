package ru.practicum.ewm.votes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.votes.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Integer deleteVoteByUserIdAndEventId(Long userId, Long eventId);
}
