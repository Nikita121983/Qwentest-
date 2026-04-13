package com.microsoft.schemas.vml.impl;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.microsoft.schemas.office.office.CTStrokeChild;
import com.microsoft.schemas.vml.CTStroke;
import com.microsoft.schemas.vml.STFillType;
import com.microsoft.schemas.vml.STImageAspect;
import com.microsoft.schemas.vml.STImageAspect$Enum;
import com.microsoft.schemas.vml.STStrokeArrowLength;
import com.microsoft.schemas.vml.STStrokeArrowLength$Enum;
import com.microsoft.schemas.vml.STStrokeArrowType;
import com.microsoft.schemas.vml.STStrokeArrowWidth;
import com.microsoft.schemas.vml.STStrokeArrowWidth$Enum;
import com.microsoft.schemas.vml.STStrokeEndCap;
import com.microsoft.schemas.vml.STStrokeEndCap$Enum;
import com.microsoft.schemas.vml.STStrokeJoinStyle;
import com.microsoft.schemas.vml.STStrokeLineStyle;
import com.microsoft.schemas.vml.STStrokeLineStyle$Enum;
import java.math.BigDecimal;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlDecimal;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STColorType;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;

/* loaded from: classes9.dex */
public class CTStrokeImpl extends XmlComplexContentImpl implements CTStroke {
    private static final QName[] PROPERTY_QNAME = {new QName("urn:schemas-microsoft-com:office:office", "left"), new QName("urn:schemas-microsoft-com:office:office", "top"), new QName("urn:schemas-microsoft-com:office:office", "right"), new QName("urn:schemas-microsoft-com:office:office", "bottom"), new QName("urn:schemas-microsoft-com:office:office", "column"), new QName("", "id"), new QName("", "on"), new QName("", "weight"), new QName("", TypedValues.Custom.S_COLOR), new QName("", "opacity"), new QName("", "linestyle"), new QName("", "miterlimit"), new QName("", "joinstyle"), new QName("", "endcap"), new QName("", "dashstyle"), new QName("", "filltype"), new QName("", "src"), new QName("", "imageaspect"), new QName("", "imagesize"), new QName("", "imagealignshape"), new QName("", "color2"), new QName("", "startarrow"), new QName("", "startarrowwidth"), new QName("", "startarrowlength"), new QName("", "endarrow"), new QName("", "endarrowwidth"), new QName("", "endarrowlength"), new QName("urn:schemas-microsoft-com:office:office", "href"), new QName("urn:schemas-microsoft-com:office:office", "althref"), new QName("urn:schemas-microsoft-com:office:office", "title"), new QName("urn:schemas-microsoft-com:office:office", "forcedash"), new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "id"), new QName("", "insetpen"), new QName("urn:schemas-microsoft-com:office:office", "relid")};
    private static final long serialVersionUID = 1;

    public CTStrokeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public CTStrokeChild getLeft() {
        CTStrokeChild cTStrokeChild;
        synchronized (monitor()) {
            check_orphaned();
            CTStrokeChild target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTStrokeChild = target == null ? null : target;
        }
        return cTStrokeChild;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetLeft() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setLeft(CTStrokeChild left) {
        generatedSetterHelperImpl(left, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public CTStrokeChild addNewLeft() {
        CTStrokeChild target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetLeft() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public CTStrokeChild getTop() {
        CTStrokeChild cTStrokeChild;
        synchronized (monitor()) {
            check_orphaned();
            CTStrokeChild target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTStrokeChild = target == null ? null : target;
        }
        return cTStrokeChild;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetTop() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setTop(CTStrokeChild top) {
        generatedSetterHelperImpl(top, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public CTStrokeChild addNewTop() {
        CTStrokeChild target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetTop() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public CTStrokeChild getRight() {
        CTStrokeChild cTStrokeChild;
        synchronized (monitor()) {
            check_orphaned();
            CTStrokeChild target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTStrokeChild = target == null ? null : target;
        }
        return cTStrokeChild;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetRight() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setRight(CTStrokeChild right) {
        generatedSetterHelperImpl(right, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public CTStrokeChild addNewRight() {
        CTStrokeChild target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetRight() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public CTStrokeChild getBottom() {
        CTStrokeChild cTStrokeChild;
        synchronized (monitor()) {
            check_orphaned();
            CTStrokeChild target = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTStrokeChild = target == null ? null : target;
        }
        return cTStrokeChild;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetBottom() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setBottom(CTStrokeChild bottom) {
        generatedSetterHelperImpl(bottom, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public CTStrokeChild addNewBottom() {
        CTStrokeChild target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetBottom() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public CTStrokeChild getColumn() {
        CTStrokeChild cTStrokeChild;
        synchronized (monitor()) {
            check_orphaned();
            CTStrokeChild target = get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTStrokeChild = target == null ? null : target;
        }
        return cTStrokeChild;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetColumn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setColumn(CTStrokeChild column) {
        generatedSetterHelperImpl(column, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public CTStrokeChild addNewColumn() {
        CTStrokeChild target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetColumn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public XmlString xgetId() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setStringValue(id);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetId(XmlString id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(id);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STTrueFalse.Enum getOn() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STTrueFalse xgetOn() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetOn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setOn(STTrueFalse.Enum on) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setEnumValue(on);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetOn(STTrueFalse on) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(on);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetOn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public String getWeight() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public XmlString xgetWeight() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetWeight() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setWeight(String weight) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setStringValue(weight);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetWeight(XmlString weight) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(weight);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetWeight() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public String getColor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STColorType xgetColor() {
        STColorType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetColor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setColor(String color) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setStringValue(color);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetColor(STColorType color) {
        synchronized (monitor()) {
            check_orphaned();
            STColorType target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STColorType) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(color);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetColor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public String getOpacity() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public XmlString xgetOpacity() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetOpacity() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setOpacity(String opacity) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setStringValue(opacity);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetOpacity(XmlString opacity) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(opacity);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetOpacity() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STStrokeLineStyle$Enum getLinestyle() {
        STStrokeLineStyle$Enum sTStrokeLineStyle$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            sTStrokeLineStyle$Enum = target == null ? null : (STStrokeLineStyle$Enum) target.getEnumValue();
        }
        return sTStrokeLineStyle$Enum;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STStrokeLineStyle xgetLinestyle() {
        STStrokeLineStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetLinestyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setLinestyle(STStrokeLineStyle$Enum linestyle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setEnumValue(linestyle);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetLinestyle(STStrokeLineStyle linestyle) {
        synchronized (monitor()) {
            check_orphaned();
            STStrokeLineStyle target = get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (STStrokeLineStyle) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(linestyle);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetLinestyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public BigDecimal getMiterlimit() {
        BigDecimal bigDecimalValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            bigDecimalValue = target == null ? null : target.getBigDecimalValue();
        }
        return bigDecimalValue;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public XmlDecimal xgetMiterlimit() {
        XmlDecimal target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDecimal) get_store().find_attribute_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetMiterlimit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setMiterlimit(BigDecimal miterlimit) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setBigDecimalValue(miterlimit);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetMiterlimit(XmlDecimal miterlimit) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDecimal target = (XmlDecimal) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (XmlDecimal) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(miterlimit);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetMiterlimit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STStrokeJoinStyle.Enum getJoinstyle() {
        STStrokeJoinStyle.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            r1 = target == null ? null : (STStrokeJoinStyle.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STStrokeJoinStyle xgetJoinstyle() {
        STStrokeJoinStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STStrokeJoinStyle) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetJoinstyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setJoinstyle(STStrokeJoinStyle.Enum joinstyle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setEnumValue(joinstyle);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetJoinstyle(STStrokeJoinStyle joinstyle) {
        synchronized (monitor()) {
            check_orphaned();
            STStrokeJoinStyle target = (STStrokeJoinStyle) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (STStrokeJoinStyle) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(joinstyle);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetJoinstyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STStrokeEndCap$Enum getEndcap() {
        STStrokeEndCap$Enum sTStrokeEndCap$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            sTStrokeEndCap$Enum = target == null ? null : (STStrokeEndCap$Enum) target.getEnumValue();
        }
        return sTStrokeEndCap$Enum;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STStrokeEndCap xgetEndcap() {
        STStrokeEndCap target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetEndcap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setEndcap(STStrokeEndCap$Enum endcap) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setEnumValue(endcap);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetEndcap(STStrokeEndCap endcap) {
        synchronized (monitor()) {
            check_orphaned();
            STStrokeEndCap target = get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (STStrokeEndCap) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(endcap);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetEndcap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public String getDashstyle() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public XmlString xgetDashstyle() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetDashstyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setDashstyle(String dashstyle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.setStringValue(dashstyle);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetDashstyle(XmlString dashstyle) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.set(dashstyle);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetDashstyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[14]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STFillType.Enum getFilltype() {
        STFillType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            r1 = target == null ? null : (STFillType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STFillType xgetFilltype() {
        STFillType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STFillType) get_store().find_attribute_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetFilltype() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[15]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setFilltype(STFillType.Enum filltype) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.setEnumValue(filltype);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetFilltype(STFillType filltype) {
        synchronized (monitor()) {
            check_orphaned();
            STFillType target = (STFillType) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (STFillType) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.set(filltype);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetFilltype() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[15]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public String getSrc() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public XmlString xgetSrc() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetSrc() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[16]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setSrc(String src) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.setStringValue(src);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetSrc(XmlString src) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.set(src);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetSrc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[16]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STImageAspect$Enum getImageaspect() {
        STImageAspect$Enum sTImageAspect$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            sTImageAspect$Enum = target == null ? null : (STImageAspect$Enum) target.getEnumValue();
        }
        return sTImageAspect$Enum;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STImageAspect xgetImageaspect() {
        STImageAspect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetImageaspect() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[17]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setImageaspect(STImageAspect$Enum imageaspect) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.setEnumValue(imageaspect);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetImageaspect(STImageAspect imageaspect) {
        synchronized (monitor()) {
            check_orphaned();
            STImageAspect target = get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (STImageAspect) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.set(imageaspect);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetImageaspect() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[17]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public String getImagesize() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public XmlString xgetImagesize() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetImagesize() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[18]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setImagesize(String imagesize) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.setStringValue(imagesize);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetImagesize(XmlString imagesize) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.set(imagesize);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetImagesize() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[18]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STTrueFalse.Enum getImagealignshape() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STTrueFalse xgetImagealignshape() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetImagealignshape() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[19]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setImagealignshape(STTrueFalse.Enum imagealignshape) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.setEnumValue(imagealignshape);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetImagealignshape(STTrueFalse imagealignshape) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.set(imagealignshape);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetImagealignshape() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[19]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public String getColor2() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STColorType xgetColor2() {
        STColorType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetColor2() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[20]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setColor2(String color2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.setStringValue(color2);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetColor2(STColorType color2) {
        synchronized (monitor()) {
            check_orphaned();
            STColorType target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (STColorType) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.set(color2);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetColor2() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[20]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STStrokeArrowType.Enum getStartarrow() {
        STStrokeArrowType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            r1 = target == null ? null : (STStrokeArrowType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STStrokeArrowType xgetStartarrow() {
        STStrokeArrowType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STStrokeArrowType) get_store().find_attribute_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetStartarrow() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[21]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setStartarrow(STStrokeArrowType.Enum startarrow) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.setEnumValue(startarrow);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetStartarrow(STStrokeArrowType startarrow) {
        synchronized (monitor()) {
            check_orphaned();
            STStrokeArrowType target = (STStrokeArrowType) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (STStrokeArrowType) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.set(startarrow);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetStartarrow() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[21]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STStrokeArrowWidth$Enum getStartarrowwidth() {
        STStrokeArrowWidth$Enum sTStrokeArrowWidth$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            sTStrokeArrowWidth$Enum = target == null ? null : (STStrokeArrowWidth$Enum) target.getEnumValue();
        }
        return sTStrokeArrowWidth$Enum;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STStrokeArrowWidth xgetStartarrowwidth() {
        STStrokeArrowWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetStartarrowwidth() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[22]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setStartarrowwidth(STStrokeArrowWidth$Enum startarrowwidth) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.setEnumValue(startarrowwidth);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetStartarrowwidth(STStrokeArrowWidth startarrowwidth) {
        synchronized (monitor()) {
            check_orphaned();
            STStrokeArrowWidth target = get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (STStrokeArrowWidth) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.set(startarrowwidth);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetStartarrowwidth() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[22]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STStrokeArrowLength$Enum getStartarrowlength() {
        STStrokeArrowLength$Enum sTStrokeArrowLength$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            sTStrokeArrowLength$Enum = target == null ? null : (STStrokeArrowLength$Enum) target.getEnumValue();
        }
        return sTStrokeArrowLength$Enum;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STStrokeArrowLength xgetStartarrowlength() {
        STStrokeArrowLength target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetStartarrowlength() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[23]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setStartarrowlength(STStrokeArrowLength$Enum startarrowlength) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.setEnumValue(startarrowlength);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetStartarrowlength(STStrokeArrowLength startarrowlength) {
        synchronized (monitor()) {
            check_orphaned();
            STStrokeArrowLength target = get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (STStrokeArrowLength) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.set(startarrowlength);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetStartarrowlength() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[23]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STStrokeArrowType.Enum getEndarrow() {
        STStrokeArrowType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            r1 = target == null ? null : (STStrokeArrowType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STStrokeArrowType xgetEndarrow() {
        STStrokeArrowType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STStrokeArrowType) get_store().find_attribute_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetEndarrow() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[24]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setEndarrow(STStrokeArrowType.Enum endarrow) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.setEnumValue(endarrow);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetEndarrow(STStrokeArrowType endarrow) {
        synchronized (monitor()) {
            check_orphaned();
            STStrokeArrowType target = (STStrokeArrowType) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (STStrokeArrowType) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.set(endarrow);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetEndarrow() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[24]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STStrokeArrowWidth$Enum getEndarrowwidth() {
        STStrokeArrowWidth$Enum sTStrokeArrowWidth$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            sTStrokeArrowWidth$Enum = target == null ? null : (STStrokeArrowWidth$Enum) target.getEnumValue();
        }
        return sTStrokeArrowWidth$Enum;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STStrokeArrowWidth xgetEndarrowwidth() {
        STStrokeArrowWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetEndarrowwidth() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[25]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setEndarrowwidth(STStrokeArrowWidth$Enum endarrowwidth) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.setEnumValue(endarrowwidth);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetEndarrowwidth(STStrokeArrowWidth endarrowwidth) {
        synchronized (monitor()) {
            check_orphaned();
            STStrokeArrowWidth target = get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (STStrokeArrowWidth) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.set(endarrowwidth);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetEndarrowwidth() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[25]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STStrokeArrowLength$Enum getEndarrowlength() {
        STStrokeArrowLength$Enum sTStrokeArrowLength$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            sTStrokeArrowLength$Enum = target == null ? null : (STStrokeArrowLength$Enum) target.getEnumValue();
        }
        return sTStrokeArrowLength$Enum;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STStrokeArrowLength xgetEndarrowlength() {
        STStrokeArrowLength target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetEndarrowlength() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[26]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setEndarrowlength(STStrokeArrowLength$Enum endarrowlength) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.setEnumValue(endarrowlength);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetEndarrowlength(STStrokeArrowLength endarrowlength) {
        synchronized (monitor()) {
            check_orphaned();
            STStrokeArrowLength target = get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (STStrokeArrowLength) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.set(endarrowlength);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetEndarrowlength() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[26]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public String getHref() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public XmlString xgetHref() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetHref() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[27]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setHref(String href) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[27]);
            }
            target.setStringValue(href);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetHref(XmlString href) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[27]);
            }
            target.set(href);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetHref() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[27]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public String getAlthref() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public XmlString xgetAlthref() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetAlthref() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[28]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setAlthref(String althref) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.setStringValue(althref);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetAlthref(XmlString althref) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.set(althref);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetAlthref() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[28]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public String getTitle() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public XmlString xgetTitle() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetTitle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[29]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setTitle(String title) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[29]);
            }
            target.setStringValue(title);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetTitle(XmlString title) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[29]);
            }
            target.set(title);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetTitle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[29]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STTrueFalse.Enum getForcedash() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STTrueFalse xgetForcedash() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[30]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetForcedash() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[30]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setForcedash(STTrueFalse.Enum forcedash) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[30]);
            }
            target.setEnumValue(forcedash);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetForcedash(STTrueFalse forcedash) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[30]);
            }
            target.set(forcedash);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetForcedash() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[30]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public String getId2() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STRelationshipId xgetId2() {
        STRelationshipId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[31]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetId2() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[31]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setId2(String id2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[31]);
            }
            target.setStringValue(id2);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetId2(STRelationshipId id2) {
        synchronized (monitor()) {
            check_orphaned();
            STRelationshipId target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            if (target == null) {
                target = (STRelationshipId) get_store().add_attribute_user(PROPERTY_QNAME[31]);
            }
            target.set(id2);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetId2() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[31]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STTrueFalse.Enum getInsetpen() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[32]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STTrueFalse xgetInsetpen() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[32]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetInsetpen() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[32]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setInsetpen(STTrueFalse.Enum insetpen) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[32]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[32]);
            }
            target.setEnumValue(insetpen);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetInsetpen(STTrueFalse insetpen) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[32]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[32]);
            }
            target.set(insetpen);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetInsetpen() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[32]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public String getRelid() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public STRelationshipId xgetRelid() {
        STRelationshipId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[33]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public boolean isSetRelid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[33]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void setRelid(String relid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[33]);
            }
            target.setStringValue(relid);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void xsetRelid(STRelationshipId relid) {
        synchronized (monitor()) {
            check_orphaned();
            STRelationshipId target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            if (target == null) {
                target = (STRelationshipId) get_store().add_attribute_user(PROPERTY_QNAME[33]);
            }
            target.set(relid);
        }
    }

    @Override // com.microsoft.schemas.vml.CTStroke
    public void unsetRelid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[33]);
        }
    }
}
