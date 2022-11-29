package com.alea.pokeapi.aleapokedex.controllers;

import com.alea.pokeapi.aleapokedex.dto.PokemonDTO;
import com.alea.pokeapi.aleapokedex.mapper.PokemonMapper;
import com.alea.pokeapi.aleapokedex.services.PokemonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/pokedex")
public class PokedexController {

    @Autowired
    private PokemonServiceImpl pokemonService;

    private final PokemonMapper pokemonMapper;

    public PokedexController(PokemonMapper pokemonMapper) {
        this.pokemonMapper = pokemonMapper;
    }

    @GetMapping(value = "/update-data-base")
    public ResponseEntity<?> updateDataBase() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<PokemonDTO> pokemonDTOList = pokemonService.saveAll();

        } catch (Exception e) {
            response.put("Message", e.getMessage().concat(": ".concat(e.getLocalizedMessage())));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        response.put("Success: ", "All the pokemons were saved in the Pokedex");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/top-heaviest")
    public ResponseEntity<?> showHeaviestsPokemons(@RequestParam(required = false) Integer numMaxResults) {

        Map<String, Object> response = new HashMap<>();
        List<PokemonDTO> pokemonListDTO;


        if (pokemonService.countRows() == 0) {
            pokemonService.saveAll();
        }
        if (numMaxResults == null) {
            numMaxResults = 5;
        }
        try {
            pokemonListDTO = pokemonService.findHeaviest(numMaxResults);
        } catch (Exception e) {
            response.put("Message", e.getMessage().concat(": ".concat(e.getLocalizedMessage())));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        response.put("message", "Here are the " + numMaxResults + " heaviest");
        response.put("List: ", pokemonListDTO);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

    @GetMapping(value = "/top-highest")
    public ResponseEntity<?> showHighestPokemons(@RequestParam(required = false) Integer numMaxResults) {


        Map<String, Object> response = new HashMap<>();
        List<PokemonDTO> pokemonListDTO;

        if (numMaxResults == null) {
            numMaxResults = 5;
        }
        if (pokemonService.countRows() == 0) {
            pokemonService.saveAll();
        }
        try {
            pokemonListDTO = pokemonService.findHighest(numMaxResults);
        } catch (Exception e) {
            response.put("Message", e.getMessage().concat(": ".concat(e.getLocalizedMessage())));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("List: ", pokemonListDTO);
        response.put("message", "Here are the " + numMaxResults + " highest");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/top-experienced")
    public ResponseEntity<?> showExperiencedPokemons(@RequestParam(required = false) Integer numMaxResults) {

        Map<String, Object> response = new HashMap<>();
        List<PokemonDTO> pokemonListDTO;

        if (numMaxResults == null) {
            numMaxResults = 5;
        }
        if (pokemonService.countRows() == 0) {
            pokemonService.saveAll();
        }
        try {
            pokemonListDTO = pokemonService.findExperienced(numMaxResults);
        } catch (Exception e) {
            response.put("Message", e.getMessage().concat(": ".concat(e.getLocalizedMessage())));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("message", "Here are the " + numMaxResults + " most experienced");
        response.put("List: ", pokemonListDTO);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/list-all-pokemons")
    public ResponseEntity<?> findAll() {

        Map<String, Object> response = new HashMap<>();
        List<PokemonDTO> pokemonListDTO;

        if (pokemonService.countRows() == 0) {
            pokemonService.saveAll();
        }
        try {
            pokemonListDTO = pokemonService.findAll();
        } catch (Exception e) {
            response.put("Message", e.getMessage().concat(": ".concat(e.getLocalizedMessage())));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        response.put("message", "All pokemons listed");
        response.put("List: ", pokemonListDTO);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
