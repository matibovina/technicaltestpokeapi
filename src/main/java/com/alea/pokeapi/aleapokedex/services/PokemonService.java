package com.alea.pokeapi.aleapokedex.services;

import com.alea.pokeapi.aleapokedex.dto.PokemonDTO;

import java.util.List;

public interface PokemonService {

    public List<PokemonDTO> findAll();

    public List<PokemonDTO> findHeaviest(Integer numResults);

    public List<PokemonDTO> findHighest(Integer numResults);

    public List<PokemonDTO> findExperienced(Integer numResults);

    public long countRows();

}
