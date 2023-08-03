package com.example.Proiectspring.repository;


import com.example.Proiectspring.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    @Query("SELECT DISTINCT p.position FROM Player p")
    List<String> findDistinctPositions();

    @Query("SELECT p FROM Player p WHERE p.team.id = :teamId")
    List<Player> findByTeamId(@Param("teamId") int teamId);

    @Query("SELECT COUNT(p) FROM Player p WHERE p.team.id = :teamId")
    int countPlayersByTeamId(@Param("teamId") int teamId);

}
