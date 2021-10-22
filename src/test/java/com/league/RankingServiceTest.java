package com.league;

import com.google.gson.Gson;
import com.league.domain.model.LeagueData;
import com.league.domain.model.MatchData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RankingServiceTest {

	@Autowired
	private MockMvc mvc;

	private final Gson gson = new Gson();

	@Test
	public void test_processRawLeagueData() throws Exception {
		String content = "Lions 3, Snakes 3";
		String uri = "/v1/rankingService/processRawLeagueData";

		mvc.perform(MockMvcRequestBuilders
						.post(uri)
						.contentType(MediaType.TEXT_PLAIN_VALUE)
						.content(content)
						.accept(MediaType.TEXT_PLAIN_VALUE))
						.andExpect(status().isOk())
				.andExpect(content().string("1. Lions, 1 pt\n" + "2. Snakes, 1 pt"));

	}

	@Test
	public void test_processLeagueData() throws Exception {
		String uri = "/v1/rankingService/processLeagueData";

		//correct format
		mvc.perform(MockMvcRequestBuilders
						.post(uri)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(gson.toJson(constructLeagueData()))
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());

		LeagueData ld;
		//invalid fields
		ld = new LeagueData();
		mvc.perform(MockMvcRequestBuilders
						.post(uri)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(gson.toJson(ld))
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest());

		ld = constructLeagueData();
		ld.getMatches().get(0).setScore1(-1);
		mvc.perform(MockMvcRequestBuilders
						.post(uri)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(gson.toJson(ld))
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest());

		ld = constructLeagueData();
		ld.getMatches().get(0).setScore2(-1);
		mvc.perform(MockMvcRequestBuilders
						.post(uri)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(gson.toJson(ld))
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest());

		ld = constructLeagueData();
		ld.getMatches().get(0).setTeam1("");
		mvc.perform(MockMvcRequestBuilders
						.post(uri)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(gson.toJson(ld))
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest());

		ld = constructLeagueData();
		ld.getMatches().get(0).setTeam2("");
		mvc.perform(MockMvcRequestBuilders
						.post(uri)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(gson.toJson(ld))
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest());


	}

	private LeagueData constructLeagueData() {
		LeagueData ld = new LeagueData();
		ld.getMatches().add(new MatchData("a", "b", 0, 0));
		return ld;
	}

}
