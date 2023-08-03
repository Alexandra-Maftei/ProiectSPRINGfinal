package com.example.Proiectspring.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditTeamDto {
    private int id;
    private String team;
    private int ranking;
    private String liga;
}
