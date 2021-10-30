package com.league.util.validation;

import com.league.api.v1.model.MatchDataDto;
import com.league.util.validation.annotation.UniqueTeamNames;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueTeamNamesValidator implements ConstraintValidator<UniqueTeamNames, MatchDataDto> {

	@Override
	public void initialize(UniqueTeamNames constraintAnnotation) {
	}

	@Override
	public boolean isValid(MatchDataDto matchDataDto, ConstraintValidatorContext constraintValidatorContext) {
		if (matchDataDto.getTeam1() != null) {
			return !matchDataDto.getTeam1().equals(matchDataDto.getTeam2());
		} else if (matchDataDto.getTeam2() != null) {
			return !matchDataDto.getTeam2().equals(matchDataDto.getTeam1());
		}
		return false;
	}
}
