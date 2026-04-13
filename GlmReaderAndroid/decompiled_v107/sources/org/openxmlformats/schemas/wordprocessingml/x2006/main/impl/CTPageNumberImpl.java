package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STChapterSep;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STChapterSep$Enum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;

/* loaded from: classes12.dex */
public class CTPageNumberImpl extends XmlComplexContentImpl implements CTPageNumber {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "fmt"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "start"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "chapStyle"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "chapSep")};
    private static final long serialVersionUID = 1;

    public CTPageNumberImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public STNumberFormat.Enum getFmt() {
        STNumberFormat.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
            r1 = target == null ? null : (STNumberFormat.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public STNumberFormat xgetFmt() {
        STNumberFormat target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STNumberFormat) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STNumberFormat) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public boolean isSetFmt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public void setFmt(STNumberFormat.Enum fmt) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(fmt);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public void xsetFmt(STNumberFormat fmt) {
        synchronized (monitor()) {
            check_orphaned();
            STNumberFormat target = (STNumberFormat) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STNumberFormat) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(fmt);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public void unsetFmt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public BigInteger getStart() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public STDecimalNumber xgetStart() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public boolean isSetStart() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public void setStart(BigInteger start) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setBigIntegerValue(start);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public void xsetStart(STDecimalNumber start) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(start);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public void unsetStart() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public BigInteger getChapStyle() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public STDecimalNumber xgetChapStyle() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public boolean isSetChapStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public void setChapStyle(BigInteger chapStyle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setBigIntegerValue(chapStyle);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public void xsetChapStyle(STDecimalNumber chapStyle) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(chapStyle);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public void unsetChapStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public STChapterSep$Enum getChapSep() {
        STChapterSep$Enum sTChapterSep$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
            sTChapterSep$Enum = target == null ? null : (STChapterSep$Enum) target.getEnumValue();
        }
        return sTChapterSep$Enum;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public STChapterSep xgetChapSep() {
        STChapterSep target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STChapterSep) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public boolean isSetChapSep() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public void setChapSep(STChapterSep$Enum chapSep) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setEnumValue(chapSep);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public void xsetChapSep(STChapterSep chapSep) {
        synchronized (monitor()) {
            check_orphaned();
            STChapterSep target = get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STChapterSep) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(chapSep);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber
    public void unsetChapSep() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }
}
