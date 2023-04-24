package com.Hotelreservations.Hotelreservations.service;

import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;
    }
    @Transactional
    public Cliente crear(Cliente cliente) {

        if(cliente.getCedula() == null  || cliente.getNombre() ==null || cliente.getApellido() ==null || cliente.getDireccion() ==null|| cliente.getEdad() <0|| cliente.getCorreoElectronico() ==null) {
            throw new RuntimeException("NO PUEDEN HABER CAMPOS VACIOS");
        }
       this.clienteRepository.save(cliente);
        return cliente;
    }
}
