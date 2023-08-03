package com.example.Proiectspring.mapper;

import com.example.Proiectspring.dto.EditTeamDto;
import com.example.Proiectspring.dto.PlayerOverviewDto;
import com.example.Proiectspring.dto.TeamFormDto;
import com.example.Proiectspring.dto.TeamOverviewDto;
import com.example.Proiectspring.model.Team;
import com.example.Proiectspring.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamMapper {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    PlayerMapper playerMapper;

    public List<TeamOverviewDto> getTeamDtoList(List<Team> teamList) {
        return teamList.stream()
                .map(team -> mapToTeamDto(team))
                .collect(Collectors.toList());
    }

    public TeamOverviewDto mapToTeamDto(Team team) {
        List<PlayerOverviewDto> playerList = playerMapper.getPlayerDtoList(teamRepository.findPlayersByTeamId(team.getId()));

        return TeamOverviewDto.builder()
                .id(team.getId())
                .team(team.getTeam())
                .ranking(team.getRanking())
                .liga(team.getLiga())
                .numberOfPlayers(team.getNumberOfPlayers())
                .playerList(playerList)
                .build();
    }

    public Team mapToTeam(TeamFormDto teamDto) {
        Team team=Team.builder()
                .id(teamDto.getId())
                .liga(teamDto.getLiga())
                .team(teamDto.getTeam())
                .build();
        return  team;
    }

    public Team mapToTeam(EditTeamDto editTeamDto) {
        return Team.builder()
                .id(editTeamDto.getId())
                .team(editTeamDto.getTeam())
                .ranking(editTeamDto.getRanking())
                .liga(editTeamDto.getLiga())
                .build();
    }

    public TeamOverviewDto mapToTeamOverviewDto(Team team) {
        return TeamOverviewDto.builder()
                .id(team.getId())
                .team(team.getTeam())
                .ranking(team.getRanking())
                .liga(team.getLiga())
                .numberOfPlayers(team.getPlayerList().size())
                .build();
    }

}
