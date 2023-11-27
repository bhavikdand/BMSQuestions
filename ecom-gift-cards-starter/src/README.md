# Implement place order functionality for an e-commerce platform

## Problem Statement

You are building an e-commerce platform. As a part of this system, you need to expose a functionality using which users buy and redeem gift cards.

## Requirements
We want to build 2 functionalities:
1. Create a gift card
2. Redeem a gift card


1. Create a gift card
   * The request to create a gift card will contain the following information:
      * Amount of the gift card.
   * Every gift card will have the following properties:
      * Gift card code (unique 10 digit alphanumeric code)
      * Amount
      * Created at (date)
      * Expires at (date)
      * List of ledger entries
   * A gift card can be redeemed multiple times till the amount is exhausted.
   * Every time a gift card is redeemed, a ledger entry will be created with the following properties:
      * Amount
      * Created at (date)
      * Transaction type (CREDIT / DEBIT)
   * When a gift card is created, a ledger entry with transaction type as CREDIT will be created with the amount of the gift card.
   * When a gift card is redeemed, a ledger entry with transaction type as DEBIT will be created with the amount being redeemed.
   * Every gift expires with 365 days of creation.

2. Redeem a gift card
   * The request to redeem a gift card will contain the following information:
      * Gift card id
      * Amount to be redeemed
   * Before redeeming a gift card we need to check the following things:
      * Does the gift card exist in the system? If not then we need to throw an exception.
      * Is the gift card expired? If yes then we need to throw an exception.
      * Does the gift card have enough amount to be redeemed? If no then use the entire amount of the gift card, the rest will be paid by the user by some other means.
      * If all the above checks pass, then we need to create a ledger entry with transaction type as DEBIT, update the ledger entry details and return the updated gift card in response.

## Instructions
1. Carefully look at dto package. These classes represent the request and response of the functionality which we want to implement.
2. Carefully examine the models package to understand the database schema.
3. Implement the `createGiftCard` and `redeemGiftCard` methods inside the `GiftCardController`.
4. Implement the `GiftCardService` interface and fix the repository interfaces.
5. You might need to add annotations like `@Service`, `@Autowired`, `@Entity` etc. to make the solution work. You might also need to handle cardinality between the models.
6. We will be using H2 database which is an in-memory SQL database. You do not need to implement any database related code. You just need to use the repository interfaces to interact with the database.
7. Implement the necessary exceptions in the `exceptions` package.
8. Do not modify the `OrderService` interface's placeOrder method signature. You can add additional methods to the interface if you want.