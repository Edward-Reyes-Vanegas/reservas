package com.Hotelreservations.Hotelreservations.service;

import com.Hotelreservations.Hotelreservations.dto.ReservaDto;
import com.Hotelreservations.Hotelreservations.exceptions.*;
import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.Reserva;
import com.Hotelreservations.Hotelreservations.repository.RepositoryCliente;
import com.Hotelreservations.Hotelreservations.repository.RepositoryHabitacion;
import com.Hotelreservations.Hotelreservations.repository.RepositoryReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ServiceReserva {
    private RepositoryHabitacion habitacionRepository;
    private RepositoryCliente clienteRepository;
    private RepositoryReserva reservaRepository;

    @Autowired
    public ServiceReserva(RepositoryHabitacion habitacionRepository, RepositoryCliente clienteRepository, RepositoryReserva reservaRepository) {
        this.habitacionRepository = habitacionRepository;
        this.clienteRepository = clienteRepository;
        this.reservaRepository = reservaRepository;
    }

    public ReservaDto reservar(Long cedula, Integer numero, String fecha){
        if (cedula <= 0 || numero <= 0 || fecha == null){
            throw new InvalidDataException("Los datos no son válidos");
        }

        Optional<Cliente> cliente = this.clienteRepository.findById(cedula);
        Optional<Habitacion> habitacion = this.habitacionRepository.findById(numero);

        if(cliente.isPresent() && habitacion.isPresent()){

            Pattern pattern = Pattern
                    .compile("^\\d{4}-\\d{2}-\\d{2}$");
            Matcher matcher = pattern.matcher(fecha);

            if(!matcher.find()){
                throw new IncorrectFormatException("La fecha no está en formato válido");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate date = LocalDate.parse(fecha, formatter);

            if(date.isBefore(LocalDate.now())){
                throw new InvalidDateException("La fecha no puede ser anterior a la actual");
            }

            List<Integer> disponiblesId = this.reservaRepository.getAvailability(fecha);
            boolean habitacionDisponible = disponiblesId.contains(numero);
            boolean noReservas = this.reservaRepository.cantidadReservas()==0;

            if(noReservas || (disponiblesId.size()!=0 && habitacionDisponible)){
                Habitacion habitacion1 = habitacion.get();
                double descuento = 0;
                Reserva reserva = new Reserva(cliente.get(),habitacion.get(),fecha);
                if(habitacion1.getTipoHabitacion().equalsIgnoreCase("premium")){
                    descuento = habitacion1.getPrecioBase() * 0.05;
                }
                reserva.setTotal(habitacion1.getPrecioBase() - descuento);
                this.reservaRepository.save(reserva);
                return new ReservaDto(reserva.getCodigo(),reserva.getFechaReserva(), reserva.getHabitacion().getNumero(),reserva.getTotal());
            }else{
                throw new BookedRoomException("La habitación ya esta reservada");
            }
        }
        throw new DataNotFoundException("Habitación y/o cliente no encontrados");
    }




    public List<Reserva> getByClient(Long cedula){
        return this.reservaRepository.findAllById(cedula);
    }

    public Set<Habitacion> getByDate(String date) {
        return this.reservaRepository.findByDate(date);
    }

    public List<Habitacion> getByDateType(String date, String tipo){
        return this.reservaRepository.findByDateType(date, tipo);
    }
}
