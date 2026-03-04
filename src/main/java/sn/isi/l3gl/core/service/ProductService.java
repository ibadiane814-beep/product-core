package sn.isi.l3gl.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.isi.l3gl.core.entity.Product;
import sn.isi.l3gl.core.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // VERSION 0.0.1 — createProduct
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // VERSION 0.1.0 — listProducts
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    // VERSION 0.2.0 — updateQuantity
    public Product updateQuantity(Long id, Integer quantity) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product product = optional.get();
            product.setQuantity(quantity);
            return productRepository.save(product);
        }
        throw new RuntimeException("Produit non trouvé avec l'id : " + id);
    }

    // VERSION 0.3.0 — countLowStockProducts
    public long countLowStockProducts() {
        return productRepository.countByQuantityLessThanEqual(5);
    }
}