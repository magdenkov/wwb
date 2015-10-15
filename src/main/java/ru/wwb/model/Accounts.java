package ru.wwb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Accounts {

    private List<Account> accounts;

    @XmlElement
    public List<Account> getAccountList() {
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        return accounts;
    }

}
