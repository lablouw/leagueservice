package com.league.mapper;

import com.league.api.model.MatchResultDto;
import com.league.domain.model.MatchResult;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, disableSubMappingMethodsGeneration = true)
public interface MatchResultMapper {
	MatchResultMapper INSTANCE = Mappers.getMapper(MatchResultMapper.class);

	MatchResultDto mapToV1(MatchResult value);
}
