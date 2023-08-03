package com.example.Proiectspring.controller;

import com.example.Proiectspring.dto.EditTeamDto;
import com.example.Proiectspring.dto.TeamFormDto;
import com.example.Proiectspring.dto.TeamOverviewDto;
import com.example.Proiectspring.service.PlayerService;
import com.example.Proiectspring.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TeamController {

    @Autowired
    TeamService teamService;

    @Autowired
    PlayerService playerService;

    @GetMapping(value = "/TeamOverview")
    public String Team(Model model, Authentication authentication) {
        List<TeamOverviewDto> teamList = teamService.getAllTeams();
        model.addAttribute("teamList", teamList);

        if (authentication != null) {
            String role = authentication.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse(null);
            model.addAttribute("isAdmin", "ROLE_ADMIN".equals(role));
        } else {
            model.addAttribute("isAdmin", false);
        }

        return "TeamOverview";
    }

    @GetMapping(value = "/TeamForm")
    public String getTeamForm(Model model) {
        model.addAttribute("teams", new TeamFormDto());
        List<String> leagueList = teamService.findAllLeagues();
        model.addAttribute("leagueList", leagueList);
        return "TeamForm";
    }

    @PostMapping(value = "/submitTeam")
    public String submitTeam(@ModelAttribute("teams") TeamFormDto teamFormDto, Model model) {
        teamService.saveTeam(teamFormDto);
        return "redirect:/TeamOverview";
    }

    @PostMapping(value = "/deleteTeam/{id}")
    public String deleteTeam(@PathVariable("id") int teamId) {
        teamService.deleteById(teamId);
        return "redirect:/TeamOverview";
    }

    @PostMapping(value = "/updateTeam")
    public String updateTeam(@ModelAttribute("team") EditTeamDto editTeamDto, Model model) {
        teamService.saveEditTeam(editTeamDto);
        return "redirect:/TeamOverview";
    }

    @GetMapping(value = "/editTeam/{id}")
    public String editTeam(@PathVariable("id") int teamId, Model model) {
        Optional<TeamOverviewDto> teamOptional = teamService.findTeamOverviewById(teamId);
        if (teamOptional.isPresent()) {
            TeamOverviewDto team = teamOptional.get();
            model.addAttribute("team", team);

            List<String> leagueList = teamService.findAllLeagues();
            model.addAttribute("leagueList", leagueList);

            return "EditTeam";
        } else {
            return "redirect:/TeamOverview";
        }
    }

}
