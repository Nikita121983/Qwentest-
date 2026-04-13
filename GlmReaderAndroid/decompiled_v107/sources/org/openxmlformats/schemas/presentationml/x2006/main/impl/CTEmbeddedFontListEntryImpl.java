package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry;

/* loaded from: classes11.dex */
public class CTEmbeddedFontListEntryImpl extends XmlComplexContentImpl implements CTEmbeddedFontListEntry {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, CellUtil.FONT), new QName(XSSFRelation.NS_PRESENTATIONML, "regular"), new QName(XSSFRelation.NS_PRESENTATIONML, "bold"), new QName(XSSFRelation.NS_PRESENTATIONML, "italic"), new QName(XSSFRelation.NS_PRESENTATIONML, "boldItalic")};
    private static final long serialVersionUID = 1;

    public CTEmbeddedFontListEntryImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public CTTextFont getFont() {
        CTTextFont cTTextFont;
        synchronized (monitor()) {
            check_orphaned();
            CTTextFont target = (CTTextFont) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTextFont = target == null ? null : target;
        }
        return cTTextFont;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public void setFont(CTTextFont font) {
        generatedSetterHelperImpl(font, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public CTTextFont addNewFont() {
        CTTextFont target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextFont) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public CTEmbeddedFontDataId getRegular() {
        CTEmbeddedFontDataId cTEmbeddedFontDataId;
        synchronized (monitor()) {
            check_orphaned();
            CTEmbeddedFontDataId target = (CTEmbeddedFontDataId) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTEmbeddedFontDataId = target == null ? null : target;
        }
        return cTEmbeddedFontDataId;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public boolean isSetRegular() {
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public void setRegular(CTEmbeddedFontDataId regular) {
        generatedSetterHelperImpl(regular, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public CTEmbeddedFontDataId addNewRegular() {
        CTEmbeddedFontDataId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmbeddedFontDataId) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public void unsetRegular() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public CTEmbeddedFontDataId getBold() {
        CTEmbeddedFontDataId cTEmbeddedFontDataId;
        synchronized (monitor()) {
            check_orphaned();
            CTEmbeddedFontDataId target = (CTEmbeddedFontDataId) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTEmbeddedFontDataId = target == null ? null : target;
        }
        return cTEmbeddedFontDataId;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public boolean isSetBold() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public void setBold(CTEmbeddedFontDataId bold) {
        generatedSetterHelperImpl(bold, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public CTEmbeddedFontDataId addNewBold() {
        CTEmbeddedFontDataId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmbeddedFontDataId) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public void unsetBold() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public CTEmbeddedFontDataId getItalic() {
        CTEmbeddedFontDataId cTEmbeddedFontDataId;
        synchronized (monitor()) {
            check_orphaned();
            CTEmbeddedFontDataId target = (CTEmbeddedFontDataId) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTEmbeddedFontDataId = target == null ? null : target;
        }
        return cTEmbeddedFontDataId;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public boolean isSetItalic() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public void setItalic(CTEmbeddedFontDataId italic) {
        generatedSetterHelperImpl(italic, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public CTEmbeddedFontDataId addNewItalic() {
        CTEmbeddedFontDataId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmbeddedFontDataId) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public void unsetItalic() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public CTEmbeddedFontDataId getBoldItalic() {
        CTEmbeddedFontDataId cTEmbeddedFontDataId;
        synchronized (monitor()) {
            check_orphaned();
            CTEmbeddedFontDataId target = (CTEmbeddedFontDataId) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTEmbeddedFontDataId = target == null ? null : target;
        }
        return cTEmbeddedFontDataId;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public boolean isSetBoldItalic() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public void setBoldItalic(CTEmbeddedFontDataId boldItalic) {
        generatedSetterHelperImpl(boldItalic, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public CTEmbeddedFontDataId addNewBoldItalic() {
        CTEmbeddedFontDataId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmbeddedFontDataId) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
    public void unsetBoldItalic() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }
}
