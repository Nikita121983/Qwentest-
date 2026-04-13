package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.TblStyleLstDocument;

/* loaded from: classes10.dex */
public class XSLFTableStyles extends POIXMLDocumentPart implements Iterable<XSLFTableStyle> {
    private List<XSLFTableStyle> _styles;
    private CTTableStyleList _tblStyleLst;

    public XSLFTableStyles() {
    }

    public XSLFTableStyles(PackagePart part) throws IOException, XmlException {
        super(part);
        InputStream is = getPackagePart().getInputStream();
        try {
            TblStyleLstDocument styleDoc = TblStyleLstDocument.Factory.parse(is);
            if (is != null) {
                is.close();
            }
            this._tblStyleLst = styleDoc.getTblStyleLst();
            List<CTTableStyle> tblStyles = this._tblStyleLst.getTblStyleList();
            this._styles = new ArrayList(tblStyles.size());
            for (CTTableStyle c : tblStyles) {
                this._styles.add(new XSLFTableStyle(c));
            }
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

    public CTTableStyleList getXmlObject() {
        return this._tblStyleLst;
    }

    @Override // java.lang.Iterable
    public Iterator<XSLFTableStyle> iterator() {
        return this._styles.iterator();
    }

    public List<XSLFTableStyle> getStyles() {
        return Collections.unmodifiableList(this._styles);
    }
}
