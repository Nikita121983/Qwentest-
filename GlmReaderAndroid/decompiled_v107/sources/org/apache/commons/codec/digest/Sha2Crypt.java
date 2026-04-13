package org.apache.commons.codec.digest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.UByte;

/* loaded from: classes9.dex */
public class Sha2Crypt {
    private static final int ROUNDS_DEFAULT = 5000;
    private static final int ROUNDS_MAX = 999999999;
    private static final int ROUNDS_MIN = 1000;
    private static final String ROUNDS_PREFIX = "rounds=";
    private static final Pattern SALT_PATTERN = Pattern.compile("^\\$([56])\\$(rounds=(\\d+)\\$)?([\\.\\/a-zA-Z0-9]{1,16}).*");
    private static final int SHA256_BLOCKSIZE = 32;
    static final String SHA256_PREFIX = "$5$";
    private static final int SHA512_BLOCKSIZE = 64;
    static final String SHA512_PREFIX = "$6$";

    public static String sha256Crypt(byte[] keyBytes) {
        return sha256Crypt(keyBytes, null);
    }

    public static String sha256Crypt(byte[] keyBytes, String salt) {
        if (salt == null) {
            salt = SHA256_PREFIX + B64.getRandomSalt(8);
        }
        return sha2Crypt(keyBytes, salt, SHA256_PREFIX, 32, MessageDigestAlgorithms.SHA_256);
    }

    public static String sha256Crypt(byte[] keyBytes, String salt, Random random) {
        if (salt == null) {
            salt = SHA256_PREFIX + B64.getRandomSalt(8, random);
        }
        return sha2Crypt(keyBytes, salt, SHA256_PREFIX, 32, MessageDigestAlgorithms.SHA_256);
    }

