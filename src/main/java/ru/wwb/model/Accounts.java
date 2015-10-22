package ru.wwb.model;

import java.util.ArrayList;
import java.util.List;


public class Accounts {

    private List<Account> accounts;


    public List<Account> getAccountList() {
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        return accounts;
    }

}
