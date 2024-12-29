package ru.sorokinad.productservice;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Tag(name = "Получить все продукты", description = "Возвращает список всех продуктов")
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Tag(name = "Создать продукт", description = "Создаёт новый продукт в базе данных")
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
}
