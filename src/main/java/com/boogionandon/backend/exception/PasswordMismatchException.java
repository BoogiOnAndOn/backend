package com.boogionandon.backend.exception;

public class PasswordMismatchException extends CustomException {
    
    public PasswordMismatchException() {
        super("비밀번호가 일치하지 않습니다.");
    }
}
