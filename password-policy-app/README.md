Project name: password-policy-app
Project description : Built to host enterprise host management policy services like password policy administration, account policy administration, etc., currently has only password policy administration services.
Developer : Dilip Kancharla
Compilation Date: 07/06/2015
---------------------------------------------------------------
1. Install instructions:

  1.1 Configuration Checks:

     - Make sure /password-policy-app/src/main/webapp/META-INF/context.xml is up to date with ActiveMQ instance configuration details such as brokerURL.
     
     - /password-policy-app/src/main/resources/app.properties  hosts application properties. Please check to make sure you specify correct value for app.auditFilePath
     
     - /password-policy-app/src/main/resources/log4j.xml hosts logging configuration. Please make sure error-file and trace-file point to desired log path. ( This could be done using JVM args in the future or through env.properties file)
     
     - Make sure ActiveMQ instance is running!

  1.2 Build instructions:

     - After completing step 1.1 build process can be started
     
     - Project will be built using Maven 
     
     - mvn clean install --> will create the war file in target folder as well as perform PMD checks and generates jacoco Code Coverage reports under /target/site/jacoco
     
     - mvn clean install site  --> will create the war file in target folder as well perform FindBugs checks and generates the following reports:
          - Project Report (/password-polcy-app/target/site/index.html)
          - CheckStyle report
          - FindBugs Report
          - JavaDocs of the project
          - Dependency information
          - Plugins report
     
     - project has also been configured with tomcat plugin and can be used to do local testing by running the maven goal mvn install tomcat:run . 
          
     - Project reports and Javadoc generated in previous steps can be used to get an idea of the project structure.     
         
2. User Guide:
  
     - Once the war has been deployed into tomcat container, application can be accessed at http://localhost/password-policy-app/ or http://localhost/password-policy-app/index.html
     
     - index.html will open the AngularJS Single Page Application that will allow the Policy administrators to queue password policies to active mq topic "PasswordPolicy"

     - Alternatively, administrators can use any REST clients like Postman or REST Client chrome extension to submit the POST request with content-type and accept headers set to JSON and send the request JSON in body.
     
     - REST URL can be found at http://localhost/password-policy-app/v1/policy/password
  
     - Sample REST request :

POST /password-policy-app/v1/policy/password HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache

{ "host": "laptop90", "enforcePasswordHistory": 5, "maxPasswordAge": 90, "minPasswordAge": 0, "minPasswordLength": 6, "mustMeetComplexityRequirements": true, "storePasswordUsingReversibleEncryption": false } 

     - JSON audit by the consumer can be verified at audit locaiton specified in step 1.1
     
3. Assumptions and Miscellaneous:

     - Hostname validation has been incorporated into system to not include any special characters except - and . (refer: https://en.wikipedia.org/wiki/Hostname)
     eg: laptop1.dell.com, laptop2,laptop3, 4dell, 5dell-lan2.dell.com ---> are good
     eg: laptop@3.dell.com, latop@$4, -latpop.com, -laptop$1 ---> are not allowed 
            
  
 4. Future Enhancements:
 
     - add Spring authentication and authorization.
     - TBD.
     
  5. Questions:
     
      - Please make sure you check logs {based of logging location specified in log4j.xml}
     - Email 
