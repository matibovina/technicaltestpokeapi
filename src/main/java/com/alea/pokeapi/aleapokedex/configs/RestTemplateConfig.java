package com.alea.pokeapi.aleapokedex.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    private final RestTemplate restTemplate;

    public RestTemplateConfig(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


}
