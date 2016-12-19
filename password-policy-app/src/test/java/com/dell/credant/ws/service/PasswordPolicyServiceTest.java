/**
 * Created by Dilip Kancharla on Jul 5, 2015
 */
package com.dell.credant.ws.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dell.credant.core.dao.PasswordPolicyDao;
import com.dell.credant.core.model.entities.PasswordPolicy;
import com.dell.credant.core.service.exceptions.InvalidHostException;
import com.dell.credant.core.service.impl.PasswordPolicyServiceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * @author Dilip Reddy Kancharla .
 *
 */
public class PasswordPolicyServiceTest {
	@InjectMocks
	private PasswordPolicyServiceImpl passwordService;
	
	@Mock
	private PasswordPolicyDao passwordPolicyDao;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	/**
	 * Test for invalid hostname.
	 */
	@Test(expected=InvalidHostException.class)
	public void hostValidationNegativeTest() {
		PasswordPolicy policy = new PasswordPolicy("laptop#$2", 5, 75, 0, 8, true, false);
		when(passwordPolicyDao.queuePolicy(policy)).thenReturn(policy);
			passwordService.queuePolicy(policy);
	}
	/**
	 * Positive test with correct policy information.
	 */
	@Test
	public void hostValidationPositiveTest() {
		PasswordPolicy policy = new PasswordPolicy("laptop2.com", 5, 75, 0, 8, true, false);
		when(passwordPolicyDao.queuePolicy(policy)).thenReturn(policy);
		assertEquals("Hostname is supposed to be valid!",policy,passwordService.queuePolicy(policy));
	}

}
