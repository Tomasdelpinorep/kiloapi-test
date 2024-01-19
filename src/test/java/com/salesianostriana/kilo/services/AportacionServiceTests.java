package com.salesianostriana.kilo.services;

import com.salesianostriana.kilo.entities.Aportacion;
import com.salesianostriana.kilo.entities.DetalleAportacion;
import com.salesianostriana.kilo.entities.KilosDisponibles;
import com.salesianostriana.kilo.entities.TipoAlimento;
import com.salesianostriana.kilo.repositories.AportacionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AportacionServiceTests {

    @Mock
    AportacionRepository aportacionRepository;

    @Mock
    TipoAlimentoService tipoAlimentoService;

    @Mock
    ClaseService claseService;

    @InjectMocks
    AportacionService aportacionService;

    @Test
    void borrarDetallesAportacion() {

        TipoAlimento t = TipoAlimento.builder()
                .nombre("Legumbres")
                .build();

        KilosDisponibles kilosDisponibles = KilosDisponibles.builder()
                .tipoAlimento(t)
                .cantidadDisponible(120.0)
                .build();

        Aportacion aportacion = new Aportacion();
        aportacion.addDetalleAportacion(detalleBorrable);

    }
}