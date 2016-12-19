/**
 * Created by Dilip Kancharla on Jul 5, 2015
 */
package com.dell.credant.consumer;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.dell.credant.core.model.entities.PasswordPolicy;
import com.dell.credant.jms.consumer.util.ConsumerUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
/**
 * @author Dilip Reddy Kancharla .
 *
 */
public class PolicyConsumerTest {

	private ConsumerUtil consUtil;
	
	@Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
 
	@Before
	public void setup() {
		consUtil = new ConsumerUtil();
	}
	/**
	 * tests audit functionality
	 * @throws Exception
	 */
	@Test
	public void checkAuditFunctionality() throws Exception {
        File actualFile = testFolder.newFile("file.txt");
        consUtil.setAuditFilePath(actualFile.getAbsolutePath());
		PasswordPolicy policy = new PasswordPolicy("laptop##2", 5, 75, 0, 8, true, false);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(policy);
		consUtil.auditMessage(policy);
		assertTrue("Audit failure!",FileUtils.readFileToString(actualFile).contains(json));
	}
    /**
     * tests for incorrect file path	
     * @throws IOException
     */
	@Test(expected=IOException.class)
	public void fileMissing() throws IOException {
        consUtil.setAuditFilePath("/file.txt");
		PasswordPolicy policy = new PasswordPolicy("laptop##2", 5, 75, 0, 8, true, false);
        consUtil.auditMessage(policy);
    }
	
}
