package com.curso.controller.test;

import org.junit.jupiter.api.BeforeEach;
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
	void setUp() {
		Reserva reserva = new Reserva(1, "joseLuisGil", "456z", 1, 2);
	}
}
