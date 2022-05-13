package co.com.avanzada.pokemonAPI.dao;

import co.com.avanzada.pokemonAPI.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPokemonDAO extends JpaRepository<Pokemon, Long> {
}
