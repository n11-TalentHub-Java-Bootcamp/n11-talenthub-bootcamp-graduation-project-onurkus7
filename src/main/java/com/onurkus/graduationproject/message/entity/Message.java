package com.onurkus.graduationproject.message.entity;

import com.onurkus.graduationproject.customer.entity.Customer;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGES")
@Data
public class Message {

    @Id
    @SequenceGenerator(name = "Messages", sequenceName = "MESSAGES_ID_SEQ")
    @GeneratedValue(generator = "Messages")
    @Column(name = "MESSAGES_ID", nullable = false, unique = true)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", foreignKey = @ForeignKey(name = "FK_MESSAGES_CUSTOMER_ID"))
    private Customer customerId;

    @Column(length = 11, name = "IDENTITY_ID", nullable = false, updatable = false)
    private Long identityId;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(length = 200, name = "MESSAGES_CONTENT")
    private String messageContent;

}
