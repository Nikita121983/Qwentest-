package org.apache.commons.compress.archivers.sevenz;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class AES256Options {
    static final String ALGORITHM = "AES";
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    static final String TRANSFORMATION = "AES/CBC/NoPadding";
    private final Cipher cipher;
    private final byte[] iv;
    private final int numCyclesPower;
    private final byte[] salt;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SecretKeySpec newSecretKeySpec(byte[] bytes) {
        return new SecretKeySpec(bytes, ALGORITHM);
    }

    private static byte[] randomBytes(int size) {
        byte[] bytes = new byte[size];
        try {
            SecureRandom.getInstanceStrong().nextBytes(bytes);
            return bytes;
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No strong secure random available to generate strong AES key", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AES256Options(char[] password) {
        this(password, EMPTY_BYTE_ARRAY, randomBytes(16), 19);
    }

    AES256Options(char[] password, byte[] salt, byte[] iv, int numCyclesPower) {
        this.salt = salt;
        this.iv = iv;
        this.numCyclesPower = numCyclesPower;
        byte[] aesKeyBytes = AES256SHA256Decoder.sha256Password(password, numCyclesPower, salt);
        SecretKey aesKey = newSecretKeySpec(aesKeyBytes);
        try {
            this.cipher = Cipher.getInstance(TRANSFORMATION);
            this.cipher.init(1, aesKey, new IvParameterSpec(iv));
        } catch (GeneralSecurityException generalSecurityException) {
            throw new IllegalStateException("Encryption error (do you have the JCE Unlimited Strength Jurisdiction Policy Files installed?)", generalSecurityException);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cipher getCipher() {
        return this.cipher;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getIv() {
        return this.iv;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getNumCyclesPower() {
        return this.numCyclesPower;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getSalt() {
        return this.salt;
    }
}
