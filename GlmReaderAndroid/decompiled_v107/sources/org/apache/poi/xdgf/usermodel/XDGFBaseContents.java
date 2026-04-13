package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.ConnectType;
import com.microsoft.schemas.office.visio.x2012.main.PageContentsType;
import com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.exceptions.XDGFException;
import org.apache.poi.xdgf.usermodel.shape.ShapeRenderer;
import org.apache.poi.xdgf.usermodel.shape.ShapeVisitor;
import org.apache.poi.xdgf.usermodel.shape.exceptions.StopVisiting;
import org.apache.poi.xdgf.xml.XDGFXMLDocumentPart;

/* loaded from: classes10.dex */
public class XDGFBaseContents extends XDGFXMLDocumentPart {
    protected List<XDGFConnection> _connections;
    protected PageContentsType _pageContents;
    protected Map<Long, XDGFShape> _shapes;
    protected List<XDGFShape> _toplevelShapes;

    public XDGFBaseContents(PackagePart part) {
        super(part);
        this._toplevelShapes = new ArrayList();
        this._shapes = new HashMap();
        this._connections = new ArrayList();
    }

    @Internal
    public PageContentsType getXmlObject() {
        return this._pageContents;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void onDocumentRead() {
        if (this._pageContents.isSetShapes()) {
            for (ShapeSheetType shapeSheet : this._pageContents.getShapes().getShapeArray()) {
                XDGFShape shape = new XDGFShape(shapeSheet, this, this._document);
                this._toplevelShapes.add(shape);
                addToShapeIndex(shape);
            }
        }
        if (this._pageContents.isSetConnects()) {
            for (ConnectType connect : this._pageContents.getConnects().getConnectArray()) {
                XDGFShape from = this._shapes.get(Long.valueOf(connect.getFromSheet()));
                XDGFShape to = this._shapes.get(Long.valueOf(connect.getToSheet()));
                if (from == null) {
                    throw new POIXMLException(this + "; Connect; Invalid from id: " + connect.getFromSheet());
                }
                if (to == null) {
                    throw new POIXMLException(this + "; Connect; Invalid to id: " + connect.getToSheet());
                }
                this._connections.add(new XDGFConnection(connect, from, to));
            }
        }
    }

    protected void addToShapeIndex(XDGFShape shape) {
        this._shapes.put(Long.valueOf(shape.getID()), shape);
        List<XDGFShape> shapes = shape.getShapes();
        if (shapes == null) {
            return;
        }
        for (XDGFShape subshape : shapes) {
            addToShapeIndex(subshape);
        }
    }

    public void draw(Graphics2D graphics) {
        visitShapes(new ShapeRenderer(graphics));
    }

    public XDGFShape getShapeById(long id) {
        return this._shapes.get(Long.valueOf(id));
    }

    public Map<Long, XDGFShape> getShapesMap() {
        return Collections.unmodifiableMap(this._shapes);
    }

    public Collection<XDGFShape> getShapes() {
        return this._shapes.values();
    }

    public List<XDGFShape> getTopLevelShapes() {
        return Collections.unmodifiableList(this._toplevelShapes);
    }

    public List<XDGFConnection> getConnections() {
        return Collections.unmodifiableList(this._connections);
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public String toString() {
        return getPackagePart().getPartName().toString();
    }

    public void visitShapes(ShapeVisitor visitor) {
        try {
            for (XDGFShape shape : this._toplevelShapes) {
                shape.visitShapes(visitor, new AffineTransform(), 0);
            }
        } catch (POIXMLException e) {
            throw XDGFException.wrap(this, e);
        } catch (StopVisiting e2) {
        }
    }
}
