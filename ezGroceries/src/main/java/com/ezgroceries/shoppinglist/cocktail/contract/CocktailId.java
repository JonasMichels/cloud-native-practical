package com.ezgroceries.shoppinglist.cocktail.contract;

import java.util.UUID;

public class CocktailId {

    private UUID cocktailId;

    public CocktailId(String cocktailId) {
        this.cocktailId = UUID.fromString(cocktailId);
    }

    public UUID getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(UUID cocktailId) {
        this.cocktailId = cocktailId;
    }
}
