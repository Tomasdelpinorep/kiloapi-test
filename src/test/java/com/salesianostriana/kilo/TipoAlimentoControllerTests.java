package com.salesianostriana.kilo;

import com.salesianostriana.kilo.controllers.TipoAlimentoController;
import com.salesianostriana.kilo.entities.DetalleAportacion;
import com.salesianostriana.kilo.entities.KilosDisponibles;
import com.salesianostriana.kilo.entities.TipoAlimento;
import com.salesianostriana.kilo.repositories.TipoAlimentoRepository;
import com.salesianostriana.kilo.services.TipoAlimentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TipoAlimentoController.class)
public class TipoAlimentoControllerTests {

    @Autowired
    MockMvc mvc;

//    @Autowired
//    ObjectMapper mapper;

    @MockBean
    TipoAlimentoService tipoAlimentoService;

    @Test
    void getAlimentoById_ValidId_ReturnsTipoAlimentoDTO_Test() throws Exception {
        TipoAlimento tipoAlimento = TipoAlimento.builder()
                .id(1L)
                .kilosDisponibles(mock(KilosDisponibles.class))
                .nombre("Legumbres")
                .detalleAportaciones(List.of(mock(DetalleAportacion.class)))
                .build();

        when(tipoAlimentoService.findById(1L)).thenReturn(Optional.of(tipoAlimento));

        mvc.perform(get("/tipoAlimento/{id}", 1L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(1)));

    }

}
