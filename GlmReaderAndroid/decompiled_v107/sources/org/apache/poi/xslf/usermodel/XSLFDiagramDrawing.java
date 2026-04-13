package org.apache.poi.xslf.usermodel;

import com.microsoft.schemas.office.drawing.x2008.diagram.DrawingDocument;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.xmlbeans.XmlException;

/* loaded from: classes10.dex */
public class XSLFDiagramDrawing extends POIXMLDocumentPart {
    private final DrawingDocument _drawingDoc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFDiagramDrawing() {
        this._drawingDoc = DrawingDocument.Factory.newInstance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFDiagramDrawing(PackagePart part) throws XmlException, IOException {
        super(part);
        this._drawingDoc = readPackagePart(part);
    }

    private static DrawingDocument readPackagePart(PackagePart part) throws IOException, XmlException {
        InputStream is = part.getInputStream();
        try {
            DrawingDocument parse = DrawingDocument.Factory.parse(is);
            if (is != null) {
                is.close();
            }
            return parse;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public DrawingDocument getDrawingDocument() {
        return this._drawingDoc;
    }
}
