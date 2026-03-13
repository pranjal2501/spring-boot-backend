package com.darchTech.co.exception;

public class InvalidCredentialsException extends AppException{
    public InvalidCredentialsException(String msg){
        super(msg,401);
    }
}
