package com.alea.pokeapi.aleapokedex.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Data
public class PokemonDTO implements Serializable {

        private Long id;

        private String name;

        private int height;

        private int weight;

        private int baseExperience;

    }