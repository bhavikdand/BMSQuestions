# Implement Inventory Management for an E-Commerce Platform

## Problem Statement

You are building an e-commerce platform. As a part of this system, you need to expose a set of functionalities using which admins can manage the inventory, i.e. create, update and delete inventories for products.

## Requirements

### Create or Update Inventory
This functionality will be called when we are adding a new product to the platform or we want to update inventory of an existing product. 
The request will contain the user id, the product id and the quantity of the product that we want to add to the inventory. 
The response will contain inventory object.


### Delete Inventory
This functionality will be called when we want to delete the inventory of a product i.e. in scenarios where we want to remove a product from the platform.
The request will contain the user id and the product id.
The response will contain the response status i.e. SUCCESS or FAILURE.

Note: All the above functionalities should be accessible by admin users only.


## Instructions
1. Carefully look at the classes in dtos package. These classes represent the request and response of the functionality which we want to implement.
2. Carefully examine the models package to understand the database schema.
3. Implement the `createOrUpdateInventory` and `deleteInventory` method inside the `InventoryController`.
4. Implement the `InventoryService` interface and fix the repository interfaces.
5. You might need to add annotations like `@Service`, `@Autowired`, `@Entity` etc. to make the solution work. You might also need to handle cardinality between the models.
6. We will be using H2 database which is an in-memory SQL database. You do not need to implement any database related code. You just need to use the repository interfaces to interact with the database.
7. Carefully examine the `TestInventoryController` class to understand how the controller will be tested. Your solution should pass all the tests in this class.