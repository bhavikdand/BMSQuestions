Parking Lot Class Diagram

Design a class diagram for a Parking Lot system with the following requirements:

1. Parking lot will have multiple floors.
2. There will be multiple entry and exit gates.
3. Parking lot will support parking for these vehicles: CAR, BIKE, TRUCK. We should park only appropriate vehicle at their spots i.e. a spot for CAR cannot be used to park bikes.
4. When a user enters via an entry gate, the operator generates a ticket and gives it to the user. The ticket contains information like entry time, vehicle number, assigned parking spot. Parking spots should be assigned as per the nearest available spot first i.e. find the nearest appropriate spot for a vechile and assign it. This way of assigning spot may change in the future.
5. Once the user decides to exit the parking lot, they will handover the ticket to the operator at the exit gate upon which their final bill will be calculated.
6. Bill calculation for a vehicle depends upon to two things, time spent in the parking lot and the type of vehicle. Refer the below tables to understand pricing.
	For BIKEs: 
	Hours spent Price per hour
	0-2 		20
	2-4			25
	4-6			30
	6 onwards	40

	For CARs:
	Hours spent Price per hour
	0-2 		25
	2-4			30
	4-6			35
	6 onwards	45

	For TRUCKs:
	Hours spent Price per hour
	0-2 		50
	2-4			60
	4-6			65
	6 onwards	80

	For eg. If a car spends 5.5 hours in the parking lot then their final bill will be: 2 * 25 (Charge for first 2 hours) + 2 * 30 (Charge for the next two hours i.e hour 3 and hour 4) + 2 * 35 (Charge for 5th hour and 6th hour (Note: even tough the vehicle was not in the parking lot for the entire hour, we charged it for the entire hour)) = Rs. 180
	Note: This is one way of charging customers, in the future we might change this.
7. We will integrate with Razorpay to manage payments.


Expected solution:

class BaseModel
	int id

class ParkingLot extends BaseModel
	String name
	List<ParkingFloor> parkingFloor
	List<EntryGate> entryGates
	List<ExitGate> exitGates
	Ticket assignSpot()

class ParkingFloor extends BaseModel
	int floorNumber
	List<ParkingSpot> parkingSpots


class ParkingSpot extends BaseModel
	VehicleType supportedVehicleType
	int spotNumber
	Status status

enum VehicleType
	CAR, BIKE, TRUCK

enum Status
	AVAILABLE, OCCUPIED

class Vehicle extends BaseModel
	String registrationNumber
	VehicleType type

class Ticket extends BaseModel
	Date entryTime
	Vehicle vehicle
	ParkingSpot assignedParkingSpot

class Gate extends BaseModel
	Operator currentOperator
	String gateName

class EntryGate extends Gate
	Ticket issueTicket()

class ExitGate extends Gate
	Payment processPayment()

class Operator extends BaseModel
	String name
	String email
	String phone

class Payment extends BaseModel
	String txnId;
	PaymentStatus status;
	double amount;
	Ticket ticket;

enum PaymentStatus:
	SUCCESSFUL,FAILED

class Slab extends BaseModel
	VehicleType vehicleType
	int startHour
	int endHour
	double amount

interface PricingStrategy
	double calculateBill()

class SlabBasedPricing implements PricingStrategy
	double calculateBill()

interface AssignSpotStrategy
	ParkingSpot assingSpot() throws NoAvailableSpotException

class AssignNearestAvailableFirstStrategy implements AssingSpotStrategy
	ParkingSpot assingSpot() throws NoAvailableSpotException
