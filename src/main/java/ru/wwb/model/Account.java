
package ru.wwb.model;

import javax.persistence.*;



@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "money_amount", precision=10, scale=2)
    private Double moneyAmount;


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Double moneyAmount) {
        this.moneyAmount = moneyAmount;
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
