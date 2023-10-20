package exercise.controller;

import exercise.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    ProductMapper productMapper;

    // BEGIN
    @GetMapping(path = "")
    public List<ProductDTO> index() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> result = new ArrayList<>();
        for (Product product : products) {
            ProductDTO productDTO = productMapper.map(product);
            result.add(productDTO);
        }
        return result;
    }
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProductDTO show(@PathVariable long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id" + " " + id + " " + "not found"));
        var productDTO = productMapper.map(product);
        return productDTO;
    }
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProductDTO update(@RequestBody ProductUpdateDTO productData, @PathVariable long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id" + " " + id + " " + "not found"));
        productMapper.update(productData, product);
        productRepository.save(product);
        var productDTO = productMapper.map(product);
        return productDTO;
    }
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    ProductDTO create(@RequestBody ProductCreateDTO productData) {
        var product = productMapper.map(productData);
        productRepository.save(product);
        var productDTO = productMapper.map(product);
        return productDTO;
    }

    // END
}
