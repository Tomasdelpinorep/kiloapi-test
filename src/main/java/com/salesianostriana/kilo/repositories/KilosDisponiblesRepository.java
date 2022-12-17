package com.salesianostriana.kilo.repositories;

import com.salesianostriana.kilo.dtos.kilos_disponibles.KilosDisponiblesDTO;
import com.salesianostriana.kilo.entities.KilosDisponibles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KilosDisponiblesRepository extends JpaRepository<KilosDisponibles, Long> {

    @Query("""
            SELECT new com.salesianostriana.kilo.dtos.kilos_disponibles.KilosDisponiblesDTO(ta.id, ta.nombre, kd.cantidadDisponible)
            FROM KilosDisponibles kd JOIN TipoAlimento ta ON ta.id = kd.tipoAlimento
            """)
    public List<KilosDisponiblesDTO> getAllKgDisponibles();
}
