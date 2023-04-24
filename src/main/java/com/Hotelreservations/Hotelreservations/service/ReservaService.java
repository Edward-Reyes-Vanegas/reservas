package com.Hotelreservations.Hotelreservations.service;

import com.Hotelreservations.Hotelreservations.exceptions.DataNotFoundException;
import com.Hotelreservations.Hotelreservations.exceptions.IncorrectFormatException;
import com.Hotelreservations.Hotelreservations.exceptions.InvalidDataException;
import com.Hotelreservations.Hotelreservations.exceptions.InvalidDateException;
import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.Reserva;
import com.Hotelreservations.Hotelreservations.repository.ClienteRepository;
import com.Hotelreservations.Hotelreservations.repository.HabitacionRepository;
import com.Hotelreservations.Hotelreservations.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ReservaService {
    private HabitacionRepository habitacionRepository;
    private ClienteRepository clienteRepository;
    private ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(HabitacionRepository habitacionRepository, ClienteRepository clienteRepository, ReservaRepository reservaRepository) {
        this.habitacionRepository = habitacionRepository;
        this.clienteRepository = clienteRepository;
        this.reservaRepository = reservaRepository;
    }
    @Transactional
    public Reserva reservar(Long cedula, Integer numero, String fecha){
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
                String tipo=habitacion1.getTipo().name();
                UUID uuid=UUID.randomUUID();
                String codigo= uuid.toString();
                double descuento = 0;
                Reserva reserva = new Reserva(codigo, fecha,habitacion.get(),cliente.get(),4500.00);
                if(tipo.equalsIgnoreCase("PREMIUM")){
                    descuento = habitacion1.getPrecioBase() * 0.05;
                }
                reserva.setTotalAPagar(habitacion1.getPrecioBase() - descuento);
                this.reservaRepository.save(reserva);
                return new Reserva(reserva.getCodigo(), reserva.getFechaReserva(), reserva.getHabitacion(), reserva.getCliente(),reserva.getTotalAPagar());
            }else{
                throw new IllegalArgumentException("Hab ya reservada");
            }
        }

        throw new DataNotFoundException("Habitación y/o cliente no encontrados");
    }

   public List<Reserva> getByClient(Long cedula){

        return this.reservaRepository.findAllById(cedula);
    }

    public Set<Habitacion> getByDate(String fecha) {
        return this.reservaRepository.findByDate(fecha);
    }

    public List<Habitacion> getByDateType(String date, String tipo){
        return this.reservaRepository.findByDateType(date, tipo);
    }
}
