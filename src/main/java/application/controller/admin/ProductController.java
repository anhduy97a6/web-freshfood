package application.controller.admin;

import application.data.model.Category;
import application.data.model.Product;
import application.data.service.CategoryService;
import application.data.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/product")
    public String listProduct(Model model,
                              @Param("keyword") String keyword,
                              @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                              @RequestParam(name = "size", required = false, defaultValue = "12") Integer size) {

        Pageable pageable = new PageRequest(page, size);
        Page<Product> productList = productService.getByAll(keyword, pageable);

        long totalItems = productList.getTotalElements();
        int totalPages = productList.getTotalPages();
        int number = productList.getNumber();
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("number", number);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("products", productList);
        return "admin/product/product";
    }

    @RequestMapping("/add-product")
    public String add(Model model) {
        Product product = new Product();
        List<Category> categories = categoryService.listCategory();

        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        return "admin/product/add";
    }
    @RequestMapping("/edit-product/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        Product product = productService.getById(id);
        List<Category> categories = categoryService.listCategory();

        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        return "admin/product/edit";
    }

    @RequestMapping("/save-product")
    public String save(@ModelAttribute("product") Product product,
                       RedirectAttributes ra,
                       @RequestParam(name = "file") MultipartFile multipartFile)  {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setMainImage(fileName);

        productService.save(product);
        return "redirect:/admin/product";
    }

}
