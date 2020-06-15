package com.ezgroceries.shoppinglist.cocktailsInShoppingList.repository;

import com.ezgroceries.shoppinglist.cocktailsInShoppingList.contract.CocktailShoppingListEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailShoppingListRepository extends JpaRepository<CocktailShoppingListEntity, UUID> {

}
