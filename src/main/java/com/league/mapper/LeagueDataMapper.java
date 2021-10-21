package com.league.mapper;

import com.league.api.v1.model.LeagueDataDto;
import com.league.domain.model.LeagueData;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, disableSubMappingMethodsGeneration = true, uses = MatchDataMapper.class)
@SuppressWarnings("squid:S1214")
public interface LeagueDataMapper {
	LeagueDataMapper INSTANCE = Mappers.getMapper(LeagueDataMapper.class);

	LeagueData mapToDomain(LeagueDataDto value);

}
