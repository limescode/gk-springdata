package pl.limescode.gkspringdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.limescode.gkspringdata.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select p from Product p where p.price >=:minPrice")
    List<Product> findProductsAboveMinPrice(Integer minPrice);

    @Query(value = "select p from Product p where p.price <=:maxPrice")
    List<Product> findProductsUnderMaxPrice(Integer maxPrice);

    @Query(value = "select p from Product p where p.price >=:minPrice and p.price <= :maxPrice")
    List<Product> findProductsWithPriceRange(Integer minPrice, Integer maxPrice);

}
