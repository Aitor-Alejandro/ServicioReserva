package com.curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.curso.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
	@Query("SELECT r.nombreCliente, r.dni, r.idVuelo FROM Reserva r WHERE r.idHotel = :idHotel")
	List<Object[]> findReservaDTOByIdHotel(long idHotel);
}
