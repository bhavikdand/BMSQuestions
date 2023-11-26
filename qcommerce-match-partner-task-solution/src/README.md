# Implement ratings functionality for movies

## Problem Statement
We are building a Quick Commerce Delivery platform like Swiggy or Zomato. As a part of this system we need to build a functionality which will integrate with a maps solution (Google Maps) to build routes for batched orders.

## Requirements
To increase the efficiency of the business and to reduce costs, we have started batching orders. Orders which have drop locations nearby will be batched together and delivered by a single delivery partner. This will reduce the number of delivery partners required to deliver the orders and will also reduce the cost of delivery.
Batching of orders is out of scope for this question, you can assume that some other system perform batching.
Once we have a list of batched orders, we need to build routes for the delivery partners. We will be using a maps solution like Google Maps to build the routes. The maps solution will give us a list of coordinates which the delivery partner needs to follow to deliver the orders this order will be the most efficient way to visit the drop locations.
You will get a batched task as input. This batched task will have a list of tasks. Each task will have a drop location. You will need to build a route for each batched task. A route is nothing but a list of locations. Give the list of drop locations to Google maps and in response you will get a list of rearranged drop locations. This rearranged list of drop locations will be the route that the delivery partner is supposed to take.
Once this route is available to us, set this in the BatchedTask object in `routeToBeTaken`, persist it in the database and return the BatchedTask object in the response.
For now, we will use Google Maps. But in future we might want to use some other map's solution. So make sure to keep the code extensible.

## Instructions
1. Carefully look at classes in `dtos` package. These classes represent the request and response of the functionality which we want to implement.
2. Carefully examine the models package to understand the database schema.
3. Implement the `matchPartnersAndTasks` method inside the `MatchPartnerTaskController`.
4. Implement the `MatchPartnerTaskService` interface and fix the repository interfaces.
5. You might need to add annotations like `@Service`, `@Autowired`, `@Entity` etc. to make the solution work. You might also need to handle cardinality between the models.
6. We will be using H2 database which is an in-memory SQL database. You do not need to implement any database related code. You just need to use the repository interfaces to interact with the database.
7. Refer the `DistanceUtils` class for the haversine distance formula.