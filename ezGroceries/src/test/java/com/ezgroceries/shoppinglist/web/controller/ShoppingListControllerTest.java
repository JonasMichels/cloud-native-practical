package com.ezgroceries.shoppinglist.web.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ezgroceries.shoppinglist.shoppingList.controllers.contract.ShoppingListResource;
import com.ezgroceries.shoppinglist.shoppingList.controllers.ShoppingListController;
import com.ezgroceries.shoppinglist.shoppingList.service.ShoppingListService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(ShoppingListController.class)
@AutoConfigureMockMvc
public class ShoppingListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingListService shoppingListService;

    @Test
    public void createShoppingList() throws Exception{
        ShoppingListResource shoppingListResource = new ShoppingListResource("my list");
        shoppingListResource.setShoppingListId(UUID.randomUUID());

        given(shoppingListService.newShopList(any(ShoppingListResource.class)))
                .willReturn(shoppingListResource);

        this.mockMvc.perform(post("/shopping-lists")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(shoppingListResource)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.shoppingListId").exists());

        verify(shoppingListService).newShopList(any(ShoppingListResource.class));

    }

    /*@Test
    public void showAllLists() throws Exception {
         mockMvc.perform(get("/shopping-lists")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].shoppingListId").exists())
            .andExpect(jsonPath("$[0].name").exists())
            .andExpect(jsonPath("$[0].ingredients").exists())
            .andExpect(jsonPath("$[0].ingredients").isArray());

    }*/

    @Test
    public void getOneList() throws Exception {
        ShoppingListResource shoppingListResource = new ShoppingListResource("my list");
        shoppingListResource.setShoppingListId(UUID.fromString("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"));

        given(shoppingListService.getShoppingList(any(UUID.class)))
                .willReturn(shoppingListResource);

        this.mockMvc.perform(get("/shopping-lists/eb18bb7c-61f3-4c9f-981c-55b1b8ee8915")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.shoppingListId").exists())
                .andExpect(jsonPath("$.name").exists());

        verify(shoppingListService).getShoppingList(any(UUID.class));

    }
/*
    @Test
    public void addCocktailToList() throws Exception{
        this.mockMvc.perform(post("/shopping-lists/97c8e5bd-5353-426e-b57b-69eb2260ace3/cocktails")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[\n"
                        + "  {\n"
                        + "    \"cocktailId\": \"23b3d85a-3928-41c0-a533-6538a71e17c4\"\n"
                        + "  },\n"
                        + "  {\n"
                        + "    \"cocktailId\": \"d615ec78-fe93-467b-8d26-5d26d8eab073\"\n"
                        + "  }\n"
                        + "]"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].cocktailId").exists());
    }
*/

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
