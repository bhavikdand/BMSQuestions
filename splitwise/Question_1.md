Design class diagram for Splitwise
Design class diagram for Splitwise with the following requirements:

1. Users should be able to track their expense with other users.
2. Users should be able to create groups and start tracking expenses.
3. Multiple people can pay up in an expense. Also, amount can be split up unequally in an expense. Consider this example:
Users involved A,B,C
Expense amount: 2000
				A		B		C
Paid amount: 	1500	500		0
Owed amount: 	500 	500 	1000
Here A paid 1500 and B paid 500. But A and B consumed worth 500 where as C consumed worth 1000.
4. Users should be able to able to settle up their balances with minimum number of transactions using the settle up feature.

Expected solution:

class BaseModel
	int id

class User
	String name
	String phone
	String email

class Expense extends BaseModel
	double amount
	Currency currency
	String description
	String billImageUrl
	List<ExpenseUser> expenseUsers

enum Currency
	USD, INR, EURO

class Group extends BaseModel
	List<User> users
	String name
	List<User> admins

class GroupExpense
	Group group
	Expense expense

class ExpenseUser extends BaseModel
	Expense expense
	User user
	double amount
	ExpenseType expenseType

enum ExpenseType
	PAID
	OWED

class Transaction extends BaseModel
	User paidBy
	User owedBy
	double amount

interface SettleUpStrategy:
	List<Transaction> settleUp()

class MinimumTransactionsStrategy implements SettleUpStrategy:
	List<Transaction> settleUp()
