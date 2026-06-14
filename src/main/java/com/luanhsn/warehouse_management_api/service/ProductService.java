package com.luanhsn.warehouse_management_api.service;

import com.luanhsn.warehouse_management_api.exception.ResourceNotFoundException;
import com.luanhsn.warehouse_management_api.model.Product;
import com.luanhsn.warehouse_management_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = productRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Product with id " + " not found"));
        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setQuantity(updatedProduct.getQuantity());
        existing.setPrice(updatedProduct.getPrice());
        existing.setSku(updatedProduct.getSku());
        return productRepository.save(existing);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}