package com.ezgroceries.shoppinglist.shoppingList.controllers;

import com.ezgroceries.shoppinglist.cocktail.resources.CocktailId;
import com.ezgroceries.shoppinglist.shoppingList.contract.ShoppingList;
import com.ezgroceries.shoppinglist.shoppingList.contract.ShoppingListResource;
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
@RequestMapping(produces = "application/json")
public class ShoppingListController {

    @PostMapping(value="/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResource createShopList(@RequestBody ShoppingList shoppingList) {
        ShoppingListResource newShopList = newShopList(shoppingList.getName());

        return newShopList;
    }

    @PostMapping(value = "/shopping-lists/{shopListId}/cocktails")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CocktailId> addCocktailstoShopList(@PathVariable("shopListId") UUID shopListId, @RequestBody List<CocktailId> cocktailIds){
        return cocktailIds;
    }

    @GetMapping(value = "/shopping-lists/{shopListId}")
    public ShoppingList getShopList(@PathVariable("shopListId") UUID shopListID) {

        return getDummyShopList(shopListID);

    }

    @GetMapping(value="/shopping-lists")
    public List<ShoppingListResource> getAllShoppingLists() {

        return getAllDummyShoppingLists();

    }

    public ShoppingListResource newShopList(String name){
        return new ShoppingListResource(name);
    }

    public ShoppingListResource getDummyShopList(UUID shoppingListId){
        ShoppingListResource oneShopList = new ShoppingListResource("Stephanie's birthday");
        oneShopList.setIngredients(Arrays.asList("Tequila", "Triple Sec", "Lime Juice", "Salt", "Cola Zero"));
        return oneShopList;


    }

    public List<ShoppingListResource> getAllDummyShoppingLists(){
        List<ShoppingListResource> shoppingList = new ArrayList<>();
        ShoppingListResource shopList1 = new ShoppingListResource("List 1");
        shopList1.setIngredients(Arrays.asList("Tequila", "Triple Sec", "Lime Juice", "Salt", "Blue Curacao"));
        shoppingList.add(shopList1);

        ShoppingListResource shopList2 = new ShoppingListResource("List 2");
        shopList2.setIngredients(Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"));
        shoppingList.add(shopList2);

        return shoppingList;
    }

}
