package pl.limescode.gkspringdata.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.limescode.gkspringdata.dto.ProductCreateDto;
import pl.limescode.gkspringdata.model.Product;
import pl.limescode.gkspringdata.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public List<Product> getAllProducts(Optional<Integer> minPrice, Optional<Integer> maxPrice) {
        if (minPrice.isEmpty() && maxPrice.isPresent()) {
            return productRepository.findProductsUnderMaxPrice(maxPrice.get());
        } else if (minPrice.isPresent() && maxPrice.isEmpty()) {
            return productRepository.findProductsAboveMinPrice(minPrice.get());
        } else if (minPrice.isPresent() && maxPrice.isPresent()) {
            return productRepository.findProductsWithPriceRange(minPrice.get(), maxPrice.get());
        } else {
            return productRepository.findAll();
        }
    }

    public Product createProduct(ProductCreateDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
