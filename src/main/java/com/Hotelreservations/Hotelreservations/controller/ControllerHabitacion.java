package com.Hotelreservations.Hotelreservations.controller;

import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.service.ServiceHabitacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ControllerHabitacion {
    private ServiceHabitacion serviceHabitacion;

    @Autowired
    public ControllerHabitacion(ServiceHabitacion serviceHabitacion) {
        this.serviceHabitacion = serviceHabitacion;
    }

    @PostMapping("/habitacion")
    public ResponseEntity<Habitacion> crear(@RequestBody Habitacion habitacion){
        Habitacion habitacion1 = serviceHabitacion.crear(habitacion);
        return ResponseEntity.ok(habitacion1);
    }
    }

