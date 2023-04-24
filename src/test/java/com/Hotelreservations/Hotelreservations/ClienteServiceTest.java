package com.Hotelreservations.Hotelreservations;


import com.Hotelreservations.Hotelreservations.exceptions.InvalidDataException;
import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.repository.RepositoryCliente;
import com.Hotelreservations.Hotelreservations.service.ServiceCliente;
import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.*;

public class ClienteServiceTest {
    RepositoryCliente clienteRepository;
    ServiceCliente clienteService;
    @Before
    public void setUp(){
        this.clienteRepository = mock(RepositoryCliente.class);
        this.clienteService = new ServiceCliente(clienteRepository);
    }

    @Test(expected= InvalidDataException.class)
    public void pruebaNombreNulo(){
        Cliente cliente = new Cliente(123L,null,"Millan","Cll 26","17","s@gmail.com");
        Cliente clienteCreado = this.clienteService.crear(cliente);
        verify(clienteRepository, times(1)).save(clienteCreado);
    }

    @Test(expected=InvalidDataException.class)
    public void pruebaApellidoNulo(){
        Cliente cliente = new Cliente(123L,"Sofía",null,"Cll 26","17","s@gmail.com");
        Cliente clienteCreado = this.clienteService.crear(cliente);
        verify(clienteRepository, times(1)).save(clienteCreado);
    }
    @Test
    public void pruebaDatosCompletos(){
        Cliente cliente = new Cliente(123L,"Sofía","Millán","Cll 26","17","s@gmail.com");
        Cliente clienteCreado = this.clienteService.crear(cliente);
        verify(clienteRepository, times(1)).save(clienteCreado);
    }
}
