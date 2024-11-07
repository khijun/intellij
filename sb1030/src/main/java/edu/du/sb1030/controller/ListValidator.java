package edu.du.sb1030.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ListValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ListCommand.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
