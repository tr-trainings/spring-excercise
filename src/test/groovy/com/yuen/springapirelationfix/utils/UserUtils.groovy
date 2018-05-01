package com.yuen.springapirelationfix.utils

import com.github.javafaker.Faker
import com.yuen.springapirelationfix.entities.Address
import com.yuen.springapirelationfix.entities.ContactInfo
import com.yuen.springapirelationfix.entities.Name
import com.yuen.springapirelationfix.entities.User
import com.yuen.springapirelationfix.enums.Gender

class UserUtils {

    def faker = new Faker()

    def createUser(){
        return new User(
                id: 0,
                name: new Name(
                        firstName: faker.name().firstName(),
                        middleName: faker.name().lastName(),
                        lastName: faker.name().lastName(),
                        suffix: faker.name().suffix()
                ),
                gender: Gender.MALE.toString(),
                contactInfo: new ContactInfo(
                        email: "${faker.name().username()}@gmail.com",
                        mobile :"${faker.number().digits(10)}",
                        telephone: "${faker.number().digits(10)}"
                ),
                address: new Address(
                        street: faker.address().streetAddress(),
                        city: faker.address().city(),
                        state: faker.address().state(),
                        zipCode: 100
                ))
    }


//    def createUser(){
//       return new User(
//               id: 0,
//               name: name,
//               gender: Gender.MALE.toString(),
//               contactInfo: contactInfo,
//               address: address)
//    }
//
//    def address = new Address(
//            street: faker.address().streetAddress(),
//            city: faker.address().city(),
//            state: faker.address().state(),
//            zipCode: 100
//    )
//
//    def contactInfo = new ContactInfo(
//            email: "${faker.name().username()}@gmail.com",
//            mobile :"${faker.number().digits(10)}",
//            telephone: "${faker.number().digits(10)}"
//    )
//
//    def name = new Name(
//            firstName: faker.name().firstName(),
//            middleName: faker.name().lastName(),
//            lastName: faker.name().lastName(),
//            suffix: faker.name().suffix()
//    )

}
