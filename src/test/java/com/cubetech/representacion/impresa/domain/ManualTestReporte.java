package com.cubetech.representacion.impresa.domain;
/*
//import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;

import org.junit.Test;

import com.cubetech.representacion.impresa.ObjetosTest;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class ManualTestReporte {

	@Test
	public void testGeneracion() {
		
		String sourceFileName = "/home/cesarvarela/CUBETECH/CFDI/representacionImpresa/src/main/resources/jasper/comprobante.jasper";
		String outFileName = "/home/cesarvarela/Documents/CUBETECH/test_Integrado.pdf";
		String printFileName = null;
		JasperPrint print;
		
		Map<String, Object> parametros;
		parametros = ObjetosTest.creaParametrosComprobante();
		
		try
		{
			print = JasperFillManager.fillReport(sourceFileName, parametros, new JREmptyDataSource());
			if(print != null){
				byte[] reporte = JasperExportManager.exportReportToPdf(print);
				printFileName = Base64.getEncoder().encodeToString(reporte);
				
				System.out.println(printFileName);
				Files.write(Paths.get(outFileName),reporte);
			}
		}
		catch (JRException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
	}

}
*/
