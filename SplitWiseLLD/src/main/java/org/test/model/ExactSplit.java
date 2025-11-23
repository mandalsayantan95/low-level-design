package org.test.model;

public class ExactSplit extends Split {
    public ExactSplit(User user) {
        super(user);
    }

    @Override
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

