/**
 * Created by Dilip Kancharla on Jun 28, 2015
 */
package com.dell.credant.ws.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dell.credant.core.model.entities.PasswordPolicy;
import com.dell.credant.core.service.PasswordPolicyService;
import com.dell.credant.core.service.exceptions.InvalidHostException;
import com.dell.credant.ws.controller.util.PolicyConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * @author Dilip Reddy Kancharla .
 * Test PasswordPolicy Controller
 */
public class PasswordPolicyTest {

       @InjectMocks
       private PasswordPolicyController controller;

       @Mock
       private PasswordPolicyService service;

       private MockMvc mockMvc;

       @Before
       public void setup() {
            MockitoAnnotations.initMocks(this); // Mock objects in test class
            mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        }
       /**
        * Positive Test from queuing functionality
        * @throws Exception
        */
        @Test
        public void queuePolicy() throws Exception {

          PasswordPolicy model=new PasswordPolicy();
          ObjectMapper mapper = new ObjectMapper();

          model.setHost("laptop1");
          model.setEnforcePasswordHistory(5);
          model.setMaxPasswordAge(90);
          model.setMinPasswordAge(0);
          model.setMustMeetComplexityRequirements(true);
          model.setStorePasswordUsingReversibleEncryption(true);
          //mock service layer behavior
          when(service.queuePolicy(any(PasswordPolicy.class))).thenReturn(model);

          String testData = mapper.writeValueAsString(model);

          mockMvc.perform(post(PolicyConstants.CREATE_POLICY)
                .content(testData)
                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk());
        }
        
        /**
         * Test response header and content
         * @throws Exception
         */
        @Test
        public void policyResponseCheck() throws Exception {
        	PasswordPolicy model=new PasswordPolicy();
            ObjectMapper mapper = new ObjectMapper();

            model.setHost("laptop2");
            model.setEnforcePasswordHistory(5);
            model.setMaxPasswordAge(90);
            model.setMinPasswordAge(0);
            model.setMustMeetComplexityRequirements(true);
            model.setStorePasswordUsingReversibleEncryption(true);

            when(service.queuePolicy(any(PasswordPolicy.class))).thenReturn(model);

            String testData = mapper.writeValueAsString(model);

            mockMvc.perform(post(PolicyConstants.CREATE_POLICY)
                    .content(testData)
                    .contentType(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(jsonPath("$.host", is("laptop2")))
                    .andExpect(jsonPath("$.enforcePasswordHistory", is(5)))
                    .andExpect(jsonPath("$.minPasswordAge", is(0)))
                    .andExpect(jsonPath("$.maxPasswordAge", is(90)))
                    .andExpect(jsonPath("$.mustMeetComplexityRequirements", is(Boolean.TRUE)))
                    .andExpect(jsonPath("$.storePasswordUsingReversibleEncryption", is(Boolean.TRUE)))
                    .andExpect(header().string("Content-Type", "application/json"))
                    .andExpect(status().isOk());
    
        }
        
        /**
         * Test invalid hostname
         */
        @Test
        public void badHostname() throws Exception{
        	PasswordPolicy model=new PasswordPolicy("laptop@#4",5,90,0,8,true,false);
            ObjectMapper mapper = new ObjectMapper();

            when(service.queuePolicy(any(PasswordPolicy.class))).thenThrow(new InvalidHostException());

            String testData = mapper.writeValueAsString(model);
            mockMvc.perform(post(PolicyConstants.CREATE_POLICY)
                    .content(testData)
                    .contentType(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isBadRequest());
        	
        }

}
