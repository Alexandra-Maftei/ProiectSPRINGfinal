package com.example.Proiectspring.service;

import com.example.Proiectspring.dto.EditTeamDto;
import com.example.Proiectspring.dto.TeamFormDto;
import com.example.Proiectspring.dto.TeamOverviewDto;
import com.example.Proiectspring.mapper.TeamMapper;
import com.example.Proiectspring.model.Team;
import com.example.Proiectspring.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    PlayerService playerService;

    public List<TeamOverviewDto> getAllTeams() {
        List<Team> teamList = teamRepository.findAll();
        return teamMapper.getTeamDtoList(teamList);
    }

    public void saveTeam(TeamFormDto teamDto) {
        Team team = teamMapper.mapToTeam(teamDto);
        teamRepository.save(team);
    }

    public void saveEditTeam(EditTeamDto editTeamDto) {
        Team team = teamMapper.mapToTeam(editTeamDto);
        teamRepository.save(team);
    }

    public List<String> findAllLeagues() {
        return teamRepository.findAllLeagues();
    }


    public void deleteById(int teamId) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            playerService.deletePlayersByTeamId(teamId);
            teamRepository.deleteById(teamId);
        }
    }

    public Optional<Team> findTeamById(int teamId) {
        return teamRepository.findById(teamId);
    }

    public Optional<String> findTeamNameById(int teamId) {
        return teamRepository.findById(teamId).map(Team::getTeam);
    }

    public List<TeamFormDto> getTeams() {
        List<Team> teams = teamRepository.findAll();
        List<TeamFormDto> teamFormDtos = new ArrayList<>();

        for (Team team : teams) {
            int numberOfPlayers = playerService.getNumberOfPlayersByTeamId(team.getId());

            TeamFormDto teamFormDto = TeamFormDto.builder()
                    .id(team.getId())
                    .team(team.getTeam())
                    .ranking(team.getRanking())
                    .liga(team.getLiga())
                    .numberOfPlayers(numberOfPlayers) // Add the number of players to the DTO
                    .build();
            teamFormDtos.add(teamFormDto);
        }

        return teamFormDtos;
    }

    public Optional<TeamOverviewDto> findTeamOverviewById(int teamId) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        return teamOptional.map(teamMapper::mapToTeamOverviewDto);
    }

}
