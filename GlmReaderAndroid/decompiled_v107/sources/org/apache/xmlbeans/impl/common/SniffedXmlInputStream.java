package org.apache.xmlbeans.impl.common;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import kotlin.UByte;
import kotlin.text.Typography;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.ss.formula.ptg.UnionPtg;

/* loaded from: classes11.dex */
public class SniffedXmlInputStream extends BufferedInputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int MAX_SNIFFED_BYTES = 192;
    private String _encoding;
    private static Charset dummy1 = Charset.forName("UTF-8");
    private static Charset dummy2 = Charset.forName("UTF-16");
    private static Charset dummy3 = Charset.forName("UTF-16BE");
    private static Charset dummy4 = Charset.forName("UTF-16LE");
    private static Charset dummy5 = Charset.forName("ISO-8859-1");
    private static Charset dummy6 = Charset.forName("US-ASCII");
    private static Charset dummy7 = Charset.forName("Cp1252");
    private static char[] WHITESPACE = {Chars.SPACE, '\r', '\t', '\n'};
    private static char[] NOTNAME = {Chars.EQ, Chars.SPACE, '\r', '\t', '\n', '?', Typography.greater, Typography.less, Chars.QUOTE, '\"'};

    public SniffedXmlInputStream(InputStream stream) throws IOException {
        super(stream);
        String encoding;
        this._encoding = sniffFourBytes();
        if (this._encoding != null && this._encoding.equals("IBM037") && (encoding = sniffForXmlDecl(this._encoding)) != null) {
            this._encoding = encoding;
        }
        if (this._encoding == null) {
            this._encoding = sniffForXmlDecl("UTF-8");
        }
        if (this._encoding == null) {
            this._encoding = "UTF-8";
        }
    }

    private int readAsMuchAsPossible(byte[] buf, int startAt, int len) throws IOException {
        int total = 0;
        while (total < len) {
            int count = read(buf, startAt + total, len - total);
            if (count < 0) {
                break;
            }
            total += count;
        }
        return total;
    }

    private String sniffFourBytes() throws IOException {
        mark(4);
        try {
            byte[] buf = new byte[4];
            if (readAsMuchAsPossible(buf, 0, 4) < 4) {
                return null;
            }
            long result = ((buf[0] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((buf[1] << UnionPtg.sid) & 16711680) | ((buf[2] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (buf[3] & UByte.MAX_VALUE);
            if (result == 65279) {
                return "UCS-4";
            }
            if (result == -131072) {
                return "UCS-4";
            }
            if (result == 60) {
                return "UCS-4BE";
            }
            if (result == 1006632960) {
                return "UCS-4LE";
            }
            if (result == 3932223) {
                return "UTF-16BE";
            }
            if (result == 1006649088) {
                return "UTF-16LE";
            }
            if (result == 1010792557) {
                return null;
            }
            if (result == 1282385812) {
                return "IBM037";
            }
            if ((result & (-65536)) == -16842752) {
                return "UTF-16";
            }
            if (((-65536) & result) == -131072) {
                return "UTF-16";
            }
            if (((-256) & result) == -272908544) {
                return "UTF-8";
            }
            return null;
        } finally {
            reset();
        }
    }

    private String sniffForXmlDecl(String encoding) throws IOException {
        mark(192);
        try {
            byte[] bytebuf = new byte[192];
            int bytelimit = readAsMuchAsPossible(bytebuf, 0, 192);
            Charset charset = Charset.forName(encoding);
            Reader reader = new InputStreamReader(new ByteArrayInputStream(bytebuf, 0, bytelimit), charset);
            char[] buf = new char[bytelimit];
            int limit = 0;
            while (limit < bytelimit) {
                int count = reader.read(buf, limit, bytelimit - limit);
                if (count < 0) {
                    break;
                }
                limit += count;
            }
            return extractXmlDeclEncoding(buf, 0, limit);
        } finally {
            reset();
        }
    }

    public String getXmlEncoding() {
        return this._encoding;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String extractXmlDeclEncoding(char[] buf, int offset, int size) {
        int limit = offset + size;
        int xmlpi = firstIndexOf("<?xml", buf, offset, limit);
        if (xmlpi >= 0) {
            int i = xmlpi + 5;
            ScannedAttribute attr = new ScannedAttribute();
            while (i < limit) {
                i = scanAttribute(buf, i, limit, attr);
                if (i < 0) {
                    return null;
                }
                if (attr.name.equals("encoding")) {
                    return attr.value;
                }
            }
        }
        return null;
    }

    private static int firstIndexOf(String s, char[] buf, int startAt, int limit) {
        if (s.isEmpty()) {
            throw new AssertionError();
        }
        char[] lookFor = s.toCharArray();
        char firstchar = lookFor[0];
        int limit2 = limit - lookFor.length;
        while (startAt < limit2) {
            if (buf[startAt] == firstchar) {
                for (int i = 1; i < lookFor.length; i++) {
                    if (buf[startAt + i] != lookFor[i]) {
                        break;
                    }
                }
                return startAt;
            }
            startAt++;
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x000e, code lost:
    
        r5 = r5 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int nextNonmatchingByte(char[] r3, char[] r4, int r5, int r6) {
        /*
        L1:
            if (r5 >= r6) goto L15
            char r0 = r4[r5]
            r1 = 0
        L6:
            int r2 = r3.length
            if (r1 >= r2) goto L14
            char r2 = r3[r1]
            if (r0 != r2) goto L11
        Le:
            int r5 = r5 + 1
            goto L1
        L11:
            int r1 = r1 + 1
            goto L6
        L14:
            return r5
        L15:
            r0 = -1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.common.SniffedXmlInputStream.nextNonmatchingByte(char[], char[], int, int):int");
    }

    private static int nextMatchingByte(char[] lookFor, char[] buf, int startAt, int limit) {
        while (startAt < limit) {
            char c = buf[startAt];
            for (char c2 : lookFor) {
                if (c == c2) {
                    return startAt;
                }
            }
            startAt++;
        }
        return -1;
    }

    private static int nextMatchingByte(char lookFor, char[] buf, int startAt, int limit) {
        while (startAt < limit) {
            if (buf[startAt] != lookFor) {
                startAt++;
            } else {
                return startAt;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class ScannedAttribute {
        public String name;
        public String value;

        private ScannedAttribute() {
        }
    }

    private static int scanAttribute(char[] buf, int startAt, int limit, ScannedAttribute attr) {
        int nameEnd;
        int equals;
        int valEndquote;
        int nameStart = nextNonmatchingByte(WHITESPACE, buf, startAt, limit);
        if (nameStart < 0 || (nameEnd = nextMatchingByte(NOTNAME, buf, nameStart, limit)) < 0 || (equals = nextNonmatchingByte(WHITESPACE, buf, nameEnd, limit)) < 0 || buf[equals] != '=') {
            return -1;
        }
        int valQuote = nextNonmatchingByte(WHITESPACE, buf, equals + 1, limit);
        if ((buf[valQuote] != '\'' && buf[valQuote] != '\"') || (valEndquote = nextMatchingByte(buf[valQuote], buf, valQuote + 1, limit)) < 0) {
            return -1;
        }
        attr.name = new String(buf, nameStart, nameEnd - nameStart);
        attr.value = new String(buf, valQuote + 1, (valEndquote - valQuote) - 1);
        return valEndquote + 1;
    }
}
