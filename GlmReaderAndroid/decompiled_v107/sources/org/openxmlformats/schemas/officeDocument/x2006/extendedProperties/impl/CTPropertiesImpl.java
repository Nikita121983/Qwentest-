package org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTDigSigBlob;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTVectorLpstr;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTVectorVariant;

/* loaded from: classes11.dex */
public class CTPropertiesImpl extends XmlComplexContentImpl implements CTProperties {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "Template"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "Manager"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "Company"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "Pages"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "Words"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "Characters"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "PresentationFormat"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "Lines"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "Paragraphs"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "Slides"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "Notes"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "TotalTime"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "HiddenSlides"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "MMClips"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "ScaleCrop"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "HeadingPairs"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "TitlesOfParts"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "LinksUpToDate"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "CharactersWithSpaces"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "SharedDoc"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "HyperlinkBase"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "HLinks"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "HyperlinksChanged"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "DigSig"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "Application"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "AppVersion"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "DocSecurity")};
    private static final long serialVersionUID = 1;

    public CTPropertiesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public String getTemplate() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlString xgetTemplate() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetTemplate() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setTemplate(String template) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(template);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetTemplate(XmlString template) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.set(template);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetTemplate() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public String getManager() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlString xgetManager() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetManager() {
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

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setManager(String manager) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(manager);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetManager(XmlString manager) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.set(manager);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetManager() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public String getCompany() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlString xgetCompany() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[2], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetCompany() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setCompany(String company) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(company);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetCompany(XmlString company) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.set(company);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetCompany() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public int getPages() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlInt xgetPages() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[3], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetPages() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setPages(int pages) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            target.setIntValue(pages);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetPages(XmlInt pages) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (target == null) {
                target = (XmlInt) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            target.set(pages);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetPages() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public int getWords() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlInt xgetWords() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[4], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetWords() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setWords(int words) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[4]);
            }
            target.setIntValue(words);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetWords(XmlInt words) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (target == null) {
                target = (XmlInt) get_store().add_element_user(PROPERTY_QNAME[4]);
            }
            target.set(words);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetWords() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public int getCharacters() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlInt xgetCharacters() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[5], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetCharacters() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setCharacters(int characters) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[5]);
            }
            target.setIntValue(characters);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetCharacters(XmlInt characters) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (target == null) {
                target = (XmlInt) get_store().add_element_user(PROPERTY_QNAME[5]);
            }
            target.set(characters);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetCharacters() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public String getPresentationFormat() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlString xgetPresentationFormat() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[6], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetPresentationFormat() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setPresentationFormat(String presentationFormat) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[6]);
            }
            target.setStringValue(presentationFormat);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetPresentationFormat(XmlString presentationFormat) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[6]);
            }
            target.set(presentationFormat);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetPresentationFormat() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public int getLines() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlInt xgetLines() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[7], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetLines() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setLines(int lines) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[7]);
            }
            target.setIntValue(lines);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetLines(XmlInt lines) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (target == null) {
                target = (XmlInt) get_store().add_element_user(PROPERTY_QNAME[7]);
            }
            target.set(lines);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetLines() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public int getParagraphs() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlInt xgetParagraphs() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[8], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetParagraphs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setParagraphs(int paragraphs) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[8]);
            }
            target.setIntValue(paragraphs);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetParagraphs(XmlInt paragraphs) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (target == null) {
                target = (XmlInt) get_store().add_element_user(PROPERTY_QNAME[8]);
            }
            target.set(paragraphs);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetParagraphs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public int getSlides() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlInt xgetSlides() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[9], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetSlides() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setSlides(int slides) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[9]);
            }
            target.setIntValue(slides);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetSlides(XmlInt slides) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (target == null) {
                target = (XmlInt) get_store().add_element_user(PROPERTY_QNAME[9]);
            }
            target.set(slides);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetSlides() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public int getNotes() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlInt xgetNotes() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[10], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetNotes() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setNotes(int notes) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[10]);
            }
            target.setIntValue(notes);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetNotes(XmlInt notes) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (target == null) {
                target = (XmlInt) get_store().add_element_user(PROPERTY_QNAME[10]);
            }
            target.set(notes);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetNotes() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public int getTotalTime() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlInt xgetTotalTime() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[11], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetTotalTime() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setTotalTime(int totalTime) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[11]);
            }
            target.setIntValue(totalTime);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetTotalTime(XmlInt totalTime) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (target == null) {
                target = (XmlInt) get_store().add_element_user(PROPERTY_QNAME[11]);
            }
            target.set(totalTime);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetTotalTime() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public int getHiddenSlides() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlInt xgetHiddenSlides() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[12], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetHiddenSlides() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setHiddenSlides(int hiddenSlides) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[12]);
            }
            target.setIntValue(hiddenSlides);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetHiddenSlides(XmlInt hiddenSlides) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (target == null) {
                target = (XmlInt) get_store().add_element_user(PROPERTY_QNAME[12]);
            }
            target.set(hiddenSlides);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetHiddenSlides() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public int getMMClips() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlInt xgetMMClips() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[13], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetMMClips() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setMMClips(int mmClips) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[13]);
            }
            target.setIntValue(mmClips);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetMMClips(XmlInt mmClips) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (target == null) {
                target = (XmlInt) get_store().add_element_user(PROPERTY_QNAME[13]);
            }
            target.set(mmClips);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetMMClips() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean getScaleCrop() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            if (target != null) {
                z = target.getBooleanValue();
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlBoolean xgetScaleCrop() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_element_user(PROPERTY_QNAME[14], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetScaleCrop() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setScaleCrop(boolean scaleCrop) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[14]);
            }
            target.setBooleanValue(scaleCrop);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetScaleCrop(XmlBoolean scaleCrop) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            if (target == null) {
                target = (XmlBoolean) get_store().add_element_user(PROPERTY_QNAME[14]);
            }
            target.set(scaleCrop);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetScaleCrop() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public CTVectorVariant getHeadingPairs() {
        CTVectorVariant cTVectorVariant;
        synchronized (monitor()) {
            check_orphaned();
            CTVectorVariant target = (CTVectorVariant) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            cTVectorVariant = target == null ? null : target;
        }
        return cTVectorVariant;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetHeadingPairs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setHeadingPairs(CTVectorVariant headingPairs) {
        generatedSetterHelperImpl(headingPairs, PROPERTY_QNAME[15], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public CTVectorVariant addNewHeadingPairs() {
        CTVectorVariant target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTVectorVariant) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetHeadingPairs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public CTVectorLpstr getTitlesOfParts() {
        CTVectorLpstr cTVectorLpstr;
        synchronized (monitor()) {
            check_orphaned();
            CTVectorLpstr target = (CTVectorLpstr) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            cTVectorLpstr = target == null ? null : target;
        }
        return cTVectorLpstr;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetTitlesOfParts() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setTitlesOfParts(CTVectorLpstr titlesOfParts) {
        generatedSetterHelperImpl(titlesOfParts, PROPERTY_QNAME[16], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public CTVectorLpstr addNewTitlesOfParts() {
        CTVectorLpstr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTVectorLpstr) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetTitlesOfParts() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean getLinksUpToDate() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            if (target != null) {
                z = target.getBooleanValue();
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlBoolean xgetLinksUpToDate() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_element_user(PROPERTY_QNAME[17], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetLinksUpToDate() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setLinksUpToDate(boolean linksUpToDate) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[17]);
            }
            target.setBooleanValue(linksUpToDate);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetLinksUpToDate(XmlBoolean linksUpToDate) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            if (target == null) {
                target = (XmlBoolean) get_store().add_element_user(PROPERTY_QNAME[17]);
            }
            target.set(linksUpToDate);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetLinksUpToDate() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public int getCharactersWithSpaces() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlInt xgetCharactersWithSpaces() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[18], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetCharactersWithSpaces() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setCharactersWithSpaces(int charactersWithSpaces) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[18]);
            }
            target.setIntValue(charactersWithSpaces);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetCharactersWithSpaces(XmlInt charactersWithSpaces) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            if (target == null) {
                target = (XmlInt) get_store().add_element_user(PROPERTY_QNAME[18]);
            }
            target.set(charactersWithSpaces);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetCharactersWithSpaces() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean getSharedDoc() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            if (target != null) {
                z = target.getBooleanValue();
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlBoolean xgetSharedDoc() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_element_user(PROPERTY_QNAME[19], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetSharedDoc() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[19]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setSharedDoc(boolean sharedDoc) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[19]);
            }
            target.setBooleanValue(sharedDoc);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetSharedDoc(XmlBoolean sharedDoc) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            if (target == null) {
                target = (XmlBoolean) get_store().add_element_user(PROPERTY_QNAME[19]);
            }
            target.set(sharedDoc);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetSharedDoc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public String getHyperlinkBase() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[20], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlString xgetHyperlinkBase() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[20], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetHyperlinkBase() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[20]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setHyperlinkBase(String hyperlinkBase) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[20], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[20]);
            }
            target.setStringValue(hyperlinkBase);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetHyperlinkBase(XmlString hyperlinkBase) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[20], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[20]);
            }
            target.set(hyperlinkBase);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetHyperlinkBase() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public CTVectorVariant getHLinks() {
        CTVectorVariant cTVectorVariant;
        synchronized (monitor()) {
            check_orphaned();
            CTVectorVariant target = (CTVectorVariant) get_store().find_element_user(PROPERTY_QNAME[21], 0);
            cTVectorVariant = target == null ? null : target;
        }
        return cTVectorVariant;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetHLinks() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[21]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setHLinks(CTVectorVariant hLinks) {
        generatedSetterHelperImpl(hLinks, PROPERTY_QNAME[21], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public CTVectorVariant addNewHLinks() {
        CTVectorVariant target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTVectorVariant) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetHLinks() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean getHyperlinksChanged() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[22], 0);
            if (target != null) {
                z = target.getBooleanValue();
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlBoolean xgetHyperlinksChanged() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_element_user(PROPERTY_QNAME[22], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetHyperlinksChanged() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[22]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setHyperlinksChanged(boolean hyperlinksChanged) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[22], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[22]);
            }
            target.setBooleanValue(hyperlinksChanged);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetHyperlinksChanged(XmlBoolean hyperlinksChanged) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_element_user(PROPERTY_QNAME[22], 0);
            if (target == null) {
                target = (XmlBoolean) get_store().add_element_user(PROPERTY_QNAME[22]);
            }
            target.set(hyperlinksChanged);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetHyperlinksChanged() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public CTDigSigBlob getDigSig() {
        CTDigSigBlob cTDigSigBlob;
        synchronized (monitor()) {
            check_orphaned();
            CTDigSigBlob target = (CTDigSigBlob) get_store().find_element_user(PROPERTY_QNAME[23], 0);
            cTDigSigBlob = target == null ? null : target;
        }
        return cTDigSigBlob;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetDigSig() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[23]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setDigSig(CTDigSigBlob digSig) {
        generatedSetterHelperImpl(digSig, PROPERTY_QNAME[23], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public CTDigSigBlob addNewDigSig() {
        CTDigSigBlob target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDigSigBlob) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetDigSig() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public String getApplication() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[24], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlString xgetApplication() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[24], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetApplication() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[24]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setApplication(String application) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[24], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[24]);
            }
            target.setStringValue(application);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetApplication(XmlString application) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[24], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[24]);
            }
            target.set(application);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetApplication() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public String getAppVersion() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[25], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlString xgetAppVersion() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[25], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetAppVersion() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[25]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setAppVersion(String appVersion) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[25], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[25]);
            }
            target.setStringValue(appVersion);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetAppVersion(XmlString appVersion) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[25], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[25]);
            }
            target.set(appVersion);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetAppVersion() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public int getDocSecurity() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[26], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public XmlInt xgetDocSecurity() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[26], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public boolean isSetDocSecurity() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[26]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void setDocSecurity(int docSecurity) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[26], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[26]);
            }
            target.setIntValue(docSecurity);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void xsetDocSecurity(XmlInt docSecurity) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[26], 0);
            if (target == null) {
                target = (XmlInt) get_store().add_element_user(PROPERTY_QNAME[26]);
            }
            target.set(docSecurity);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties
    public void unsetDocSecurity() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], 0);
        }
    }
}
