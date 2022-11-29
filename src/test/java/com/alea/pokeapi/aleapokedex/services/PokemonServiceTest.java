package com.alea.pokeapi.aleapokedex.services;

import com.alea.pokeapi.aleapokedex.dto.PokemonDTO;
import com.alea.pokeapi.aleapokedex.dto.PokemonRestResponseDTO;
import com.alea.pokeapi.aleapokedex.dto.PokemonResultRestResponseDTO;
import com.alea.pokeapi.aleapokedex.entity.Pokemon;
import com.alea.pokeapi.aleapokedex.mapper.PokemonMapper;
import com.alea.pokeapi.aleapokedex.repository.PokemonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class PokemonServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Mock
    private PokemonMapper pokemonMapper;

    @Mock
    private PokemonRepository pokemonRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private PokemonDTO getPokemonDTO() {
        final PokemonDTO pokemonDTO = new PokemonDTO();
        pokemonDTO.setHeight(123);
        pokemonDTO.setWeight(12);
        pokemonDTO.setBaseExperience(234);
        pokemonDTO.setName("Leo");
        pokemonDTO.setId(1L);
        return pokemonDTO;
    }

    @Test
    void retrieveHeaviestTest() {

        Pokemon pokemon = new Pokemon();
        pokemon.setHeight(123);
        pokemon.setWeight(12);
        pokemon.setBaseExperience(234);
        pokemon.setName("Leo");
        pokemon.setId(1L);

        Mockito.when(pokemonRepository.findByOrderByWeightDesc(Mockito.any())).thenReturn(Arrays.asList(pokemon));
        Mockito.when(pokemonMapper.asPokemonDTOList(any())).thenReturn(Arrays.asList(getPokemonDTO()));

        List<PokemonDTO> response = pokemonService.findHeaviest(5);

        assertNotNull(response);
        Assertions.assertEquals(12, response.get(0).getWeight());
    }

    @Test
    void retrieveHighestTest() {

        Pokemon pokemon = new Pokemon();
        pokemon.setHeight(123);
        pokemon.setWeight(123);
        pokemon.setBaseExperience(234);
        pokemon.setName("Leo");
        pokemon.setId(1L);

        Mockito.when(pokemonRepository.findByOrderByHeightDesc(Mockito.any())).thenReturn(Arrays.asList(pokemon));
        Mockito.when(pokemonMapper.asPokemonDTOList(any())).thenReturn(Arrays.asList(getPokemonDTO()));

        List<PokemonDTO> response = pokemonService.findHighest(1);

        assertNotNull(response);
        Assertions.assertEquals(123, response.get(0).getHeight());
    }

    @Test
    void retrieveExperiencedTest() {

        //Preparar objeto mockeado
        Pokemon pokemon = new Pokemon();
        pokemon.setHeight(123);
        pokemon.setWeight(12);
        pokemon.setBaseExperience(234);
        pokemon.setName("Leo");
        pokemon.setId(1L);

        Mockito.when(pokemonRepository.findByOrderByBaseExperienceDesc(Mockito.any())).thenReturn(Arrays.asList(pokemon));
        Mockito.when(pokemonMapper.asPokemonDTOList(any())).thenReturn(Arrays.asList(getPokemonDTO()));

        List<PokemonDTO> response = pokemonService.findExperienced(5);
        assertNotNull(response);
        Assertions.assertEquals(234, response.get(0).getBaseExperience());


    }


    @Test
    void saveAllTest() {


        final PokemonRestResponseDTO pokemonRestResponseDTO = getPokemonRestResponseDTO();
        final PokemonDTO pokemonDTO = getPokemonDTO();

        final ResponseEntity<PokemonRestResponseDTO> restResponse = new ResponseEntity<>(
                pokemonRestResponseDTO,
                HttpStatus.OK);
        final ResponseEntity<PokemonDTO> responseEntity2 = new ResponseEntity<>(
                pokemonDTO, HttpStatus.OK
        );

        String url = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=5000";

        Mockito.when(restTemplate.getForEntity(url, PokemonRestResponseDTO.class)).thenReturn(restResponse);
        Mockito.when(restTemplate.getForEntity(restResponse.getBody().getResults().get(0).getUrl(), PokemonDTO.class)).thenReturn(responseEntity2);


        List<PokemonDTO> response = pokemonService.saveAll();
        assertNotNull(response);

        Mockito.verify(pokemonRepository, times(1)).saveAll(any());

    }

    @Test
    void findAllTest(){

        //Preparar objeto mockeado
        Pokemon pokemon = new Pokemon();
        pokemon.setHeight(123);
        pokemon.setWeight(12);
        pokemon.setBaseExperience(234);
        pokemon.setName("Leo");
        pokemon.setId(1L);

        Mockito.when(pokemonRepository.findAll()).thenReturn(Arrays.asList(pokemon));
        Mockito.when(pokemonMapper.asPokemonDTOList(any())).thenReturn(Arrays.asList(getPokemonDTO()));

        List<PokemonDTO> response = pokemonService.findAll();
        //Asserts
        assertNotNull(response);
        Assertions.assertEquals(pokemon.getWeight(), response.get(0).getWeight());
        Assertions.assertEquals(pokemon.getName(), response.get(0).getName());
        Assertions.assertEquals(pokemon.getHeight(), response.get(0).getHeight());
        Assertions.assertEquals(pokemon.getBaseExperience(), response.get(0).getBaseExperience());

    }

    @Test
    void countTest() {
        Long response = 100L;
        Long expected = 100L;
        Mockito.when(pokemonService.countRows()).thenReturn(response);
        assertNotNull(pokemonService.countRows());
        assertEquals(expected, response);

    }


    private PokemonRestResponseDTO getPokemonRestResponseDTO() {

        final PokemonResultRestResponseDTO pokemonResultRestResponseDTO = new PokemonResultRestResponseDTO();
        pokemonResultRestResponseDTO.setName("Leo");
        pokemonResultRestResponseDTO.setUrl("www.perez.com");

        final PokemonRestResponseDTO pokemonRestResponseDTO = new PokemonRestResponseDTO();
        pokemonRestResponseDTO.setCount(1);
        pokemonRestResponseDTO.setPrevious("");
        pokemonRestResponseDTO.setNext("");
        pokemonRestResponseDTO.setResults(
                Arrays.asList(pokemonResultRestResponseDTO)
        );
        return pokemonRestResponseDTO;
    }


}