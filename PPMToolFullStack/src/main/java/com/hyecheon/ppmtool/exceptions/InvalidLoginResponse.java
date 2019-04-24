package com.hyecheon.ppmtool.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class InvalidLoginResponse {
    private String username = "Invalid Username";
    private String password = "Invalid Password";
}
