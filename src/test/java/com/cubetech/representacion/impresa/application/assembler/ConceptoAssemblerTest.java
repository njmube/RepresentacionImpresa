package com.cubetech.representacion.impresa.application.assembler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cubetech.representacion.impresa.ObjetosTest;
import com.cubetech.representacion.impresa.application.xml.comprobante.Comprobante;
import com.cubetech.representacion.impresa.domain.valueobjects.Concepto;




public class ConceptoAssemblerTest {

	private Comprobante.Conceptos.Concepto conceptoXml;
	private ConceptoAssembler conceptoAssembler;
	
	public ConceptoAssemblerTest(){
		this.conceptoAssembler = new ConceptoAssembler();
	}
	
	@Before
	public void inicializa(){
		this.conceptoXml = ObjetosTest.creaConceptoXml();
	}
	
	@Test
	public void testToConcepto() {
		Concepto tmp;
		tmp = this.conceptoAssembler.toConcepto(this.conceptoXml);
		assertNotNull(tmp);
	}

}
