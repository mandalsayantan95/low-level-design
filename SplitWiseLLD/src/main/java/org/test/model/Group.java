package org.test.model;

import lombok.Data;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Group {
    private String id;
    private String name;
    private List<User> members;
    private List<Expense> expenses;

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
        this.members = new CopyOnWriteArrayList<>();
        this.expenses = new CopyOnWriteArrayList<>();
    }

    public void addMember(User user) {
        members.add(user);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<User> getMembers() {
        return members;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}
