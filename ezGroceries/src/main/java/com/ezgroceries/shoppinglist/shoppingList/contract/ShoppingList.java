package com.ezgroceries.shoppinglist.shoppingList.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class ShoppingList {

    private String name;
    private List<String> ingredients;


    public ShoppingList(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

}

