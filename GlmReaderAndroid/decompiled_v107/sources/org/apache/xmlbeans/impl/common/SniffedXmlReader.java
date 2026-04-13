package org.apache.xmlbeans.impl.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

/* loaded from: classes11.dex */
public class SniffedXmlReader extends BufferedReader {
    public static final int MAX_SNIFFED_CHARS = 192;
    private static Charset dummy1 = Charset.forName("UTF-8");
    private static Charset dummy2 = Charset.forName("UTF-16");
    private static Charset dummy3 = Charset.forName("UTF-16BE");
    private static Charset dummy4 = Charset.forName("UTF-16LE");
    private static Charset dummy5 = Charset.forName("ISO-8859-1");
    private static Charset dummy6 = Charset.forName("US-ASCII");
    private static Charset dummy7 = Charset.forName("Cp1252");
    private String _encoding;

    public SniffedXmlReader(Reader reader) throws IOException {
        super(reader);
        this._encoding = sniffForXmlDecl();
    }

    private int readAsMuchAsPossible(char[] buf, int startAt, int len) throws IOException {
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

    private String sniffForXmlDecl() throws IOException {
        mark(192);
        try {
            char[] buf = new char[192];
            int limit = readAsMuchAsPossible(buf, 0, 192);
            return SniffedXmlInputStream.extractXmlDeclEncoding(buf, 0, limit);
        } finally {
            reset();
        }
    }

    public String getXmlEncoding() {
        return this._encoding;
    }
}
