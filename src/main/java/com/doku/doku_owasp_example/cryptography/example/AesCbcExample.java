package com.doku.doku_owasp_example.cryptography.example;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author Daniel Joi Partogi Hutapea
 */
public class AesCbcExample
{
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private AesCbcExample()
    {
    }

    /**
     * Send ivParameterSpec = null if the algorithm does not support IV.
     */
    public static String encrypt(String plaintext, String algorithm, SecretKey secretKey, IvParameterSpec ivParameterSpec)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(algorithm);

            if(ivParameterSpec == null)
            {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            }
            else
            {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            }

            byte[] cipherText = cipher.doFinal(plaintext.getBytes());
            return Base64.getEncoder().encodeToString(cipherText);
        }
        catch(GeneralSecurityException ex)
        {
            String errorInfo = String.format("Failed to encrypt using algorithm = '%s'. Cause: %s - %s", secretKey.getAlgorithm(), ex.getClass().getSimpleName(), ex.getMessage());
            throw new RuntimeException(errorInfo, ex);
        }
    }

    /**
     * Send ivParameterSpec = null if the algorithm does not support IV.
     */
    public static String decrypt(String ciphertext, String algorithm, SecretKey secretKey, IvParameterSpec ivParameterSpec)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] plaintext = cipher.doFinal(Base64.getDecoder().decode(ciphertext.trim()));
            return new String(plaintext);
        }
        catch(GeneralSecurityException ex)
        {
            String errorInfo = String.format("Failed to decrypt using algorithm = '%s'. Cause: %s - %s", secretKey.getAlgorithm(), ex.getClass().getSimpleName(), ex.getMessage());
            throw new RuntimeException(errorInfo, ex);
        }
    }

    public static SecretKey generateAesKey() throws Exception
    {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // 256-bit AES key
        return keyGen.generateKey();
    }

    public static byte[] randomBytes(int bytesLength)
    {
        byte[] result = new byte[bytesLength];
        SECURE_RANDOM.nextBytes(result);
        return result;
    }

    @SneakyThrows
    public static void main(String[] args)
    {
        SecretKey aesKey = generateAesKey();
        IvParameterSpec iv = new IvParameterSpec(randomBytes(16)); // Remember to explain IV must be unique per encryption.

        for(int i = 0; i < 5; i++)
        {
            System.out.println("=======================================");

            String originalText = "Hi!";

            String encryptedText = encrypt(originalText, "AES/CBC/PKCS5Padding", aesKey, iv);
            System.out.println("Original Text          : " + originalText);
            System.out.println("Encrypted Text (Base64): " + encryptedText);

            String decryptedText = decrypt(encryptedText, "AES/CBC/PKCS5Padding", aesKey, iv);
            System.out.println("Decrypted Text         : " + decryptedText);

            System.out.println("---------------------------------------\n");
        }
    }
}
