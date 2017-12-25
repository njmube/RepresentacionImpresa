package com.cubetech.representacion.impresa.interfaces.restclient.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.cubetech.representacion.impresa.AppProperties;
import com.cubetech.representacion.impresa.application.error.ConexionException;
import com.cubetech.representacion.impresa.interfaces.dto.ArchivoRepDTO;
import com.cubetech.representacion.impresa.interfaces.interceptor.RequestLoggingInterceptor;
import com.cubetech.representacion.impresa.interfaces.restclient.ArchivoRepository;


@Repository
public class ArchivoRepositoryImpl implements ArchivoRepository {
	
	private final static Logger logger = LoggerFactory.getLogger(ArchivoRepositoryImpl.class);
	private static final String ARCHIVO = "/Archivo";
	
	private AppProperties properties;
  private RestTemplate rest;

  @Autowired
  public ArchivoRepositoryImpl(AppProperties properties) {
    this.rest = new RestTemplate(getClientHttpRequestFactory());
    List<ClientHttpRequestInterceptor> tmp = new ArrayList<ClientHttpRequestInterceptor>();
    tmp.add(new RequestLoggingInterceptor());
    rest.setInterceptors(tmp);
    
    this.properties = properties;
  }
  
	@Override
	public ArchivoRepDTO findbyCuentaCorrelacion(String cuenta, String correlation) throws ConexionException{
		ArchivoRepDTO ret = null;
		final String url = properties.getArchivourl() + ARCHIVO + "/" + correlation;
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).build();
		HttpHeaders headers = ArchivoRepositoryImpl.HedersJson();
		ResponseEntity<ArchivoRepDTO> response;
		
		headers.set("cuenta", cuenta);
		HttpEntity<ArchivoRepDTO> request = new  HttpEntity<>(headers);
		try{
			response = rest.exchange(uriComponents.toUriString(), HttpMethod.GET, request, ArchivoRepDTO.class);
		}catch(RestClientException e){
			logger.error("Consulta de Archivo URL {}", url ,e);
			throw new ConexionException("Error de conexion al consultar los Archivos ");
		}
		
		if(response.getStatusCode() == HttpStatus.ACCEPTED || response.getStatusCode() == HttpStatus.OK){
			ret = response.getBody();
		}else{
			logger.error("No fue posible hacer la consulta de los archivos Url: {} CodigoRestpuetsa: {}", url, response.getStatusCode());
			throw new ConexionException("Error de conexion al consultar los Archivos ");
		}
		
		return ret;
	}

	public static HttpHeaders HedersJson(){
		HttpHeaders headers =  new HttpHeaders();
		
		headers.add("Content-Type", "application/json");
    headers.add("Accept", "*/*");
    headers.add("Authorization","Basic dXNlcjo1RFtqWjRSXyRyOHdZLlNM");
		return headers;
	}
	
	private BufferingClientHttpRequestFactory getClientHttpRequestFactory() {
    int timeout = 5000;
    
    SimpleClientHttpRequestFactory clientHttpRequestFactory
      = new SimpleClientHttpRequestFactory();
    clientHttpRequestFactory.setConnectTimeout(timeout);
    clientHttpRequestFactory.setReadTimeout(timeout * 2);
    return new BufferingClientHttpRequestFactory(clientHttpRequestFactory);
	}
	
	
	@Override
	public List<ArchivoRepDTO> save(String cuenta, List<ArchivoRepDTO> archivo)  throws ConexionException{
		final String url = properties.getArchivourl() + ARCHIVO;
		ArchivoRepDTO[] tmp = archivo.toArray( new ArchivoRepDTO[0]);
		ArchivoRepDTO[] respuesta;
		List<ArchivoRepDTO> ret;
		ResponseEntity<ArchivoRepDTO[]> response;
		HttpHeaders headers = ArchivoRepositoryImpl.HedersJson();
		headers.set("cuenta", cuenta);
		
		HttpEntity<ArchivoRepDTO[]> request = new  HttpEntity<>(tmp, headers);
		
		try{
			response = rest.exchange(url, HttpMethod.POST, request, ArchivoRepDTO[].class);
		}catch(RestClientException e){
			logger.error("Guardar de Archivos URL {}", url ,e);
			throw new ConexionException("Error de conexion al almacenar los archivos");
		}
			
		if(response.getStatusCode() == HttpStatus.OK){
			respuesta = response.getBody();
			ret = Arrays.asList(respuesta);
		}else{
			logger.error("No fue posible guardar los archivos Url: {} CodigoRestpuetsa: {}", url, response.getStatusCode());
			throw new ConexionException("Error al almacenar los archivos ");
		}
		
		return ret;
	}

}
