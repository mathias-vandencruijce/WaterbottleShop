package be.ucll.cloud9.domain.service;

import be.ucll.cloud9.domain.db.ProductRepository;
import be.ucll.cloud9.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {

        System.out.println(product);
        productRepository.save(product);
    }

}
