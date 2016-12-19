package com.dell.credant.jms.consumer.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Dilip Reddy Kancharla .
 * Utility class to be used by JMS consumer classes.
 */
public class ConsumerUtil {
    /**
     * Logger instance.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ConsumerUtil.class);
    /**
     * Holds the location of audit file.
     */
    private String auditFilePath;

    /**
     * @return the auditFile
     */
    public final String getAuditFilePath() {
        return auditFilePath;
    }

    /**
     * @param auditFile the auditFile to set.
     */
    public final void setAuditFilePath(final String auditFile) {
        this.auditFilePath = auditFile;
    }

    /**
     * appends object passed into method to log file.
     * @param obj -represents object to be logged in json format.
     * @throws IOException - when the audit file path is invalid
     */
    public final void auditMessage(final Object obj) throws IOException {
        File file = null;
        FileOutputStream fileOpStream = null;
        Writer fileWriter = null;
        BufferedWriter bufferWriter = null;
        LOGGER.info("Audit has begun by the consumer!");
        LOGGER.debug("Audit file path: " + auditFilePath);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            String json = mapper.writeValueAsString(obj);
            LOGGER.debug(json);
            file = new File(auditFilePath);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            //append to audit file
            fileOpStream = new FileOutputStream(file, true);
            fileWriter = new OutputStreamWriter(fileOpStream, "UTF-8");
            bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write("Below Policy Change has been"
                                  + " recorded at: " + new Date());
            bufferWriter.write(json);
            bufferWriter.flush();
            LOGGER.info("Completed audit");
        } catch (IOException e) {
                LOGGER.error("Exception occured while auditing"
                        + " json representation: " + e.toString(), e);
                throw e;
          } catch (SecurityException e) {
                LOGGER.error("Security Exception occured while auditing"
                        + " json representation: " + e.toString(), e);
                throw e;
          } finally {
                 try {
                     if (bufferWriter != null) {
                        bufferWriter.close();
                     }
                    } catch (IOException anException) {
                            LOGGER.error("Unable to close resources!"
                                 + anException.toString(), anException);
                            anException.printStackTrace();
                    }
            }
    }
}
