package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.impl;

import javax.xml.namespace.QName;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGraphicFrameProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTEffectExtent;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosV;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapNone;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapThrough;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapTight;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapTopBottom;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapDistance;

/* loaded from: classes11.dex */
public class CTAnchorImpl extends XmlComplexContentImpl implements CTAnchor {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "simplePos"), new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "positionH"), new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "positionV"), new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "extent"), new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "effectExtent"), new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "wrapNone"), new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "wrapSquare"), new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "wrapTight"), new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "wrapThrough"), new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "wrapTopAndBottom"), new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "docPr"), new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "cNvGraphicFramePr"), new QName(XSSFRelation.NS_DRAWINGML, "graphic"), new QName("", "distT"), new QName("", "distB"), new QName("", "distL"), new QName("", "distR"), new QName("", "simplePos"), new QName("", "relativeHeight"), new QName("", "behindDoc"), new QName("", CellUtil.LOCKED), new QName("", "layoutInCell"), new QName("", CellUtil.HIDDEN), new QName("", "allowOverlap")};
    private static final long serialVersionUID = 1;

    public CTAnchorImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTPoint2D getSimplePos() {
        CTPoint2D cTPoint2D;
        synchronized (monitor()) {
            check_orphaned();
            CTPoint2D target = (CTPoint2D) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTPoint2D = target == null ? null : target;
        }
        return cTPoint2D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setSimplePos(CTPoint2D simplePos) {
        generatedSetterHelperImpl(simplePos, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTPoint2D addNewSimplePos() {
        CTPoint2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPoint2D) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTPosH getPositionH() {
        CTPosH cTPosH;
        synchronized (monitor()) {
            check_orphaned();
            CTPosH target = (CTPosH) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTPosH = target == null ? null : target;
        }
        return cTPosH;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setPositionH(CTPosH positionH) {
        generatedSetterHelperImpl(positionH, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTPosH addNewPositionH() {
        CTPosH target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPosH) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTPosV getPositionV() {
        CTPosV cTPosV;
        synchronized (monitor()) {
            check_orphaned();
            CTPosV target = (CTPosV) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTPosV = target == null ? null : target;
        }
        return cTPosV;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setPositionV(CTPosV positionV) {
        generatedSetterHelperImpl(positionV, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTPosV addNewPositionV() {
        CTPosV target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPosV) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTPositiveSize2D getExtent() {
        CTPositiveSize2D cTPositiveSize2D;
        synchronized (monitor()) {
            check_orphaned();
            CTPositiveSize2D target = (CTPositiveSize2D) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTPositiveSize2D = target == null ? null : target;
        }
        return cTPositiveSize2D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setExtent(CTPositiveSize2D extent) {
        generatedSetterHelperImpl(extent, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTPositiveSize2D addNewExtent() {
        CTPositiveSize2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveSize2D) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTEffectExtent getEffectExtent() {
        CTEffectExtent cTEffectExtent;
        synchronized (monitor()) {
            check_orphaned();
            CTEffectExtent target = (CTEffectExtent) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTEffectExtent = target == null ? null : target;
        }
        return cTEffectExtent;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean isSetEffectExtent() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setEffectExtent(CTEffectExtent effectExtent) {
        generatedSetterHelperImpl(effectExtent, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTEffectExtent addNewEffectExtent() {
        CTEffectExtent target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEffectExtent) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void unsetEffectExtent() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTWrapNone getWrapNone() {
        CTWrapNone cTWrapNone;
        synchronized (monitor()) {
            check_orphaned();
            CTWrapNone target = (CTWrapNone) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTWrapNone = target == null ? null : target;
        }
        return cTWrapNone;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean isSetWrapNone() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setWrapNone(CTWrapNone wrapNone) {
        generatedSetterHelperImpl(wrapNone, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTWrapNone addNewWrapNone() {
        CTWrapNone target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTWrapNone) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void unsetWrapNone() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTWrapSquare getWrapSquare() {
        CTWrapSquare cTWrapSquare;
        synchronized (monitor()) {
            check_orphaned();
            CTWrapSquare target = (CTWrapSquare) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTWrapSquare = target == null ? null : target;
        }
        return cTWrapSquare;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean isSetWrapSquare() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setWrapSquare(CTWrapSquare wrapSquare) {
        generatedSetterHelperImpl(wrapSquare, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTWrapSquare addNewWrapSquare() {
        CTWrapSquare target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTWrapSquare) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void unsetWrapSquare() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTWrapTight getWrapTight() {
        CTWrapTight cTWrapTight;
        synchronized (monitor()) {
            check_orphaned();
            CTWrapTight target = get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTWrapTight = target == null ? null : target;
        }
        return cTWrapTight;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean isSetWrapTight() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setWrapTight(CTWrapTight wrapTight) {
        generatedSetterHelperImpl(wrapTight, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTWrapTight addNewWrapTight() {
        CTWrapTight target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void unsetWrapTight() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTWrapThrough getWrapThrough() {
        CTWrapThrough cTWrapThrough;
        synchronized (monitor()) {
            check_orphaned();
            CTWrapThrough target = get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTWrapThrough = target == null ? null : target;
        }
        return cTWrapThrough;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean isSetWrapThrough() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setWrapThrough(CTWrapThrough wrapThrough) {
        generatedSetterHelperImpl(wrapThrough, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTWrapThrough addNewWrapThrough() {
        CTWrapThrough target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void unsetWrapThrough() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTWrapTopBottom getWrapTopAndBottom() {
        CTWrapTopBottom cTWrapTopBottom;
        synchronized (monitor()) {
            check_orphaned();
            CTWrapTopBottom target = get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTWrapTopBottom = target == null ? null : target;
        }
        return cTWrapTopBottom;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean isSetWrapTopAndBottom() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setWrapTopAndBottom(CTWrapTopBottom wrapTopAndBottom) {
        generatedSetterHelperImpl(wrapTopAndBottom, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTWrapTopBottom addNewWrapTopAndBottom() {
        CTWrapTopBottom target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void unsetWrapTopAndBottom() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTNonVisualDrawingProps getDocPr() {
        CTNonVisualDrawingProps cTNonVisualDrawingProps;
        synchronized (monitor()) {
            check_orphaned();
            CTNonVisualDrawingProps target = (CTNonVisualDrawingProps) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTNonVisualDrawingProps = target == null ? null : target;
        }
        return cTNonVisualDrawingProps;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setDocPr(CTNonVisualDrawingProps docPr) {
        generatedSetterHelperImpl(docPr, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTNonVisualDrawingProps addNewDocPr() {
        CTNonVisualDrawingProps target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNonVisualDrawingProps) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTNonVisualGraphicFrameProperties getCNvGraphicFramePr() {
        CTNonVisualGraphicFrameProperties cTNonVisualGraphicFrameProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTNonVisualGraphicFrameProperties target = (CTNonVisualGraphicFrameProperties) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTNonVisualGraphicFrameProperties = target == null ? null : target;
        }
        return cTNonVisualGraphicFrameProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean isSetCNvGraphicFramePr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setCNvGraphicFramePr(CTNonVisualGraphicFrameProperties cNvGraphicFramePr) {
        generatedSetterHelperImpl(cNvGraphicFramePr, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTNonVisualGraphicFrameProperties addNewCNvGraphicFramePr() {
        CTNonVisualGraphicFrameProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNonVisualGraphicFrameProperties) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void unsetCNvGraphicFramePr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTGraphicalObject getGraphic() {
        CTGraphicalObject cTGraphicalObject;
        synchronized (monitor()) {
            check_orphaned();
            CTGraphicalObject target = (CTGraphicalObject) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTGraphicalObject = target == null ? null : target;
        }
        return cTGraphicalObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setGraphic(CTGraphicalObject graphic) {
        generatedSetterHelperImpl(graphic, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public CTGraphicalObject addNewGraphic() {
        CTGraphicalObject target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGraphicalObject) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public long getDistT() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public STWrapDistance xgetDistT() {
        STWrapDistance target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STWrapDistance) get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean isSetDistT() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setDistT(long distT) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setLongValue(distT);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void xsetDistT(STWrapDistance distT) {
        synchronized (monitor()) {
            check_orphaned();
            STWrapDistance target = (STWrapDistance) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (STWrapDistance) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(distT);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void unsetDistT() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public long getDistB() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public STWrapDistance xgetDistB() {
        STWrapDistance target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STWrapDistance) get_store().find_attribute_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean isSetDistB() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setDistB(long distB) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.setLongValue(distB);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void xsetDistB(STWrapDistance distB) {
        synchronized (monitor()) {
            check_orphaned();
            STWrapDistance target = (STWrapDistance) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (STWrapDistance) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.set(distB);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void unsetDistB() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[14]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public long getDistL() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public STWrapDistance xgetDistL() {
        STWrapDistance target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STWrapDistance) get_store().find_attribute_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean isSetDistL() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[15]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setDistL(long distL) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.setLongValue(distL);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void xsetDistL(STWrapDistance distL) {
        synchronized (monitor()) {
            check_orphaned();
            STWrapDistance target = (STWrapDistance) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (STWrapDistance) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.set(distL);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void unsetDistL() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[15]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public long getDistR() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public STWrapDistance xgetDistR() {
        STWrapDistance target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STWrapDistance) get_store().find_attribute_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean isSetDistR() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[16]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setDistR(long distR) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.setLongValue(distR);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void xsetDistR(STWrapDistance distR) {
        synchronized (monitor()) {
            check_orphaned();
            STWrapDistance target = (STWrapDistance) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (STWrapDistance) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.set(distR);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void unsetDistR() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[16]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean getSimplePos2() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public XmlBoolean xgetSimplePos2() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean isSetSimplePos2() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[17]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setSimplePos2(boolean simplePos2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.setBooleanValue(simplePos2);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void xsetSimplePos2(XmlBoolean simplePos2) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.set(simplePos2);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void unsetSimplePos2() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[17]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public long getRelativeHeight() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public XmlUnsignedInt xgetRelativeHeight() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setRelativeHeight(long relativeHeight) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.setLongValue(relativeHeight);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void xsetRelativeHeight(XmlUnsignedInt relativeHeight) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.set(relativeHeight);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean getBehindDoc() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public XmlBoolean xgetBehindDoc() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setBehindDoc(boolean behindDoc) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.setBooleanValue(behindDoc);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void xsetBehindDoc(XmlBoolean behindDoc) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.set(behindDoc);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean getLocked() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public XmlBoolean xgetLocked() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setLocked(boolean locked) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.setBooleanValue(locked);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void xsetLocked(XmlBoolean locked) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.set(locked);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean getLayoutInCell() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public XmlBoolean xgetLayoutInCell() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setLayoutInCell(boolean layoutInCell) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.setBooleanValue(layoutInCell);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void xsetLayoutInCell(XmlBoolean layoutInCell) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.set(layoutInCell);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean getHidden() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public XmlBoolean xgetHidden() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean isSetHidden() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[22]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setHidden(boolean hidden) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.setBooleanValue(hidden);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void xsetHidden(XmlBoolean hidden) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.set(hidden);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void unsetHidden() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[22]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public boolean getAllowOverlap() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public XmlBoolean xgetAllowOverlap() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void setAllowOverlap(boolean allowOverlap) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.setBooleanValue(allowOverlap);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor
    public void xsetAllowOverlap(XmlBoolean allowOverlap) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.set(allowOverlap);
        }
    }
}
