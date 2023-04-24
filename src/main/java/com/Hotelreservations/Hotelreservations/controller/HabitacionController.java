package com.Hotelreservations.Hotelreservations.controller;

import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class HabitacionController {

   private HabitacionService habitacionService;
    @Autowired
    public HabitacionController(HabitacionService habitacionService)
    {
        this.habitacionService = habitacionService;
    }

    @PostMapping("/habitacion")
    public Habitacion crear(@RequestBody Habitacion habitacion){
        return this.habitacionService.crearHabitacion(habitacion);
    }


}
