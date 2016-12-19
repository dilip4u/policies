/**
 * Created by Dilip Kancharla on Jun 28, 2015
 */
package com.dell.credant.ws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dell.credant.core.model.entities.PasswordPolicy;
import com.dell.credant.core.service.PasswordPolicyService;
import com.dell.credant.core.service.exceptions.InvalidHostException;
import com.dell.credant.ws.controller.exceptions.BadRequestException;
import com.dell.credant.ws.controller.util.PolicyConstants;

/**
 * @author Dilip
 *
 */
@RestController
public class PasswordPolicyController {
    /**
     * Logger object to assist in logging.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(PasswordPolicyController.class);
    /**
     * passwordService object - service layer reference.
     */
    private PasswordPolicyService passwordService;

    /**
     * Rest Controller that handles policy related requests.
     * @param service - service reference.
     */
    @Autowired
    public PasswordPolicyController(final PasswordPolicyService service) {
        passwordService = service;
     }

    /**
     * Create Policy Rest Controller that will consume PasswordPolicy object
     * and produce PasswordPolicy object in JSON format after inserting
     *  into JMS topic.
     * @param policy - Takes PasswordPolicy Object as input
     * @return - returns PasswordPolicy object as response after inserting
     * to Topic.
     */
    @RequestMapping(value = PolicyConstants.CREATE_POLICY,
            method = RequestMethod.POST,
            headers = { "Accept=application/json" },
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public final PasswordPolicy createPasswordPolicy(
            @RequestBody final PasswordPolicy policy) {
        try  {
            LOGGER.debug("Start processing password policy.");
            PasswordPolicy createdPolicy = passwordService.queuePolicy(policy);
            LOGGER.debug("End processing password policy.");
            return createdPolicy;
        } catch (InvalidHostException exception) {
            throw new BadRequestException(exception);
        }
    }
}
