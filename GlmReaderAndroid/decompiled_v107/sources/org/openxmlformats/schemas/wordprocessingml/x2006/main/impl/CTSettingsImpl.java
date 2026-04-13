package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMathPr;
import org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchemaLibrary;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCharacterSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColorSchemeMapping;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCompat;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumberOrPrecent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocRsids;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocVars;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEdnDocProps;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEmpty;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnDocProps;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTKinsoku;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMailMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProof;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRel;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSaveThroughXslt;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShapeDefaults;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSmartTagType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStylePaneFilter;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyleSort;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTView;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWriteProtection;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTZoom;

/* loaded from: classes12.dex */
public class CTSettingsImpl extends XmlComplexContentImpl implements CTSettings {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "writeProtection"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "view"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "zoom"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "removePersonalInformation"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "removeDateAndTime"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "doNotDisplayPageBoundaries"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "displayBackgroundShape"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "printPostScriptOverText"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "printFractionalCharacterWidth"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "printFormsData"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "embedTrueTypeFonts"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "embedSystemFonts"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "saveSubsetFonts"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "saveFormsData"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "mirrorMargins"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "alignBordersAndEdges"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bordersDoNotSurroundHeader"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bordersDoNotSurroundFooter"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "gutterAtTop"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hideSpellingErrors"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hideGrammaticalErrors"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "activeWritingStyle"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "proofState"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "formsDesign"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "attachedTemplate"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "linkStyles"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "stylePaneFormatFilter"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "stylePaneSortMethod"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "documentType"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "mailMerge"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "revisionView"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "trackRevisions"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "doNotTrackMoves"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "doNotTrackFormatting"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "documentProtection"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "autoFormatOverride"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "styleLockTheme"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "styleLockQFSet"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "defaultTabStop"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "autoHyphenation"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "consecutiveHyphenLimit"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hyphenationZone"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "doNotHyphenateCaps"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "showEnvelope"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "summaryLength"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "clickAndTypeStyle"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "defaultTableStyle"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "evenAndOddHeaders"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bookFoldRevPrinting"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bookFoldPrinting"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bookFoldPrintingSheets"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "drawingGridHorizontalSpacing"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "drawingGridVerticalSpacing"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "displayHorizontalDrawingGridEvery"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "displayVerticalDrawingGridEvery"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "doNotUseMarginsForDrawingGridOrigin"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "drawingGridHorizontalOrigin"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "drawingGridVerticalOrigin"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "doNotShadeFormData"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "noPunctuationKerning"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "characterSpacingControl"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "printTwoOnOne"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "strictFirstAndLastChars"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "noLineBreaksAfter"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "noLineBreaksBefore"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "savePreviewPicture"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "doNotValidateAgainstSchema"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "saveInvalidXml"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "ignoreMixedContent"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "alwaysShowPlaceholderText"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "doNotDemarcateInvalidXml"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "saveXmlDataOnly"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "useXSLTWhenSaving"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "saveThroughXslt"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "showXMLTags"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "alwaysMergeEmptyNamespace"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "updateFields"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hdrShapeDefaults"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "footnotePr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "endnotePr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "compat"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "docVars"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rsids"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "mathPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "attachedSchema"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "themeFontLang"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "clrSchemeMapping"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "doNotIncludeSubdocsInStats"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "doNotAutoCompressPictures"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "forceUpgrade"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "captions"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "readModeInkLockDown"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "smartTagType"), new QName("http://schemas.openxmlformats.org/schemaLibrary/2006/main", "schemaLibrary"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "shapeDefaults"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "doNotEmbedSmartTags"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "decimalSymbol"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "listSeparator")};
    private static final long serialVersionUID = 1;

