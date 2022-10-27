package com.dss.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity(name="admin")
@Getter
@Setter
public class Admin {
    private int id;
    private String firstName;
    private String lastName;
    private String password;
}
