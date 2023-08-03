package com.example.Proiectspring.controller;

import com.example.Proiectspring.dto.*;
import com.example.Proiectspring.service.PlayerService;
import com.example.Proiectspring.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @Autowired
    TeamService teamService;

    @GetMapping(value = "/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping(value = "/PlayerOverview")
    public String Player(Model model, Authentication authentication) {
        List<TeamOverviewDto> teamList = teamService.getAllTeams();
        model.addAttribute("teamList", teamList);
        List<PlayerOverviewDto> playerList = playerService.getAllPlayers();
        model.addAttribute("playerList", playerList);

        if (authentication != null) {
            String role = authentication.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse(null);
            model.addAttribute("isAdmin", "ROLE_ADMIN".equals(role));
        } else {
            model.addAttribute("isAdmin", false);
        }

        return "PlayerOverview";
    }

    @GetMapping(value = "/PlayerForm")
    public String getPlayerForm(Model model) {
        List<TeamOverviewDto> teamList = teamService.getAllTeams();
        List<String> positions = playerService.getDistinctPositions();

        PlayerFormDto playerFormDto = new PlayerFormDto();
        playerFormDto.setAllPositions(Arrays.asList("portar", "mijlocas", "fundas", "atacant", "rezerva"));

        model.addAttribute("playerFormDto", playerFormDto);
        model.addAttribute("teamList", teamList);
        model.addAttribute("positions", positions);

        return "PlayerForm";
    }


    @PostMapping(value = "/submitPlayer")
    public String submitPlayer(@ModelAttribute("playerFormDto") PlayerFormDto player, Model model) {
        playerService.savePlayer(player);
        return "redirect:/PlayerOverview";
    }

    @PostMapping(value = "/deletePlayer/{id}")
    public String deletePlayer(@PathVariable("id") int playerId) {
        playerService.deletePlayer(playerId);
        return "redirect:/PlayerOverview";
    }

    @PostMapping(value = "/updatePlayer")
    public String updatePlayer(@ModelAttribute("player") EditPlayerDto editPlayerDto, Model model) {
        playerService.saveEditPlayer(editPlayerDto);
        return "redirect:/PlayerOverview";

    }

    @GetMapping("/editPlayer/{id}")
    public String editPlayer(@PathVariable("id") int playerId, Model model) {
        Optional<EditPlayerDto> playerOptional = playerService.findEditPlayerById(playerId);
        if (playerOptional.isPresent()) {

            EditPlayerDto editPlayerDto = playerOptional.get();
            model.addAttribute("player", editPlayerDto);

            List<TeamOverviewDto> teamList = teamService.getAllTeams();
            model.addAttribute("teamList", teamList);

            List<String> predefinedPositions = List.of("portar", "fundas", "mijlocas", "atacant", "rezerva");
            List<String> dbPositions = playerService.getDistinctPositions();
            Set<String> positions = new LinkedHashSet<>(predefinedPositions);
            positions.addAll(dbPositions);
            model.addAttribute("positions", positions);

            return "EditPlayer";
        } else {
            return "ErrorPage";
        }
    }



    @GetMapping("/view")
    public String viewTeamPlayers(@RequestParam("teamId") int teamId, Model model) {
        List<PlayerOverviewDto> teamPlayers = playerService.getPlayersByTeamId(teamId);
        String teamName = teamService.findTeamNameById(teamId).orElse("");
        model.addAttribute("teamPlayers", teamPlayers);
        model.addAttribute("teamName", teamName);
        model.addAttribute("teamId", teamId);
        return "ViewTeamPlayers";
    }


}

