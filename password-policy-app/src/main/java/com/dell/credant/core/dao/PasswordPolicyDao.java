package com.dell.credant.core.dao;

import com.dell.credant.core.model.entities.PasswordPolicy;

/**
 * Created by Dilip Kancharla on Jun 28, 2015.
 */
public interface PasswordPolicyDao {
    /**
     * Takes passwordPolicy object that needs to be queued
     * into JMS topic and returns the same after queuing.
     * @param data - passwordPolicy Object.
     * @return - passwordPolic object
     */
    PasswordPolicy queuePolicy(PasswordPolicy data);
}
