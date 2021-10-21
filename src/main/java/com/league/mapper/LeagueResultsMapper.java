package com.league.mapper;

import com.league.api.v1.model.LeagueResultDto;
import com.league.domain.model.LeagueResult;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, disableSubMappingMethodsGeneration = true, uses = MatchResultMapper.class)
@SuppressWarnings("squid:S1214")
public interface LeagueResultsMapper {
	LeagueResultsMapper INSTANCE = Mappers.getMapper(LeagueResultsMapper.class);

	LeagueResultDto mapToV1(LeagueResult value);

}
