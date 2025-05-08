package com.pfms.gateway_server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfms.gateway_server.enums.GatewayServerCustomError;
import com.pfms.gateway_server.exception.ApplicationException;

import java.util.stream.Stream;

public class ErrorMessageExtractor {
    private ErrorMessageExtractor() {}

    public static String extractErrorMessage(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(responseBody);

            return Stream.of(
                            // 1. Check dynamic exception types under "data" (applicationException, validationException, etc.)
                            Stream.of("applicationException")
                                    .map(exceptionType -> root.path("data").path(exceptionType).path("errorMessage")),

                            // 2. Check new "data.error.errorMessage" structure
                            Stream.of(root.path("data").path("error").path("errorMessage")),

                            // 3. Fallback to generic error locations
                            Stream.of(
                                    root.path("error").path("message"),
                                    root.path("message"),
                                    root.path("exception").path("detail")
                            )
                    )
                    .flatMap(stream -> stream)
                    .filter(node -> !node.isMissingNode())
                    .map(JsonNode::asText)
                    .filter(text -> !text.isEmpty())
                    .findFirst()
                    .orElse("Unknown error");

        } catch (JsonProcessingException ex) {
            throw new ApplicationException(GatewayServerCustomError.CUSTOM_METADATA_ERROR,"Invalid JSON response: " + ex.getOriginalMessage());
        }
        catch (Exception ex) {
            throw new ApplicationException(GatewayServerCustomError.CUSTOM_METADATA_ERROR, ex.getMessage());
        }
    }
}
