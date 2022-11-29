package com.alea.pokeapi.aleapokedex.services;

import com.alea.pokeapi.aleapokedex.dto.PokemonDTO;
import com.alea.pokeapi.aleapokedex.dto.PokemonRestResponseDTO;
import com.alea.pokeapi.aleapokedex.mapper.PokemonMapper;
import com.alea.pokeapi.aleapokedex.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PokemonMapper pokemonMapper;
    @Autowired
    private PokemonRepository pokemonRepository;



    @Override
    public List<PokemonDTO> saveAll() {

        String url = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=5000";
        List<PokemonDTO> pokemonListDTO = new ArrayList<>();

        try {
            PokemonRestResponseDTO response = restTemplate.getForEntity(url, PokemonRestResponseDTO.class).getBody();
            pokemonListDTO = response.getResults().parallelStream().map(pokemonResultRestResponseDTO -> restTemplate.getForEntity(
                    pokemonResultRestResponseDTO.getUrl(), PokemonDTO.class).getBody()).collect(Collectors.toList());

            pokemonRepository.saveAll(pokemonMapper.asPokemonList(pokemonListDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pokemonListDTO;
    }

    @Override
    public List<PokemonDTO> findHeaviest(Integer numResults) {
        return pokemonMapper.asPokemonDTOList(pokemonRepository.findByOrderByWeightDesc(PageRequest.of(0, numResults)));
    }

    @Override
    public List<PokemonDTO> findHighest(Integer numResults) {
        return pokemonMapper.asPokemonDTOList(pokemonRepository.findByOrderByHeightDesc(PageRequest.of(0, numResults)));
    }

    @Override
    public List<PokemonDTO> findExperienced(Integer numResults) {
        return pokemonMapper.asPokemonDTOList(pokemonRepository.findByOrderByBaseExperienceDesc(PageRequest.of(0, numResults)));
    }

    @Override
    public long countRows() {
        return pokemonRepository.count();
    }

    @Override
    public List<PokemonDTO> findAll() {
        return pokemonMapper.asPokemonDTOList(pokemonRepository.findAll());
    }
}
