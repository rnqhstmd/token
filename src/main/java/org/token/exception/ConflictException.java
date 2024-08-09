package org.token.exception;

import org.token.exception.errorcode.ErrorCode;

public class ConflictException extends CustomException {
    public ConflictException(ErrorCode errorCode){
        super(errorCode);
    }
}