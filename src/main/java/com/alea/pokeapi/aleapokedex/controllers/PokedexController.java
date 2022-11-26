package com.alea.pokeapi.aleapokedex.controllers;

import com.alea.pokeapi.aleapokedex.entity.Pokemon;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class PokedexController {

    private final RestTemplate restTemplate;

    @Autowired
    public PokedexController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping(value = "/test")
    public List<Pokemon> testApi() {
        String url = "https://pokeapi.co/api/v2/pokemon/1/";
        List<Pokemon> pokemones = new ArrayList<>();
         pokemones = Collections.singletonList(restTemplate.getForObject(url, Pokemon.class));
         pokemones.stream();
        return pokemones;
    }


}
