package com.league.config;

import com.league.domain.manager.RankingsManager;
import com.league.aspect.RestBoundaryLoggerAspect;
import com.league.domain.manager.RankingsManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestServiceConfig {

	@Bean
	public RestBoundaryLoggerAspect restBoundaryLoggerAspect() {
		return new RestBoundaryLoggerAspect();
	}

	@Bean
	public RankingsManager rankingsManager() {
		return new RankingsManagerImpl();
	}

}
