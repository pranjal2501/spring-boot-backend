package com.darchTech.co.exception;

public class JwtTokenException extends AppException {
    public JwtTokenException(String msg) {
        super(msg, 401);
    }
}