package com.Hotelreservations.Hotelreservations.repository;

import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.Reserva;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface RepositoryReserva extends CrudRepository<Reserva, UUID> {
    @Query("SELECT r FROM Reserva r WHERE r.cliente.cedula = ?1")
    public List<Reserva> findAllById(Long keyword);

    @Query("Select  h FROM Habitacion h, Reserva r WHERE h.numero not in(SELECT habitacion.numero from Reserva) " +
            "OR h.numero not in(select distinct r.habitacion.numero from  Reserva r where r.fechaReserva= ?1)")
        public Set<Habitacion> findByDate(String fecha);

    @Query("Select  h FROM Habitacion h, Reserva r WHERE (h.numero not in(SELECT habitacion.numero from Reserva) " +
            "OR h.numero not in(select distinct r.habitacion.numero from  Reserva r where r.fechaReserva= ?1)) AND h.tipoHabitacion=?2")
    public List<Habitacion> findByDateType(String fecha, String tipo);

    @Query("Select h.numero FROM Habitacion h, Reserva r WHERE h.numero not in(SELECT habitacion.numero from Reserva) " +
            "OR h.numero not in(select distinct r.habitacion.numero from  Reserva r where r.fechaReserva= ?1)")
    public List<Integer> getAvailability(String fecha);

    @Query("select count(*) from Reserva")
    public Integer cantidadReservas();
}

