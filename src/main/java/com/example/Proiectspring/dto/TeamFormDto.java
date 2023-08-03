package com.example.Proiectspring.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamFormDto {
    private int id;
    private String team;
    private String liga;
    private int ranking;
    private int numberOfPlayers;
}
