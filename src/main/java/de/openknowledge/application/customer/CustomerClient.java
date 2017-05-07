package de.openknowledge.application.customer;

import de.openknowledge.domain.customer.Customer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Sven Koelpin
 */

public class CustomerClient {

    private WebTarget webTarget;
    private Client client;

    public CustomerClient() {
        client = ClientBuilder.newClient();
        webTarget = client.target("http://localhost:8080/java-ee-simple-sample-1.0-SNAPSHOT/api/").path("customers");
    }

    public List<Customer> getAll() {
        return webTarget
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Customer>>() {
                });
    }

    public void createCustomer(Customer customer) {
        webTarget
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(customer, MediaType.APPLICATION_JSON));
    }


    public void close() {
        client.close();
    }

}
