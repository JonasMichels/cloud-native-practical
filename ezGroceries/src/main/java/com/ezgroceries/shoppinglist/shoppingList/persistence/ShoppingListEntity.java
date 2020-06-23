package com.ezgroceries.shoppinglist.shoppingList.persistence;

import com.ezgroceries.shoppinglist.cocktail.persistence.CocktailEntity;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SHOPPING_LIST")
public class ShoppingListEntity {

    @Id
    @Column(name = "ID")
    private UUID shoppingListId;

    @Column(name = "NAME")
    private String shoppingListName;

    @OneToMany
    @JoinTable(
            name = "COCKTAIL_SHOPPING_LIST",
            joinColumns = @JoinColumn(name = "SHOPPING_LIST_ID"),
            inverseJoinColumns = @JoinColumn(name = "COCKTAIL_ID"))
    List<CocktailEntity> cocktails;

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getShoppingListName() {
        return shoppingListName;
    }

    public void setShoppingListName(String shoppingListName) {
        this.shoppingListName = shoppingListName;
    }

    public List<CocktailEntity> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<CocktailEntity> cocktails) {
        this.cocktails = cocktails;
    }

    public void addCocktail(CocktailEntity cocktail) {
        this.cocktails.add(cocktail);
    }
}
