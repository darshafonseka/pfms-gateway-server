package com.pfms.gateway_server.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pfms.gateway_server.enums.GatewayServerCustomError;
import com.pfms.gateway_server.enums.Severity;
import lombok.Getter;
import lombok.ToString;

@Getter
@JsonIgnoreProperties({"cause" , "message" , "stackTrace" , "suppressed" , "localizedMessage" , "severity"})
@ToString
public class ApplicationException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;
    private final Severity severity;

    public ApplicationException(GatewayServerCustomError gatewayServerCustomError) {
        this.errorCode = gatewayServerCustomError.getErrorCode();
        this.errorMessage = gatewayServerCustomError.getErrorMessage();
        this.severity = gatewayServerCustomError.getSeverity();
    }

    public ApplicationException(GatewayServerCustomError gatewayServerCustomError, String customMessage) {
        this.errorCode = gatewayServerCustomError.getErrorCode();
        this.errorMessage = customMessage != null ? customMessage : gatewayServerCustomError.getErrorMessage();
        this.severity = gatewayServerCustomError.getSeverity();
    }
}
