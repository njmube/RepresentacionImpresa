package com.cubetech.representacion.impresa;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AppConfig extends WebMvcConfigurerAdapter{

	@Bean
	public JAXBContext jaxbContext(){
		try {
			return JAXBContext.newInstance("com.cubetech.representacion.impresa.application.xml.comprobante:com.cubetech.representacion.impresa.application.xml.timbre");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@Bean(name="templateCadenaTimbre")
	public Templates templatesComprobante(){
		Source xslt;
		Templates template = null;
		try {
			xslt = new StreamSource(new ClassPathResource("xslt/cadenaoriginal_TFD_1_1.xslt").getInputStream());
			TransformerFactory tf = TransformerFactory.newInstance();
			try {
				template = tf.newTemplates(xslt);
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return template;
	}
	
}
