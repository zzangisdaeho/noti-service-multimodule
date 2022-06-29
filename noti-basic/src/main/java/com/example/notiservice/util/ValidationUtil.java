package com.example.notiservice.util;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Collection;
import java.util.Set;

@Slf4j
public class ValidationUtil {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> void checkValidation(T dto) {
        if(dto instanceof Collection){
            ((Collection)dto).forEach(ValidationUtil::validate);
        }else{
            validate(dto);
        }
    }

    private static <T> void validate(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : violations) {
                sb.append(constraintViolation.getPropertyPath()).append(" : ").append(constraintViolation.getMessage()).append(". ");
            }
            throw new ConstraintViolationException("Error occurred: " + sb, violations);
        }

        log.info("DTO validation pass : {}", dto);
    }

}
