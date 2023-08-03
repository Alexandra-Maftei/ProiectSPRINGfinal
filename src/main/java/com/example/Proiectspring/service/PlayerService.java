package com.example.Proiectspring.service;

import com.example.Proiectspring.dto.EditPlayerDto;
import com.example.Proiectspring.dto.PlayerFormDto;
import com.example.Proiectspring.dto.PlayerOverviewDto;
import com.example.Proiectspring.mapper.PlayerMapper;
import com.example.Proiectspring.model.Player;
import com.example.Proiectspring.model.Team;
import com.example.Proiectspring.repository.PlayerRepository;
import com.example.Proiectspring.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlayerMapper playerMapper;

    @Autowired
    TeamRepository teamRepository;

    public List<PlayerOverviewDto> getAllPlayers() {
        List<Player> playerList = playerRepository.findAll();
        return playerMapper.getPlayerDtoList(playerList);
    }

    public List<String> getDistinctPositions() {
        return playerRepository.findDistinctPositions();
    }

    
    public void savePlayer(PlayerFormDto playerDto) {
        Player player = playerMapper.mapToPlayer(playerDto);
        
        int teamId = playerDto.getTeamId();
        
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            player.setTeam(team); 

            playerRepository.save(player);
        }
    }

    public void deletePlayer(int playerId) {
        playerRepository.deleteById(playerId);
    }

    public void deletePlayersByTeamId(int teamId) {
        List<Player> teamPlayers = playerRepository.findByTeamId(teamId);
        playerRepository.deleteAll(teamPlayers);
    }

    public void saveEditPlayer(EditPlayerDto editPlayerDto) {
        Optional<Player> playerOptional = playerRepository.findById(editPlayerDto.getId());
        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            player.setNumber(editPlayerDto.getNumber());
            player.setFirstName(editPlayerDto.getFirstName());
            player.setLastName(editPlayerDto.getLastName());
            player.setPosition(editPlayerDto.getPosition());

            int teamId = editPlayerDto.getTeamId();
            Optional<Team> teamOptional = teamRepository.findById(teamId);
            if (teamOptional.isPresent()) {
                Team team = teamOptional.get();
                player.setTeam(team);
            } else {
            }

            playerRepository.save(player);
        } else {
        }
    }

    public Optional<Player> findPlayerById(int playerId) {
        return playerRepository.findById(playerId);
    }

    public EditPlayerDto mapToEditPlayer(Player player) {
        return playerMapper.mapToEditPlayerDto(player);
    }

    public List<PlayerOverviewDto> getPlayersByTeamId(int teamId) {
        List<Player> teamPlayers = playerRepository.findByTeamId(teamId);
        return playerMapper.getPlayerDtoList(teamPlayers);
    }

    public int getNumberOfPlayersByTeamId(int teamId) {
        return playerRepository.countPlayersByTeamId(teamId);
    }

    public Optional<EditPlayerDto> findEditPlayerById(int playerId) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        return playerOptional.map(playerMapper::mapToEditPlayerDto);
    }

}