    private static String sha2Crypt(byte[] bArr, String str, String str2, int i, String str3) {
        int i2;
        byte b;
        int i3;
        int i4;
        int length = bArr.length;
        int i5 = ROUNDS_DEFAULT;
        boolean z = false;
        if (str == null) {
            throw new IllegalArgumentException("Salt must not be null");
        }
        Matcher matcher = SALT_PATTERN.matcher(str);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid salt value: " + str);
        }
        int i6 = 3;
        if (matcher.group(3) != null) {
            i5 = Math.max(1000, Math.min(ROUNDS_MAX, Integer.parseInt(matcher.group(3))));
            z = true;
        }
        String group = matcher.group(4);
        byte[] bytes = group.getBytes(StandardCharsets.UTF_8);
        int length2 = bytes.length;
        MessageDigest digest = DigestUtils.getDigest(str3);
        digest.update(bArr);
        digest.update(bytes);
        MessageDigest digest2 = DigestUtils.getDigest(str3);
        digest2.update(bArr);
        digest2.update(bytes);
        digest2.update(bArr);
        byte[] digest3 = digest2.digest();
        int length3 = bArr.length;
        while (true) {
            i2 = i6;
            if (length3 <= i) {
                break;
            }
            digest.update(digest3, 0, i);
            length3 -= i;
            i6 = i2;
        }
        digest.update(digest3, 0, length3);
        for (int length4 = bArr.length; length4 > 0; length4 >>= 1) {
            if ((length4 & 1) != 0) {
                digest.update(digest3, 0, i);
            } else {
                digest.update(bArr);
            }
        }
        byte[] digest4 = digest.digest();
        MessageDigest digest5 = DigestUtils.getDigest(str3);
        for (int i7 = 1; i7 <= length; i7++) {
            digest5.update(bArr);
        }
        byte[] digest6 = digest5.digest();
        byte[] bArr2 = new byte[length];
        boolean z2 = z;
        int i8 = 0;
        while (true) {
            Matcher matcher2 = matcher;
            if (i8 >= length - i) {
                break;
            }
            System.arraycopy(digest6, 0, bArr2, i8, i);
            i8 += i;
            matcher = matcher2;
        }
        boolean z3 = false;
        MessageDigest messageDigest = digest;
        System.arraycopy(digest6, 0, bArr2, i8, length - i8);
        MessageDigest digest7 = DigestUtils.getDigest(str3);
        int i9 = 1;
        while (i9 <= (digest4[z3 ? 1 : 0] & UByte.MAX_VALUE) + 16) {
            digest7.update(bytes);
            i9++;
            z3 = false;
        }
        byte[] digest8 = digest7.digest();
        byte[] bArr3 = new byte[length2];
        int i10 = 0;
        while (i10 < length2 - i) {
            System.arraycopy(digest8, 0, bArr3, i10, i);
            i10 += i;
        }
        System.arraycopy(digest8, 0, bArr3, i10, length2 - i10);
        int i11 = 0;
        while (i11 <= i5 - 1) {
            MessageDigest digest9 = DigestUtils.getDigest(str3);
            if ((i11 & 1) != 0) {
                i3 = i10;
                i4 = 0;
                digest9.update(bArr2, 0, length);
            } else {
                i3 = i10;
                i4 = 0;
                digest9.update(digest4, 0, i);
            }
            if (i11 % 3 != 0) {
                digest9.update(bArr3, i4, length2);
            }
            if (i11 % 7 != 0) {
                digest9.update(bArr2, i4, length);
            }
            if ((i11 & 1) != 0) {
                digest9.update(digest4, i4, i);
            } else {
                digest9.update(bArr2, i4, length);
            }
            digest4 = digest9.digest();
            i11++;
            messageDigest = digest9;
            i10 = i3;
        }
        StringBuilder sb = new StringBuilder(str2);
        if (z2) {
            sb.append(ROUNDS_PREFIX);
            sb.append(i5);
            sb.append("$");
        }
        sb.append(group);
        sb.append("$");
        if (i == 32) {
            B64.b64from24bit(digest4[0], digest4[10], digest4[20], 4, sb);
            B64.b64from24bit(digest4[21], digest4[1], digest4[11], 4, sb);
            B64.b64from24bit(digest4[12], digest4[22], digest4[2], 4, sb);
            B64.b64from24bit(digest4[i2], digest4[13], digest4[23], 4, sb);
            B64.b64from24bit(digest4[24], digest4[4], digest4[14], 4, sb);
            B64.b64from24bit(digest4[15], digest4[25], digest4[5], 4, sb);
            B64.b64from24bit(digest4[6], digest4[16], digest4[26], 4, sb);
            B64.b64from24bit(digest4[27], digest4[7], digest4[17], 4, sb);
            B64.b64from24bit(digest4[18], digest4[28], digest4[8], 4, sb);
            B64.b64from24bit(digest4[9], digest4[19], digest4[29], 4, sb);
            b = 0;
            B64.b64from24bit((byte) 0, digest4[31], digest4[30], i2, sb);
        } else {
            B64.b64from24bit(digest4[0], digest4[21], digest4[42], 4, sb);
            B64.b64from24bit(digest4[22], digest4[43], digest4[1], 4, sb);
            B64.b64from24bit(digest4[44], digest4[2], digest4[23], 4, sb);
            B64.b64from24bit(digest4[3], digest4[24], digest4[45], 4, sb);
            B64.b64from24bit(digest4[25], digest4[46], digest4[4], 4, sb);
            B64.b64from24bit(digest4[47], digest4[5], digest4[26], 4, sb);
            B64.b64from24bit(digest4[6], digest4[27], digest4[48], 4, sb);
            B64.b64from24bit(digest4[28], digest4[49], digest4[7], 4, sb);
            B64.b64from24bit(digest4[50], digest4[8], digest4[29], 4, sb);
            B64.b64from24bit(digest4[9], digest4[30], digest4[51], 4, sb);
            B64.b64from24bit(digest4[31], digest4[52], digest4[10], 4, sb);
            B64.b64from24bit(digest4[53], digest4[11], digest4[32], 4, sb);
            B64.b64from24bit(digest4[12], digest4[33], digest4[54], 4, sb);
            B64.b64from24bit(digest4[34], digest4[55], digest4[13], 4, sb);
            B64.b64from24bit(digest4[56], digest4[14], digest4[35], 4, sb);
            B64.b64from24bit(digest4[15], digest4[36], digest4[57], 4, sb);
            B64.b64from24bit(digest4[37], digest4[58], digest4[16], 4, sb);
            B64.b64from24bit(digest4[59], digest4[17], digest4[38], 4, sb);
            B64.b64from24bit(digest4[18], digest4[39], digest4[60], 4, sb);
            B64.b64from24bit(digest4[40], digest4[61], digest4[19], 4, sb);
            B64.b64from24bit(digest4[62], digest4[20], digest4[41], 4, sb);
            b = 0;
            B64.b64from24bit((byte) 0, (byte) 0, digest4[63], 2, sb);
        }
        Arrays.fill(digest8, b);
        Arrays.fill(bArr2, b);
        Arrays.fill(bArr3, b);
        messageDigest.reset();
        digest7.reset();
        Arrays.fill(bArr, b);
        Arrays.fill(bytes, b);
        return sb.toString();
    }

    public static String sha512Crypt(byte[] keyBytes) {
        return sha512Crypt(keyBytes, null);
    }

    public static String sha512Crypt(byte[] keyBytes, String salt) {
        if (salt == null) {
            salt = SHA512_PREFIX + B64.getRandomSalt(8);
        }
        return sha2Crypt(keyBytes, salt, SHA512_PREFIX, 64, MessageDigestAlgorithms.SHA_512);
    }

    public static String sha512Crypt(byte[] keyBytes, String salt, Random random) {
        if (salt == null) {
            salt = SHA512_PREFIX + B64.getRandomSalt(8, random);
        }
        return sha2Crypt(keyBytes, salt, SHA512_PREFIX, 64, MessageDigestAlgorithms.SHA_512);
    }

    @Deprecated
    public Sha2Crypt() {
    }
}
