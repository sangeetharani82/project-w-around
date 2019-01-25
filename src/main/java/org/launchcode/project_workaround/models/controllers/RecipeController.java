package org.launchcode.project_workaround.models.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.launchcode.project_workaround.models.*;
import org.launchcode.project_workaround.models.data.CategoryDao;
import org.launchcode.project_workaround.models.data.CourseDao;
import org.launchcode.project_workaround.models.data.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("recipe")
public class RecipeController {

    @Autowired
    RecipeDao recipeDao;

    @Autowired
    CourseDao courseDao;

    @Autowired
    CategoryDao categoryDao;

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
        model.addAttribute("courses", courseDao.findAll());
        model.addAttribute("categories", categoryDao.findAll());
        return "recipe/add";
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddRecipeForm(Model model, @ModelAttribute @Valid Recipe newRecipe,
                                       Errors errors, @RequestParam int courseId, @RequestParam int categoryId) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a recipe");
            model.addAttribute("courses", courseDao.findAll());
            model.addAttribute("categories", categoryDao.findAll());
            return "recipe/add";
        }

        Course cor = courseDao.findOne(courseId);
        Category cat = categoryDao.findOne(categoryId);
        newRecipe.setCourseName(cor);
        newRecipe.setCategoryName(cat);
        recipeDao.save(newRecipe);
        return "redirect:";
    }

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

    @RequestMapping(value="edit/{recipeId}", method = RequestMethod.GET)
    public String displayEditRecipeForm(Model model, @PathVariable int recipeId){
        model.addAttribute(recipeDao.findOne(recipeId));
        model.addAttribute("title", "Edit Recipe");
        model.addAttribute("courses", courseDao.findAll());
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("name", recipeDao.findOne(recipeId).getRecipeName());
        return "recipe/edit";
    }

    @RequestMapping(value = "edit/{recipeId}", method = RequestMethod.POST)
    public String processEditForm(@PathVariable int recipeId, @RequestParam String recipeName,
                                  @RequestParam int courseId, @RequestParam int categoryId,
                                  @RequestParam int servingSize, @RequestParam String prepTime,
                                  @RequestParam String cookTime, @RequestParam String ingredient,
                                  @RequestParam String direction){
        Recipe edited = recipeDao.findOne(recipeId);
        edited.setRecipeName(recipeName);
        edited.setServingSize(servingSize);
        edited.setPrepTime(prepTime);
        edited.setCookTime(cookTime);
        edited.setIngredient(ingredient);
        edited.setDirection(direction);

        Course cor = courseDao.findOne(courseId);
        edited.setCourseName(cor);

        Category cat = categoryDao.findOne(categoryId);
        edited.setCategoryName(cat);
        recipeDao.save(edited);
        return "redirect:/recipe";
    }


    //view-single-recipe
    @RequestMapping(value="single", method = RequestMethod.GET)
    public String viewSingleRecipe(Model model, @RequestParam int id){
        model.addAttribute("recipes", recipeDao.findOne(id));
        return "recipe/singleRecipe";
    }
    //view all courses
    @RequestMapping(value="allCourses", method = RequestMethod.GET)
    public String displayAllCourse(Model model){
        model.addAttribute("title", "List of Courses available");
        model.addAttribute("courses", courseDao.findAll());
        return "recipe/viewCourse";
    }

    //view all categories
    @RequestMapping(value="allCategories", method = RequestMethod.GET)
    public String displayAllCategory(Model model){
        model.addAttribute("title", "List of Category available");
        model.addAttribute("categories", categoryDao.findAll());
        return "recipe/viewCategory";
    }

    //recipes in a course
    @RequestMapping(value = "course", method = RequestMethod.GET)
    public String course(Model model, @RequestParam int id){
        Course cor = courseDao.findOne(id);
        List<Recipe> recipes = cor.getRecipes();
        model.addAttribute("recipes", recipes);
        model.addAttribute("title", "Recipes in Course "+ cor.getCourseName());
        return "recipe/index";
    }

    @RequestMapping(value = "category", method = RequestMethod.GET)
    public String category(Model model, @RequestParam int id){
        Category cat = categoryDao.findOne(id);
        List<Recipe> recipes = cat.getRecipes();
        model.addAttribute("recipes", recipes);
        model.addAttribute("title", "Recipes in Category" + cat.getCategoryName());
        return "recipe/index";
    }
}
