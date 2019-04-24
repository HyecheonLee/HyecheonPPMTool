package com.hyecheon.ppmtool.web;

import com.hyecheon.ppmtool.domain.User;
import com.hyecheon.ppmtool.payload.JWTLoginSuccessResponse;
import com.hyecheon.ppmtool.payload.LoginRequest;
import com.hyecheon.ppmtool.security.JwtTokenProvider;
import com.hyecheon.ppmtool.services.MapValidationErrorService;
import com.hyecheon.ppmtool.services.UserService;
import com.hyecheon.ppmtool.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

import static com.hyecheon.ppmtool.security.securityConstants.TOKEN_PREFIX;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {
    private final MapValidationErrorService mapValidationErrorService;
    private final UserService userService;
    private final UserValidator userValidator;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
        final Optional<ResponseEntity<?>> optionalErrorMap = mapValidationErrorService.mapValidationService(result);
        return optionalErrorMap.orElseGet(() -> {
            final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
        });
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
        // validate passwords match
        userValidator.validate(user, result);
        final Optional<ResponseEntity<?>> optionalResponseEntity = mapValidationErrorService.mapValidationService(result);
        return optionalResponseEntity.orElseGet(() -> {
            final User newUser = userService.saveUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        });
    }
}
