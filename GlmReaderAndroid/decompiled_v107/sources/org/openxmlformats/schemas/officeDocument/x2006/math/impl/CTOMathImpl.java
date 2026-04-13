package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

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
import org.openxmlformats.schemas.officeDocument.x2006.math.CTAcc;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTBar;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTBorderBox;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTBox;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTD;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArr;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTF;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTFunc;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTGroupChr;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTLimLow;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTLimUpp;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTM;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTNary;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTPhant;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTR;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTRad;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSPre;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSSubSup;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSSup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPermStart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSmartTagRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;

/* loaded from: classes11.dex */
public class CTOMathImpl extends XmlComplexContentImpl implements CTOMath {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "acc"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "bar"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "box"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "borderBox"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "d"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "eqArr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "f"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "func"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "groupChr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "limLow"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "limUpp"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "m"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "nary"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "phant"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "rad"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "sPre"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "sSub"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "sSubSup"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "sSup"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "r"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXml"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "fldSimple"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hyperlink"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "smartTag"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "sdt"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "proofErr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "permStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "permEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bookmarkStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bookmarkEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveFromRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveFromRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveToRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveToRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "commentRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "commentRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlInsRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlInsRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlDelRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlDelRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlMoveFromRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlMoveFromRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlMoveToRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlMoveToRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "ins"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "del"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveFrom"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveTo"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMathPara"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMath")};
    private static final long serialVersionUID = 1;

