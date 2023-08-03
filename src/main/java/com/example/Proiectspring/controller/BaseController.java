package com.example.Proiectspring.controller;

import com.example.Proiectspring.dto.BarDataDto;
import com.example.Proiectspring.service.BarDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BaseController {
    private final BarDataService barDataService;

    public BaseController(BarDataService barDataService) {
        this.barDataService = barDataService;
    }

    @GetMapping("/teamdata")
    public List<BarDataDto> getTeamData() {
        List<BarDataDto> teamData = barDataService.getChartData();
        return teamData;
    }

}
