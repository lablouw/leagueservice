package com.league;

import com.league.domain.manager.RankingsManager;
import com.league.domain.manager.RankingsManagerImpl;
import com.league.domain.model.LeagueData;
import com.league.domain.model.LeagueResult;
import com.league.domain.model.MatchData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class RankingsManagerTest {

	private RankingsManager rankingsManager = new RankingsManagerImpl();

	@Test
	public void test_RankingsManager_alphabeticalOrder() {
		LeagueData ld = constructLeagueData();

		LeagueResult lr = rankingsManager.processLeagueData(ld);
		assertEquals("a", lr.getTeamResults().get(0).getTeam());
		assertEquals("b", lr.getTeamResults().get(1).getTeam());

	}

	@Test
	public void test_RankingsManager_winnerFirst() {
		LeagueData ld = constructLeagueData();
		ld.getMatches().get(0).setScore1(1);

		LeagueResult lr = rankingsManager.processLeagueData(ld);
		assertEquals("b", lr.getTeamResults().get(0).getTeam());
		assertEquals("a", lr.getTeamResults().get(1).getTeam());

		ld = constructLeagueData();
		ld.getMatches().get(0).setScore2(1);
		lr = rankingsManager.processLeagueData(ld);
		assertEquals("a", lr.getTeamResults().get(0).getTeam());
		assertEquals("b", lr.getTeamResults().get(1).getTeam());

	}

	private LeagueData constructLeagueData() {
		LeagueData ld = new LeagueData();
		ld.getMatches().add(new MatchData("b", "a", 0, 0));
		return ld;
	}

}
