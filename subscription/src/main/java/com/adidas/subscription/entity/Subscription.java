package com.adidas.subscription.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "consent")
    private Boolean consent;

    @Column(name = "newsletter_id")
    private Long newsletterId;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "updated")
    private Date updated;

}
