package co.com.avanzada.pokemonAPI.services;

import co.com.avanzada.pokemonAPI.models.Pokemon;
import java.util.List;

public interface IPokemonService {
    public List<Pokemon> retrieveAll();
    public Pokemon saveNew(Pokemon pokemon);
    public void delete(Long id);
}
