package de.openknowledge.domain.messaging;

import de.openknowledge.domain.customer.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sven Koelpin
 */

@ApplicationScoped
public class SmsService {
    private static final Logger LOGGER = Logger.getLogger(SmsService.class.getName());

    public void sendMail(@Observes Customer newCustomer) {
        LOGGER.log(Level.INFO, "Sending E-SMS notification to customer {0}", newCustomer.getId());
    }
}
