package com.ezgroceries.shoppinglist.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ezgroceries.shoppinglist.external.CocktailDBResponse.DrinkResource;
import com.ezgroceries.shoppinglist.cocktail.contract.CocktailResource;
import com.ezgroceries.shoppinglist.cocktail.service.CocktailService;
import com.ezgroceries.shoppinglist.shoppingList.controllers.ShoppingListController;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ShoppingListController.class)
@AutoConfigureDataJpa
public class CocktailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CocktailService cocktailService;

    @Test
    public void getCocktails() throws Exception {
        String searchany = "All";
        List<CocktailResource> cocktails = new ArrayList<>();
        DrinkResource drinkResource = new DrinkResource();
        drinkResource.setIdDrink("23b3d85a-3928-41c0-a533-6538a71e17c4");
        drinkResource.setStrDrink("margarita");
        drinkResource.setStrGlass("Cocktail Glass");
        drinkResource.setStrDrinkThumb("https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg");
        drinkResource.setStrInstructions("Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..");
        drinkResource.setStrIngredient1("Tequila");
        drinkResource.setStrIngredient2("Blue Curacao");
        drinkResource.setStrIngredient3("Lemon Juice");
        drinkResource.setStrIngredient4("Salt");
        CocktailResource cocktail1 = new CocktailResource(drinkResource);
        cocktails.add(cocktail1);
        DrinkResource drinkResource2 = new DrinkResource();
        drinkResource2.setIdDrink("d615ec78-fe93-467b-8d26-5d26d8eab073");
        drinkResource2.setStrDrink( "Blue Margerita");
        drinkResource2.setStrGlass("Cocktail glass");
        drinkResource2.setStrInstructions("Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..");
        drinkResource2.setStrDrinkThumb("https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg");
        drinkResource2.setStrIngredient1("Tequila");
        drinkResource2.setStrIngredient2("Blue Curacao");
        drinkResource2.setStrIngredient3("Lime juice");
        drinkResource2.setStrIngredient4("Salt");
        CocktailResource cocktail2 = new CocktailResource(drinkResource2);
        cocktails.add(cocktail2);

        given(cocktailService.getCocktails(any(String.class)))
                .willReturn(cocktails);


        mockMvc.perform(get("/cocktails")
                .contentType(MediaType.APPLICATION_JSON)
                .param("search",searchany))
        .andDo(print())
        .andExpect(status().isOk());

        verify(cocktailService).getCocktails(any(String.class));
    }

}
