package com.Hotelreservations.Hotelreservations.repository;

import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, String> {
    @Query("SELECT h FROM Habitacion h WHERE h.numero NOT IN (SELECT r.habitacion.numero FROM Reserva r) " +
            "OR h.numero NOT IN (SELECT DISTINCT r.habitacion.numero FROM Reserva r WHERE r.fechaReserva = ?1)")
    public Set<Habitacion> findByDate(String fecha);

    @Query("SELECT r FROM Reserva r WHERE r.cliente.cedula = ?1")
    public List<Reserva> findAllById(Long cedula);

     /*@Query("Select  h FROM Habitacion h, Reserva r WHERE (h.numero not in(SELECT h.Habitacion.numero from r.Reserva) " +
            "OR h.numero not in(select distinct r.Habitacion.numero from  Reserva r where r.fechaReserva= ?1)) AND h.tipoHabitacion=?2")
    public List<Habitacion> findByDateType(String fecha, String tipo);*/

    @Query("SELECT h FROM Habitacion h WHERE (h.numero NOT IN (SELECT r.habitacion.numero FROM Reserva r)" +
            " OR h.numero NOT IN (SELECT DISTINCT r.habitacion.numero FROM Reserva r WHERE r.fechaReserva = ?1)) AND h.tipo = ?2")
    public List<Habitacion> findByDateType(String fecha, String tipo);




    @Query("select count(*) from Reserva")
    public Integer cantidadReservas();

    /*@Query("Select h.numero FROM Habitacion h, Reserva r WHERE h.numero not in(SELECT r.habitacion.numero from Reserva r) " +
            "OR h.numero not in(select distinct h.Habitacion.numero from  Reserva r where r.fechaReserva= ?1)")
    public List<Integer> getAvailability(String fecha);*/

    @Query("SELECT h.numero FROM Habitacion h WHERE h.numero NOT IN (SELECT r.habitacion.numero FROM Reserva r) " +
            "OR h.numero NOT IN (SELECT DISTINCT r.habitacion.numero FROM Reserva r WHERE r.fechaReserva = ?1)")
    public List<Integer> getAvailability(String fecha);


}


