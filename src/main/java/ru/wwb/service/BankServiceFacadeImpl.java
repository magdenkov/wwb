
package ru.wwb.service;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.wwb.model.Account;
import ru.wwb.model.Client;
import ru.wwb.model.Transaction;
import ru.wwb.repository.AccountRepository;
import ru.wwb.repository.ClientRepository;
import ru.wwb.repository.TransactionRepository;

import java.util.Collection;


@Service
public class BankServiceFacadeImpl implements BankServiceFacade {


    private AccountRepository accountRepository;
    private ClientRepository clientRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public BankServiceFacadeImpl(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {

        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }



    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "clients")
    public Collection<Client> findClients() throws DataAccessException {
        Collection<Client> clients = clientRepository.findAll();
        for (Client client : clients){
            calcAge(client);
        }

        return clients;
    }

    @Override
    @Transactional
    public void saveClient(Client client) throws DataAccessException {
        calcAge(client);
        clientRepository.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "accounts")
    public Collection<Account> findAllAccounts() throws DataAccessException {
        return accountRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "transactions")
    public Collection<Transaction> findAllTransactions() throws DataAccessException {
        return transactionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Client findClientById(int clientId) throws DataAccessException {
        Client client = clientRepository.findById(clientId);
        calcAge(client);
        return client;
    }

    @Override
    @Transactional
    public void saveAccount(Account account) throws DataAccessException {
        accountRepository.save(account);
        saveTransactionWhenNewAccountCreate(account);
    }

    private void saveTransactionWhenNewAccountCreate(Account account) {
        Transaction transaction = new Transaction();
        transaction.settDate(new DateTime());
        transaction.setAccountFrom(account);
        transaction.setAccountTo(account);
        transaction.setMessage("Client " + account.getClient().getLastName() + " has opened new account");
        transaction.setMoneyTransferAmount(account.getMoneyAmount());
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void saveTransaction(Transaction transaction) throws DataAccessException {
        transferMoney(transaction);
        accountRepository.save(transaction.getAccountTo());
        accountRepository.save(transaction.getAccountFrom());
        transactionRepository.save(transaction);
    }

    private void transferMoney(Transaction transaction) {
        int moneyTransfer = transaction.getMoneyTransferAmount();
        int moneyFrom = transaction.getAccountFrom().getMoneyAmount() - moneyTransfer;
        int moneyTo = transaction.getAccountTo().getMoneyAmount() + moneyTransfer;

        transaction.getAccountTo().setMoneyAmount(moneyTo);
        transaction.getAccountFrom().setMoneyAmount(moneyFrom);
    }

    @Override
    @Transactional(readOnly = true)
    public Account findAccountById(int id) throws DataAccessException {
        return accountRepository.findById(id);
    }

    private void calcAge(Client client) {
        DateTime now = new DateTime();
        int age = Years.yearsBetween(client.getBirthDate(), now).getYears();
        client.setAge(age);
    }


}
