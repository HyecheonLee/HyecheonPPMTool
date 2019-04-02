package com.hyecheon.ppmtool.services;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MapValidationErrorService {
    public Optional<ResponseEntity<?>> mapValidationService(@NotNull BindingResult result) {
        if (result.hasErrors()) {
            final Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                if (!StringUtils.isEmpty(fieldError.getDefaultMessage())) {
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
            }
            return Optional.of(new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST));
        } else {
            return Optional.empty();
        }
    }
}
