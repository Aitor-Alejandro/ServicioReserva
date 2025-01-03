package com.curso.inicio.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.curso.model.Reserva;
import com.curso.service.ReservaService;
@SpringBootTest
@AutoConfigureMockMvc
class TestReservaController {
	@MockitoBean
	private ReservaService service;
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		Reserva reserva = new Reserva(1, "joseLuisGil", "456z", 1, 2);
		when(service.findById(1L)).thenReturn(reserva);
		when(service.findAll()).thenReturn(Arrays.asList(reserva));
	}
	
	@Test
	public void testGetAll()  {
		try {
		mockMvc.perform(get("/api/reservas"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].nombreCliente", is("joseLuisGil")))
			.andExpect(jsonPath("$[0].dni", is("456z")));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetById() throws Exception {
		mockMvc.perform(get("/api/reservas/id/1"))
			.andExpect(status().isOk());
		mockMvc.perform(get("/api/reservas/id/2"))
			.andExpect(status().isNotFound());
	}
	
}
