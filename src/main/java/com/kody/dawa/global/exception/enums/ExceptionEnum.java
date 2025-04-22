package com.kody.dawa.global.exception.enums;

import com.kody.dawa.global.exception.HttpException;
import org.springframework.http.HttpStatus;

public enum ExceptionEnum {

    AUTH_EMPTY_TOKEN("AUTH001", "토큰이 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    AUTH_EXPIRED_TOKEN("AUTH002", "토큰이 만료되었습니다.", HttpStatus.UNAUTHORIZED),
    AUTH_UNSUPPORTED_TOKEN("AUTH003", "지원하지 않는 토큰입니다.", HttpStatus.BAD_REQUEST),
    AUTH_MALFORMED_TOKEN("AUTH004", "잘못된 형식의 토큰입니다.", HttpStatus.BAD_REQUEST),
    AUTH_OTHER_TOKEN("AUTH005", "기타 JWT 토큰 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
    ExceptionEnum(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public HttpException toHttpException() {
        return new HttpException(this.httpStatus, this.message);
    }
}
