Design schema for Splitwise

Requirements:

1. Users should be able to track their expense with other users.
2. Users should be able to create groups and start tracking expenses.
3. Multiple people can pay up in an expense. Also, amount can be split up unequally in an expense. Consider this example:
Users involved A,B,C
Expense amount: 2000
				A		B		C
Paid amount: 	1500	500		0
Owed amount: 	500 	500 	1000
Here A paid 1500 and B paid 500. But A and B consumed worth 500 where as C consumed worth 1000.
4. An expense will also have its currency, description and an optional bill image attribute.
5. Users should be able to able to settle up their balances with minimum number of transactions using the settle up feature.

Expected solution:

users(id, name, phone, number)
expenses(id, currency, bill_iamge_url)
expense_users(id, expense_id, user_id, amount, expenseType)
group(id, name)
group_users(group_id, user_id)
group_admins(group_id, user_id)
group_expenses(group_id, expense_id)
transactions(id, paid_by, owed_by, amount)
