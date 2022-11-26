package com.alea.pokeapi.aleapokedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@ComponentScan({"com.delivery.request"})
public class AleaPokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(AleaPokedexApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
