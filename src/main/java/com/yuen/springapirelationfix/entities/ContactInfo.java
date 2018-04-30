package com.yuen.springapirelationfix.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;

@Embeddable
@Data
class ContactInfo {

    @Email(message = "email must be a in valid form")
    private String email;

    private String mobile, telephone;
}
