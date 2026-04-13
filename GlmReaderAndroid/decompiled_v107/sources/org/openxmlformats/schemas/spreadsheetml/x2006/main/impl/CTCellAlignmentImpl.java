package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.poi.ss.util.CellUtil;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STHorizontalAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STTextRotation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STVerticalAlignment;

/* loaded from: classes12.dex */
public class CTCellAlignmentImpl extends XmlComplexContentImpl implements CTCellAlignment {
    private static final QName[] PROPERTY_QNAME = {new QName("", "horizontal"), new QName("", "vertical"), new QName("", "textRotation"), new QName("", CellUtil.WRAP_TEXT), new QName("", "indent"), new QName("", "relativeIndent"), new QName("", "justifyLastLine"), new QName("", CellUtil.SHRINK_TO_FIT), new QName("", "readingOrder")};
    private static final long serialVersionUID = 1;

    public CTCellAlignmentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public STHorizontalAlignment.Enum getHorizontal() {
        STHorizontalAlignment.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STHorizontalAlignment.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public STHorizontalAlignment xgetHorizontal() {
        STHorizontalAlignment target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STHorizontalAlignment) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public boolean isSetHorizontal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void setHorizontal(STHorizontalAlignment.Enum horizontal) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(horizontal);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void xsetHorizontal(STHorizontalAlignment horizontal) {
        synchronized (monitor()) {
            check_orphaned();
            STHorizontalAlignment target = (STHorizontalAlignment) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STHorizontalAlignment) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(horizontal);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void unsetHorizontal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public STVerticalAlignment.Enum getVertical() {
        STVerticalAlignment.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
            r1 = target == null ? null : (STVerticalAlignment.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public STVerticalAlignment xgetVertical() {
        STVerticalAlignment target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STVerticalAlignment) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STVerticalAlignment) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public boolean isSetVertical() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().find_attribute_user(PROPERTY_QNAME[1]) == null) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void setVertical(STVerticalAlignment.Enum vertical) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(vertical);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void xsetVertical(STVerticalAlignment vertical) {
        synchronized (monitor()) {
            check_orphaned();
            STVerticalAlignment target = (STVerticalAlignment) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STVerticalAlignment) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(vertical);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void unsetVertical() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public BigInteger getTextRotation() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public STTextRotation xgetTextRotation() {
        STTextRotation target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextRotation) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public boolean isSetTextRotation() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void setTextRotation(BigInteger textRotation) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setBigIntegerValue(textRotation);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void xsetTextRotation(STTextRotation textRotation) {
        synchronized (monitor()) {
            check_orphaned();
            STTextRotation target = (STTextRotation) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STTextRotation) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(textRotation);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void unsetTextRotation() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public boolean getWrapText() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public XmlBoolean xgetWrapText() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public boolean isSetWrapText() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void setWrapText(boolean wrapText) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setBooleanValue(wrapText);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void xsetWrapText(XmlBoolean wrapText) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(wrapText);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void unsetWrapText() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public long getIndent() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public XmlUnsignedInt xgetIndent() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public boolean isSetIndent() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void setIndent(long indent) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setLongValue(indent);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void xsetIndent(XmlUnsignedInt indent) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(indent);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void unsetIndent() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public int getRelativeIndent() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public XmlInt xgetRelativeIndent() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public boolean isSetRelativeIndent() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void setRelativeIndent(int relativeIndent) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setIntValue(relativeIndent);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void xsetRelativeIndent(XmlInt relativeIndent) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlInt) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(relativeIndent);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void unsetRelativeIndent() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public boolean getJustifyLastLine() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public XmlBoolean xgetJustifyLastLine() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public boolean isSetJustifyLastLine() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void setJustifyLastLine(boolean justifyLastLine) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setBooleanValue(justifyLastLine);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void xsetJustifyLastLine(XmlBoolean justifyLastLine) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(justifyLastLine);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void unsetJustifyLastLine() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public boolean getShrinkToFit() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public XmlBoolean xgetShrinkToFit() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public boolean isSetShrinkToFit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void setShrinkToFit(boolean shrinkToFit) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setBooleanValue(shrinkToFit);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void xsetShrinkToFit(XmlBoolean shrinkToFit) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(shrinkToFit);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void unsetShrinkToFit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public long getReadingOrder() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public XmlUnsignedInt xgetReadingOrder() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public boolean isSetReadingOrder() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void setReadingOrder(long readingOrder) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setLongValue(readingOrder);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void xsetReadingOrder(XmlUnsignedInt readingOrder) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(readingOrder);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment
    public void unsetReadingOrder() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }
}
