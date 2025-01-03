package com.curso.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.curso.dtopattern.HotelDTO;
import com.curso.dtopattern.ReservaDTO;
import com.curso.model.Reserva;
import com.curso.model.WrapperReserva;
import com.curso.service.ReservaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST de la api de reservas, presenta interaccion con los servicios de Hotel y Avion
 * @author Aitor Alejandro Martinez Cedillo \ Viewnext
 * @version 1.0 2024-12-31
 */
@Tag(name = "Servicio de reservas", description="API para el control de reservas de vuelos y de hoteles")
@RestController
@RequestMapping(value="/api/reservas")
public class ReservaController {
	@Autowired
	ReservaService service;
	@Autowired
	RestTemplate template;
	private static final String URLHOTEL = "http://localhost:8080/api/hoteles";
	private static final String URLAVION = "http://localhost:8081/api/vuelos";
	
	@Operation(summary = "Recupera toda la lista de reservas", description = "Recupera todas las reservas existentes en la base de datos", responses = {
			@ApiResponse(responseCode = "200", description = "Se han logrado devolver todos los elementos de la base de datos")
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Reserva>> findAll(){
		return new ResponseEntity<> (service.findAll(), HttpStatus.OK);
	}
	@Operation(summary = "Recupera la informacion de una reserva a partir de su Id", description = "Recibe como parametro en la URL el id de una" + 
	" reserva, si esta reserva existe, devuelve el valor null en caso contrario", responses = {
			@ApiResponse(responseCode = "200", description = "La reserva existe en la base de datos y se devuelve"),
			@ApiResponse(responseCode = "404", description = "No existe niguna reserva con el Id solicitado")
	})
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Reserva> findById(@Parameter(
			name = "id",
			description = "El id por el que se pretende buscar a la Reserva",
			required = true)
	@PathVariable long id) {
		Reserva reserva = service.findById(id);
		HttpStatus status = reserva != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<> (service.findById(id), status);
	}
	@Operation(summary = "Busca las reservas de un hotel buscando por su nombre", description = "Recibe como parametro de entrada el nombre de un hotel" +
	" y envia una peticion GET al servicio de Hotel para obtener informacion de ese hotel, una vez que se obtiene la informacion del hotel, se devuelven" +
	" las reservas existentes para dicho Hotel", responses = {
			@ApiResponse(responseCode = "200", description = "Se devuelven todas las reservas existentes para el hotel buscado")
	})
	@GetMapping(value="/{nombre}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReservaDTO>> getReservas(@Parameter(
			name="nombre",
			description="Nombre del hotel que se pretende buscar",
			required = true)
			@PathVariable String nombre){
		HotelDTO hDto = template.getForObject(URLHOTEL + "/{nombre}", HotelDTO.class, nombre);
		List<ReservaDTO> listReservaDto = new ArrayList<>();
		List<Object[]> listObj = service.reservasHotel(hDto.getIdHotel());
		for (Object[] object : listObj) {
			listReservaDto.add(new ReservaDTO ((String) object[0], (String) object[1], (long) object[2]));

		}
		
		return new ResponseEntity<>(listReservaDto, HttpStatus.OK);
	}
	@Operation(summary = "Crear una nueva reserva", description = "Recibe en el cuerpo del mensaje la descripcion de una reserva y un numero" + 
	" de plazas, consulta al servicio de Vuelos si es posible reservar el numero de plazas indicado para el vuelo solicitado, dependiendo de la" + 
	" respuesta del servicio de Vuelo, se registrará o no la reserva", responses = {
			@ApiResponse(responseCode = "201", description = "La reserva se ha creado con éxito"),
			@ApiResponse(responseCode = "409", description = "El servicio Vuelo ha rechazado la operacion")
	})
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> reservarVuelo(@RequestBody WrapperReserva request) {
		
		Reserva reserva = new Reserva (request.getIdReserva(), request.getNombreCliente(), request.getDni(),request.getIdHotel(), request.getIdVuelo());
		int nPersonas = request.getPlazas();
		System.out.println(nPersonas);
		HttpEntity<Reserva> entity = new HttpEntity<>(reserva);
		boolean peticionAceptada = true;
		try {
		ResponseEntity<Void> response = template.exchange(URLAVION + "/{idVuelo}/{nPersonas}", HttpMethod.PUT,
				entity, Void.class, reserva.getIdVuelo(), nPersonas);
		System.out.println(response.getStatusCode());
		}catch(HttpClientErrorException e) {
			peticionAceptada = false;
		}
		if(peticionAceptada) {
			service.save(reserva);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
}
