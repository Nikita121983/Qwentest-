package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.sl.usermodel.MetroShapeProvider;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;

@Internal
/* loaded from: classes10.dex */
public class XSLFMetroShape implements MetroShapeProvider {
    @Override // org.apache.poi.sl.usermodel.MetroShapeProvider
    public XSLFShape parseShape(byte[] metroBytes) throws IOException {
        try {
            OPCPackage pkg = OPCPackage.open(UnsynchronizedByteArrayInputStream.builder().setByteArray(metroBytes).get());
            try {
                PackagePartName shapePN = PackagingURIHelper.createPartName("/drs/shapexml.xml");
                PackagePart shapePart = pkg.getPart(shapePN);
                if (shapePart != null) {
                    InputStream stream = shapePart.getInputStream();
                    try {
                        CTGroupShape gs = CTGroupShape.Factory.parse(stream, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                        XSLFGroupShape xgs = new XSLFGroupShape(gs, null);
                        XSLFShape xSLFShape = xgs.getShapes().get(0);
                        if (stream != null) {
                            stream.close();
                        }
                        if (pkg != null) {
                            pkg.close();
                        }
                        return xSLFShape;
                    } finally {
                    }
                } else {
                    if (pkg != null) {
                        pkg.close();
                    }
                    return null;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (pkg != null) {
                        try {
                            pkg.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        } catch (InvalidFormatException | XmlException e) {
            throw new IOException("can't parse metro shape", e);
        }
    }
}
