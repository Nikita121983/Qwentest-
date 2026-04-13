package org.apache.poi.xssf.usermodel;

import com.microsoft.schemas.office.excel.CTClientData;
import com.microsoft.schemas.office.excel.STObjectType;
import com.microsoft.schemas.office.office.CTIdMap;
import com.microsoft.schemas.office.office.CTShapeLayout;
import com.microsoft.schemas.office.office.STConnectType;
import com.microsoft.schemas.office.office.STInsetMode;
import com.microsoft.schemas.office.office.ShapelayoutDocument;
import com.microsoft.schemas.vml.CTGroup;
import com.microsoft.schemas.vml.CTPath;
import com.microsoft.schemas.vml.CTShadow;
import com.microsoft.schemas.vml.CTShape;
import com.microsoft.schemas.vml.CTShapetype;
import com.microsoft.schemas.vml.STExt;
import com.microsoft.schemas.vml.STStrokeJoinStyle;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.schemas.vmldrawing.XmlDocument;
import org.apache.poi.util.Internal;
import org.apache.poi.util.ReplacingInputStream;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;

/* loaded from: classes10.dex */
public final class XSSFVMLDrawing extends POIXMLDocumentPart {
    private static final String COMMENT_SHAPE_TYPE_ID = "_x0000_t202";
    public static final QName QNAME_VMLDRAWING = new QName("urn:schemas-poi-apache-org:vmldrawing", "xml");
    private static final Pattern ptrn_shapeId = Pattern.compile("_x0000_s(\\d+)");
    private int _shapeId;
    private String _shapeTypeId;
    private XmlDocument root;

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFVMLDrawing() {
        this._shapeId = 1024;
        newDrawing();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFVMLDrawing(PackagePart part) throws IOException, XmlException {
        super(part);
        this._shapeId = 1024;
        InputStream stream = getPackagePart().getInputStream();
        try {
            read(stream);
            if (stream != null) {
                stream.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public XmlDocument getDocument() {
        return this.root;
    }

    protected void read(InputStream is) throws IOException, XmlException {
        XmlOptions xopt = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xopt.setLoadSubstituteNamespaces(Collections.singletonMap("", QNAME_VMLDRAWING.getNamespaceURI()));
        xopt.setDocumentType(XmlDocument.type);
        this.root = XmlDocument.Factory.parse(new ReplacingInputStream(new ReplacingInputStream(is, "<br>", "<br/>"), " xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\"", ""), xopt);
        if (this.root.getXml() == null) {
            return;
        }
        XmlCursor cur = this.root.getXml().newCursor();
        try {
            for (boolean found = cur.toFirstChild(); found; found = cur.toNextSibling()) {
                XmlObject xo = cur.getObject();
                if (xo instanceof CTShapetype) {
                    this._shapeTypeId = ((CTShapetype) xo).getId();
                } else if (xo instanceof CTShape) {
                    CTShape shape = (CTShape) xo;
                    String id = shape.getId();
                    if (id != null) {
                        Matcher m = ptrn_shapeId.matcher(id);
                        if (m.find()) {
                            this._shapeId = Math.max(this._shapeId, Integer.parseInt(m.group(1)));
                        }
                    }
                }
            }
            if (cur != null) {
                cur.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    protected List<XmlObject> getItems() {
        List<XmlObject> items = new ArrayList<>();
        XmlCursor cur = this.root.getXml().newCursor();
        try {
            for (boolean found = cur.toFirstChild(); found; found = cur.toNextSibling()) {
                items.add(cur.getObject());
            }
            if (cur != null) {
                cur.close();
            }
            return items;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    protected void write(OutputStream out) throws IOException {
        XmlOptions xopt = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xopt.setSaveImplicitNamespaces(Collections.singletonMap("", QNAME_VMLDRAWING.getNamespaceURI()));
        this.root.save(out, xopt);
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            write(out);
            if (out != null) {
                out.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (out != null) {
                    try {
                        out.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private void newDrawing() {
        this.root = XmlDocument.Factory.newInstance();
        XmlCursor xml = this.root.addNewXml().newCursor();
        try {
            ShapelayoutDocument layDoc = ShapelayoutDocument.Factory.newInstance();
            CTShapeLayout layout = layDoc.addNewShapelayout();
            layout.setExt(STExt.EDIT);
            CTIdMap idmap = layout.addNewIdmap();
            idmap.setExt(STExt.EDIT);
            idmap.setData("1");
            xml.toEndToken();
            XmlCursor layCur = layDoc.newCursor();
            try {
                layCur.copyXmlContents(xml);
                if (layCur != null) {
                    layCur.close();
                }
                CTGroup grp = CTGroup.Factory.newInstance();
                CTShapetype shapetype = grp.addNewShapetype();
                this._shapeTypeId = COMMENT_SHAPE_TYPE_ID;
                shapetype.setId(this._shapeTypeId);
                shapetype.setCoordsize("21600,21600");
                shapetype.setSpt(202.0f);
                shapetype.setPath2("m,l,21600r21600,l21600,xe");
                shapetype.addNewStroke().setJoinstyle(STStrokeJoinStyle.MITER);
                CTPath path = shapetype.addNewPath();
                path.setGradientshapeok(STTrueFalse.T);
                path.setConnecttype(STConnectType.RECT);
                xml.toEndToken();
                XmlCursor grpCur = grp.newCursor();
                try {
                    grpCur.copyXmlContents(xml);
                    if (grpCur != null) {
                        grpCur.close();
                    }
                    if (xml != null) {
                        xml.close();
                    }
                } finally {
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (xml != null) {
                    try {
                        xml.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Internal
    public CTShape newCommentShape() {
        CTGroup grp = CTGroup.Factory.newInstance();
        CTShape shape = grp.addNewShape();
        StringBuilder append = new StringBuilder().append("_x0000_s");
        int i = this._shapeId + 1;
        this._shapeId = i;
        shape.setId(append.append(i).toString());
        shape.setType("#" + this._shapeTypeId);
        shape.setStyle("position:absolute; visibility:hidden");
        shape.setFillcolor("#ffffe1");
        shape.setInsetmode(STInsetMode.AUTO);
        shape.addNewFill().setColor("#ffffe1");
        CTShadow shadow = shape.addNewShadow();
        shadow.setOn(STTrueFalse.T);
        shadow.setColor("black");
        shadow.setObscured(STTrueFalse.T);
        shape.addNewPath().setConnecttype(STConnectType.NONE);
        shape.addNewTextbox().setStyle("mso-direction-alt:auto");
        CTClientData cldata = shape.addNewClientData();
        cldata.setObjectType(STObjectType.NOTE);
        cldata.addNewMoveWithCells();
        cldata.addNewSizeWithCells();
        cldata.addNewAnchor().setStringValue("1, 15, 0, 2, 3, 15, 3, 16");
        cldata.addNewAutoFill().setStringValue("False");
        cldata.addNewRow().setBigIntegerValue(BigInteger.valueOf(0L));
        cldata.addNewColumn().setBigIntegerValue(BigInteger.valueOf(0L));
        XmlCursor xml = this.root.getXml().newCursor();
        try {
            xml.toEndToken();
            XmlCursor grpCur = grp.newCursor();
            try {
                grpCur.copyXmlContents(xml);
                xml.toPrevSibling();
                CTShape shape2 = (CTShape) xml.getObject();
                if (grpCur != null) {
                    grpCur.close();
                }
                if (xml != null) {
                    xml.close();
                }
                return shape2;
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (xml != null) {
                    try {
                        xml.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public CTShape findCommentShape(int row, int col) {
        XmlCursor cur = this.root.getXml().newCursor();
        try {
            for (boolean found = cur.toFirstChild(); found; found = cur.toNextSibling()) {
                XmlObject itm = cur.getObject();
                if (matchCommentShape(itm, row, col)) {
                    CTShape cTShape = (CTShape) itm;
                    if (cur != null) {
                        cur.close();
                    }
                    return cTShape;
                }
            }
            if (cur != null) {
                cur.close();
                return null;
            }
            return null;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private boolean matchCommentShape(XmlObject itm, int row, int col) {
        if (!(itm instanceof CTShape)) {
            return false;
        }
        CTShape sh = (CTShape) itm;
        if (sh.sizeOfClientDataArray() == 0) {
            return false;
        }
        CTClientData cldata = sh.getClientDataArray(0);
        try {
            if (cldata.getObjectType() != STObjectType.NOTE) {
                return false;
            }
            int crow = cldata.getRowArray(0).intValue();
            int ccol = cldata.getColumnArray(0).intValue();
            return crow == row && ccol == col;
        } catch (XmlValueOutOfRangeException e) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean removeCommentShape(int row, int col) {
        XmlCursor cur = this.root.getXml().newCursor();
        try {
            for (boolean found = cur.toFirstChild(); found; found = cur.toNextSibling()) {
                XmlObject itm = cur.getObject();
                if (matchCommentShape(itm, row, col)) {
                    cur.removeXml();
                    if (cur != null) {
                        cur.close();
                        return true;
                    }
                    return true;
                }
            }
            if (cur != null) {
                cur.close();
                return false;
            }
            return false;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }
}
