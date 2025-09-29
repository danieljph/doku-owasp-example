package com.doku.doku_owasp_example.cryptography.example;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.crypto.generators.SCrypt;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.security.Security;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Arrays;

/**
 * @author Daniel Joi Partogi Hutapea
 */
public class ScryptHashingExample
{
    public static void main(String[] args)
    {
        // Instantiate the encoder with recommended parameters
        // N = 65536, r = 8, p = 1, keyLength = 32, saltLength = 16
        SCryptPasswordEncoder encoder = new SCryptPasswordEncoder(65536*2, 32, 1, 32, 16);

        String originalPassword = "mySecurePassword123";

        // Encode (hash) the password
        ZonedDateTime t1 = ZonedDateTime.now();
        String hashedPassword = encoder.encode(originalPassword);
        ZonedDateTime t2 = ZonedDateTime.now();
        Duration duration = Duration.between(t1, t2);
        System.out.println("Stored Hash: " + hashedPassword + " (Time taken: " + duration.toMillis() + " ms)");

        // Verify a correct password
        boolean isMatch = encoder.matches(originalPassword, hashedPassword);
        System.out.println("Password match: " + isMatch);

        // Verify an incorrect password
        boolean isIncorrectMatch = encoder.matches("wrongpassword", hashedPassword);
        System.out.println("Incorrect password match: " + isIncorrectMatch);
    }
}
