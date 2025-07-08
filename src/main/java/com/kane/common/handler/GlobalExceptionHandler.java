package com.kane.common.handler;

import com.kane.common.exception.UserAlreadyExitsException;
import com.kane.common.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // có tác dụng xử lý các ngoại lệ toàn cục trong ứng dụng Spring Boot
public class GlobalExceptionHandler {
    // conflict là mã lỗi 409, thường được sử dụng khi có xung đột trong yêu cầu, ví dụ như khi người dùng đã tồn tại trong hệ thống
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(exception = {UserAlreadyExitsException.class})
    public ErrorResponse handle(final UserAlreadyExitsException e) {
        return ErrorResponse.of(e.getErrorCode(), e.getDomain(), e.getMessage());
    }
}
