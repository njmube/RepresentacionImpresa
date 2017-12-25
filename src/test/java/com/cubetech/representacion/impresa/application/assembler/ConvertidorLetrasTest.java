package com.cubetech.representacion.impresa.application.assembler;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class ConvertidorLetrasTest {

	
	@Test
	public void testConvierteLetrasBigInteger() {
		String descripcion = ConvertidorLetras.convierteLetras(new BigInteger("15936535897932384626433832795"));
		assertTrue(descripcion.equals("QUINCE MIL NOVECIENTOS TREINTA Y SEIS CUATRILLONES QUINIENTOS TREINTA Y CINCO MIL OCHOCIENTOS NOVENTA Y SIETE TRILLONES NOVECIENTOS TREINTA Y DOS MIL TRESCIENTOS OCHENTA Y CUATRO BILLONES SEISCIENTOS VEINTISEIS MIL CUATROCIENTOS TREINTA Y TRES MILLONES OCHOCIENTOS TREINTA Y DOS MIL SETECIENTOS NOVENTA Y CINCO"));
	}

	
	@Test
	public void testConvierteLetrasCien() {
		String descripcion = ConvertidorLetras.convierteLetras(new BigInteger("100"));
		assertTrue(descripcion.equals("CIEN"));
	}
	
	@Test
	public void testConvierteLetras25() {
		String descripcion = ConvertidorLetras.convierteLetras(new BigInteger("25"));
		assertTrue(descripcion.equals("VEINTICINCO"));
	}
	@Test
	public void testConvierteLetras1000() {
		String descripcion = ConvertidorLetras.convierteLetras(new BigInteger("1000"));
		assertTrue(descripcion.equals("MIL"));
	}
}
