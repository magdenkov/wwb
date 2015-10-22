package ru.wwb.model;

import java.util.ArrayList;
import java.util.List;


public class Transactions {

    private List<Transaction> transactions;

    public List<Transaction> getTransactionList() {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }
        return transactions;
    }

}
