package com.kudo.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by brenda on 22/07/2018.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @NotNull
    @JsonProperty
    public int id;

    @Column(name = "login", length = 20, nullable = false)
    @NotNull
    @JsonProperty
    public String login;

    @Column(name = "name", length = 100, nullable = false)
    @NotNull
    @JsonProperty
    public String name;

    @Column(name = "kudo_quantity", nullable = false)
    @NotNull
    @JsonProperty
    public int kudoQuantity;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}