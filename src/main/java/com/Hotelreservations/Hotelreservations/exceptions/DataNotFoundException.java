package com.Hotelreservations.Hotelreservations.exceptions;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(){}
    public DataNotFoundException(String messageError){
        super(messageError);
    }
}
