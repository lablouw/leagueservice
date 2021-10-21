package com.league.domain.manager;

import com.league.domain.model.LeagueData;
import com.league.domain.model.LeagueResult;
import com.league.domain.model.MatchData;
import com.league.domain.model.TeamResult;
import org.springframework.beans.factory.annotation.Value;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RankingsManagerImpl implements RankingsManager {

	@Value("${rankings.team.winning.score}")
	private int winningScore = 3;

	@Value("${rankings.team.losing.score}")
	private int losingScore = 0;

	@Value("${rankings.team.draw.score}")
	private int drawScore = 1;

	private final TeamResultComparator teamResultComparator = new TeamResultComparator();

	@Override
	public LeagueResult processLeagueData(LeagueData leagueData) {
		//teamName, score
		Map<String, Integer> processedData = new HashMap<>();

		for (MatchData matchResult : leagueData.getMatches()) {
			processedData.computeIfAbsent(matchResult.getTeam1(), t -> processedData.put(t, 0));
			processedData.computeIfAbsent(matchResult.getTeam2(), t -> processedData.put(t, 0));

			if (matchResult.getScore1() > matchResult.getScore2()) {
				processedData.put(matchResult.getTeam1(), processedData.get(matchResult.getTeam1()) + winningScore);
				processedData.put(matchResult.getTeam2(), processedData.get(matchResult.getTeam2()) + losingScore);
			} else if (matchResult.getScore2() > matchResult.getScore1()) {
				processedData.put(matchResult.getTeam1(), processedData.get(matchResult.getTeam1()) + losingScore);
				processedData.put(matchResult.getTeam2(), processedData.get(matchResult.getTeam2()) + winningScore);
			} else {
				processedData.put(matchResult.getTeam1(), processedData.get(matchResult.getTeam1()) + drawScore);
				processedData.put(matchResult.getTeam2(), processedData.get(matchResult.getTeam2()) + drawScore);
			}
		}

		return new LeagueResult(
				processedData.entrySet()
						.stream()
						.map(e -> new TeamResult(e.getKey(), e.getValue()))
						.sorted(teamResultComparator)
						.collect(Collectors.toList())
		);

	}

	private static class TeamResultComparator implements Comparator<TeamResult> {

		@Override
		public int compare(TeamResult tr1, TeamResult tr2) {
			int diff = tr2.getScore() - tr1.getScore();
			return diff == 0 ? tr1.getTeam().compareTo(tr2.getTeam()) : diff;
		}
	}

}
