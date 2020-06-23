package com.ezgroceries.shoppinglist.cocktail.service;

import com.ezgroceries.shoppinglist.cocktail.service.external.CocktailDBResponse.DrinkResource;
import com.ezgroceries.shoppinglist.cocktail.persistence.CocktailRepository;
import com.ezgroceries.shoppinglist.cocktail.service.external.CocktailDBClient;
import com.ezgroceries.shoppinglist.cocktail.service.external.CocktailDBResponse;
import com.ezgroceries.shoppinglist.cocktail.persistence.CocktailEntity;
import com.ezgroceries.shoppinglist.cocktail.controllers.contract.CocktailResource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CocktailService {

    private CocktailDBClient cocktailDBClient;
    private CocktailRepository cocktailRepository;

    @Autowired
    public CocktailService(CocktailDBClient cocktailDBClient, CocktailRepository cocktailRepository) {
        this.cocktailDBClient = cocktailDBClient;
        this.cocktailRepository = cocktailRepository;
    }

    public List<CocktailResource> getCocktails(String search) {
        return mergeCocktails(cocktailDBClient.searchCocktails(search).getDrinks());

    }

    public CocktailResource getCocktailById(String cocktailId) {
        CocktailEntity cocktailEntity = cocktailRepository.getOne(UUID.fromString(cocktailId));
        CocktailDBResponse cocktailResponse = cocktailDBClient.searchCocktails(cocktailEntity.getDrinkId());
        DrinkResource drink = cocktailResponse.getDrinks().get(0);
        CocktailResource cocktailResource = new CocktailResource(cocktailEntity.getCocktailId(), cocktailEntity.getName(),drink.getStrGlass(),
                drink.getStrInstructions(),drink.getStrDrinkThumb(),drink.getIngredients());
        return cocktailResource;

    }

    public List<CocktailResource> mergeCocktails(List<CocktailDBResponse.DrinkResource> drinks) {
        //Get all the idDrink attributes
        List<String> ids = drinks.stream().map(CocktailDBResponse.DrinkResource::getIdDrink).collect(Collectors.toList());

        //Get all the ones we already have from our DB, use a Map for convenient lookup
        Map<String, CocktailEntity> existingEntityMap = cocktailRepository.findByIdDrinkIn(ids).stream().collect(Collectors.toMap(CocktailEntity::getDrinkId, o -> o, (o, o2) -> o));

        //Stream over all the drinks, map them to the existing ones, persist a new one if not existing
        Map<String, CocktailEntity> allEntityMap = drinks.stream().map(drinkResource -> {
            CocktailEntity cocktailEntity = existingEntityMap.get(drinkResource.getIdDrink());
            if (cocktailEntity == null) {
                CocktailEntity newCocktailEntity = new CocktailEntity();
                newCocktailEntity.setCocktailId(UUID.randomUUID());
                newCocktailEntity.setDrinkId(drinkResource.getIdDrink());
                newCocktailEntity.setName(drinkResource.getStrDrink());
                cocktailEntity = cocktailRepository.save(newCocktailEntity);
            }
            return cocktailEntity;
        }).collect(Collectors.toMap(CocktailEntity::getDrinkId, o -> o, (o, o2) -> o));

        //Merge drinks and our entities, transform to CocktailResource instances
        return mergeAndTransform(drinks, allEntityMap);
    }

    private List<CocktailResource> mergeAndTransform(List<CocktailDBResponse.DrinkResource> drinks, Map<String, CocktailEntity> allEntityMap) {
        return drinks.stream().map(drinkResource -> new CocktailResource(allEntityMap.get(drinkResource.getIdDrink()).getCocktailId(), drinkResource.getStrDrink(), drinkResource.getStrGlass(),
                drinkResource.getStrInstructions(), drinkResource.getStrDrinkThumb(), drinkResource.getIngredients())).collect(Collectors.toList());
    }

    public List<CocktailEntity> getAllById(List<String> cocktails) {
        return cocktailRepository.findAllById(cocktails.stream().map(UUID::fromString).collect(Collectors.toList()));
    }

}
