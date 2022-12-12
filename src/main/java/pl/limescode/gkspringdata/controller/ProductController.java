package pl.limescode.gkspringdata.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.limescode.gkspringdata.dto.ProductCreateDto;
import pl.limescode.gkspringdata.model.Product;
import pl.limescode.gkspringdata.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProducts(@RequestParam(required = false) Integer min,
                                     @RequestParam(required = false) Integer max) {
        return productService.getAllProducts(Optional.ofNullable(min), Optional.ofNullable(max));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> createProduct(@RequestBody ProductCreateDto productCreateDto) {
        return ResponseEntity.ok(productService.createProduct(productCreateDto));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
