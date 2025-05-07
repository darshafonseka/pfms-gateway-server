package com.pfms.gateway_server.service.impl;

import com.pfms.gateway_server.enums.GatewayServerCustomError;
import com.pfms.gateway_server.exception.ApplicationException;
import com.pfms.gateway_server.model.UserDto;
import com.pfms.gateway_server.request.AuthRequest;
import com.pfms.gateway_server.service.AuthService;
import com.pfms.gateway_server.util.ErrorMessageExtractor;
import com.pfms.gateway_server.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthServiceImpl implements AuthService {

    private final RestTemplate restTemplate;

    private final JwtUtil jwtUtil;

    public AuthServiceImpl(RestTemplate restTemplate, JwtUtil jwtUtil) {
        this.restTemplate = restTemplate;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String userLogin(AuthRequest authRequest) {
        try {
            UserDto user = restTemplate.postForObject("http://localhost:8081/api/user/login-validate", authRequest, UserDto.class);
            if( user == null){
                throw new ApplicationException(GatewayServerCustomError.USER_NOT_FOUND);
            }
            return jwtUtil.generateToken(authRequest.getEmail(), String.valueOf(user.getRole()),user.getUserId());

        } catch (HttpStatusCodeException e) {
            String errorMessage = ErrorMessageExtractor.extractErrorMessage(e.getResponseBodyAsString());

            throw new ApplicationException(GatewayServerCustomError.CUSTOM_METADATA_ERROR,errorMessage);

        }
    }
}
