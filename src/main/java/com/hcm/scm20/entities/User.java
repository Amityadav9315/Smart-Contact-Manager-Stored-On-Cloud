package com.hcm.scm20.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    private String userId;

    @Column(name="user_name", nullable=false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    @Getter(AccessLevel.NONE)

    private String password;

    @Column(length = 100)
    private String about;

    @Column(length = 100)
    private String profilePic;

    private String phoneNumber;

    @Getter(value = AccessLevel.NONE)

    // Account status fields
    private boolean enabled = true;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;

    private String providerUserId;

    // Relationship with Contact entities
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    // UserDetails interface methods
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList=new ArrayList<>();
   

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // list of role(User,admin)
        //collection of SimpleGrandAuthority[roles{admin,user}]
        Collection<SimpleGrantedAuthority> roles=roleList.stream().map(role-> new SimpleGrantedAuthority(role))
        .collect(Collectors.toList());
        
        return roles;
    }
    
    //for this project
    //email id  hai wahi hamara username

    @Override
    public boolean isAccountNonExpired() {
        return true; // Assuming no account expiration logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Assuming no account locking logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Assuming no credentials expiration logic
    }

    @Override
    public String getUsername() {
        return this.email; // Assuming email as username
    }

    @Override
    public boolean isEnabled(){
        return this.enabled;
    }
     
    @Override
    public String getPassword(){
        return this.password;

    }

    public static Object withDefaultPasswordEncoder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'withDefaultPasswordEncoder'");
    }

}
