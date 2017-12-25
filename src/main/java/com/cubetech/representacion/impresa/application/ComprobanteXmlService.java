package com.cubetech.representacion.impresa.application;

import com.cubetech.representacion.impresa.application.xml.comprobante.Comprobante;
import com.cubetech.representacion.impresa.application.xml.timbre.TimbreFiscalDigital;

public interface ComprobanteXmlService {
	public Comprobante generaComprobante(String xmlComprobante);
	public TimbreFiscalDigital obtenerTimbre(Comprobante comprobante);
	public String cadenaOriginalTFD(TimbreFiscalDigital timbre);
}
