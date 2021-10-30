package com.league.api.v1.model;

import com.league.util.validation.annotation.UniqueTeamNames;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ApiModel
@UniqueTeamNames
public class MatchDataDto {
	@NotBlank(message = "team1 must be populated")
	private String team1;
	@NotBlank(message = "team2 must be populated")
	private String team2;
	@PositiveOrZero(message = "score1 must be >= 0")
	private int score1;
	@PositiveOrZero(message = "score2 must be >= 0")
	private int score2;
}
