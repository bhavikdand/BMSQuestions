package com.example.ecom.controllers;

import com.example.ecom.dtos.*;
import com.example.ecom.models.Product;
import com.example.ecom.models.User;
import com.example.ecom.models.UserType;
import com.example.ecom.repositories.InventoryRepository;
import com.example.ecom.repositories.ProductRepository;
import com.example.ecom.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestInventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InventoryController inventoryController;

    private User user, admin;

    private List<Product> products;

    @BeforeEach
    public void insertDummyData(){
        user = new User();
        user.setUserType(UserType.CUSTOMER);
        user.setEmail("john@doe.com");
        user.setName("John Doe");
        user = userRepository.save(user);

        admin = new User();
        admin.setUserType(UserType.ADMIN);
        admin.setEmail("admin@ecom.com");
        admin.setName("Admin");
        admin = userRepository.save(admin);

        Product product1 = new Product();
        product1.setName("Test Product 1");
        product1.setPrice(100);
        product1.setDescription("Test Description 1");
        product1 = productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Test Product 2");
        product2.setPrice(200);
        product2.setDescription("Test Description 2");
        product2 = productRepository.save(product2);

        products = List.of(product1, product2);
    }

    @AfterEach
    public void cleanUp(){
        this.inventoryRepository.deleteAll();
        this.productRepository.deleteAll();
        this.userRepository.deleteAll();
    }

    @Test
    public void testCreateOrUpdateInventory_Create_Success(){
        CreateOrUpdateRequestDto requestDto = new CreateOrUpdateRequestDto();
        requestDto.setProductId(products.get(0).getId());
        requestDto.setQuantity(10);
        requestDto.setUserId(admin.getId());

        CreateOrUpdateResponseDto responseDto = inventoryController.createOrUpdateInventory(requestDto);
        assertNotNull(responseDto, "Response should not be null");
        assertEquals(ResponseStatus.SUCCESS, responseDto.getResponseStatus(), "Response status should be success");
        assertNotNull(responseDto.getInventory(), "Inventory should not be null");
    }

    @Test
    public void testCreateOrUpdateInventory_Update_Success(){
        CreateOrUpdateRequestDto requestDto = new CreateOrUpdateRequestDto();
        requestDto.setProductId(products.get(0).getId());
        requestDto.setQuantity(10);
        requestDto.setUserId(admin.getId());

        CreateOrUpdateResponseDto responseDto = inventoryController.createOrUpdateInventory(requestDto);

        requestDto = new CreateOrUpdateRequestDto();
        requestDto.setProductId(products.get(0).getId());
        requestDto.setQuantity(20);
        requestDto.setUserId(admin.getId());

        responseDto = inventoryController.createOrUpdateInventory(requestDto);

        assertNotNull(responseDto, "Response should not be null");
        assertEquals(ResponseStatus.SUCCESS, responseDto.getResponseStatus(), "Response status should be success");
        assertNotNull(responseDto.getInventory(), "Inventory should not be null");
        assertEquals(30, responseDto.getInventory().getQuantity(), "Quantity should be 30");
    }

    @Test
    public void testCreateOrUpdateInventory_ProductNotFound_Failure(){
        CreateOrUpdateRequestDto requestDto = new CreateOrUpdateRequestDto();
        requestDto.setProductId(products.get(0).getId() * 100);
        requestDto.setQuantity(10);
        requestDto.setUserId(admin.getId());

        CreateOrUpdateResponseDto responseDto = inventoryController.createOrUpdateInventory(requestDto);
        assertNotNull(responseDto, "Response should not be null");
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus(), "Response status should be failure");
        assertNull(responseDto.getInventory(), "Inventory should be null");
    }

    @Test
    public void testCreateOrUpdateInventory_UserNotFound_Failure(){
        CreateOrUpdateRequestDto requestDto = new CreateOrUpdateRequestDto();
        requestDto.setProductId(products.get(0).getId());
        requestDto.setQuantity(10);
        requestDto.setUserId(admin.getId()*100);

        CreateOrUpdateResponseDto responseDto = inventoryController.createOrUpdateInventory(requestDto);
        assertNotNull(responseDto, "Response should not be null");
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus(), "Response status should be failure");
        assertNull(responseDto.getInventory(), "Inventory should be null");
    }

    @Test
    public void testCreateOrUpdateInventory_NonAdminUser_Failure(){
        CreateOrUpdateRequestDto requestDto = new CreateOrUpdateRequestDto();
        requestDto.setProductId(products.get(0).getId());
        requestDto.setQuantity(10);
        requestDto.setUserId(user.getId());

        CreateOrUpdateResponseDto responseDto = inventoryController.createOrUpdateInventory(requestDto);
        assertNotNull(responseDto, "Response should not be null");
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus(), "Response status should be failure");
        assertNull(responseDto.getInventory(), "Inventory should be null");
    }

    @Test
    public void testDeleteInventory_Success(){
        DeleteInventoryRequestDto requestDto = new DeleteInventoryRequestDto();
        requestDto.setProductId(products.get(0).getId());
        requestDto.setUserId(admin.getId());

        DeleteInventoryResponseDto responseDto = inventoryController.deleteInventory(requestDto);
        assertNotNull(responseDto, "Response should not be null");
        assertEquals(ResponseStatus.SUCCESS, responseDto.getResponseStatus(), "Response status should be success");
    }

    @Test
    public void testDeleteInventory_UserNotFound_Failure(){
        DeleteInventoryRequestDto requestDto = new DeleteInventoryRequestDto();
        requestDto.setProductId(products.get(0).getId());
        requestDto.setUserId(admin.getId()*100);

        DeleteInventoryResponseDto responseDto = inventoryController.deleteInventory(requestDto);
        assertNotNull(responseDto, "Response should not be null");
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus(), "Response status should be failure");
    }


    @Test
    public void testDeleteInventory_NonAdminUser_Failure(){
        DeleteInventoryRequestDto requestDto = new DeleteInventoryRequestDto();
        requestDto.setProductId(products.get(0).getId());
        requestDto.setUserId(admin.getId()*100);

        DeleteInventoryResponseDto responseDto = inventoryController.deleteInventory(requestDto);
        assertNotNull(responseDto, "Response should not be null");
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus(), "Response status should be failure");
    }
}
