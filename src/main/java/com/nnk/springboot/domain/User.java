package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Size(max = 125, message= "{Size.Field.String}")
    @NotEmpty(message = "{Empty.Field.String}")
    private String username;

    @NotEmpty(message = "{Empty.Field.String}")
    @Size(max = 125, message = "{Size.Field.String}")
    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~\\]])[a-zA-Z0-9!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~\\]]{8,}$", message="{Password.Constraint.Validation}")
    private String password;

    @Size(max = 125, message= "{Size.Field.String}")
    @NotEmpty(message = "{Empty.Field.String}")
    private String fullname;

    @Size(max = 125, message= "{Size.Field.String}")
    @NotEmpty(message = "{Empty.Field.String}")
    private String role;

    public User() {
    }

    public User(@Size(max = 125, message = "{Size.Field.String}") @NotEmpty(message = "{Empty.Field.String}") String username, @NotEmpty(message = "{Empty.Field.String}") @Size(max = 125, message = "{Size.Field.Password}") @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~\\]])[a-zA-Z0-9!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~\\]]{8,}$", message = "The password is incorrect") String password, @Size(max = 125, message = "{Size.Field.String}") @NotEmpty(message = "{Empty.Field.String}") String fullname, @Size(max = 125, message = "{Size.Field.String}") @NotEmpty(message = "{Empty.Field.String}") String role) {
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
