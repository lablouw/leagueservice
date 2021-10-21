package com.league.api.v1.model;

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
public class MatchDataDto {
	@NotBlank(message = "Team name must be populated")
	private String team1;
	@NotBlank(message = "Team name must be populated")
	private String team2;
	@PositiveOrZero(message = "Score must be >= 0")
	private int score1;
	@PositiveOrZero(message = "Score must be >= 0")
	private int score2;
}
