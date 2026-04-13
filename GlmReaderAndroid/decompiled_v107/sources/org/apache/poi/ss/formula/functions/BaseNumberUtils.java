package org.apache.poi.ss.formula.functions;

/* loaded from: classes10.dex */
public class BaseNumberUtils {
    public static double convertToDecimal(String value, int base, int maxNumberOfPlaces) throws IllegalArgumentException {
        long digit;
        if (value == null || value.isEmpty()) {
            return 0.0d;
        }
        long stringLength = value.length();
        if (stringLength > maxNumberOfPlaces) {
            throw new IllegalArgumentException();
        }
        long signedDigit = 0;
        boolean hasSignedDigit = true;
        char[] characters = value.toCharArray();
        int length = characters.length;
        boolean isNegative = false;
        double decimalValue = 0.0d;
        int i = 0;
        while (i < length) {
            char character = characters[i];
            if ('0' <= character && character <= '9') {
                digit = character - 48;
            } else if ('A' <= character && character <= 'Z') {
                digit = 10 + (character - 'A');
            } else {
                digit = ('a' > character || character > 'z') ? base : 10 + (character - 'a');
            }
            if (digit >= base) {
                throw new IllegalArgumentException("character not allowed");
            }
            if (hasSignedDigit) {
                hasSignedDigit = false;
                signedDigit = digit;
            }
            decimalValue = (base * decimalValue) + digit;
            i++;
            stringLength = stringLength;
        }
        long stringLength2 = stringLength;
        if (!hasSignedDigit && stringLength2 == maxNumberOfPlaces && signedDigit >= base / 2) {
            isNegative = true;
        }
        if (isNegative) {
            return getTwoComplement(base, maxNumberOfPlaces, decimalValue) * (-1.0d);
        }
        return decimalValue;
    }

    private static double getTwoComplement(double base, double maxNumberOfPlaces, double decimalValue) {
        return Math.pow(base, maxNumberOfPlaces) - decimalValue;
    }
}
