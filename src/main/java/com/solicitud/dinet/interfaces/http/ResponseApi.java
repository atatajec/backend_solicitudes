package com.solicitud.dinet.interfaces.http;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Standard API response wrapper")
public class ResponseApi<T> {

    @Schema(description = "Indicates whether the operation was successful", example = "true")
    private boolean success;

    @Schema(description = "Descriptive message about the result", example = "Solicitud created successfully")
    private String message;

    @Schema(description = "Payload of the response")
    private T data;
}
