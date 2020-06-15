package com.ezgroceries.shoppinglist.shoppingList.service;

import com.ezgroceries.shoppinglist.cocktail.contract.CocktailResource;
import com.ezgroceries.shoppinglist.cocktail.service.CocktailService;
import com.ezgroceries.shoppinglist.cocktailsInShoppingList.contract.CocktailShoppingListEntity;
import com.ezgroceries.shoppinglist.cocktailsInShoppingList.repository.CocktailShoppingListRepository;
import com.ezgroceries.shoppinglist.shoppingList.contract.ShoppingListEntity;
import com.ezgroceries.shoppinglist.shoppingList.contract.ShoppingListResource;
import com.ezgroceries.shoppinglist.shoppingList.repository.ShoppingListRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final CocktailService cocktailService;
    private final CocktailShoppingListRepository cocktailShoppingListRepository;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository, CocktailShoppingListRepository cocktailShoppingListRepository, CocktailService cocktailService) {

        this.shoppingListRepository = shoppingListRepository;
        this.cocktailShoppingListRepository = cocktailShoppingListRepository;
        this.cocktailService = cocktailService;

    }

    public ShoppingListResource newShopList(ShoppingListResource shoppingListResource){
        ShoppingListEntity shopList = new ShoppingListEntity();
        shopList.setShoppingListId(shoppingListResource.getShoppingListId());
        shopList.setShoppingListName(shoppingListResource.getName());
        shoppingListRepository.save(shopList);
        return shoppingListResource;
    }

    public ShoppingListResource getShoppingList(UUID shoppingListId){
        ShoppingListEntity shoppingListEntity = shoppingListRepository.getOne(shoppingListId);
        ShoppingListResource shoppingListResource = new ShoppingListResource(shoppingListEntity.getShoppingListName());
        shoppingListResource.setShoppingListId(shoppingListEntity.getShoppingListId());
        return shoppingListResource;
    }

    public List<ShoppingListResource> getAllShoppingLists() {
        List<ShoppingListEntity> shoppingListEntity = shoppingListRepository.findAll();
        List<ShoppingListResource> shoppingLists = new ArrayList<>();
        for(ShoppingListEntity shopListEntity : shoppingListEntity) {
            shoppingLists.add(new ShoppingListResource(shopListEntity.getShoppingListId(), shopListEntity.getShoppingListName()));
        }
        return shoppingLists;
    }

    public List<CocktailResource> addcocktailsToShoppinglist(ShoppingListResource shoppingListResource, List<String> cocktailIds) {
        List<CocktailResource> cocktails = new ArrayList<>();
        for (String cocktailId : cocktailIds) {
            cocktails.add(addOneCocktailToShoppingList(shoppingListResource, cocktailId));
        }

        return cocktails;
    }

    public CocktailResource addOneCocktailToShoppingList(ShoppingListResource shoppingListResource, String cocktailId){
        CocktailResource cocktailResource = cocktailService.getCocktailById(cocktailId);
        CocktailShoppingListEntity cocktailShoppingListEntity = new CocktailShoppingListEntity();
        cocktailShoppingListEntity.setCocktailId(UUID.fromString(cocktailResource.getCocktailId()));
        cocktailShoppingListEntity.setShoppingListId(shoppingListResource.getShoppingListId());
        cocktailShoppingListRepository.save(cocktailShoppingListEntity);
        return cocktailResource;
    }

}
