package com.onurkus.graduationproject.credit.entity;

import com.onurkus.graduationproject.credit.enums.EnumCreditStatus;
import com.onurkus.graduationproject.customer.entity.Customer;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "CREDIT")
@Data
public class Credit {

    @Id
    @SequenceGenerator(name = "Credit", sequenceName = "CREDIT_ID_SEQ")
    @GeneratedValue(generator = "Credit")
    @Column(name = "CREDIT_ID", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", foreignKey = @ForeignKey(name = "FK_CREDIT_CUSTOMER_ID"))
    private Customer customerId;

    @Column(length = 11, name = "IDENTITY_ID", nullable = false, updatable = false)
    private Long identityId;

    @Column(name = "CREATION_DATE", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false)
    @CreationTimestamp
    private Date creationDate;

    @Column(name = "CREDIT_SCORE", nullable = false)
    private Integer creditScore;

    @Column(name = "CREDIT_STATUS", nullable = false)
    private EnumCreditStatus creditStatus;

    @Column(length = 50, name = "CREDIT_LIMIT", nullable = false)
    private BigDecimal creditLimit;

}
