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

    @PostMapping("/save-new")
    @ResponseStatus(HttpStatus.CREATED)
    public Pokemon saveNewPokemon(@RequestBody Pokemon pokemon) {
        return pokemonService.saveNew(pokemon);
    }

    @DeleteMapping("/delete-pokemon/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePokemon(@PathVariable Long id) {
        pokemonService.delete(id);
    }
}
