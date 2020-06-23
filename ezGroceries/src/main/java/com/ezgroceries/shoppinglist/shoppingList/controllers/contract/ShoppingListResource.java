package com.ezgroceries.shoppinglist.shoppingList.controllers.contract;

import java.util.UUID;

public class ShoppingListResource extends ShoppingList {

    private UUID shoppingListId;

    public ShoppingListResource(String name) {
        super(name);
        this.shoppingListId = UUID.randomUUID();
    }

    public ShoppingListResource(UUID shoppingListId, String name) {
        super(name);
        this.shoppingListId = shoppingListId;
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

}
