package com.doku.doku_owasp_example.cryptography.example;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Duration;
import java.time.ZonedDateTime;

/**
 * Advantages:
 * - BuiltIn salt generation and storage within the hash.
 * - The Slow factor can be configured.
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class BCryptExample
{
    public static void main(String[] args)
    {
        /*
         * BCrypt Output Explanation: $2a$12$YFVJLaQwWOLSLHteNkOGTuxYK4X/W7S1YxWDrojYxOjZM4RHQCIbC
         * $2a : Identifier for BCrypt algorithm
         * $12 : Parameter for number of rounds, here 2^12 rounds
         * YFVJLaQwWOLSLHteNkOGTu : The first 22 characters are Salt (128 bits).
         * xYK4X/W7S1YxWDrojYxOjZM4RHQCIbC : Actual hash value (184 bits)
         */

        int strengthAsSlowFactor = 12; // The valid range is 4 to 31. The work factor increases as 2^strength. This strength value is only used by the method "encode". On the method "matches", it will extract the strength from the encoded value.
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strengthAsSlowFactor);

        for(int i = 0; i < 5; i++)
        {
            ZonedDateTime t1 = ZonedDateTime.now();

            String password = "daniel";
            String hashedPassword = bCryptPasswordEncoder.encode(password);

            ZonedDateTime t2 = ZonedDateTime.now();

            boolean isMatched = bCryptPasswordEncoder.matches(password, hashedPassword);
            ZonedDateTime t3 = ZonedDateTime.now();

            Duration hashingOpsDuration = Duration.between(t1, t2);
            Duration matchingOpsDuration = Duration.between(t2, t3);

            System.out.println("=======================================");
            System.out.println("Password                      : " + password);
            System.out.println("Hashed Password               : " + hashedPassword);
            System.out.println("Is Password Matched           : " + isMatched);
            System.out.println("Duration - Hashing Operation  : " + hashingOpsDuration);
            System.out.println("Duration - Matching Operation : " + matchingOpsDuration);
            System.out.println("---------------------------------------\n");
        }
    }
}
