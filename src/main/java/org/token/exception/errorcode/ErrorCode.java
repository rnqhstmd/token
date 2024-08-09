package org.token.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //UnauthorizedException
    INVALID_EMAIL_OR_PASSWORD("4010", "등록되지 않은 이메일 또는 비밀번호를 잘못 입력했습니다."),
    COOKIE_NOT_FOUND("4011", "쿠키를 찾을 수 없습니다."),
    INVALID_TOKEN("4012", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN("4013", "만료된 토큰입니다."),

    //NotFoundException
    USER_NOT_FOUND("4040","존재하지 않는 사용자입니다."),

    //ConflictException
    DUPLICATED_NAME("4090","이미 사용 중인 이름입니다."),
    DUPLICATED_EMAIL("4091", "이미 사용 중인 이메일입니다."),

    //ValidationException
    NOT_NULL("9001", "필수값이 누락되었습니다."),
    NOT_BLANK("9002", "필수값이 빈 값이거나 공백으로 되어있습니다."),
    REGEX("9003", "형식에 맞지 않습니다."),
    LENGTH("9004", "길이가 유효하지 않습니다.");

    private final String code;
    private final String message;

    public static ErrorCode resolveValidationErrorCode(String code) {
        return switch (code) {
            case "NotNull" -> NOT_NULL;
            case "NotBlank" -> NOT_BLANK;
            case "Pattern" -> REGEX;
            case "Length" -> LENGTH;
            default -> throw new IllegalArgumentException("Unexpected value: " + code);
        };
    }
}
