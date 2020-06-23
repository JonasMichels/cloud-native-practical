package com.ezgroceries.shoppinglist.shoppingList.controllers;

import com.ezgroceries.shoppinglist.cocktail.controllers.contract.CocktailId;
import com.ezgroceries.shoppinglist.cocktail.controllers.contract.CocktailResource;
import com.ezgroceries.shoppinglist.shoppingList.controllers.contract.ShoppingList;
import com.ezgroceries.shoppinglist.shoppingList.controllers.contract.ShoppingListResource;
import com.ezgroceries.shoppinglist.shoppingList.service.ShoppingListService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ShoppingListService shoppingListService;

    @PostMapping(value="/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResource createShopList(@RequestBody ShoppingList shoppingList) {
        ShoppingListResource newShopList = new ShoppingListResource(shoppingList.getName());

        return shoppingListService.newShopList(newShopList);
    }

    @PostMapping(value = "/shopping-lists/{shopListId}/cocktails")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CocktailId> addCocktailstoShopList(@PathVariable("shopListId") UUID shopListId, @RequestBody List<CocktailId> cocktailIds){
        return shoppingListService.addCocktailsToShoppingList(shopListId, cocktailIds);
    }

    @GetMapping(value = "/shopping-lists/{shopListId}")
    public ShoppingListResource getShopList(@PathVariable("shopListId") UUID shopListID) {

        return shoppingListService.getShoppingList(shopListID);

    }

    @GetMapping(value="/shopping-lists")
    public List<ShoppingListResource> getAllShoppingLists() {

        return shoppingListService.getAllShoppingLists();

    }


}
