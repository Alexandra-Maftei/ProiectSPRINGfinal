package com.example.Proiectspring.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BarDataDto {
    private String teamName;
    private int numberOfPlayers;

    @Override
    public String toString() {
        return "BarDataDto{" +
                "teamName='" + teamName + '\'' +
                ", numberOfPlayers=" + numberOfPlayers +
                '}';
    }
}
