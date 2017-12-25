package com.cubetech.representacion.impresa.application.impl;
/*
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cubetech.representacion.impresa.AppConfig;
import com.cubetech.representacion.impresa.AppConfigTest;
import com.cubetech.representacion.impresa.application.ComprobanteXmlService;
import com.cubetech.representacion.impresa.application.xml.comprobante.Comprobante;
import com.cubetech.representacion.impresa.application.xml.timbre.TimbreFiscalDigital;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, AppConfigTest.class})
public class ComprobanteXmlServiceImplTest {

	@Autowired
	private ComprobanteXmlService service;
	private String comprobanteStr;
	
	@Before
	public void inicializa(){
		comprobanteStr = "<cfdi:Comprobante xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd\" LugarExpedicion=\"76090\" MetodoPago=\"PUE\" TipoDeComprobante=\"I\" Total=\"16168.08\" Moneda=\"MXN\" Certificado=\"MIIGlDCCBHygAwIBAgIUMDAwMDEwMDAwMDA0MDUwMDMwNzcwDQYJKoZIhvcNAQELBQAwggGyMTgwNgYDVQQDDC9BLkMuIGRlbCBTZXJ2aWNpbyBkZSBBZG1pbmlzdHJhY2nDs24gVHJpYnV0YXJpYTEvMC0GA1UECgwmU2VydmljaW8gZGUgQWRtaW5pc3RyYWNpw7NuIFRyaWJ1dGFyaWExODA2BgNVBAsML0FkbWluaXN0cmFjacOzbiBkZSBTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpw7NuMR8wHQYJKoZIhvcNAQkBFhBhY29kc0BzYXQuZ29iLm14MSYwJAYDVQQJDB1Bdi4gSGlkYWxnbyA3NywgQ29sLiBHdWVycmVybzEOMAwGA1UEEQwFMDYzMDAxCzAJBgNVBAYTAk1YMRkwFwYDVQQIDBBEaXN0cml0byBGZWRlcmFsMRQwEgYDVQQHDAtDdWF1aHTDqW1vYzEVMBMGA1UELRMMU0FUOTcwNzAxTk4zMV0wWwYJKoZIhvcNAQkCDE5SZXNwb25zYWJsZTogQWRtaW5pc3RyYWNpw7NuIENlbnRyYWwgZGUgU2VydmljaW9zIFRyaWJ1dGFyaW9zIGFsIENvbnRyaWJ1eWVudGUwHhcNMTcwMTMwMjMzNjUzWhcNMjEwMTMwMjMzNjUzWjCCATMxRzBFBgNVBAMTPkNVQkUgTUFOQUdFTUVOVCBDT05TVUxUSU5HIEFORCBFTkdJTkVFUklORyBTT0xVVElPTlMgU0FTIERFIENWMUcwRQYDVQQpEz5DVUJFIE1BTkFHRU1FTlQgQ09OU1VMVElORyBBTkQgRU5HSU5FRVJJTkcgU09MVVRJT05TIFNBUyBERSBDVjFHMEUGA1UEChM+Q1VCRSBNQU5BR0VNRU5UIENPTlNVTFRJTkcgQU5EIEVOR0lORUVSSU5HIFNPTFVUSU9OUyBTQVMgREUgQ1YxJTAjBgNVBC0THENNQzE2MTExNEUxMCAvIE1BQ004NjA1MDRUTTkxHjAcBgNVBAUTFSAvIE1BQ004NjA1MDRIU1BSU0cwMjEPMA0GA1UECxMGVU5JREFEMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgknDHKtAwnLoFyzn/IXbpElrSsAvXwLyeMINDJr+fOALBcXUr0+UbLTiDovERwXPGNnXf7aB4wF15SW4ry7K4K1fvCunlly1m0qofD9/45W4HJTEDmsmW7fJ05Qw0Z6bI6xJoy2CWyv7NfKiJeqar/2zW7bbblzV7ihRUBULQgMgiSuH7MNTDbzbAAuqz9QZ/Yaz+eOGaDwFs6cWgj9+uI8QKXhAURLupZ1vXbEjI2lB2AUse56PcoDl1espDlVRqHGSsZtCMwsqgaYeWXT8nVPVDliLUrVxheHA4cGMql80mVjQs/Xk5LFBUkTcC5WpA0lEDN1Y6JtNHLAX6LfDcQIDAQABox0wGzAMBgNVHRMBAf8EAjAAMAsGA1UdDwQEAwIGwDANBgkqhkiG9w0BAQsFAAOCAgEAguaw+ShdSBog0Xm/6xDodq8cxWhGFbq/lVab/q6RU7BushaERRSr4IjLSB9yzGDibczKS+DOeSSnXz949uGvrc1aHUOF3ZpuRwkHM8DlzzugXoQ5+U/XQ7Gv7A3ZdcGH/L6NMwYkaFD18ZyApu+hvCbvrjucm/I3EHcrEV+xb4Anzv988iQ9vu5gmNq0aZ1JnB/1OygOaeljGMI/KQbKFDElf8+hPtwR33hsNIIJctSuKtl+s6S93+ihTN1t58AlD0N4sq4ojTJuyWvxN17dgyqJVO/uKoCc4JkvXtDnqFh72e+8bX64HYKEp0tm1Td+/mssAAslnS6F4SXaoDKLAkRQJ39FD05F9RDYSqC3LG4yZwz4eLldUr+BFMWBSEbaraRjJDtNGrqpdK7j9vGi5KRSVPeblvi1jfw0hQw2QOFu8XEt1a/qYeyy/IYk0m3xa7+Inz1zpEL7oX1g7EE4DpWo0gCBGXbP7sDxKNxO+Bcz6ZkvVJs1qFXSvpCLnIgqmfWfYLSjjj3zTwQRlakubIDmzloqzW7Ps8YtZkv/sZ2ewJVRB8mQ7NmmpjAHuYXHaR3IzPHmzQLNGu8iHfqwQjxSGsS5gl3Mb/6cYiTTqOd3GxGyK9qUwfKoB3z71FR9E383nFSUNRLV9WovGyweM8XrCVSclra1jvE+nfqtSeE=\" SubTotal=\"13938.00\" CondicionesDePago=\"30 dias credito.\" NoCertificado=\"00001000000405003077\" FormaPago=\"99\" Sello=\"EKAtacGtgElJ3vvyjEAHA0vz+mNoWqLZSamELhfQKra70qmhc4SOrPaER6WbVEdQCp7fCXoE+EMHDjFlk26VBuBGAOJFqsYu/wa43ybZheCjBxbZfs8fHc+5Lg56f0pxdrjspS2WtzCLLRI1sLPvNjZkKT9l6rJuHKk+ClGEXFMbjC0mujGttXi7NQnROZAc9tCQcKJSx9cMneyvk/QPStpFIugXH0tep/038EVaC/sw4tfBuE0jGYy5PZzEeZTJweEVlHtD0KJB0yhi7qDLlAYnYO2RisI6hdQ+5dVctaSIQeXWqmOtoYoUSQmLZT9poga0RkrIVXjmN3YJjyMlgA==\" Fecha=\"2017-07-14T10:36:02\" Version=\"3.3\" xmlns:cfdi=\"http://www.sat.gob.mx/cfd/3\">  <cfdi:Emisor Rfc=\"CMC161114E10\" Nombre=\"CUBE MANAGEMENT CONSULTING AND ENGINEERING SOLUTIONS SAS DE CV\" RegimenFiscal=\"601\" />  <cfdi:Receptor Rfc=\"LAB110228D55\" Nombre=\"LATIN AMERICA BUSINESS CONSULTING MEXICO SA DE CV\" UsoCFDI=\"P01\" />  <cfdi:Conceptos>    <cfdi:Concepto ClaveProdServ=\"81141902\" Cantidad=\"1\" ClaveUnidad=\"E48\" Unidad=\"Servicio\" Descripcion=\"Implementación Fenix II\" ValorUnitario=\"13938\" Importe=\"13938.00\">      <cfdi:Impuestos>        <cfdi:Traslados>          <cfdi:Traslado Base=\"13938.00\" Impuesto=\"002\" TipoFactor=\"Tasa\" TasaOCuota=\"0.160000\" Importe=\"2230.08\" />        </cfdi:Traslados>      </cfdi:Impuestos>    </cfdi:Concepto>  </cfdi:Conceptos>  <cfdi:Impuestos TotalImpuestosTrasladados=\"2230.08\">    <cfdi:Traslados>      <cfdi:Traslado Impuesto=\"002\" TipoFactor=\"Tasa\" TasaOCuota=\"0.160000\" Importe=\"2230.08\" />    </cfdi:Traslados>  </cfdi:Impuestos>  <cfdi:Complemento>    <tfd:TimbreFiscalDigital xmlns:tfd=\"http://www.sat.gob.mx/TimbreFiscalDigital\" xsi:schemaLocation=\"http://www.sat.gob.mx/TimbreFiscalDigital http://www.sat.gob.mx/sitio_internet/cfd/TimbreFiscalDigital/TimbreFiscalDigitalv11.xsd\" Version=\"1.1\" UUID=\"5B7BBBCA-7942-4789-BF70-AF779ED3F795\" FechaTimbrado=\"2017-07-14T10:52:26\" RfcProvCertif=\"SAT970701NN3\" SelloCFD=\"EKAtacGtgElJ3vvyjEAHA0vz+mNoWqLZSamELhfQKra70qmhc4SOrPaER6WbVEdQCp7fCXoE+EMHDjFlk26VBuBGAOJFqsYu/wa43ybZheCjBxbZfs8fHc+5Lg56f0pxdrjspS2WtzCLLRI1sLPvNjZkKT9l6rJuHKk+ClGEXFMbjC0mujGttXi7NQnROZAc9tCQcKJSx9cMneyvk/QPStpFIugXH0tep/038EVaC/sw4tfBuE0jGYy5PZzEeZTJweEVlHtD0KJB0yhi7qDLlAYnYO2RisI6hdQ+5dVctaSIQeXWqmOtoYoUSQmLZT9poga0RkrIVXjmN3YJjyMlgA==\" NoCertificadoSAT=\"00001000000403258748\" SelloSAT=\"KSsTaSRrsS1HBCTN197wCNWC8YYoX4SDNUEZSOqLPkBVxm/kDLceyUcEKJQXkOC3w/XyvAxlycw+yDvzdND5+gjfebgACTUJGcJ8TVEkQrE/OLcxvrIfWQb6K9WjNYuUnm/Z4vGcZ5mOGqGEajDX2Fz2GEjkA0xt6kkBFRrH8Db6wkl9xdmZRqOJ1Q61buNdH2I7biLBF6BzRawBFcE7pYzKlLL/w0URNy7bTp4VK1dg3uNtyZ/7gNjPTmnaFkuR9BwyJ3kRWWNm3A1oMw6Kmbi++Nu1DRzj4fH03GoXL9D1Pbb8qj6RaKNapeGAWjIIuzgVp0C3YzG0Y3ABW/Srug==\" />  </cfdi:Complemento></cfdi:Comprobante>";
	}
	
	@Test
	public void testGeneraComprobante() {
		Comprobante comprobante;
		comprobante = this.service.generaComprobante(comprobanteStr);
		assertNotNull(comprobante);
	}
	
	@Test
	public void testObtenerTimbre() {
		Comprobante comprobante;
		TimbreFiscalDigital timbre; 
		comprobante = this.service.generaComprobante(comprobanteStr);
		timbre = this.service.obtenerTimbre(comprobante);
		assertNotNull(timbre);
	}
	
	@Test
	public void testCadenaOriginalTFD() {
		Comprobante comprobante;
		TimbreFiscalDigital timbre;
		String cadena;
		comprobante = this.service.generaComprobante(comprobanteStr);
		timbre = this.service.obtenerTimbre(comprobante);
		cadena = this.service.cadenaOriginalTFD(timbre);
		assertTrue(cadena.equals("||1.1|5B7BBBCA-7942-4789-BF70-AF779ED3F795|2017-07-14T10:52:26|SAT970701NN3|EKAtacGtgElJ3vvyjEAHA0vz+mNoWqLZSamELhfQKra70qmhc4SOrPaER6WbVEdQCp7fCXoE+EMHDjFlk26VBuBGAOJFqsYu/wa43ybZheCjBxbZfs8fHc+5Lg56f0pxdrjspS2WtzCLLRI1sLPvNjZkKT9l6rJuHKk+ClGEXFMbjC0mujGttXi7NQnROZAc9tCQcKJSx9cMneyvk/QPStpFIugXH0tep/038EVaC/sw4tfBuE0jGYy5PZzEeZTJweEVlHtD0KJB0yhi7qDLlAYnYO2RisI6hdQ+5dVctaSIQeXWqmOtoYoUSQmLZT9poga0RkrIVXjmN3YJjyMlgA==|00001000000403258748||"));
	}

}
*/
