
package ru.wwb.service;

import org.springframework.beans.factory.annotation.Autowired;
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


    private final BSDelegate BSDelegate = new BSDelegate(this);
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
    public Collection<Client> findClients() throws DataAccessException {
        Collection<Client> clients = clientRepository.findAll();

        return clients;
    }

    @Override
    @Transactional
    public void saveClient(Client client) throws DataAccessException {
        clientRepository.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Account> findAllAccounts() throws DataAccessException {
        return accountRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Transaction> findAllTransactions() throws DataAccessException {
        return transactionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Client findClientById(int clientId) throws DataAccessException {
        Client client = clientRepository.findById(clientId);
        return client;
    }

    @Override
    @Transactional
    public void saveAccount(Account account) throws DataAccessException {
        accountRepository.save(account);
        transactionRepository.save(BSDelegate.prepareTransactionForNewAccountCreate(account));
    }

    @Override
    @Transactional
    public void saveTransaction(Transaction transaction) throws DataAccessException {
        BSDelegate.transferMoney(transaction);
        accountRepository.save(transaction.getAccountTo());
        accountRepository.save(transaction.getAccountFrom());
        transactionRepository.save(transaction);
    }



    @Override
    @Transactional(readOnly = true)
    public Account findAccountById(int id) throws DataAccessException {
        return accountRepository.findById(id);
    }

}
