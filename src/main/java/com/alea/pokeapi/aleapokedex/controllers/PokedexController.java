package com.alea.pokeapi.aleapokedex.controllers;

import com.alea.pokeapi.aleapokedex.entity.Pokemon;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(value = "/test/")
    public Pokemon[] getAll() {

        ResponseEntity<Pokemon[]> response =
                restTemplate.getForEntity("https://pokeapi.co/api/v2/pokemon/?offset=0&limit=5000",
                        Pokemon[].class);
        //String url = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=5000";
        Pokemon[] pokemones = response.getBody();
       // List<Pokemon> pokemones = new ArrayList<>();
        //Object forObjects = Collections.restTemplate.getForObject(url, Object.class);

        return pokemones;
    }

    @GetMapping(value = "/test/pokemon/{id}")
    public Pokemon testApi(@PathVariable int id) {

         String url = "https://pokeapi.co/api/v2/pokemon/" + id;
         Pokemon pokemones = new Pokemon();
         pokemones = restTemplate.getForObject(url, Pokemon.class);
        return pokemones;
    }


}
