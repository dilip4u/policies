/**
 * Created by Dilip Kancharla on Jun 28, 2015
 */
package com.dell.credant.core.service;

import com.dell.credant.core.model.entities.PasswordPolicy;

/**
 * @author Dilip Reddy Kancharla .
 *
 */
public interface PasswordPolicyService {
     /**
      * service layer - takes passwordPolicy object that needs to be queued
      * into JMS topic.
      * @param data - PasswordPolicy Object
      * @return PasswordPolicy object
      */
     PasswordPolicy queuePolicy(PasswordPolicy data);
}
