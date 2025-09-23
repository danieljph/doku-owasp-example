package com.doku.doku_owasp_example.cryptography.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author Daniel Joi Partogi Hutapea
 */
public class AesGcmExample
{
    private static final int GCM_IV_LENGTH = 12; // 96 bits
    private static final int GCM_TAG_LENGTH = 16; // 128 bits

    private static final String staticIvAsBase64 = "ghPl7oIdbFJi1nQv";

    public static SecretKey generateAesKey() throws Exception
    {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // 256-bit AES key
        return keyGen.generateKey();
    }

    public static byte[] generateIv()
    {
        return generateIv(true);
    }

    public static byte[] generateIv(boolean randomIv)
    {
        if(randomIv)
        {
            byte[] iv = new byte[GCM_IV_LENGTH];
            new SecureRandom().nextBytes(iv);
            return iv;
        }
        else
        {
            return Base64.getDecoder().decode(staticIvAsBase64);
        }
    }

    public static String encrypt(String plaintext, SecretKey key, byte[] iv) throws Exception
    {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);

        byte[] cipherText = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        // Combine IV and ciphertext for storage/transmission
        byte[] encryptedData = new byte[iv.length + cipherText.length];
        System.arraycopy(iv, 0, encryptedData, 0, iv.length);
        System.arraycopy(cipherText, 0, encryptedData, iv.length, cipherText.length);

        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedDataString, SecretKey key) throws Exception
    {
        byte[] encryptedData = Base64.getDecoder().decode(encryptedDataString);

        byte[] iv = new byte[GCM_IV_LENGTH];
        System.arraycopy(encryptedData, 0, iv, 0, iv.length);

        byte[] cipherText = new byte[encryptedData.length - iv.length];
        System.arraycopy(encryptedData, iv.length, cipherText, 0, cipherText.length);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);

        byte[] decryptedText = cipher.doFinal(cipherText);
        return new String(decryptedText, StandardCharsets.UTF_8);
    }

    @SuppressWarnings({"ConstantValue", "DuplicatedCode"})
    public static void main(String[] args) throws Exception
    {
        if(false)
        {
            SecretKey aesKey = generateAesKey();
            byte[] iv = generateIv();
            String originalText = "This is a secret message to be encrypted with AES/GCM.";

            String encryptedText = encrypt(originalText, aesKey, iv);
            System.out.println("Original Text          : " + originalText);
            System.out.println("Encrypted Text (Base64): " + encryptedText);

            String decryptedText = decrypt(encryptedText, aesKey);
            System.out.println("Decrypted Text         : " + decryptedText);
        }

        SecretKey aesKey = generateAesKey();
        byte[] iv = generateIv(false); // Remember to explain IV must be unique per encryption.

        for(int i=0; i<5; i++)
        {
            System.out.println("=======================================");

            String originalText = "Hi!";

            String encryptedText = encrypt(originalText, aesKey, iv);
            System.out.println("Original Text          : " + originalText);
            System.out.println("Encrypted Text (Base64): " + encryptedText);

            String decryptedText = decrypt(encryptedText, aesKey);
            System.out.println("Decrypted Text         : " + decryptedText);

            System.out.println("---------------------------------------\n");
        }
    }
}
