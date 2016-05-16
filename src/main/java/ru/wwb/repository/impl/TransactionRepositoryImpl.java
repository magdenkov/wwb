
package ru.wwb.repository.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import ru.wwb.model.Transaction;
import ru.wwb.repository.TransactionRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;


@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @SuppressWarnings("unchecked")
    public Collection<Transaction> findAll() {
        return this.em.createQuery("SELECT  transaction FROM Transaction transaction  ORDER BY transaction.moneyTransferAmount").getResultList();
    }

    @Override
    public Transaction findById(int id) throws DataAccessException {
       return null;

    }

    @Override
    public void save(Transaction transaction) throws DataAccessException {
        if (transaction.getId() == null) {
            this.em.persist(transaction);
        }
        else {
            this.em.merge(transaction);
        }

    }

}
