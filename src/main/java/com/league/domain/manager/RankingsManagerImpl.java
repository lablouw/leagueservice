package com.league.domain.manager;

import com.league.domain.model.LeagueData;
import com.league.domain.model.LeagueResult;
import com.league.domain.model.MatchData;
import com.league.domain.model.TeamResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
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
		log.info("Processing league [matches={}]", leagueData.getMatches().size());

		//teamName, score
		Map<String, Integer> processedData = new HashMap<>();

		for (MatchData matchData : leagueData.getMatches()) {
			log.info("Processing match [matchData={}]", matchData);
			processedData.computeIfAbsent(matchData.getTeam1(), t -> processedData.put(t, 0));
			processedData.computeIfAbsent(matchData.getTeam2(), t -> processedData.put(t, 0));

			if (matchData.getScore1() > matchData.getScore2()) {
				processedData.put(matchData.getTeam1(), processedData.get(matchData.getTeam1()) + winningScore);
				processedData.put(matchData.getTeam2(), processedData.get(matchData.getTeam2()) + losingScore);
			} else if (matchData.getScore2() > matchData.getScore1()) {
				processedData.put(matchData.getTeam1(), processedData.get(matchData.getTeam1()) + losingScore);
				processedData.put(matchData.getTeam2(), processedData.get(matchData.getTeam2()) + winningScore);
			} else {
				processedData.put(matchData.getTeam1(), processedData.get(matchData.getTeam1()) + drawScore);
				processedData.put(matchData.getTeam2(), processedData.get(matchData.getTeam2()) + drawScore);
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
