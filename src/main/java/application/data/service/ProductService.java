package application.data.service;

import application.data.model.Product;
import application.data.reposiory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repo;

    public void save(Product product) {
        repo.save(product);
    }

    public void delete(int id) {
        repo.delete(id);
    }

    public Product getById(int id) {
        return repo.getOne(id);
    }
    public Page<Product> getByAll(String keyword, Pageable pageable) {
        if (keyword != null)
            return repo.findAll(keyword, pageable);
        else
            return repo.findAll(pageable);

    }
}
