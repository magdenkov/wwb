
package ru.wwb.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import ru.wwb.model.Client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;


@Repository
public class ClientRepositoryImpl implements ClientRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @SuppressWarnings("unchecked")
    public Collection<Client> findAll() {
        return this.em.createQuery("SELECT distinct client FROM Client client  ORDER BY client.lastName, client.firstName").getResultList();
    }

    @Override
    public Client findById(int id) throws DataAccessException {
        Query query = this.em.createQuery("SELECT client FROM Client client left join fetch client.accounts WHERE client.id =:id");
        query.setParameter("id", id);
        return (Client) query.getSingleResult();
    }

    @Override
    public void save(Client client) throws DataAccessException {
        if (client.getId() == null) {
            this.em.persist(client);
        }
        else {
            this.em.merge(client);
        }

    }

}
