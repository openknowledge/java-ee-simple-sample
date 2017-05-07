package de.openknowledge.application.customer;

import de.openknowledge.domain.customer.Customer;
import de.openknowledge.domain.customer.CustomerRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Sven Koelpin
 */

@Named
@RequestScoped
public class CustomerBean {
    private static final String CUSTOMERS_PAGE = "customers.xhtml?faces-redirect=true";

    @Inject
    private CustomerRepository customerRepository;

    @NotNull
    @Size(min = 3, max = 100)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 100)
    private String lastName;


    private List<Customer> customers;

    @PostConstruct
    public void init() {
        customers = customerRepository.findAll();
    }

    public String createCustomer() {
        customerRepository.persist(new Customer(firstName, lastName));
        //this is to prevent double-form submissions (Post/Redirect/Get-Pattern).
        //got to https://en.wikipedia.org/wiki/Post/Redirect/Get for more information
        return CUSTOMERS_PAGE;
    }

    public void deleteCustomer(Customer customer) {
        //this method is triggered by ajax, so no redirect necessary
        customerRepository.delete(customer);
        init();
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

}

