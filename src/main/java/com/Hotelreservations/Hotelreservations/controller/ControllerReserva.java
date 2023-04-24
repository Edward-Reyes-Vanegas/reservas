package com.Hotelreservations.Hotelreservations.controller;

import com.Hotelreservations.Hotelreservations.dto.ReservaDto;
import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.Reserva;
import com.Hotelreservations.Hotelreservations.service.ServiceReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1")
public class ControllerReserva {
    private ServiceReserva reservaService;

    @Autowired
    public ControllerReserva(ServiceReserva reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping("/cliente/{cedula}/habitacion/{numero}/fecha/{fechaReserva}/reservar")
    public ResponseEntity<ReservaDto> add(@PathVariable ("cedula") Long cedula,
                                          @PathVariable ("numero") Integer numero,
                                          @PathVariable ("fechaReserva") String fechaReserva){
        ReservaDto confirmacion = reservaService.reservar(cedula, numero, fechaReserva);
        return ResponseEntity.ok(confirmacion);
    }


    @GetMapping("/cliente/{cedula}")
    public List<Reserva> getByClient(@PathVariable Long cedula){
        return this.reservaService.getByClient(cedula);
    }

    @GetMapping("/disponible-fecha")
    public Set<Habitacion> getByDate(@RequestParam String fecha){
        return this.reservaService.getByDate(fecha);
    }
    @GetMapping("/disponible-fecha-tipo")
    public List<Habitacion> getByDateType(@RequestParam String fecha, @RequestParam String tipo){
        return this.reservaService.getByDateType(fecha,tipo);
    }
}
