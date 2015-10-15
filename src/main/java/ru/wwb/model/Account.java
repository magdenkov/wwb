
package ru.wwb.model;

import javax.persistence.*;



@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "money_amount")
    private Integer moneyAmount;


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Integer moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString() {
        return "ID" + getId() + " " + getClient().getFirstName() + " " + getClient().getLastName() + " " + getMoneyAmount() + "$";
    }
}
