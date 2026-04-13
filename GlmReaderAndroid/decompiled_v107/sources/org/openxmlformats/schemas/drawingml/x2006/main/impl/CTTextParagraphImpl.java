package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextField;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

/* loaded from: classes11.dex */
public class CTTextParagraphImpl extends XmlComplexContentImpl implements CTTextParagraph {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "pPr"), new QName(XSSFRelation.NS_DRAWINGML, "r"), new QName(XSSFRelation.NS_DRAWINGML, CompressorStreamFactory.BROTLI), new QName(XSSFRelation.NS_DRAWINGML, "fld"), new QName(XSSFRelation.NS_DRAWINGML, "endParaRPr")};
    private static final long serialVersionUID = 1;

    public CTTextParagraphImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public CTTextParagraphProperties getPPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTTextParagraphProperties target = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTextParagraphProperties = target == null ? null : target;
        }
        return cTTextParagraphProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public boolean isSetPPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public void setPPr(CTTextParagraphProperties pPr) {
        generatedSetterHelperImpl(pPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public CTTextParagraphProperties addNewPPr() {
        CTTextParagraphProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public void unsetPPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public List<CTRegularTextRun> getRList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextParagraphImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTextParagraphImpl.this.getRArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextParagraphImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTextParagraphImpl.this.setRArray(((Integer) obj).intValue(), (CTRegularTextRun) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextParagraphImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTextParagraphImpl.this.insertNewR(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextParagraphImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTextParagraphImpl.this.removeR(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextParagraphImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTextParagraphImpl.this.sizeOfRArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public CTRegularTextRun[] getRArray() {
        return (CTRegularTextRun[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTRegularTextRun[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public CTRegularTextRun getRArray(int i) {
        CTRegularTextRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRegularTextRun) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public int sizeOfRArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public void setRArray(CTRegularTextRun[] rArray) {
        check_orphaned();
        arraySetterHelper(rArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public void setRArray(int i, CTRegularTextRun r) {
        generatedSetterHelperImpl(r, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public CTRegularTextRun insertNewR(int i) {
        CTRegularTextRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRegularTextRun) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public CTRegularTextRun addNewR() {
        CTRegularTextRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRegularTextRun) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public void removeR(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public List<CTTextLineBreak> getBrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextParagraphImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTextParagraphImpl.this.getBrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextParagraphImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTextParagraphImpl.this.setBrArray(((Integer) obj).intValue(), (CTTextLineBreak) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextParagraphImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTextParagraphImpl.this.insertNewBr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextParagraphImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTextParagraphImpl.this.removeBr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextParagraphImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTextParagraphImpl.this.sizeOfBrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public CTTextLineBreak[] getBrArray() {
        return (CTTextLineBreak[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTTextLineBreak[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public CTTextLineBreak getBrArray(int i) {
        CTTextLineBreak target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextLineBreak) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public int sizeOfBrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public void setBrArray(CTTextLineBreak[] brArray) {
        check_orphaned();
        arraySetterHelper(brArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public void setBrArray(int i, CTTextLineBreak br) {
        generatedSetterHelperImpl(br, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public CTTextLineBreak insertNewBr(int i) {
        CTTextLineBreak target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextLineBreak) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public CTTextLineBreak addNewBr() {
        CTTextLineBreak target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextLineBreak) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public void removeBr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public List<CTTextField> getFldList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextParagraphImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTextParagraphImpl.this.getFldArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextParagraphImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTextParagraphImpl.this.setFldArray(((Integer) obj).intValue(), (CTTextField) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextParagraphImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTextParagraphImpl.this.insertNewFld(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextParagraphImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTextParagraphImpl.this.removeFld(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextParagraphImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTextParagraphImpl.this.sizeOfFldArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public CTTextField[] getFldArray() {
        return (CTTextField[]) getXmlObjectArray(PROPERTY_QNAME[3], new CTTextField[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public CTTextField getFldArray(int i) {
        CTTextField target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextField) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public int sizeOfFldArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public void setFldArray(CTTextField[] fldArray) {
        check_orphaned();
        arraySetterHelper(fldArray, PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public void setFldArray(int i, CTTextField fld) {
        generatedSetterHelperImpl(fld, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public CTTextField insertNewFld(int i) {
        CTTextField target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextField) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public CTTextField addNewFld() {
        CTTextField target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextField) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public void removeFld(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public CTTextCharacterProperties getEndParaRPr() {
        CTTextCharacterProperties cTTextCharacterProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTTextCharacterProperties target = (CTTextCharacterProperties) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTTextCharacterProperties = target == null ? null : target;
        }
        return cTTextCharacterProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public boolean isSetEndParaRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public void setEndParaRPr(CTTextCharacterProperties endParaRPr) {
        generatedSetterHelperImpl(endParaRPr, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public CTTextCharacterProperties addNewEndParaRPr() {
        CTTextCharacterProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextCharacterProperties) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph
    public void unsetEndParaRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }
}
