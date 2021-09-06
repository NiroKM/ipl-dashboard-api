package com.example.demo.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.demo.model.Match;
import com.example.demo.repository.MatchRepository;

@Component
public class MatchDao {
	
	@Autowired
	private MatchRepository matchRepository;
	
	public List<Match> findLatestMatches(String teamName,int records){
		Pageable pageable = PageRequest.of(0, records);
		return matchRepository.findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable);
	}
}
