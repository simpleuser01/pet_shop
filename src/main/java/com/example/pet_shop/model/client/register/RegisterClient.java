package com.example.pet_shop.model.client.register;


import com.example.pet_shop.model.client.Role;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "register_client")
public class RegisterClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private long clientId;

    @Column(name = "client_first_name")
    private String firstName;

    @Column(name = "client_last_name")
    private String lastName;


    @Column(name = "client_email")
    private String email;

    @Column(name = "client_tel")
    private int tel;

    @Column(name = "client_password")
    private String password;

    @Column(name = "client_username")
    private String userName;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_role",
            joinColumns = @JoinColumn(name = "client_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "role_id_fk"))
    Set<Role> roles;

    public RegisterClient() {
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
