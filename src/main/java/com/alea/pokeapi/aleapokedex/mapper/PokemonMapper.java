package com.alea.pokeapi.aleapokedex.mapper;


import com.alea.pokeapi.aleapokedex.dto.PokemonDTO;
import com.alea.pokeapi.aleapokedex.entity.Pokemon;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PokemonMapper {

    List<PokemonDTO> asPokemonDTOList(List<Pokemon> pokemonList);
    List<Pokemon> asPokemonList(List<PokemonDTO> pokemonList);

}
