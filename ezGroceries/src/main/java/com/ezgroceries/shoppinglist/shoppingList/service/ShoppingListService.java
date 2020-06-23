package com.ezgroceries.shoppinglist.shoppingList.service;

import com.ezgroceries.shoppinglist.cocktail.controllers.contract.CocktailId;
import com.ezgroceries.shoppinglist.cocktail.controllers.contract.CocktailResource;
import com.ezgroceries.shoppinglist.cocktail.persistence.CocktailEntity;
import com.ezgroceries.shoppinglist.cocktail.service.CocktailService;
import com.ezgroceries.shoppinglist.shoppingList.persistence.CocktailShoppingListEntity;
import com.ezgroceries.shoppinglist.shoppingList.persistence.CocktailShoppingListRepository;
import com.ezgroceries.shoppinglist.shoppingList.persistence.ShoppingListEntity;
import com.ezgroceries.shoppinglist.shoppingList.controllers.contract.ShoppingListResource;
import com.ezgroceries.shoppinglist.shoppingList.persistence.ShoppingListRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final CocktailService cocktailService;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository, CocktailService cocktailService) {

        this.shoppingListRepository = shoppingListRepository;
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
        ShoppingListEntity shoppingListEntity = shoppingListRepository.findByShoppingListId(shoppingListId);
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

    public List<CocktailId> addCocktailsToShoppingList (UUID shoppingListId, List<CocktailId> cocktailIds) {
        ShoppingListEntity shoppingListEntity = shoppingListRepository.findByShoppingListId(shoppingListId);
        if (shoppingListEntity != null) {
                List<CocktailId> addedCocktails = shoppingListEntity.getCocktails().stream()
                        .map(cocktail -> new CocktailId(cocktail.getCocktailId())).collect(Collectors.toList());

            for (CocktailId cocktailId : cocktailIds) {
                shoppingListEntity.addCocktail(new CocktailEntity(cocktailId.getCocktailId()));
            }
            shoppingListRepository.save(shoppingListEntity);
        }

        return shoppingListRepository.findByShoppingListId(shoppingListId).getCocktails().stream()
                .map(entity -> new CocktailId(entity.getCocktailId())).collect(Collectors.toList());
    }


}
