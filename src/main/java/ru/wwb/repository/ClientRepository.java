
package ru.wwb.repository;

import org.springframework.dao.DataAccessException;
import ru.wwb.model.Client;

import java.util.Collection;


public interface ClientRepository {

    Collection<Client> findAll() throws DataAccessException;


    Client findById(int id) throws DataAccessException;

    void save(Client client) throws DataAccessException;

}
