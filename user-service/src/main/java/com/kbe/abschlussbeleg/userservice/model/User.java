package com.kbe.abschlussbeleg.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User  {
    @Id
    @Column(name = "userid")
    private String userId;
    private String firstname;
    private String lastname;
    private String password;
}
