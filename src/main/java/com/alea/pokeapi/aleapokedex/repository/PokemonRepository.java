package com.alea.pokeapi.aleapokedex.repository;

import com.alea.pokeapi.aleapokedex.entity.Pokemon;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    @Query("select p from Pokemon p order by p.weight desc")
    public List<Pokemon> findByWeigth(PageRequest of);

  @Query("select p from Pokemon p order by p.height desc")
    public List<Pokemon> findByHeight(PageRequest of);

    @Query("select p from Pokemon p order by p.baseExperience desc")
    public List<Pokemon> findByBaseExperience(PageRequest of);

}
