package com.alea.pokeapi.aleapokedex.entity;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "pokemon")
@Entity
public class Pokemon {

    @Id
    private Long id;

    private String name;

    private int height;

    private int weight;

    @Column(name = "base_experience")
    private int base_experience;

}
