package paquete_raiz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	//sobre escribir metodo
	public void addResourceHandlers ( ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/logos/**").
		addResourceLocations("file:c:/empleos/img-vacantes/");
	}

}
