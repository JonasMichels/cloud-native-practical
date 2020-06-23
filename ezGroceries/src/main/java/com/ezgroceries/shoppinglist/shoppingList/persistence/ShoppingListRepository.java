package com.ezgroceries.shoppinglist.shoppingList.persistence;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.Repository;


public interface ShoppingListRepository extends Repository<ShoppingListEntity, UUID> {
    ShoppingListEntity save(ShoppingListEntity shoppingListEntity);

    ShoppingListEntity findByEntityId(UUID shoppingListId);

    List<ShoppingListEntity> findAll();

}
