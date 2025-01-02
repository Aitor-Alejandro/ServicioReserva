package com.curso.service;

import java.util.List;

import com.curso.model.Reserva;

public interface ReservaService {
	List<Object[]> reservasHotel(long idHotel);
	void save(Reserva reserva);
}
