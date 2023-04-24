package com.Hotelreservations.Hotelreservations;

import static org.junit.Assert.assertTrue;

import com.Hotelreservations.Hotelreservations.dto.ReservaDto;
import com.Hotelreservations.Hotelreservations.exceptions.DataNotFoundException;
import com.Hotelreservations.Hotelreservations.exceptions.IncorrectFormatException;
import com.Hotelreservations.Hotelreservations.exceptions.InvalidDataException;
import com.Hotelreservations.Hotelreservations.exceptions.InvalidDateException;
import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.Reserva;
import com.Hotelreservations.Hotelreservations.repository.RepositoryCliente;
import com.Hotelreservations.Hotelreservations.repository.RepositoryHabitacion;
import com.Hotelreservations.Hotelreservations.repository.RepositoryReserva;
import com.Hotelreservations.Hotelreservations.service.ServiceReserva;
import org.junit.*;

import java.util.Optional;

import static org.mockito.Mockito.*;


public class ReservaServiceTest {

    RepositoryReserva reservaRepository;
    RepositoryHabitacion habitacionRepository;
    RepositoryCliente clienteRepository;
    ServiceReserva reservaService;

    @Before
    public void setUp(){
        this.reservaRepository = mock(RepositoryReserva.class);
        this.clienteRepository = mock(RepositoryCliente.class);
        this.habitacionRepository = mock(RepositoryHabitacion.class);
        this.reservaService = new ServiceReserva(habitacionRepository,clienteRepository,reservaRepository);
    }

    @Test(expected= InvalidDataException.class)
    public void pruebaReservaConFechaNula(){
        Long cedula = 123L;
        Integer numero = 101;
        String fecha = null;
        ReservaDto confirmacion = this.reservaService.reservar(cedula,numero,fecha);
    }

    @Test(expected=InvalidDataException.class)
    public void pruebaReservaConCedulaNegativa(){
        Long cedula = -123L;
        Integer numero = 101;
        String fecha = "2023-05-05";
        ReservaDto confirmacion = this.reservaService.reservar(cedula,numero,fecha);
    }

    @Test(expected=InvalidDataException.class)
    public void pruebaReservaConNumeroHabitacionNegativo(){
        Long cedula = 123L;
        Integer numero = -101;
        String fecha = "2023-05-05";
        ReservaDto confirmacion = this.reservaService.reservar(cedula,numero,fecha);
    }
    @Test(expected= InvalidDateException.class)
    public void pruebaReservaFechaAnteriorActual(){
        Long cedula = 123L;
        Integer numero = 101;
        String fecha = "2010-05-05";
        Cliente cliente = new Cliente(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Habitacion habitacion = new Habitacion(101,"premium",100000.0);
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(habitacion));
        ReservaDto confirmacion = this.reservaService.reservar(cedula,numero,fecha);
        verify(reservaRepository,times(1)).save(any());
    }

    @Test(expected= IncorrectFormatException.class)
    public void pruebaReservaFechaFormatoIncorrecto(){
        Long cedula = 123L;
        Integer numero = 101;
        String fecha = "5-5-2023";
        Cliente cliente = new Cliente(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Habitacion habitacion = new Habitacion(101,"premium",100000.0);
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(habitacion));
        ReservaDto confirmacion = this.reservaService.reservar(cedula,numero,fecha);
        verify(reservaRepository,times(1)).save(any());
    }


    @Test(expected = DataNotFoundException.class)
    public void pruebaHabitacionNoEncontrada(){
        Long cedula = 123L;
        Integer numero = 101;
        String fecha = "2010-05-05";
        Cliente cliente = new Cliente(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Habitacion habitacion = new Habitacion(101,"premium",100000.0);
        ReservaDto confirmacion = this.reservaService.reservar(cedula,numero,fecha);
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));
        when(habitacionRepository.findById(any())).thenReturn(Optional.empty());
        Reserva reserva = new Reserva(cliente,habitacion,fecha);
        assertTrue(reserva.getCliente() == null);
        assertTrue(reserva.getHabitacion() == null);
    }

    @Test(expected = DataNotFoundException.class)
    public void pruebaClienteNoEncontrado(){
        Long cedula = 123L;
        Integer numero = 101;
        String fecha = "2010-05-05";
        Cliente cliente = new Cliente(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Habitacion habitacion = new Habitacion(101,"premium",100000.0);
        ReservaDto confirmacion = this.reservaService.reservar(cedula,numero,fecha);
        when(clienteRepository.findById(any())).thenReturn(Optional.empty());
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(habitacion));
        Reserva reserva = new Reserva(cliente,habitacion,fecha);
        assertTrue(reserva.getCliente() == null);
        assertTrue(reserva.getHabitacion() == null);
    }

    @Test(expected = DataNotFoundException.class)
    public void pruebaClienteNiHabitacionEncontrados(){
        Long cedula = 123L;
        Integer numero = 101;
        String fecha = "2023-05-05";
        Cliente cliente = new Cliente(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Habitacion habitacion = new Habitacion(101,"premium",100000.0);
        ReservaDto confirmacion = this.reservaService.reservar(cedula,numero,fecha);
        when(clienteRepository.findById(any())).thenReturn(Optional.empty());
        when(habitacionRepository.findById(any())).thenReturn(Optional.empty());
        Reserva reserva = new Reserva(cliente,habitacion,fecha);
        assertTrue(reserva.getCliente() == null);
        assertTrue(reserva.getHabitacion() == null);
    }

    @Test
    public void pruebaReservaHabitacion(){
        Long cedula = 123L;
        Integer numero = 101;
        String fecha = "2023-05-05";
        Cliente cliente = new Cliente(123L,"Sofia","Millan","Cll 26","17","s@gmail.com");
        Habitacion habitacion = new Habitacion(101,"premium",100000.0);
        when(clienteRepository.findById(cedula)).thenReturn(Optional.of(cliente));
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(habitacion));
        ReservaDto confirmacion = this.reservaService.reservar(cedula,numero,fecha);
        Reserva reserva = new Reserva(cliente,habitacion,fecha);
        verify(reservaRepository,times(1)).save(any());
        assertTrue(reserva.getCliente().getCedula()==123L);
        assertTrue(reserva.getHabitacion().getNumero()==101);
        assertTrue(reserva.getFechaReserva()!=null);
    }
}
