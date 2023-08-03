package com.example.Proiectspring.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerOverviewDto {
    private int id;
    private int number;
    private String firstName;
    private String lastName;
    private String teamName;
    private String position;
}
