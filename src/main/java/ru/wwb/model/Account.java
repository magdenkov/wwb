
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

        if (getClient() != null ? !getClient().equals(account.getClient()) : account.getClient() != null) return false;
        return !(getMoneyAmount() != null ? !getMoneyAmount().equals(account.getMoneyAmount()) : account.getMoneyAmount() != null);

    }

    @Override
    public int hashCode() {
        int result = getClient() != null ? getClient().hashCode() : 0;
        result = 31 * result + (getMoneyAmount() != null ? getMoneyAmount().hashCode() : 0);
        return result;
    }
}
