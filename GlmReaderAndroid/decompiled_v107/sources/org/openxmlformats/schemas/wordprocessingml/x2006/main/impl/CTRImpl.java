package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEmpty;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPicture;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRel;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSym;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber;

/* loaded from: classes12.dex */
public class CTRImpl extends XmlComplexContentImpl implements CTR {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "rPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, CompressorStreamFactory.BROTLI), new QName(XSSFRelation.NS_WORDPROCESSINGML, "t"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "contentPart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "delText"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "instrText"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "delInstrText"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "noBreakHyphen"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "softHyphen"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "dayShort"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "monthShort"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "yearShort"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "dayLong"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "monthLong"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "yearLong"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "annotationRef"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "footnoteRef"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "endnoteRef"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "separator"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "continuationSeparator"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "sym"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "pgNum"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "cr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tab"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "object"), new QName(XSSFRelation.NS_WORDPROCESSINGML, ContentTypes.EXTENSION_PICT), new QName(XSSFRelation.NS_WORDPROCESSINGML, "fldChar"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "ruby"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "footnoteReference"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "endnoteReference"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "commentReference"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "drawing"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "ptab"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "lastRenderedPageBreak"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rsidRPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rsidDel"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rsidR")};
    private static final long serialVersionUID = 1;

    public CTRImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTRPr getRPr() {
        CTRPr cTRPr;
        synchronized (monitor()) {
            check_orphaned();
            CTRPr target = (CTRPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTRPr = target == null ? null : target;
        }
        return cTRPr;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public boolean isSetRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setRPr(CTRPr rPr) {
        generatedSetterHelperImpl(rPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTRPr addNewRPr() {
        CTRPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void unsetRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTBr> getBrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda143
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getBrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda144
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setBrArray(((Integer) obj).intValue(), (CTBr) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda145
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewBr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda146
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeBr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda147
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfBrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTBr[] getBrArray() {
        return (CTBr[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTBr[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTBr getBrArray(int i) {
        CTBr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBr) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfBrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setBrArray(CTBr[] brArray) {
        check_orphaned();
        arraySetterHelper(brArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setBrArray(int i, CTBr br) {
        generatedSetterHelperImpl(br, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTBr insertNewBr(int i) {
        CTBr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBr) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTBr addNewBr() {
        CTBr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBr) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeBr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTText> getTList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda93
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getTArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda94
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setTArray(((Integer) obj).intValue(), (CTText) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda95
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewT(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda96
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeT(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda97
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfTArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTText[] getTArray() {
        return (CTText[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTText[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTText getTArray(int i) {
        CTText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTText) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfTArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setTArray(CTText[] tArray) {
        check_orphaned();
        arraySetterHelper(tArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setTArray(int i, CTText t) {
        generatedSetterHelperImpl(t, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTText insertNewT(int i) {
        CTText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTText) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTText addNewT() {
        CTText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTText) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeT(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTRel> getContentPartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getContentPartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setContentPartArray(((Integer) obj).intValue(), (CTRel) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewContentPart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeContentPart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfContentPartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTRel[] getContentPartArray() {
        return (CTRel[]) getXmlObjectArray(PROPERTY_QNAME[3], new CTRel[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTRel getContentPartArray(int i) {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRel) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfContentPartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setContentPartArray(CTRel[] contentPartArray) {
        check_orphaned();
        arraySetterHelper(contentPartArray, PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setContentPartArray(int i, CTRel contentPart) {
        generatedSetterHelperImpl(contentPart, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTRel insertNewContentPart(int i) {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRel) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTRel addNewContentPart() {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRel) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeContentPart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTText> getDelTextList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda120
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getDelTextArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda131
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setDelTextArray(((Integer) obj).intValue(), (CTText) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda142
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewDelText(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda153
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeDelText(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda164
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfDelTextArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTText[] getDelTextArray() {
        return (CTText[]) getXmlObjectArray(PROPERTY_QNAME[4], new CTText[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTText getDelTextArray(int i) {
        CTText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTText) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfDelTextArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setDelTextArray(CTText[] delTextArray) {
        check_orphaned();
        arraySetterHelper(delTextArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setDelTextArray(int i, CTText delText) {
        generatedSetterHelperImpl(delText, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTText insertNewDelText(int i) {
        CTText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTText) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTText addNewDelText() {
        CTText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTText) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeDelText(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTText> getInstrTextList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getInstrTextArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setInstrTextArray(((Integer) obj).intValue(), (CTText) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewInstrText(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeInstrText(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfInstrTextArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTText[] getInstrTextArray() {
        return (CTText[]) getXmlObjectArray(PROPERTY_QNAME[5], new CTText[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTText getInstrTextArray(int i) {
        CTText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTText) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfInstrTextArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setInstrTextArray(CTText[] instrTextArray) {
        check_orphaned();
        arraySetterHelper(instrTextArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setInstrTextArray(int i, CTText instrText) {
        generatedSetterHelperImpl(instrText, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTText insertNewInstrText(int i) {
        CTText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTText) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTText addNewInstrText() {
        CTText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTText) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeInstrText(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTText> getDelInstrTextList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda104
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getDelInstrTextArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda105
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setDelInstrTextArray(((Integer) obj).intValue(), (CTText) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda106
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewDelInstrText(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda107
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeDelInstrText(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda108
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfDelInstrTextArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTText[] getDelInstrTextArray() {
        return (CTText[]) getXmlObjectArray(PROPERTY_QNAME[6], new CTText[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTText getDelInstrTextArray(int i) {
        CTText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTText) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfDelInstrTextArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setDelInstrTextArray(CTText[] delInstrTextArray) {
        check_orphaned();
        arraySetterHelper(delInstrTextArray, PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setDelInstrTextArray(int i, CTText delInstrText) {
        generatedSetterHelperImpl(delInstrText, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTText insertNewDelInstrText(int i) {
        CTText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTText) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTText addNewDelInstrText() {
        CTText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTText) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeDelInstrText(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTEmpty> getNoBreakHyphenList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda110
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getNoBreakHyphenArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda111
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setNoBreakHyphenArray(((Integer) obj).intValue(), (CTEmpty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda112
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewNoBreakHyphen(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda113
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeNoBreakHyphen(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda114
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfNoBreakHyphenArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty[] getNoBreakHyphenArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[7], new CTEmpty[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty getNoBreakHyphenArray(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfNoBreakHyphenArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setNoBreakHyphenArray(CTEmpty[] noBreakHyphenArray) {
        check_orphaned();
        arraySetterHelper(noBreakHyphenArray, PROPERTY_QNAME[7]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setNoBreakHyphenArray(int i, CTEmpty noBreakHyphen) {
        generatedSetterHelperImpl(noBreakHyphen, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty insertNewNoBreakHyphen(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty addNewNoBreakHyphen() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeNoBreakHyphen(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTEmpty> getSoftHyphenList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getSoftHyphenArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setSoftHyphenArray(((Integer) obj).intValue(), (CTEmpty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewSoftHyphen(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeSoftHyphen(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfSoftHyphenArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty[] getSoftHyphenArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[8], new CTEmpty[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty getSoftHyphenArray(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfSoftHyphenArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setSoftHyphenArray(CTEmpty[] softHyphenArray) {
        check_orphaned();
        arraySetterHelper(softHyphenArray, PROPERTY_QNAME[8]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setSoftHyphenArray(int i, CTEmpty softHyphen) {
        generatedSetterHelperImpl(softHyphen, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty insertNewSoftHyphen(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty addNewSoftHyphen() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeSoftHyphen(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTEmpty> getDayShortList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getDayShortArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setDayShortArray(((Integer) obj).intValue(), (CTEmpty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewDayShort(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeDayShort(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfDayShortArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty[] getDayShortArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[9], new CTEmpty[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty getDayShortArray(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfDayShortArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setDayShortArray(CTEmpty[] dayShortArray) {
        check_orphaned();
        arraySetterHelper(dayShortArray, PROPERTY_QNAME[9]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setDayShortArray(int i, CTEmpty dayShort) {
        generatedSetterHelperImpl(dayShort, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty insertNewDayShort(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty addNewDayShort() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeDayShort(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTEmpty> getMonthShortList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda137
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getMonthShortArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda138
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setMonthShortArray(((Integer) obj).intValue(), (CTEmpty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda139
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewMonthShort(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda140
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeMonthShort(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda141
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfMonthShortArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty[] getMonthShortArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[10], new CTEmpty[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty getMonthShortArray(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfMonthShortArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setMonthShortArray(CTEmpty[] monthShortArray) {
        check_orphaned();
        arraySetterHelper(monthShortArray, PROPERTY_QNAME[10]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setMonthShortArray(int i, CTEmpty monthShort) {
        generatedSetterHelperImpl(monthShort, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty insertNewMonthShort(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty addNewMonthShort() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeMonthShort(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTEmpty> getYearShortList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda67
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getYearShortArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda68
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setYearShortArray(((Integer) obj).intValue(), (CTEmpty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda69
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewYearShort(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda70
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeYearShort(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda71
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfYearShortArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty[] getYearShortArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[11], new CTEmpty[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty getYearShortArray(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfYearShortArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setYearShortArray(CTEmpty[] yearShortArray) {
        check_orphaned();
        arraySetterHelper(yearShortArray, PROPERTY_QNAME[11]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setYearShortArray(int i, CTEmpty yearShort) {
        generatedSetterHelperImpl(yearShort, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty insertNewYearShort(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty addNewYearShort() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeYearShort(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTEmpty> getDayLongList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getDayLongArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setDayLongArray(((Integer) obj).intValue(), (CTEmpty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewDayLong(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeDayLong(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfDayLongArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty[] getDayLongArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[12], new CTEmpty[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty getDayLongArray(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfDayLongArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setDayLongArray(CTEmpty[] dayLongArray) {
        check_orphaned();
        arraySetterHelper(dayLongArray, PROPERTY_QNAME[12]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setDayLongArray(int i, CTEmpty dayLong) {
        generatedSetterHelperImpl(dayLong, PROPERTY_QNAME[12], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty insertNewDayLong(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty addNewDayLong() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeDayLong(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTEmpty> getMonthLongList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getMonthLongArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setMonthLongArray(((Integer) obj).intValue(), (CTEmpty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewMonthLong(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeMonthLong(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfMonthLongArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty[] getMonthLongArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[13], new CTEmpty[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty getMonthLongArray(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfMonthLongArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setMonthLongArray(CTEmpty[] monthLongArray) {
        check_orphaned();
        arraySetterHelper(monthLongArray, PROPERTY_QNAME[13]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setMonthLongArray(int i, CTEmpty monthLong) {
        generatedSetterHelperImpl(monthLong, PROPERTY_QNAME[13], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty insertNewMonthLong(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty addNewMonthLong() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeMonthLong(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTEmpty> getYearLongList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda121
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getYearLongArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda122
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setYearLongArray(((Integer) obj).intValue(), (CTEmpty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda123
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewYearLong(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda124
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeYearLong(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda125
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfYearLongArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty[] getYearLongArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[14], new CTEmpty[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty getYearLongArray(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfYearLongArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setYearLongArray(CTEmpty[] yearLongArray) {
        check_orphaned();
        arraySetterHelper(yearLongArray, PROPERTY_QNAME[14]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setYearLongArray(int i, CTEmpty yearLong) {
        generatedSetterHelperImpl(yearLong, PROPERTY_QNAME[14], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty insertNewYearLong(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty addNewYearLong() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeYearLong(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTEmpty> getAnnotationRefList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda77
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getAnnotationRefArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda78
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setAnnotationRefArray(((Integer) obj).intValue(), (CTEmpty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda79
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewAnnotationRef(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda80
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeAnnotationRef(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda81
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfAnnotationRefArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty[] getAnnotationRefArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[15], new CTEmpty[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty getAnnotationRefArray(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfAnnotationRefArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setAnnotationRefArray(CTEmpty[] annotationRefArray) {
        check_orphaned();
        arraySetterHelper(annotationRefArray, PROPERTY_QNAME[15]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setAnnotationRefArray(int i, CTEmpty annotationRef) {
        generatedSetterHelperImpl(annotationRef, PROPERTY_QNAME[15], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty insertNewAnnotationRef(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty addNewAnnotationRef() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeAnnotationRef(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTEmpty> getFootnoteRefList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda99
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getFootnoteRefArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda100
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setFootnoteRefArray(((Integer) obj).intValue(), (CTEmpty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda101
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewFootnoteRef(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda102
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeFootnoteRef(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda103
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfFootnoteRefArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty[] getFootnoteRefArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[16], new CTEmpty[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty getFootnoteRefArray(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfFootnoteRefArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setFootnoteRefArray(CTEmpty[] footnoteRefArray) {
        check_orphaned();
        arraySetterHelper(footnoteRefArray, PROPERTY_QNAME[16]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setFootnoteRefArray(int i, CTEmpty footnoteRef) {
        generatedSetterHelperImpl(footnoteRef, PROPERTY_QNAME[16], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty insertNewFootnoteRef(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty addNewFootnoteRef() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeFootnoteRef(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTEmpty> getEndnoteRefList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda88
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getEndnoteRefArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda89
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setEndnoteRefArray(((Integer) obj).intValue(), (CTEmpty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda90
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewEndnoteRef(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda91
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeEndnoteRef(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda92
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfEndnoteRefArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty[] getEndnoteRefArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[17], new CTEmpty[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty getEndnoteRefArray(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfEndnoteRefArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setEndnoteRefArray(CTEmpty[] endnoteRefArray) {
        check_orphaned();
        arraySetterHelper(endnoteRefArray, PROPERTY_QNAME[17]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setEndnoteRefArray(int i, CTEmpty endnoteRef) {
        generatedSetterHelperImpl(endnoteRef, PROPERTY_QNAME[17], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty insertNewEndnoteRef(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty addNewEndnoteRef() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeEndnoteRef(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTEmpty> getSeparatorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getSeparatorArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setSeparatorArray(((Integer) obj).intValue(), (CTEmpty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewSeparator(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeSeparator(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfSeparatorArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty[] getSeparatorArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[18], new CTEmpty[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty getSeparatorArray(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfSeparatorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setSeparatorArray(CTEmpty[] separatorArray) {
        check_orphaned();
        arraySetterHelper(separatorArray, PROPERTY_QNAME[18]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setSeparatorArray(int i, CTEmpty separator) {
        generatedSetterHelperImpl(separator, PROPERTY_QNAME[18], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty insertNewSeparator(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty addNewSeparator() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeSeparator(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTEmpty> getContinuationSeparatorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getContinuationSeparatorArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setContinuationSeparatorArray(((Integer) obj).intValue(), (CTEmpty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewContinuationSeparator(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeContinuationSeparator(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda65
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfContinuationSeparatorArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty[] getContinuationSeparatorArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[19], new CTEmpty[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty getContinuationSeparatorArray(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfContinuationSeparatorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setContinuationSeparatorArray(CTEmpty[] continuationSeparatorArray) {
        check_orphaned();
        arraySetterHelper(continuationSeparatorArray, PROPERTY_QNAME[19]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setContinuationSeparatorArray(int i, CTEmpty continuationSeparator) {
        generatedSetterHelperImpl(continuationSeparator, PROPERTY_QNAME[19], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty insertNewContinuationSeparator(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty addNewContinuationSeparator() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeContinuationSeparator(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTSym> getSymList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda66
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getSymArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda72
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setSymArray(((Integer) obj).intValue(), (CTSym) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda73
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewSym(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda74
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeSym(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda75
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfSymArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTSym[] getSymArray() {
        return (CTSym[]) getXmlObjectArray(PROPERTY_QNAME[20], new CTSym[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTSym getSymArray(int i) {
        CTSym target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSym) get_store().find_element_user(PROPERTY_QNAME[20], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfSymArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setSymArray(CTSym[] symArray) {
        check_orphaned();
        arraySetterHelper(symArray, PROPERTY_QNAME[20]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setSymArray(int i, CTSym sym) {
        generatedSetterHelperImpl(sym, PROPERTY_QNAME[20], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTSym insertNewSym(int i) {
        CTSym target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSym) get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTSym addNewSym() {
        CTSym target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSym) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeSym(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTEmpty> getPgNumList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda82
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getPgNumArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda83
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setPgNumArray(((Integer) obj).intValue(), (CTEmpty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda84
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewPgNum(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda85
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removePgNum(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda86
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfPgNumArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty[] getPgNumArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[21], new CTEmpty[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty getPgNumArray(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfPgNumArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setPgNumArray(CTEmpty[] pgNumArray) {
        check_orphaned();
        arraySetterHelper(pgNumArray, PROPERTY_QNAME[21]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setPgNumArray(int i, CTEmpty pgNum) {
        generatedSetterHelperImpl(pgNum, PROPERTY_QNAME[21], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty insertNewPgNum(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty addNewPgNum() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removePgNum(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTEmpty> getCrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getCrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setCrArray(((Integer) obj).intValue(), (CTEmpty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewCr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeCr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfCrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty[] getCrArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[22], new CTEmpty[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty getCrArray(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[22], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfCrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setCrArray(CTEmpty[] crArray) {
        check_orphaned();
        arraySetterHelper(crArray, PROPERTY_QNAME[22]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setCrArray(int i, CTEmpty cr) {
        generatedSetterHelperImpl(cr, PROPERTY_QNAME[22], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty insertNewCr(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty addNewCr() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeCr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTEmpty> getTabList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getTabArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda76
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setTabArray(((Integer) obj).intValue(), (CTEmpty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda87
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewTab(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda98
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeTab(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda109
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfTabArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty[] getTabArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[23], new CTEmpty[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty getTabArray(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[23], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfTabArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setTabArray(CTEmpty[] tabArray) {
        check_orphaned();
        arraySetterHelper(tabArray, PROPERTY_QNAME[23]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setTabArray(int i, CTEmpty tab) {
        generatedSetterHelperImpl(tab, PROPERTY_QNAME[23], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty insertNewTab(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty addNewTab() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeTab(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTObject> getObjectList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda148
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getObjectArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda149
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setObjectArray(((Integer) obj).intValue(), (CTObject) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda150
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewObject(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda151
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeObject(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda152
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfObjectArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTObject[] getObjectArray() {
        return (CTObject[]) getXmlObjectArray(PROPERTY_QNAME[24], new CTObject[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTObject getObjectArray(int i) {
        CTObject target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTObject) get_store().find_element_user(PROPERTY_QNAME[24], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfObjectArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setObjectArray(CTObject[] objectArray) {
        check_orphaned();
        arraySetterHelper(objectArray, PROPERTY_QNAME[24]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setObjectArray(int i, CTObject object) {
        generatedSetterHelperImpl(object, PROPERTY_QNAME[24], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTObject insertNewObject(int i) {
        CTObject target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTObject) get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTObject addNewObject() {
        CTObject target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTObject) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeObject(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTPicture> getPictList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda115
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getPictArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda116
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setPictArray(((Integer) obj).intValue(), (CTPicture) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda117
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewPict(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda118
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removePict(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda119
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfPictArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTPicture[] getPictArray() {
        return (CTPicture[]) getXmlObjectArray(PROPERTY_QNAME[25], new CTPicture[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTPicture getPictArray(int i) {
        CTPicture target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPicture) get_store().find_element_user(PROPERTY_QNAME[25], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfPictArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setPictArray(CTPicture[] pictArray) {
        check_orphaned();
        arraySetterHelper(pictArray, PROPERTY_QNAME[25]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setPictArray(int i, CTPicture pict) {
        generatedSetterHelperImpl(pict, PROPERTY_QNAME[25], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTPicture insertNewPict(int i) {
        CTPicture target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPicture) get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTPicture addNewPict() {
        CTPicture target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPicture) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removePict(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTFldChar> getFldCharList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getFldCharArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setFldCharArray(((Integer) obj).intValue(), (CTFldChar) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewFldChar(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeFldChar(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfFldCharArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTFldChar[] getFldCharArray() {
        return (CTFldChar[]) getXmlObjectArray(PROPERTY_QNAME[26], new CTFldChar[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTFldChar getFldCharArray(int i) {
        CTFldChar target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFldChar) get_store().find_element_user(PROPERTY_QNAME[26], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfFldCharArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[26]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setFldCharArray(CTFldChar[] fldCharArray) {
        check_orphaned();
        arraySetterHelper(fldCharArray, PROPERTY_QNAME[26]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setFldCharArray(int i, CTFldChar fldChar) {
        generatedSetterHelperImpl(fldChar, PROPERTY_QNAME[26], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTFldChar insertNewFldChar(int i) {
        CTFldChar target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFldChar) get_store().insert_element_user(PROPERTY_QNAME[26], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTFldChar addNewFldChar() {
        CTFldChar target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFldChar) get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeFldChar(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTRuby> getRubyList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getRubyArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setRubyArray(((Integer) obj).intValue(), (CTRuby) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewRuby(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeRuby(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfRubyArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTRuby[] getRubyArray() {
        return (CTRuby[]) getXmlObjectArray(PROPERTY_QNAME[27], new CTRuby[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTRuby getRubyArray(int i) {
        CTRuby target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRuby) get_store().find_element_user(PROPERTY_QNAME[27], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfRubyArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[27]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setRubyArray(CTRuby[] rubyArray) {
        check_orphaned();
        arraySetterHelper(rubyArray, PROPERTY_QNAME[27]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setRubyArray(int i, CTRuby ruby) {
        generatedSetterHelperImpl(ruby, PROPERTY_QNAME[27], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTRuby insertNewRuby(int i) {
        CTRuby target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRuby) get_store().insert_element_user(PROPERTY_QNAME[27], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTRuby addNewRuby() {
        CTRuby target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRuby) get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeRuby(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTFtnEdnRef> getFootnoteReferenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getFootnoteReferenceArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setFootnoteReferenceArray(((Integer) obj).intValue(), (CTFtnEdnRef) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewFootnoteReference(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeFootnoteReference(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfFootnoteReferenceArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTFtnEdnRef[] getFootnoteReferenceArray() {
        return (CTFtnEdnRef[]) getXmlObjectArray(PROPERTY_QNAME[28], new CTFtnEdnRef[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTFtnEdnRef getFootnoteReferenceArray(int i) {
        CTFtnEdnRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFtnEdnRef) get_store().find_element_user(PROPERTY_QNAME[28], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfFootnoteReferenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[28]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setFootnoteReferenceArray(CTFtnEdnRef[] footnoteReferenceArray) {
        check_orphaned();
        arraySetterHelper(footnoteReferenceArray, PROPERTY_QNAME[28]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setFootnoteReferenceArray(int i, CTFtnEdnRef footnoteReference) {
        generatedSetterHelperImpl(footnoteReference, PROPERTY_QNAME[28], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTFtnEdnRef insertNewFootnoteReference(int i) {
        CTFtnEdnRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFtnEdnRef) get_store().insert_element_user(PROPERTY_QNAME[28], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTFtnEdnRef addNewFootnoteReference() {
        CTFtnEdnRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFtnEdnRef) get_store().add_element_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeFootnoteReference(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTFtnEdnRef> getEndnoteReferenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda132
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getEndnoteReferenceArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda133
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setEndnoteReferenceArray(((Integer) obj).intValue(), (CTFtnEdnRef) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda134
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewEndnoteReference(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda135
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeEndnoteReference(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda136
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfEndnoteReferenceArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTFtnEdnRef[] getEndnoteReferenceArray() {
        return (CTFtnEdnRef[]) getXmlObjectArray(PROPERTY_QNAME[29], new CTFtnEdnRef[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTFtnEdnRef getEndnoteReferenceArray(int i) {
        CTFtnEdnRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFtnEdnRef) get_store().find_element_user(PROPERTY_QNAME[29], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfEndnoteReferenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[29]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setEndnoteReferenceArray(CTFtnEdnRef[] endnoteReferenceArray) {
        check_orphaned();
        arraySetterHelper(endnoteReferenceArray, PROPERTY_QNAME[29]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setEndnoteReferenceArray(int i, CTFtnEdnRef endnoteReference) {
        generatedSetterHelperImpl(endnoteReference, PROPERTY_QNAME[29], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTFtnEdnRef insertNewEndnoteReference(int i) {
        CTFtnEdnRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFtnEdnRef) get_store().insert_element_user(PROPERTY_QNAME[29], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTFtnEdnRef addNewEndnoteReference() {
        CTFtnEdnRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFtnEdnRef) get_store().add_element_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeEndnoteReference(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTMarkup> getCommentReferenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda154
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getCommentReferenceArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda155
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setCommentReferenceArray(((Integer) obj).intValue(), (CTMarkup) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda156
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewCommentReference(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda157
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeCommentReference(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda158
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfCommentReferenceArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTMarkup[] getCommentReferenceArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[30], new CTMarkup[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTMarkup getCommentReferenceArray(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[30], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfCommentReferenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[30]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setCommentReferenceArray(CTMarkup[] commentReferenceArray) {
        check_orphaned();
        arraySetterHelper(commentReferenceArray, PROPERTY_QNAME[30]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setCommentReferenceArray(int i, CTMarkup commentReference) {
        generatedSetterHelperImpl(commentReference, PROPERTY_QNAME[30], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTMarkup insertNewCommentReference(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[30], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTMarkup addNewCommentReference() {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[30]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeCommentReference(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[30], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTDrawing> getDrawingList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getDrawingArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setDrawingArray(((Integer) obj).intValue(), (CTDrawing) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewDrawing(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeDrawing(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda60
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfDrawingArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTDrawing[] getDrawingArray() {
        return (CTDrawing[]) getXmlObjectArray(PROPERTY_QNAME[31], new CTDrawing[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTDrawing getDrawingArray(int i) {
        CTDrawing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDrawing) get_store().find_element_user(PROPERTY_QNAME[31], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfDrawingArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[31]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setDrawingArray(CTDrawing[] drawingArray) {
        check_orphaned();
        arraySetterHelper(drawingArray, PROPERTY_QNAME[31]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setDrawingArray(int i, CTDrawing drawing) {
        generatedSetterHelperImpl(drawing, PROPERTY_QNAME[31], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTDrawing insertNewDrawing(int i) {
        CTDrawing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDrawing) get_store().insert_element_user(PROPERTY_QNAME[31], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTDrawing addNewDrawing() {
        CTDrawing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDrawing) get_store().add_element_user(PROPERTY_QNAME[31]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeDrawing(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[31], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTPTab> getPtabList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda126
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getPtabArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda127
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setPtabArray(((Integer) obj).intValue(), (CTPTab) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda128
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewPtab(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda129
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removePtab(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda130
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfPtabArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTPTab[] getPtabArray() {
        return (CTPTab[]) getXmlObjectArray(PROPERTY_QNAME[32], new CTPTab[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTPTab getPtabArray(int i) {
        CTPTab target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPTab) get_store().find_element_user(PROPERTY_QNAME[32], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfPtabArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[32]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setPtabArray(CTPTab[] ptabArray) {
        check_orphaned();
        arraySetterHelper(ptabArray, PROPERTY_QNAME[32]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setPtabArray(int i, CTPTab ptab) {
        generatedSetterHelperImpl(ptab, PROPERTY_QNAME[32], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTPTab insertNewPtab(int i) {
        CTPTab target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPTab) get_store().insert_element_user(PROPERTY_QNAME[32], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTPTab addNewPtab() {
        CTPTab target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPTab) get_store().add_element_user(PROPERTY_QNAME[32]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removePtab(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[32], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public List<CTEmpty> getLastRenderedPageBreakList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda159
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.getLastRenderedPageBreakArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda160
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRImpl.this.setLastRenderedPageBreakArray(((Integer) obj).intValue(), (CTEmpty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda161
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRImpl.this.insertNewLastRenderedPageBreak(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda162
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRImpl.this.removeLastRenderedPageBreak(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl$$ExternalSyntheticLambda163
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRImpl.this.sizeOfLastRenderedPageBreakArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty[] getLastRenderedPageBreakArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[33], new CTEmpty[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty getLastRenderedPageBreakArray(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[33], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public int sizeOfLastRenderedPageBreakArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[33]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setLastRenderedPageBreakArray(CTEmpty[] lastRenderedPageBreakArray) {
        check_orphaned();
        arraySetterHelper(lastRenderedPageBreakArray, PROPERTY_QNAME[33]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setLastRenderedPageBreakArray(int i, CTEmpty lastRenderedPageBreak) {
        generatedSetterHelperImpl(lastRenderedPageBreak, PROPERTY_QNAME[33], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty insertNewLastRenderedPageBreak(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[33], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public CTEmpty addNewLastRenderedPageBreak() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[33]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void removeLastRenderedPageBreak(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[33], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public byte[] getRsidRPr() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public STLongHexNumber xgetRsidRPr() {
        STLongHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[34]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public boolean isSetRsidRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[34]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setRsidRPr(byte[] rsidRPr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[34]);
            }
            target.setByteArrayValue(rsidRPr);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void xsetRsidRPr(STLongHexNumber rsidRPr) {
        synchronized (monitor()) {
            check_orphaned();
            STLongHexNumber target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            if (target == null) {
                target = (STLongHexNumber) get_store().add_attribute_user(PROPERTY_QNAME[34]);
            }
            target.set(rsidRPr);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void unsetRsidRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[34]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public byte[] getRsidDel() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public STLongHexNumber xgetRsidDel() {
        STLongHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[35]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public boolean isSetRsidDel() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[35]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setRsidDel(byte[] rsidDel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[35]);
            }
            target.setByteArrayValue(rsidDel);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void xsetRsidDel(STLongHexNumber rsidDel) {
        synchronized (monitor()) {
            check_orphaned();
            STLongHexNumber target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            if (target == null) {
                target = (STLongHexNumber) get_store().add_attribute_user(PROPERTY_QNAME[35]);
            }
            target.set(rsidDel);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void unsetRsidDel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[35]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public byte[] getRsidR() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public STLongHexNumber xgetRsidR() {
        STLongHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[36]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public boolean isSetRsidR() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[36]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void setRsidR(byte[] rsidR) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[36]);
            }
            target.setByteArrayValue(rsidR);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void xsetRsidR(STLongHexNumber rsidR) {
        synchronized (monitor()) {
            check_orphaned();
            STLongHexNumber target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            if (target == null) {
                target = (STLongHexNumber) get_store().add_attribute_user(PROPERTY_QNAME[36]);
            }
            target.set(rsidR);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR
    public void unsetRsidR() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[36]);
        }
    }
}
