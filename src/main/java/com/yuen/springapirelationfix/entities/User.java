package com.yuen.springapirelationfix.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.yuen.springapirelationfix.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonPropertyOrder(value = {"id", "name", "gender", "address", "contact_info"})
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
//    @Valid
    private Name name;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;


    @Embedded
    @JsonProperty("contact_info")
//    @Valid
    private ContactInfo contactInfo;

    @Embedded
//    @Valid
    private  Address address;


}
