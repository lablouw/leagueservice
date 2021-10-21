package com.league;

import com.league.domain.manager.RankingsManager;
import com.league.domain.manager.RankingsManagerImpl;
import com.league.domain.model.LeagueData;
import com.league.domain.model.LeagueResult;
import lombok.Cleanup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandLineApplication {
	public static void run(File f) {
		try {
			String leagueData = readText(f);
			LeagueData leagueDataModel = LeagueData.fromString(leagueData);
			RankingsManager rankingsManager = new RankingsManagerImpl();
			LeagueResult leagueResult = rankingsManager.processLeagueData(leagueDataModel);
			System.out.println(leagueResult.toFriendlyString());
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	private static String readText(File f) throws IOException {
		@Cleanup BufferedReader reader = new BufferedReader(new FileReader(f));
		List<String> lines = new ArrayList<>();
		String line;
		while ((line = reader.readLine()) != null) {
			lines.add(line);
		}
		return String.join("\n", lines);
	}
}
