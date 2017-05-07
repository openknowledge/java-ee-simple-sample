package de.openknowledge.domain.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * @author Sven Koelpin
 */
@Entity
@Table(name = "TBL_CUSTOMER")
public final class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null //ensure that only JPA can created IDs (important when creating customers via the HTTP-API)
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "CUSTOMER_FISTNAME", nullable = false, length = 100)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 100)
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

    void setFirstName(String firstName) {
        this.firstName = Objects.requireNonNull(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = Objects.requireNonNull(lastName);
    }

    public Long getId() {
        return id;
    }
}
