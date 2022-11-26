package com.alea.pokeapi.aleapokedex.services;

import com.alea.pokeapi.aleapokedex.dto.PokemonDTO;
import com.alea.pokeapi.aleapokedex.dto.PokemonRestResponseDTO;
import com.alea.pokeapi.aleapokedex.dto.PokemonResultRestResponseDTO;
import com.alea.pokeapi.aleapokedex.entity.Pokemon;
import com.alea.pokeapi.aleapokedex.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PokemonRepository pokemonRepository;

    private String url = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=5000";

    @Override
    public List<PokemonResultRestResponseDTO> retrieveAll() {
        PokemonRestResponseDTO response = new PokemonRestResponseDTO();

        response = restTemplate.getForEntity(url, PokemonRestResponseDTO.class).getBody();

        List<Pokemon> pokemonList = new ArrayList<>();
       pokemonList = response.getResults().parallelStream().map(pokemonResultRestResponseDTO -> restTemplate.getForEntity(
                pokemonResultRestResponseDTO.getUrl(), Pokemon.class).getBody()).collect(Collectors.toList());

        pokemonRepository.saveAll(pokemonList);
        return response.getResults();
    }



    @Override
    public List<Pokemon> retrieveHeaviest() {
        //1- llamar a repositorio para recuperar lista de pokemones (find all order by height)
        //2- recorrer la lista de vuelta con stream filter u order
        return null;
    }

    @Override
    public List<Pokemon> retrieveHighest() {
        return null;
    }

    @Override
    public List<Pokemon> retrieveExperienced() {
        return null;
    }
}
