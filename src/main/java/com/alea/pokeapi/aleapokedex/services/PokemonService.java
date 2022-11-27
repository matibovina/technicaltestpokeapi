package com.alea.pokeapi.aleapokedex.services;

import com.alea.pokeapi.aleapokedex.entity.Pokemon;

import java.util.List;

public interface PokemonService {

    public List<Pokemon> retrieveAll();

    public List<Pokemon> retrieveHeaviest( Integer numResults);

    public List<Pokemon> retrieveHighest(int numResults);

    public List<Pokemon> retrieveExperienced(int numResults);


}
