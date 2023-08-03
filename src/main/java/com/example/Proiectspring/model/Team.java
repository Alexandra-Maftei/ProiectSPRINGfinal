package com.example.Proiectspring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_team")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String team;

    @Column(name = "rank")
    private int ranking;

    @Column(name = "league")
    private String liga;

    @OneToMany(mappedBy = "team")
    private List<Player> playerList = new ArrayList<>();


    public Team(String team) {
        this.team = team;
    }


    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public List<Player> getPlayerList() {return playerList;}

    public void setPlayerList(List<Player> playerList) {this.playerList = playerList;}

    public int getNumberOfPlayers() {
        return playerList.size();
    }

    public void addPlayer(Player player) {
        playerList.add(player);
        player.setTeam(this);
    }

    @Override
    public String toString() {
        return "Team{" +
                "team='" + team + '\'' +
                ", ranking=" + ranking +
                ", liga='" + liga + '\'' +
                '}';
    }
}
