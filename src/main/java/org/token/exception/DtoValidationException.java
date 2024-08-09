package org.token.exception;

import org.token.exception.errorcode.ErrorCode;

public class DtoValidationException extends CustomException {
    public DtoValidationException(ErrorCode errorCode, String detail) {super(errorCode, detail);}
}
