package com.league.mapper;

import com.league.api.v1.model.MatchDataDto;
import com.league.domain.model.MatchData;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, disableSubMappingMethodsGeneration = true)
public interface MatchDataMapper {
	MatchDataMapper INSTANCE = Mappers.getMapper(MatchDataMapper.class);

	MatchData mapToDomain(MatchDataDto value);
}
