package com.league.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MatchData {
	private String team1;
	private String team2;
	private int score1;
	private int score2;

	public static MatchData fromStrings(String[] teamsData) {
		MatchData m = new MatchData();

		String d1 =teamsData[0].trim();
		String d2 =teamsData[1].trim();

		m.setTeam1(d1.substring(0, d1.lastIndexOf(" ")));
		m.setScore1(Integer.parseInt(d1.substring(d1.lastIndexOf(" ")+1)));

		m.setTeam2(d2.substring(0, d2.lastIndexOf(" ")));
		m.setScore2(Integer.parseInt(d2.substring(d2.lastIndexOf(" ")+1)));

		return m;
	}
}
