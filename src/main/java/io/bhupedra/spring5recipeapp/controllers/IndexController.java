package io.bhupedra.spring5recipeapp.controllers;

import io.bhupedra.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndex(Model model) {
        log.debug("calling recipe controller");
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
