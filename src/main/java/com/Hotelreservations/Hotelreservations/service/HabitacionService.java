package com.Hotelreservations.Hotelreservations.service;

import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HabitacionService {

    private HabitacionRepository habitacionRepository;
    @Autowired
    public HabitacionService(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }
    @Transactional
    public Habitacion crearHabitacion(Habitacion habitacion) {
        if(habitacion.getNumero() == null  || habitacion.getTipo() ==null || habitacion.getPrecioBase() ==null) {
            throw new RuntimeException("NO PUEDEN HABER CAMPOS VACIOS");
        }
        this.habitacionRepository.save(habitacion);
        return habitacion;
    }
}
