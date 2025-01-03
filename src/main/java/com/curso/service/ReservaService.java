package com.curso.service;

import java.util.List;

import com.curso.model.Reserva;

public interface ReservaService {
	List<Object[]> reservasHotel(long idHotel);
	List<Reserva> findAll();
	Reserva findById(long id);
	void save(Reserva reserva);
}
