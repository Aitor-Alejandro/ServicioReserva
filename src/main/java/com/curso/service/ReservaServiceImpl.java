package com.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.dtopattern.ReservaDTO;
import com.curso.repository.ReservaRepository;
/**
 * Clase implementadora de las operaciones CRUD de la tabla 'reservas' de la base de datos
 * @author Aitor Alejandro Martinez Cedillo \ Viewnext
 * @version 1.0 2024-12-31
 */
@Service
public class ReservaServiceImpl implements ReservaService {
	@Autowired
	ReservaRepository repository;
	@Override
	public List<Object[]> reservasHotel(long idHotel) {
		return repository.findReservaDTOByIdHotel(idHotel);
	}
	
	
}
