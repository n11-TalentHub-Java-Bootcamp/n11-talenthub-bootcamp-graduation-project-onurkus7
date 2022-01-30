package com.onurkus.graduationproject.customer.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "CUSTOMER")
@Data
public class Customer {

    @Id
    @SequenceGenerator(name = "Customer", sequenceName = "CUSTOMER_ID_SEQ")
    @GeneratedValue(generator = "Customer")
    @Column(name = "CUSTOMER_ID", updatable = false, unique = true)
    private Long id;

    @Column(length = 11, name = "IDENTITY_ID", nullable = false, unique = true)
    private Long identityId;

    @Column(name = "REGISTRATION_DATE", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false)
    @CreationTimestamp
    private Date registrationDate;

    @Column(length = 50, name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "BIRTHDAY_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthdayDate;

    @Column(name = "PHONE_NUMBER", unique = true)
    private String phoneNumber;

    @Column(name = "SALARY", nullable = false)
    private BigDecimal salary;

    @Column(name = "COLLATERAL", nullable = false)
    private BigDecimal collateral;

}
