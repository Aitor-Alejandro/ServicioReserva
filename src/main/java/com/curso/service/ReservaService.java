package com.curso.service;

import java.util.List;

import com.curso.dtopattern.ReservaDTO;

public interface ReservaService {
	List<Object[]> reservasHotel(long idHotel);
}
