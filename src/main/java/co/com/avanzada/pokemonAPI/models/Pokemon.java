package co.com.avanzada.pokemonAPI.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pokemon")
public class Pokemon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String category;

    @Column
    private Double height;

    @Column
    private Double weight;

    @Column
    private String description;

    @JoinTable(
            name = "pokemon_type",
            joinColumns = @JoinColumn(name = "fk_pokemon", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "fk_type", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Type> type;

    @JoinTable(
            name = "pokemon_ability",
            joinColumns = @JoinColumn(name = "fk_pokemon", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "fk_ability", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Ability> abilities;

    @JoinTable(
            name = "pokemon_weakness",
            joinColumns = @JoinColumn(name = "fk_pokemon", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "fk_weakness", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Weakness> weaknesses;

    @Column
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Type> getType() {
        return type;
    }

    public void setType(List<Type> type) {
        this.type = type;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public List<Weakness> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<Weakness> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public void addType(Type type){
        if(this.type == null){
            this.type = new ArrayList<>();
        }
        this.type.add(type);
    }

    public void addAbility(Ability ability){
        if(this.abilities == null){
            this.abilities = new ArrayList<>();
        }
        this.abilities.add(ability);
    }

    public void addWeakness(Weakness weakness){
        if(this.weaknesses == null){
            this.weaknesses = new ArrayList<>();
        }
        this.weaknesses.add(weakness);
    }
}
