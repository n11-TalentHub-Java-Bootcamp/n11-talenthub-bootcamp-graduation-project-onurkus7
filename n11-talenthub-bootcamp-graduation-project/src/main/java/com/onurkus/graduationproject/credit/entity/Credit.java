package com.onurkus.graduationproject.credit.entity;

import com.onurkus.graduationproject.credit.enums.EnumCreditStatus;
import com.onurkus.graduationproject.customer.entity.Customer;
import com.onurkus.graduationproject.gen.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CREDIT")
@Data
public class Credit implements BaseEntity {

    @Id
    @SequenceGenerator(name = "Credit", sequenceName = "CREDIT_ID_SEQ")
    @GeneratedValue(generator = "Credit")
    @Column(name = "CREDIT_ID", nullable = false, unique = true)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", foreignKey = @ForeignKey(name = "FK_CREDIT_CUSTOMER_ID"))
    private Customer customerId;

    @Column(name = "CREDIT_SCORE", nullable = false)
    private Integer creditScore;

    @Column(name = "CREDIT_STATUS", nullable = false)
    private EnumCreditStatus creditStatus;

    @Column(length = 50, name = "CREDIT_LIMIT", nullable = false)
    private BigDecimal creditLimit;

}
