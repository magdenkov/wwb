package ru.wwb.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    @Column(name = "t_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private DateTime tDate;

    @ManyToOne
    @JoinColumn(name = "account_id_from")
    private Account accountFrom;

    @ManyToOne
    @JoinColumn(name = "account_id_to")
    private Account accountTo;


    @Column(name = "message")
    private String message;

    @Column(name = "money_transfer_amount")
    private Integer moneyTransferAmount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (gettDate() != null ? !gettDate().equals(that.gettDate()) : that.gettDate() != null) return false;
        if (!getAccountFrom().equals(that.getAccountFrom())) return false;
        if (!getAccountTo().equals(that.getAccountTo())) return false;
        if (!getMessage().equals(that.getMessage())) return false;
        return !(getMoneyTransferAmount() != null ? !getMoneyTransferAmount().equals(that.getMoneyTransferAmount()) : that.getMoneyTransferAmount() != null);

    }

    @Override
    public int hashCode() {
        int result = gettDate() != null ? gettDate().hashCode() : 0;
        result = 31 * result + getAccountFrom().hashCode();
        result = 31 * result + getAccountTo().hashCode();
        result = 31 * result + getMessage().hashCode();
        result = 31 * result + (getMoneyTransferAmount() != null ? getMoneyTransferAmount().hashCode() : 0);
        return result;
    }

    public Integer getMoneyTransferAmount() {
        return moneyTransferAmount;
    }

    public void setMoneyTransferAmount(Integer moneyTransferAmount) {
        this.moneyTransferAmount = moneyTransferAmount;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public DateTime gettDate() {
        return tDate;
    }

    public void settDate(DateTime tDate) {
        this.tDate = tDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
