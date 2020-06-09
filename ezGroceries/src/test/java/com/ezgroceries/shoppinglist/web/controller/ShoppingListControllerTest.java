package com.ezgroceries.shoppinglist.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ezgroceries.shoppinglist.cocktail.resources.CocktailId;
import com.ezgroceries.shoppinglist.shoppingList.contract.ShoppingList;
import com.ezgroceries.shoppinglist.shoppingList.contract.ShoppingListResource;
import com.ezgroceries.shoppinglist.shoppingList.controllers.ShoppingListController;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(ShoppingListController.class)
@AutoConfigureDataJpa
public class ShoppingListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingListController shoppingListController;

    @Test
    public void createShoppingList() throws Exception{
        ShoppingListResource shopList = new ShoppingListResource("My List");

        given(shoppingListController.createShopList(any(ShoppingList.class)))
                .willReturn(shopList);

        mockMvc.perform(post("/shopping-lists")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(shopList)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name").value("My List"));

        verify(shoppingListController).createShopList(any(ShoppingList.class));

    }

    @Test
    public void showAllLists() throws Exception {
        List<ShoppingListResource> shopList = new ArrayList<>();
        ShoppingListResource listSteph = new ShoppingListResource("Stephanie's birthday");
        listSteph.setIngredients(Arrays.asList("Tequila", "Triple Sec", "Lime Juice", "Salt", "Blue Curacao"));
        shopList.add(listSteph);
        ShoppingListResource listTwo = new ShoppingListResource("My birthday");
        listTwo.setIngredients(Arrays.asList("Fanta", "Cola", "7up", "Lemon juice", "Blue Curacao"));
        shopList.add(listTwo);

        given(shoppingListController.getAllShoppingLists())
                .willReturn(shopList);

        mockMvc.perform(get("/shopping-lists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(shopList)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(shoppingListController).getAllShoppingLists();
    }

    @Test
    public void getOneList() throws Exception {
        ShoppingListResource listSteph = new ShoppingListResource("Stephanie's birthday");
        listSteph.setIngredients(Arrays.asList("Tequila", "Triple Sec", "Lime Juice", "Salt", "Blue Curacao"));

        given(shoppingListController.getShopList(any(UUID.class)))
                .willReturn(listSteph);

        mockMvc.perform(get("/shopping-lists/{shopListId}",UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(listSteph)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("shoppingListId").exists())
                .andExpect(jsonPath("name").value("Stephanie's birthday"))
                .andExpect(jsonPath("ingredients").exists());

        verify(shoppingListController).getShopList(any(UUID.class));
    }

    @Test
    public void addCocktailToList() throws Exception{
        List<CocktailId> cocktailIds = new ArrayList<>();
        CocktailId first = new CocktailId();
        first.setCocktailId(UUID.fromString("eb99bb7c-61f3-4c9f-981c-55b1b8ee8915"));
        cocktailIds.add(first);

        given(shoppingListController.addCocktailstoShopList(any(UUID.class),cocktailIds))
            .willReturn(cocktailIds);

        mockMvc.perform(post("/shopping-lists/{shopListId}/cocktails/",UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(cocktailIds)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("cocktailId").exists());

        verify(shoppingListController).addCocktailstoShopList(any(UUID.class),cocktailIds);
    }


    protected static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
