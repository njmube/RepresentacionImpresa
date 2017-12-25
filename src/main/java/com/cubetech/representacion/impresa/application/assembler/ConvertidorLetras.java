package com.cubetech.representacion.impresa.application.assembler;

import java.math.BigInteger;

/**
 * 
 * @author Cesar Varela
 *
 */

public class ConvertidorLetras {
	private static final String[] UNIDADES = { "", "UN ", "DOS ", "TRES ",
      "CUATRO ", "CINCO ", "SEIS ", "SIETE ", "OCHO ", "NUEVE ", "DIEZ ",
      "ONCE ", "DOCE ", "TRECE ", "CATORCE ", "QUINCE ", "DIECISEIS",
      "DIECISIETE", "DIECIOCHO", "DIECINUEVE", "VEINTE" };
	private static final String[] DECENAS = {"","DIEZ ", "VEINTI", "TREINTA ", "CUARENTA ",
      "CINCUENTA ", "SESENTA ", "SETENTA ", "OCHENTA ", "NOVENTA ",
      "CIEN " };	 
	 private static final String[] CENTENAS = { "", "CIENTO ", "DOSCIENTOS ",
       "TRESCIENTOS ", "CUATROCIENTOS ", "QUINIENTOS ", "SEISCIENTOS ",
       "SETECIENTOS ", "OCHOCIENTOS ", "NOVECIENTOS " };
	 private static final String[] PERIODO = {"", "MILLON", "BILLON", "TRILLON", "CUATRILLON"};
	 
	 private static final BigInteger MIL = new BigInteger("1000");
	 private static final BigInteger MILLON = new BigInteger("1000000");
	 
	 public static String convierteLetras(int valor) throws NumberFormatException {
		 String ret = "";
		 int unidad;
		 int decena;
		 int centena;
		 
		 if(valor > 999)
			 throw new NumberFormatException("EL valor maximo permitido es 999");
		 if(valor < 0)
			 throw new NumberFormatException("EL valor minimo permitido es 0");

		 unidad = valor%10;
		 valor = valor/10;
		 
		 ret = UNIDADES[unidad];
		 
		 decena = valor%10;
		 valor = valor/10;
		 
		 if(unidad == 0 && decena > 0){
			 if(decena == 2)
				 ret = UNIDADES[20];
			 else
				 ret = DECENAS[decena];
		 }
		 else if(decena == 1 )
			 ret = UNIDADES[10 + unidad];
		 else if(decena == 2){
			 ret = DECENAS[decena] + ret;
		 }
		 else if(decena > 2){
			 ret = DECENAS[decena] + "Y " + ret;
		 }
		 
		 centena = valor%10;
		 if(unidad == 0 && decena == 0 && centena == 1)
			 ret =DECENAS[10];
		 else if(centena > 0)
			 ret = CENTENAS[centena] + ret;
		 return ret;
	 }
	 
	 public static String convierteLetras(BigInteger valor){
		 String ret = "";
		 String miles = "";
		 int millones = 0;
		 BigInteger[] divMillones;
		 BigInteger valorMiles;
		 BigInteger[] divMiles;
		 
		 do{
			 divMillones = valor.divideAndRemainder(MILLON);
			 valorMiles =divMillones[1];
			 divMiles = valorMiles.divideAndRemainder(MIL);
			 
			 if(divMiles[1].compareTo(BigInteger.ZERO) > 0){
				 miles = convierteLetras(divMiles[1].intValueExact());
			 }
			 if(divMiles[0].compareTo(BigInteger.ZERO) > 0){
				 if(divMiles[0].compareTo(BigInteger.ONE) == 0)
					 miles = "MIL " + miles;
				 else
					 miles = convierteLetras(divMiles[0].intValueExact()) + "MIL " + miles;
			 }
			 
			 miles = miles + PERIODO[millones];
			 if(millones > 0 && valor.compareTo(BigInteger.ONE) > 0)
				 miles = miles + "ES ";
			 else
				 miles = miles + " ";
			 ret = miles + ret;
			 
			 valor = divMillones[0];
			 millones++;
		 }while(valor.compareTo(BigInteger.ZERO) > 0);
		 
		 ret = ret.trim();
		 return ret;
	 }
	 
}
