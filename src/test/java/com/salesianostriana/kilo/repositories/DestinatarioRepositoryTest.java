package com.salesianostriana.kilo.repositories;

import com.salesianostriana.kilo.entities.Caja;
import com.salesianostriana.kilo.entities.Destinatario;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DestinatarioRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    DestinatarioRepository destinatarioRepository;

    @Test
    void numerosdeCaja() {

        Destinatario destinatario = Destinatario.builder()
//                .id(1L)
                .nombre("Destinatario 1")
                .direccion("C/ Destinatario")
                .personaContacto("ContactoDestinatario")
                .telefono("123456789")
                .build();

        Caja caja = Caja.builder()
//                .id(1L)
                .qr("Caja 1")
                .numCaja(1)
                .kilosTotales(50.0)
                .destinatario(destinatario)
                .build();

        destinatario.getCajas().add(caja);

        entityManager.persist(destinatario);
        entityManager.persist(caja);

        List<Integer> numCajas = destinatarioRepository.numerosdeCaja(1L);

        System.out.println("Número de Cajas: " + numCajas);

        assertEquals(List.of(1, 5, 6), numCajas);
//        assertEquals(1, numCajas);

    }
}