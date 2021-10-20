package com.league.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Match {
	private String team1;
	private String team2;
	private int score1;
	private int score2;

	public Match(String[] teamsData) {
		String d1 =teamsData[0].trim();
		String d2 =teamsData[1].trim();

		team1 = d1.substring(0, d1.lastIndexOf(" "));
		score1 = Integer.parseInt(d1.substring(d1.lastIndexOf(" ")+1));

		team2 = d2.substring(0, d2.lastIndexOf(" "));
		score2 = Integer.parseInt(d2.substring(d2.lastIndexOf(" ")+1));
	}
}
