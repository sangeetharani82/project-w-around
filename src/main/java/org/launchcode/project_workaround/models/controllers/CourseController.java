package org.launchcode.project_workaround.models.controllers;

import org.launchcode.project_workaround.models.Course;
import org.launchcode.project_workaround.models.data.CourseDao;
import org.launchcode.project_workaround.models.data.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseDao courseDao;

    @Autowired
    private RecipeDao recipeDao;

    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("categories", courseDao.findAll());
        model.addAttribute("title", "Courses");
        return "course/index";
    }

    @RequestMapping(value = "add")
    public String displayAddForm(Model model){
        model.addAttribute("title", "Add Category");
        model.addAttribute("category", new Course());
        return "course/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddForm(Model model,
                                 @ModelAttribute @Valid Course course, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Category");
            return "course/add";
        }
        courseDao.save(course);
        return "redirect:";
    }
}
