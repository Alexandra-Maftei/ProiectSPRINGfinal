package com.example.Proiectspring.mapper;

import com.example.Proiectspring.dto.EditPlayerDto;
import com.example.Proiectspring.dto.PlayerFormDto;
import com.example.Proiectspring.dto.PlayerOverviewDto;
import com.example.Proiectspring.model.Player;
import com.example.Proiectspring.model.Team;
import com.example.Proiectspring.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PlayerMapper {

    @Autowired
    TeamRepository teamRepository;
    public List<PlayerOverviewDto> getPlayerDtoList(List<Player> playerList) {
        return playerList.stream()
                .map(player -> mapToPlayerDto(player))
                .collect(Collectors.toList());
    }

    public PlayerOverviewDto mapToPlayerDto(Player player) {
        return PlayerOverviewDto.builder()
                .id(player.getId())
                .number(player.getNumber())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .position(player.getPosition())
                .teamName(player.getTeam() != null ? player.getTeam().getTeam() : "No team assigned")
                .build();
    }

    public Player mapToPlayer(PlayerFormDto playerDto) {
        Player player = Player.builder()
                .number(playerDto.getNumber())
                .firstName(playerDto.getFirstName())
                .lastName(playerDto.getLastName())
                .position(playerDto.getPosition())
                .build();


        int teamId = playerDto.getTeamId();


        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            player.setTeam(team);
        }

        return player;
    }

    public EditPlayerDto mapToEditPlayerDto(Player player) {
        EditPlayerDto editPlayerDto = EditPlayerDto.builder()
                .id(player.getId()) // Include the player's ID in the EditPlayerDto
                .number(player.getNumber())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .position(player.getPosition())
                .teamId(player.getTeam() != null ? player.getTeam().getId() : null)
                //.allPositions(new ArrayList<>()) // You might want to populate this with relevant data.
                .build();

        return editPlayerDto;
    }

}
