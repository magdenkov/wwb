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
