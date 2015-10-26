
package ru.wwb.repository.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import ru.wwb.model.Account;
import ru.wwb.repository.AccountRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;


@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Account> findAll() throws DataAccessException {
        return this.em.createQuery
                ("SELECT distinct account FROM Account account  ORDER BY account.id").getResultList();


    }

    @Override
    public Account findById(int id) throws DataAccessException {
          return this.em.find(Account.class, id);
    }

    @Override
    public void save(Account account) throws DataAccessException {
        if (account.getId() == null) {
            this.em.persist(account);
        }
        else {
            this.em.merge(account);
        }
    }


}
