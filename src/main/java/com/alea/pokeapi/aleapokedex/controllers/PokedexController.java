package com.alea.pokeapi.aleapokedex.controllers;

import com.alea.pokeapi.aleapokedex.dto.PokemonDTO;
import com.alea.pokeapi.aleapokedex.dto.PokemonResultRestResponseDTO;
import com.alea.pokeapi.aleapokedex.entity.Pokemon;
import com.alea.pokeapi.aleapokedex.mapper.PokemonMapper;
import com.alea.pokeapi.aleapokedex.services.PokemonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PokedexController {

    private final RestTemplate restTemplate;

    @Autowired
    private PokemonServiceImpl pokemonService;


    private final PokemonMapper pokemonMapper;

    public PokedexController(final RestTemplate restTemplate, PokemonMapper pokemonMapper) {
        this.restTemplate = restTemplate;
        this.pokemonMapper = pokemonMapper;
    }

    @GetMapping(value = "/update-data-base/")
    public ResponseEntity<List<PokemonResultRestResponseDTO>> updateDataBase() {
        List<PokemonDTO> pokemonDTOList = new ArrayList<>();
        List<PokemonResultRestResponseDTO> pokemonList = pokemonService.retrieveAll();
        return ResponseEntity.ok(pokemonList);
    }

    @GetMapping(value = "/test/pokemon/{id}")
    public Pokemon testApi(@PathVariable int id) {

         String url = "https://pokeapi.co/api/v2/pokemon/" + id;
         Pokemon pokemon;
         pokemon = restTemplate.getForObject(url, Pokemon.class);
        return pokemon;
    }
}
