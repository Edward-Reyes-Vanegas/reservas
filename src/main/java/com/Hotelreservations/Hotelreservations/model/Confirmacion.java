package com.Hotelreservations.Hotelreservations.model;

import java.util.UUID;

public class Confirmacion {

    private UUID numReserva;
    private String fecha;
    private Integer numHabitacion;
    private String nombre;
    private Double total;



    public Confirmacion(){

    }

    public Confirmacion(UUID numReserva, String fecha, Integer numHabitacion, String nombre, Double total) {
        this.numReserva = numReserva;
        this.fecha = fecha;
        this.numHabitacion = numHabitacion;
        this.nombre = nombre;
        this.total = total;
    }

    public UUID getNumReserva() {
        return numReserva;
    }

    public String getFecha() {
        return fecha;
    }

    public Integer getNumHabitacion() {
        return numHabitacion;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getTotal() {
        return total;
    }
}
