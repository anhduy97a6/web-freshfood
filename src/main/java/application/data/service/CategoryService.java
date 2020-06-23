package application.data.service;

import application.data.model.Category;
import application.data.reposiory.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;



    public void saveCategory(Category category) {

        categoryRepository.save(category);

    }

    public void deleteCategory(int categoryId) {

        categoryRepository.delete(categoryId);

    }

    public Category findOne(int categoryId) {
        return categoryRepository.findOne(categoryId);
    }

    public Page<Category> getListAll(String keyword, Pageable pageable) {
        if (keyword != null)
            return categoryRepository.findAll(keyword, pageable);
        else
            return categoryRepository.findAll(pageable);
    }
    public List<Category> listCategory() {
        return categoryRepository.findAll();
    }
}
