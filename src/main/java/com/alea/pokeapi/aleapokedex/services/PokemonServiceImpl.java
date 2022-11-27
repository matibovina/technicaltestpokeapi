package com.alea.pokeapi.aleapokedex.services;

import com.alea.pokeapi.aleapokedex.dto.PokemonDTO;
import com.alea.pokeapi.aleapokedex.dto.PokemonRestResponseDTO;
import com.alea.pokeapi.aleapokedex.entity.Pokemon;
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
public class PokemonServiceImpl implements PokemonService{

  //  private Logger log = LoggerFactory.getLogger(PokemonServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PokemonMapper pokemonMapper;
    @Autowired
    private PokemonRepository pokemonRepository;

    private String url = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=5000";

    @Override
    public List<PokemonDTO> retrieveAll() {
        PokemonRestResponseDTO response  = restTemplate.getForEntity(url, PokemonRestResponseDTO.class).getBody();



        List<PokemonDTO> pokemonListDTO = new ArrayList<>();
        pokemonListDTO = response.getResults().parallelStream().map(pokemonResultRestResponseDTO -> restTemplate.getForEntity(
                pokemonResultRestResponseDTO.getUrl(), PokemonDTO.class).getBody()).collect(Collectors.toList());

        pokemonRepository.saveAll(pokemonMapper.asPokemonList(pokemonListDTO));

        return pokemonListDTO;
    }



    @Override
    public List<PokemonDTO> retrieveHeaviest(Integer numResults) {
        //1- llamar a repositorio para recuperar lista de pokemones (find all order by height)
        //2- recorrer la lista de vuelta con stream filter u order
        return pokemonMapper.asPokemonDTOList(pokemonRepository.findByWeigth(PageRequest.of(0, numResults)));
    }

    @Override
    public List<PokemonDTO> retrieveHighest(int numResults) {
        return pokemonMapper.asPokemonDTOList(pokemonRepository.findByHeight(PageRequest.of(0, numResults)));
    }

    @Override
    public List<PokemonDTO> retrieveExperienced(int numResults) {
        return pokemonMapper.asPokemonDTOList(pokemonRepository.findByBaseExperience(PageRequest.of(0, numResults)));
    }
}
