package com.alea.pokeapi.aleapokedex.controllers;

import com.alea.pokeapi.aleapokedex.entity.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PokedexController {

    private final RestTemplate restTemplate;

    @Autowired
    public PokedexController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/test/")
    public List<String> getAll() {

        ResponseEntity<List<Pokemon>> response =
                restTemplate.exchange("https://pokeapi.co/api/v2/pokemon/?offset=0&limit=5000",
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Pokemon>>() {}
                        );
        //String url = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=5000";
        List<Pokemon> pokemones = response.getBody();
       // List<Pokemon> pokemones = new ArrayList<>();
        //Object forObjects = Collections.restTemplate.getForObject(url, Object.class);

        return pokemones.stream().map(Pokemon::getName).collect(Collectors.toList());
    }

    @GetMapping(value = "/test/pokemon/{id}")
    public Pokemon testApi(@PathVariable int id) {

         String url = "https://pokeapi.co/api/v2/pokemon/" + id;
         Pokemon pokemones = new Pokemon();
         pokemones = restTemplate.getForObject(url, Pokemon.class);
        return pokemones;
    }


}
