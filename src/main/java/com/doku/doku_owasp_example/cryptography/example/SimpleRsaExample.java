package com.doku.doku_owasp_example.cryptography.example;

import java.math.BigInteger;
import java.security.SecureRandom;
 
/**
 * @author Daniel Joi Partogi Hutapea
 */
public class SimpleRsaExample
{
    private final BigInteger n;
    private BigInteger d, e;
 
    public SimpleRsaExample(int bitlen)
    {
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bitlen / 2, 100, r);
        BigInteger q = new BigInteger(bitlen / 2, 100, r);
        n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        e = new BigInteger("3");
        while(m.gcd(e).intValue() > 1) e = e.add(new BigInteger("2"));
        d = e.modInverse(m);
    }
 
    public void swapKeyPair()
    {
        BigInteger temp = e;
        e = d;
        d = temp;
    }
 
    public BigInteger encrypt(BigInteger message)
    {
        return message.modPow(e, n);
    }
 
    public BigInteger decrypt(BigInteger message)
    {
        return message.modPow(d, n);
    }
 
    public void testEncryptDecrypt(BigInteger message)
    {
        System.out.println("Public Key (e) : " + e);
        System.out.println("Private Key (d): " + d);
        System.out.println("Modulus (n)    : " + n);
 
        System.out.println("Message        : " + message);
        BigInteger ciphertext = encrypt(message);
        System.out.printf("Ciphertext     : %-6d => (message^e)%%n = (%d^%d)%%%d\n", ciphertext, message, e, n);
        BigInteger plaintext = decrypt(ciphertext);
        System.out.printf("Plaintext      : %-6d => (ciphertext^d)%%n = (%d^%d)%%%d\n", plaintext, ciphertext, d, n);
        System.out.println("Note: If message != plaintext retry again to generate another key-pair.");
    }
 
    public static void main(String[] args)
    {
        SimpleRsaExample rsa = new SimpleRsaExample(10);
 
        System.out.println("===== Public Key Encrypt - Private Key Decrypt =====");
        BigInteger message = BigInteger.valueOf(6);
        rsa.testEncryptDecrypt(message);
        System.out.println("====================================================\n");
 
        rsa.swapKeyPair();
 
        System.out.println("===== Private Key Encrypt - Public Key Decrypt =====");
        message = BigInteger.valueOf(8);
        rsa.testEncryptDecrypt(message);
        System.out.println("====================================================");
    }
}
