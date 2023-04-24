package com.Hotelreservations.Hotelreservations.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="reserva")
public class Reserva {
    @Id
    @GeneratedValue()
    private UUID codigo;
    @Column(name="fechaReserva")
    private String fechaReserva;

    @ManyToOne
    @JoinColumn(name="cedula")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="numero")
    private Habitacion habitacion;

    @Column(name="total")
    private Double total;

    public Reserva() {
    }

    public Reserva( Cliente cliente, Habitacion habitacion,String fechaReserva) {
        this.codigo = UUID.randomUUID();
        this.fechaReserva = fechaReserva;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.total = total;
    }

    public UUID getCodigo() {
        return codigo;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
