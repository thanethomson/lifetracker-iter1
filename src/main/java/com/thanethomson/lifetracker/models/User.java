package com.thanethomson.lifetracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    private String email;

    private String firstName;

    private String lastName;

    /**
     * We store a salted hash of the user's password instead of the raw password itself.
     */
    @JsonIgnore
    private String passwordHash;

    /**
     * Has the user's e-mail address been confirmed?
     */
    private Boolean emailConfirmed;

    /**
     * The token to use when verifying the user's e-mail address.
     */
    @JsonIgnore
    private String emailConfirmationToken;

    /**
     * A unique token to e-mail to a user to allow them to reset their password.
     */
    @JsonIgnore
    private String passwordResetToken;

}
