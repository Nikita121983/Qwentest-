package org.apache.poi.poifs.crypt.dsig;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import javax.xml.crypto.Data;
import javax.xml.crypto.OctetStreamData;
import javax.xml.crypto.URIDereferencer;
import javax.xml.crypto.URIReference;
import javax.xml.crypto.URIReferenceException;
import javax.xml.crypto.XMLCryptoContext;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

/* loaded from: classes10.dex */
public class OOXMLURIDereferencer implements URIDereferencer {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) OOXMLURIDereferencer.class);
    private URIDereferencer baseUriDereferencer;
    private SignatureInfo signatureInfo;

    public void setSignatureInfo(SignatureInfo signatureInfo) {
        this.signatureInfo = signatureInfo;
        this.baseUriDereferencer = signatureInfo.getSignatureFactory().getURIDereferencer();
    }

    public Data dereference(URIReference uriReference, XMLCryptoContext context) throws URIReferenceException {
        if (uriReference == null) {
            throw new NullPointerException("URIReference cannot be null - call setSignatureInfo(...) before");
        }
        if (context == null) {
            throw new NullPointerException("XMLCryptoContext cannot be null");
        }
        try {
            URI uri = new URI(uriReference.getURI());
            PackagePart part = findPart(uri);
            if (part == null) {
                LOG.atDebug().log("cannot resolve {}, delegating to base DOM URI dereferencer", uri);
                return this.baseUriDereferencer.dereference(uriReference, context);
            }
            InputStream dataStream = null;
            try {
                dataStream = part.getInputStream();
                if (part.getPartName().toString().endsWith(PackagingURIHelper.RELATIONSHIP_PART_EXTENSION_NAME)) {
                    UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().get();
                    while (true) {
                        try {
                            int ch = dataStream.read();
                            if (ch == -1) {
                                break;
                            }
                            if (ch != 10 && ch != 13) {
                                bos.write(ch);
                            }
                        } finally {
                        }
                    }
                    dataStream = bos.toInputStream();
                    if (bos != null) {
                        bos.close();
                    }
                }
                return new OctetStreamData(dataStream, uri.toString(), (String) null);
            } catch (IOException e) {
                IOUtils.closeQuietly(dataStream);
                throw new URIReferenceException("I/O error: " + e.getMessage(), e);
            }
        } catch (URISyntaxException e2) {
            throw new URIReferenceException("could not URL decode the uri: " + uriReference.getURI(), e2);
        }
    }

    private PackagePart findPart(URI uri) {
        LOG.atDebug().log("dereference: {}", uri);
        String path = uri.getPath();
        if (path == null || path.isEmpty()) {
            LOG.atDebug().log("illegal part name (expected): {}", uri);
            return null;
        }
        try {
            PackagePartName ppn = PackagingURIHelper.createPartName(path);
            return this.signatureInfo.getOpcPackage().getPart(ppn);
        } catch (InvalidFormatException e) {
            LOG.atWarn().log("illegal part name (not expected) in {}", uri);
            return null;
        }
    }
}
