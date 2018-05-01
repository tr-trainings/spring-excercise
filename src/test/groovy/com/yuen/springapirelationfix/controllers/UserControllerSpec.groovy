package com.yuen.springapirelationfix.controllers

import com.yuen.springapirelationfix.entities.User

class UserControllerSpec extends BaseSpec{

    def "should able to fetch all users data"() {

        when: 'we make a request on /api/v1/users'
            def getResponse = get("/api/v1/users")

        then: 'we assert that the request is successful'
            getResponse.response.status == 200
            getResponse.response.contentAsString == "[]"

    }

    def "should able to add user data && should be able to fetch user data by id"() {

        given: 'we create user post request'
            def postRequest = userUtils.createUser()

        when: 'we send a post request on /api/v1/users'
            def postResponse = post("/api/v1/users", postRequest)
            def postResponseId = mapper.readValue(postResponse.response.contentAsString, User.class).id


        then: 'we send a get request to see if the value has been saved'
            def getResponse = get("/api/v1/users/${postResponseId}")

        expect: 'we assert that both request are successful'
            postResponse.response.status == 201
            getResponse.response.status == 200
            getResponse.response.contentAsString == postResponse.response.contentAsString

    }

    def "should able to update user data"() {

        given: 'we create user post request'
            def postRequest = userUtils.createUser()
            def putRequest = userUtils.createUser()

        when: 'we send a post request on /api/v1/users'
            def postResponse = post("/api/v1/users", postRequest)
            def postResponseId = mapper.readValue(postResponse.response.contentAsString, User.class).id

        and: 'we send a get request to see if the value has been saved'
            def getResponseNotUpdated = get("/api/v1/users/${postResponseId}")

        and: 'we send a put request to update to value'
            def putResponse = put("/api/v1/users/${postResponseId}", putRequest)

        then: 'we send a get request to see if the value has been updated'
            def getResponseUpdated = get("/api/v1/users/${postResponseId}")

        expect: 'we assert all request are successful'
            postResponse.response.status == 201
            getResponseNotUpdated.response.status == 200
            putResponse.response.status == 200
            getResponseUpdated.response.status == 200
            getResponseUpdated.response.contentAsString == putResponse.response.contentAsString

    }

    def "should able to delete user data"() {

        given: 'we create user post request'
            def postRequest = userUtils.createUser()

        when: 'we send a post request on /api/v1/users'
            def postResponse = post("/api/v1/users", postRequest)
            def postResponseId = mapper.readValue(postResponse.response.contentAsString, User.class).id


        then: 'we delete the data we add'
            def deleteResponse = delete("/api/v1/users/${postResponseId}")

        expect: 'we assert the data was deleted'
            postResponse.response.status == 201
            deleteResponse.response.status == 200
    }
}
