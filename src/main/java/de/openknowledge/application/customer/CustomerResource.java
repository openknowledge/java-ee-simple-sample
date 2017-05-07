package de.openknowledge.application.customer;

import de.openknowledge.domain.customer.Customer;
import de.openknowledge.domain.customer.CustomerRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * @author Sven Koelpin
 */
@Path(CustomerResource.PATH)
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class CustomerResource {
    static final String PATH = "customers";

    @Inject
    private CustomerRepository customerRepository;

    @GET
    public Response getAll(@QueryParam("from") @DefaultValue("0") @Min(0) @Max(100) Integer start,
                           @QueryParam("size") @DefaultValue("5") @Min(1) @Max(10) Integer size) {
        //In a good API-design, pagination (via an envelope or link-headers) would be added to a response that returns a collection.
        //But this is not in the scope of this demo
        List<Customer> customers = customerRepository.findAll(start, size);
        return Response.ok(customers).build();
    }

    @GET
    @Path("{customerNo}")
    public Response getCustomer(@PathParam("customerNo") Long customerNo) {
        Optional<Customer> customer = customerRepository.find(customerNo);
        if (customer.isPresent()) {
            return Response.ok(customer.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response createCustomer(@Valid Customer customer) {
        Customer createdCustomer = customerRepository.persist(customer);
        return Response.created(URI.create(PATH + "/" + createdCustomer.getId())).entity(createdCustomer).build();
    }

}
