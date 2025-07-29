package com.lsda.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        
        // Configurar timeouts
        factory.setConnectTimeout(10000);  // 10 segundos para conectar
        factory.setReadTimeout(30000);     // 30 segundos para leer respuesta
        
        return new RestTemplate(factory);
    }
}