package com.example.demo.data;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.example.demo.model.Match;


public class MatchDataProcessor implements ItemProcessor<MatchInput, Match>{
	private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

	  @Override
	  public Match process(final MatchInput matchInput) throws Exception {
	     Match match = new Match();
	     
	     match.setId(Long.parseLong(matchInput.getId()));
	     match.setCity(matchInput.getCity());
	     match.setDate(LocalDate.parse(matchInput.getDate()));
	     match.setVenue(matchInput.getVenue());
	     
	     //select first batting team as the first innings team and the other as the second innings team
	     String firstInningsTeam, secondInningsTeam;
	     if(matchInput.getToss_decision().equals("bat")) {
	    	 firstInningsTeam = matchInput.getToss_winner();
	    	 secondInningsTeam = matchInput.getTeam1().equals(firstInningsTeam) ? matchInput.getTeam2() : matchInput.getTeam1();
	     }else {
	    	 secondInningsTeam = matchInput.getToss_winner();
	    	 firstInningsTeam = matchInput.getTeam1().equals(secondInningsTeam) ? matchInput.getTeam2() : matchInput.getTeam1();
	     }
	     
	     match.setTeam1(firstInningsTeam);
	     match.setTeam2(secondInningsTeam);
	     match.setTossWinner(matchInput.getToss_winner());
	     match.setTossDecision(matchInput.getToss_decision());
	     match.setMatchWinner(matchInput.getWinner());
	     match.setResult(matchInput.getResult());
	     match.setResultMargin(matchInput.getResult_margin());
	     match.setUmpire1(matchInput.getUmpire1());
	     match.setUmpire2(matchInput.getUmpire2());
	     
	     return match;
	  }

}
