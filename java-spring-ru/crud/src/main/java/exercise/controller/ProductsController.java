package exercise.controller;

import java.util.ArrayList;
import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import exercise.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

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
    ProductDTO show(@PathVariable long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        var productDTO = productMapper.map(product);
        return productDTO;
    }
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    ProductDTO create(@Valid @RequestBody ProductCreateDTO productDATA) {
        var product = productMapper.map(productDATA);
        productRepository.save(product);
        var productDTO = productMapper.map(product);
        return productDTO;
    }
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProductDTO update(@RequestBody @Valid ProductUpdateDTO productDATA, @PathVariable long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        productMapper.update(productDATA, product);
        productRepository.save(product);
        var productDTO = productMapper.map(product);
        return productDTO;
    }
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        productRepository.deleteById(id);
    }
    // END
}
