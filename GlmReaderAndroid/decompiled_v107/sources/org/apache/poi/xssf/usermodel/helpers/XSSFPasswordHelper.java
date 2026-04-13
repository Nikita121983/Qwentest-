package org.apache.poi.xssf.usermodel.helpers;

import java.util.Arrays;
import java.util.Base64;
import java.util.Locale;
import javax.xml.namespace.QName;
import org.apache.commons.compress.compressors.bzip2.BZip2Constants;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.util.Internal;
import org.apache.poi.util.RandomSingleton;
import org.apache.poi.util.StringUtil;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;

@Internal(since = "3.15 beta 3")
/* loaded from: classes10.dex */
public final class XSSFPasswordHelper {
    private XSSFPasswordHelper() {
    }

    public static void setPassword(XmlObject xobj, String password, HashAlgorithm hashAlgo, String prefix) {
        XmlCursor cur = xobj.newCursor();
        try {
            if (password == null) {
                cur.removeAttribute(getAttrName(prefix, "password"));
                cur.removeAttribute(getAttrName(prefix, "algorithmName"));
                cur.removeAttribute(getAttrName(prefix, "hashValue"));
                cur.removeAttribute(getAttrName(prefix, "saltValue"));
                cur.removeAttribute(getAttrName(prefix, "spinCount"));
                if (cur != null) {
                    cur.close();
                    return;
                }
                return;
            }
            cur.toFirstContentToken();
            if (hashAlgo == null) {
                int hash = CryptoFunctions.createXorVerifier1(password);
                cur.insertAttributeWithValue(getAttrName(prefix, "password"), String.format(Locale.ROOT, "%04X", Integer.valueOf(hash)).toUpperCase(Locale.ROOT));
            } else {
                byte[] salt = RandomSingleton.getInstance().generateSeed(16);
                byte[] hash2 = CryptoFunctions.hashPassword(password, hashAlgo, salt, BZip2Constants.BASEBLOCKSIZE, false);
                Base64.Encoder enc64 = Base64.getEncoder();
                cur.insertAttributeWithValue(getAttrName(prefix, "algorithmName"), hashAlgo.jceId);
                cur.insertAttributeWithValue(getAttrName(prefix, "hashValue"), enc64.encodeToString(hash2));
                cur.insertAttributeWithValue(getAttrName(prefix, "saltValue"), enc64.encodeToString(salt));
                cur.insertAttributeWithValue(getAttrName(prefix, "spinCount"), "" + BZip2Constants.BASEBLOCKSIZE);
            }
            if (cur != null) {
                cur.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static boolean validatePassword(XmlObject xobj, String password, String prefix) {
        if (password == null) {
            return false;
        }
        XmlCursor cur = xobj.newCursor();
        try {
            String xorHashVal = cur.getAttributeText(getAttrName(prefix, "password"));
            String algoName = cur.getAttributeText(getAttrName(prefix, "algorithmName"));
            String hashVal = cur.getAttributeText(getAttrName(prefix, "hashValue"));
            String saltVal = cur.getAttributeText(getAttrName(prefix, "saltValue"));
            String spinCount = cur.getAttributeText(getAttrName(prefix, "spinCount"));
            if (xorHashVal != null) {
                int hash1 = Integer.parseInt(xorHashVal, 16);
                int hash2 = CryptoFunctions.createXorVerifier1(password);
                boolean z = hash1 == hash2;
                if (cur != null) {
                    cur.close();
                }
                return z;
            }
            if (hashVal == null || algoName == null || saltVal == null || spinCount == null) {
                if (cur != null) {
                    cur.close();
                }
                return false;
            }
            Base64.Decoder dec64 = Base64.getDecoder();
            byte[] hash12 = dec64.decode(hashVal);
            HashAlgorithm hashAlgo = HashAlgorithm.fromString(algoName);
            byte[] salt = dec64.decode(saltVal);
            int spinCnt = Integer.parseInt(spinCount);
            byte[] hash22 = CryptoFunctions.hashPassword(password, hashAlgo, salt, spinCnt, false);
            boolean equals = Arrays.equals(hash12, hash22);
            if (cur != null) {
                cur.close();
            }
            return equals;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private static QName getAttrName(String prefix, String name) {
        if (prefix == null || prefix.isEmpty()) {
            return new QName(name);
        }
        return new QName(prefix + StringUtil.toUpperCase(name.charAt(0)) + name.substring(1));
    }
}
