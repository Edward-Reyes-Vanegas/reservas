package com.Hotelreservations.Hotelreservations.controller;

import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.Reserva;
import com.Hotelreservations.Hotelreservations.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("api/v1")
public class ReservaController {
@Autowired
private ReservaService reservaService;


    @PostMapping("/cliente/{cedula}/habitacion/{numero}/fecha/{fechaReserva}/reservar")
    public ResponseEntity<Reserva> add(@PathVariable ("cedula") Long cedula,
                                          @PathVariable ("numero") Integer numero,
                                          @PathVariable ("fechaReserva") String fechaReserva) {
        Reserva confirmacion = reservaService.reservar(cedula, numero, fechaReserva);
        return ResponseEntity.ok(confirmacion);
    }

//localhost:8080/api/v1/reserva/cliente/123/habitacion/5/fecha/2023-03-28

}
