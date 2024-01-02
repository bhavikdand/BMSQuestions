Design schema for BookMyShow

Requirements:

1. We will have multiple cities.
2. Each city has mulitple theatres.
3. Each theatre has multiple screens.
4. Each screen has multiple seats.
5. There are different types of seats: PLATINUM, GOLD, SILVER.
6. Each screen will have multiple shows.
7. Every show will be for a movie and will have a start time and end time.
8. Each show depending upon the day, time and seat type will have different prices.
9. Movies, shows and screens will support features like 2D, 3D, DOLBY_IMAX, DOLBY_VISION etc.
10. We integrate with Razorpay to handle payments.
11. Users should be able to book multiple seats for a show via the app by paying for them at the time of booking.

Expected solution:

cities(id, name)
theatres(id, city_id, name)
screens(id, theatre_id, name)
screen_features(screen_id, feature)
seats(id, screen_id, name, type)
movie(id, name)
shows(id, movie_id, screen_id, start_time, end_time)
show_seats(id, show_id, seat_id, state, user_id)
show_seat_types(show_id, seat_type, price)
users(id, name, email, phone_number)
tickets(id, user_id, show_id, amount)
ticket_seats(ticket_id, seat_id)
payment(id, ticket_id, txn_id, status)