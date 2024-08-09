package org.token.exception;

import org.token.exception.errorcode.ErrorCode;

public class UnauthorizedException extends CustomException {
    public UnauthorizedException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
    public UnauthorizedException(ErrorCode errorCode) {super(errorCode);}
}
