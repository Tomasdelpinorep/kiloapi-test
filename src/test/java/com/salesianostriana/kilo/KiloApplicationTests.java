package com.salesianostriana.kilo;

import com.salesianostriana.kilo.entities.Aportacion;
import com.salesianostriana.kilo.entities.DetalleAportacion;
import com.salesianostriana.kilo.entities.KilosDisponibles;
import com.salesianostriana.kilo.entities.TipoAlimento;
import com.salesianostriana.kilo.repositories.AportacionRepository;
import com.salesianostriana.kilo.services.AportacionService;
import com.salesianostriana.kilo.services.TipoAlimentoSaveService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class KiloApplicationTests {

	@InjectMocks
	AportacionService aportacionService;

	@Mock
	TipoAlimentoSaveService tipoAlimentoSaveService;

	@Mock
	AportacionRepository aportacionRepository;


	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this); // Initialize annotated mocks

		// You need to set the mocked tipoAlimentoSaveService in the aportacionService
		aportacionService.setTipoAlimentoSaveService(tipoAlimentoSaveService);
		aportacionService.setAportacionRepository(aportacionRepository);
	}

	@Test
	public void CambiarKilosDetalleResultGreaterOrEqualTo0Test() {

		KilosDisponibles kilosDisponibles = KilosDisponibles.builder()
				.cantidadDisponible(10d)
				.build();

		TipoAlimento tipoAlimento = TipoAlimento.builder()
				.kilosDisponibles(kilosDisponibles)
				.build();

		DetalleAportacion detalle = DetalleAportacion.builder()
				.tipoAlimento(tipoAlimento)
				.cantidadKg(10)
				.build();

		Aportacion aportacion = Aportacion.builder()
				.detalleAportaciones(List.of(detalle))
				.build();

		when(tipoAlimentoSaveService.save(detalle.getTipoAlimento())).thenReturn(tipoAlimento);
		when(aportacionRepository.save(detalle.getAportacion())).thenReturn(aportacion);

		Optional<Aportacion> result = aportacionService.cambiarKilosDetalle(detalle, 15.0);//Result is always null :(

		Assertions.assertEquals(15, result.get().getDetalleAportaciones().get(0).getCantidadKg());
		Assertions.assertEquals(15,tipoAlimento.getKilosDisponibles().getCantidadDisponible());
		Assertions.assertEquals(15, detalle.getCantidadKg());

		verify(tipoAlimentoSaveService, times(1)).save(any(TipoAlimento.class));

	}

}
