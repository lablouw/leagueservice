package com.league.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LeagueData {

	List<Match> matches = new ArrayList<>();

	public LeagueData(String data) {
		for (String matchData : data.split("\n")) {
			Match match = new Match(matchData.split(","));
			matches.add(match);
		}
	}


}
