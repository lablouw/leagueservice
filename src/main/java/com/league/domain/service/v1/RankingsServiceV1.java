package com.league.domain.service.v1;

import com.league.api.model.LeagueDataDto;
import com.league.aspect.UncaughtExceptionHandler;
import com.league.domain.manager.RankingsManager;
import com.league.domain.model.LeagueData;
import com.league.domain.model.LeagueResult;
import com.league.mapper.LeagueDataMapper;
import com.league.mapper.LeagueResultsMapper;
import com.league.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Service layer should contain only input validation and calls to manager classes.
// No non-domain classes are to be passed into the manager layer, mapping between api and domain classes to take place here.

@RestController
@RequestMapping("/rankingService/v1")
@Slf4j
@UncaughtExceptionHandler
public class RankingsServiceV1 {

	@Autowired
	private RankingsManager rankingsManager;

	@PostMapping(value = "/processRawLeagueData", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity processRawLeagueData(@RequestBody String leagueDataText) {

		LeagueData leagueData = new LeagueData(leagueDataText);
		LeagueResult leagueResult = rankingsManager.processLeagueData(leagueData);

		return ResponseEntity.ok().body(leagueResult.toFriendlyString());
	}

	@PostMapping(value = "/processLeagueData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity processLeagueData(@RequestBody LeagueDataDto leagueDataDto) {
		ResponseEntity<List<String>> re = ValidationUtils.validate(leagueDataDto, leagueDataDto.getMatches());
		if (re != null) {
			return re;
		}

		LeagueData leagueData = LeagueDataMapper.INSTANCE.mapToDomain(leagueDataDto);
		LeagueResult leagueResult = rankingsManager.processLeagueData(leagueData);

		return ResponseEntity.ok().body(LeagueResultsMapper.INSTANCE.mapToV1(leagueResult));
	}

	@GetMapping(value = "/testException")
	public ResponseEntity testException() throws Exception {
		throw new Exception("Exception from rest method testException");
	}

}

