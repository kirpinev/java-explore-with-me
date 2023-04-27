package ru.practicum.ewm.votes.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "votes")
@IdClass(CombinedVoteKeyId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    @Id
    @Column(name = "event_id")
    private Long eventId;
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "initiator_id")
    private Long initiatorId;
    @Column(name = "vote_type", nullable = false)
    private VoteType voteType;
}
