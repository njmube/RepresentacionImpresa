package com.cubetech.representacion.impresa;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("app")
@Data
public class AppProperties {
	private String catalogourl;
	private String emisorurl;
	private String archivourl;
	private String cuentaFiles;
}

