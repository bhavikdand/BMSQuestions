package com.example.splitwise.controllers;

import com.example.splitwise.dtos.SettleGroupRequestDto;
import com.example.splitwise.dtos.SettleGroupResponseDto;
import com.example.splitwise.dtos.SettleUserRequestDto;
import com.example.splitwise.models.*;
import com.example.splitwise.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestSettleUpController {

    private GroupExpenseRepository groupExpenseRepository;

    private ExpenseUserRepository expenseUserRepository;
    private ExpenseRepository expenseRepository;
    private GroupRepository groupRepository;
    private UserRepository userRepository;

    private SettleUpController settleUpController;

    @Autowired
    public TestSettleUpController(GroupExpenseRepository groupExpenseRepository, ExpenseUserRepository expenseUserRepository, ExpenseRepository expenseRepository, GroupRepository groupRepository, UserRepository userRepository, SettleUpController settleUpController) {
        this.groupExpenseRepository = groupExpenseRepository;
        this.expenseUserRepository = expenseUserRepository;
        this.expenseRepository = expenseRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.settleUpController = settleUpController;
    }

    @Test
    public void testSettleGroup_Success() {
        Group group = new Group();
        group.setName("group1");
        group = groupRepository.save(group);

        User user1 = new User();
        user1.setName("user1");
        user1 = userRepository.save(user1);

        User user2 = new User();
        user2.setName("user2");
        user2 = userRepository.save(user2);

        User user3 = new User();
        user3.setName("user3");
        user3 = userRepository.save(user3);

        Expense expense1 = new Expense();
        expense1.setAmount(1000);
        expense1.setAddedAt(new Date());
        expense1.setDescription("expense1");
        expense1 = expenseRepository.save(expense1);

        ExpenseUser e1_1 = new ExpenseUser();
        e1_1.setExpense(expense1);
        e1_1.setUser(user1);
        e1_1.setAmount(1000);
        e1_1.setExpenseType(ExpenseType.PAID);
        e1_1 = expenseUserRepository.save(e1_1);

        ExpenseUser e1_2 = new ExpenseUser();
        e1_2.setExpense(expense1);
        e1_2.setUser(user2);
        e1_2.setAmount(300);
        e1_2.setExpenseType(ExpenseType.OWED);
        e1_2 = expenseUserRepository.save(e1_2);

        ExpenseUser e1_3 = new ExpenseUser();
        e1_3.setExpense(expense1);
        e1_3.setUser(user3);
        e1_3.setAmount(300);
        e1_3.setExpenseType(ExpenseType.OWED);
        e1_3 = expenseUserRepository.save(e1_3);

        ExpenseUser e1_4 = new ExpenseUser();
        e1_4.setExpense(expense1);
        e1_4.setUser(user1);
        e1_4.setAmount(400);
        e1_4.setExpenseType(ExpenseType.OWED);
        e1_4 = expenseUserRepository.save(e1_4);

        expense1.setExpenseUsers(List.of(e1_1, e1_2, e1_3, e1_4));
        expense1 = expenseRepository.save(expense1);

        GroupExpense groupExpense1 = new GroupExpense();
        groupExpense1.setGroup(group);
        groupExpense1.setExpense(expense1);
        groupExpense1 = groupExpenseRepository.save(groupExpense1);


        Expense expense2 = new Expense();
        expense2.setAmount(2000);
        expense2.setAddedAt(new Date());
        expense2.setDescription("expense2");
        expense2 = expenseRepository.save(expense2);

        ExpenseUser e2_1 = new ExpenseUser();
        e2_1.setExpense(expense2);
        e2_1.setUser(user1);
        e2_1.setAmount(2000);
        e2_1.setExpenseType(ExpenseType.PAID);
        e2_1 = expenseUserRepository.save(e2_1);

        ExpenseUser e2_2 = new ExpenseUser();
        e2_2.setExpense(expense2);
        e2_2.setUser(user2);
        e2_2.setAmount(500);
        e2_2.setExpenseType(ExpenseType.OWED);
        e2_2 = expenseUserRepository.save(e2_2);

        ExpenseUser e2_3 = new ExpenseUser();
        e2_3.setExpense(expense2);
        e2_3.setUser(user3);
        e2_3.setAmount(1500);
        e2_3.setExpenseType(ExpenseType.OWED);
        e2_3 = expenseUserRepository.save(e2_3);

        expense2.setExpenseUsers(List.of(e2_1, e2_2, e2_3));
        expense2 = expenseRepository.save(expense2);

        GroupExpense groupExpense2 = new GroupExpense();
        groupExpense2.setGroup(group);
        groupExpense2.setExpense(expense2);
        groupExpense2 = groupExpenseRepository.save(groupExpense2);

        SettleGroupRequestDto settleGroupRequestDto = new SettleGroupRequestDto();
        settleGroupRequestDto.setGroupId(group.getId());
        SettleGroupResponseDto responseDto = settleUpController.settleGroup(settleGroupRequestDto);

        assertNotNull(responseDto, "Response dto should not be null");
        assertNotNull(responseDto.getTransactions(), "Transactions should not be null");
        assertEquals(2, responseDto.getTransactions().size(), "There should be 2 transactions");

        double totalAmount = 0;
        for(Transaction t: responseDto.getTransactions()){
            assertEquals(user1, t.getPaidTo(), "User1 should be paid");
            totalAmount += t.getAmount();
        }
        assertEquals(2600, totalAmount, "User 1 should be paid 2600");


    }

    @Test
    public void testSettleGroup_GroupNotFound() {
        SettleGroupRequestDto settleGroupRequestDto = new SettleGroupRequestDto();
        settleGroupRequestDto.setGroupId(1);
        SettleGroupResponseDto responseDto = settleUpController.settleGroup(settleGroupRequestDto);

        assertNotNull(responseDto, "Response dto should not be null");
        assertNull(responseDto.getTransactions(), "Transactions should be null");
    }

    @Test
    public void testSettleUser_Success() {
        User user1 = new User();
        user1.setName("user1");
        user1 = userRepository.save(user1);

        User user2 = new User();
        user2.setName("user2");
        user2 = userRepository.save(user2);

        User user3 = new User();
        user3.setName("user3");
        user3 = userRepository.save(user3);

        Expense expense1 = new Expense();
        expense1.setAmount(1000);
        expense1.setAddedAt(new Date());
        expense1.setDescription("expense1");
        expense1 = expenseRepository.save(expense1);

        ExpenseUser e1_1 = new ExpenseUser();
        e1_1.setExpense(expense1);
        e1_1.setUser(user1);
        e1_1.setAmount(1000);
        e1_1.setExpenseType(ExpenseType.PAID);
        e1_1 = expenseUserRepository.save(e1_1);

        ExpenseUser e1_2 = new ExpenseUser();
        e1_2.setExpense(expense1);
        e1_2.setUser(user2);
        e1_2.setAmount(300);
        e1_2.setExpenseType(ExpenseType.OWED);
        e1_2 = expenseUserRepository.save(e1_2);

        ExpenseUser e1_3 = new ExpenseUser();
        e1_3.setExpense(expense1);
        e1_3.setUser(user3);
        e1_3.setAmount(300);
        e1_3.setExpenseType(ExpenseType.OWED);
        e1_3 = expenseUserRepository.save(e1_3);

        ExpenseUser e1_4 = new ExpenseUser();
        e1_4.setExpense(expense1);
        e1_4.setUser(user1);
        e1_4.setAmount(400);
        e1_4.setExpenseType(ExpenseType.OWED);
        e1_4 = expenseUserRepository.save(e1_4);

        expense1.setExpenseUsers(List.of(e1_1, e1_2, e1_3, e1_4));
        expense1 = expenseRepository.save(expense1);

        Expense expense2 = new Expense();
        expense2.setAmount(2000);
        expense2.setAddedAt(new Date());
        expense2.setDescription("expense2");
        expense2 = expenseRepository.save(expense2);

        ExpenseUser e2_1 = new ExpenseUser();
        e2_1.setExpense(expense2);
        e2_1.setUser(user1);
        e2_1.setAmount(2000);
        e2_1.setExpenseType(ExpenseType.PAID);
        e2_1 = expenseUserRepository.save(e2_1);

        ExpenseUser e2_2 = new ExpenseUser();
        e2_2.setExpense(expense2);
        e2_2.setUser(user2);
        e2_2.setAmount(500);
        e2_2.setExpenseType(ExpenseType.OWED);
        e2_2 = expenseUserRepository.save(e2_2);

        ExpenseUser e2_3 = new ExpenseUser();
        e2_3.setExpense(expense2);
        e2_3.setUser(user3);
        e2_3.setAmount(1500);
        e2_3.setExpenseType(ExpenseType.OWED);
        e2_3 = expenseUserRepository.save(e2_3);

        expense2.setExpenseUsers(List.of(e2_1, e2_2, e2_3));
        expense2 = expenseRepository.save(expense2);

        GroupExpense groupExpense1 = new GroupExpense();
        groupExpense1.setExpense(expense2);
        groupExpense1 = groupExpenseRepository.save(groupExpense1);

        SettleUserRequestDto settleUserRequestDto = new SettleUserRequestDto();
        settleUserRequestDto.setUserId(user1.getId());
        SettleGroupResponseDto responseDto = settleUpController.settleUser(settleUserRequestDto);
        assertNotNull(responseDto, "Response dto should not be null");
        assertNotNull(responseDto.getTransactions(), "Transactions should not be null");
        assertEquals(2, responseDto.getTransactions().size(), "There should be 2 transactions");

        double totalAmount = 0;
        for(Transaction t: responseDto.getTransactions()){
            assertEquals(user1, t.getPaidTo(), "User1 should be paid");
            totalAmount += t.getAmount();
        }
        assertEquals(600, totalAmount, "User 1 should be paid 2600");

    }

    @Test
    public void testSettleUser_UserNotFound() {
        SettleUserRequestDto settleUserRequestDto = new SettleUserRequestDto();
        settleUserRequestDto.setUserId(1);
        SettleGroupResponseDto responseDto = settleUpController.settleUser(settleUserRequestDto);

        assertNotNull(responseDto, "Response dto should not be null");
        assertNull(responseDto.getTransactions(), "Transactions should be null");
    }




}
