package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.ss.formula.functions.Complex;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFitText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHighlight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextEffect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextScale;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalAlignRun;

/* loaded from: classes12.dex */
public class CTParaRPrOriginalImpl extends XmlComplexContentImpl implements CTParaRPrOriginal {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "ins"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "del"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveFrom"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveTo"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rStyle"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rFonts"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "b"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bCs"), new QName(XSSFRelation.NS_WORDPROCESSINGML, Complex.DEFAULT_SUFFIX), new QName(XSSFRelation.NS_WORDPROCESSINGML, "iCs"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "caps"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "smallCaps"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "strike"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "dstrike"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "outline"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "shadow"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "emboss"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "imprint"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "noProof"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "snapToGrid"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "vanish"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "webHidden"), new QName(XSSFRelation.NS_WORDPROCESSINGML, TypedValues.Custom.S_COLOR), new QName(XSSFRelation.NS_WORDPROCESSINGML, "spacing"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "w"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "kern"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "position"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "sz"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "szCs"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "highlight"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "u"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "effect"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bdr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "shd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "fitText"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "vertAlign"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rtl"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "cs"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "em"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "lang"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "eastAsianLayout"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "specVanish"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "oMath")};
    private static final long serialVersionUID = 1;

    public CTParaRPrOriginalImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTTrackChange getIns() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            CTTrackChange target = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTrackChange = target == null ? null : target;
        }
        return cTTrackChange;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public boolean isSetIns() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setIns(CTTrackChange ins) {
        generatedSetterHelperImpl(ins, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTTrackChange addNewIns() {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void unsetIns() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTTrackChange getDel() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            CTTrackChange target = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTrackChange = target == null ? null : target;
        }
        return cTTrackChange;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public boolean isSetDel() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setDel(CTTrackChange del) {
        generatedSetterHelperImpl(del, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTTrackChange addNewDel() {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void unsetDel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTTrackChange getMoveFrom() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            CTTrackChange target = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTTrackChange = target == null ? null : target;
        }
        return cTTrackChange;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public boolean isSetMoveFrom() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setMoveFrom(CTTrackChange moveFrom) {
        generatedSetterHelperImpl(moveFrom, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTTrackChange addNewMoveFrom() {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void unsetMoveFrom() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTTrackChange getMoveTo() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            CTTrackChange target = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTTrackChange = target == null ? null : target;
        }
        return cTTrackChange;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public boolean isSetMoveTo() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setMoveTo(CTTrackChange moveTo) {
        generatedSetterHelperImpl(moveTo, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTTrackChange addNewMoveTo() {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void unsetMoveTo() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTString> getRStyleList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda129
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getRStyleArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda130
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setRStyleArray(((Integer) obj).intValue(), (CTString) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda131
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewRStyle(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda132
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeRStyle(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda133
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfRStyleArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTString[] getRStyleArray() {
        return (CTString[]) getXmlObjectArray(PROPERTY_QNAME[4], new CTString[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTString getRStyleArray(int i) {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfRStyleArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setRStyleArray(CTString[] rStyleArray) {
        check_orphaned();
        arraySetterHelper(rStyleArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setRStyleArray(int i, CTString rStyle) {
        generatedSetterHelperImpl(rStyle, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTString insertNewRStyle(int i) {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTString addNewRStyle() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeRStyle(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTFonts> getRFontsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda83
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getRFontsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda84
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setRFontsArray(((Integer) obj).intValue(), (CTFonts) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda85
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewRFonts(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda86
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeRFonts(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda87
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfRFontsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTFonts[] getRFontsArray() {
        return (CTFonts[]) getXmlObjectArray(PROPERTY_QNAME[5], new CTFonts[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTFonts getRFontsArray(int i) {
        CTFonts target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFonts) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfRFontsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setRFontsArray(CTFonts[] rFontsArray) {
        check_orphaned();
        arraySetterHelper(rFontsArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setRFontsArray(int i, CTFonts rFonts) {
        generatedSetterHelperImpl(rFonts, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTFonts insertNewRFonts(int i) {
        CTFonts target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFonts) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTFonts addNewRFonts() {
        CTFonts target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFonts) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeRFonts(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getBList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda178
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getBArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda179
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setBArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda180
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewB(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda181
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeB(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda182
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfBArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getBArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[6], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getBArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfBArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setBArray(CTOnOff[] bArray) {
        check_orphaned();
        arraySetterHelper(bArray, PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setBArray(int i, CTOnOff b) {
        generatedSetterHelperImpl(b, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewB(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewB() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeB(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getBCsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda162
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getBCsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda163
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setBCsArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda164
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewBCs(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda165
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeBCs(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda166
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfBCsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getBCsArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[7], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getBCsArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfBCsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setBCsArray(CTOnOff[] bCsArray) {
        check_orphaned();
        arraySetterHelper(bCsArray, PROPERTY_QNAME[7]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setBCsArray(int i, CTOnOff bCs) {
        generatedSetterHelperImpl(bCs, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewBCs(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewBCs() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeBCs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getIList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda150
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getIArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda161
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setIArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda172
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewI(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda183
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeI(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda194
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfIArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getIArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[8], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getIArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfIArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setIArray(CTOnOff[] iValueArray) {
        check_orphaned();
        arraySetterHelper(iValueArray, PROPERTY_QNAME[8]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setIArray(int i, CTOnOff iValue) {
        generatedSetterHelperImpl(iValue, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewI(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewI() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeI(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getICsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda118
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getICsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda119
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setICsArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda120
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewICs(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda121
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeICs(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda122
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfICsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getICsArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[9], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getICsArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfICsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setICsArray(CTOnOff[] iCsArray) {
        check_orphaned();
        arraySetterHelper(iCsArray, PROPERTY_QNAME[9]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setICsArray(int i, CTOnOff iCs) {
        generatedSetterHelperImpl(iCs, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewICs(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewICs() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeICs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getCapsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda151
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getCapsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda152
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setCapsArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda153
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewCaps(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda154
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeCaps(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda155
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfCapsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getCapsArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[10], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getCapsArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfCapsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setCapsArray(CTOnOff[] capsArray) {
        check_orphaned();
        arraySetterHelper(capsArray, PROPERTY_QNAME[10]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setCapsArray(int i, CTOnOff caps) {
        generatedSetterHelperImpl(caps, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewCaps(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewCaps() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeCaps(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getSmallCapsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getSmallCapsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setSmallCapsArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewSmallCaps(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeSmallCaps(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda65
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfSmallCapsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getSmallCapsArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[11], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getSmallCapsArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfSmallCapsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setSmallCapsArray(CTOnOff[] smallCapsArray) {
        check_orphaned();
        arraySetterHelper(smallCapsArray, PROPERTY_QNAME[11]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setSmallCapsArray(int i, CTOnOff smallCaps) {
        generatedSetterHelperImpl(smallCaps, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewSmallCaps(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewSmallCaps() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeSmallCaps(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getStrikeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getStrikeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setStrikeArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewStrike(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeStrike(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfStrikeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getStrikeArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[12], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getStrikeArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfStrikeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setStrikeArray(CTOnOff[] strikeArray) {
        check_orphaned();
        arraySetterHelper(strikeArray, PROPERTY_QNAME[12]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setStrikeArray(int i, CTOnOff strike) {
        generatedSetterHelperImpl(strike, PROPERTY_QNAME[12], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewStrike(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewStrike() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeStrike(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getDstrikeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda134
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getDstrikeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda135
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setDstrikeArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda136
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewDstrike(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda137
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeDstrike(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda138
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfDstrikeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getDstrikeArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[13], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getDstrikeArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfDstrikeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setDstrikeArray(CTOnOff[] dstrikeArray) {
        check_orphaned();
        arraySetterHelper(dstrikeArray, PROPERTY_QNAME[13]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setDstrikeArray(int i, CTOnOff dstrike) {
        generatedSetterHelperImpl(dstrike, PROPERTY_QNAME[13], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewDstrike(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewDstrike() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeDstrike(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getOutlineList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getOutlineArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setOutlineArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewOutline(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeOutline(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfOutlineArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getOutlineArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[14], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getOutlineArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfOutlineArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setOutlineArray(CTOnOff[] outlineArray) {
        check_orphaned();
        arraySetterHelper(outlineArray, PROPERTY_QNAME[14]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setOutlineArray(int i, CTOnOff outline) {
        generatedSetterHelperImpl(outline, PROPERTY_QNAME[14], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewOutline(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewOutline() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeOutline(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getShadowList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda94
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getShadowArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda95
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setShadowArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda96
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewShadow(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda97
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeShadow(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda98
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfShadowArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getShadowArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[15], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getShadowArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfShadowArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setShadowArray(CTOnOff[] shadowArray) {
        check_orphaned();
        arraySetterHelper(shadowArray, PROPERTY_QNAME[15]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setShadowArray(int i, CTOnOff shadow) {
        generatedSetterHelperImpl(shadow, PROPERTY_QNAME[15], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewShadow(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewShadow() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeShadow(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getEmbossList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda112
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getEmbossArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda113
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setEmbossArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda114
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewEmboss(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda115
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeEmboss(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda116
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfEmbossArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getEmbossArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[16], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getEmbossArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfEmbossArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setEmbossArray(CTOnOff[] embossArray) {
        check_orphaned();
        arraySetterHelper(embossArray, PROPERTY_QNAME[16]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setEmbossArray(int i, CTOnOff emboss) {
        generatedSetterHelperImpl(emboss, PROPERTY_QNAME[16], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewEmboss(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewEmboss() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeEmboss(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getImprintList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getImprintArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setImprintArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewImprint(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeImprint(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfImprintArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getImprintArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[17], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getImprintArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfImprintArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setImprintArray(CTOnOff[] imprintArray) {
        check_orphaned();
        arraySetterHelper(imprintArray, PROPERTY_QNAME[17]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setImprintArray(int i, CTOnOff imprint) {
        generatedSetterHelperImpl(imprint, PROPERTY_QNAME[17], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewImprint(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewImprint() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeImprint(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getNoProofList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getNoProofArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setNoProofArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewNoProof(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeNoProof(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfNoProofArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getNoProofArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[18], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getNoProofArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfNoProofArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setNoProofArray(CTOnOff[] noProofArray) {
        check_orphaned();
        arraySetterHelper(noProofArray, PROPERTY_QNAME[18]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setNoProofArray(int i, CTOnOff noProof) {
        generatedSetterHelperImpl(noProof, PROPERTY_QNAME[18], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewNoProof(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewNoProof() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeNoProof(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getSnapToGridList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getSnapToGridArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setSnapToGridArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewSnapToGrid(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeSnapToGrid(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfSnapToGridArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getSnapToGridArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[19], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getSnapToGridArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfSnapToGridArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setSnapToGridArray(CTOnOff[] snapToGridArray) {
        check_orphaned();
        arraySetterHelper(snapToGridArray, PROPERTY_QNAME[19]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setSnapToGridArray(int i, CTOnOff snapToGrid) {
        generatedSetterHelperImpl(snapToGrid, PROPERTY_QNAME[19], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewSnapToGrid(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewSnapToGrid() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeSnapToGrid(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getVanishList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getVanishArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setVanishArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewVanish(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeVanish(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfVanishArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getVanishArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[20], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getVanishArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[20], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfVanishArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setVanishArray(CTOnOff[] vanishArray) {
        check_orphaned();
        arraySetterHelper(vanishArray, PROPERTY_QNAME[20]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setVanishArray(int i, CTOnOff vanish) {
        generatedSetterHelperImpl(vanish, PROPERTY_QNAME[20], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewVanish(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewVanish() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeVanish(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getWebHiddenList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda67
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getWebHiddenArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda68
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setWebHiddenArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda69
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewWebHidden(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda70
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeWebHidden(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda71
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfWebHiddenArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getWebHiddenArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[21], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getWebHiddenArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfWebHiddenArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setWebHiddenArray(CTOnOff[] webHiddenArray) {
        check_orphaned();
        arraySetterHelper(webHiddenArray, PROPERTY_QNAME[21]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setWebHiddenArray(int i, CTOnOff webHidden) {
        generatedSetterHelperImpl(webHidden, PROPERTY_QNAME[21], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewWebHidden(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewWebHidden() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeWebHidden(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTColor> getColorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda184
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getColorArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda185
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setColorArray(((Integer) obj).intValue(), (CTColor) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda186
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewColor(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda187
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeColor(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda188
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfColorArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTColor[] getColorArray() {
        return (CTColor[]) getXmlObjectArray(PROPERTY_QNAME[22], new CTColor[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTColor getColorArray(int i) {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[22], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfColorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setColorArray(CTColor[] colorArray) {
        check_orphaned();
        arraySetterHelper(colorArray, PROPERTY_QNAME[22]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setColorArray(int i, CTColor color) {
        generatedSetterHelperImpl(color, PROPERTY_QNAME[22], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTColor insertNewColor(int i) {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTColor addNewColor() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeColor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTSignedTwipsMeasure> getSpacingList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda173
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getSpacingArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda174
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setSpacingArray(((Integer) obj).intValue(), (CTSignedTwipsMeasure) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda175
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewSpacing(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda176
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeSpacing(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda177
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfSpacingArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTSignedTwipsMeasure[] getSpacingArray() {
        return (CTSignedTwipsMeasure[]) getXmlObjectArray(PROPERTY_QNAME[23], new CTSignedTwipsMeasure[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTSignedTwipsMeasure getSpacingArray(int i) {
        CTSignedTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSignedTwipsMeasure) get_store().find_element_user(PROPERTY_QNAME[23], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfSpacingArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setSpacingArray(CTSignedTwipsMeasure[] spacingArray) {
        check_orphaned();
        arraySetterHelper(spacingArray, PROPERTY_QNAME[23]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setSpacingArray(int i, CTSignedTwipsMeasure spacing) {
        generatedSetterHelperImpl(spacing, PROPERTY_QNAME[23], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTSignedTwipsMeasure insertNewSpacing(int i) {
        CTSignedTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSignedTwipsMeasure) get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTSignedTwipsMeasure addNewSpacing() {
        CTSignedTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSignedTwipsMeasure) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeSpacing(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTTextScale> getWList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda107
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getWArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda108
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setWArray(((Integer) obj).intValue(), (CTTextScale) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda109
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewW(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda110
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeW(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda111
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfWArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTTextScale[] getWArray() {
        return (CTTextScale[]) getXmlObjectArray(PROPERTY_QNAME[24], new CTTextScale[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTTextScale getWArray(int i) {
        CTTextScale target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextScale) get_store().find_element_user(PROPERTY_QNAME[24], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfWArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setWArray(CTTextScale[] wArray) {
        check_orphaned();
        arraySetterHelper(wArray, PROPERTY_QNAME[24]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setWArray(int i, CTTextScale w) {
        generatedSetterHelperImpl(w, PROPERTY_QNAME[24], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTTextScale insertNewW(int i) {
        CTTextScale target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextScale) get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTTextScale addNewW() {
        CTTextScale target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextScale) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeW(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTHpsMeasure> getKernList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getKernArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setKernArray(((Integer) obj).intValue(), (CTHpsMeasure) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewKern(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeKern(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfKernArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTHpsMeasure[] getKernArray() {
        return (CTHpsMeasure[]) getXmlObjectArray(PROPERTY_QNAME[25], new CTHpsMeasure[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTHpsMeasure getKernArray(int i) {
        CTHpsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHpsMeasure) get_store().find_element_user(PROPERTY_QNAME[25], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfKernArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setKernArray(CTHpsMeasure[] kernArray) {
        check_orphaned();
        arraySetterHelper(kernArray, PROPERTY_QNAME[25]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setKernArray(int i, CTHpsMeasure kern) {
        generatedSetterHelperImpl(kern, PROPERTY_QNAME[25], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTHpsMeasure insertNewKern(int i) {
        CTHpsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHpsMeasure) get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTHpsMeasure addNewKern() {
        CTHpsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHpsMeasure) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeKern(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTSignedHpsMeasure> getPositionList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda167
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getPositionArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda168
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setPositionArray(((Integer) obj).intValue(), (CTSignedHpsMeasure) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda169
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewPosition(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda170
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removePosition(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda171
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfPositionArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTSignedHpsMeasure[] getPositionArray() {
        return (CTSignedHpsMeasure[]) getXmlObjectArray(PROPERTY_QNAME[26], new CTSignedHpsMeasure[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTSignedHpsMeasure getPositionArray(int i) {
        CTSignedHpsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSignedHpsMeasure) get_store().find_element_user(PROPERTY_QNAME[26], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfPositionArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[26]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setPositionArray(CTSignedHpsMeasure[] positionArray) {
        check_orphaned();
        arraySetterHelper(positionArray, PROPERTY_QNAME[26]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setPositionArray(int i, CTSignedHpsMeasure position) {
        generatedSetterHelperImpl(position, PROPERTY_QNAME[26], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTSignedHpsMeasure insertNewPosition(int i) {
        CTSignedHpsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSignedHpsMeasure) get_store().insert_element_user(PROPERTY_QNAME[26], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTSignedHpsMeasure addNewPosition() {
        CTSignedHpsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSignedHpsMeasure) get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removePosition(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTHpsMeasure> getSzList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getSzArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setSzArray(((Integer) obj).intValue(), (CTHpsMeasure) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewSz(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeSz(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfSzArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTHpsMeasure[] getSzArray() {
        return (CTHpsMeasure[]) getXmlObjectArray(PROPERTY_QNAME[27], new CTHpsMeasure[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTHpsMeasure getSzArray(int i) {
        CTHpsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHpsMeasure) get_store().find_element_user(PROPERTY_QNAME[27], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfSzArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[27]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setSzArray(CTHpsMeasure[] szArray) {
        check_orphaned();
        arraySetterHelper(szArray, PROPERTY_QNAME[27]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setSzArray(int i, CTHpsMeasure sz) {
        generatedSetterHelperImpl(sz, PROPERTY_QNAME[27], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTHpsMeasure insertNewSz(int i) {
        CTHpsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHpsMeasure) get_store().insert_element_user(PROPERTY_QNAME[27], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTHpsMeasure addNewSz() {
        CTHpsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHpsMeasure) get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeSz(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTHpsMeasure> getSzCsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getSzCsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setSzCsArray(((Integer) obj).intValue(), (CTHpsMeasure) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewSzCs(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeSzCs(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfSzCsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTHpsMeasure[] getSzCsArray() {
        return (CTHpsMeasure[]) getXmlObjectArray(PROPERTY_QNAME[28], new CTHpsMeasure[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTHpsMeasure getSzCsArray(int i) {
        CTHpsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHpsMeasure) get_store().find_element_user(PROPERTY_QNAME[28], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfSzCsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[28]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setSzCsArray(CTHpsMeasure[] szCsArray) {
        check_orphaned();
        arraySetterHelper(szCsArray, PROPERTY_QNAME[28]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setSzCsArray(int i, CTHpsMeasure szCs) {
        generatedSetterHelperImpl(szCs, PROPERTY_QNAME[28], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTHpsMeasure insertNewSzCs(int i) {
        CTHpsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHpsMeasure) get_store().insert_element_user(PROPERTY_QNAME[28], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTHpsMeasure addNewSzCs() {
        CTHpsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHpsMeasure) get_store().add_element_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeSzCs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTHighlight> getHighlightList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda72
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getHighlightArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda73
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setHighlightArray(((Integer) obj).intValue(), (CTHighlight) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda74
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewHighlight(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda75
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeHighlight(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda76
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfHighlightArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTHighlight[] getHighlightArray() {
        return (CTHighlight[]) getXmlObjectArray(PROPERTY_QNAME[29], new CTHighlight[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTHighlight getHighlightArray(int i) {
        CTHighlight target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHighlight) get_store().find_element_user(PROPERTY_QNAME[29], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfHighlightArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[29]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setHighlightArray(CTHighlight[] highlightArray) {
        check_orphaned();
        arraySetterHelper(highlightArray, PROPERTY_QNAME[29]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setHighlightArray(int i, CTHighlight highlight) {
        generatedSetterHelperImpl(highlight, PROPERTY_QNAME[29], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTHighlight insertNewHighlight(int i) {
        CTHighlight target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHighlight) get_store().insert_element_user(PROPERTY_QNAME[29], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTHighlight addNewHighlight() {
        CTHighlight target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHighlight) get_store().add_element_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeHighlight(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTUnderline> getUList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getUArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setUArray(((Integer) obj).intValue(), (CTUnderline) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewU(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeU(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfUArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTUnderline[] getUArray() {
        return (CTUnderline[]) getXmlObjectArray(PROPERTY_QNAME[30], new CTUnderline[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTUnderline getUArray(int i) {
        CTUnderline target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTUnderline) get_store().find_element_user(PROPERTY_QNAME[30], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfUArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[30]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setUArray(CTUnderline[] uArray) {
        check_orphaned();
        arraySetterHelper(uArray, PROPERTY_QNAME[30]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setUArray(int i, CTUnderline u) {
        generatedSetterHelperImpl(u, PROPERTY_QNAME[30], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTUnderline insertNewU(int i) {
        CTUnderline target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTUnderline) get_store().insert_element_user(PROPERTY_QNAME[30], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTUnderline addNewU() {
        CTUnderline target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTUnderline) get_store().add_element_user(PROPERTY_QNAME[30]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeU(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[30], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTTextEffect> getEffectList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda140
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getEffectArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda141
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setEffectArray(((Integer) obj).intValue(), (CTTextEffect) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda142
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewEffect(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda143
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeEffect(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda144
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfEffectArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTTextEffect[] getEffectArray() {
        return getXmlObjectArray(PROPERTY_QNAME[31], (XmlObject[]) new CTTextEffect[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTTextEffect getEffectArray(int i) {
        CTTextEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[31], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfEffectArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[31]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setEffectArray(CTTextEffect[] effectArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) effectArray, PROPERTY_QNAME[31]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setEffectArray(int i, CTTextEffect effect) {
        generatedSetterHelperImpl(effect, PROPERTY_QNAME[31], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTTextEffect insertNewEffect(int i) {
        CTTextEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[31], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTTextEffect addNewEffect() {
        CTTextEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[31]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeEffect(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[31], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTBorder> getBdrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda78
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getBdrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda79
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setBdrArray(((Integer) obj).intValue(), (CTBorder) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda80
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewBdr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda81
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeBdr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda82
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfBdrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTBorder[] getBdrArray() {
        return (CTBorder[]) getXmlObjectArray(PROPERTY_QNAME[32], new CTBorder[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTBorder getBdrArray(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[32], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfBdrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[32]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setBdrArray(CTBorder[] bdrArray) {
        check_orphaned();
        arraySetterHelper(bdrArray, PROPERTY_QNAME[32]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setBdrArray(int i, CTBorder bdr) {
        generatedSetterHelperImpl(bdr, PROPERTY_QNAME[32], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTBorder insertNewBdr(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().insert_element_user(PROPERTY_QNAME[32], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTBorder addNewBdr() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[32]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeBdr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[32], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTShd> getShdList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda123
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getShdArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda124
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setShdArray(((Integer) obj).intValue(), (CTShd) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda125
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewShd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda126
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeShd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda127
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfShdArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTShd[] getShdArray() {
        return (CTShd[]) getXmlObjectArray(PROPERTY_QNAME[33], new CTShd[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTShd getShdArray(int i) {
        CTShd target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShd) get_store().find_element_user(PROPERTY_QNAME[33], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfShdArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[33]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setShdArray(CTShd[] shdArray) {
        check_orphaned();
        arraySetterHelper(shdArray, PROPERTY_QNAME[33]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setShdArray(int i, CTShd shd) {
        generatedSetterHelperImpl(shd, PROPERTY_QNAME[33], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTShd insertNewShd(int i) {
        CTShd target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShd) get_store().insert_element_user(PROPERTY_QNAME[33], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTShd addNewShd() {
        CTShd target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShd) get_store().add_element_user(PROPERTY_QNAME[33]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeShd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[33], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTFitText> getFitTextList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getFitTextArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setFitTextArray(((Integer) obj).intValue(), (CTFitText) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewFitText(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeFitText(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfFitTextArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTFitText[] getFitTextArray() {
        return (CTFitText[]) getXmlObjectArray(PROPERTY_QNAME[34], new CTFitText[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTFitText getFitTextArray(int i) {
        CTFitText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFitText) get_store().find_element_user(PROPERTY_QNAME[34], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfFitTextArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[34]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setFitTextArray(CTFitText[] fitTextArray) {
        check_orphaned();
        arraySetterHelper(fitTextArray, PROPERTY_QNAME[34]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setFitTextArray(int i, CTFitText fitText) {
        generatedSetterHelperImpl(fitText, PROPERTY_QNAME[34], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTFitText insertNewFitText(int i) {
        CTFitText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFitText) get_store().insert_element_user(PROPERTY_QNAME[34], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTFitText addNewFitText() {
        CTFitText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFitText) get_store().add_element_user(PROPERTY_QNAME[34]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeFitText(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[34], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTVerticalAlignRun> getVertAlignList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getVertAlignArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setVertAlignArray(((Integer) obj).intValue(), (CTVerticalAlignRun) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewVertAlign(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeVertAlign(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda60
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfVertAlignArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTVerticalAlignRun[] getVertAlignArray() {
        return (CTVerticalAlignRun[]) getXmlObjectArray(PROPERTY_QNAME[35], new CTVerticalAlignRun[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTVerticalAlignRun getVertAlignArray(int i) {
        CTVerticalAlignRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTVerticalAlignRun) get_store().find_element_user(PROPERTY_QNAME[35], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfVertAlignArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[35]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setVertAlignArray(CTVerticalAlignRun[] vertAlignArray) {
        check_orphaned();
        arraySetterHelper(vertAlignArray, PROPERTY_QNAME[35]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setVertAlignArray(int i, CTVerticalAlignRun vertAlign) {
        generatedSetterHelperImpl(vertAlign, PROPERTY_QNAME[35], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTVerticalAlignRun insertNewVertAlign(int i) {
        CTVerticalAlignRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTVerticalAlignRun) get_store().insert_element_user(PROPERTY_QNAME[35], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTVerticalAlignRun addNewVertAlign() {
        CTVerticalAlignRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTVerticalAlignRun) get_store().add_element_user(PROPERTY_QNAME[35]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeVertAlign(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[35], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getRtlList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda156
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getRtlArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda157
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setRtlArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda158
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewRtl(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda159
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeRtl(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda160
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfRtlArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getRtlArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[36], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getRtlArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[36], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfRtlArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[36]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setRtlArray(CTOnOff[] rtlArray) {
        check_orphaned();
        arraySetterHelper(rtlArray, PROPERTY_QNAME[36]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setRtlArray(int i, CTOnOff rtl) {
        generatedSetterHelperImpl(rtl, PROPERTY_QNAME[36], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewRtl(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[36], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewRtl() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[36]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeRtl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[36], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getCsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda145
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getCsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda146
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setCsArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda147
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewCs(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda148
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeCs(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda149
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfCsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getCsArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[37], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getCsArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[37], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfCsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[37]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setCsArray(CTOnOff[] csArray) {
        check_orphaned();
        arraySetterHelper(csArray, PROPERTY_QNAME[37]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setCsArray(int i, CTOnOff cs) {
        generatedSetterHelperImpl(cs, PROPERTY_QNAME[37], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewCs(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[37], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewCs() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[37]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeCs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[37], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTEm> getEmList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda100
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getEmArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda101
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setEmArray(((Integer) obj).intValue(), (CTEm) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda102
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewEm(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda103
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeEm(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda104
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfEmArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTEm[] getEmArray() {
        return (CTEm[]) getXmlObjectArray(PROPERTY_QNAME[38], new CTEm[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTEm getEmArray(int i) {
        CTEm target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEm) get_store().find_element_user(PROPERTY_QNAME[38], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfEmArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[38]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setEmArray(CTEm[] emArray) {
        check_orphaned();
        arraySetterHelper(emArray, PROPERTY_QNAME[38]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setEmArray(int i, CTEm em) {
        generatedSetterHelperImpl(em, PROPERTY_QNAME[38], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTEm insertNewEm(int i) {
        CTEm target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEm) get_store().insert_element_user(PROPERTY_QNAME[38], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTEm addNewEm() {
        CTEm target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEm) get_store().add_element_user(PROPERTY_QNAME[38]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeEm(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[38], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTLanguage> getLangList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda89
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getLangArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda90
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setLangArray(((Integer) obj).intValue(), (CTLanguage) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda91
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewLang(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda92
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeLang(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda93
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfLangArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTLanguage[] getLangArray() {
        return (CTLanguage[]) getXmlObjectArray(PROPERTY_QNAME[39], new CTLanguage[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTLanguage getLangArray(int i) {
        CTLanguage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLanguage) get_store().find_element_user(PROPERTY_QNAME[39], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfLangArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[39]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setLangArray(CTLanguage[] langArray) {
        check_orphaned();
        arraySetterHelper(langArray, PROPERTY_QNAME[39]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setLangArray(int i, CTLanguage lang) {
        generatedSetterHelperImpl(lang, PROPERTY_QNAME[39], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTLanguage insertNewLang(int i) {
        CTLanguage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLanguage) get_store().insert_element_user(PROPERTY_QNAME[39], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTLanguage addNewLang() {
        CTLanguage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLanguage) get_store().add_element_user(PROPERTY_QNAME[39]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeLang(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[39], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTEastAsianLayout> getEastAsianLayoutList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda189
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getEastAsianLayoutArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda190
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setEastAsianLayoutArray(((Integer) obj).intValue(), (CTEastAsianLayout) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda191
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewEastAsianLayout(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda192
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeEastAsianLayout(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda193
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfEastAsianLayoutArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTEastAsianLayout[] getEastAsianLayoutArray() {
        return getXmlObjectArray(PROPERTY_QNAME[40], (XmlObject[]) new CTEastAsianLayout[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTEastAsianLayout getEastAsianLayoutArray(int i) {
        CTEastAsianLayout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[40], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfEastAsianLayoutArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[40]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setEastAsianLayoutArray(CTEastAsianLayout[] eastAsianLayoutArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) eastAsianLayoutArray, PROPERTY_QNAME[40]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setEastAsianLayoutArray(int i, CTEastAsianLayout eastAsianLayout) {
        generatedSetterHelperImpl(eastAsianLayout, PROPERTY_QNAME[40], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTEastAsianLayout insertNewEastAsianLayout(int i) {
        CTEastAsianLayout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[40], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTEastAsianLayout addNewEastAsianLayout() {
        CTEastAsianLayout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[40]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeEastAsianLayout(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[40], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getSpecVanishList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getSpecVanishArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda106
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setSpecVanishArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda117
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewSpecVanish(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda128
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeSpecVanish(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda139
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfSpecVanishArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getSpecVanishArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[41], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getSpecVanishArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[41], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfSpecVanishArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[41]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setSpecVanishArray(CTOnOff[] specVanishArray) {
        check_orphaned();
        arraySetterHelper(specVanishArray, PROPERTY_QNAME[41]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setSpecVanishArray(int i, CTOnOff specVanish) {
        generatedSetterHelperImpl(specVanish, PROPERTY_QNAME[41], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewSpecVanish(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[41], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewSpecVanish() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[41]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeSpecVanish(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[41], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public List<CTOnOff> getOMathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda66
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.getOMathArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda77
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTParaRPrOriginalImpl.this.setOMathArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda88
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTParaRPrOriginalImpl.this.insertNewOMath(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda99
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTParaRPrOriginalImpl.this.removeOMath(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTParaRPrOriginalImpl$$ExternalSyntheticLambda105
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTParaRPrOriginalImpl.this.sizeOfOMathArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff[] getOMathArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[42], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff getOMathArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[42], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public int sizeOfOMathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[42]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setOMathArray(CTOnOff[] oMathArray) {
        check_orphaned();
        arraySetterHelper(oMathArray, PROPERTY_QNAME[42]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void setOMathArray(int i, CTOnOff oMath) {
        generatedSetterHelperImpl(oMath, PROPERTY_QNAME[42], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff insertNewOMath(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[42], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public CTOnOff addNewOMath() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[42]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal
    public void removeOMath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[42], i);
        }
    }
}
