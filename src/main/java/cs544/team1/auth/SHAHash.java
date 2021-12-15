package cs544.team1.auth;


import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAHash {
    public static String getSHA256(String msg) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(msg.getBytes());
            byte[] shaDig = md.digest();
            msg = new String(Hex.encode(shaDig));
            return msg;
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
    public static void main(String[] args) {
        System.out.println("SHA admin=" + getSHA256("admin"));
    }
}
