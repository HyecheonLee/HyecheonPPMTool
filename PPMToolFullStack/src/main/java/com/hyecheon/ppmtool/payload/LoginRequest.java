package com.hyecheon.ppmtool.payload;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;

    public LoginRequest(@NotBlank(message = "Username cannot be blank") String username) {
        this.username = username;
    }
}

