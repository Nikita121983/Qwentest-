package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorders;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellStyleXfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellStyles;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellXfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFills;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFonts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyles;

/* loaded from: classes12.dex */
public class CTStylesheetImpl extends XmlComplexContentImpl implements CTStylesheet {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "numFmts"), new QName(XSSFRelation.NS_SPREADSHEETML, "fonts"), new QName(XSSFRelation.NS_SPREADSHEETML, "fills"), new QName(XSSFRelation.NS_SPREADSHEETML, "borders"), new QName(XSSFRelation.NS_SPREADSHEETML, "cellStyleXfs"), new QName(XSSFRelation.NS_SPREADSHEETML, "cellXfs"), new QName(XSSFRelation.NS_SPREADSHEETML, "cellStyles"), new QName(XSSFRelation.NS_SPREADSHEETML, "dxfs"), new QName(XSSFRelation.NS_SPREADSHEETML, "tableStyles"), new QName(XSSFRelation.NS_SPREADSHEETML, "colors"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTStylesheetImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTNumFmts getNumFmts() {
        CTNumFmts cTNumFmts;
        synchronized (monitor()) {
            check_orphaned();
            CTNumFmts target = (CTNumFmts) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTNumFmts = target == null ? null : target;
        }
        return cTNumFmts;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public boolean isSetNumFmts() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void setNumFmts(CTNumFmts numFmts) {
        generatedSetterHelperImpl(numFmts, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTNumFmts addNewNumFmts() {
        CTNumFmts target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNumFmts) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void unsetNumFmts() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTFonts getFonts() {
        CTFonts cTFonts;
        synchronized (monitor()) {
            check_orphaned();
            CTFonts target = (CTFonts) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTFonts = target == null ? null : target;
        }
        return cTFonts;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public boolean isSetFonts() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void setFonts(CTFonts fonts) {
        generatedSetterHelperImpl(fonts, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTFonts addNewFonts() {
        CTFonts target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFonts) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void unsetFonts() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTFills getFills() {
        CTFills cTFills;
        synchronized (monitor()) {
            check_orphaned();
            CTFills target = (CTFills) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTFills = target == null ? null : target;
        }
        return cTFills;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public boolean isSetFills() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void setFills(CTFills fills) {
        generatedSetterHelperImpl(fills, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTFills addNewFills() {
        CTFills target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFills) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void unsetFills() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTBorders getBorders() {
        CTBorders cTBorders;
        synchronized (monitor()) {
            check_orphaned();
            CTBorders target = (CTBorders) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTBorders = target == null ? null : target;
        }
        return cTBorders;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public boolean isSetBorders() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void setBorders(CTBorders borders) {
        generatedSetterHelperImpl(borders, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTBorders addNewBorders() {
        CTBorders target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorders) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void unsetBorders() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTCellStyleXfs getCellStyleXfs() {
        CTCellStyleXfs cTCellStyleXfs;
        synchronized (monitor()) {
            check_orphaned();
            CTCellStyleXfs target = (CTCellStyleXfs) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTCellStyleXfs = target == null ? null : target;
        }
        return cTCellStyleXfs;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public boolean isSetCellStyleXfs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void setCellStyleXfs(CTCellStyleXfs cellStyleXfs) {
        generatedSetterHelperImpl(cellStyleXfs, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTCellStyleXfs addNewCellStyleXfs() {
        CTCellStyleXfs target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCellStyleXfs) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void unsetCellStyleXfs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTCellXfs getCellXfs() {
        CTCellXfs cTCellXfs;
        synchronized (monitor()) {
            check_orphaned();
            CTCellXfs target = (CTCellXfs) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTCellXfs = target == null ? null : target;
        }
        return cTCellXfs;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public boolean isSetCellXfs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void setCellXfs(CTCellXfs cellXfs) {
        generatedSetterHelperImpl(cellXfs, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTCellXfs addNewCellXfs() {
        CTCellXfs target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCellXfs) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void unsetCellXfs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTCellStyles getCellStyles() {
        CTCellStyles cTCellStyles;
        synchronized (monitor()) {
            check_orphaned();
            CTCellStyles target = get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTCellStyles = target == null ? null : target;
        }
        return cTCellStyles;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public boolean isSetCellStyles() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void setCellStyles(CTCellStyles cellStyles) {
        generatedSetterHelperImpl(cellStyles, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTCellStyles addNewCellStyles() {
        CTCellStyles target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void unsetCellStyles() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTDxfs getDxfs() {
        CTDxfs cTDxfs;
        synchronized (monitor()) {
            check_orphaned();
            CTDxfs target = (CTDxfs) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTDxfs = target == null ? null : target;
        }
        return cTDxfs;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public boolean isSetDxfs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void setDxfs(CTDxfs dxfs) {
        generatedSetterHelperImpl(dxfs, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTDxfs addNewDxfs() {
        CTDxfs target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDxfs) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void unsetDxfs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTTableStyles getTableStyles() {
        CTTableStyles cTTableStyles;
        synchronized (monitor()) {
            check_orphaned();
            CTTableStyles target = (CTTableStyles) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTTableStyles = target == null ? null : target;
        }
        return cTTableStyles;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public boolean isSetTableStyles() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void setTableStyles(CTTableStyles tableStyles) {
        generatedSetterHelperImpl(tableStyles, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTTableStyles addNewTableStyles() {
        CTTableStyles target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTableStyles) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void unsetTableStyles() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTColors getColors() {
        CTColors cTColors;
        synchronized (monitor()) {
            check_orphaned();
            CTColors target = (CTColors) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTColors = target == null ? null : target;
        }
        return cTColors;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public boolean isSetColors() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void setColors(CTColors colors) {
        generatedSetterHelperImpl(colors, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTColors addNewColors() {
        CTColors target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColors) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void unsetColors() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }
}
