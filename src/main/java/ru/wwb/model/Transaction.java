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
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private DateTime tDate;

    @ManyToOne
    @JoinColumn(name = "account_id_from")
    private Account accountFrom;

    @ManyToOne
    @JoinColumn(name = "account_id_to")
    private Account accountTo;


    @Column(name = "message")
    private String message;

    @Column(name = "money_transfer_amount", precision=10, scale=2)
    private Double moneyTransferAmount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (tDate != null ? !tDate.equals(that.tDate) : that.tDate != null) return false;
        if (accountFrom != null ? !accountFrom.equals(that.accountFrom) : that.accountFrom != null) return false;
        if (accountTo != null ? !accountTo.equals(that.accountTo) : that.accountTo != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        return !(moneyTransferAmount != null ? !moneyTransferAmount.equals(that.moneyTransferAmount) : that.moneyTransferAmount != null);

    }

    @Override
    public int hashCode() {
        int result = tDate != null ? tDate.hashCode() : 0;
        result = 31 * result + (accountFrom != null ? accountFrom.hashCode() : 0);
        result = 31 * result + (accountTo != null ? accountTo.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (moneyTransferAmount != null ? moneyTransferAmount.hashCode() : 0);
        return result;
    }

    public Double getMoneyTransferAmount() {
        return moneyTransferAmount;
    }

    public void setMoneyTransferAmount(Double moneyTransferAmount) {
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
