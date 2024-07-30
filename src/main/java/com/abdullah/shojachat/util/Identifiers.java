package com.abdullah.shojachat.util;

public abstract class Identifiers
{
    /////// VERSION TRACKING
    public static final int VERSION_MAJOR = 0;
    public static final int VERSION_MINOR = 1;

    /////// CIC Identifiers
    public static final String CIC_CwdPath = "takano-chan";

    /////// Java Constructor Arguments
    // Fallbacks to the Password Hashing Algorithm (currently unused, PWHasher takes their own hash
    public static final String PASSWORD_HASHING_ALGORITHM = "PBKDF2WithHmacSHA256";
    public static final String PASSWORD_HASHING_ALGORITHM_2 = "PBKDF2WithHmacSHA1";
    public static final String PASSWORD_HASHING_ALGORITHM_fallback = "DESede";

    public static final int PASSWORD_HASH_ITERATIONS = 1048576;
}
