package com.cubetech.representacion.impresa.domain.repositorio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cubetech.representacion.impresa.ObjetosTest;
import com.cubetech.representacion.impresa.domain.entidades.CuentaEmisor;
import com.cubetech.representacion.impresa.domain.entidades.Reporte;
import com.cubetech.representacion.impresa.domain.valueobjects.EmisorReporte;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CuentaEmisorRepositoryTest {

	private Reporte reporte = null;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CuentaEmisorRepository cuentaRepository;
	
	@Before
	public void inicializa(){
		if(reporte == null){
			this.reporte = entityManager.persist(ObjetosTest.creaReporte());
			entityManager.flush();
		}
	}

	@Test
	public void testFindOne() {
		CuentaEmisor cta = ObjetosTest.creaCuentaEmisor();
		cta.setReporte(this.reporte);
		CuentaEmisor cta2 = cuentaRepository.save(cta);
		assertNotNull(cta2);
		EmisorReporte em =ObjetosTest.creaEmisorReporte();
		CuentaEmisor cta3 = cuentaRepository.findOne(em);
		assertTrue(cta3.sameIdentityAs(cta2));
	}

}
