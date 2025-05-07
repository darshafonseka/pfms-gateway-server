package com.pfms.gateway_server.service;

import com.pfms.gateway_server.request.AuthRequest;

public interface AuthService {
    String userLogin(AuthRequest authRequest);
}
