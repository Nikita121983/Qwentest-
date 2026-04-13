package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIndexedColors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMRUColors;

/* loaded from: classes12.dex */
public class CTColorsImpl extends XmlComplexContentImpl implements CTColors {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "indexedColors"), new QName(XSSFRelation.NS_SPREADSHEETML, "mruColors")};
    private static final long serialVersionUID = 1;

    public CTColorsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors
    public CTIndexedColors getIndexedColors() {
        CTIndexedColors cTIndexedColors;
        synchronized (monitor()) {
            check_orphaned();
            CTIndexedColors target = (CTIndexedColors) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTIndexedColors = target == null ? null : target;
        }
        return cTIndexedColors;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors
    public boolean isSetIndexedColors() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors
    public void setIndexedColors(CTIndexedColors indexedColors) {
        generatedSetterHelperImpl(indexedColors, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors
    public CTIndexedColors addNewIndexedColors() {
        CTIndexedColors target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTIndexedColors) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors
    public void unsetIndexedColors() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors
    public CTMRUColors getMruColors() {
        CTMRUColors cTMRUColors;
        synchronized (monitor()) {
            check_orphaned();
            CTMRUColors target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTMRUColors = target == null ? null : target;
        }
        return cTMRUColors;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors
    public boolean isSetMruColors() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors
    public void setMruColors(CTMRUColors mruColors) {
        generatedSetterHelperImpl(mruColors, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors
    public CTMRUColors addNewMruColors() {
        CTMRUColors target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors
    public void unsetMruColors() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
