package ru.wwb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Transactions {

    private List<Transaction> transactions;

    @XmlElement
    public List<Transaction> getTransactionList() {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }
        return transactions;
    }

}
