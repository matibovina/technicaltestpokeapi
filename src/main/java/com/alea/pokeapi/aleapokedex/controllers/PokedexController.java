package com.alea.pokeapi.aleapokedex.controllers;

import com.alea.pokeapi.aleapokedex.dto.PokemonDTO;
import com.alea.pokeapi.aleapokedex.mapper.PokemonMapper;
import com.alea.pokeapi.aleapokedex.services.PokemonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class PokedexController {

    @Autowired
    private PokemonServiceImpl pokemonService;

    private final PokemonMapper pokemonMapper;

    public PokedexController(PokemonMapper pokemonMapper) {
        this.pokemonMapper = pokemonMapper;
    }

    @GetMapping(value = "/update-data-base/")
    public ResponseEntity<?> updateDataBase() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<PokemonDTO> pokemonDTOList = pokemonService.findAll();

        } catch (Exception e) {
            response.put("Message", e.getMessage().concat(": ".concat(e.getLocalizedMessage())));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        response.put("Success: ", "All the pokemons were saved in the Pokedex");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/heaviest")
    public ResponseEntity<?> showHeaviestsPokemons(@RequestParam(required = false) Integer num) {

        Map<String, Object> response = new HashMap<>();
        List<PokemonDTO> pokemonListDTO = new ArrayList<>();


        if (pokemonService.countRows() == 0) {
            pokemonService.findAll();
        }
        if (num == null) {
            num = 5;
        }
        try {
            pokemonListDTO = pokemonService.findHeaviest(num);
        } catch (Exception e) {
            response.put("Message", e.getMessage().concat(": ".concat(e.getLocalizedMessage())));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        response.put("message", "Here are the " + num + " heaviest");
        response.put("List: ", pokemonListDTO);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

    @GetMapping(value = "/highest")
    public ResponseEntity<?> showHighestPokemons(@RequestParam(required = false) Integer num) {


        Map<String, Object> response = new HashMap<>();
        List<PokemonDTO> pokemonListDTO = new ArrayList<>();

        if (num == null) {
            num = 5;
        }
        if (pokemonService.countRows() == 0) {
            pokemonService.findAll();
        }
        try {
            pokemonListDTO = pokemonService.findHighest(num);
        } catch (Exception e) {
            response.put("Message", e.getMessage().concat(": ".concat(e.getLocalizedMessage())));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("List: ", pokemonListDTO);
        response.put("message", "Here are the " + num + " highest");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/experienced")
    public ResponseEntity<?> showExperiencedPokemons(@RequestParam(required = false) Integer num) {

        Map<String, Object> response = new HashMap<>();
        List<PokemonDTO> pokemonListDTO = new ArrayList<>();

        if (num == null) {
            num = 5;
        }
        if (pokemonService.countRows() == 0) {
            pokemonService.findAll();
        }
        try {
            pokemonListDTO = pokemonService.findExperienced(num);
        } catch (Exception e) {
            response.put("Message", e.getMessage().concat(": ".concat(e.getLocalizedMessage())));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("message", "Here are the " + num + " most experienced");
        response.put("List: ", pokemonListDTO);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
