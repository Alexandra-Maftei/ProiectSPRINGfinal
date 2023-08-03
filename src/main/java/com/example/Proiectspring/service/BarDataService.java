package com.example.Proiectspring.service;

import com.example.Proiectspring.dto.BarDataDto;
import com.example.Proiectspring.dto.TeamFormDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BarDataService {
    private final TeamService teamService;

    public BarDataService(TeamService teamService) {
        this.teamService = teamService;
    }

    public List<BarDataDto> getChartData() {
        List<TeamFormDto> teamData = teamService.getTeams();
        List<BarDataDto> chartData = new ArrayList<>();

        for (TeamFormDto team : teamData) {
            BarDataDto chartDataDto = new BarDataDto();
            chartDataDto.setTeamName(team.getTeam());
            chartDataDto.setNumberOfPlayers(team.getNumberOfPlayers());
            chartData.add(chartDataDto);
        }

        return chartData;
    }
}
