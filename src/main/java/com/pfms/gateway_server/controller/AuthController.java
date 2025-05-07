package com.pfms.gateway_server.controller;

import com.pfms.gateway_server.exception.GlobalExceptionResponse;
import com.pfms.gateway_server.model.ApiResponse;
import com.pfms.gateway_server.request.AuthRequest;
import com.pfms.gateway_server.service.AuthService;
import com.pfms.gateway_server.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController( AuthService authService) {

        this.authService = authService;
    }

    @Operation(summary = "Login User")
    @ApiResponses(
            {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Request processed successfully"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "401",
                            description = "Invalid Credentials",
                            content = @Content(
                                    schema = @Schema(implementation = GlobalExceptionResponse.class)
                            )
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody AuthRequest authRequest) {
        return ResponseUtil.ok(authService.userLogin(authRequest));
    }
}
