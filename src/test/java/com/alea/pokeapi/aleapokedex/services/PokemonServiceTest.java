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
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PokemonServiceTest {

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
        this.restTemplate = Mockito.mock(RestTemplate.class);
    }
/*

    @Test
    void retrieveAllTest() {



        final PokemonRestResponseDTO pokemonRestResponseDTO = getPokemonRestResponseDTO();
        final PokemonDTO pokemonDTO = getPokemonDTO();

        final ResponseEntity<PokemonRestResponseDTO> restResponse = new ResponseEntity<>(
                pokemonRestResponseDTO,
                HttpStatus.OK);
        final ResponseEntity<PokemonDTO> responseEntity2 = new ResponseEntity<>(
                pokemonDTO, HttpStatus.OK
        );

        String url = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=5000";

        Mockito.when(this.restTemplate.getForEntity(url, PokemonRestResponseDTO.class)).thenReturn(restResponse);
       Mockito.when(restTemplate.getForEntity(url , PokemonDTO.class)).thenReturn(responseEntity2);
       //   Mockito.when(this.restTemplate.getForEntity(url, PokemonRestResponseDTO.class).getBody()).thenReturn(pokemonRestResponseDTO);


        List<PokemonDTO> response = pokemonService.retrieveAll();
        assertNotNull(response);

        verificar cuantas veces se llama a saveAll
                Mockito.verify(pokemonRepository, times(1)).saveAll(any())

    }
*/
    private PokemonDTO getPokemonDTO() {
        final PokemonDTO pokemonDTO = new PokemonDTO();
        pokemonDTO.setHeight(123);
        pokemonDTO.setWeight(12);
        pokemonDTO.setBaseExperience(234);
        pokemonDTO.setName("Leo");
        pokemonDTO.setId(1L);
        return pokemonDTO;
    }

    private PokemonRestResponseDTO getPokemonRestResponseDTO() {

        final PokemonResultRestResponseDTO pokemonResultRestResponseDTO= new PokemonResultRestResponseDTO();
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

    @Test
    void retrieveHeaviestTest() {

        //Preparar objeto mockeado
        Pokemon pokemon = new Pokemon();
        pokemon.setHeight(123);
        pokemon.setWeight(12);
        pokemon.setBaseExperience(234);
        pokemon.setName("Leo");
        pokemon.setId(1L);

        //times(1).verify(asd);
        //Mockito.verify(pokemonRepository, times(1)).saveAll()
        //AVERIGUAR FACTORY o UTILS para tener los objetos disponibles para los tests

        //Mockear las llamadas externas (llamadas a repositorio, llamadas a servicios externos, mappers)
        Mockito.when(pokemonRepository.findByWeigth(Mockito.any())).thenReturn(Arrays.asList(pokemon));
        Mockito.when(pokemonMapper.asPokemonDTOList(any())).thenReturn(Arrays.asList(getPokemonDTO()));
        //llamada al servicio que se esta probando(Service.retireveHeaviest)
        List<PokemonDTO> response = pokemonService.findHeaviest(5);
        //Asserts
        assertNotNull(response);
        Assertions.assertEquals(123, response.get(0).getHeight());
    }

    @Test
    void retrieveHighestTest() {
    }

    @Test
    void retrieveExperiencedTest() {
    }
}