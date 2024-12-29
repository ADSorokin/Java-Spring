package ru.sorokinad.orderservice;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@FeignClient(
        name = "product-service-client",
        url = "${product.service.url}",
        configuration = FeignConfig.class
)
public interface ProductClient {
    @GetMapping("/api/products")
    List<ProductDTO> getAllProducts();
}