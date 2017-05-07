package de.openknowledge.domain.customer;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sven Koelpin
 */
@ApplicationScoped
public class CustomerRepository {
    private static final Logger LOGGER = Logger.getLogger(CustomerRepository.class.getName());


    @PersistenceContext
    private EntityManager entityManager;

    public List<Customer> findAll() {
        return entityManager
                .createQuery("SELECT c FROM Customer c", Customer.class)
                .getResultList();
    }

    public Optional<Customer> find(Long id) {
        return Optional.ofNullable(entityManager.find(Customer.class, id));
    }

    @Transactional
    public Customer persist(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }

    @Transactional
    public Customer update(Customer customer) {
        return entityManager.merge(customer);
    }

    @Transactional
    public void delete(Customer customer) {
        Optional<Customer> customerToBeDeleted = find(customer.getId());
        if (customerToBeDeleted.isPresent()) {
            entityManager.remove(customerToBeDeleted.get());
        } else {
            //do sth. smarter here in a real application ;)
            LOGGER.log(Level.WARNING, "customer with id {0} is already deleted or does not exist", customer.getId());
        }
    }
}
