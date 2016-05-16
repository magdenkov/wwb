
package ru.wwb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.wwb.exception.NotEnoughMoneyException;
import ru.wwb.model.Account;
import ru.wwb.model.Client;
import ru.wwb.model.Transaction;
import ru.wwb.repository.AccountRepository;
import ru.wwb.repository.ClientRepository;
import ru.wwb.repository.TransactionRepository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.Collection;


@Service
public class BankServiceFacadeImpl implements BankServiceFacade {



    private AccountRepository accountRepository;
    private ClientRepository clientRepository;
    private TransactionRepository transactionRepository;
    @Autowired
    EntityManager entityManager;

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
        transactionRepository.save(new BSDelegate().prepareTransactionForNewAccountCreate(account));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void saveTransaction(Transaction transaction) throws DataAccessException, NotEnoughMoneyException {
        Account accountFrom = accountRepository.findById(transaction.getAccountFrom().getId());
        Account accountTo = accountRepository.findById(transaction.getAccountTo().getId());

        entityManager.lock(accountFrom, LockModeType.PESSIMISTIC_WRITE);
        entityManager.lock(accountTo, LockModeType.PESSIMISTIC_WRITE);

        Double moneyTransfer = transaction.getMoneyTransferAmount();
        Double moneyFrom = accountFrom.getMoneyAmount() - moneyTransfer;

        if (moneyFrom < 0.0) {
            throw new NotEnoughMoneyException("Not enough money on Account " + transaction.getAccountFrom().getId());
        }

        Double moneyTo = transaction.getAccountTo().getMoneyAmount() + moneyTransfer;

        transaction.getAccountTo().setMoneyAmount(moneyTo);
        transaction.getAccountFrom().setMoneyAmount(moneyFrom);

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
