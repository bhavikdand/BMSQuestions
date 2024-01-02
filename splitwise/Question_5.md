Design class diagram for an Ecommerce app

Requirements:

1. Registered sellers will list their products with some selling price on the platform.
2. Registered buyers can add multiple of such products to their cart and buy them by placing an order if all the required products are in stock.
3. We need to maintain inventory of all the products for each seller.
4. Sometimes a product can be out of stock, interested users can subscribe to a notification which they will get when the product becomes available next.
5. We want to show advertisements to users depending upon their interests, previous shopping behaviour, etc. We have a list of interested categories for every user eg. Ram is interested in Tech, Clothing and Footwear. Now all the ads related to these categories should be shown to Ram.
6. Each advertisements has the following things: Name of the product, link to buy the product, category of the product (used to show ads to the users) etc.


Exepcted solution:

class BaseModel
	int id

class Seller extends BaseModel
	String name
	String email
	String phone

class Product extends BaseModel
	String name
	String description

class Listing extends BaseModel
	Seller seller
	Product product
	double price

class User extends BaseModel
	String name
	String email
	String phone
	List<Category> preferredCategories

class Category extends BaseModel
	String name
	String description
	Date createdAt

class Cart
	User user
	List<CartItem> cartItems

class CartItem
	Listing product
	int quantity

class Order
	User user
	List<OrderItem> orderItems

class OrderItem
	Listing product
	int quantity

class Inventory
	Listing listedProduct
	int quantity


class Advertisement
	String name
	String description
	String link
	Category category



