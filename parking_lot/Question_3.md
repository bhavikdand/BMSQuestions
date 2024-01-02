Design class diagram for a Restaurant Management System

Requirements:

1. A restaurant has a name, an address, cuisines served, menu, waiters, chefs.
2. Cuisines can be Italian, French, Chinese, Japanese, Indian, Continental etc.
3. A menu contains a list of dishes divided into multiple sections like Soups, Appetizers etc.
4. A dish has a name, description, type (veg / non-veg / vegan), price, a recipe for the chefs to follow.
5. A dish also has a list of ingredients and its amount required to cook the dish.
6. Ingredients can be measured either in grams for solids or ml (mililitres) for liquids/semiliquids.
6. A waiter has a name, email, phone number and level (Junior, Senior, Manager).
7. A chef has a name, email, phone number and level (Commis Chef, Sous chef, Head Chef, Executive chef).
8. We need to track orders made by customers during their visit so that we can generate a bill at the end. Note: customer during a visit will order multiple times and each time an order can contain multiple dishes in it.

Expected solution:

class BaseModel
	int id

class Restaurant extends BaseModel
	String name
	String address
	List<Cuisine> cuisines
	Menu menu
	List<Waiter> waiters
	List<Chef> chefs

enum Cuisine
	ITALIAN, FRENCH, JAPANESE, INDIAN, CONTINENTAL

class Dish extends BaseModel
	String name
	String description
	DishType type
	double price
	String recipe

enum DishType
	VEG, NON_VEG, VEGAN

class Ingredient extends BaseModel
	String name
	MeasurementType type

enum MeasurementType
	GRAMS, ML

class DishIngredient extends BaseModel
	Dish dish
	Ingredient ingredient
	double qty

class Menu extends BaseModel
	List<MenuSection> sections
	Date createdAt
	Date publishedAt

class MenuSection extends BaseModel
	Menu menu
	String name
	String description
	List<Dish> dishes

class Waiter extends BaseModel
	String name
	String email
	String phone
	WaiterLevel level

enum WaiterLevel
	Junior, Senior, Manager

class Chef extends BaseModel
	String name
	String email
	String phone
	ChefLevel level

enum ChefLevel
	COMMIS_CHEF, SOUS_CHEF, HEAD_CHEF, EXEC_CHEF

class Customer extends BaseModel
	String name
	String email
	String phone

class Order extends BaseModel
	List<OrderItem> orderItems
	Date orderedAt

class OrderItem extends BaseModel
	Order order
	Dish dish
	int quantity

class CustomerVisit extends BaseModel
	List<Order> orders
	VisitStatus status
	Date visitDate

enum VisitStatus
	IN_PROGRESS, ENDED

class Bill extends BaseModel
	CustomerVisit visit
	double amount
	double state_gst
	double central_gst



