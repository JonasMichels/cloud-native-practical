package com.ezgroceries.shoppinglist.shoppingList.controllers;

import com.ezgroceries.shoppinglist.cocktail.resources.CocktailId;
import com.ezgroceries.shoppinglist.shoppingList.resources.ShoppingListResource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value = "/shopping-lists")
public class ShoppingListController {

    @PostMapping(value="/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResource createShopList(@RequestBody String name) {
        ShoppingListResource newShopList = newShopList(name);

        return newShopList;
    }

    @PostMapping(value = "/shopping-lists/{shopListId}/cocktails")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CocktailId> addCocktailstoShopList(@PathVariable("shopListId") UUID shopListId,@RequestBody ArrayList<CocktailId> cocktailId){
        return cocktailId;
    }

    @GetMapping(value = "/shopping-lists/{shopListId}")
    public ShoppingListResource getShopList(@PathVariable("shopListId") UUID shopListID) {

        return getDummyShopList(shopListID);

    }

    @GetMapping(value="/shopping-lists")
    public List<ShoppingListResource> getAllShoppingLists() {

        return getAllDummyShoppingLists();

    }

    public ShoppingListResource newShopList(String name){
        ShoppingListResource shoppingList = new ShoppingListResource();
        shoppingList.setShoppingListId(UUID.fromString("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"));
        shoppingList.setName(name);

        return shoppingList;
    }

    public ShoppingListResource getDummyShopList(UUID shoppingListId){
        return new ShoppingListResource(
                shoppingListId,
                "Stephanie's birthday",
                Arrays.asList("Tequila", "Triple Sec", "Lime Juice", "Salt", "Blue Curacao"));
    }

    public List<ShoppingListResource> getAllDummyShoppingLists(){
        return Arrays.asList(new ShoppingListResource(
                UUID.fromString("4ba92a46-1d1b-4e52-8e38-13cd56c7224c"),
                "Stephanie's birthday",
                Arrays.asList("Tequila", "Triple Sec", "Lime Juice", "Salt", "Blue Curacao")),
                new ShoppingListResource(UUID.fromString("6c7d09c2-8a25-4d54-a979-25ae779d2465"),"My birthday",
                        Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao")));
    }

}