    public CTOMathImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTAcc> getAccList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda178
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getAccArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda179
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setAccArray(((Integer) obj).intValue(), (CTAcc) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda180
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewAcc(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda181
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeAcc(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda182
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfAccArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTAcc[] getAccArray() {
        return getXmlObjectArray(PROPERTY_QNAME[0], (XmlObject[]) new CTAcc[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTAcc getAccArray(int i) {
        CTAcc target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfAccArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setAccArray(CTAcc[] accArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) accArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setAccArray(int i, CTAcc acc) {
        generatedSetterHelperImpl(acc, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTAcc insertNewAcc(int i) {
        CTAcc target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTAcc addNewAcc() {
        CTAcc target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeAcc(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTBar> getBarList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda94
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getBarArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda95
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setBarArray(((Integer) obj).intValue(), (CTBar) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda96
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewBar(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda97
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeBar(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda98
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfBarArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTBar[] getBarArray() {
        return getXmlObjectArray(PROPERTY_QNAME[1], (XmlObject[]) new CTBar[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTBar getBarArray(int i) {
        CTBar target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfBarArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setBarArray(CTBar[] barArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) barArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setBarArray(int i, CTBar bar) {
        generatedSetterHelperImpl(bar, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTBar insertNewBar(int i) {
        CTBar target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTBar addNewBar() {
        CTBar target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeBar(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTBox> getBoxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getBoxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setBoxArray(((Integer) obj).intValue(), (CTBox) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewBox(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeBox(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfBoxArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTBox[] getBoxArray() {
        return getXmlObjectArray(PROPERTY_QNAME[2], (XmlObject[]) new CTBox[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTBox getBoxArray(int i) {
        CTBox target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfBoxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setBoxArray(CTBox[] boxArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) boxArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setBoxArray(int i, CTBox box) {
        generatedSetterHelperImpl(box, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTBox insertNewBox(int i) {
        CTBox target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTBox addNewBox() {
        CTBox target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeBox(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTBorderBox> getBorderBoxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda173
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getBorderBoxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda174
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setBorderBoxArray(((Integer) obj).intValue(), (CTBorderBox) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda175
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewBorderBox(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda176
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeBorderBox(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda177
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfBorderBoxArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTBorderBox[] getBorderBoxArray() {
        return getXmlObjectArray(PROPERTY_QNAME[3], (XmlObject[]) new CTBorderBox[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTBorderBox getBorderBoxArray(int i) {
        CTBorderBox target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfBorderBoxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setBorderBoxArray(CTBorderBox[] borderBoxArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) borderBoxArray, PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setBorderBoxArray(int i, CTBorderBox borderBox) {
        generatedSetterHelperImpl(borderBox, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTBorderBox insertNewBorderBox(int i) {
        CTBorderBox target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTBorderBox addNewBorderBox() {
        CTBorderBox target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeBorderBox(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTD> getDList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda222
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getDArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda223
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setDArray(((Integer) obj).intValue(), (CTD) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda224
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewD(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda225
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeD(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda226
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfDArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTD[] getDArray() {
        return (CTD[]) getXmlObjectArray(PROPERTY_QNAME[4], new CTD[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTD getDArray(int i) {
        CTD target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTD) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfDArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setDArray(CTD[] dArray) {
        check_orphaned();
        arraySetterHelper(dArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setDArray(int i, CTD d) {
        generatedSetterHelperImpl(d, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTD insertNewD(int i) {
        CTD target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTD) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTD addNewD() {
        CTD target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTD) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeD(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTEqArr> getEqArrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda189
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getEqArrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda190
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setEqArrArray(((Integer) obj).intValue(), (CTEqArr) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda191
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewEqArr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda192
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeEqArr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda193
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfEqArrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTEqArr[] getEqArrArray() {
        return getXmlObjectArray(PROPERTY_QNAME[5], (XmlObject[]) new CTEqArr[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTEqArr getEqArrArray(int i) {
        CTEqArr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfEqArrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setEqArrArray(CTEqArr[] eqArrArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) eqArrArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setEqArrArray(int i, CTEqArr eqArr) {
        generatedSetterHelperImpl(eqArr, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTEqArr insertNewEqArr(int i) {
        CTEqArr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTEqArr addNewEqArr() {
        CTEqArr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeEqArr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTF> getFList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda105
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getFArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda106
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setFArray(((Integer) obj).intValue(), (CTF) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda107
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewF(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda108
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeF(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda109
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfFArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTF[] getFArray() {
        return getXmlObjectArray(PROPERTY_QNAME[6], (XmlObject[]) new CTF[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTF getFArray(int i) {
        CTF target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfFArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setFArray(CTF[] fArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) fArray, PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setFArray(int i, CTF f) {
        generatedSetterHelperImpl(f, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTF insertNewF(int i) {
        CTF target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTF addNewF() {
        CTF target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeF(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTFunc> getFuncList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda239
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getFuncArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda240
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setFuncArray(((Integer) obj).intValue(), (CTFunc) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda241
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewFunc(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda242
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeFunc(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda243
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfFuncArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTFunc[] getFuncArray() {
        return getXmlObjectArray(PROPERTY_QNAME[7], (XmlObject[]) new CTFunc[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTFunc getFuncArray(int i) {
        CTFunc target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfFuncArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setFuncArray(CTFunc[] funcArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) funcArray, PROPERTY_QNAME[7]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setFuncArray(int i, CTFunc func) {
        generatedSetterHelperImpl(func, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTFunc insertNewFunc(int i) {
        CTFunc target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTFunc addNewFunc() {
        CTFunc target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeFunc(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTGroupChr> getGroupChrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda217
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getGroupChrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda218
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setGroupChrArray(((Integer) obj).intValue(), (CTGroupChr) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda219
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewGroupChr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda220
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeGroupChr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda221
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfGroupChrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTGroupChr[] getGroupChrArray() {
        return getXmlObjectArray(PROPERTY_QNAME[8], (XmlObject[]) new CTGroupChr[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTGroupChr getGroupChrArray(int i) {
        CTGroupChr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfGroupChrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setGroupChrArray(CTGroupChr[] groupChrArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) groupChrArray, PROPERTY_QNAME[8]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setGroupChrArray(int i, CTGroupChr groupChr) {
        generatedSetterHelperImpl(groupChr, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTGroupChr insertNewGroupChr(int i) {
        CTGroupChr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTGroupChr addNewGroupChr() {
        CTGroupChr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeGroupChr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTLimLow> getLimLowList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda100
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getLimLowArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda101
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setLimLowArray(((Integer) obj).intValue(), (CTLimLow) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda102
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewLimLow(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda103
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeLimLow(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda104
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfLimLowArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTLimLow[] getLimLowArray() {
        return getXmlObjectArray(PROPERTY_QNAME[9], (XmlObject[]) new CTLimLow[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTLimLow getLimLowArray(int i) {
        CTLimLow target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfLimLowArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setLimLowArray(CTLimLow[] limLowArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) limLowArray, PROPERTY_QNAME[9]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setLimLowArray(int i, CTLimLow limLow) {
        generatedSetterHelperImpl(limLow, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTLimLow insertNewLimLow(int i) {
        CTLimLow target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTLimLow addNewLimLow() {
        CTLimLow target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeLimLow(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTLimUpp> getLimUppList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda161
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getLimUppArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda162
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setLimUppArray(((Integer) obj).intValue(), (CTLimUpp) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda163
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewLimUpp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda164
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeLimUpp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda165
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfLimUppArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTLimUpp[] getLimUppArray() {
        return getXmlObjectArray(PROPERTY_QNAME[10], (XmlObject[]) new CTLimUpp[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTLimUpp getLimUppArray(int i) {
        CTLimUpp target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfLimUppArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setLimUppArray(CTLimUpp[] limUppArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) limUppArray, PROPERTY_QNAME[10]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setLimUppArray(int i, CTLimUpp limUpp) {
        generatedSetterHelperImpl(limUpp, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTLimUpp insertNewLimUpp(int i) {
        CTLimUpp target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTLimUpp addNewLimUpp() {
        CTLimUpp target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeLimUpp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTM> getMList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getMArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setMArray(((Integer) obj).intValue(), (CTM) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewM(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeM(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfMArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTM[] getMArray() {
        return (CTM[]) getXmlObjectArray(PROPERTY_QNAME[11], new CTM[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTM getMArray(int i) {
        CTM target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTM) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfMArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setMArray(CTM[] mArray) {
        check_orphaned();
        arraySetterHelper(mArray, PROPERTY_QNAME[11]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setMArray(int i, CTM m) {
        generatedSetterHelperImpl(m, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTM insertNewM(int i) {
        CTM target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTM) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTM addNewM() {
        CTM target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTM) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeM(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTNary> getNaryList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda78
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getNaryArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda79
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setNaryArray(((Integer) obj).intValue(), (CTNary) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda80
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewNary(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda81
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeNary(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda82
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfNaryArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTNary[] getNaryArray() {
        return getXmlObjectArray(PROPERTY_QNAME[12], (XmlObject[]) new CTNary[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTNary getNaryArray(int i) {
        CTNary target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfNaryArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setNaryArray(CTNary[] naryArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) naryArray, PROPERTY_QNAME[12]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setNaryArray(int i, CTNary nary) {
        generatedSetterHelperImpl(nary, PROPERTY_QNAME[12], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTNary insertNewNary(int i) {
        CTNary target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTNary addNewNary() {
        CTNary target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeNary(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTPhant> getPhantList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getPhantArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setPhantArray(((Integer) obj).intValue(), (CTPhant) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewPhant(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removePhant(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfPhantArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTPhant[] getPhantArray() {
        return getXmlObjectArray(PROPERTY_QNAME[13], (XmlObject[]) new CTPhant[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTPhant getPhantArray(int i) {
        CTPhant target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfPhantArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setPhantArray(CTPhant[] phantArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) phantArray, PROPERTY_QNAME[13]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setPhantArray(int i, CTPhant phant) {
        generatedSetterHelperImpl(phant, PROPERTY_QNAME[13], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTPhant insertNewPhant(int i) {
        CTPhant target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTPhant addNewPhant() {
        CTPhant target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removePhant(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTRad> getRadList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getRadArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setRadArray(((Integer) obj).intValue(), (CTRad) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewRad(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeRad(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfRadArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRad[] getRadArray() {
        return getXmlObjectArray(PROPERTY_QNAME[14], (XmlObject[]) new CTRad[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRad getRadArray(int i) {
        CTRad target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfRadArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setRadArray(CTRad[] radArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) radArray, PROPERTY_QNAME[14]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setRadArray(int i, CTRad rad) {
        generatedSetterHelperImpl(rad, PROPERTY_QNAME[14], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRad insertNewRad(int i) {
        CTRad target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRad addNewRad() {
        CTRad target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeRad(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTSPre> getSPreList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda145
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getSPreArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda146
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setSPreArray(((Integer) obj).intValue(), (CTSPre) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda147
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewSPre(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda148
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeSPre(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda149
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfSPreArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSPre[] getSPreArray() {
        return getXmlObjectArray(PROPERTY_QNAME[15], (XmlObject[]) new CTSPre[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSPre getSPreArray(int i) {
        CTSPre target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfSPreArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setSPreArray(CTSPre[] sPreArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) sPreArray, PROPERTY_QNAME[15]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setSPreArray(int i, CTSPre sPre) {
        generatedSetterHelperImpl(sPre, PROPERTY_QNAME[15], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSPre insertNewSPre(int i) {
        CTSPre target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSPre addNewSPre() {
        CTSPre target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeSPre(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTSSub> getSSubList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda228
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getSSubArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda229
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setSSubArray(((Integer) obj).intValue(), (CTSSub) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda230
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewSSub(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda231
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeSSub(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda232
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfSSubArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSSub[] getSSubArray() {
        return (CTSSub[]) getXmlObjectArray(PROPERTY_QNAME[16], new CTSSub[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSSub getSSubArray(int i) {
        CTSSub target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSSub) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfSSubArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setSSubArray(CTSSub[] sSubArray) {
        check_orphaned();
        arraySetterHelper(sSubArray, PROPERTY_QNAME[16]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setSSubArray(int i, CTSSub sSub) {
        generatedSetterHelperImpl(sSub, PROPERTY_QNAME[16], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSSub insertNewSSub(int i) {
        CTSSub target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSSub) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSSub addNewSSub() {
        CTSSub target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSSub) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeSSub(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTSSubSup> getSSubSupList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda112
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getSSubSupArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda113
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setSSubSupArray(((Integer) obj).intValue(), (CTSSubSup) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda114
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewSSubSup(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda115
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeSSubSup(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda116
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfSSubSupArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSSubSup[] getSSubSupArray() {
        return getXmlObjectArray(PROPERTY_QNAME[17], (XmlObject[]) new CTSSubSup[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSSubSup getSSubSupArray(int i) {
        CTSSubSup target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfSSubSupArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setSSubSupArray(CTSSubSup[] sSubSupArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) sSubSupArray, PROPERTY_QNAME[17]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setSSubSupArray(int i, CTSSubSup sSubSup) {
        generatedSetterHelperImpl(sSubSup, PROPERTY_QNAME[17], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSSubSup insertNewSSubSup(int i) {
        CTSSubSup target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSSubSup addNewSSubSup() {
        CTSSubSup target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeSSubSup(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTSSup> getSSupList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda167
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getSSupArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda168
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setSSupArray(((Integer) obj).intValue(), (CTSSup) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda169
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewSSup(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda170
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeSSup(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda171
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfSSupArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSSup[] getSSupArray() {
        return getXmlObjectArray(PROPERTY_QNAME[18], (XmlObject[]) new CTSSup[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSSup getSSupArray(int i) {
        CTSSup target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfSSupArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setSSupArray(CTSSup[] sSupArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) sSupArray, PROPERTY_QNAME[18]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setSSupArray(int i, CTSSup sSup) {
        generatedSetterHelperImpl(sSup, PROPERTY_QNAME[18], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSSup insertNewSSup(int i) {
        CTSSup target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSSup addNewSSup() {
        CTSSup target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeSSup(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTR> getRList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda117
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getRArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda118
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setRArray(((Integer) obj).intValue(), (CTR) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda119
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewR(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda120
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeR(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda121
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfRArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTR[] getRArray() {
        return (CTR[]) getXmlObjectArray(PROPERTY_QNAME[19], new CTR[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTR getRArray(int i) {
        CTR target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTR) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfRArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setRArray(CTR[] rArray) {
        check_orphaned();
        arraySetterHelper(rArray, PROPERTY_QNAME[19]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setRArray(int i, CTR r) {
        generatedSetterHelperImpl(r, PROPERTY_QNAME[19], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTR insertNewR(int i) {
        CTR target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTR) get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTR addNewR() {
        CTR target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTR) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeR(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTCustomXmlRun> getCustomXmlList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getCustomXmlArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda111
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setCustomXmlArray(((Integer) obj).intValue(), (CTCustomXmlRun) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda172
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewCustomXml(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda183
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeCustomXml(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda194
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfCustomXmlArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTCustomXmlRun[] getCustomXmlArray() {
        return (CTCustomXmlRun[]) getXmlObjectArray(PROPERTY_QNAME[20], new CTCustomXmlRun[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTCustomXmlRun getCustomXmlArray(int i) {
        CTCustomXmlRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCustomXmlRun) get_store().find_element_user(PROPERTY_QNAME[20], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfCustomXmlArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCustomXmlArray(CTCustomXmlRun[] customXmlArray) {
        check_orphaned();
        arraySetterHelper(customXmlArray, PROPERTY_QNAME[20]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCustomXmlArray(int i, CTCustomXmlRun customXml) {
        generatedSetterHelperImpl(customXml, PROPERTY_QNAME[20], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTCustomXmlRun insertNewCustomXml(int i) {
        CTCustomXmlRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCustomXmlRun) get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTCustomXmlRun addNewCustomXml() {
        CTCustomXmlRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCustomXmlRun) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeCustomXml(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTSimpleField> getFldSimpleList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getFldSimpleArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setFldSimpleArray(((Integer) obj).intValue(), (CTSimpleField) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewFldSimple(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeFldSimple(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfFldSimpleArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSimpleField[] getFldSimpleArray() {
        return (CTSimpleField[]) getXmlObjectArray(PROPERTY_QNAME[21], new CTSimpleField[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSimpleField getFldSimpleArray(int i) {
        CTSimpleField target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSimpleField) get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfFldSimpleArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setFldSimpleArray(CTSimpleField[] fldSimpleArray) {
        check_orphaned();
        arraySetterHelper(fldSimpleArray, PROPERTY_QNAME[21]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setFldSimpleArray(int i, CTSimpleField fldSimple) {
        generatedSetterHelperImpl(fldSimple, PROPERTY_QNAME[21], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSimpleField insertNewFldSimple(int i) {
        CTSimpleField target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSimpleField) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSimpleField addNewFldSimple() {
        CTSimpleField target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSimpleField) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeFldSimple(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTHyperlink> getHyperlinkList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda66
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getHyperlinkArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda77
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setHyperlinkArray(((Integer) obj).intValue(), (CTHyperlink) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda88
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewHyperlink(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda99
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeHyperlink(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda110
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfHyperlinkArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTHyperlink[] getHyperlinkArray() {
        return (CTHyperlink[]) getXmlObjectArray(PROPERTY_QNAME[22], new CTHyperlink[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTHyperlink getHyperlinkArray(int i) {
        CTHyperlink target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHyperlink) get_store().find_element_user(PROPERTY_QNAME[22], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfHyperlinkArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setHyperlinkArray(CTHyperlink[] hyperlinkArray) {
        check_orphaned();
        arraySetterHelper(hyperlinkArray, PROPERTY_QNAME[22]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setHyperlinkArray(int i, CTHyperlink hyperlink) {
        generatedSetterHelperImpl(hyperlink, PROPERTY_QNAME[22], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTHyperlink insertNewHyperlink(int i) {
        CTHyperlink target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHyperlink) get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTHyperlink addNewHyperlink() {
        CTHyperlink target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHyperlink) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeHyperlink(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTSmartTagRun> getSmartTagList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda123
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getSmartTagArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda124
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setSmartTagArray(((Integer) obj).intValue(), (CTSmartTagRun) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda125
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewSmartTag(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda126
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeSmartTag(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda127
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfSmartTagArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSmartTagRun[] getSmartTagArray() {
        return (CTSmartTagRun[]) getXmlObjectArray(PROPERTY_QNAME[23], new CTSmartTagRun[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSmartTagRun getSmartTagArray(int i) {
        CTSmartTagRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSmartTagRun) get_store().find_element_user(PROPERTY_QNAME[23], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfSmartTagArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setSmartTagArray(CTSmartTagRun[] smartTagArray) {
        check_orphaned();
        arraySetterHelper(smartTagArray, PROPERTY_QNAME[23]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setSmartTagArray(int i, CTSmartTagRun smartTag) {
        generatedSetterHelperImpl(smartTag, PROPERTY_QNAME[23], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSmartTagRun insertNewSmartTag(int i) {
        CTSmartTagRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSmartTagRun) get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSmartTagRun addNewSmartTag() {
        CTSmartTagRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSmartTagRun) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeSmartTag(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTSdtRun> getSdtList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda83
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getSdtArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda84
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setSdtArray(((Integer) obj).intValue(), (CTSdtRun) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda85
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewSdt(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda86
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeSdt(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda87
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfSdtArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSdtRun[] getSdtArray() {
        return (CTSdtRun[]) getXmlObjectArray(PROPERTY_QNAME[24], new CTSdtRun[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSdtRun getSdtArray(int i) {
        CTSdtRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtRun) get_store().find_element_user(PROPERTY_QNAME[24], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfSdtArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setSdtArray(CTSdtRun[] sdtArray) {
        check_orphaned();
        arraySetterHelper(sdtArray, PROPERTY_QNAME[24]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setSdtArray(int i, CTSdtRun sdt) {
        generatedSetterHelperImpl(sdt, PROPERTY_QNAME[24], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSdtRun insertNewSdt(int i) {
        CTSdtRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtRun) get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTSdtRun addNewSdt() {
        CTSdtRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtRun) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeSdt(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTProofErr> getProofErrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getProofErrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setProofErrArray(((Integer) obj).intValue(), (CTProofErr) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewProofErr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeProofErr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfProofErrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTProofErr[] getProofErrArray() {
        return (CTProofErr[]) getXmlObjectArray(PROPERTY_QNAME[25], new CTProofErr[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTProofErr getProofErrArray(int i) {
        CTProofErr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTProofErr) get_store().find_element_user(PROPERTY_QNAME[25], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfProofErrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setProofErrArray(CTProofErr[] proofErrArray) {
        check_orphaned();
        arraySetterHelper(proofErrArray, PROPERTY_QNAME[25]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setProofErrArray(int i, CTProofErr proofErr) {
        generatedSetterHelperImpl(proofErr, PROPERTY_QNAME[25], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTProofErr insertNewProofErr(int i) {
        CTProofErr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTProofErr) get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTProofErr addNewProofErr() {
        CTProofErr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTProofErr) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeProofErr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTPermStart> getPermStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda134
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getPermStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda135
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setPermStartArray(((Integer) obj).intValue(), (CTPermStart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda136
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewPermStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda137
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removePermStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda138
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfPermStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTPermStart[] getPermStartArray() {
        return (CTPermStart[]) getXmlObjectArray(PROPERTY_QNAME[26], new CTPermStart[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTPermStart getPermStartArray(int i) {
        CTPermStart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPermStart) get_store().find_element_user(PROPERTY_QNAME[26], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfPermStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[26]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setPermStartArray(CTPermStart[] permStartArray) {
        check_orphaned();
        arraySetterHelper(permStartArray, PROPERTY_QNAME[26]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setPermStartArray(int i, CTPermStart permStart) {
        generatedSetterHelperImpl(permStart, PROPERTY_QNAME[26], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTPermStart insertNewPermStart(int i) {
        CTPermStart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPermStart) get_store().insert_element_user(PROPERTY_QNAME[26], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTPermStart addNewPermStart() {
        CTPermStart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPermStart) get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removePermStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTPerm> getPermEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda244
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getPermEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda245
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setPermEndArray(((Integer) obj).intValue(), (CTPerm) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda246
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewPermEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda247
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removePermEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda248
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfPermEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTPerm[] getPermEndArray() {
        return (CTPerm[]) getXmlObjectArray(PROPERTY_QNAME[27], new CTPerm[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTPerm getPermEndArray(int i) {
        CTPerm target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPerm) get_store().find_element_user(PROPERTY_QNAME[27], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfPermEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[27]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setPermEndArray(CTPerm[] permEndArray) {
        check_orphaned();
        arraySetterHelper(permEndArray, PROPERTY_QNAME[27]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setPermEndArray(int i, CTPerm permEnd) {
        generatedSetterHelperImpl(permEnd, PROPERTY_QNAME[27], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTPerm insertNewPermEnd(int i) {
        CTPerm target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPerm) get_store().insert_element_user(PROPERTY_QNAME[27], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTPerm addNewPermEnd() {
        CTPerm target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPerm) get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removePermEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTBookmark> getBookmarkStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getBookmarkStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setBookmarkStartArray(((Integer) obj).intValue(), (CTBookmark) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewBookmarkStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeBookmarkStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda60
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfBookmarkStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTBookmark[] getBookmarkStartArray() {
        return (CTBookmark[]) getXmlObjectArray(PROPERTY_QNAME[28], new CTBookmark[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTBookmark getBookmarkStartArray(int i) {
        CTBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBookmark) get_store().find_element_user(PROPERTY_QNAME[28], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfBookmarkStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[28]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setBookmarkStartArray(CTBookmark[] bookmarkStartArray) {
        check_orphaned();
        arraySetterHelper(bookmarkStartArray, PROPERTY_QNAME[28]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setBookmarkStartArray(int i, CTBookmark bookmarkStart) {
        generatedSetterHelperImpl(bookmarkStart, PROPERTY_QNAME[28], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTBookmark insertNewBookmarkStart(int i) {
        CTBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBookmark) get_store().insert_element_user(PROPERTY_QNAME[28], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTBookmark addNewBookmarkStart() {
        CTBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBookmark) get_store().add_element_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeBookmarkStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTMarkupRange> getBookmarkEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getBookmarkEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setBookmarkEndArray(((Integer) obj).intValue(), (CTMarkupRange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewBookmarkEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeBookmarkEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda65
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfBookmarkEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange[] getBookmarkEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[29], new CTMarkupRange[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange getBookmarkEndArray(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[29], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfBookmarkEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[29]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setBookmarkEndArray(CTMarkupRange[] bookmarkEndArray) {
        check_orphaned();
        arraySetterHelper(bookmarkEndArray, PROPERTY_QNAME[29]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setBookmarkEndArray(int i, CTMarkupRange bookmarkEnd) {
        generatedSetterHelperImpl(bookmarkEnd, PROPERTY_QNAME[29], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange insertNewBookmarkEnd(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[29], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange addNewBookmarkEnd() {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeBookmarkEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTMoveBookmark> getMoveFromRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda72
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getMoveFromRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda73
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setMoveFromRangeStartArray(((Integer) obj).intValue(), (CTMoveBookmark) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda74
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewMoveFromRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda75
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeMoveFromRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda76
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfMoveFromRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMoveBookmark[] getMoveFromRangeStartArray() {
        return (CTMoveBookmark[]) getXmlObjectArray(PROPERTY_QNAME[30], new CTMoveBookmark[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMoveBookmark getMoveFromRangeStartArray(int i) {
        CTMoveBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMoveBookmark) get_store().find_element_user(PROPERTY_QNAME[30], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfMoveFromRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[30]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setMoveFromRangeStartArray(CTMoveBookmark[] moveFromRangeStartArray) {
        check_orphaned();
        arraySetterHelper(moveFromRangeStartArray, PROPERTY_QNAME[30]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setMoveFromRangeStartArray(int i, CTMoveBookmark moveFromRangeStart) {
        generatedSetterHelperImpl(moveFromRangeStart, PROPERTY_QNAME[30], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMoveBookmark insertNewMoveFromRangeStart(int i) {
        CTMoveBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMoveBookmark) get_store().insert_element_user(PROPERTY_QNAME[30], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMoveBookmark addNewMoveFromRangeStart() {
        CTMoveBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMoveBookmark) get_store().add_element_user(PROPERTY_QNAME[30]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeMoveFromRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[30], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTMarkupRange> getMoveFromRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda67
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getMoveFromRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda68
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setMoveFromRangeEndArray(((Integer) obj).intValue(), (CTMarkupRange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda69
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewMoveFromRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda70
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeMoveFromRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda71
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfMoveFromRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange[] getMoveFromRangeEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[31], new CTMarkupRange[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange getMoveFromRangeEndArray(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[31], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfMoveFromRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[31]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setMoveFromRangeEndArray(CTMarkupRange[] moveFromRangeEndArray) {
        check_orphaned();
        arraySetterHelper(moveFromRangeEndArray, PROPERTY_QNAME[31]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setMoveFromRangeEndArray(int i, CTMarkupRange moveFromRangeEnd) {
        generatedSetterHelperImpl(moveFromRangeEnd, PROPERTY_QNAME[31], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange insertNewMoveFromRangeEnd(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[31], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange addNewMoveFromRangeEnd() {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[31]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeMoveFromRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[31], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTMoveBookmark> getMoveToRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda150
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getMoveToRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda151
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setMoveToRangeStartArray(((Integer) obj).intValue(), (CTMoveBookmark) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda152
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewMoveToRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda153
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeMoveToRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda154
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfMoveToRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMoveBookmark[] getMoveToRangeStartArray() {
        return (CTMoveBookmark[]) getXmlObjectArray(PROPERTY_QNAME[32], new CTMoveBookmark[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMoveBookmark getMoveToRangeStartArray(int i) {
        CTMoveBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMoveBookmark) get_store().find_element_user(PROPERTY_QNAME[32], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfMoveToRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[32]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setMoveToRangeStartArray(CTMoveBookmark[] moveToRangeStartArray) {
        check_orphaned();
        arraySetterHelper(moveToRangeStartArray, PROPERTY_QNAME[32]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setMoveToRangeStartArray(int i, CTMoveBookmark moveToRangeStart) {
        generatedSetterHelperImpl(moveToRangeStart, PROPERTY_QNAME[32], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMoveBookmark insertNewMoveToRangeStart(int i) {
        CTMoveBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMoveBookmark) get_store().insert_element_user(PROPERTY_QNAME[32], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMoveBookmark addNewMoveToRangeStart() {
        CTMoveBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMoveBookmark) get_store().add_element_user(PROPERTY_QNAME[32]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeMoveToRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[32], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTMarkupRange> getMoveToRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda233
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getMoveToRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda234
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setMoveToRangeEndArray(((Integer) obj).intValue(), (CTMarkupRange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda235
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewMoveToRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda236
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeMoveToRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda237
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfMoveToRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange[] getMoveToRangeEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[33], new CTMarkupRange[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange getMoveToRangeEndArray(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[33], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfMoveToRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[33]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setMoveToRangeEndArray(CTMarkupRange[] moveToRangeEndArray) {
        check_orphaned();
        arraySetterHelper(moveToRangeEndArray, PROPERTY_QNAME[33]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setMoveToRangeEndArray(int i, CTMarkupRange moveToRangeEnd) {
        generatedSetterHelperImpl(moveToRangeEnd, PROPERTY_QNAME[33], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange insertNewMoveToRangeEnd(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[33], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange addNewMoveToRangeEnd() {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[33]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeMoveToRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[33], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTMarkupRange> getCommentRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda89
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getCommentRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda90
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setCommentRangeStartArray(((Integer) obj).intValue(), (CTMarkupRange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda91
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewCommentRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda92
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeCommentRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda93
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfCommentRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange[] getCommentRangeStartArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[34], new CTMarkupRange[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange getCommentRangeStartArray(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[34], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfCommentRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[34]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCommentRangeStartArray(CTMarkupRange[] commentRangeStartArray) {
        check_orphaned();
        arraySetterHelper(commentRangeStartArray, PROPERTY_QNAME[34]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCommentRangeStartArray(int i, CTMarkupRange commentRangeStart) {
        generatedSetterHelperImpl(commentRangeStart, PROPERTY_QNAME[34], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange insertNewCommentRangeStart(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[34], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange addNewCommentRangeStart() {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[34]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeCommentRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[34], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTMarkupRange> getCommentRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getCommentRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setCommentRangeEndArray(((Integer) obj).intValue(), (CTMarkupRange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewCommentRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeCommentRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfCommentRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange[] getCommentRangeEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[35], new CTMarkupRange[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange getCommentRangeEndArray(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[35], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfCommentRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[35]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCommentRangeEndArray(CTMarkupRange[] commentRangeEndArray) {
        check_orphaned();
        arraySetterHelper(commentRangeEndArray, PROPERTY_QNAME[35]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCommentRangeEndArray(int i, CTMarkupRange commentRangeEnd) {
        generatedSetterHelperImpl(commentRangeEnd, PROPERTY_QNAME[35], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange insertNewCommentRangeEnd(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[35], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkupRange addNewCommentRangeEnd() {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[35]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeCommentRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[35], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTTrackChange> getCustomXmlInsRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda156
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getCustomXmlInsRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda157
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setCustomXmlInsRangeStartArray(((Integer) obj).intValue(), (CTTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda158
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewCustomXmlInsRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda159
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeCustomXmlInsRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda160
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfCustomXmlInsRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTTrackChange[] getCustomXmlInsRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[36], new CTTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTTrackChange getCustomXmlInsRangeStartArray(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[36], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfCustomXmlInsRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[36]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCustomXmlInsRangeStartArray(CTTrackChange[] customXmlInsRangeStartArray) {
        check_orphaned();
        arraySetterHelper(customXmlInsRangeStartArray, PROPERTY_QNAME[36]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCustomXmlInsRangeStartArray(int i, CTTrackChange customXmlInsRangeStart) {
        generatedSetterHelperImpl(customXmlInsRangeStart, PROPERTY_QNAME[36], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTTrackChange insertNewCustomXmlInsRangeStart(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[36], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTTrackChange addNewCustomXmlInsRangeStart() {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[36]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeCustomXmlInsRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[36], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTMarkup> getCustomXmlInsRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getCustomXmlInsRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setCustomXmlInsRangeEndArray(((Integer) obj).intValue(), (CTMarkup) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewCustomXmlInsRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeCustomXmlInsRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfCustomXmlInsRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkup[] getCustomXmlInsRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[37], new CTMarkup[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkup getCustomXmlInsRangeEndArray(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[37], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfCustomXmlInsRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[37]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCustomXmlInsRangeEndArray(CTMarkup[] customXmlInsRangeEndArray) {
        check_orphaned();
        arraySetterHelper(customXmlInsRangeEndArray, PROPERTY_QNAME[37]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCustomXmlInsRangeEndArray(int i, CTMarkup customXmlInsRangeEnd) {
        generatedSetterHelperImpl(customXmlInsRangeEnd, PROPERTY_QNAME[37], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkup insertNewCustomXmlInsRangeEnd(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[37], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkup addNewCustomXmlInsRangeEnd() {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[37]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeCustomXmlInsRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[37], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTTrackChange> getCustomXmlDelRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda205
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getCustomXmlDelRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda216
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setCustomXmlDelRangeStartArray(((Integer) obj).intValue(), (CTTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda227
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewCustomXmlDelRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda238
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeCustomXmlDelRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda249
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfCustomXmlDelRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTTrackChange[] getCustomXmlDelRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[38], new CTTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTTrackChange getCustomXmlDelRangeStartArray(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[38], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfCustomXmlDelRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[38]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCustomXmlDelRangeStartArray(CTTrackChange[] customXmlDelRangeStartArray) {
        check_orphaned();
        arraySetterHelper(customXmlDelRangeStartArray, PROPERTY_QNAME[38]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCustomXmlDelRangeStartArray(int i, CTTrackChange customXmlDelRangeStart) {
        generatedSetterHelperImpl(customXmlDelRangeStart, PROPERTY_QNAME[38], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTTrackChange insertNewCustomXmlDelRangeStart(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[38], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTTrackChange addNewCustomXmlDelRangeStart() {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[38]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeCustomXmlDelRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[38], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTMarkup> getCustomXmlDelRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda206
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getCustomXmlDelRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda207
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setCustomXmlDelRangeEndArray(((Integer) obj).intValue(), (CTMarkup) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda208
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewCustomXmlDelRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda209
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeCustomXmlDelRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda210
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfCustomXmlDelRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkup[] getCustomXmlDelRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[39], new CTMarkup[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkup getCustomXmlDelRangeEndArray(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[39], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfCustomXmlDelRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[39]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCustomXmlDelRangeEndArray(CTMarkup[] customXmlDelRangeEndArray) {
        check_orphaned();
        arraySetterHelper(customXmlDelRangeEndArray, PROPERTY_QNAME[39]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCustomXmlDelRangeEndArray(int i, CTMarkup customXmlDelRangeEnd) {
        generatedSetterHelperImpl(customXmlDelRangeEnd, PROPERTY_QNAME[39], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkup insertNewCustomXmlDelRangeEnd(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[39], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkup addNewCustomXmlDelRangeEnd() {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[39]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeCustomXmlDelRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[39], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTTrackChange> getCustomXmlMoveFromRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda128
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getCustomXmlMoveFromRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda129
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setCustomXmlMoveFromRangeStartArray(((Integer) obj).intValue(), (CTTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda130
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewCustomXmlMoveFromRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda131
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeCustomXmlMoveFromRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda132
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfCustomXmlMoveFromRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTTrackChange[] getCustomXmlMoveFromRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[40], new CTTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTTrackChange getCustomXmlMoveFromRangeStartArray(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[40], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfCustomXmlMoveFromRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[40]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCustomXmlMoveFromRangeStartArray(CTTrackChange[] customXmlMoveFromRangeStartArray) {
        check_orphaned();
        arraySetterHelper(customXmlMoveFromRangeStartArray, PROPERTY_QNAME[40]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCustomXmlMoveFromRangeStartArray(int i, CTTrackChange customXmlMoveFromRangeStart) {
        generatedSetterHelperImpl(customXmlMoveFromRangeStart, PROPERTY_QNAME[40], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTTrackChange insertNewCustomXmlMoveFromRangeStart(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[40], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTTrackChange addNewCustomXmlMoveFromRangeStart() {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[40]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeCustomXmlMoveFromRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[40], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTMarkup> getCustomXmlMoveFromRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda184
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getCustomXmlMoveFromRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda185
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setCustomXmlMoveFromRangeEndArray(((Integer) obj).intValue(), (CTMarkup) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda186
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewCustomXmlMoveFromRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda187
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeCustomXmlMoveFromRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda188
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfCustomXmlMoveFromRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkup[] getCustomXmlMoveFromRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[41], new CTMarkup[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkup getCustomXmlMoveFromRangeEndArray(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[41], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfCustomXmlMoveFromRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[41]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCustomXmlMoveFromRangeEndArray(CTMarkup[] customXmlMoveFromRangeEndArray) {
        check_orphaned();
        arraySetterHelper(customXmlMoveFromRangeEndArray, PROPERTY_QNAME[41]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCustomXmlMoveFromRangeEndArray(int i, CTMarkup customXmlMoveFromRangeEnd) {
        generatedSetterHelperImpl(customXmlMoveFromRangeEnd, PROPERTY_QNAME[41], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkup insertNewCustomXmlMoveFromRangeEnd(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[41], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkup addNewCustomXmlMoveFromRangeEnd() {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[41]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeCustomXmlMoveFromRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[41], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTTrackChange> getCustomXmlMoveToRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda195
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getCustomXmlMoveToRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda196
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setCustomXmlMoveToRangeStartArray(((Integer) obj).intValue(), (CTTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda197
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewCustomXmlMoveToRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda198
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeCustomXmlMoveToRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda199
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfCustomXmlMoveToRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTTrackChange[] getCustomXmlMoveToRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[42], new CTTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTTrackChange getCustomXmlMoveToRangeStartArray(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[42], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfCustomXmlMoveToRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[42]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCustomXmlMoveToRangeStartArray(CTTrackChange[] customXmlMoveToRangeStartArray) {
        check_orphaned();
        arraySetterHelper(customXmlMoveToRangeStartArray, PROPERTY_QNAME[42]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCustomXmlMoveToRangeStartArray(int i, CTTrackChange customXmlMoveToRangeStart) {
        generatedSetterHelperImpl(customXmlMoveToRangeStart, PROPERTY_QNAME[42], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTTrackChange insertNewCustomXmlMoveToRangeStart(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[42], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTTrackChange addNewCustomXmlMoveToRangeStart() {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[42]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeCustomXmlMoveToRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[42], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTMarkup> getCustomXmlMoveToRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda211
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getCustomXmlMoveToRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda212
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setCustomXmlMoveToRangeEndArray(((Integer) obj).intValue(), (CTMarkup) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda213
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewCustomXmlMoveToRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda214
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeCustomXmlMoveToRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda215
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfCustomXmlMoveToRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkup[] getCustomXmlMoveToRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[43], new CTMarkup[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkup getCustomXmlMoveToRangeEndArray(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[43], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfCustomXmlMoveToRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[43]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCustomXmlMoveToRangeEndArray(CTMarkup[] customXmlMoveToRangeEndArray) {
        check_orphaned();
        arraySetterHelper(customXmlMoveToRangeEndArray, PROPERTY_QNAME[43]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setCustomXmlMoveToRangeEndArray(int i, CTMarkup customXmlMoveToRangeEnd) {
        generatedSetterHelperImpl(customXmlMoveToRangeEnd, PROPERTY_QNAME[43], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkup insertNewCustomXmlMoveToRangeEnd(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[43], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTMarkup addNewCustomXmlMoveToRangeEnd() {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[43]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeCustomXmlMoveToRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[43], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTRunTrackChange> getInsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getInsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setInsArray(((Integer) obj).intValue(), (CTRunTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewIns(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeIns(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfInsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRunTrackChange[] getInsArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[44], new CTRunTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRunTrackChange getInsArray(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[44], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfInsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[44]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setInsArray(CTRunTrackChange[] insArray) {
        check_orphaned();
        arraySetterHelper(insArray, PROPERTY_QNAME[44]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setInsArray(int i, CTRunTrackChange ins) {
        generatedSetterHelperImpl(ins, PROPERTY_QNAME[44], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRunTrackChange insertNewIns(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[44], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRunTrackChange addNewIns() {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[44]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeIns(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[44], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTRunTrackChange> getDelList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda200
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getDelArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda201
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setDelArray(((Integer) obj).intValue(), (CTRunTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda202
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewDel(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda203
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeDel(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda204
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfDelArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRunTrackChange[] getDelArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[45], new CTRunTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRunTrackChange getDelArray(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[45], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfDelArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[45]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setDelArray(CTRunTrackChange[] delArray) {
        check_orphaned();
        arraySetterHelper(delArray, PROPERTY_QNAME[45]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setDelArray(int i, CTRunTrackChange del) {
        generatedSetterHelperImpl(del, PROPERTY_QNAME[45], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRunTrackChange insertNewDel(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[45], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRunTrackChange addNewDel() {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[45]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeDel(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[45], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTRunTrackChange> getMoveFromList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda122
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getMoveFromArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda133
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setMoveFromArray(((Integer) obj).intValue(), (CTRunTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda144
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewMoveFrom(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda155
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeMoveFrom(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda166
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfMoveFromArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRunTrackChange[] getMoveFromArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[46], new CTRunTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRunTrackChange getMoveFromArray(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[46], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfMoveFromArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[46]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setMoveFromArray(CTRunTrackChange[] moveFromArray) {
        check_orphaned();
        arraySetterHelper(moveFromArray, PROPERTY_QNAME[46]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setMoveFromArray(int i, CTRunTrackChange moveFrom) {
        generatedSetterHelperImpl(moveFrom, PROPERTY_QNAME[46], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRunTrackChange insertNewMoveFrom(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[46], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRunTrackChange addNewMoveFrom() {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[46]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeMoveFrom(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[46], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTRunTrackChange> getMoveToList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getMoveToArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setMoveToArray(((Integer) obj).intValue(), (CTRunTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewMoveTo(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeMoveTo(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfMoveToArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRunTrackChange[] getMoveToArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[47], new CTRunTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRunTrackChange getMoveToArray(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[47], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfMoveToArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[47]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setMoveToArray(CTRunTrackChange[] moveToArray) {
        check_orphaned();
        arraySetterHelper(moveToArray, PROPERTY_QNAME[47]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setMoveToArray(int i, CTRunTrackChange moveTo) {
        generatedSetterHelperImpl(moveTo, PROPERTY_QNAME[47], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRunTrackChange insertNewMoveTo(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[47], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTRunTrackChange addNewMoveTo() {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[47]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeMoveTo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[47], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTOMathPara> getOMathParaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getOMathParaArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setOMathParaArray(((Integer) obj).intValue(), (CTOMathPara) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewOMathPara(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeOMathPara(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfOMathParaArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTOMathPara[] getOMathParaArray() {
        return (CTOMathPara[]) getXmlObjectArray(PROPERTY_QNAME[48], new CTOMathPara[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTOMathPara getOMathParaArray(int i) {
        CTOMathPara target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMathPara) get_store().find_element_user(PROPERTY_QNAME[48], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfOMathParaArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[48]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setOMathParaArray(CTOMathPara[] oMathParaArray) {
        check_orphaned();
        arraySetterHelper(oMathParaArray, PROPERTY_QNAME[48]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setOMathParaArray(int i, CTOMathPara oMathPara) {
        generatedSetterHelperImpl(oMathPara, PROPERTY_QNAME[48], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTOMathPara insertNewOMathPara(int i) {
        CTOMathPara target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMathPara) get_store().insert_element_user(PROPERTY_QNAME[48], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTOMathPara addNewOMathPara() {
        CTOMathPara target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMathPara) get_store().add_element_user(PROPERTY_QNAME[48]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeOMathPara(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[48], i);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public List<CTOMath> getOMathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda139
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.getOMathArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda140
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOMathImpl.this.setOMathArray(((Integer) obj).intValue(), (CTOMath) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda141
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOMathImpl.this.insertNewOMath(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda142
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOMathImpl.this.removeOMath(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.math.impl.CTOMathImpl$$ExternalSyntheticLambda143
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOMathImpl.this.sizeOfOMathArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTOMath[] getOMathArray() {
        return (CTOMath[]) getXmlObjectArray(PROPERTY_QNAME[49], new CTOMath[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTOMath getOMathArray(int i) {
        CTOMath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMath) get_store().find_element_user(PROPERTY_QNAME[49], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public int sizeOfOMathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[49]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setOMathArray(CTOMath[] oMathArray) {
        check_orphaned();
        arraySetterHelper(oMathArray, PROPERTY_QNAME[49]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void setOMathArray(int i, CTOMath oMath) {
        generatedSetterHelperImpl(oMath, PROPERTY_QNAME[49], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTOMath insertNewOMath(int i) {
        CTOMath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMath) get_store().insert_element_user(PROPERTY_QNAME[49], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public CTOMath addNewOMath() {
        CTOMath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMath) get_store().add_element_user(PROPERTY_QNAME[49]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath
    public void removeOMath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[49], i);
        }
    }
}
