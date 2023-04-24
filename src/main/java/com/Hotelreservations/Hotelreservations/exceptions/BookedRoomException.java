package com.Hotelreservations.Hotelreservations.exceptions;

public class BookedRoomException extends RuntimeException{
    public BookedRoomException(){}
    public BookedRoomException(String messageError){
        super(messageError);
    }
}
