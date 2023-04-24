package com.Hotelreservations.Hotelreservations.service;


import com.Hotelreservations.Hotelreservations.exceptions.InvalidDataException;
import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.repository.RepositoryCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServiceCliente {

    private RepositoryCliente repositoryCliente;

    @Autowired
    public ServiceCliente(RepositoryCliente repositoryCliente) {
        this.repositoryCliente = repositoryCliente;
    }

    public Cliente crear(Cliente cliente){
        if(cliente.getApellido()==null||cliente.getNombre()==null){
            throw new InvalidDataException(("Nombre y apellido no pueden ser nulos"));
        }
        this.repositoryCliente.save(cliente);
        return cliente;
    }
}
