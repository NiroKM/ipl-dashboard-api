package com.example.demo.controller;

import java.util.List;

import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.MatchDao;
import com.example.demo.model.Match;
import com.example.demo.model.Team;
import com.example.demo.repository.MatchRepository;
import com.example.demo.repository.TeamRepository;

@RestController
public class TeamController {
	
	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private MatchDao matchDao;

	@GetMapping("/team/{teamName}")
	public Team getTeamData(@PathVariable String teamName) {
		Team team = teamRepository.findByTeamName(teamName);
		List<Match> matches = matchDao.findLatestMatches(teamName, 4);
		team.setMatches(matches);
		return team;
	}
}
