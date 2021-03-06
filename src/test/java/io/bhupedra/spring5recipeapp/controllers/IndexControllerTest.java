package io.bhupedra.spring5recipeapp.controllers;

import io.bhupedra.spring5recipeapp.domain.Recipe;
import io.bhupedra.spring5recipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void mockMVcTest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndex() {
        //---given---
        Set<Recipe> recipeSet = new HashSet<>();

        Recipe r1 = new Recipe();
        r1.setId(1L);
        recipeSet.add(r1);

        Recipe r2 = new Recipe();
        r1.setId(2L);
        recipeSet.add(r2);

        when(recipeService.getRecipes()).thenReturn(recipeSet);

        ArgumentCaptor<Set<Recipe>> setArgumentCaptor = ArgumentCaptor.forClass(Set.class);

        //---when---
        String viewname =indexController.getIndex(model);

        //---then---

        assertEquals("index", viewname );

        //verifying how many times recipeService.getRecipe() is called
        verify(recipeService, times(1)).getRecipes();

        //verifying how many times model.addAttribute() is called
        verify(model, times(1)).addAttribute(eq("recipes"),setArgumentCaptor.capture());

        Set<Recipe> recipes = setArgumentCaptor.getValue();
        assertEquals(2, recipes.size());
    }
}