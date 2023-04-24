package com.Hotelreservations.Hotelreservations.service;


import com.Hotelreservations.Hotelreservations.exceptions.InvalidDataException;
import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.repository.RepositoryHabitacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceHabitacion {
    private RepositoryHabitacion repositoryHabitacion;

    @Autowired
    public ServiceHabitacion(RepositoryHabitacion repositoryHabitacion) {
        this.repositoryHabitacion = repositoryHabitacion;
    }

    public Habitacion crear(Habitacion habitacion){
        if(habitacion.getNumero()==null||habitacion.getTipoHabitacion()==null||habitacion.getPrecioBase()==null){
            throw new InvalidDataException(("Los datos de la habitación no pueden ser nulos"));
        }
        this.repositoryHabitacion.save(habitacion);
        return habitacion;
    }
}
