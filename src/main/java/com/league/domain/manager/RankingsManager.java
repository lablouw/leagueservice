package com.league.domain.manager;

import com.league.domain.model.LeagueData;
import com.league.domain.model.LeagueResult;

public interface RankingsManager {

	LeagueResult processLeagueData(LeagueData leagueData);

}
