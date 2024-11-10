package com.hcm.scm20.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Entity(name = "user")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class User {

    @Id
    private String userId;
    @Column(name="user_name",nullable=false)
    private String name;
    @Column(unique = true,nullable = false)
    private String email;

    private String password;
    @Column(length = 100)

    private String about;
    @Column(length = 100)

    private String profilePic;

    private String phoneNumber;
    //information
    private boolean enabled=false;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;

    //self google git linkdin
    private Providers provider=Providers.SELF;
    private String providerUserId;

    //add more filed if needede
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact>contacts=new ArrayList<>();
    





}
 
