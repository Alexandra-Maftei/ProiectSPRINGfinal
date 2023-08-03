package com.example.Proiectspring.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamOverviewDto {
    private int id;
    private String team;
    private int ranking;
    private String liga;
    private int numberOfPlayers;
    private List<PlayerOverviewDto> playerList;
}
