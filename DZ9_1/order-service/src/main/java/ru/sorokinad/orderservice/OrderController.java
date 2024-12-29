package ru.sorokinad.orderservice;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Заказы", description = "API для управления заказами")
@RequestMapping("/api/orders")
public class OrderController {

    private final ProductClient productClient;

    public OrderController(ProductClient productClient) {
        this.productClient = productClient;
    }
    @Tag(name = "Продукты", description = "API для продуктами")
    @GetMapping("/products")
    public List<ProductDTO> getProductsForOrder() {
        return productClient.getAllProducts();
    }
}