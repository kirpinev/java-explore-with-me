package ru.practicum.ewm.users.service;

import ru.practicum.ewm.users.dto.NewUserDto;
import ru.practicum.ewm.users.dto.UserDto;
import ru.practicum.ewm.users.model.User;
import ru.practicum.ewm.votes.dto.VoteType;
import ru.practicum.ewm.votes.model.Vote;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    private UserMapper() {
    }

    public static User toUser(NewUserDto newUserDto) {
        User user = new User();

        user.setName(newUserDto.getName());
        user.setEmail(newUserDto.getEmail());

        return user;
    }

    public static UserDto toUserDto(User user) {
        int likes = 0;
        int dislikes = 0;

        for (Vote vote : user.getVotes()) {
            if (vote.getVoteType().equals(VoteType.LIKE)) {
                likes += 1;
            } else {
                dislikes += 1;
            }
        }

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                likes - dislikes);
    }

    public static List<UserDto> toUserDto(List<User> users) {
        return users
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }
}
