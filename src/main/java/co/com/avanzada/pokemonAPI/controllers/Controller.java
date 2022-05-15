package co.com.avanzada.pokemonAPI.controllers;

import co.com.avanzada.pokemonAPI.models.Pokemon;
import co.com.avanzada.pokemonAPI.services.IPokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        pokemon_update.setHeight(pokemon.getHeight());
        pokemon_update.setWeight(pokemon.getWeight());
        pokemon_update.setDescription(pokemon.getDescription());
        pokemon_update.setType(pokemon.getType());
        pokemon_update.setAbilities(pokemon.getAbilities());
        pokemon_update.setWeaknesses(pokemon.getWeaknesses());
        return pokemonService.savePokemon(pokemon_update);
    }

    @PostMapping("/upload-pokemon-image")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file, @RequestParam("id") Long id) {
        Pokemon actual = pokemonService.retrieveById(id);
        Map<String, Object> response = new HashMap<>();
        if(!file.isEmpty()) {
            String nameFile = UUID.randomUUID()+"_"+file.getOriginalFilename().replace(" ", "");
            java.nio.file.Path path = Paths.get("uploads").resolve(nameFile).toAbsolutePath();

            try {
                Files.copy(file.getInputStream(), path);
            } catch (IOException e) {
                response.put("Mensaje", "Error al subir la imagen");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String imageAnterior = actual.getImage();
            if (imageAnterior != null && imageAnterior.length() > 0) {
                java.nio.file.Path pathAnterior = Paths.get("uploads").resolve(imageAnterior).toAbsolutePath();
                File fileAnt = pathAnterior.toFile();
                fileAnt.delete();
            }

            actual.setImage(nameFile);
            pokemonService.savePokemon(actual);
            response.put("Pokémon", actual);
            response.put("Mensaje", "Ha sido subido");
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/retrieve-pokemon-image/{nameImg:.+}")
    public ResponseEntity<Resource> viewImg (@PathVariable String nameImg) {
        java.nio.file.Path path = Paths.get("uploads").resolve(nameImg).toAbsolutePath();
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
        HttpHeaders head = new HttpHeaders();
        head.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename\""+resource.getFilename());
        return new ResponseEntity<Resource>(resource, head, HttpStatus.OK);
    }
}
