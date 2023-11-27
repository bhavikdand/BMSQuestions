package com.example.ecom.controllers;

import com.example.ecom.dtos.GenerateRecommendationsRequestDto;
import com.example.ecom.dtos.GenerateRecommendationsResponseDto;
import com.example.ecom.dtos.ResponseStatus;
import com.example.ecom.models.Product;
import com.example.ecom.models.ProductGroup;
import com.example.ecom.repositories.ProductGroupsRepository;
import com.example.ecom.repositories.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestRecommendationsController {

    @Autowired
    private RecommendationsController recommendationsController;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductGroupsRepository productGroupsRepository;
    private Product cricketBat;

    @BeforeEach
    public void insertData() {
        Product product1 = new Product();
        product1.setName("Cricket bat");
        product1.setDescription("Cricket bat");
        product1.setPrice(1000);
        product1 = productRepository.save(product1);
        cricketBat = product1;

        Product product2 = new Product();
        product2.setName("Cricket ball");
        product2.setDescription("Cricket ball");
        product2.setPrice(100);
        product2 = productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("Cricket stumps");
        product3.setDescription("Cricket stumps");
        product3.setPrice(500);
        product3 = productRepository.save(product3);

        Product product4 = new Product();
        product4.setName("Cricket gloves");
        product4.setDescription("Cricket gloves");
        product4.setPrice(200);
        product4 = productRepository.save(product4);

        ProductGroup productGroup1 = new ProductGroup();
        productGroup1.setProducts(Arrays.asList(product1, product2));
        productGroup1 = productGroupsRepository.save(productGroup1);

        ProductGroup productGroup2 = new ProductGroup();
        productGroup2.setProducts(Arrays.asList(product1, product3));
        productGroup2 = productGroupsRepository.save(productGroup2);

        ProductGroup productGroup3 = new ProductGroup();
        productGroup3.setProducts(Arrays.asList(product1, product2, product4));
        productGroup3 = productGroupsRepository.save(productGroup3);

        List<Product> products = productRepository.findAll();
        List<ProductGroup> productGroups = productGroupsRepository.findAll();
        System.out.println("Dummy data: products:" + products +", productGroups: " + productGroups);
    }

    @AfterEach
    public void deleteDummyData(){
        productGroupsRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void testGenerateRecommendations_ProductNotFound() {
        GenerateRecommendationsRequestDto requestDto = new GenerateRecommendationsRequestDto();
        requestDto.setProductId(100);
        GenerateRecommendationsResponseDto responseDto = recommendationsController.generateRecommendations(requestDto);
        assertTrue(responseDto.getRecommendations() == null || responseDto.getRecommendations().isEmpty(), "Recommendations should be empty");
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus(), "Response status should be FAILURE");
    }

    @Test
    public void testGenerateRecommendations(){
        GenerateRecommendationsRequestDto requestDto = new GenerateRecommendationsRequestDto();
        requestDto.setProductId(cricketBat.getId());
        GenerateRecommendationsResponseDto responseDto = recommendationsController.generateRecommendations(requestDto);
        assertEquals(3, responseDto.getRecommendations().size(), "Recommendations should be 3");
        assertEquals(ResponseStatus.SUCCESS, responseDto.getResponseStatus(), "Response status should be SUCCESS");
        List<Product> recommendations = responseDto.getRecommendations();
        Product cricketBall = recommendations.stream().filter(product -> product.getName().equals("Cricket ball")).findFirst().get();
        assertNotNull(cricketBall, "Cricket ball should be present in recommendations");
        Product cricketStumps = recommendations.stream().filter(product -> product.getName().equals("Cricket stumps")).findFirst().get();
        assertNotNull(cricketStumps, "Cricket stumps should be present in recommendations");
        Product cricketGloves = recommendations.stream().filter(product -> product.getName().equals("Cricket gloves")).findFirst().get();
        assertNotNull(cricketGloves, "Cricket gloves should be present in recommendations");
    }

}
