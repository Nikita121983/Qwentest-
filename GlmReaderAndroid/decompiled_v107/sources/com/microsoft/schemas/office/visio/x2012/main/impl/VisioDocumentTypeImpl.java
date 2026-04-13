package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.ColorsType;
import com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType;
import com.microsoft.schemas.office.visio.x2012.main.DocumentSheetType;
import com.microsoft.schemas.office.visio.x2012.main.EventListType;
import com.microsoft.schemas.office.visio.x2012.main.FaceNamesType;
import com.microsoft.schemas.office.visio.x2012.main.HeaderFooterType;
import com.microsoft.schemas.office.visio.x2012.main.PublishSettingsType;
import com.microsoft.schemas.office.visio.x2012.main.StyleSheetsType;
import com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class VisioDocumentTypeImpl extends XmlComplexContentImpl implements VisioDocumentType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "DocumentSettings"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "Colors"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "FaceNames"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "StyleSheets"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "DocumentSheet"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "EventList"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "HeaderFooter"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "PublishSettings")};
    private static final long serialVersionUID = 1;

    public VisioDocumentTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public DocumentSettingsType getDocumentSettings() {
        DocumentSettingsType documentSettingsType;
        synchronized (monitor()) {
            check_orphaned();
            DocumentSettingsType target = (DocumentSettingsType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            documentSettingsType = target == null ? null : target;
        }
        return documentSettingsType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public boolean isSetDocumentSettings() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public void setDocumentSettings(DocumentSettingsType documentSettings) {
        generatedSetterHelperImpl(documentSettings, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public DocumentSettingsType addNewDocumentSettings() {
        DocumentSettingsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (DocumentSettingsType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public void unsetDocumentSettings() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public ColorsType getColors() {
        ColorsType colorsType;
        synchronized (monitor()) {
            check_orphaned();
            ColorsType target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            colorsType = target == null ? null : target;
        }
        return colorsType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public boolean isSetColors() {
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

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public void setColors(ColorsType colors) {
        generatedSetterHelperImpl(colors, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public ColorsType addNewColors() {
        ColorsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public void unsetColors() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public FaceNamesType getFaceNames() {
        FaceNamesType faceNamesType;
        synchronized (monitor()) {
            check_orphaned();
            FaceNamesType target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            faceNamesType = target == null ? null : target;
        }
        return faceNamesType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public boolean isSetFaceNames() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public void setFaceNames(FaceNamesType faceNames) {
        generatedSetterHelperImpl(faceNames, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public FaceNamesType addNewFaceNames() {
        FaceNamesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public void unsetFaceNames() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public StyleSheetsType getStyleSheets() {
        StyleSheetsType styleSheetsType;
        synchronized (monitor()) {
            check_orphaned();
            StyleSheetsType target = (StyleSheetsType) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            styleSheetsType = target == null ? null : target;
        }
        return styleSheetsType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public boolean isSetStyleSheets() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public void setStyleSheets(StyleSheetsType styleSheets) {
        generatedSetterHelperImpl(styleSheets, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public StyleSheetsType addNewStyleSheets() {
        StyleSheetsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (StyleSheetsType) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public void unsetStyleSheets() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public DocumentSheetType getDocumentSheet() {
        DocumentSheetType documentSheetType;
        synchronized (monitor()) {
            check_orphaned();
            DocumentSheetType target = get_store().find_element_user(PROPERTY_QNAME[4], 0);
            documentSheetType = target == null ? null : target;
        }
        return documentSheetType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public boolean isSetDocumentSheet() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public void setDocumentSheet(DocumentSheetType documentSheet) {
        generatedSetterHelperImpl(documentSheet, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public DocumentSheetType addNewDocumentSheet() {
        DocumentSheetType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public void unsetDocumentSheet() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public EventListType getEventList() {
        EventListType eventListType;
        synchronized (monitor()) {
            check_orphaned();
            EventListType target = get_store().find_element_user(PROPERTY_QNAME[5], 0);
            eventListType = target == null ? null : target;
        }
        return eventListType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public boolean isSetEventList() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public void setEventList(EventListType eventList) {
        generatedSetterHelperImpl(eventList, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public EventListType addNewEventList() {
        EventListType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public void unsetEventList() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public HeaderFooterType getHeaderFooter() {
        HeaderFooterType headerFooterType;
        synchronized (monitor()) {
            check_orphaned();
            HeaderFooterType target = get_store().find_element_user(PROPERTY_QNAME[6], 0);
            headerFooterType = target == null ? null : target;
        }
        return headerFooterType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public boolean isSetHeaderFooter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public void setHeaderFooter(HeaderFooterType headerFooter) {
        generatedSetterHelperImpl(headerFooter, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public HeaderFooterType addNewHeaderFooter() {
        HeaderFooterType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public void unsetHeaderFooter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public PublishSettingsType getPublishSettings() {
        PublishSettingsType publishSettingsType;
        synchronized (monitor()) {
            check_orphaned();
            PublishSettingsType target = get_store().find_element_user(PROPERTY_QNAME[7], 0);
            publishSettingsType = target == null ? null : target;
        }
        return publishSettingsType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public boolean isSetPublishSettings() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public void setPublishSettings(PublishSettingsType publishSettings) {
        generatedSetterHelperImpl(publishSettings, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public PublishSettingsType addNewPublishSettings() {
        PublishSettingsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType
    public void unsetPublishSettings() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }
}
