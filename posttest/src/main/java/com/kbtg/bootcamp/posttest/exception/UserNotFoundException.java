package com.kbtg.bootcamp.posttest.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String name){
        super(name);
    }
}
