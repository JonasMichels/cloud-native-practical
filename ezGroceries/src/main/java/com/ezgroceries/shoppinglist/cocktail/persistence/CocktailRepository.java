package com.ezgroceries.shoppinglist.cocktail.persistence;

import com.ezgroceries.shoppinglist.cocktail.persistence.CocktailEntity;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepository extends JpaRepository<CocktailEntity, UUID> {
    Set<CocktailEntity> findByIdDrinkIn(List<String> cocktailIds);
}
