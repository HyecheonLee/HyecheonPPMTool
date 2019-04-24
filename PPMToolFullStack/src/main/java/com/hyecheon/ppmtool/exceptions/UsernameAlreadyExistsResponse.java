package com.hyecheon.ppmtool.exceptions;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsernameAlreadyExistsResponse {
    private final String username;
}
