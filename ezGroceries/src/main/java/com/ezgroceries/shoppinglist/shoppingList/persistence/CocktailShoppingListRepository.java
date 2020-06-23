package com.ezgroceries.shoppinglist.shoppingList.persistence;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailShoppingListRepository extends JpaRepository<CocktailShoppingListEntity, UUID> {

}
