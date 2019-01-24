package org.launchcode.project_workaround;

import org.launchcode.project_workaround.models.*;
import org.launchcode.project_workaround.models.data.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("recipe")
public class RecipeController {

    @Autowired
    private RecipeDao recipeDao;

    // Request path: /recipe
    @RequestMapping(value="")
    public String index(Model model){

        model.addAttribute("recipes", recipeDao.findAll());
        model.addAttribute("title", "All recipes");
        return "recipe/index";
    }

    // add/create a recipe
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddRecipeForm(Model model){
        model.addAttribute("title", "Add recipes");
        model.addAttribute(new Recipe());
        model.addAttribute("recipeTypes", RecipeType.values());
        model.addAttribute("recipeCategories", RecipeCategory.values());
        return "recipe/add";
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddRecipeForm(Model model, @ModelAttribute @Valid Recipe newRecipe,
                                       Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a recipe");
            return "recipe/add";
        }
        recipeDao.save(newRecipe);
        model.addAttribute("recipes", newRecipe);
        return "redirect:";
    }

//    @RequestMapping(value="choose", method = RequestMethod.GET)
//    public String displayChooseIngAndQuantityForm(Model model){
//        model.addAttribute("title", "Choose Ingredients from the List and specify it's Quantity");
//        model.addAttribute("recipeIngredients", RecipeIngredient.values());
//        return "recipe/chooseIngredientsAndQuantity";
//    }

    //delete a recipe
    @RequestMapping(value="remove", method = RequestMethod.GET)
    public String displayRemoveRecipeForm(Model model){
        model.addAttribute("recipes", recipeDao.findAll());
        model.addAttribute("title", "Delete recipe(s)");
        return "recipe/remove";
    }

    @RequestMapping(value="remove", method = RequestMethod.POST)
    public String processRemoveRecipeForm(@RequestParam int[] recipeIds){
        for (int recipeId : recipeIds){
            recipeDao.delete(recipeId);
        }
        return "redirect:";
    }

    //view-single-recipe
    @RequestMapping(value="single", method = RequestMethod.GET)
    public String viewSingleRecipe(Model model, int id){
        model.addAttribute("recipes", recipeDao.findOne(id));
        return "recipe/singleRecipe";
    }
    //view all courses
    @RequestMapping(value="course", method = RequestMethod.GET)
    public String displayAllCourse(Model model){
        model.addAttribute("title", "List of Courses available");
        model.addAttribute("courses", RecipeType.values());
        return "recipe/viewCourse";
    }
}
