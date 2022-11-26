package com.alea.pokeapi.aleapokedex.entity;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Pokemon {

    private Long id;

    private String name;

    private int height;

    private int weight;

    private int order;

    private int baseExperience;

}
