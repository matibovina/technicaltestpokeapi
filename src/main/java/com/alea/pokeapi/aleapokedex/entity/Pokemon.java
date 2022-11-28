package com.alea.pokeapi.aleapokedex.entity;


import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Pokemon {

    @Id
    private Long id;

    private String name;

    private Integer height;

    private Integer weight;

    private Integer baseExperience;

}
