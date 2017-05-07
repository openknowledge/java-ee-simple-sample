package de.openknowledge.domain.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author Sven Koelpin
 */
@Entity
@Table(name = "TBL_CUSTOMER")
public final class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CUSTOMER_FISTNAME", nullable = false, length = 100)
    private String firstName;
    @Column(name = "CUSTOMER_LASTNAME", nullable = false, length = 100)
    private String lastName;


    public Customer(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    protected Customer() {
    }

    public String getFirstName() {
        return firstName;
    }

    protected void setFirstName(String firstName) {
        this.firstName = Objects.requireNonNull(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    protected void setLastName(String lastName) {
        this.lastName = Objects.requireNonNull(lastName);
    }

    public Long getId() {
        return id;
    }
}
