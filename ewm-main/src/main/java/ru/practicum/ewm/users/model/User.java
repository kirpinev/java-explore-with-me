package ru.practicum.ewm.users.model;

import lombok.*;
import ru.practicum.ewm.votes.model.Vote;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(
        name = "user-with-votes",
        attributeNodes = @NamedAttributeNode(value = "votes")
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "initiator_id")
    private Set<Vote> votes = new HashSet<>();
}
