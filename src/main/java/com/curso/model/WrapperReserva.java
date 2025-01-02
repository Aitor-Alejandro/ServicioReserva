package com.curso.model;
/**
 * Clase Wrapper para la recepcion de reserva JSON con valores adicionales
 * @author Aitor Alejandro Martinez Cedillo \ Viewnext
 * @version 1.0 2025-01-02
 */
public class WrapperReserva extends Reserva {
	private int plazas;
	
	public WrapperReserva() {
		super();
	}

	public WrapperReserva(long idReserva) {
		super(idReserva);
	}
	
	public WrapperReserva(long idReserva, String nombreCliente, String dni, long idHotel, long idVuelo, int plazas) {
		super(idReserva, nombreCliente, dni, idHotel, idVuelo);
		this.plazas = plazas;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}
	
}
