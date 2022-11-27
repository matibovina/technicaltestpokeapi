package com.alea.pokeapi.aleapokedex.controllers;

import com.alea.pokeapi.aleapokedex.dto.PokemonDTO;
import com.alea.pokeapi.aleapokedex.entity.Pokemon;
import com.alea.pokeapi.aleapokedex.mapper.PokemonMapper;
import com.alea.pokeapi.aleapokedex.services.PokemonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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
    public ResponseEntity<List<PokemonDTO>> updateDataBase() {
        List<PokemonDTO> pokemonDTOList = new ArrayList<>();
        List<Pokemon> pokemonList = pokemonService.retrieveAll();
        return ResponseEntity.ok(pokemonMapper.asPokemonDTOList(pokemonList));
    }

    @GetMapping(value = "/test/pokemon/{id}")
    public Pokemon testApi(@PathVariable int id) {

         String url = "https://pokeapi.co/api/v2/pokemon/" + id;
         Pokemon pokemon;
         pokemon = restTemplate.getForObject(url, Pokemon.class);
        return pokemon;
    }


    @GetMapping(value = "/heaviest")
    public ResponseEntity<?> showHeaviestsPokemons(@RequestParam int num) {

        Map<String, Object> response = new HashMap<>();

        List<Pokemon> pokemonList = new ArrayList<>();


      Integer numResult = 0;
        try {
            pokemonList = pokemonService.retrieveHeaviest(num);
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(pokemonList.size() == 0) {
            response.put("message", "Please, first run \"updateDataBase()\" to fill the database");
            //response.put("List: ", pokemonMapper.asPokemonDTOList(pokemonList));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
            response.put("message", "Here are the " + num+ " most heaviest");
            response.put("List: ", pokemonMapper.asPokemonDTOList(pokemonList));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

    @GetMapping(value = "/highest")
    public ResponseEntity<?> showHighestPokemons(@RequestParam int num) {

        Map<String, Object> response = new HashMap<>();

        List<Pokemon> pokemonList = new ArrayList<>();

        Integer numResult = 0;
        try {
            pokemonList = pokemonService.retrieveHighest(num);
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(pokemonList.size() == 0) {
            response.put("message", "Please, first run \"updateDataBase()\" to fill the database");
            //response.put("List: ", pokemonMapper.asPokemonDTOList(pokemonList));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("List: ", pokemonMapper.asPokemonDTOList(pokemonList));
        response.put("message", "Here are the " + num + " most highest");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

    @GetMapping(value = "/experienced")
    public ResponseEntity<?> showExperiencedPokemons(@RequestParam int num) {

        Map<String, Object> response = new HashMap<>();

        List<Pokemon> pokemonList = new ArrayList<>();

        Integer numResult = 0;
        try {
            pokemonList = pokemonService.retrieveExperienced(num);
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if(pokemonList.size() == 0) {
            response.put("message", "Please, first run \"updateDataBase()\" to fill the database");
            //response.put("List: ", pokemonMapper.asPokemonDTOList(pokemonList));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("message", "Here are the " + num + " most experienced");
        response.put("List: ", pokemonMapper.asPokemonDTOList(pokemonList));
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
