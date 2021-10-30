package com.league.util.validation.annotation;

import com.league.util.validation.UniqueTeamNamesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueTeamNamesValidator.class})
@Documented
public @interface UniqueTeamNames {
	String message() default "Team names must be unique";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
