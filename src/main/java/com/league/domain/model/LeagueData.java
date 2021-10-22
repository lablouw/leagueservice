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

	List<MatchData> matches = new ArrayList<>();

	public static LeagueData fromString(String data) {
		LeagueData ld = new LeagueData();
		for (String matchString : data.split("\n")) {
			MatchData matchData = MatchData.fromStrings(matchString.split(","));
			ld.getMatches().add(matchData);
		}

		return ld;
	}


}
