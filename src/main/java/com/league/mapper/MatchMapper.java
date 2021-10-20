package com.league.mapper;

import com.league.api.model.MatchDto;
import com.league.domain.model.Match;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, disableSubMappingMethodsGeneration = true)
public interface MatchMapper {
	MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

	Match mapToDomain(MatchDto value);
}
