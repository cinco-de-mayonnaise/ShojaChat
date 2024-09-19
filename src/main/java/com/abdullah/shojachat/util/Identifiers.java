package com.abdullah.shojachat.util;

public abstract class Identifiers
{
    /////// VERSION TRACKING
    public static final int VERSION_MAJOR = 0;
    public static final int VERSION_MINOR = 1;

    /////// CIC Identifiers
    // Should conform to the following name: <Typename>_name_CIC
    public static final String CIC_String_CwdPath = "takano-chan";
    public static final String CIC_ServerImpl_CurrentRunningServer = "meow";

    /////// Java Constructor Arguments
    // Fallbacks to the Password Hashing Algorithm (currently unused, PWHasher takes their own hash
    public static final String PASSWORD_HASHING_ALGORITHM = "PBKDF2WithHmacSHA256";
    public static final String PASSWORD_HASHING_ALGORITHM_2 = "PBKDF2WithHmacSHA1";
    public static final String PASSWORD_HASHING_ALGORITHM_fallback = "DESede";

    public static final int PASSWORD_HASH_ITERATIONS = 1048576;

    /////// Scene paths
    public abstract static class Scenes
    {
        public static final String server__loadServer = "Server/server-loadServer.fxml";
        public static final String server__createServer1 = "Server/server-createServer1.fxml";
        public static final String server_MainWindow__main = "Server/MainWindow/main.fxml";

    }
}
