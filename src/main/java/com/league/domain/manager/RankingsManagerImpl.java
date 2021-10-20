package com.league.domain.manager;

import com.league.domain.model.LeagueData;
import com.league.domain.model.LeagueResult;
import com.league.domain.model.Match;
import com.league.domain.model.MatchResult;
import org.springframework.beans.factory.annotation.Value;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RankingsManagerImpl implements RankingsManager {

	@Value("${rankings.team.winning.score:3}")
	private int winningScore = 3;

	@Value("${rankings.team.losing.score:0}")
	private int losingScore = 0;

	@Value("${rankings.team.draw.score:1}")
	private int drawScore = 1;

	private final TeamResultComparator teamResultComparator = new TeamResultComparator();

	@Override
	public LeagueResult processLeagueData(LeagueData leagueData) {
		Map<String, Integer> processedData = new HashMap<>();

		for (Match match : leagueData.getMatches()) {
			processedData.computeIfAbsent(match.getTeam1(), t -> processedData.put(t, 0));
			processedData.computeIfAbsent(match.getTeam2(), t -> processedData.put(t, 0));

			if (match.getScore1() > match.getScore2()) {
				processedData.put(match.getTeam1(), processedData.get(match.getTeam1()) + winningScore);
				processedData.put(match.getTeam2(), processedData.get(match.getTeam2()) + losingScore);
			} else if (match.getScore2() > match.getScore1()) {
				processedData.put(match.getTeam1(), processedData.get(match.getTeam1()) + losingScore);
				processedData.put(match.getTeam2(), processedData.get(match.getTeam2()) + winningScore);
			} else {
				processedData.put(match.getTeam1(), processedData.get(match.getTeam1()) + drawScore);
				processedData.put(match.getTeam2(), processedData.get(match.getTeam2()) + drawScore);
			}
		}

		return new LeagueResult(
				processedData.entrySet()
						.stream()
						.map(e -> new MatchResult(e.getKey(), e.getValue()))
						.sorted(teamResultComparator)
						.collect(Collectors.toList())
		);

	}

	private static class TeamResultComparator implements Comparator<MatchResult> {

		@Override
		public int compare(MatchResult tr1, MatchResult tr2) {
			int diff = tr2.getScore() - tr1.getScore();
			return diff == 0 ? tr1.getTeam().compareTo(tr2.getTeam()) : diff;
		}
	}

}
