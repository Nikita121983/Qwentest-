package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix;

/* loaded from: classes11.dex */
public class CTBaseStylesImpl extends XmlComplexContentImpl implements CTBaseStyles {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "clrScheme"), new QName(XSSFRelation.NS_DRAWINGML, "fontScheme"), new QName(XSSFRelation.NS_DRAWINGML, "fmtScheme"), new QName(XSSFRelation.NS_DRAWINGML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTBaseStylesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles
    public CTColorScheme getClrScheme() {
        CTColorScheme cTColorScheme;
        synchronized (monitor()) {
            check_orphaned();
            CTColorScheme target = (CTColorScheme) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTColorScheme = target == null ? null : target;
        }
        return cTColorScheme;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles
    public void setClrScheme(CTColorScheme clrScheme) {
        generatedSetterHelperImpl(clrScheme, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles
    public CTColorScheme addNewClrScheme() {
        CTColorScheme target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColorScheme) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles
    public CTFontScheme getFontScheme() {
        CTFontScheme cTFontScheme;
        synchronized (monitor()) {
            check_orphaned();
            CTFontScheme target = (CTFontScheme) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTFontScheme = target == null ? null : target;
        }
        return cTFontScheme;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles
    public void setFontScheme(CTFontScheme fontScheme) {
        generatedSetterHelperImpl(fontScheme, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles
    public CTFontScheme addNewFontScheme() {
        CTFontScheme target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFontScheme) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles
    public CTStyleMatrix getFmtScheme() {
        CTStyleMatrix cTStyleMatrix;
        synchronized (monitor()) {
            check_orphaned();
            CTStyleMatrix target = (CTStyleMatrix) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTStyleMatrix = target == null ? null : target;
        }
        return cTStyleMatrix;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles
    public void setFmtScheme(CTStyleMatrix fmtScheme) {
        generatedSetterHelperImpl(fmtScheme, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles
    public CTStyleMatrix addNewFmtScheme() {
        CTStyleMatrix target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStyleMatrix) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
