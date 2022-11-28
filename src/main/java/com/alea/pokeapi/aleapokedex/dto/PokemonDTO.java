package com.alea.pokeapi.aleapokedex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;


@Data
public class PokemonDTO implements Serializable {

    private Long id;

    private String name;

    private Integer height;

    private Integer weight;

    @JsonProperty("base_experience")
    private Integer baseExperience;

}