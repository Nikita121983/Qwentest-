package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.logging.log4j.util.Chars;

/* loaded from: classes9.dex */
public class MatchRatingApproachEncoder implements StringEncoder {
    private static final String[] DOUBLE_CONSONANT = {"BB", "CC", "DD", "FF", "GG", "HH", "JJ", "KK", "LL", "MM", "NN", "PP", "QQ", "RR", "SS", "TT", "VV", "WW", "XX", "YY", "ZZ"};
    private static final String EMPTY = "";
    private static final String PLAIN_ASCII = "AaEeIiOoUuAaEeIiOoUuYyAaEeIiOoUuYyAaOoNnAaEeIiOoUuYyAaCcOoUu";
    private static final String SPACE = " ";
    private static final String UNICODE = "ÀàÈèÌìÒòÙùÁáÉéÍíÓóÚúÝýÂâÊêÎîÔôÛûŶŷÃãÕõÑñÄäËëÏïÖöÜüŸÿÅåÇçŐőŰű";

    String cleanName(String name) {
        String upperName = name.toUpperCase(Locale.ENGLISH);
        String[] charsToTrim = {"\\-", "[&]", "\\'", "\\.", "[\\,]"};
        for (String str : charsToTrim) {
            upperName = upperName.replaceAll(str, "");
        }
        return removeAccents(upperName).replaceAll("\\s+", "");
    }

    @Override // org.apache.commons.codec.Encoder
    public final Object encode(Object object) throws EncoderException {
        if (!(object instanceof String)) {
            throw new EncoderException("Parameter supplied to Match Rating Approach encoder is not of type java.lang.String");
        }
        return encode((String) object);
    }

    @Override // org.apache.commons.codec.StringEncoder
    public final String encode(String name) {
        if (name == null || "".equalsIgnoreCase(name) || " ".equalsIgnoreCase(name) || name.length() == 1) {
            return "";
        }
        String name2 = cleanName(name);
        if (" ".equals(name2) || name2.isEmpty()) {
            return "";
        }
        String name3 = removeVowels(name2);
        if (" ".equals(name3) || name3.isEmpty()) {
            return "";
        }
        return getFirst3Last3(removeDoubleConsonants(name3));
    }

    String getFirst3Last3(String name) {
        int nameLength = name.length();
        if (nameLength > 6) {
            String firstThree = name.substring(0, 3);
            String lastThree = name.substring(nameLength - 3, nameLength);
            return firstThree + lastThree;
        }
        return name;
    }

    int getMinRating(int sumLength) {
        if (sumLength <= 4) {
            return 5;
        }
        if (sumLength <= 7) {
            return 4;
        }
        if (sumLength <= 11) {
            return 3;
        }
        if (sumLength == 12) {
            return 2;
        }
        return 1;
    }

    public boolean isEncodeEquals(String name1, String name2) {
        if (name1 == null || "".equalsIgnoreCase(name1) || " ".equalsIgnoreCase(name1) || name2 == null || "".equalsIgnoreCase(name2) || " ".equalsIgnoreCase(name2) || name1.length() == 1 || name2.length() == 1) {
            return false;
        }
        if (name1.equalsIgnoreCase(name2)) {
            return true;
        }
        String name12 = cleanName(name1);
        String name22 = cleanName(name2);
        String name13 = removeVowels(name12);
        String name23 = removeVowels(name22);
        String name14 = removeDoubleConsonants(name13);
        String name24 = removeDoubleConsonants(name23);
        String name15 = getFirst3Last3(name14);
        String name25 = getFirst3Last3(name24);
        if (Math.abs(name15.length() - name25.length()) >= 3) {
            return false;
        }
        int sumLength = Math.abs(name15.length() + name25.length());
        int minRating = getMinRating(sumLength);
        int count = leftToRightThenRightToLeftProcessing(name15, name25);
        return count >= minRating;
    }

    boolean isVowel(String letter) {
        return letter.equalsIgnoreCase("E") || letter.equalsIgnoreCase("A") || letter.equalsIgnoreCase("O") || letter.equalsIgnoreCase("I") || letter.equalsIgnoreCase("U");
    }

    int leftToRightThenRightToLeftProcessing(String name1, String name2) {
        char[] name1Char = name1.toCharArray();
        char[] name2Char = name2.toCharArray();
        int name1Size = name1.length() - 1;
        int name2Size = name2.length() - 1;
        for (int i = 0; i < name1Char.length && i <= name2Size; i++) {
            String name1LtRStart = name1.substring(i, i + 1);
            String name1LtREnd = name1.substring(name1Size - i, (name1Size - i) + 1);
            String name2RtLStart = name2.substring(i, i + 1);
            String name2RtLEnd = name2.substring(name2Size - i, (name2Size - i) + 1);
            if (name1LtRStart.equals(name2RtLStart)) {
                name1Char[i] = Chars.SPACE;
                name2Char[i] = Chars.SPACE;
            }
            if (name1LtREnd.equals(name2RtLEnd)) {
                name1Char[name1Size - i] = Chars.SPACE;
                name2Char[name2Size - i] = Chars.SPACE;
            }
        }
        String strA = new String(name1Char).replaceAll("\\s+", "");
        String strB = new String(name2Char).replaceAll("\\s+", "");
        if (strA.length() > strB.length()) {
            return Math.abs(6 - strA.length());
        }
        return Math.abs(6 - strB.length());
    }

    String removeAccents(String accentedWord) {
        if (accentedWord == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int n = accentedWord.length();
        for (int i = 0; i < n; i++) {
            char c = accentedWord.charAt(i);
            int pos = UNICODE.indexOf(c);
            if (pos > -1) {
                sb.append(PLAIN_ASCII.charAt(pos));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    String removeDoubleConsonants(String name) {
        String replacedName = name.toUpperCase(Locale.ENGLISH);
        for (String dc : DOUBLE_CONSONANT) {
            if (replacedName.contains(dc)) {
                String singleLetter = dc.substring(0, 1);
                replacedName = replacedName.replace(dc, singleLetter);
            }
        }
        return replacedName;
    }

    String removeVowels(String name) {
        String firstLetter = name.substring(0, 1);
        String name2 = name.replace("A", "").replace("E", "").replace("I", "").replace("O", "").replace("U", "").replaceAll("\\s{2,}\\b", " ");
        if (isVowel(firstLetter)) {
            return firstLetter + name2;
        }
        return name2;
    }
}
