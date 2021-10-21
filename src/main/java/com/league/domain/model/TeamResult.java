package com.league.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TeamResult {
	public String team;
	public int score;

	public String toFriendlyString() {
		return team + ", " + score + (score==1 ? " pt" : " pts");
	}
}
