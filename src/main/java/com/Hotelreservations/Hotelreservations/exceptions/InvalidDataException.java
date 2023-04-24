package com.Hotelreservations.Hotelreservations.exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException() {
    }

    public InvalidDataException(String messageError){
        super(messageError);
    }

}
