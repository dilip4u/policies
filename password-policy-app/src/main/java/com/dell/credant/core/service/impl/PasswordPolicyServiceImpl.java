/**
 * Created by Dilip Kancharla on Jun 28, 2015
 */
package com.dell.credant.core.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dell.credant.core.dao.PasswordPolicyDao;
import com.dell.credant.core.model.entities.PasswordPolicy;
import com.dell.credant.core.service.PasswordPolicyService;
import com.dell.credant.core.service.exceptions.InvalidHostException;

/**
 * @author Dilip Reddy Kancharla .
 *
 */
@Repository
public class PasswordPolicyServiceImpl implements PasswordPolicyService {
    /**
     * Logger class variable.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(PasswordPolicyServiceImpl.class);
    /**
     * passwordDao object.
     */
    @Autowired
    private PasswordPolicyDao passwordDao;

    /**
     * Implements contract defined in PasswordPolicyService
     * service layer - takes passwordPolicy object that needs to be queued
     * into JMS topic.
     * @param policy - PasswordPolicy Object
     * @return PasswordPolicy object
     */
    @Override
    public final PasswordPolicy queuePolicy(final PasswordPolicy policy) {
        LOGGER.info("hostname: " + policy.getHost());
        if (!isValidHostname(policy.getHost())) {
            throw new InvalidHostException();
        }
        return passwordDao.queuePolicy(policy);
    }

    /**
     *
     * @param host - hostname
     * @return returns if a given hostname is valid or not. We will
     * allow - in hostname as well.
     */
    public final boolean isValidHostname(final String host) {
        String hostPattern = "([a-zA-Z0-9]|"
                + "[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9]\\.)*";
        // note we will allow - in host name
        Pattern p = Pattern.compile(hostPattern);
        Matcher m = p.matcher(host);
        LOGGER.debug("host name validation: " + m.matches());
        return  m.matches();
    }
}
