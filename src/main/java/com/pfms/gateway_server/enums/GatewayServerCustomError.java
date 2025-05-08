package com.pfms.gateway_server.enums;

import lombok.Getter;

@Getter
public enum GatewayServerCustomError {
    USER_NOT_FOUND("001", "User not found", Severity.HIGH),
    CUSTOM_METADATA_ERROR("002","Custom Error",Severity.HIGH);


    private final String errorCode;
    private final String errorMessage;
    private final Severity severity;

    GatewayServerCustomError(String errorCode, String errorMessage, Severity severity) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.severity = severity;
    }
}
