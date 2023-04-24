package com.Hotelreservations.Hotelreservations.exceptions;

public class InvalidDateException extends RuntimeException{
    public InvalidDateException() {
    }

    public InvalidDateException(String messageError){
        super(messageError);
    }
}
