package com.indonesia.bookstore.handler;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@NoArgsConstructor
public class CustomException extends RuntimeException{

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(String message) {
        super(message);
    }
}
