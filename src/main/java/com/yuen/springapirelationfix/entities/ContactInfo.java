package com.yuen.springapirelationfix.entities;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;

@Embeddable
@Data
public class ContactInfo {

    @Email(message = "email must be a in valid form")
    private String email;
    private String mobile, telephone;
}
