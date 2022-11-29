package com.alea.pokeapi.aleapokedex.repository;

import com.alea.pokeapi.aleapokedex.dto.PokemonDTO;
import com.alea.pokeapi.aleapokedex.entity.Pokemon;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    public List<Pokemon> findByOrderByWeightDesc(PageRequest of);

    public List<Pokemon> findByOrderByHeightDesc(PageRequest of);

    public List<Pokemon> findByOrderByBaseExperienceDesc(PageRequest of);


}
