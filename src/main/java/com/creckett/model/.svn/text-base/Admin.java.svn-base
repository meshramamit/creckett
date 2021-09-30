package com.creckett.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADMIN_INFO")
public class Admin {

    @Column(name = "USER_NAME")
    @Id
    private String username;

    @Column(name = "PASSWORD")
    private String password;

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
	this.password = String.format("%x", new BigInteger(1, password
		.getBytes()));
    }
}
