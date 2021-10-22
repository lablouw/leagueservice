package com.league.api.v1.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ApiModel
public class LeagueDataDto {

	@NotEmpty(message = "At least one match must be supplied")
	List<MatchDataDto> matches;

}
