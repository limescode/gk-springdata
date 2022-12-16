package pl.limescode.gkspringdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.limescode.gkspringdata.model.Product;
import pl.limescode.gkspringdata.repository.ProductRepository;

@Component
public class DatabaseFiller {

    private final ProductRepository productRepository;

    public DatabaseFiller(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initialUsers() {
        productRepository.save(createProduct("Headphones", 10));
        productRepository.save(createProduct("Keyboard", 20));
        productRepository.save(createProduct("Vinyl", 25));
        productRepository.save(createProduct("Sound card", 40));
        productRepository.save(createProduct("Monitor", 150));
    }

    private Product createProduct(String name, Integer price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        return product;
    }

}
