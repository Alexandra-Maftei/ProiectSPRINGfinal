package com.example.Proiectspring.repository;

import com.example.Proiectspring.model.Player;
import com.example.Proiectspring.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    @Query("SELECT DISTINCT t.liga FROM Team t")
    List<String> findAllLeagues();
    @Query("SELECT p FROM Player p WHERE p.team.id = ?1")
    List<Player> findPlayersByTeamId(int teamId);
}
