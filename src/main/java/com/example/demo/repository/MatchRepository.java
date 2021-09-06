package com.example.demo.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{
	public List<Match> findByTeam1OrTeam2OrderByDateDesc(String teamName1,String teamName2,Pageable pageable);
}
