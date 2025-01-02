package com.curso.dtopattern;
/**
 * 
 * @author Aitor Alejandro Martinez Cedillo \ Viewnext
 * @version 1.0 2024-12-31
 */
public class ReservaDTO {
	private String nombre;
	private String dni;
	private long idVuelo;
	
	public ReservaDTO() {
		super();
	}
	
	public ReservaDTO(String nombre) {
		this();
		this.nombre = nombre;
	}
	
	public ReservaDTO(String nombre, String dni) {
		this(nombre);
		this.dni = dni;
	}
	
	public ReservaDTO(String nombre, String dni, long idVuelo) {
		this(nombre, dni);
		this.idVuelo = idVuelo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public long getIdVuelo() {
		return idVuelo;
	}

	public void setIdVuelo(long idVuelo) {
		this.idVuelo = idVuelo;
	}

}
