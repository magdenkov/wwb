
package ru.wwb.service;

import org.springframework.dao.DataAccessException;
import ru.wwb.exception.NotEnoughMoneyException;
import ru.wwb.model.Account;
import ru.wwb.model.Client;
import ru.wwb.model.Transaction;

import java.util.Collection;


public interface BankServiceFacade {


    Collection<Client> findClients() throws DataAccessException;

    void saveClient(Client client) throws DataAccessException;

    Collection<Account> findAllAccounts() throws DataAccessException;

    Collection<Transaction> findAllTransactions() throws DataAccessException;

    Client findClientById(int clientId) throws DataAccessException;

    void saveAccount(Account account) throws DataAccessException;

    void saveTransaction(Transaction transaction) throws DataAccessException, NotEnoughMoneyException;

    Account findAccountById(int id) throws DataAccessException;
}
