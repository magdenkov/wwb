
package ru.wwb.repository;

import org.springframework.dao.DataAccessException;
import ru.wwb.model.Account;

import java.util.Collection;

public interface AccountRepository {

    Collection<Account> findAll() throws DataAccessException;


    Account findById(int id) throws DataAccessException;


    void save(Account account) throws DataAccessException;

}
