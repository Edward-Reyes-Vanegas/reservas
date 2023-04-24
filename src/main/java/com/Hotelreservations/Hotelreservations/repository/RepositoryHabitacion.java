package com.Hotelreservations.Hotelreservations.repository;


import com.Hotelreservations.Hotelreservations.model.Habitacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryHabitacion extends CrudRepository<Habitacion, Integer> {
}
