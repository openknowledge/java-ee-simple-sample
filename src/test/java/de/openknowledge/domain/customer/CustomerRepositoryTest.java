package de.openknowledge.domain.customer;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Sven Koelpin
 */
public class CustomerRepositoryTest {

    private EntityManagerFactory factory;
    private EntityManager entityManager;

    private CustomerRepository customerRepository;


    @Test
    public void findAll() throws Exception {
        assertThat(customerRepository.findAll().isEmpty(), is(true));
        persistCustomers();
        assertThat(customerRepository.findAll().size(), is(3));
    }

    @Test
    public void find() throws Exception {
        persistCustomers();
        assertThat(customerRepository.find(1000L).isPresent(), is(false));
        assertThat(customerRepository.find(1L).isPresent(), is(true));
    }

    @Test
    public void persist() throws Exception {
        Customer persisted = new Customer("testFn", "testLn");
        assertThat(persisted.getId(), is(nullValue()));
        entityManager.getTransaction().begin();
        customerRepository.persist(persisted);
        entityManager.getTransaction().commit();
        assertThat(persisted.getId(), is(1L));
    }

    @Test
    public void update() throws Exception {
        entityManager.getTransaction().begin();
        Customer toBeUpdated = customerRepository.persist(new Customer("testFn", "testLn"));
        entityManager.getTransaction().commit();
        toBeUpdated.setFirstName("newFn");
        entityManager.getTransaction().begin();
        customerRepository.update(toBeUpdated);
        entityManager.getTransaction().commit();
        assertThat(customerRepository.find(toBeUpdated.getId()).get().getFirstName(), is("newFn"));
    }

    @Test
    public void delete() throws Exception {
        persistCustomers();
        Customer toBeDeleted = customerRepository.find(1L).get();
        entityManager.getTransaction().begin();
        customerRepository.delete(toBeDeleted);
        entityManager.getTransaction().commit();
        assertThat(customerRepository.findAll().size(), is(2));
    }


    @Before
    public void init() throws Exception {
        factory = Persistence.createEntityManagerFactory("test");
        entityManager = factory.createEntityManager();
        customerRepository = new CustomerRepository(entityManager);
    }


    @After
    public void destroy() {
        entityManager.close();
        factory.close();
    }

    private void persistCustomers() {
        entityManager.getTransaction().begin();
        customerRepository.persist(new Customer("testFirstName_a", "testLastName_a"));
        customerRepository.persist(new Customer("testFirstName_b", "testLastName_b"));
        customerRepository.persist(new Customer("testFirstName_c", "testLastName_c"));
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

}