package com.league.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LeagueResult {

	List<TeamResult> teamResults;

	public String toFriendlyString() {
		StringBuilder s = new StringBuilder();
		int pos = 1;
		for (TeamResult tr : teamResults) {
			s.append(pos++).append(". ").append(tr.toFriendlyString()).append("\n");
		}

		return s.substring(0,s.length()-1);
	}


}
