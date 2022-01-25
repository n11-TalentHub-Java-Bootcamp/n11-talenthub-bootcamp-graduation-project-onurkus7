package com.onurkus.graduationproject.customer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onurkus.graduationproject.gen.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "CUSTOMER")
@Data
public class Customer implements BaseEntity {

    @Id
    @SequenceGenerator(name = "Customer", sequenceName = "CUSTOMER_ID_SEQ")
    @GeneratedValue(generator = "Customer")
    @Column(name = "CUSTOMER_ID", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(length = 11, name = "IDENTITY_ID", nullable = false, updatable = false, unique = true)
    private Long identityId;

    @Column(name = "REGISTRATION_DATE", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @Column(length = 50, name = "FULL_NAME", nullable = false)
    private String fullName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "BIRTHDAY_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthdayDate;

    @Column(name = "PHONE_NUMBER", unique = true)
    private String phoneNumber;

    @Column(name = "SALARY", nullable = false)
    private BigDecimal salary;

    @Column(name = "COLLATERAL", nullable = false)
    private BigDecimal collateral;

}
