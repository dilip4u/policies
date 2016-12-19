/**
 * Created by Dilip Kancharla on Jun 28, 2015
 */
package com.dell.credant.core.model.entities;

import java.io.Serializable;

/**
 * @author Dilip Reddy K.
 *
 */
public class PasswordPolicy implements Serializable {

    /**
     * Servial Version ID to help in serialization.
     */
    private static final long serialVersionUID = -7788619177798333712L;
    /**
     * host - holds the hostname.
     */
    private String host;
    /**
     * enforcePasswordHistory - Prevents users
     *  from creating a new password that is the same
     *  as their current password or a recently used password.
     */
    private int enforcePasswordHistory;
    /**
     * maxPasswordAge - Sets the maximum number of days
     *  that a password is valid.
     */
    private int maxPasswordAge;
    /**
     * minPasswordAge - Sets the minimum number of days
     *  that must pass before a password can be changed.
     */
    private int minPasswordAge;
    /**
     * minPasswordLength - Specifies the fewest number
     *  of characters a password can have.
     */
    private int minPasswordLength;
    /**
     * mustMeetComplexityRequirements - Specifies if the
     *  password has to be complex or not.
     */

    private boolean mustMeetComplexityRequirements;
    /**
     * storePasswordUsingReversibleEncryption - holds if
     * reversible encryption is true or false.
     */
    private boolean storePasswordUsingReversibleEncryption;

    /**
     * Default Constructor.
     */
    public PasswordPolicy() {
        super();
    }

    /**
     * @param hostname
     *            : Host name or IP address that uniquely identifies the
     *            computer
     * @param enforcePwdHistory
     *            : indicates number of previous passwords to be compated
     *            against
     * @param maxPwdAge
     *            : the maximum number of days that a password is valid
     * @param minPwdAge
     *            : Sets the minimum number of days that must pass before a
     *            password can be changed
     * @param minPwdLength
     *            : minimum length of password
     * @param mustMeetComplexityReqs
     *            : indicates enforcement of complex password requirements
     * @param storePasswordUsingReversibleEnc : indicates if pwd is
     *  reversible encryption enabled.
     */
    public PasswordPolicy(final String hostname, final int enforcePwdHistory,
            final int maxPwdAge, final int minPwdAge, final int minPwdLength,
            final boolean mustMeetComplexityReqs,
            final boolean storePasswordUsingReversibleEnc) {
        super();
        this.host = hostname;
        this.enforcePasswordHistory = enforcePwdHistory;
        this.maxPasswordAge = maxPwdAge;
        this.minPasswordAge = minPwdAge;
        this.minPasswordLength = minPwdLength;
        this.mustMeetComplexityRequirements = mustMeetComplexityReqs;
        this.storePasswordUsingReversibleEncryption
                      = storePasswordUsingReversibleEnc;
    }

    /**
     * @return the host
     */
    public final String getHost() {
        return host;
    }

    /**
     * @param hostname
     *            the host to set
     */
    public final void setHost(final String hostname) {
        this.host = hostname;
    }

    /**
     * @return the enforcePasswordHistory
     */
    public final int getEnforcePasswordHistory() {
        return enforcePasswordHistory;
    }

    /**
     * @param enforcePwdHistory
     *            the enforcePasswordHistory to set
     */
    public final void setEnforcePasswordHistory(final int enforcePwdHistory) {
        this.enforcePasswordHistory = enforcePwdHistory;
    }

    /**
     * @return the maxPasswordAge
     */
    public final int getMaxPasswordAge() {
        return maxPasswordAge;
    }

    /**
     * @param maxPwdAge
     *            the maxPasswordAge to set
     */
    public final void setMaxPasswordAge(final int maxPwdAge) {
        this.maxPasswordAge = maxPwdAge;
    }

    /**
     * @return the minPasswordAge
     */
    public final int getMinPasswordAge() {
        return minPasswordAge;
    }

    /**
     * @param minPwdAge
     *            the minPasswordAge to set
     */
    public final void setMinPasswordAge(final int minPwdAge) {
        this.minPasswordAge = minPwdAge;
    }

    /**
     * @return the minPasswordLength
     */
    public final int getMinPasswordLength() {
        return minPasswordLength;
    }

    /**
     * @param minPwdLength
     *            the minPasswordLength to set
     */
    public final void setMinPasswordLength(final int minPwdLength) {
        this.minPasswordLength = minPwdLength;
    }

    /**
     * @return the mustMeetComplexityRequirements
     */
    public final boolean isMustMeetComplexityRequirements() {
        return mustMeetComplexityRequirements;
    }

    /**
     * @param mustMeetComplexityReqs
     *            the mustMeetComplexityRequirements to set
     */
    public final void setMustMeetComplexityRequirements(
            final boolean mustMeetComplexityReqs) {
        this.mustMeetComplexityRequirements = mustMeetComplexityReqs;
    }

    /**
     * @return the storePasswordUsingReversibleEncryption
     */
    public final boolean isStorePasswordUsingReversibleEncryption() {
        return storePasswordUsingReversibleEncryption;
    }

    /**
     * @param storePasswordUsingReversEnc
     *            the storePasswordUsingReversibleEncryption to set
     */
    public final void setStorePasswordUsingReversibleEncryption(
            final boolean storePasswordUsingReversEnc) {
      this.storePasswordUsingReversibleEncryption = storePasswordUsingReversEnc;
    }

    /*
     * Prints Password Policy 0bject.
     */
    @Override
    public final String toString() {
        return "PasswordPolicy [host=" + host
                + ", enforcePasswordHistory="
                + enforcePasswordHistory + ", maxPasswordAge="
                + maxPasswordAge
                + ", minPasswordAge=" + minPasswordAge
                + ", mustMeetComplexityRequirements="
                + mustMeetComplexityRequirements
                + ", storePasswordUsingReversibleEncryption="
                + storePasswordUsingReversibleEncryption + "]";
    }
}