    public CTSettingsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTWriteProtection getWriteProtection() {
        CTWriteProtection cTWriteProtection;
        synchronized (monitor()) {
            check_orphaned();
            CTWriteProtection target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTWriteProtection = target == null ? null : target;
        }
        return cTWriteProtection;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetWriteProtection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setWriteProtection(CTWriteProtection writeProtection) {
        generatedSetterHelperImpl(writeProtection, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTWriteProtection addNewWriteProtection() {
        CTWriteProtection target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetWriteProtection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTView getView() {
        CTView cTView;
        synchronized (monitor()) {
            check_orphaned();
            CTView target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTView = target == null ? null : target;
        }
        return cTView;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetView() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setView(CTView view) {
        generatedSetterHelperImpl(view, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTView addNewView() {
        CTView target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetView() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTZoom getZoom() {
        CTZoom cTZoom;
        synchronized (monitor()) {
            check_orphaned();
            CTZoom target = (CTZoom) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTZoom = target == null ? null : target;
        }
        return cTZoom;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetZoom() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setZoom(CTZoom zoom) {
        generatedSetterHelperImpl(zoom, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTZoom addNewZoom() {
        CTZoom target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTZoom) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetZoom() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getRemovePersonalInformation() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetRemovePersonalInformation() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setRemovePersonalInformation(CTOnOff removePersonalInformation) {
        generatedSetterHelperImpl(removePersonalInformation, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewRemovePersonalInformation() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetRemovePersonalInformation() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getRemoveDateAndTime() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetRemoveDateAndTime() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setRemoveDateAndTime(CTOnOff removeDateAndTime) {
        generatedSetterHelperImpl(removeDateAndTime, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewRemoveDateAndTime() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetRemoveDateAndTime() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getDoNotDisplayPageBoundaries() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDoNotDisplayPageBoundaries() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDoNotDisplayPageBoundaries(CTOnOff doNotDisplayPageBoundaries) {
        generatedSetterHelperImpl(doNotDisplayPageBoundaries, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewDoNotDisplayPageBoundaries() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDoNotDisplayPageBoundaries() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getDisplayBackgroundShape() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDisplayBackgroundShape() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDisplayBackgroundShape(CTOnOff displayBackgroundShape) {
        generatedSetterHelperImpl(displayBackgroundShape, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewDisplayBackgroundShape() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDisplayBackgroundShape() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getPrintPostScriptOverText() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetPrintPostScriptOverText() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setPrintPostScriptOverText(CTOnOff printPostScriptOverText) {
        generatedSetterHelperImpl(printPostScriptOverText, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewPrintPostScriptOverText() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetPrintPostScriptOverText() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getPrintFractionalCharacterWidth() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetPrintFractionalCharacterWidth() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setPrintFractionalCharacterWidth(CTOnOff printFractionalCharacterWidth) {
        generatedSetterHelperImpl(printFractionalCharacterWidth, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewPrintFractionalCharacterWidth() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetPrintFractionalCharacterWidth() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getPrintFormsData() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetPrintFormsData() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setPrintFormsData(CTOnOff printFormsData) {
        generatedSetterHelperImpl(printFormsData, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewPrintFormsData() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetPrintFormsData() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getEmbedTrueTypeFonts() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetEmbedTrueTypeFonts() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setEmbedTrueTypeFonts(CTOnOff embedTrueTypeFonts) {
        generatedSetterHelperImpl(embedTrueTypeFonts, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewEmbedTrueTypeFonts() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetEmbedTrueTypeFonts() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getEmbedSystemFonts() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetEmbedSystemFonts() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setEmbedSystemFonts(CTOnOff embedSystemFonts) {
        generatedSetterHelperImpl(embedSystemFonts, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewEmbedSystemFonts() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetEmbedSystemFonts() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getSaveSubsetFonts() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetSaveSubsetFonts() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setSaveSubsetFonts(CTOnOff saveSubsetFonts) {
        generatedSetterHelperImpl(saveSubsetFonts, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewSaveSubsetFonts() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetSaveSubsetFonts() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getSaveFormsData() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetSaveFormsData() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setSaveFormsData(CTOnOff saveFormsData) {
        generatedSetterHelperImpl(saveFormsData, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewSaveFormsData() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetSaveFormsData() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getMirrorMargins() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetMirrorMargins() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setMirrorMargins(CTOnOff mirrorMargins) {
        generatedSetterHelperImpl(mirrorMargins, PROPERTY_QNAME[14], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewMirrorMargins() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetMirrorMargins() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getAlignBordersAndEdges() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetAlignBordersAndEdges() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setAlignBordersAndEdges(CTOnOff alignBordersAndEdges) {
        generatedSetterHelperImpl(alignBordersAndEdges, PROPERTY_QNAME[15], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewAlignBordersAndEdges() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetAlignBordersAndEdges() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getBordersDoNotSurroundHeader() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetBordersDoNotSurroundHeader() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setBordersDoNotSurroundHeader(CTOnOff bordersDoNotSurroundHeader) {
        generatedSetterHelperImpl(bordersDoNotSurroundHeader, PROPERTY_QNAME[16], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewBordersDoNotSurroundHeader() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetBordersDoNotSurroundHeader() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getBordersDoNotSurroundFooter() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetBordersDoNotSurroundFooter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setBordersDoNotSurroundFooter(CTOnOff bordersDoNotSurroundFooter) {
        generatedSetterHelperImpl(bordersDoNotSurroundFooter, PROPERTY_QNAME[17], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewBordersDoNotSurroundFooter() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetBordersDoNotSurroundFooter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getGutterAtTop() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetGutterAtTop() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setGutterAtTop(CTOnOff gutterAtTop) {
        generatedSetterHelperImpl(gutterAtTop, PROPERTY_QNAME[18], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewGutterAtTop() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetGutterAtTop() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getHideSpellingErrors() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetHideSpellingErrors() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[19]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setHideSpellingErrors(CTOnOff hideSpellingErrors) {
        generatedSetterHelperImpl(hideSpellingErrors, PROPERTY_QNAME[19], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewHideSpellingErrors() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetHideSpellingErrors() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getHideGrammaticalErrors() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[20], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetHideGrammaticalErrors() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[20]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setHideGrammaticalErrors(CTOnOff hideGrammaticalErrors) {
        generatedSetterHelperImpl(hideGrammaticalErrors, PROPERTY_QNAME[20], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewHideGrammaticalErrors() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetHideGrammaticalErrors() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public List<CTWritingStyle> getActiveWritingStyleList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSettingsImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSettingsImpl.this.getActiveWritingStyleArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSettingsImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSettingsImpl.this.setActiveWritingStyleArray(((Integer) obj).intValue(), (CTWritingStyle) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSettingsImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSettingsImpl.this.insertNewActiveWritingStyle(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSettingsImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSettingsImpl.this.removeActiveWritingStyle(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSettingsImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSettingsImpl.this.sizeOfActiveWritingStyleArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTWritingStyle[] getActiveWritingStyleArray() {
        return getXmlObjectArray(PROPERTY_QNAME[21], (XmlObject[]) new CTWritingStyle[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTWritingStyle getActiveWritingStyleArray(int i) {
        CTWritingStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public int sizeOfActiveWritingStyleArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setActiveWritingStyleArray(CTWritingStyle[] activeWritingStyleArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) activeWritingStyleArray, PROPERTY_QNAME[21]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setActiveWritingStyleArray(int i, CTWritingStyle activeWritingStyle) {
        generatedSetterHelperImpl(activeWritingStyle, PROPERTY_QNAME[21], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTWritingStyle insertNewActiveWritingStyle(int i) {
        CTWritingStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTWritingStyle addNewActiveWritingStyle() {
        CTWritingStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void removeActiveWritingStyle(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTProof getProofState() {
        CTProof cTProof;
        synchronized (monitor()) {
            check_orphaned();
            CTProof target = get_store().find_element_user(PROPERTY_QNAME[22], 0);
            cTProof = target == null ? null : target;
        }
        return cTProof;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetProofState() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[22]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setProofState(CTProof proofState) {
        generatedSetterHelperImpl(proofState, PROPERTY_QNAME[22], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTProof addNewProofState() {
        CTProof target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetProofState() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getFormsDesign() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[23], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetFormsDesign() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[23]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setFormsDesign(CTOnOff formsDesign) {
        generatedSetterHelperImpl(formsDesign, PROPERTY_QNAME[23], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewFormsDesign() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetFormsDesign() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTRel getAttachedTemplate() {
        CTRel cTRel;
        synchronized (monitor()) {
            check_orphaned();
            CTRel target = (CTRel) get_store().find_element_user(PROPERTY_QNAME[24], 0);
            cTRel = target == null ? null : target;
        }
        return cTRel;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetAttachedTemplate() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[24]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setAttachedTemplate(CTRel attachedTemplate) {
        generatedSetterHelperImpl(attachedTemplate, PROPERTY_QNAME[24], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTRel addNewAttachedTemplate() {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRel) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetAttachedTemplate() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getLinkStyles() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[25], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetLinkStyles() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[25]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setLinkStyles(CTOnOff linkStyles) {
        generatedSetterHelperImpl(linkStyles, PROPERTY_QNAME[25], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewLinkStyles() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetLinkStyles() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTStylePaneFilter getStylePaneFormatFilter() {
        CTStylePaneFilter cTStylePaneFilter;
        synchronized (monitor()) {
            check_orphaned();
            CTStylePaneFilter target = get_store().find_element_user(PROPERTY_QNAME[26], 0);
            cTStylePaneFilter = target == null ? null : target;
        }
        return cTStylePaneFilter;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetStylePaneFormatFilter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[26]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setStylePaneFormatFilter(CTStylePaneFilter stylePaneFormatFilter) {
        generatedSetterHelperImpl(stylePaneFormatFilter, PROPERTY_QNAME[26], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTStylePaneFilter addNewStylePaneFormatFilter() {
        CTStylePaneFilter target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetStylePaneFormatFilter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTStyleSort getStylePaneSortMethod() {
        CTStyleSort cTStyleSort;
        synchronized (monitor()) {
            check_orphaned();
            CTStyleSort target = get_store().find_element_user(PROPERTY_QNAME[27], 0);
            cTStyleSort = target == null ? null : target;
        }
        return cTStyleSort;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetStylePaneSortMethod() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[27]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setStylePaneSortMethod(CTStyleSort stylePaneSortMethod) {
        generatedSetterHelperImpl(stylePaneSortMethod, PROPERTY_QNAME[27], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTStyleSort addNewStylePaneSortMethod() {
        CTStyleSort target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetStylePaneSortMethod() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTDocType getDocumentType() {
        CTDocType cTDocType;
        synchronized (monitor()) {
            check_orphaned();
            CTDocType target = get_store().find_element_user(PROPERTY_QNAME[28], 0);
            cTDocType = target == null ? null : target;
        }
        return cTDocType;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDocumentType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[28]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDocumentType(CTDocType documentType) {
        generatedSetterHelperImpl(documentType, PROPERTY_QNAME[28], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTDocType addNewDocumentType() {
        CTDocType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDocumentType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTMailMerge getMailMerge() {
        CTMailMerge cTMailMerge;
        synchronized (monitor()) {
            check_orphaned();
            CTMailMerge target = get_store().find_element_user(PROPERTY_QNAME[29], 0);
            cTMailMerge = target == null ? null : target;
        }
        return cTMailMerge;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetMailMerge() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[29]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setMailMerge(CTMailMerge mailMerge) {
        generatedSetterHelperImpl(mailMerge, PROPERTY_QNAME[29], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTMailMerge addNewMailMerge() {
        CTMailMerge target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetMailMerge() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTTrackChangesView getRevisionView() {
        CTTrackChangesView cTTrackChangesView;
        synchronized (monitor()) {
            check_orphaned();
            CTTrackChangesView target = get_store().find_element_user(PROPERTY_QNAME[30], 0);
            cTTrackChangesView = target == null ? null : target;
        }
        return cTTrackChangesView;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetRevisionView() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[30]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setRevisionView(CTTrackChangesView revisionView) {
        generatedSetterHelperImpl(revisionView, PROPERTY_QNAME[30], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTTrackChangesView addNewRevisionView() {
        CTTrackChangesView target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[30]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetRevisionView() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[30], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getTrackRevisions() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[31], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetTrackRevisions() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[31]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setTrackRevisions(CTOnOff trackRevisions) {
        generatedSetterHelperImpl(trackRevisions, PROPERTY_QNAME[31], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewTrackRevisions() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[31]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetTrackRevisions() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[31], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getDoNotTrackMoves() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[32], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDoNotTrackMoves() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[32]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDoNotTrackMoves(CTOnOff doNotTrackMoves) {
        generatedSetterHelperImpl(doNotTrackMoves, PROPERTY_QNAME[32], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewDoNotTrackMoves() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[32]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDoNotTrackMoves() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[32], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getDoNotTrackFormatting() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[33], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDoNotTrackFormatting() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[33]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDoNotTrackFormatting(CTOnOff doNotTrackFormatting) {
        generatedSetterHelperImpl(doNotTrackFormatting, PROPERTY_QNAME[33], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewDoNotTrackFormatting() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[33]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDoNotTrackFormatting() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[33], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTDocProtect getDocumentProtection() {
        CTDocProtect cTDocProtect;
        synchronized (monitor()) {
            check_orphaned();
            CTDocProtect target = (CTDocProtect) get_store().find_element_user(PROPERTY_QNAME[34], 0);
            cTDocProtect = target == null ? null : target;
        }
        return cTDocProtect;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDocumentProtection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[34]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDocumentProtection(CTDocProtect documentProtection) {
        generatedSetterHelperImpl(documentProtection, PROPERTY_QNAME[34], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTDocProtect addNewDocumentProtection() {
        CTDocProtect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDocProtect) get_store().add_element_user(PROPERTY_QNAME[34]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDocumentProtection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[34], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getAutoFormatOverride() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[35], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetAutoFormatOverride() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[35]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setAutoFormatOverride(CTOnOff autoFormatOverride) {
        generatedSetterHelperImpl(autoFormatOverride, PROPERTY_QNAME[35], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewAutoFormatOverride() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[35]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetAutoFormatOverride() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[35], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getStyleLockTheme() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[36], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetStyleLockTheme() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[36]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setStyleLockTheme(CTOnOff styleLockTheme) {
        generatedSetterHelperImpl(styleLockTheme, PROPERTY_QNAME[36], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewStyleLockTheme() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[36]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetStyleLockTheme() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[36], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getStyleLockQFSet() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[37], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetStyleLockQFSet() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[37]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setStyleLockQFSet(CTOnOff styleLockQFSet) {
        generatedSetterHelperImpl(styleLockQFSet, PROPERTY_QNAME[37], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewStyleLockQFSet() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[37]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetStyleLockQFSet() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[37], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTTwipsMeasure getDefaultTabStop() {
        CTTwipsMeasure cTTwipsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            CTTwipsMeasure target = get_store().find_element_user(PROPERTY_QNAME[38], 0);
            cTTwipsMeasure = target == null ? null : target;
        }
        return cTTwipsMeasure;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDefaultTabStop() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[38]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDefaultTabStop(CTTwipsMeasure defaultTabStop) {
        generatedSetterHelperImpl(defaultTabStop, PROPERTY_QNAME[38], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTTwipsMeasure addNewDefaultTabStop() {
        CTTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[38]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDefaultTabStop() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[38], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getAutoHyphenation() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[39], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetAutoHyphenation() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[39]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setAutoHyphenation(CTOnOff autoHyphenation) {
        generatedSetterHelperImpl(autoHyphenation, PROPERTY_QNAME[39], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewAutoHyphenation() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[39]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetAutoHyphenation() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[39], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTDecimalNumber getConsecutiveHyphenLimit() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTDecimalNumber target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[40], 0);
            cTDecimalNumber = target == null ? null : target;
        }
        return cTDecimalNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetConsecutiveHyphenLimit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[40]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setConsecutiveHyphenLimit(CTDecimalNumber consecutiveHyphenLimit) {
        generatedSetterHelperImpl(consecutiveHyphenLimit, PROPERTY_QNAME[40], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTDecimalNumber addNewConsecutiveHyphenLimit() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[40]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetConsecutiveHyphenLimit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[40], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTTwipsMeasure getHyphenationZone() {
        CTTwipsMeasure cTTwipsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            CTTwipsMeasure target = get_store().find_element_user(PROPERTY_QNAME[41], 0);
            cTTwipsMeasure = target == null ? null : target;
        }
        return cTTwipsMeasure;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetHyphenationZone() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[41]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setHyphenationZone(CTTwipsMeasure hyphenationZone) {
        generatedSetterHelperImpl(hyphenationZone, PROPERTY_QNAME[41], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTTwipsMeasure addNewHyphenationZone() {
        CTTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[41]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetHyphenationZone() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[41], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getDoNotHyphenateCaps() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[42], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDoNotHyphenateCaps() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[42]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDoNotHyphenateCaps(CTOnOff doNotHyphenateCaps) {
        generatedSetterHelperImpl(doNotHyphenateCaps, PROPERTY_QNAME[42], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewDoNotHyphenateCaps() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[42]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDoNotHyphenateCaps() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[42], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getShowEnvelope() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[43], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetShowEnvelope() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[43]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setShowEnvelope(CTOnOff showEnvelope) {
        generatedSetterHelperImpl(showEnvelope, PROPERTY_QNAME[43], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewShowEnvelope() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[43]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetShowEnvelope() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[43], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTDecimalNumberOrPrecent getSummaryLength() {
        CTDecimalNumberOrPrecent cTDecimalNumberOrPrecent;
        synchronized (monitor()) {
            check_orphaned();
            CTDecimalNumberOrPrecent target = get_store().find_element_user(PROPERTY_QNAME[44], 0);
            cTDecimalNumberOrPrecent = target == null ? null : target;
        }
        return cTDecimalNumberOrPrecent;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetSummaryLength() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[44]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setSummaryLength(CTDecimalNumberOrPrecent summaryLength) {
        generatedSetterHelperImpl(summaryLength, PROPERTY_QNAME[44], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTDecimalNumberOrPrecent addNewSummaryLength() {
        CTDecimalNumberOrPrecent target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[44]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetSummaryLength() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[44], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTString getClickAndTypeStyle() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[45], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetClickAndTypeStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[45]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setClickAndTypeStyle(CTString clickAndTypeStyle) {
        generatedSetterHelperImpl(clickAndTypeStyle, PROPERTY_QNAME[45], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTString addNewClickAndTypeStyle() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[45]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetClickAndTypeStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[45], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTString getDefaultTableStyle() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[46], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDefaultTableStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[46]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDefaultTableStyle(CTString defaultTableStyle) {
        generatedSetterHelperImpl(defaultTableStyle, PROPERTY_QNAME[46], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTString addNewDefaultTableStyle() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[46]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDefaultTableStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[46], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getEvenAndOddHeaders() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[47], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetEvenAndOddHeaders() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[47]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setEvenAndOddHeaders(CTOnOff evenAndOddHeaders) {
        generatedSetterHelperImpl(evenAndOddHeaders, PROPERTY_QNAME[47], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewEvenAndOddHeaders() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[47]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetEvenAndOddHeaders() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[47], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getBookFoldRevPrinting() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[48], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetBookFoldRevPrinting() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[48]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setBookFoldRevPrinting(CTOnOff bookFoldRevPrinting) {
        generatedSetterHelperImpl(bookFoldRevPrinting, PROPERTY_QNAME[48], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewBookFoldRevPrinting() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[48]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetBookFoldRevPrinting() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[48], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getBookFoldPrinting() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[49], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetBookFoldPrinting() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[49]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setBookFoldPrinting(CTOnOff bookFoldPrinting) {
        generatedSetterHelperImpl(bookFoldPrinting, PROPERTY_QNAME[49], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewBookFoldPrinting() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[49]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetBookFoldPrinting() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[49], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTDecimalNumber getBookFoldPrintingSheets() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTDecimalNumber target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[50], 0);
            cTDecimalNumber = target == null ? null : target;
        }
        return cTDecimalNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetBookFoldPrintingSheets() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[50]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setBookFoldPrintingSheets(CTDecimalNumber bookFoldPrintingSheets) {
        generatedSetterHelperImpl(bookFoldPrintingSheets, PROPERTY_QNAME[50], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTDecimalNumber addNewBookFoldPrintingSheets() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[50]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetBookFoldPrintingSheets() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[50], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTTwipsMeasure getDrawingGridHorizontalSpacing() {
        CTTwipsMeasure cTTwipsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            CTTwipsMeasure target = get_store().find_element_user(PROPERTY_QNAME[51], 0);
            cTTwipsMeasure = target == null ? null : target;
        }
        return cTTwipsMeasure;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDrawingGridHorizontalSpacing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[51]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDrawingGridHorizontalSpacing(CTTwipsMeasure drawingGridHorizontalSpacing) {
        generatedSetterHelperImpl(drawingGridHorizontalSpacing, PROPERTY_QNAME[51], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTTwipsMeasure addNewDrawingGridHorizontalSpacing() {
        CTTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[51]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDrawingGridHorizontalSpacing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[51], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTTwipsMeasure getDrawingGridVerticalSpacing() {
        CTTwipsMeasure cTTwipsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            CTTwipsMeasure target = get_store().find_element_user(PROPERTY_QNAME[52], 0);
            cTTwipsMeasure = target == null ? null : target;
        }
        return cTTwipsMeasure;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDrawingGridVerticalSpacing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[52]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDrawingGridVerticalSpacing(CTTwipsMeasure drawingGridVerticalSpacing) {
        generatedSetterHelperImpl(drawingGridVerticalSpacing, PROPERTY_QNAME[52], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTTwipsMeasure addNewDrawingGridVerticalSpacing() {
        CTTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[52]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDrawingGridVerticalSpacing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[52], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTDecimalNumber getDisplayHorizontalDrawingGridEvery() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTDecimalNumber target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[53], 0);
            cTDecimalNumber = target == null ? null : target;
        }
        return cTDecimalNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDisplayHorizontalDrawingGridEvery() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[53]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDisplayHorizontalDrawingGridEvery(CTDecimalNumber displayHorizontalDrawingGridEvery) {
        generatedSetterHelperImpl(displayHorizontalDrawingGridEvery, PROPERTY_QNAME[53], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTDecimalNumber addNewDisplayHorizontalDrawingGridEvery() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[53]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDisplayHorizontalDrawingGridEvery() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[53], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTDecimalNumber getDisplayVerticalDrawingGridEvery() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTDecimalNumber target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[54], 0);
            cTDecimalNumber = target == null ? null : target;
        }
        return cTDecimalNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDisplayVerticalDrawingGridEvery() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[54]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDisplayVerticalDrawingGridEvery(CTDecimalNumber displayVerticalDrawingGridEvery) {
        generatedSetterHelperImpl(displayVerticalDrawingGridEvery, PROPERTY_QNAME[54], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTDecimalNumber addNewDisplayVerticalDrawingGridEvery() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[54]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDisplayVerticalDrawingGridEvery() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[54], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getDoNotUseMarginsForDrawingGridOrigin() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[55], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDoNotUseMarginsForDrawingGridOrigin() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[55]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDoNotUseMarginsForDrawingGridOrigin(CTOnOff doNotUseMarginsForDrawingGridOrigin) {
        generatedSetterHelperImpl(doNotUseMarginsForDrawingGridOrigin, PROPERTY_QNAME[55], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewDoNotUseMarginsForDrawingGridOrigin() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[55]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDoNotUseMarginsForDrawingGridOrigin() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[55], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTTwipsMeasure getDrawingGridHorizontalOrigin() {
        CTTwipsMeasure cTTwipsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            CTTwipsMeasure target = get_store().find_element_user(PROPERTY_QNAME[56], 0);
            cTTwipsMeasure = target == null ? null : target;
        }
        return cTTwipsMeasure;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDrawingGridHorizontalOrigin() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[56]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDrawingGridHorizontalOrigin(CTTwipsMeasure drawingGridHorizontalOrigin) {
        generatedSetterHelperImpl(drawingGridHorizontalOrigin, PROPERTY_QNAME[56], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTTwipsMeasure addNewDrawingGridHorizontalOrigin() {
        CTTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[56]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDrawingGridHorizontalOrigin() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[56], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTTwipsMeasure getDrawingGridVerticalOrigin() {
        CTTwipsMeasure cTTwipsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            CTTwipsMeasure target = get_store().find_element_user(PROPERTY_QNAME[57], 0);
            cTTwipsMeasure = target == null ? null : target;
        }
        return cTTwipsMeasure;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDrawingGridVerticalOrigin() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[57]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDrawingGridVerticalOrigin(CTTwipsMeasure drawingGridVerticalOrigin) {
        generatedSetterHelperImpl(drawingGridVerticalOrigin, PROPERTY_QNAME[57], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTTwipsMeasure addNewDrawingGridVerticalOrigin() {
        CTTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[57]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDrawingGridVerticalOrigin() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[57], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getDoNotShadeFormData() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[58], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDoNotShadeFormData() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[58]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDoNotShadeFormData(CTOnOff doNotShadeFormData) {
        generatedSetterHelperImpl(doNotShadeFormData, PROPERTY_QNAME[58], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewDoNotShadeFormData() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[58]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDoNotShadeFormData() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[58], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getNoPunctuationKerning() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[59], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetNoPunctuationKerning() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[59]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setNoPunctuationKerning(CTOnOff noPunctuationKerning) {
        generatedSetterHelperImpl(noPunctuationKerning, PROPERTY_QNAME[59], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewNoPunctuationKerning() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[59]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetNoPunctuationKerning() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[59], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTCharacterSpacing getCharacterSpacingControl() {
        CTCharacterSpacing cTCharacterSpacing;
        synchronized (monitor()) {
            check_orphaned();
            CTCharacterSpacing target = get_store().find_element_user(PROPERTY_QNAME[60], 0);
            cTCharacterSpacing = target == null ? null : target;
        }
        return cTCharacterSpacing;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetCharacterSpacingControl() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[60]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setCharacterSpacingControl(CTCharacterSpacing characterSpacingControl) {
        generatedSetterHelperImpl(characterSpacingControl, PROPERTY_QNAME[60], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTCharacterSpacing addNewCharacterSpacingControl() {
        CTCharacterSpacing target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[60]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetCharacterSpacingControl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[60], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getPrintTwoOnOne() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[61], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetPrintTwoOnOne() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[61]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setPrintTwoOnOne(CTOnOff printTwoOnOne) {
        generatedSetterHelperImpl(printTwoOnOne, PROPERTY_QNAME[61], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewPrintTwoOnOne() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[61]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetPrintTwoOnOne() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[61], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getStrictFirstAndLastChars() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[62], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetStrictFirstAndLastChars() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[62]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setStrictFirstAndLastChars(CTOnOff strictFirstAndLastChars) {
        generatedSetterHelperImpl(strictFirstAndLastChars, PROPERTY_QNAME[62], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewStrictFirstAndLastChars() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[62]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetStrictFirstAndLastChars() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[62], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTKinsoku getNoLineBreaksAfter() {
        CTKinsoku cTKinsoku;
        synchronized (monitor()) {
            check_orphaned();
            CTKinsoku target = get_store().find_element_user(PROPERTY_QNAME[63], 0);
            cTKinsoku = target == null ? null : target;
        }
        return cTKinsoku;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetNoLineBreaksAfter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[63]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setNoLineBreaksAfter(CTKinsoku noLineBreaksAfter) {
        generatedSetterHelperImpl(noLineBreaksAfter, PROPERTY_QNAME[63], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTKinsoku addNewNoLineBreaksAfter() {
        CTKinsoku target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[63]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetNoLineBreaksAfter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[63], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTKinsoku getNoLineBreaksBefore() {
        CTKinsoku cTKinsoku;
        synchronized (monitor()) {
            check_orphaned();
            CTKinsoku target = get_store().find_element_user(PROPERTY_QNAME[64], 0);
            cTKinsoku = target == null ? null : target;
        }
        return cTKinsoku;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetNoLineBreaksBefore() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[64]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setNoLineBreaksBefore(CTKinsoku noLineBreaksBefore) {
        generatedSetterHelperImpl(noLineBreaksBefore, PROPERTY_QNAME[64], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTKinsoku addNewNoLineBreaksBefore() {
        CTKinsoku target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[64]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetNoLineBreaksBefore() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[64], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getSavePreviewPicture() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[65], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetSavePreviewPicture() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[65]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setSavePreviewPicture(CTOnOff savePreviewPicture) {
        generatedSetterHelperImpl(savePreviewPicture, PROPERTY_QNAME[65], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewSavePreviewPicture() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[65]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetSavePreviewPicture() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[65], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getDoNotValidateAgainstSchema() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[66], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDoNotValidateAgainstSchema() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[66]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDoNotValidateAgainstSchema(CTOnOff doNotValidateAgainstSchema) {
        generatedSetterHelperImpl(doNotValidateAgainstSchema, PROPERTY_QNAME[66], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewDoNotValidateAgainstSchema() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[66]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDoNotValidateAgainstSchema() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[66], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getSaveInvalidXml() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[67], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetSaveInvalidXml() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[67]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setSaveInvalidXml(CTOnOff saveInvalidXml) {
        generatedSetterHelperImpl(saveInvalidXml, PROPERTY_QNAME[67], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewSaveInvalidXml() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[67]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetSaveInvalidXml() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[67], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getIgnoreMixedContent() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[68], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetIgnoreMixedContent() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[68]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setIgnoreMixedContent(CTOnOff ignoreMixedContent) {
        generatedSetterHelperImpl(ignoreMixedContent, PROPERTY_QNAME[68], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewIgnoreMixedContent() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[68]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetIgnoreMixedContent() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[68], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getAlwaysShowPlaceholderText() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[69], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetAlwaysShowPlaceholderText() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[69]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setAlwaysShowPlaceholderText(CTOnOff alwaysShowPlaceholderText) {
        generatedSetterHelperImpl(alwaysShowPlaceholderText, PROPERTY_QNAME[69], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewAlwaysShowPlaceholderText() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[69]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetAlwaysShowPlaceholderText() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[69], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getDoNotDemarcateInvalidXml() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[70], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDoNotDemarcateInvalidXml() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[70]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDoNotDemarcateInvalidXml(CTOnOff doNotDemarcateInvalidXml) {
        generatedSetterHelperImpl(doNotDemarcateInvalidXml, PROPERTY_QNAME[70], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewDoNotDemarcateInvalidXml() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[70]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDoNotDemarcateInvalidXml() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[70], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getSaveXmlDataOnly() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[71], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetSaveXmlDataOnly() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[71]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setSaveXmlDataOnly(CTOnOff saveXmlDataOnly) {
        generatedSetterHelperImpl(saveXmlDataOnly, PROPERTY_QNAME[71], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewSaveXmlDataOnly() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[71]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetSaveXmlDataOnly() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[71], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getUseXSLTWhenSaving() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[72], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetUseXSLTWhenSaving() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[72]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setUseXSLTWhenSaving(CTOnOff useXSLTWhenSaving) {
        generatedSetterHelperImpl(useXSLTWhenSaving, PROPERTY_QNAME[72], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewUseXSLTWhenSaving() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[72]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetUseXSLTWhenSaving() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[72], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTSaveThroughXslt getSaveThroughXslt() {
        CTSaveThroughXslt cTSaveThroughXslt;
        synchronized (monitor()) {
            check_orphaned();
            CTSaveThroughXslt target = get_store().find_element_user(PROPERTY_QNAME[73], 0);
            cTSaveThroughXslt = target == null ? null : target;
        }
        return cTSaveThroughXslt;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetSaveThroughXslt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[73]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setSaveThroughXslt(CTSaveThroughXslt saveThroughXslt) {
        generatedSetterHelperImpl(saveThroughXslt, PROPERTY_QNAME[73], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTSaveThroughXslt addNewSaveThroughXslt() {
        CTSaveThroughXslt target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[73]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetSaveThroughXslt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[73], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getShowXMLTags() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[74], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetShowXMLTags() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[74]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setShowXMLTags(CTOnOff showXMLTags) {
        generatedSetterHelperImpl(showXMLTags, PROPERTY_QNAME[74], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewShowXMLTags() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[74]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetShowXMLTags() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[74], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getAlwaysMergeEmptyNamespace() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[75], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetAlwaysMergeEmptyNamespace() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[75]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setAlwaysMergeEmptyNamespace(CTOnOff alwaysMergeEmptyNamespace) {
        generatedSetterHelperImpl(alwaysMergeEmptyNamespace, PROPERTY_QNAME[75], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewAlwaysMergeEmptyNamespace() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[75]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetAlwaysMergeEmptyNamespace() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[75], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getUpdateFields() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[76], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetUpdateFields() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[76]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setUpdateFields(CTOnOff updateFields) {
        generatedSetterHelperImpl(updateFields, PROPERTY_QNAME[76], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewUpdateFields() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[76]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetUpdateFields() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[76], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTShapeDefaults getHdrShapeDefaults() {
        CTShapeDefaults cTShapeDefaults;
        synchronized (monitor()) {
            check_orphaned();
            CTShapeDefaults target = get_store().find_element_user(PROPERTY_QNAME[77], 0);
            cTShapeDefaults = target == null ? null : target;
        }
        return cTShapeDefaults;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetHdrShapeDefaults() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[77]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setHdrShapeDefaults(CTShapeDefaults hdrShapeDefaults) {
        generatedSetterHelperImpl(hdrShapeDefaults, PROPERTY_QNAME[77], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTShapeDefaults addNewHdrShapeDefaults() {
        CTShapeDefaults target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[77]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetHdrShapeDefaults() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[77], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTFtnDocProps getFootnotePr() {
        CTFtnDocProps cTFtnDocProps;
        synchronized (monitor()) {
            check_orphaned();
            CTFtnDocProps target = get_store().find_element_user(PROPERTY_QNAME[78], 0);
            cTFtnDocProps = target == null ? null : target;
        }
        return cTFtnDocProps;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetFootnotePr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[78]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setFootnotePr(CTFtnDocProps footnotePr) {
        generatedSetterHelperImpl(footnotePr, PROPERTY_QNAME[78], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTFtnDocProps addNewFootnotePr() {
        CTFtnDocProps target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[78]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetFootnotePr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[78], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTEdnDocProps getEndnotePr() {
        CTEdnDocProps cTEdnDocProps;
        synchronized (monitor()) {
            check_orphaned();
            CTEdnDocProps target = get_store().find_element_user(PROPERTY_QNAME[79], 0);
            cTEdnDocProps = target == null ? null : target;
        }
        return cTEdnDocProps;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetEndnotePr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[79]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setEndnotePr(CTEdnDocProps endnotePr) {
        generatedSetterHelperImpl(endnotePr, PROPERTY_QNAME[79], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTEdnDocProps addNewEndnotePr() {
        CTEdnDocProps target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[79]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetEndnotePr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[79], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTCompat getCompat() {
        CTCompat cTCompat;
        synchronized (monitor()) {
            check_orphaned();
            CTCompat target = get_store().find_element_user(PROPERTY_QNAME[80], 0);
            cTCompat = target == null ? null : target;
        }
        return cTCompat;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetCompat() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[80]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setCompat(CTCompat compat) {
        generatedSetterHelperImpl(compat, PROPERTY_QNAME[80], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTCompat addNewCompat() {
        CTCompat target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[80]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetCompat() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[80], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTDocVars getDocVars() {
        CTDocVars cTDocVars;
        synchronized (monitor()) {
            check_orphaned();
            CTDocVars target = get_store().find_element_user(PROPERTY_QNAME[81], 0);
            cTDocVars = target == null ? null : target;
        }
        return cTDocVars;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDocVars() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[81]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDocVars(CTDocVars docVars) {
        generatedSetterHelperImpl(docVars, PROPERTY_QNAME[81], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTDocVars addNewDocVars() {
        CTDocVars target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[81]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDocVars() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[81], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTDocRsids getRsids() {
        CTDocRsids cTDocRsids;
        synchronized (monitor()) {
            check_orphaned();
            CTDocRsids target = get_store().find_element_user(PROPERTY_QNAME[82], 0);
            cTDocRsids = target == null ? null : target;
        }
        return cTDocRsids;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetRsids() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[82]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setRsids(CTDocRsids rsids) {
        generatedSetterHelperImpl(rsids, PROPERTY_QNAME[82], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTDocRsids addNewRsids() {
        CTDocRsids target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[82]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetRsids() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[82], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTMathPr getMathPr() {
        CTMathPr cTMathPr;
        synchronized (monitor()) {
            check_orphaned();
            CTMathPr target = get_store().find_element_user(PROPERTY_QNAME[83], 0);
            cTMathPr = target == null ? null : target;
        }
        return cTMathPr;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetMathPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[83]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setMathPr(CTMathPr mathPr) {
        generatedSetterHelperImpl(mathPr, PROPERTY_QNAME[83], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTMathPr addNewMathPr() {
        CTMathPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[83]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetMathPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[83], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public List<CTString> getAttachedSchemaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSettingsImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSettingsImpl.this.getAttachedSchemaArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSettingsImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSettingsImpl.this.setAttachedSchemaArray(((Integer) obj).intValue(), (CTString) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSettingsImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSettingsImpl.this.insertNewAttachedSchema(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSettingsImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSettingsImpl.this.removeAttachedSchema(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSettingsImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSettingsImpl.this.sizeOfAttachedSchemaArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTString[] getAttachedSchemaArray() {
        return (CTString[]) getXmlObjectArray(PROPERTY_QNAME[84], new CTString[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTString getAttachedSchemaArray(int i) {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().find_element_user(PROPERTY_QNAME[84], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public int sizeOfAttachedSchemaArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[84]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setAttachedSchemaArray(CTString[] attachedSchemaArray) {
        check_orphaned();
        arraySetterHelper(attachedSchemaArray, PROPERTY_QNAME[84]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setAttachedSchemaArray(int i, CTString attachedSchema) {
        generatedSetterHelperImpl(attachedSchema, PROPERTY_QNAME[84], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTString insertNewAttachedSchema(int i) {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().insert_element_user(PROPERTY_QNAME[84], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTString addNewAttachedSchema() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[84]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void removeAttachedSchema(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[84], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTLanguage getThemeFontLang() {
        CTLanguage cTLanguage;
        synchronized (monitor()) {
            check_orphaned();
            CTLanguage target = (CTLanguage) get_store().find_element_user(PROPERTY_QNAME[85], 0);
            cTLanguage = target == null ? null : target;
        }
        return cTLanguage;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetThemeFontLang() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[85]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setThemeFontLang(CTLanguage themeFontLang) {
        generatedSetterHelperImpl(themeFontLang, PROPERTY_QNAME[85], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTLanguage addNewThemeFontLang() {
        CTLanguage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLanguage) get_store().add_element_user(PROPERTY_QNAME[85]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetThemeFontLang() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[85], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTColorSchemeMapping getClrSchemeMapping() {
        CTColorSchemeMapping cTColorSchemeMapping;
        synchronized (monitor()) {
            check_orphaned();
            CTColorSchemeMapping target = get_store().find_element_user(PROPERTY_QNAME[86], 0);
            cTColorSchemeMapping = target == null ? null : target;
        }
        return cTColorSchemeMapping;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetClrSchemeMapping() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[86]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setClrSchemeMapping(CTColorSchemeMapping clrSchemeMapping) {
        generatedSetterHelperImpl(clrSchemeMapping, PROPERTY_QNAME[86], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTColorSchemeMapping addNewClrSchemeMapping() {
        CTColorSchemeMapping target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[86]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetClrSchemeMapping() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[86], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getDoNotIncludeSubdocsInStats() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[87], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDoNotIncludeSubdocsInStats() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[87]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDoNotIncludeSubdocsInStats(CTOnOff doNotIncludeSubdocsInStats) {
        generatedSetterHelperImpl(doNotIncludeSubdocsInStats, PROPERTY_QNAME[87], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewDoNotIncludeSubdocsInStats() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[87]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDoNotIncludeSubdocsInStats() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[87], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getDoNotAutoCompressPictures() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[88], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDoNotAutoCompressPictures() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[88]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDoNotAutoCompressPictures(CTOnOff doNotAutoCompressPictures) {
        generatedSetterHelperImpl(doNotAutoCompressPictures, PROPERTY_QNAME[88], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewDoNotAutoCompressPictures() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[88]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDoNotAutoCompressPictures() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[88], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTEmpty getForceUpgrade() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            CTEmpty target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[89], 0);
            cTEmpty = target == null ? null : target;
        }
        return cTEmpty;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetForceUpgrade() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[89]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setForceUpgrade(CTEmpty forceUpgrade) {
        generatedSetterHelperImpl(forceUpgrade, PROPERTY_QNAME[89], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTEmpty addNewForceUpgrade() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[89]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetForceUpgrade() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[89], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTCaptions getCaptions() {
        CTCaptions cTCaptions;
        synchronized (monitor()) {
            check_orphaned();
            CTCaptions target = get_store().find_element_user(PROPERTY_QNAME[90], 0);
            cTCaptions = target == null ? null : target;
        }
        return cTCaptions;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetCaptions() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[90]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setCaptions(CTCaptions captions) {
        generatedSetterHelperImpl(captions, PROPERTY_QNAME[90], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTCaptions addNewCaptions() {
        CTCaptions target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[90]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetCaptions() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[90], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTReadingModeInkLockDown getReadModeInkLockDown() {
        CTReadingModeInkLockDown cTReadingModeInkLockDown;
        synchronized (monitor()) {
            check_orphaned();
            CTReadingModeInkLockDown target = get_store().find_element_user(PROPERTY_QNAME[91], 0);
            cTReadingModeInkLockDown = target == null ? null : target;
        }
        return cTReadingModeInkLockDown;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetReadModeInkLockDown() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[91]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setReadModeInkLockDown(CTReadingModeInkLockDown readModeInkLockDown) {
        generatedSetterHelperImpl(readModeInkLockDown, PROPERTY_QNAME[91], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTReadingModeInkLockDown addNewReadModeInkLockDown() {
        CTReadingModeInkLockDown target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[91]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetReadModeInkLockDown() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[91], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public List<CTSmartTagType> getSmartTagTypeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSettingsImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSettingsImpl.this.getSmartTagTypeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSettingsImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSettingsImpl.this.setSmartTagTypeArray(((Integer) obj).intValue(), (CTSmartTagType) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSettingsImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSettingsImpl.this.insertNewSmartTagType(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSettingsImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSettingsImpl.this.removeSmartTagType(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSettingsImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSettingsImpl.this.sizeOfSmartTagTypeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTSmartTagType[] getSmartTagTypeArray() {
        return getXmlObjectArray(PROPERTY_QNAME[92], (XmlObject[]) new CTSmartTagType[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTSmartTagType getSmartTagTypeArray(int i) {
        CTSmartTagType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[92], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public int sizeOfSmartTagTypeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[92]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setSmartTagTypeArray(CTSmartTagType[] smartTagTypeArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) smartTagTypeArray, PROPERTY_QNAME[92]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setSmartTagTypeArray(int i, CTSmartTagType smartTagType) {
        generatedSetterHelperImpl(smartTagType, PROPERTY_QNAME[92], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTSmartTagType insertNewSmartTagType(int i) {
        CTSmartTagType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[92], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTSmartTagType addNewSmartTagType() {
        CTSmartTagType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[92]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void removeSmartTagType(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[92], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTSchemaLibrary getSchemaLibrary() {
        CTSchemaLibrary cTSchemaLibrary;
        synchronized (monitor()) {
            check_orphaned();
            CTSchemaLibrary target = get_store().find_element_user(PROPERTY_QNAME[93], 0);
            cTSchemaLibrary = target == null ? null : target;
        }
        return cTSchemaLibrary;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetSchemaLibrary() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[93]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setSchemaLibrary(CTSchemaLibrary schemaLibrary) {
        generatedSetterHelperImpl(schemaLibrary, PROPERTY_QNAME[93], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTSchemaLibrary addNewSchemaLibrary() {
        CTSchemaLibrary target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[93]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetSchemaLibrary() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[93], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTShapeDefaults getShapeDefaults() {
        CTShapeDefaults cTShapeDefaults;
        synchronized (monitor()) {
            check_orphaned();
            CTShapeDefaults target = get_store().find_element_user(PROPERTY_QNAME[94], 0);
            cTShapeDefaults = target == null ? null : target;
        }
        return cTShapeDefaults;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetShapeDefaults() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[94]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setShapeDefaults(CTShapeDefaults shapeDefaults) {
        generatedSetterHelperImpl(shapeDefaults, PROPERTY_QNAME[94], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTShapeDefaults addNewShapeDefaults() {
        CTShapeDefaults target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[94]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetShapeDefaults() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[94], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff getDoNotEmbedSmartTags() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[95], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDoNotEmbedSmartTags() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[95]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDoNotEmbedSmartTags(CTOnOff doNotEmbedSmartTags) {
        generatedSetterHelperImpl(doNotEmbedSmartTags, PROPERTY_QNAME[95], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTOnOff addNewDoNotEmbedSmartTags() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[95]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDoNotEmbedSmartTags() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[95], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTString getDecimalSymbol() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[96], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetDecimalSymbol() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[96]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setDecimalSymbol(CTString decimalSymbol) {
        generatedSetterHelperImpl(decimalSymbol, PROPERTY_QNAME[96], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTString addNewDecimalSymbol() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[96]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetDecimalSymbol() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[96], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTString getListSeparator() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[97], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public boolean isSetListSeparator() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[97]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void setListSeparator(CTString listSeparator) {
        generatedSetterHelperImpl(listSeparator, PROPERTY_QNAME[97], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public CTString addNewListSeparator() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[97]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings
    public void unsetListSeparator() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[97], 0);
        }
    }
}
