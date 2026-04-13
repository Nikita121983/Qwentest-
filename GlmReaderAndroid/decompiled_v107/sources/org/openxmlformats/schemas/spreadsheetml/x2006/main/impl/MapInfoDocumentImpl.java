package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMapInfo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.MapInfoDocument;

/* loaded from: classes12.dex */
public class MapInfoDocumentImpl extends XmlComplexContentImpl implements MapInfoDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "MapInfo")};
    private static final long serialVersionUID = 1;

    public MapInfoDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.MapInfoDocument
    public CTMapInfo getMapInfo() {
        CTMapInfo cTMapInfo;
        synchronized (monitor()) {
            check_orphaned();
            CTMapInfo target = (CTMapInfo) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTMapInfo = target == null ? null : target;
        }
        return cTMapInfo;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.MapInfoDocument
    public void setMapInfo(CTMapInfo mapInfo) {
        generatedSetterHelperImpl(mapInfo, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.MapInfoDocument
    public CTMapInfo addNewMapInfo() {
        CTMapInfo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMapInfo) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
