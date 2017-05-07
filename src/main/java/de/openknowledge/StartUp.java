package de.openknowledge;

import de.openknowledge.domain.customer.Customer;
import de.openknowledge.domain.customer.CustomerRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * @author Sven Koelpin
 */
@Startup
@Singleton
public class StartUp {

    @Inject
    private CustomerRepository customerRepository;

    @PostConstruct
    public void init() {
        customerRepository.persist(new Customer("Erika", "Musterfrau"));
        customerRepository.persist(new Customer("Max", "Mustermann"));
    }
}
