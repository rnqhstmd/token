package org.token.dto.response.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.token.exception.CustomException;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {
    private final String errorCode;
    private final String message;
    private final String detail;

    public static ErrorResponseDto res(final CustomException customException) {
        String errorCode = customException.getErrorCode().getCode();
        String message = customException.getErrorCode().getMessage();
        String detail = customException.getDetail();
        return new ErrorResponseDto(errorCode, message, detail);
    }

    public static ErrorResponseDto res(final String errorCode, final Exception exception) {
        return new ErrorResponseDto(errorCode, exception.getMessage(), Arrays.toString(exception.getStackTrace()));
    }
}