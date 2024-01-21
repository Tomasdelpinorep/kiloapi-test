package com.salesianostriana.kilo.services;

import com.salesianostriana.kilo.entities.Aportacion;
import com.salesianostriana.kilo.entities.DetalleAportacion;
import com.salesianostriana.kilo.entities.KilosDisponibles;
import com.salesianostriana.kilo.entities.TipoAlimento;
import com.salesianostriana.kilo.repositories.AportacionRepository;
import com.salesianostriana.kilo.repositories.TipoAlimentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AportacionServiceTests {

    @Mock
    AportacionRepository aportacionRepository;

    @Mock
    TipoAlimentoSaveService tipoAlimentoSaveService;

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

        t.setKilosDisponibles(kilosDisponibles);

        DetalleAportacion detalleBorrable = DetalleAportacion.builder()
                .tipoAlimento(t)
                .cantidadKg(10.0)
                .build();



        Aportacion aportacion = Aportacion.builder()
                .fecha(LocalDate.now())
                .detalleAportaciones(List.of(detalleBorrable))
                .build();

        aportacionService.borrarDetallesAportacion(aportacion);
        tipoAlimentoSaveService.save(kilosDisponibles.getTipoAlimento());

        System.out.println(t.getKilosDisponibles().getCantidadDisponible());

        assertEquals(110.0, t.getKilosDisponibles().getCantidadDisponible());
        assertTrue(aportacion.getDetalleAportaciones().isEmpty());


    }
}