package com.Hotelreservations.Hotelreservations.controller;

import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ClienteController {

    private ClienteService clienteService;
    @Autowired
    public ClienteController(ClienteService clienteService) {

        this.clienteService = clienteService;
    }

@PostMapping("/cliente")
    public Cliente crear(@RequestBody Cliente cliente){

        return this.clienteService.crear(cliente);
    }

}
