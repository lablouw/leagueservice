package com.league.mapper;

import com.league.api.v1.model.TeamResultDto;
import com.league.domain.model.TeamResult;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, disableSubMappingMethodsGeneration = true)
public interface MatchResultMapper {
	MatchResultMapper INSTANCE = Mappers.getMapper(MatchResultMapper.class);

	TeamResultDto mapToV1(TeamResult value);
}
