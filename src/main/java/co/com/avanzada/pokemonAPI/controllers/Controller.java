package co.com.avanzada.pokemonAPI.controllers;

import co.com.avanzada.pokemonAPI.models.Pokemon;
import co.com.avanzada.pokemonAPI.services.IPokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemon")
public class Controller {

    @Autowired
    private IPokemonService pokemonService;

    @GetMapping("/retrieve-all")
    public List<Pokemon> getPokemones() {
        return pokemonService.retrieveAll();
    }

    @GetMapping("/retrieve-especific/{id}")
    public Pokemon getPokemonById(@PathVariable Long id) {
        return pokemonService.retrieveById(id);
    }

    @PostMapping("/save-pokemon")
    @ResponseStatus(HttpStatus.CREATED)
    public Pokemon saveNewPokemon(@RequestBody Pokemon pokemon) {
        return pokemonService.savePokemon(pokemon);
    }

    @DeleteMapping("/delete-pokemon/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePokemon(@PathVariable Long id) {
        pokemonService.delete(id);
    }

    @PutMapping("/update-pokemon/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Pokemon updatePokemon(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        Pokemon pokemon_update = pokemonService.retrieveById(id);
        pokemon_update.setName(pokemon.getName());
        pokemon_update.setCategory(pokemon.getCategory());
        pokemon_update.setDescription(pokemon.getDescription());
        return pokemonService.savePokemon(pokemon_update);
    }
}
