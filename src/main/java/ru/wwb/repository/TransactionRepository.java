
package ru.wwb.repository;

import org.springframework.dao.DataAccessException;
import ru.wwb.model.Transaction;

import java.util.Collection;


public interface TransactionRepository {

    Collection<Transaction> findAll() throws DataAccessException;


    Transaction findById(int id) throws DataAccessException;

    void save(Transaction client) throws DataAccessException;

}
