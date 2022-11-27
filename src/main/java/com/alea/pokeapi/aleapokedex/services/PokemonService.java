package com.alea.pokeapi.aleapokedex.services;

import com.alea.pokeapi.aleapokedex.dto.PokemonDTO;
import com.alea.pokeapi.aleapokedex.entity.Pokemon;

import java.util.List;

public interface PokemonService {

    public List<PokemonDTO> retrieveAll();

    public List<PokemonDTO> retrieveHeaviest( Integer numResults);

    public List<PokemonDTO> retrieveHighest(int numResults);

    public List<PokemonDTO> retrieveExperienced(int numResults);


}
