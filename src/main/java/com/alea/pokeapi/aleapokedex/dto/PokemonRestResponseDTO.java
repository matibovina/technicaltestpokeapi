package com.alea.pokeapi.aleapokedex.dto;


import lombok.Data;

import java.util.List;

@Data
public class PokemonRestResponseDTO {

    private Integer count;

    private String previous;

    private String next;

    private List<PokemonResultRestResponseDTO> results;


}
