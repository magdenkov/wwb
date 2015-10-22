
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (!client.equals(account.client)) return false;
        return moneyAmount.equals(account.moneyAmount);

    }

    @Override
    public int hashCode() {
        int result = client.hashCode();
        result = 31 * result + moneyAmount.hashCode();
        return result;
    }
}
