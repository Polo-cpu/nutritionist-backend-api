package com.nutritionist.api.service;

import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.dto.ProductDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.entity.ProductEntity;
import com.nutritionist.api.model.mapper.CustomerMapper;
import com.nutritionist.api.model.mapper.ProductMapper;
import com.nutritionist.api.repository.CustomerRepository;
import com.nutritionist.api.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductService productService;

    @Test
    void getAllCustomers(){
        List<ProductEntity> sampleProducts = sampleProductList();
        Mockito.when(productRepository.findAll()).thenReturn(sampleProducts);
        List<ProductEntity> actualProducts = productRepository.findAll();
        Assertions.assertEquals(actualProducts.size(), sampleProducts.size());
        for(int i = 0;i<actualProducts.size();i++){
            ProductEntity toCompare1 = sampleProducts.get(i);
            ProductEntity toCompare2 = actualProducts.get(i);

            Assertions.assertEquals(toCompare1.getProductName(),toCompare2.getProductName());
            Assertions.assertEquals(toCompare1.getProductDetails(),toCompare2.getProductDetails());
            Assertions.assertEquals(toCompare1.getProductPrice(),toCompare2.getProductPrice());
        }


    }
    @Test
    void getById(){
        ProductEntity sampleProduct = sampleProductList().get(1);
        Mockito.when(productRepository.getReferenceById(Mockito.any())).thenReturn(sampleProduct);
        ProductEntity realProduct = productService.getById(1L);
        Assertions.assertEquals(sampleProduct.getProductName(), realProduct.getProductName());
        Assertions.assertEquals(sampleProduct.getProductDetails(), realProduct.getProductDetails());
        Assertions.assertEquals(sampleProduct.getProductPrice(), realProduct.getProductPrice());

    }
    @Test
    void addProduct(){
        ProductEntity expectedProduct = sampleProductList().get(0);
        expectedProduct.setId(null);
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(expectedProduct);
        ProductDto productDto = new ProductDto();
        productDto.setProductNameDto(expectedProduct.getProductName());
        productDto.setProductDetailsDto(expectedProduct.getProductDetails());
        productDto.setProductPriceDto(expectedProduct.getProductPrice());
        ProductEntity actualProduct = productService.addProduct(productDto);

        verify(productRepository,times(1)).save(expectedProduct);
        Assertions.assertEquals(expectedProduct.getProductName(),actualProduct.getProductName());
        Assertions.assertEquals(expectedProduct.getProductDetails(),actualProduct.getProductDetails());
        Assertions.assertEquals(expectedProduct.getProductPrice(),actualProduct.getProductPrice());
    }
    @Test
    void delete(){
        Long productId = 1L;
        ProductEntity product = sampleProductList().get(0);
        Mockito.when(productRepository.getReferenceById(productId)).thenReturn(product);
        doNothing().when(productRepository).deleteById(productId);
        productService.deleteById(1L);
        verify(productRepository,times(1)).deleteById(productId);
    }
    public List<ProductEntity> sampleProductList(){
        List<ProductEntity> sampleProducts = new ArrayList<>();
        ProductEntity customer1 = new ProductEntity(1L,"creatine","medicine",5.0);
        ProductEntity customer2 = new ProductEntity(2L,"dumbbell","sport",10.0);
        ProductEntity customer3 = new ProductEntity(3L,"rope","sport",2.0);
        ProductEntity customer4 = new ProductEntity(4L,"slimming_cure","slimming",1.0);
        sampleProducts.add(customer1);
        sampleProducts.add(customer2);
        sampleProducts.add(customer3);
        sampleProducts.add(customer4);
        return sampleProducts;
    }
}
