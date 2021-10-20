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

	List<MatchResult> teamResults;

	public String toFriendlyString() {
		StringBuilder s = new StringBuilder();
		int pos = 1;
		for (MatchResult tr : teamResults) {
			s.append(pos++).append(". ").append(tr.toFriendlyString()).append("\n");
		}

		return s.toString();
	}


}
