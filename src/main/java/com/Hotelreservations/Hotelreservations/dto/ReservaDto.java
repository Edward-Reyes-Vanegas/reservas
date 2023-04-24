package com.Hotelreservations.Hotelreservations.dto;


import java.util.UUID;
    public class ReservaDto {
        private UUID numeroReserva;
        private String fecha;
        private Integer numeroHabitacion;

        private Double total;

        public ReservaDto(){
        }
        public ReservaDto(UUID numeroReserva, String fecha, Integer numeroHabitacion, Double total) {
            this.numeroReserva = numeroReserva;
            this.fecha = fecha;
            this.numeroHabitacion = numeroHabitacion;
            this.total = total;
        }
        public UUID getNumeroReserva() {
            return numeroReserva;
        }
        public String getFecha() {
            return fecha;
        }
        public Integer getNumeroHabitacion() {
            return numeroHabitacion;
        }

        public Double getTotal() {
            return total;
        }
    }