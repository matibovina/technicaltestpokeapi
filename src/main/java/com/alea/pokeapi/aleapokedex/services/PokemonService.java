package com.alea.pokeapi.aleapokedex.services;

import com.alea.pokeapi.aleapokedex.entity.Pokemon;

import java.util.List;

public interface PokemonService {

    public List<Pokemon> getAll();

    public List<Pokemon> getFiveHeaviest();

    public List<Pokemon> getFiveHighest();

    public List<Pokemon> getFiveExperienced();


}
