package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "{NotNull.Field.Limitation}")
    @Size(min = 1, max = 125, message= "{Size.Field.String}")
    private String username;

    @NotNull(message = "{NotNull.Field.Limitation}")
    @Size(min = 1, max = 125, message= "{Size.Field.String}")
    private String password;

    @NotNull(message = "{NotNull.Field.Limitation}")
    @Size(min = 1, max = 125, message= "{Size.Field.String}")
    private String fullname;

    @NotNull
    @NotEmpty
    @Size(min=1, max=30)
    private String role;

    public User() {
    }

    public User(@NotNull(message = "{NotNull.Field.Limitation}") @Size(min = 1, max = 125, message = "{Size.Field.String}") String username, @NotNull(message = "{NotNull.Field.Limitation}") @Size(min = 1, max = 125, message = "{Size.Field.String}") String password, @NotNull(message = "{NotNull.Field.Limitation}") @Size(min = 1, max = 125, message = "{Size.Field.String}") String fullname, @NotNull @NotEmpty @Size(min = 1, max = 30) String role) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
