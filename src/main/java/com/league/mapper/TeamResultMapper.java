package com.league.mapper;

import com.league.api.v1.model.TeamResultDto;
import com.league.domain.model.TeamResult;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, disableSubMappingMethodsGeneration = true)
public interface TeamResultMapper {
	TeamResultMapper INSTANCE = Mappers.getMapper(TeamResultMapper.class);

	TeamResultDto mapToV1(TeamResult value);
}
