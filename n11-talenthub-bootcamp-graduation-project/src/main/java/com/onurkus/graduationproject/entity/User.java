package com.onurkus.graduationproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "USERS")
@Data
public class User implements BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false, unique = true)
    private Long id;

    @Column(length = 11, name = "IDENTITY_ID", nullable = false, unique = true)
    private Long identityId;

    @Column(name = "REGISTRATION_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @Column(length = 50, name = "FULL_NAME", nullable = false)
    private String fullName;

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
