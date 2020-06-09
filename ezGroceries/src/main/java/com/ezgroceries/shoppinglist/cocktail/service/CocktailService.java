package com.ezgroceries.shoppinglist.cocktail.service;

import com.ezgroceries.shoppinglist.cocktail.resources.CocktailDBClient;
import com.ezgroceries.shoppinglist.cocktail.resources.CocktailDBResponse;
import com.ezgroceries.shoppinglist.cocktail.resources.CocktailResource;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CocktailService {

    @Autowired
    private CocktailDBClient cocktailDBClient;

    //private CocktailDBResponse cocktailDBResponse;

    public List<CocktailResource> getCocktails(String search) {
        CocktailDBResponse cocktailResponse = cocktailDBClient.searchCocktails(search);
         List<CocktailResource> cocktails = new ArrayList<>();
        for (CocktailDBResponse.DrinkResource drink : cocktailResponse.getDrinks()){
            cocktails.add(new CocktailResource(drink));
        }

        return cocktails;
    }

}
