package application.controller.admin;

import application.data.model.Category;
import application.data.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/category")
    public String listCategory(Model model,
           @Param("keyword") String keyword,
           @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
           @RequestParam(name = "size", required = false, defaultValue = "8") Integer size) {
        Pageable pageable = new PageRequest(page, size);
        Page<Category> categoryList = categoryService.getListAll(keyword, pageable);
        long totalItems = categoryList.getTotalElements();
        int totalPages = categoryList.getTotalPages();
        int number = categoryList.getNumber();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("number", number);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("categorys", categoryList);
        return "admin/category/category";
    }

    @RequestMapping("/add-category")
    public String addCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "admin/category/add";
    }

    @RequestMapping("/edit-category/{id}")
    public ModelAndView editCategory( @PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("admin/category/edit");
        Category category = categoryService.findOne(id);
        mav.addObject("category", category);
        return mav;
    }

    @PostMapping("/save-category")
    public String addCategory(@ModelAttribute("category") Category category) {
        category.setCreatedDate(new Date());
        categoryService.saveCategory(category);
        return "redirect:/admin/category";
    }

    @RequestMapping("/delete-category/{id}")
    public String delelteCategory(@PathVariable("id") int id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/category";
    }

}
