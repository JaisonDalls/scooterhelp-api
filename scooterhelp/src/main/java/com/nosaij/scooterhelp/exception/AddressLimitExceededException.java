package com.nosaij.scooterhelp.exception;

public class AddressLimitExceededException extends RuntimeException{
    public AddressLimitExceededException(String message){
        super(message);
    }
}
