package ru.practicum.ewm.users.model;

import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Formula("((SELECT COUNT(*) FROM votes v WHERE v.vote_type = 0 AND v.initiator_id = id) " +
            "- (SELECT COUNT(*) FROM votes v WHERE v.vote_type = 1 AND v.initiator_id = id))")
    private Integer rating;
}
