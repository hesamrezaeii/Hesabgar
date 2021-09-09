package ir.ac.aut.hesabgar.helper;

import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class PasswordDecoderHelper {
    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    public String digest(String password) {
        MessageDigest md;
        byte[] input = password.getBytes(UTF_8);
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(input);
        return bytesToHex(result);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public boolean isEqual(String input, String password) {
        return digest(input).equals(password);
    }

}
