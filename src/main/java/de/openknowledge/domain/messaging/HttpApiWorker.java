package de.openknowledge.domain.messaging;

import de.openknowledge.application.customer.CustomerClient;
import de.openknowledge.domain.customer.Customer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedExecutorService;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sven Koelpin
 */
@Startup
@Singleton
public class HttpApiWorker {
    private static final Logger LOGGER = Logger.getLogger(HttpApiWorker.class.getName());
    private static final long THIRTY_SECONDS = 30000L;

    @Resource
    private ManagedExecutorService executorService;


    private CustomerClient customerClient;

    @PostConstruct
    public void init() {
        //create an HTTP-Client and request + create customers every 30 seconds
        customerClient = new CustomerClient();

        executorService.execute(() -> {
            try {
                Thread.sleep(THIRTY_SECONDS);

                List<Customer> all = customerClient.getAll();
                LOGGER.log(Level.INFO, "Requested {0} customers via the HTTP-API", all.size());

                Random random = new Random();
                customerClient.createCustomer(new Customer("FN_" + random.nextInt(), "LN_" + random.nextInt()));
                LOGGER.log(Level.INFO, "Created new random customer via the HTTP-API");

            } catch (InterruptedException e) {
                LOGGER.log(Level.WARNING, e.getMessage(), e);
            }
        });
    }
}
