package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass$Enum;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCustomShowList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCustomerDataList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTHandoutMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTKinsoku;
import org.openxmlformats.schemas.presentationml.x2006.main.CTModifyVerifier;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSmartTags;
import org.openxmlformats.schemas.presentationml.x2006.main.STBookmarkIdSeed;

/* loaded from: classes11.dex */
public class CTPresentationImpl extends XmlComplexContentImpl implements CTPresentation {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "sldMasterIdLst"), new QName(XSSFRelation.NS_PRESENTATIONML, "notesMasterIdLst"), new QName(XSSFRelation.NS_PRESENTATIONML, "handoutMasterIdLst"), new QName(XSSFRelation.NS_PRESENTATIONML, "sldIdLst"), new QName(XSSFRelation.NS_PRESENTATIONML, "sldSz"), new QName(XSSFRelation.NS_PRESENTATIONML, "notesSz"), new QName(XSSFRelation.NS_PRESENTATIONML, "smartTags"), new QName(XSSFRelation.NS_PRESENTATIONML, "embeddedFontLst"), new QName(XSSFRelation.NS_PRESENTATIONML, "custShowLst"), new QName(XSSFRelation.NS_PRESENTATIONML, "photoAlbum"), new QName(XSSFRelation.NS_PRESENTATIONML, "custDataLst"), new QName(XSSFRelation.NS_PRESENTATIONML, "kinsoku"), new QName(XSSFRelation.NS_PRESENTATIONML, "defaultTextStyle"), new QName(XSSFRelation.NS_PRESENTATIONML, "modifyVerifier"), new QName(XSSFRelation.NS_PRESENTATIONML, "extLst"), new QName("", "serverZoom"), new QName("", "firstSlideNum"), new QName("", "showSpecialPlsOnTitleSld"), new QName("", "rtl"), new QName("", "removePersonalInfoOnSave"), new QName("", "compatMode"), new QName("", "strictFirstAndLastChars"), new QName("", "embedTrueTypeFonts"), new QName("", "saveSubsetFonts"), new QName("", "autoCompressPictures"), new QName("", "bookmarkIdSeed"), new QName("", "conformance")};
    private static final long serialVersionUID = 1;

    public CTPresentationImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTSlideMasterIdList getSldMasterIdLst() {
        CTSlideMasterIdList cTSlideMasterIdList;
        synchronized (monitor()) {
            check_orphaned();
            CTSlideMasterIdList target = (CTSlideMasterIdList) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTSlideMasterIdList = target == null ? null : target;
        }
        return cTSlideMasterIdList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetSldMasterIdLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setSldMasterIdLst(CTSlideMasterIdList sldMasterIdLst) {
        generatedSetterHelperImpl(sldMasterIdLst, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTSlideMasterIdList addNewSldMasterIdLst() {
        CTSlideMasterIdList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSlideMasterIdList) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetSldMasterIdLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTNotesMasterIdList getNotesMasterIdLst() {
        CTNotesMasterIdList cTNotesMasterIdList;
        synchronized (monitor()) {
            check_orphaned();
            CTNotesMasterIdList target = (CTNotesMasterIdList) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTNotesMasterIdList = target == null ? null : target;
        }
        return cTNotesMasterIdList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetNotesMasterIdLst() {
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setNotesMasterIdLst(CTNotesMasterIdList notesMasterIdLst) {
        generatedSetterHelperImpl(notesMasterIdLst, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTNotesMasterIdList addNewNotesMasterIdLst() {
        CTNotesMasterIdList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNotesMasterIdList) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetNotesMasterIdLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTHandoutMasterIdList getHandoutMasterIdLst() {
        CTHandoutMasterIdList cTHandoutMasterIdList;
        synchronized (monitor()) {
            check_orphaned();
            CTHandoutMasterIdList target = (CTHandoutMasterIdList) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTHandoutMasterIdList = target == null ? null : target;
        }
        return cTHandoutMasterIdList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetHandoutMasterIdLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setHandoutMasterIdLst(CTHandoutMasterIdList handoutMasterIdLst) {
        generatedSetterHelperImpl(handoutMasterIdLst, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTHandoutMasterIdList addNewHandoutMasterIdLst() {
        CTHandoutMasterIdList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHandoutMasterIdList) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetHandoutMasterIdLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTSlideIdList getSldIdLst() {
        CTSlideIdList cTSlideIdList;
        synchronized (monitor()) {
            check_orphaned();
            CTSlideIdList target = (CTSlideIdList) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTSlideIdList = target == null ? null : target;
        }
        return cTSlideIdList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetSldIdLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setSldIdLst(CTSlideIdList sldIdLst) {
        generatedSetterHelperImpl(sldIdLst, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTSlideIdList addNewSldIdLst() {
        CTSlideIdList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSlideIdList) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetSldIdLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTSlideSize getSldSz() {
        CTSlideSize cTSlideSize;
        synchronized (monitor()) {
            check_orphaned();
            CTSlideSize target = (CTSlideSize) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTSlideSize = target == null ? null : target;
        }
        return cTSlideSize;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetSldSz() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setSldSz(CTSlideSize sldSz) {
        generatedSetterHelperImpl(sldSz, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTSlideSize addNewSldSz() {
        CTSlideSize target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSlideSize) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetSldSz() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTPositiveSize2D getNotesSz() {
        CTPositiveSize2D cTPositiveSize2D;
        synchronized (monitor()) {
            check_orphaned();
            CTPositiveSize2D target = (CTPositiveSize2D) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTPositiveSize2D = target == null ? null : target;
        }
        return cTPositiveSize2D;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setNotesSz(CTPositiveSize2D notesSz) {
        generatedSetterHelperImpl(notesSz, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTPositiveSize2D addNewNotesSz() {
        CTPositiveSize2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveSize2D) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTSmartTags getSmartTags() {
        CTSmartTags cTSmartTags;
        synchronized (monitor()) {
            check_orphaned();
            CTSmartTags target = get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTSmartTags = target == null ? null : target;
        }
        return cTSmartTags;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetSmartTags() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setSmartTags(CTSmartTags smartTags) {
        generatedSetterHelperImpl(smartTags, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTSmartTags addNewSmartTags() {
        CTSmartTags target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetSmartTags() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTEmbeddedFontList getEmbeddedFontLst() {
        CTEmbeddedFontList cTEmbeddedFontList;
        synchronized (monitor()) {
            check_orphaned();
            CTEmbeddedFontList target = (CTEmbeddedFontList) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTEmbeddedFontList = target == null ? null : target;
        }
        return cTEmbeddedFontList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetEmbeddedFontLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setEmbeddedFontLst(CTEmbeddedFontList embeddedFontLst) {
        generatedSetterHelperImpl(embeddedFontLst, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTEmbeddedFontList addNewEmbeddedFontLst() {
        CTEmbeddedFontList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmbeddedFontList) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetEmbeddedFontLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTCustomShowList getCustShowLst() {
        CTCustomShowList cTCustomShowList;
        synchronized (monitor()) {
            check_orphaned();
            CTCustomShowList target = get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTCustomShowList = target == null ? null : target;
        }
        return cTCustomShowList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetCustShowLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setCustShowLst(CTCustomShowList custShowLst) {
        generatedSetterHelperImpl(custShowLst, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTCustomShowList addNewCustShowLst() {
        CTCustomShowList target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetCustShowLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTPhotoAlbum getPhotoAlbum() {
        CTPhotoAlbum cTPhotoAlbum;
        synchronized (monitor()) {
            check_orphaned();
            CTPhotoAlbum target = get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTPhotoAlbum = target == null ? null : target;
        }
        return cTPhotoAlbum;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetPhotoAlbum() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setPhotoAlbum(CTPhotoAlbum photoAlbum) {
        generatedSetterHelperImpl(photoAlbum, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTPhotoAlbum addNewPhotoAlbum() {
        CTPhotoAlbum target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetPhotoAlbum() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTCustomerDataList getCustDataLst() {
        CTCustomerDataList cTCustomerDataList;
        synchronized (monitor()) {
            check_orphaned();
            CTCustomerDataList target = (CTCustomerDataList) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTCustomerDataList = target == null ? null : target;
        }
        return cTCustomerDataList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetCustDataLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setCustDataLst(CTCustomerDataList custDataLst) {
        generatedSetterHelperImpl(custDataLst, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTCustomerDataList addNewCustDataLst() {
        CTCustomerDataList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCustomerDataList) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetCustDataLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTKinsoku getKinsoku() {
        CTKinsoku cTKinsoku;
        synchronized (monitor()) {
            check_orphaned();
            CTKinsoku target = get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTKinsoku = target == null ? null : target;
        }
        return cTKinsoku;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetKinsoku() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setKinsoku(CTKinsoku kinsoku) {
        generatedSetterHelperImpl(kinsoku, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTKinsoku addNewKinsoku() {
        CTKinsoku target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetKinsoku() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTTextListStyle getDefaultTextStyle() {
        CTTextListStyle cTTextListStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTTextListStyle target = (CTTextListStyle) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTTextListStyle = target == null ? null : target;
        }
        return cTTextListStyle;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetDefaultTextStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setDefaultTextStyle(CTTextListStyle defaultTextStyle) {
        generatedSetterHelperImpl(defaultTextStyle, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTTextListStyle addNewDefaultTextStyle() {
        CTTextListStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextListStyle) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetDefaultTextStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTModifyVerifier getModifyVerifier() {
        CTModifyVerifier cTModifyVerifier;
        synchronized (monitor()) {
            check_orphaned();
            CTModifyVerifier target = get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTModifyVerifier = target == null ? null : target;
        }
        return cTModifyVerifier;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetModifyVerifier() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setModifyVerifier(CTModifyVerifier modifyVerifier) {
        generatedSetterHelperImpl(modifyVerifier, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTModifyVerifier addNewModifyVerifier() {
        CTModifyVerifier target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetModifyVerifier() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[14], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public Object getServerZoom() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[15]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public STPercentage xgetServerZoom() {
        STPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (STPercentage) get_default_attribute_value(PROPERTY_QNAME[15]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetServerZoom() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[15]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setServerZoom(Object serverZoom) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.setObjectValue(serverZoom);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void xsetServerZoom(STPercentage serverZoom) {
        synchronized (monitor()) {
            check_orphaned();
            STPercentage target = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (STPercentage) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.set(serverZoom);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetServerZoom() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[15]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public int getFirstSlideNum() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[16]);
            }
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public XmlInt xgetFirstSlideNum() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (XmlInt) get_default_attribute_value(PROPERTY_QNAME[16]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetFirstSlideNum() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[16]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setFirstSlideNum(int firstSlideNum) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.setIntValue(firstSlideNum);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void xsetFirstSlideNum(XmlInt firstSlideNum) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (XmlInt) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.set(firstSlideNum);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetFirstSlideNum() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[16]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean getShowSpecialPlsOnTitleSld() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[17]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public XmlBoolean xgetShowSpecialPlsOnTitleSld() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[17]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetShowSpecialPlsOnTitleSld() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[17]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setShowSpecialPlsOnTitleSld(boolean showSpecialPlsOnTitleSld) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.setBooleanValue(showSpecialPlsOnTitleSld);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void xsetShowSpecialPlsOnTitleSld(XmlBoolean showSpecialPlsOnTitleSld) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.set(showSpecialPlsOnTitleSld);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetShowSpecialPlsOnTitleSld() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[17]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean getRtl() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[18]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public XmlBoolean xgetRtl() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[18]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetRtl() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[18]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setRtl(boolean rtl) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.setBooleanValue(rtl);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void xsetRtl(XmlBoolean rtl) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.set(rtl);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetRtl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[18]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean getRemovePersonalInfoOnSave() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[19]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public XmlBoolean xgetRemovePersonalInfoOnSave() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[19]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetRemovePersonalInfoOnSave() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[19]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setRemovePersonalInfoOnSave(boolean removePersonalInfoOnSave) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.setBooleanValue(removePersonalInfoOnSave);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void xsetRemovePersonalInfoOnSave(XmlBoolean removePersonalInfoOnSave) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.set(removePersonalInfoOnSave);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetRemovePersonalInfoOnSave() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[19]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean getCompatMode() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[20]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public XmlBoolean xgetCompatMode() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[20]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetCompatMode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[20]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setCompatMode(boolean compatMode) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.setBooleanValue(compatMode);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void xsetCompatMode(XmlBoolean compatMode) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.set(compatMode);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetCompatMode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[20]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean getStrictFirstAndLastChars() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[21]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public XmlBoolean xgetStrictFirstAndLastChars() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[21]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetStrictFirstAndLastChars() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[21]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setStrictFirstAndLastChars(boolean strictFirstAndLastChars) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.setBooleanValue(strictFirstAndLastChars);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void xsetStrictFirstAndLastChars(XmlBoolean strictFirstAndLastChars) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.set(strictFirstAndLastChars);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetStrictFirstAndLastChars() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[21]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean getEmbedTrueTypeFonts() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[22]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public XmlBoolean xgetEmbedTrueTypeFonts() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[22]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetEmbedTrueTypeFonts() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[22]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setEmbedTrueTypeFonts(boolean embedTrueTypeFonts) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.setBooleanValue(embedTrueTypeFonts);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void xsetEmbedTrueTypeFonts(XmlBoolean embedTrueTypeFonts) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.set(embedTrueTypeFonts);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetEmbedTrueTypeFonts() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[22]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean getSaveSubsetFonts() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[23]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public XmlBoolean xgetSaveSubsetFonts() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[23]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetSaveSubsetFonts() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[23]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setSaveSubsetFonts(boolean saveSubsetFonts) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.setBooleanValue(saveSubsetFonts);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void xsetSaveSubsetFonts(XmlBoolean saveSubsetFonts) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.set(saveSubsetFonts);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetSaveSubsetFonts() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[23]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean getAutoCompressPictures() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[24]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public XmlBoolean xgetAutoCompressPictures() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[24]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetAutoCompressPictures() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[24]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setAutoCompressPictures(boolean autoCompressPictures) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.setBooleanValue(autoCompressPictures);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void xsetAutoCompressPictures(XmlBoolean autoCompressPictures) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.set(autoCompressPictures);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetAutoCompressPictures() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[24]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public long getBookmarkIdSeed() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[25]);
            }
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public STBookmarkIdSeed xgetBookmarkIdSeed() {
        STBookmarkIdSeed target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBookmarkIdSeed) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (STBookmarkIdSeed) get_default_attribute_value(PROPERTY_QNAME[25]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetBookmarkIdSeed() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[25]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setBookmarkIdSeed(long bookmarkIdSeed) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.setLongValue(bookmarkIdSeed);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void xsetBookmarkIdSeed(STBookmarkIdSeed bookmarkIdSeed) {
        synchronized (monitor()) {
            check_orphaned();
            STBookmarkIdSeed target = (STBookmarkIdSeed) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (STBookmarkIdSeed) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.set(bookmarkIdSeed);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetBookmarkIdSeed() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[25]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public STConformanceClass$Enum getConformance() {
        STConformanceClass$Enum sTConformanceClass$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            sTConformanceClass$Enum = target == null ? null : (STConformanceClass$Enum) target.getEnumValue();
        }
        return sTConformanceClass$Enum;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public STConformanceClass xgetConformance() {
        STConformanceClass target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public boolean isSetConformance() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[26]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void setConformance(STConformanceClass$Enum conformance) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.setEnumValue(conformance);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void xsetConformance(STConformanceClass conformance) {
        synchronized (monitor()) {
            check_orphaned();
            STConformanceClass target = get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (STConformanceClass) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.set(conformance);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation
    public void unsetConformance() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[26]);
        }
    }
}
