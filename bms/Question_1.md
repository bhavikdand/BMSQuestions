Design a class diagram for BookMyShow
Design a class diagram for BookMyShow with the following requirements:

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

class BaseModel
	int id

class City extends BaseModel
	String name
	List<Theatres> theatres

class Theatre extends BaseModel
	String name
	List<Screen> screens

class Screen extends BaseModel
	List<Seats> seats
	String name
	List<Feature> features

enum Feature
	2D, 3D, DOLBY_VISION, DOLBY_ATMOS, IMAX

class Seat extends BaseModel
	SeatType type
	String name

enum SeatType
	PLATINUM, GOLD, SILVER

class Show extends BaseModel
	Movie movie
	Screen screen
	Date startTime
	Date endTime

class Movie extends BaseModel
	String name
	List<String> actors

class ShowSeat extends BaseModel
	Show show
	Seat seat
	SeatState state
	User user

enum SeatState
	AVL, BLOCKED, BOOKED

class ShowSeatType
	Show show
	SeatType seatType
	double price

class User extends BaseModel
	String name
	String email
	String phone

class Ticket extends BaseModel
	int id
	User user
	Show show
	List<Seat> seats
	double amount
	
class Payment extends BaseModel
	Ticket ticket
	String txnId
	PaymentStatus status

enum PaymentStatus
	FAILED, SUCCEEDED, IN_PROGRESS