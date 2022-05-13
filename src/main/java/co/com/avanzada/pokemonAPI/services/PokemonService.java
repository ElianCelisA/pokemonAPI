package co.com.avanzada.pokemonAPI.services;

import co.com.avanzada.pokemonAPI.dao.IPokemonDAO;
import co.com.avanzada.pokemonAPI.models.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PokemonService implements IPokemonService {

    @Autowired
    private IPokemonDAO pokemonDao;

    @Override
    public List<Pokemon> retrieveAll() {
        return pokemonDao.findAll();
    }

    @Override
    public Pokemon saveNew(Pokemon pokemon) {
        return pokemonDao.save(pokemon);
    }

    @Override
    public void delete(Long id) {
        pokemonDao.deleteById(id);
    }
}
