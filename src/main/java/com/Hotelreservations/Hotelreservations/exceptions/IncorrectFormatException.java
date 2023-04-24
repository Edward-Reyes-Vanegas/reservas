package com.Hotelreservations.Hotelreservations.exceptions;

public class IncorrectFormatException extends RuntimeException{

    public IncorrectFormatException(){}
    public IncorrectFormatException(String messageError){
        super(messageError);
    }

}
