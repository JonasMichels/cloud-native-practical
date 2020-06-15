package com.ezgroceries.shoppinglist.cocktail.contract;

import com.ezgroceries.shoppinglist.util.StringSetConverter;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COCKTAIL")
public class CocktailEntity {

    @Id
    @Column(name="ID")
    private UUID cocktailId;

    @Column(name = "ID_DRINK")
    private String idDrink;

    @Column(name = "NAME")
    private String cocktailName;

    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    public UUID getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(UUID cocktailId) {
        this.cocktailId = cocktailId;
    }

    public String getDrinkId() {
        return idDrink;
    }

    public void setDrinkId(String idDrink) {
        this.idDrink = idDrink;
    }

    public String getName() {
        return cocktailName;
    }

    public void setName(String name) {
        this.cocktailName = name;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }
}
