package org.apache.poi.ss.format;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import org.apache.poi.ss.format.CellFormatPart;
import org.apache.poi.ss.format.CellNumberFormatter;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public class CellNumberPartHandler implements CellFormatPart.PartHandler {
    private CellNumberFormatter.Special decimalPoint;
    private CellNumberFormatter.Special exponent;
    private boolean improperFraction;
    private char insertSignForExponent;
    private CellNumberFormatter.Special numerator;
    private CellNumberFormatter.Special slash;
    private double scale = 1.0d;
    private final List<CellNumberFormatter.Special> specials = new LinkedList();

    @Override // org.apache.poi.ss.format.CellFormatPart.PartHandler
    public String handlePart(Matcher m, String part, CellFormatType type, StringBuffer descBuf) {
        int pos = descBuf.length();
        char firstCh = part.charAt(0);
        switch (firstCh) {
            case '#':
            case '0':
            case '?':
                if (this.insertSignForExponent != 0) {
                    this.specials.add(new CellNumberFormatter.Special(this.insertSignForExponent, pos));
                    descBuf.append(this.insertSignForExponent);
                    this.insertSignForExponent = (char) 0;
                    pos++;
                }
                for (int i = 0; i < part.length(); i++) {
                    char ch = part.charAt(i);
                    this.specials.add(new CellNumberFormatter.Special(ch, pos + i));
                }
                return part;
            case '%':
                this.scale *= 100.0d;
                return part;
            case '.':
                if (this.decimalPoint == null && !this.specials.isEmpty()) {
                    this.decimalPoint = new CellNumberFormatter.Special('.', pos);
                    this.specials.add(this.decimalPoint);
                }
                return part;
            case '/':
                if (this.slash == null && !this.specials.isEmpty()) {
                    this.numerator = previousNumber();
                    this.improperFraction = (this.numerator == firstDigit(this.specials)) | this.improperFraction;
                    this.slash = new CellNumberFormatter.Special('.', pos);
                    this.specials.add(this.slash);
                }
                return part;
            case 'E':
            case 'e':
                if (this.exponent == null && !this.specials.isEmpty()) {
                    this.exponent = new CellNumberFormatter.Special('.', pos);
                    this.specials.add(this.exponent);
                    this.insertSignForExponent = part.charAt(1);
                    return part.substring(0, 1);
                }
                return part;
            default:
                return null;
        }
    }

    public double getScale() {
        return this.scale;
    }

    public CellNumberFormatter.Special getDecimalPoint() {
        return this.decimalPoint;
    }

    public CellNumberFormatter.Special getSlash() {
        return this.slash;
    }

    public CellNumberFormatter.Special getExponent() {
        return this.exponent;
    }

    public CellNumberFormatter.Special getNumerator() {
        return this.numerator;
    }

    public List<CellNumberFormatter.Special> getSpecials() {
        return this.specials;
    }

    public boolean isImproperFraction() {
        return this.improperFraction;
    }

    private CellNumberFormatter.Special previousNumber() {
        CellNumberFormatter.Special last;
        ListIterator<CellNumberFormatter.Special> it = this.specials.listIterator(this.specials.size());
        while (it.hasPrevious()) {
            CellNumberFormatter.Special s = it.previous();
            if (isDigitFmt(s)) {
                do {
                    last = s;
                    if (!it.hasPrevious()) {
                        break;
                    }
                    s = it.previous();
                    if (last.pos - s.pos > 1) {
                        break;
                    }
                } while (isDigitFmt(s));
                return last;
            }
        }
        return null;
    }

    private static boolean isDigitFmt(CellNumberFormatter.Special s) {
        return s.ch == '0' || s.ch == '?' || s.ch == '#';
    }

    private static CellNumberFormatter.Special firstDigit(List<CellNumberFormatter.Special> specials) {
        for (CellNumberFormatter.Special s : specials) {
            if (isDigitFmt(s)) {
                return s;
            }
        }
        return null;
    }
}
