package com.yaroshevich.app.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PasswordUtil {

    public static String encrypt(String string, byte[] salt) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update(salt);

        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        byte[] newBytes = messageDigest.digest(bytes);
        Arrays.fill(bytes, (byte) Character.MIN_VALUE);

        return toHexString(salt) + ":" + toHexString(newBytes);
    }

    public static String encrypt(String string, String salt) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update(hexStringToByteArray(salt));

        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        byte[] newBytes = messageDigest.digest(bytes);
        Arrays.fill(bytes, (byte) Character.MIN_VALUE);

        return toHexString(hexStringToByteArray(salt)) + ":" + toHexString(newBytes);
    }

    public static byte[] getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[10];

        secureRandom.nextBytes(salt);
        return salt;
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    private static String toHexString(byte[] bytes) {
        return IntStream.range(0, bytes.length)
                .mapToObj(i -> String.format("%02X", bytes[i]))
                .collect(Collectors.joining());
    }

}
