package com.hyecheon.ppmtool.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JWTLoginSuccessResponse {
    private boolean success;
    private String token;
}
