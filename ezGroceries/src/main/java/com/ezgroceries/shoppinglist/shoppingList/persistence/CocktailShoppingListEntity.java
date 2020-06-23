package com.ezgroceries.shoppinglist.shoppingList.persistence;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "COCKTAIL_SHOPPING_LIST")
@IdClass(CocktailShoppingListEntity.class)
public class CocktailShoppingListEntity implements Serializable {

    @Id
    @Column(name = "COCKTAIL_ID")
    private UUID cocktailId;

    @Id
    @Column(name = "SHOPPING_LIST_ID")
    private UUID shoppingListId;

    public UUID getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(UUID cocktailId) {
        this.cocktailId = cocktailId;
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

}
