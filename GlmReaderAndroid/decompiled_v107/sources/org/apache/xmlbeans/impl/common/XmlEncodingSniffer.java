package org.apache.xmlbeans.impl.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/* loaded from: classes11.dex */
public class XmlEncodingSniffer {
    private String _javaencoding;
    private Reader _reader;
    private InputStream _stream;
    private String _xmlencoding;

    public XmlEncodingSniffer(Reader reader, String encodingDefault) throws IOException, UnsupportedEncodingException {
        encodingDefault = encodingDefault == null ? "UTF-8" : encodingDefault;
        SniffedXmlReader sniffedReader = new SniffedXmlReader(reader);
        this._reader = sniffedReader;
        this._xmlencoding = sniffedReader.getXmlEncoding();
        if (this._xmlencoding == null) {
            this._xmlencoding = EncodingMap.getJava2IANAMapping(encodingDefault);
            if (this._xmlencoding != null) {
                this._javaencoding = encodingDefault;
            } else {
                this._xmlencoding = encodingDefault;
            }
        }
        if (this._xmlencoding == null) {
            this._xmlencoding = "UTF-8";
        }
        this._javaencoding = EncodingMap.getIANA2JavaMapping(this._xmlencoding);
        if (this._javaencoding == null) {
            this._javaencoding = this._xmlencoding;
        }
    }

    public String getXmlEncoding() {
        return this._xmlencoding;
    }

    public String getJavaEncoding() {
        return this._javaencoding;
    }

    public InputStream getStream() throws UnsupportedEncodingException {
        if (this._stream != null) {
            InputStream is = this._stream;
            this._stream = null;
            return is;
        }
        if (this._reader == null) {
            return null;
        }
        InputStream is2 = new ReaderInputStream(this._reader, this._javaencoding);
        this._reader = null;
        return is2;
    }

    public Reader getReader() throws UnsupportedEncodingException {
        if (this._reader != null) {
            Reader reader = this._reader;
            this._reader = null;
            return reader;
        }
        if (this._stream == null) {
            return null;
        }
        Reader reader2 = new InputStreamReader(this._stream, this._javaencoding);
        this._stream = null;
        return reader2;
    }
}
