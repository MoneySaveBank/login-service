package org.com.MSBank.login_service.login_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @Column(name = "customer_number")
    private String customerNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "session")
    private Boolean session;

    @Column(name = "status")
    private String status;
}
