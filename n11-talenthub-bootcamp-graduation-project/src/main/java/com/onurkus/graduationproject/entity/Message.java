package com.onurkus.graduationproject.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGE")
@Data
public class Message implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_MESSAGE_USER_ID"))
    private User userId;

    @Column(length = 160, name = "MESSAGE_CONTENT", nullable = false)
    private String messageContent;
}
