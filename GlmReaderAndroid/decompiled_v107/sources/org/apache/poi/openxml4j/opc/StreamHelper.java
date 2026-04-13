package org.apache.poi.openxml4j.opc;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.XMLHelper;
import org.w3c.dom.Document;

@Internal
/* loaded from: classes10.dex */
public final class StreamHelper {
    private StreamHelper() {
    }

    public static boolean saveXmlInStream(Document xmlContent, OutputStream outStream) {
        try {
            xmlContent.setXmlStandalone(true);
            Transformer trans = XMLHelper.newTransformer();
            DOMSource xmlSource = new DOMSource(xmlContent);
            Result outputTarget = new StreamResult(new FilterOutputStream(outStream) { // from class: org.apache.poi.openxml4j.opc.StreamHelper.1
                @Override // java.io.FilterOutputStream, java.io.OutputStream
                public void write(byte[] b, int off, int len) throws IOException {
                    this.out.write(b, off, len);
                }

                @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    this.out.flush();
                }
            });
            trans.setOutputProperty("encoding", "UTF-8");
            trans.setOutputProperty("indent", BooleanUtils.NO);
            trans.setOutputProperty("standalone", BooleanUtils.YES);
            trans.transform(xmlSource, outputTarget);
            return true;
        } catch (TransformerException e) {
            return false;
        }
    }

    public static boolean copyStream(InputStream inStream, OutputStream outStream) {
        try {
            IOUtils.copy(inStream, outStream);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
