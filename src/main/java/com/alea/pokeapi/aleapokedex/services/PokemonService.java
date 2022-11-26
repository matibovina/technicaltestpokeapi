package com.alea.pokeapi.aleapokedex.services;

import com.alea.pokeapi.aleapokedex.dto.PokemonResultRestResponseDTO;
import com.alea.pokeapi.aleapokedex.entity.Pokemon;

import java.util.List;

public interface PokemonService {

    public List<PokemonResultRestResponseDTO> retrieveAll();

    public List<Pokemon> retrieveHeaviest();

    public List<Pokemon> retrieveHighest();

    public List<Pokemon> retrieveExperienced();


}
