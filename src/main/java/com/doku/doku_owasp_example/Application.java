package com.doku.doku_owasp_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author Daniel Joi Partogi Hutapea
 */
@SpringBootApplication
(
    exclude =
    {
        SecurityAutoConfiguration.class
    }
)
public class Application
{
    public static void main(String[] args)
    {
        // These 2 lines below are needed to simulate the "Billion Laugh" attack.
        System.setProperty("jdk.xml.entityExpansionLimit", "0"); // 0 disables the limit. This line is needed to bypass this limit: JAXP00010001: The parser has encountered more than "64000" entity expansions in this document; this is the limit imposed by the JDK.
        System.setProperty("jdk.xml.totalEntitySizeLimit", "0"); // 0 disables the limit. This line is needed to bypass this limit: JAXP00010004: The accumulated size of entities is "50,000,001" that exceeded the "50,000,000" limit set by "FEATURE_SECURE_PROCESSING".

        SpringApplication.run(Application.class, args);
    }
}
