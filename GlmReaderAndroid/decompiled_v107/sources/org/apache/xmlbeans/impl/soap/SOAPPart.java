package org.apache.xmlbeans.impl.soap;

import java.util.Iterator;
import javax.xml.transform.Source;
import org.w3c.dom.Document;

/* loaded from: classes11.dex */
public abstract class SOAPPart implements Document {
    public abstract void addMimeHeader(String str, String str2);

    public abstract Iterator<MimeHeader> getAllMimeHeaders();

    public abstract Source getContent() throws SOAPException;

    public abstract SOAPEnvelope getEnvelope() throws SOAPException;

    public abstract Iterator<MimeHeader> getMatchingMimeHeaders(String[] strArr);

    public abstract String[] getMimeHeader(String str);

    public abstract Iterator<MimeHeader> getNonMatchingMimeHeaders(String[] strArr);

    public abstract void removeAllMimeHeaders();

    public abstract void removeMimeHeader(String str);

    public abstract void setContent(Source source) throws SOAPException;

    public abstract void setMimeHeader(String str, String str2);

    public String getContentId() {
        String[] as = getMimeHeader("Content-Id");
        if (as != null && as.length > 0) {
            return as[0];
        }
        return null;
    }

    public String getContentLocation() {
        String[] as = getMimeHeader("Content-Location");
        if (as != null && as.length > 0) {
            return as[0];
        }
        return null;
    }

    public void setContentId(String contentId) {
        setMimeHeader("Content-Id", contentId);
    }

    public void setContentLocation(String contentLocation) {
        setMimeHeader("Content-Location", contentLocation);
    }
}
