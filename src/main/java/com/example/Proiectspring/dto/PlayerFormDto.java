package com.example.Proiectspring.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerFormDto {
    private int number;
    private String firstName;
    private String lastName;
    private String position;
    private Integer teamId;
    private List<String> allPositions;

}

