package com.darchTech.co.exception;

import lombok.Data;

@Data
public class AppException extends RuntimeException{
    private int statusCode;
    public AppException(String msg, int statusCode){
        super(msg);
        this.statusCode = statusCode;
    }
}
