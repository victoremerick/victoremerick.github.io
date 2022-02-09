package app7food;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario {

    public static String email;
    public static String senha;
    public static String codigo;
    public static boolean ok;

    public static String encrypt() {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(senha.getBytes(), 0, senha.length());
            return (new BigInteger(1, m.digest()).toString(16));
        } catch (NoSuchAlgorithmException ex) {
            throw new NullPointerException();
        }
    }

}
