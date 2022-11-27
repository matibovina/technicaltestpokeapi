package com.alea.pokeapi.aleapokedex.services;

import com.alea.pokeapi.aleapokedex.dto.PokemonDTO;
import com.alea.pokeapi.aleapokedex.dto.PokemonRestResponseDTO;
import com.alea.pokeapi.aleapokedex.dto.PokemonResultRestResponseDTO;
import com.alea.pokeapi.aleapokedex.entity.Pokemon;
import com.alea.pokeapi.aleapokedex.repository.PokemonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService{

  //  private Logger log = LoggerFactory.getLogger(PokemonServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PokemonRepository pokemonRepository;

    private String url = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=5000";

    @Override
    public List<Pokemon> retrieveAll() {
        PokemonRestResponseDTO response = new PokemonRestResponseDTO();

            response = restTemplate.getForEntity(url, PokemonRestResponseDTO.class).getBody();

        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList = response.getResults().parallelStream().map(pokemonResultRestResponseDTO -> restTemplate.getForEntity(
                pokemonResultRestResponseDTO.getUrl(), Pokemon.class).getBody()).collect(Collectors.toList());

        pokemonRepository.saveAll(pokemonList);
        return pokemonList;
    }



    @Override
    public List<Pokemon> retrieveHeaviest(Integer numResults) {
        //1- llamar a repositorio para recuperar lista de pokemones (find all order by height)
        //2- recorrer la lista de vuelta con stream filter u order
           return pokemonRepository.findByWeigth(PageRequest.of(0, numResults));
    }

    @Override
    public List<Pokemon> retrieveHighest(int numResults) {
        return pokemonRepository.findByHeight(PageRequest.of(0, numResults));
    }

    @Override
    public List<Pokemon> retrieveExperienced(int numResults) {
        return pokemonRepository.findByBaseExperience(PageRequest.of(0, numResults));
    }
}
