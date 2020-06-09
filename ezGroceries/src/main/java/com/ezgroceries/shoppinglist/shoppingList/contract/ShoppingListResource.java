package com.ezgroceries.shoppinglist.shoppingList.contract;

import java.util.UUID;

public class ShoppingListResource extends ShoppingList {

    private UUID shoppingListId;

    public ShoppingListResource(String name) {
        super(name);
        this.shoppingListId = UUID.randomUUID();
    }

    public String getShoppingListId() {
        return shoppingListId.toString();
    }

    public void setShoppingListId(String shoppingListId) {
        this.shoppingListId = UUID.fromString(shoppingListId);
    }

}
