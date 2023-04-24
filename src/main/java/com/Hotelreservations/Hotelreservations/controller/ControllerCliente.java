package com.Hotelreservations.Hotelreservations.controller;


import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.service.ServiceCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ControllerCliente {

    private ServiceCliente serviceCliente;

    @Autowired
    public ControllerCliente(ServiceCliente serviceCliente) {
        this.serviceCliente = serviceCliente;
    }

    @PostMapping("/cliente")
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente){
        Cliente cliente1 = serviceCliente.crear(cliente);
        return ResponseEntity.ok(cliente1);
    }
}
