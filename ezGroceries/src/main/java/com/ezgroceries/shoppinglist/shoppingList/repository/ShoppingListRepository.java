package com.ezgroceries.shoppinglist.shoppingList.repository;

import com.ezgroceries.shoppinglist.shoppingList.contract.ShoppingListEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, UUID> {

}
