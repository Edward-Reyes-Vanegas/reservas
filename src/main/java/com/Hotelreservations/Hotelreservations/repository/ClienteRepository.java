package com.Hotelreservations.Hotelreservations.repository;

import com.Hotelreservations.Hotelreservations.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    }


