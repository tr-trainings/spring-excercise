package com.yuen.springapirelationfix.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.yuen.springapirelationfix.SpringApiRelationFixApplication
import com.yuen.springapirelationfix.utils.UserUtils
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ContextConfiguration
@WebAppConfiguration
@SpringBootTest(classes = SpringApiRelationFixApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Transactional
class BaseSpec extends Specification{

    @Shared
    protected MockMvc mockMvc

    @Autowired
    protected WebApplicationContext context

    def userUtils = new UserUtils()

    ObjectMapper mapper = new ObjectMapper()

    @Before
    def setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }

    def get(String url) {
        return mockMvc.perform( MockMvcRequestBuilders.get(url) )
                .andDo( MockMvcResultHandlers.print() )
                .andExpect( status().isOk() )
                .andExpect( content().contentType(MediaType.APPLICATION_JSON_UTF8) )
                .andReturn()
    }

    def post(String url, data) {
       return mockMvc.perform( MockMvcRequestBuilders.post(url)
                .contentType( MediaType.APPLICATION_JSON_UTF8 )
                .content( mapper.writeValueAsString(data) ))
                .andDo( MockMvcResultHandlers.print() )
                .andExpect(status().isCreated())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON_UTF8) )
                .andReturn()
    }

    def put(String url, data) {
        return mockMvc.perform( MockMvcRequestBuilders.put(url)
                .contentType( MediaType.APPLICATION_JSON_UTF8 )
                .content( mapper.writeValueAsString(data) ))
                .andDo( MockMvcResultHandlers.print() )
                .andExpect( status().isOk() )
                .andExpect( content().contentType(MediaType.APPLICATION_JSON_UTF8) )
                .andReturn()
    }

    def delete(String url) {
        return mockMvc.perform( MockMvcRequestBuilders.delete(url) )
                .andDo( MockMvcResultHandlers.print() )
                .andExpect( status().isOk() )
                .andExpect( content().contentType(MediaType.APPLICATION_JSON_UTF8) )
                .andReturn()
    }


}
