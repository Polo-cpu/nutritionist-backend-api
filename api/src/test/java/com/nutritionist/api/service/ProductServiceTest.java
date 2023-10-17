package com.nutritionist.api.service;

import com.nutritionist.api.model.entity.ProductEntity;
import com.nutritionist.api.model.enums.Language;
import com.nutritionist.api.model.mapper.ProductMapper;
import com.nutritionist.api.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Autowired
    private final Language language = Language.EN;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductService productService;

    @Test
    void getAllCustomers(){
        List<ProductEntity> sampleProducts = sampleProductList();
        Mockito.when(productService.getAll(language)).thenReturn(sampleProducts);
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
        Mockito.when(productService.getById(language,Mockito.any())).thenReturn(Optional.ofNullable(sampleProduct));
        Optional<ProductEntity> realProduct = productService.getById(language,1L);
        Assertions.assertEquals(sampleProduct.getProductName(), realProduct.get().getProductName());
        Assertions.assertEquals(sampleProduct.getProductDetails(), realProduct.get().getProductDetails());
        Assertions.assertEquals(sampleProduct.getProductPrice(), realProduct.get().getProductPrice());
    }
    @Test
    void addProduct(){
        ProductEntity expectedProduct = sampleProductList().get(0);
        expectedProduct.setId(null);
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(expectedProduct);
        ProductEntity product = new ProductEntity();
        product.setId(expectedProduct.getId());
        product.setProductName(expectedProduct.getProductName());
        product.setProductDetails(expectedProduct.getProductDetails());
        product.setProductPrice(expectedProduct.getProductPrice());
        productRepository.save(product);
        verify(productRepository,times(1)).save(expectedProduct);
        Assertions.assertEquals(expectedProduct.getProductName(),product.getProductName());
        Assertions.assertEquals(expectedProduct.getProductDetails(),product.getProductDetails());
        Assertions.assertEquals(expectedProduct.getProductPrice(),product.getProductPrice());
    }
    @Test
    void delete(){
        Long productId = 1L;
        ProductEntity product = sampleProductList().get(0);
        Mockito.when(productRepository.getReferenceById(productId)).thenReturn(product);
        doNothing().when(productRepository).deleteById(productId);
        productService.deleteById(language, 1L);
        verify(productRepository,times(1)).deleteById(productId);
    }
    public List<ProductEntity> sampleProductList(){
        List<ProductEntity> sampleProducts = new ArrayList<>();
        ProductEntity customer1 = new ProductEntity(1L,"creatine","medicine",5.0,null);
        ProductEntity customer2 = new ProductEntity(2L,"dumbbell","sport",10.0,null);
        ProductEntity customer3 = new ProductEntity(3L,"rope","sport",2.0,null);
        ProductEntity customer4 = new ProductEntity(4L,"slimming_cure","slimming",1.0,null);
        sampleProducts.add(customer1);
        sampleProducts.add(customer2);
        sampleProducts.add(customer3);
        sampleProducts.add(customer4);
        return sampleProducts;
    }
}
