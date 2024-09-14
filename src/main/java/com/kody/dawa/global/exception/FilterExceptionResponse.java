package com.kody.dawa.global.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FilterExceptionResponse {
    Integer status;
    String message;
}