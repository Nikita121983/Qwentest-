package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocGrid;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEdnProps;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnProps;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtrRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLineNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRel;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPrChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber;

/* loaded from: classes12.dex */
public class CTSectPrImpl extends XmlComplexContentImpl implements CTSectPr {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "headerReference"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "footerReference"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "footnotePr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "endnotePr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY), new QName(XSSFRelation.NS_WORDPROCESSINGML, "pgSz"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "pgMar"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "paperSrc"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "pgBorders"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "lnNumType"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "pgNumType"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "cols"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "formProt"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "vAlign"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "noEndnote"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "titlePg"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "textDirection"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bidi"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rtlGutter"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "docGrid"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "printerSettings"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "sectPrChange"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rsidRPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rsidDel"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rsidR"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rsidSect")};
    private static final long serialVersionUID = 1;

    public CTSectPrImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public List<CTHdrFtrRef> getHeaderReferenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSectPrImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSectPrImpl.this.getHeaderReferenceArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSectPrImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSectPrImpl.this.setHeaderReferenceArray(((Integer) obj).intValue(), (CTHdrFtrRef) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSectPrImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSectPrImpl.this.insertNewHeaderReference(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSectPrImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSectPrImpl.this.removeHeaderReference(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSectPrImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSectPrImpl.this.sizeOfHeaderReferenceArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTHdrFtrRef[] getHeaderReferenceArray() {
        return (CTHdrFtrRef[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTHdrFtrRef[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTHdrFtrRef getHeaderReferenceArray(int i) {
        CTHdrFtrRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHdrFtrRef) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public int sizeOfHeaderReferenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setHeaderReferenceArray(CTHdrFtrRef[] headerReferenceArray) {
        check_orphaned();
        arraySetterHelper(headerReferenceArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setHeaderReferenceArray(int i, CTHdrFtrRef headerReference) {
        generatedSetterHelperImpl(headerReference, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTHdrFtrRef insertNewHeaderReference(int i) {
        CTHdrFtrRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHdrFtrRef) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTHdrFtrRef addNewHeaderReference() {
        CTHdrFtrRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHdrFtrRef) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void removeHeaderReference(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public List<CTHdrFtrRef> getFooterReferenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSectPrImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSectPrImpl.this.getFooterReferenceArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSectPrImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSectPrImpl.this.setFooterReferenceArray(((Integer) obj).intValue(), (CTHdrFtrRef) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSectPrImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSectPrImpl.this.insertNewFooterReference(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSectPrImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSectPrImpl.this.removeFooterReference(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSectPrImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSectPrImpl.this.sizeOfFooterReferenceArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTHdrFtrRef[] getFooterReferenceArray() {
        return (CTHdrFtrRef[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTHdrFtrRef[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTHdrFtrRef getFooterReferenceArray(int i) {
        CTHdrFtrRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHdrFtrRef) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public int sizeOfFooterReferenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setFooterReferenceArray(CTHdrFtrRef[] footerReferenceArray) {
        check_orphaned();
        arraySetterHelper(footerReferenceArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setFooterReferenceArray(int i, CTHdrFtrRef footerReference) {
        generatedSetterHelperImpl(footerReference, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTHdrFtrRef insertNewFooterReference(int i) {
        CTHdrFtrRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHdrFtrRef) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTHdrFtrRef addNewFooterReference() {
        CTHdrFtrRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHdrFtrRef) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void removeFooterReference(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTFtnProps getFootnotePr() {
        CTFtnProps cTFtnProps;
        synchronized (monitor()) {
            check_orphaned();
            CTFtnProps target = (CTFtnProps) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTFtnProps = target == null ? null : target;
        }
        return cTFtnProps;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetFootnotePr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setFootnotePr(CTFtnProps footnotePr) {
        generatedSetterHelperImpl(footnotePr, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTFtnProps addNewFootnotePr() {
        CTFtnProps target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFtnProps) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetFootnotePr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTEdnProps getEndnotePr() {
        CTEdnProps cTEdnProps;
        synchronized (monitor()) {
            check_orphaned();
            CTEdnProps target = (CTEdnProps) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTEdnProps = target == null ? null : target;
        }
        return cTEdnProps;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetEndnotePr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setEndnotePr(CTEdnProps endnotePr) {
        generatedSetterHelperImpl(endnotePr, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTEdnProps addNewEndnotePr() {
        CTEdnProps target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEdnProps) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetEndnotePr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTSectType getType() {
        CTSectType cTSectType;
        synchronized (monitor()) {
            check_orphaned();
            CTSectType target = (CTSectType) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTSectType = target == null ? null : target;
        }
        return cTSectType;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setType(CTSectType type) {
        generatedSetterHelperImpl(type, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTSectType addNewType() {
        CTSectType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSectType) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTPageSz getPgSz() {
        CTPageSz cTPageSz;
        synchronized (monitor()) {
            check_orphaned();
            CTPageSz target = (CTPageSz) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTPageSz = target == null ? null : target;
        }
        return cTPageSz;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetPgSz() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setPgSz(CTPageSz pgSz) {
        generatedSetterHelperImpl(pgSz, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTPageSz addNewPgSz() {
        CTPageSz target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageSz) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetPgSz() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTPageMar getPgMar() {
        CTPageMar cTPageMar;
        synchronized (monitor()) {
            check_orphaned();
            CTPageMar target = (CTPageMar) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTPageMar = target == null ? null : target;
        }
        return cTPageMar;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetPgMar() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setPgMar(CTPageMar pgMar) {
        generatedSetterHelperImpl(pgMar, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTPageMar addNewPgMar() {
        CTPageMar target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageMar) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetPgMar() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTPaperSource getPaperSrc() {
        CTPaperSource cTPaperSource;
        synchronized (monitor()) {
            check_orphaned();
            CTPaperSource target = (CTPaperSource) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTPaperSource = target == null ? null : target;
        }
        return cTPaperSource;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetPaperSrc() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setPaperSrc(CTPaperSource paperSrc) {
        generatedSetterHelperImpl(paperSrc, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTPaperSource addNewPaperSrc() {
        CTPaperSource target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPaperSource) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetPaperSrc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTPageBorders getPgBorders() {
        CTPageBorders cTPageBorders;
        synchronized (monitor()) {
            check_orphaned();
            CTPageBorders target = (CTPageBorders) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTPageBorders = target == null ? null : target;
        }
        return cTPageBorders;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetPgBorders() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setPgBorders(CTPageBorders pgBorders) {
        generatedSetterHelperImpl(pgBorders, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTPageBorders addNewPgBorders() {
        CTPageBorders target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageBorders) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetPgBorders() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTLineNumber getLnNumType() {
        CTLineNumber cTLineNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTLineNumber target = get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTLineNumber = target == null ? null : target;
        }
        return cTLineNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetLnNumType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setLnNumType(CTLineNumber lnNumType) {
        generatedSetterHelperImpl(lnNumType, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTLineNumber addNewLnNumType() {
        CTLineNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetLnNumType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTPageNumber getPgNumType() {
        CTPageNumber cTPageNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTPageNumber target = (CTPageNumber) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTPageNumber = target == null ? null : target;
        }
        return cTPageNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetPgNumType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setPgNumType(CTPageNumber pgNumType) {
        generatedSetterHelperImpl(pgNumType, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTPageNumber addNewPgNumType() {
        CTPageNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageNumber) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetPgNumType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTColumns getCols() {
        CTColumns cTColumns;
        synchronized (monitor()) {
            check_orphaned();
            CTColumns target = (CTColumns) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTColumns = target == null ? null : target;
        }
        return cTColumns;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetCols() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setCols(CTColumns cols) {
        generatedSetterHelperImpl(cols, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTColumns addNewCols() {
        CTColumns target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColumns) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetCols() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTOnOff getFormProt() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetFormProt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setFormProt(CTOnOff formProt) {
        generatedSetterHelperImpl(formProt, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTOnOff addNewFormProt() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetFormProt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTVerticalJc getVAlign() {
        CTVerticalJc cTVerticalJc;
        synchronized (monitor()) {
            check_orphaned();
            CTVerticalJc target = (CTVerticalJc) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTVerticalJc = target == null ? null : target;
        }
        return cTVerticalJc;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetVAlign() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setVAlign(CTVerticalJc vAlign) {
        generatedSetterHelperImpl(vAlign, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTVerticalJc addNewVAlign() {
        CTVerticalJc target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTVerticalJc) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetVAlign() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTOnOff getNoEndnote() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetNoEndnote() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setNoEndnote(CTOnOff noEndnote) {
        generatedSetterHelperImpl(noEndnote, PROPERTY_QNAME[14], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTOnOff addNewNoEndnote() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetNoEndnote() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTOnOff getTitlePg() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetTitlePg() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setTitlePg(CTOnOff titlePg) {
        generatedSetterHelperImpl(titlePg, PROPERTY_QNAME[15], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTOnOff addNewTitlePg() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetTitlePg() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTTextDirection getTextDirection() {
        CTTextDirection cTTextDirection;
        synchronized (monitor()) {
            check_orphaned();
            CTTextDirection target = (CTTextDirection) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            cTTextDirection = target == null ? null : target;
        }
        return cTTextDirection;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetTextDirection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setTextDirection(CTTextDirection textDirection) {
        generatedSetterHelperImpl(textDirection, PROPERTY_QNAME[16], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTTextDirection addNewTextDirection() {
        CTTextDirection target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextDirection) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetTextDirection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTOnOff getBidi() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetBidi() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setBidi(CTOnOff bidi) {
        generatedSetterHelperImpl(bidi, PROPERTY_QNAME[17], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTOnOff addNewBidi() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetBidi() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTOnOff getRtlGutter() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetRtlGutter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setRtlGutter(CTOnOff rtlGutter) {
        generatedSetterHelperImpl(rtlGutter, PROPERTY_QNAME[18], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTOnOff addNewRtlGutter() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetRtlGutter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTDocGrid getDocGrid() {
        CTDocGrid cTDocGrid;
        synchronized (monitor()) {
            check_orphaned();
            CTDocGrid target = (CTDocGrid) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            cTDocGrid = target == null ? null : target;
        }
        return cTDocGrid;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetDocGrid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[19]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setDocGrid(CTDocGrid docGrid) {
        generatedSetterHelperImpl(docGrid, PROPERTY_QNAME[19], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTDocGrid addNewDocGrid() {
        CTDocGrid target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDocGrid) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetDocGrid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTRel getPrinterSettings() {
        CTRel cTRel;
        synchronized (monitor()) {
            check_orphaned();
            CTRel target = (CTRel) get_store().find_element_user(PROPERTY_QNAME[20], 0);
            cTRel = target == null ? null : target;
        }
        return cTRel;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetPrinterSettings() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[20]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setPrinterSettings(CTRel printerSettings) {
        generatedSetterHelperImpl(printerSettings, PROPERTY_QNAME[20], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTRel addNewPrinterSettings() {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRel) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetPrinterSettings() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTSectPrChange getSectPrChange() {
        CTSectPrChange cTSectPrChange;
        synchronized (monitor()) {
            check_orphaned();
            CTSectPrChange target = get_store().find_element_user(PROPERTY_QNAME[21], 0);
            cTSectPrChange = target == null ? null : target;
        }
        return cTSectPrChange;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetSectPrChange() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[21]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setSectPrChange(CTSectPrChange sectPrChange) {
        generatedSetterHelperImpl(sectPrChange, PROPERTY_QNAME[21], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public CTSectPrChange addNewSectPrChange() {
        CTSectPrChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetSectPrChange() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public byte[] getRsidRPr() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public STLongHexNumber xgetRsidRPr() {
        STLongHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetRsidRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[22]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setRsidRPr(byte[] rsidRPr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.setByteArrayValue(rsidRPr);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void xsetRsidRPr(STLongHexNumber rsidRPr) {
        synchronized (monitor()) {
            check_orphaned();
            STLongHexNumber target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (STLongHexNumber) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.set(rsidRPr);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetRsidRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[22]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public byte[] getRsidDel() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public STLongHexNumber xgetRsidDel() {
        STLongHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetRsidDel() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[23]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setRsidDel(byte[] rsidDel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.setByteArrayValue(rsidDel);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void xsetRsidDel(STLongHexNumber rsidDel) {
        synchronized (monitor()) {
            check_orphaned();
            STLongHexNumber target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (STLongHexNumber) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.set(rsidDel);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetRsidDel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[23]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public byte[] getRsidR() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public STLongHexNumber xgetRsidR() {
        STLongHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetRsidR() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[24]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setRsidR(byte[] rsidR) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.setByteArrayValue(rsidR);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void xsetRsidR(STLongHexNumber rsidR) {
        synchronized (monitor()) {
            check_orphaned();
            STLongHexNumber target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (STLongHexNumber) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.set(rsidR);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetRsidR() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[24]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public byte[] getRsidSect() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public STLongHexNumber xgetRsidSect() {
        STLongHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public boolean isSetRsidSect() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[25]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void setRsidSect(byte[] rsidSect) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.setByteArrayValue(rsidSect);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void xsetRsidSect(STLongHexNumber rsidSect) {
        synchronized (monitor()) {
            check_orphaned();
            STLongHexNumber target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (STLongHexNumber) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.set(rsidSect);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr
    public void unsetRsidSect() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[25]);
        }
    }
}
