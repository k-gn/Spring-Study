package com.example.hello.validation.validator;

import com.example.hello.validation.annotation.YearMonth;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// 어노테이션에 대한 검증 로직이 있는 Validator
// ConstraintValidator<어노테이션, 들어가는 값>
public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {

    private String pattern;

    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            // LocalDate은 기본적으로 dd까지 들어가야 한다.
            LocalDate localDate = LocalDate.parse(value + "01", DateTimeFormatter.ofPattern(this.pattern));
        }catch (Exception e) {
            return false;
        }
        return true;
    }
}
