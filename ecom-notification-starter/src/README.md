# Implement notify users for out of stock products functionality for an e-commerce platform

## Problem Statement

You are building an e-commerce platform. As a part of this system, you need to expose a functionality using which users can register for notifications when a product out-of-stock product becomes available.

## Requirements
We need to build 3 functionalities:
Requirement #1: Interested users can register for notifications when a product becomes available. 
Requirement #2: Users who have already registered for notifications can unregister themselves.
Requirement #3: There is a functionality which also Admins can add stocks for a product. Modify this functionality to notify all the users who have registered for notifications for this product. The users who have registered themselves should be notified via email and the users who have registered via SMS should be notified via email.

Requirement #1:

Request for registering for notifications will contain: product id and user id.
Before registering for notifications we need to check the following things:
* Does the user exist in the system? If not then we need to throw an exception.
* Does the product exist in the system? If not then we need to throw an exception.
* Is the product really out of stock? If not then we need to throw an exception.
* If all the above checks pass, then we need to register the user for notifications by making an entry in the `notifications` table.

Response for registering for notifications will contain: notification id and response status.

Requirement #2:

Request for unregistering for notifications will contain: notification id and user id.
Before unregistering for notifications we need to check the following things:
* Does the user exist in the system? If not then we need to throw an exception.
* Does the notification exist in the system? If not then we need to throw an exception.
* Does the notification belong to the user? If not then we need to throw an exception.
* If all the above checks pass, then we need to unregister the user for notifications by deleting the entry from the `notifications` table.

Response for unregistering for notifications will contain: response status.

Requirement #3:
We need to modify the `addStock` method inside the `ProductController` to notify all the users who have registered for notifications for this product. The users who have registered themselves should be notified via email.
We will use SendGrid to send emails.

## Instructions
1. Carefully look at `PlaceOrderRequestDto` and `PlaceOrderResponseDto` classes. These classes represent the request and response of the functionality which we want to implement.
2. Carefully examine the models package to understand the database schema.
3. Implement the `placeOrder` method inside the `OrderController`.
4. Implement the `OrderService` interface and fix the repository interfaces.
5. You might need to add annotations like `@Service`, `@Autowired`, `@Entity` etc. to make the solution work. You might also need to handle cardinality between the models.
6. We will be using H2 database which is an in-memory SQL database. You do not need to implement any database related code. You just need to use the repository interfaces to interact with the database.
7. Carefully examine the `TestOrderController` class to understand how the controller will be tested. Your solution should pass all the tests in this class.
8. Implement the necessary exceptions in the `exceptions` package.
9. Do not modify the `OrderService` interface's placeOrder method signature. You can add additional methods to the interface if you want.