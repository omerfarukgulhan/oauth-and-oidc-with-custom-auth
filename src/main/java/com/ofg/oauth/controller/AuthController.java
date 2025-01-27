package com.ofg.oauth.controller;

import com.ofg.oauth.core.util.response.ResponseUtil;
import com.ofg.oauth.core.util.results.ApiDataResponse;
import com.ofg.oauth.core.util.results.ApiResponse;
import com.ofg.oauth.model.request.SignInCredentials;
import com.ofg.oauth.model.request.UserCreateRequest;
import com.ofg.oauth.model.response.AuthResponse;
import com.ofg.oauth.model.response.UserResponse;
import com.ofg.oauth.security.CurrentUser;
import com.ofg.oauth.service.abstracts.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthService authService;
    private final String tokenType;

    private static final String LOGIN_SUCCESS = "app.msg.login.success";
    private static final String REGISTER_SUCCESS = "app.msg.register.success";
    private static final String LOGOUT_SUCCESS = "app.msg.logout.success";
    private static final String ENDPOINT_DISABLED = "app.msg.endpoint.disabled";

    @Autowired
    public AuthController(AuthService authService,
                          @Value("${app.token-type}") String tokenType) {
        this.authService = authService;
        this.tokenType = tokenType;
    }

    @GetMapping("/test")
    public String test() {
        return "Ok";
    }

    @GetMapping("/login/oauth")
    public ResponseEntity<HttpStatus> login(){
        return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, "http://localhost:8080/login/oauth2/code/github").build();
    }

    @PostMapping("/login")
    public ResponseEntity<ApiDataResponse<AuthResponse>> login(
            @Valid @RequestBody SignInCredentials signInCredentials,
            @RequestParam("method") String method) {
        if ("oauth".equals(method)) {

        }
        AuthResponse authResponse = authService.login(signInCredentials);
        return ResponseUtil.createApiDataResponse(authResponse, LOGIN_SUCCESS, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiDataResponse<UserResponse>> register(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        UserResponse userResponse = authService.register(userCreateRequest);
        return ResponseUtil.createApiDataResponse(userResponse, REGISTER_SUCCESS, HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout(@AuthenticationPrincipal CurrentUser currentUser) {
        if ("opaque".equals(tokenType)) {
            authService.logout(currentUser.getId());
            return ResponseUtil.createApiResponse(LOGOUT_SUCCESS, HttpStatus.NO_CONTENT);
        }
        return ResponseUtil.createApiResponse(false, ENDPOINT_DISABLED, HttpStatus.FORBIDDEN);
    }
}
