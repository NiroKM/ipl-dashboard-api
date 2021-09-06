package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{
	public List<Match> findByTeam1OrTeam2OrderByDateDesc(String teamName1,String teamName2,Pageable pageable);
	
	@Query("select m from Match m where (m.team1= :teamName or m.team2= :teamName) and m.date between :startDate and :endDate order by m.date desc")
	public List<Match> getMatchesByTeamBetweenDates(
			@Param("teamName") String teamName, 
			@Param("startDate") LocalDate startDate, 
			@Param("endDate") LocalDate endDate);
}
