package org.token.exception;

import org.token.exception.errorcode.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode errorCode) {super(errorCode);}
}
