package ru.wwb.service;

import org.joda.time.DateTime;
import org.joda.time.Years;
import ru.wwb.model.Account;
import ru.wwb.model.Client;
import ru.wwb.model.Transaction;

public class BSDelegate {
    private final BankServiceFacadeImpl bankServiceFacadeImpl;

    public BSDelegate(BankServiceFacadeImpl bankServiceFacadeImpl) {
        this.bankServiceFacadeImpl = bankServiceFacadeImpl;
    }

    Transaction prepareTransactionForNewAccountCreate(Account account) {
        Transaction transaction = new Transaction();
        transaction.settDate(new DateTime());
        transaction.setAccountFrom(account);
        transaction.setAccountTo(account);
        transaction.setMessage("Client " + account.getClient().getLastName() + " has opened new account");
        transaction.setMoneyTransferAmount(account.getMoneyAmount());
        return transaction;
    }

    void calcAge(Client client) {
        DateTime now = new DateTime();
        int age = Years.yearsBetween(client.getBirthDate(), now).getYears();
        client.setAge(age);
    }

    void transferMoney(Transaction transaction) {
        Double moneyTransfer = transaction.getMoneyTransferAmount();
        Double moneyFrom = transaction.getAccountFrom().getMoneyAmount() - moneyTransfer;
        Double moneyTo = transaction.getAccountTo().getMoneyAmount() + moneyTransfer;

        transaction.getAccountTo().setMoneyAmount(moneyTo);
        transaction.getAccountFrom().setMoneyAmount(moneyFrom);
    }
}