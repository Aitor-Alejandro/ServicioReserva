package com.curso.dtopattern;
/**
 * Clase POJO HotelDTO para la implementacion del patron DTO y la recepcion de datos del servicio de hotel
 * @author Aitor Alejandro Martinez Cedillo \ Viewnext
 * @version 1.0 2024-12-31
 */
public class HotelDTO {
	private long idHotel;
	private String nombre;
	private String categoria;
	private double precio;
	private boolean disponible;
	
	public HotelDTO() {
		super();
	}
	
	public HotelDTO(long idHotel) {
		super();
		this.idHotel = idHotel;
	}
	
	public HotelDTO(long idHotel, String nombre, String categoria, double precio, boolean disponible) {
		super();
		this.idHotel = idHotel;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.disponible = disponible;
	}

	public long getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(long idHotel) {
		this.idHotel = idHotel;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
}
