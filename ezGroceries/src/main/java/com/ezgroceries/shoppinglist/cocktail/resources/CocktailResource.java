package com.ezgroceries.shoppinglist.cocktail.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CocktailResource extends Cocktail{
    private UUID cocktailId;

    public CocktailResource(UUID cocktailId, String name, String glass, String recipe, String imageUri, List<String> ingredients) {
        super(name,glass, recipe, imageUri, ingredients);
        this.cocktailId = cocktailId;
    }

    public CocktailResource(CocktailDBResponse.DrinkResource drinkResource) {
        super(drinkResource.getStrDrink(), drinkResource.getStrGlass(), drinkResource.getStrInstructions(),drinkResource.getStrDrinkThumb(),
                Arrays.asList(drinkResource.getStrIngredient1(),drinkResource.getStrIngredient2(),drinkResource.getStrIngredient3(),drinkResource.getStrIngredient4(),
                        drinkResource.getStrIngredient5(),drinkResource.getStrIngredient6(),drinkResource.getStrIngredient7(),drinkResource.getStrIngredient8(),
                        drinkResource.getStrIngredient9(),drinkResource.getStrIngredient10(),drinkResource.getStrIngredient11(),drinkResource.getStrIngredient12(),
                        drinkResource.getStrIngredient13(),drinkResource.getStrIngredient14(),drinkResource.getStrIngredient15()));
        this.cocktailId = UUID.randomUUID();
    }

    public String getCocktailId() {
        return cocktailId.toString();
    }

    public void setCocktailId(String cocktailId) {
        this.cocktailId = UUID.fromString(cocktailId);
    }
}


