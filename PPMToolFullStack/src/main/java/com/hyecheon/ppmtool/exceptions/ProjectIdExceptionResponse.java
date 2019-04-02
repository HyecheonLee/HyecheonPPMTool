package com.hyecheon.ppmtool.exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ProjectIdExceptionResponse {
    private final String projectIdentifier;

}
