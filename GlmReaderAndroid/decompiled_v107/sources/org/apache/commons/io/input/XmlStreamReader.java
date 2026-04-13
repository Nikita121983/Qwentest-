package org.apache.commons.io.input;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.poi.openxml4j.opc.ContentTypes;

/* loaded from: classes9.dex */
public class XmlStreamReader extends Reader {
    private static final String HTTP_EX_1 = "Illegal encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], BOM must be null";
    private static final String HTTP_EX_2 = "Illegal encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], encoding mismatch";
    private static final String HTTP_EX_3 = "Illegal encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], Illegal MIME";
    private static final String RAW_EX_1 = "Illegal encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] encoding mismatch";
    private static final String RAW_EX_2 = "Illegal encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] unknown BOM";
    private static final String UTF_32 = "UTF-32";
    private final String defaultEncoding;
    private final String encoding;
    private final Reader reader;
    private static final String UTF_8 = StandardCharsets.UTF_8.name();
    private static final String US_ASCII = StandardCharsets.US_ASCII.name();
    private static final String UTF_16BE = StandardCharsets.UTF_16BE.name();
    private static final String UTF_16LE = StandardCharsets.UTF_16LE.name();
    private static final String UTF_16 = StandardCharsets.UTF_16.name();
    private static final ByteOrderMark[] BOMS = {ByteOrderMark.UTF_8, ByteOrderMark.UTF_16BE, ByteOrderMark.UTF_16LE, ByteOrderMark.UTF_32BE, ByteOrderMark.UTF_32LE};
    private static final String UTF_32BE = "UTF-32BE";
    private static final String UTF_32LE = "UTF-32LE";
    private static final String EBCDIC = "CP1047";
    private static final ByteOrderMark[] XML_GUESS_BYTES = {new ByteOrderMark(UTF_8, 60, 63, 120, 109), new ByteOrderMark(UTF_16BE, 0, 60, 0, 63), new ByteOrderMark(UTF_16LE, 60, 0, 63, 0), new ByteOrderMark(UTF_32BE, 0, 0, 0, 60, 0, 0, 0, 63, 0, 0, 0, 120, 0, 0, 0, 109), new ByteOrderMark(UTF_32LE, 60, 0, 0, 0, 63, 0, 0, 0, 120, 0, 0, 0, 109, 0, 0, 0), new ByteOrderMark(EBCDIC, 76, 111, 167, 148)};
    private static final Pattern CHARSET_PATTERN = Pattern.compile("charset=[\"']?([.[^; \"']]*)[\"']?");
    public static final Pattern ENCODING_PATTERN = Pattern.compile("^<\\?xml\\s+(?:version\\s*=\\s*(?:(?:\"1\\.[0-9]+\")|(?:'1.[0-9]+'))\\s+)??encoding\\s*=\\s*((?:\"[A-Za-z0-9][A-Za-z0-9._+:-]*\")|(?:'[A-Za-z0-9][A-Za-z0-9._+:-]*'))", 8);

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<XmlStreamReader, Builder> {
        private String httpContentType;
        private boolean nullCharset = true;
        private boolean lenient = true;

        @Override // org.apache.commons.io.function.IOSupplier
        public XmlStreamReader get() throws IOException {
            String defaultEncoding = this.nullCharset ? null : getCharset().name();
            if (this.httpContentType == null) {
                return new XmlStreamReader(getInputStream(), this.lenient, defaultEncoding);
            }
            return new XmlStreamReader(getInputStream(), this.httpContentType, this.lenient, defaultEncoding);
        }

        @Override // org.apache.commons.io.build.AbstractStreamBuilder
        public Builder setCharset(Charset charset) {
            this.nullCharset = charset == null;
            return (Builder) super.setCharset(charset);
        }

        @Override // org.apache.commons.io.build.AbstractStreamBuilder
        public Builder setCharset(String charset) {
            this.nullCharset = charset == null;
            return (Builder) super.setCharset(Charsets.toCharset(charset, getCharsetDefault()));
        }

        public Builder setHttpContentType(String httpContentType) {
            this.httpContentType = httpContentType;
            return this;
        }

        public Builder setLenient(boolean lenient) {
            this.lenient = lenient;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    static String getContentTypeEncoding(String httpContentType) {
        int i;
        if (httpContentType == null || (i = httpContentType.indexOf(";")) <= -1) {
            return null;
        }
        String postMime = httpContentType.substring(i + 1);
        Matcher m = CHARSET_PATTERN.matcher(postMime);
        String encoding = m.find() ? m.group(1) : null;
        return encoding != null ? encoding.toUpperCase(Locale.ROOT) : null;
    }

    static String getContentTypeMime(String httpContentType) {
        if (httpContentType == null) {
            return null;
        }
        int i = httpContentType.indexOf(";");
        String mime = i >= 0 ? httpContentType.substring(0, i) : httpContentType;
        return mime.trim();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0033, code lost:
    
        if (r5 != (-1)) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x003c, code lost:
    
        throw new java.io.IOException("Unexpected end of XML stream");
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x005b, code lost:
    
        throw new java.io.IOException("XML prolog or ROOT element not found on first " + r3 + " bytes");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String getXmlProlog(java.io.InputStream r14, java.lang.String r15) throws java.io.IOException {
        /*
            r0 = 0
            if (r15 == 0) goto La3
            byte[] r1 = org.apache.commons.io.IOUtils.byteArray()
            r2 = 8192(0x2000, float:1.14794E-41)
            r14.mark(r2)
            r3 = 0
            r4 = 8192(0x2000, float:1.14794E-41)
            int r5 = r14.read(r1, r3, r4)
            r6 = -1
            java.lang.String r7 = ""
        L16:
            r8 = 0
            r9 = -1
            if (r5 == r9) goto L31
            if (r6 != r9) goto L31
            if (r3 >= r2) goto L31
            int r3 = r3 + r5
            int r4 = r4 - r5
            int r5 = r14.read(r1, r3, r4)
            java.lang.String r9 = new java.lang.String
            r9.<init>(r1, r8, r3, r15)
            r7 = r9
            r8 = 62
            int r6 = r7.indexOf(r8)
            goto L16
        L31:
            if (r6 != r9) goto L5c
            if (r5 != r9) goto L3d
            java.io.IOException r2 = new java.io.IOException
            java.lang.String r8 = "Unexpected end of XML stream"
            r2.<init>(r8)
            throw r2
        L3d:
            java.io.IOException r2 = new java.io.IOException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "XML prolog or ROOT element not found on first "
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r8 = r8.append(r3)
            java.lang.String r9 = " bytes"
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r2.<init>(r8)
            throw r2
        L5c:
            r2 = r3
            if (r2 <= 0) goto La3
            r14.reset()
            java.io.BufferedReader r9 = new java.io.BufferedReader
            java.io.StringReader r10 = new java.io.StringReader
            int r11 = r6 + 1
            java.lang.String r8 = r7.substring(r8, r11)
            r10.<init>(r8)
            r9.<init>(r10)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.util.stream.Stream r10 = r9.lines()
            org.apache.commons.io.input.XmlStreamReader$$ExternalSyntheticLambda0 r11 = new org.apache.commons.io.input.XmlStreamReader$$ExternalSyntheticLambda0
            r11.<init>()
            org.apache.commons.io.function.IOConsumer.forEach(r10, r11)
            java.util.regex.Pattern r10 = org.apache.commons.io.input.XmlStreamReader.ENCODING_PATTERN
            java.util.regex.Matcher r10 = r10.matcher(r8)
            boolean r11 = r10.find()
            if (r11 == 0) goto La3
            r11 = 1
            java.lang.String r12 = r10.group(r11)
            java.util.Locale r13 = java.util.Locale.ROOT
            java.lang.String r0 = r12.toUpperCase(r13)
            int r12 = r0.length()
            int r12 = r12 - r11
            java.lang.String r0 = r0.substring(r11, r12)
        La3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.input.XmlStreamReader.getXmlProlog(java.io.InputStream, java.lang.String):java.lang.String");
    }

    static boolean isAppXml(String mime) {
        return mime != null && (mime.equals(ContentTypes.PLAIN_OLD_XML) || mime.equals("application/xml-dtd") || mime.equals("application/xml-external-parsed-entity") || (mime.startsWith("application/") && mime.endsWith("+xml")));
    }

    static boolean isTextXml(String mime) {
        return mime != null && (mime.equals(ContentTypes.XML) || mime.equals("text/xml-external-parsed-entity") || (mime.startsWith("text/") && mime.endsWith("+xml")));
    }

    @Deprecated
    public XmlStreamReader(File file) throws IOException {
        this(((File) Objects.requireNonNull(file, "file")).toPath());
    }

    @Deprecated
    public XmlStreamReader(InputStream inputStream) throws IOException {
        this(inputStream, true);
    }

    @Deprecated
    public XmlStreamReader(InputStream inputStream, boolean lenient) throws IOException {
        this(inputStream, lenient, (String) null);
    }

    @Deprecated
    public XmlStreamReader(InputStream inputStream, boolean lenient, String defaultEncoding) throws IOException {
        this.defaultEncoding = defaultEncoding;
        BOMInputStream bom = new BOMInputStream(new BufferedInputStream((InputStream) Objects.requireNonNull(inputStream, "inputStream"), 8192), false, BOMS);
        BOMInputStream pis = new BOMInputStream(bom, true, XML_GUESS_BYTES);
        this.encoding = processHttpStream(bom, pis, lenient);
        this.reader = new InputStreamReader(pis, this.encoding);
    }

    @Deprecated
    public XmlStreamReader(InputStream inputStream, String httpContentType) throws IOException {
        this(inputStream, httpContentType, true);
    }

    @Deprecated
    public XmlStreamReader(InputStream inputStream, String httpContentType, boolean lenient) throws IOException {
        this(inputStream, httpContentType, lenient, null);
    }

    @Deprecated
    public XmlStreamReader(InputStream inputStream, String httpContentType, boolean lenient, String defaultEncoding) throws IOException {
        this.defaultEncoding = defaultEncoding;
        BOMInputStream bom = new BOMInputStream(new BufferedInputStream((InputStream) Objects.requireNonNull(inputStream, "inputStream"), 8192), false, BOMS);
        BOMInputStream pis = new BOMInputStream(bom, true, XML_GUESS_BYTES);
        this.encoding = processHttpStream(bom, pis, lenient, httpContentType);
        this.reader = new InputStreamReader(pis, this.encoding);
    }

    @Deprecated
    public XmlStreamReader(Path file) throws IOException {
        this(Files.newInputStream((Path) Objects.requireNonNull(file, "file"), new OpenOption[0]));
    }

    public XmlStreamReader(URL url) throws IOException {
        this(((URL) Objects.requireNonNull(url, "url")).openConnection(), (String) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public XmlStreamReader(URLConnection urlConnection, String defaultEncoding) throws IOException {
        Objects.requireNonNull(urlConnection, "urlConnection");
        this.defaultEncoding = defaultEncoding;
        String contentType = urlConnection.getContentType();
        InputStream inputStream = urlConnection.getInputStream();
        BOMInputStream bomInput = ((BOMInputStream.Builder) BOMInputStream.builder().setInputStream(new BufferedInputStream(inputStream, 8192))).setInclude(false).setByteOrderMarks(BOMS).get();
        BOMInputStream piInput = ((BOMInputStream.Builder) BOMInputStream.builder().setInputStream(new BufferedInputStream(bomInput, 8192))).setInclude(true).setByteOrderMarks(XML_GUESS_BYTES).get();
        if ((urlConnection instanceof HttpURLConnection) || contentType != null) {
            this.encoding = processHttpStream(bomInput, piInput, true, contentType);
        } else {
            this.encoding = processHttpStream(bomInput, piInput, true);
        }
        this.reader = new InputStreamReader(piInput, this.encoding);
    }

    String calculateHttpEncoding(String bomEnc, String xmlGuessEnc, String xmlEnc, boolean lenient, String httpContentType) throws IOException {
        String bomEnc2;
        String xmlGuessEnc2;
        String xmlEnc2;
        if (lenient && xmlEnc != null) {
            return xmlEnc;
        }
        String cTMime = getContentTypeMime(httpContentType);
        String cTEnc = getContentTypeEncoding(httpContentType);
        boolean appXml = isAppXml(cTMime);
        boolean textXml = isTextXml(cTMime);
        if (appXml) {
            bomEnc2 = bomEnc;
            xmlGuessEnc2 = xmlGuessEnc;
            xmlEnc2 = xmlEnc;
        } else {
            if (!textXml) {
                String msg = MessageFormat.format(HTTP_EX_3, cTMime, cTEnc, bomEnc, xmlGuessEnc, xmlEnc);
                throw new XmlStreamReaderException(msg, cTMime, cTEnc, bomEnc, xmlGuessEnc, xmlEnc);
            }
            bomEnc2 = bomEnc;
            xmlGuessEnc2 = xmlGuessEnc;
            xmlEnc2 = xmlEnc;
        }
        if (cTEnc == null) {
            if (appXml) {
                return calculateRawEncoding(bomEnc2, xmlGuessEnc2, xmlEnc2);
            }
            return this.defaultEncoding == null ? US_ASCII : this.defaultEncoding;
        }
        if (cTEnc.equals(UTF_16BE) || cTEnc.equals(UTF_16LE)) {
            if (bomEnc2 != null) {
                String msg2 = MessageFormat.format(HTTP_EX_1, cTMime, cTEnc, bomEnc2, xmlGuessEnc2, xmlEnc2);
                throw new XmlStreamReaderException(msg2, cTMime, cTEnc, bomEnc2, xmlGuessEnc2, xmlEnc2);
            }
            return cTEnc;
        }
        if (cTEnc.equals(UTF_16)) {
            if (bomEnc2 != null && bomEnc2.startsWith(UTF_16)) {
                return bomEnc2;
            }
            String msg3 = MessageFormat.format(HTTP_EX_2, cTMime, cTEnc, bomEnc2, xmlGuessEnc2, xmlEnc2);
            throw new XmlStreamReaderException(msg3, cTMime, cTEnc, bomEnc2, xmlGuessEnc2, xmlEnc2);
        }
        if (cTEnc.equals(UTF_32BE) || cTEnc.equals(UTF_32LE)) {
            if (bomEnc2 != null) {
                String msg4 = MessageFormat.format(HTTP_EX_1, cTMime, cTEnc, bomEnc2, xmlGuessEnc2, xmlEnc2);
                throw new XmlStreamReaderException(msg4, cTMime, cTEnc, bomEnc2, xmlGuessEnc2, xmlEnc2);
            }
            return cTEnc;
        }
        if (cTEnc.equals(UTF_32)) {
            if (bomEnc2 != null && bomEnc2.startsWith(UTF_32)) {
                return bomEnc2;
            }
            String msg5 = MessageFormat.format(HTTP_EX_2, cTMime, cTEnc, bomEnc2, xmlGuessEnc2, xmlEnc2);
            throw new XmlStreamReaderException(msg5, cTMime, cTEnc, bomEnc2, xmlGuessEnc2, xmlEnc2);
        }
        return cTEnc;
    }

    String calculateRawEncoding(String bomEnc, String xmlGuessEnc, String xmlEnc) throws IOException {
        if (bomEnc == null) {
            if (xmlGuessEnc == null || xmlEnc == null) {
                return this.defaultEncoding == null ? UTF_8 : this.defaultEncoding;
            }
            if (xmlEnc.equals(UTF_16) && (xmlGuessEnc.equals(UTF_16BE) || xmlGuessEnc.equals(UTF_16LE))) {
                return xmlGuessEnc;
            }
            return xmlEnc;
        }
        if (bomEnc.equals(UTF_8)) {
            if (xmlGuessEnc != null && !xmlGuessEnc.equals(UTF_8)) {
                String msg = MessageFormat.format(RAW_EX_1, bomEnc, xmlGuessEnc, xmlEnc);
                throw new XmlStreamReaderException(msg, bomEnc, xmlGuessEnc, xmlEnc);
            }
            if (xmlEnc != null && !xmlEnc.equals(UTF_8)) {
                String msg2 = MessageFormat.format(RAW_EX_1, bomEnc, xmlGuessEnc, xmlEnc);
                throw new XmlStreamReaderException(msg2, bomEnc, xmlGuessEnc, xmlEnc);
            }
            return bomEnc;
        }
        if (bomEnc.equals(UTF_16BE) || bomEnc.equals(UTF_16LE)) {
            if (xmlGuessEnc != null && !xmlGuessEnc.equals(bomEnc)) {
                String msg3 = MessageFormat.format(RAW_EX_1, bomEnc, xmlGuessEnc, xmlEnc);
                throw new XmlStreamReaderException(msg3, bomEnc, xmlGuessEnc, xmlEnc);
            }
            if (xmlEnc != null && !xmlEnc.equals(UTF_16) && !xmlEnc.equals(bomEnc)) {
                String msg4 = MessageFormat.format(RAW_EX_1, bomEnc, xmlGuessEnc, xmlEnc);
                throw new XmlStreamReaderException(msg4, bomEnc, xmlGuessEnc, xmlEnc);
            }
            return bomEnc;
        }
        if (bomEnc.equals(UTF_32BE) || bomEnc.equals(UTF_32LE)) {
            if (xmlGuessEnc != null && !xmlGuessEnc.equals(bomEnc)) {
                String msg5 = MessageFormat.format(RAW_EX_1, bomEnc, xmlGuessEnc, xmlEnc);
                throw new XmlStreamReaderException(msg5, bomEnc, xmlGuessEnc, xmlEnc);
            }
            if (xmlEnc != null && !xmlEnc.equals(UTF_32) && !xmlEnc.equals(bomEnc)) {
                String msg6 = MessageFormat.format(RAW_EX_1, bomEnc, xmlGuessEnc, xmlEnc);
                throw new XmlStreamReaderException(msg6, bomEnc, xmlGuessEnc, xmlEnc);
            }
            return bomEnc;
        }
        String msg7 = MessageFormat.format(RAW_EX_2, bomEnc, xmlGuessEnc, xmlEnc);
        throw new XmlStreamReaderException(msg7, bomEnc, xmlGuessEnc, xmlEnc);
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.reader.close();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String doLenientDetection(java.lang.String r8, org.apache.commons.io.input.XmlStreamReaderException r9) throws java.io.IOException {
        /*
            r7 = this;
            if (r8 == 0) goto L40
            java.lang.String r0 = "text/html"
            boolean r1 = r8.startsWith(r0)
            if (r1 == 0) goto L40
            int r0 = r0.length()
            java.lang.String r8 = r8.substring(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "text/xml"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r8)
            java.lang.String r6 = r0.toString()
            java.lang.String r2 = r9.getBomEncoding()     // Catch: org.apache.commons.io.input.XmlStreamReaderException -> L3a
            java.lang.String r3 = r9.getXmlGuessEncoding()     // Catch: org.apache.commons.io.input.XmlStreamReaderException -> L3a
            java.lang.String r4 = r9.getXmlEncoding()     // Catch: org.apache.commons.io.input.XmlStreamReaderException -> L3a
            r5 = 1
            r1 = r7
            java.lang.String r8 = r1.calculateHttpEncoding(r2, r3, r4, r5, r6)     // Catch: org.apache.commons.io.input.XmlStreamReaderException -> L38
            return r8
        L38:
            r0 = move-exception
            goto L3c
        L3a:
            r0 = move-exception
            r1 = r7
        L3c:
            r8 = r0
            r9 = r8
            r8 = r6
            goto L41
        L40:
            r1 = r7
        L41:
            java.lang.String r0 = r9.getXmlEncoding()
            if (r0 != 0) goto L4b
            java.lang.String r0 = r9.getContentTypeEncoding()
        L4b:
            if (r0 != 0) goto L57
            java.lang.String r2 = r1.defaultEncoding
            if (r2 != 0) goto L54
            java.lang.String r2 = org.apache.commons.io.input.XmlStreamReader.UTF_8
            goto L56
        L54:
            java.lang.String r2 = r1.defaultEncoding
        L56:
            r0 = r2
        L57:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.input.XmlStreamReader.doLenientDetection(java.lang.String, org.apache.commons.io.input.XmlStreamReaderException):java.lang.String");
    }

    public String getDefaultEncoding() {
        return this.defaultEncoding;
    }

    public String getEncoding() {
        return this.encoding;
    }

    private String processHttpStream(BOMInputStream bomInput, BOMInputStream piInput, boolean lenient) throws IOException {
        String bomEnc = bomInput.getBOMCharsetName();
        String xmlGuessEnc = piInput.getBOMCharsetName();
        String xmlEnc = getXmlProlog(piInput, xmlGuessEnc);
        try {
            return calculateRawEncoding(bomEnc, xmlGuessEnc, xmlEnc);
        } catch (XmlStreamReaderException ex) {
            if (lenient) {
                return doLenientDetection(null, ex);
            }
            throw ex;
        }
    }

    private String processHttpStream(BOMInputStream bomInput, BOMInputStream piInput, boolean lenient, String httpContentType) throws IOException {
        String bomEnc = bomInput.getBOMCharsetName();
        String xmlGuessEnc = piInput.getBOMCharsetName();
        String xmlEnc = getXmlProlog(piInput, xmlGuessEnc);
        try {
            return calculateHttpEncoding(bomEnc, xmlGuessEnc, xmlEnc, lenient, httpContentType);
        } catch (XmlStreamReaderException ex) {
            if (lenient) {
                return doLenientDetection(httpContentType, ex);
            }
            throw ex;
        }
    }

    @Override // java.io.Reader
    public int read(char[] buf, int offset, int len) throws IOException {
        return this.reader.read(buf, offset, len);
    }
}
