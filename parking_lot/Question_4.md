Design schema for a Restaurant Management System:
Design schema for a Restaurant Management system with the following requirements:

1. A restaurant has a name, an address, cuisines served, menu, waiters, chefs.
2. Cuisines can be Italian, French, Chinese, Japanese, Indian, Continental etc.
3. A menu contains a list of dishes divided into multiple sections like Soups, Appetizers etc.
4. A dish has a name, description, type (veg / non-veg / vegan), a recipe for the chefs to follow.
5. A dish also has a list of ingredients and its amount required to cook the dish.
6. Ingredients can be measured either in grams for solids or ml (mililitres) for liquids/semiliquids.
6. A waiter has a name, email, phone number and level (Junior, Senior, Manager).
7. A chef has a name, email, phone number and level (Commis Chef, Sous chef, Head Chef, Executive chef).
8. We need to track orders made by customers during their visit so that we can generate a bill at the end. Note: customer during a visit will order multiple times and each time an order can contain multiple dishes in it.

Expected solution:

restaurant(id, name, address, menu_id)
restaurant_cuisines(restaurant_id, cuisine)
restaurant_waiters(restaurant_id, waiter_id)
restaurant_chefs(restaurant_id, chef_id)
dishes(id, name. description, recipe, price, type)
ingredients(id, name, type)
dish_ingredients(dish_id, ingredient_id, qty)
menus(id, createdAt, publishedAt)
menu_sections(id, menu_id, name, description)
menu_section_dishes(menu_section_id, dish_id, price)
waiters(id, name, email, phone, level)
chefs(id, name, email, phone, level)
customers(id, name, email, phone)
orders(id, orderedAt)
order_items(id, order_id, dish_id, qty)
customer_visits(id, status, visit_date)
bill(id, customer_visit_id, amount, state_gst, central_gst)



