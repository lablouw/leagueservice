package com.league.mapper;

import com.league.api.model.LeagueDataDto;
import com.league.domain.model.LeagueData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, disableSubMappingMethodsGeneration = true, uses = MatchMapper.class)
@SuppressWarnings("squid:S1214")
public interface LeagueDataMapper {
	LeagueDataMapper INSTANCE = Mappers.getMapper(LeagueDataMapper.class);

	LeagueData mapToDomain(LeagueDataDto value);

}
