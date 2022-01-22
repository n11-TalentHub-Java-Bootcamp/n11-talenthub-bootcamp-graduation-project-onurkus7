package com.onurkus.graduationproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CREDIT")
@Data
public class Credit implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_CREDIT_USER_ID"))
    private User userId;

    @Column(name = "CREDIT_SCORE", nullable = false)
    private Integer creditScore;

    @Column(name = "CREDIT_STATUS", nullable = false)
    private Enum creditStatus;

    @Column(length = 50, name = "CREDIT_LIMIT", nullable = false)
    private BigDecimal creditLimit;

}
