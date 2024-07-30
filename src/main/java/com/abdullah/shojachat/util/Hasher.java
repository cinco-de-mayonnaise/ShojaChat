package com.abdullah.shojachat.util;


import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import static com.abdullah.shojachat.util.Identifiers.PASSWORD_HASH_ITERATIONS;

public class Hasher
{
    // HashMaster is responsible for all cryptographic operations required by ShojaChat
    
    private final SecretKeyFactory factory;
    private final String hashType;

    public String getHashType() {
        return hashType;
    }

    public Hasher(String algorithm) throws NoSuchAlgorithmException, IllegalArgumentException
    {
        factory = SecretKeyFactory.getInstance(algorithm);
        hashType = factory.getAlgorithm();

        // check whether this algorithm allows a password to be hashed
        KeySpec tempspec = new PBEKeySpec("hello".toCharArray(), new byte[]{1, 22, 5, 3, 4, 11, 2, 0, 9}, PASSWORD_HASH_ITERATIONS);
        try
        {
            factory.generateSecret(tempspec).getEncoded();
        }
        catch (InvalidKeySpecException e)
        {
            // log: given algorithm cannot generate a hash for password!
            throw new IllegalArgumentException("Given algorithm cannot generate a hash for password!");
        }
    }

    public byte[] hashPassword(String rawPassword, byte[] salt) throws IllegalArgumentException
    {
        KeySpec spec = new PBEKeySpec(rawPassword.toCharArray(), salt, PASSWORD_HASH_ITERATIONS);
        try
        {
            return factory.generateSecret(spec).getEncoded();
        }
        catch (InvalidKeySpecException e)
        {
            throw new IllegalStateException("How can the KeySpec be inappropriate/invalid when it was tested during constructor???");
            // it is not possible for salt to be null, PBEKeySpec ensures this, we need to test it
        }
    }
}
