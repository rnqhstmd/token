package org.token.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.token.exception.*;
import org.token.dto.response.common.ErrorResponseDto;
import org.token.exception.errorcode.ErrorCode;

import static org.token.dto.response.common.ErrorResponseDto.res;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    //ConflictException 핸들러
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponseDto> handleConflictException(ConflictException conflictException) {
        this.writeLog(conflictException);
        return new ResponseEntity<>(ErrorResponseDto.res(conflictException), HttpStatus.CONFLICT);
    }

    //NotFoundException 핸들러
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundException notFoundException) {
        this.writeLog(notFoundException);
        return new ResponseEntity<>(res(notFoundException), HttpStatus.NOT_FOUND);
    }

    //UnauthorizedException  핸들러
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponseDto> handleUnauthorizedException(UnauthorizedException unauthorizedException) {
        this.writeLog(unauthorizedException);
        return new ResponseEntity<>(res(unauthorizedException), HttpStatus.UNAUTHORIZED);
    }

    //DtoValidationException 핸들러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        FieldError fieldError = methodArgumentNotValidException.getBindingResult().getFieldError();
        if (fieldError == null) {
            return new ResponseEntity<>(res(String.valueOf(HttpStatus.BAD_REQUEST.value())
                    , methodArgumentNotValidException), HttpStatus.BAD_REQUEST);
        }
        ErrorCode errorCode = ErrorCode.resolveValidationErrorCode(fieldError.getCode());
        String detail = fieldError.getDefaultMessage();
        DtoValidationException dtoValidationException = new DtoValidationException(errorCode, detail);

        this.writeLog(dtoValidationException);

        return new ResponseEntity<>(res(dtoValidationException), HttpStatus.BAD_REQUEST);
    }

    // HttpMediaTypeNotSupportedException 핸들러
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException) {
        this.writeLog(httpMediaTypeNotSupportedException);
        return new ResponseEntity<>(
                res(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()), httpMediaTypeNotSupportedException),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    // MultipartException 핸들러
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<ErrorResponseDto> handleMultipartException(MultipartException multipartException) {
        this.writeLog(multipartException);
        return new ResponseEntity<>(
                res(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), multipartException),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 일반 예외 핸들러
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception exception) {
        this.writeLog(exception);
        return new ResponseEntity<>(
                res(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), exception),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void writeLog(CustomException customException) {
        String exceptionName = customException.getClass().getSimpleName();
        ErrorCode errorCode = customException.getErrorCode();
        String detail = customException.getDetail();

        log.error("({}){}: {}", exceptionName, errorCode.getMessage(), detail);
    }

    private void writeLog(Exception exception) {
        log.error("({}){}", exception.getClass().getSimpleName(), exception.getMessage());
    }
}
