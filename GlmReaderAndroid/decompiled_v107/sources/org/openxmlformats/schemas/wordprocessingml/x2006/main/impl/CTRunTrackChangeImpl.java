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
import org.openxmlformats.schemas.officeDocument.x2006.math.CTRad;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSPre;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSSubSup;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSSup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBdoContentRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDirContentRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPermStart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSmartTagRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;

/* loaded from: classes12.dex */
public class CTRunTrackChangeImpl extends CTTrackChangeImpl implements CTRunTrackChange {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXml"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "smartTag"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "sdt"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "dir"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bdo"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "r"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "proofErr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "permStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "permEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bookmarkStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bookmarkEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveFromRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveFromRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveToRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveToRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "commentRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "commentRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlInsRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlInsRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlDelRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlDelRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlMoveFromRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlMoveFromRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlMoveToRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlMoveToRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "ins"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "del"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveFrom"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveTo"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMathPara"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMath"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "acc"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "bar"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "box"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "borderBox"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "d"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "eqArr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "f"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "func"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "groupChr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "limLow"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "limUpp"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "m"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "nary"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "phant"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "rad"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "sPre"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "sSub"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "sSubSup"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "sSup"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "r")};
    private static final long serialVersionUID = 1;

    public CTRunTrackChangeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTCustomXmlRun> getCustomXmlList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getCustomXmlArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setCustomXmlArray(((Integer) obj).intValue(), (CTCustomXmlRun) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewCustomXml(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeCustomXml(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfCustomXmlArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTCustomXmlRun[] getCustomXmlArray() {
        return (CTCustomXmlRun[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTCustomXmlRun[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTCustomXmlRun getCustomXmlArray(int i) {
        CTCustomXmlRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCustomXmlRun) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfCustomXmlArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCustomXmlArray(CTCustomXmlRun[] customXmlArray) {
        check_orphaned();
        arraySetterHelper(customXmlArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCustomXmlArray(int i, CTCustomXmlRun customXml) {
        generatedSetterHelperImpl(customXml, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTCustomXmlRun insertNewCustomXml(int i) {
        CTCustomXmlRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCustomXmlRun) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTCustomXmlRun addNewCustomXml() {
        CTCustomXmlRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCustomXmlRun) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeCustomXml(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTSmartTagRun> getSmartTagList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getSmartTagArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setSmartTagArray(((Integer) obj).intValue(), (CTSmartTagRun) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewSmartTag(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeSmartTag(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfSmartTagArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSmartTagRun[] getSmartTagArray() {
        return (CTSmartTagRun[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTSmartTagRun[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSmartTagRun getSmartTagArray(int i) {
        CTSmartTagRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSmartTagRun) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfSmartTagArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setSmartTagArray(CTSmartTagRun[] smartTagArray) {
        check_orphaned();
        arraySetterHelper(smartTagArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setSmartTagArray(int i, CTSmartTagRun smartTag) {
        generatedSetterHelperImpl(smartTag, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSmartTagRun insertNewSmartTag(int i) {
        CTSmartTagRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSmartTagRun) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSmartTagRun addNewSmartTag() {
        CTSmartTagRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSmartTagRun) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeSmartTag(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTSdtRun> getSdtList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getSdtArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setSdtArray(((Integer) obj).intValue(), (CTSdtRun) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewSdt(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeSdt(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda65
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfSdtArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSdtRun[] getSdtArray() {
        return (CTSdtRun[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTSdtRun[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSdtRun getSdtArray(int i) {
        CTSdtRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtRun) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfSdtArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setSdtArray(CTSdtRun[] sdtArray) {
        check_orphaned();
        arraySetterHelper(sdtArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setSdtArray(int i, CTSdtRun sdt) {
        generatedSetterHelperImpl(sdt, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSdtRun insertNewSdt(int i) {
        CTSdtRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtRun) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSdtRun addNewSdt() {
        CTSdtRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtRun) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeSdt(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTDirContentRun> getDirList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda134
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getDirArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda135
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setDirArray(((Integer) obj).intValue(), (CTDirContentRun) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda136
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewDir(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda137
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeDir(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda138
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfDirArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTDirContentRun[] getDirArray() {
        return (CTDirContentRun[]) getXmlObjectArray(PROPERTY_QNAME[3], new CTDirContentRun[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTDirContentRun getDirArray(int i) {
        CTDirContentRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDirContentRun) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfDirArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setDirArray(CTDirContentRun[] dirArray) {
        check_orphaned();
        arraySetterHelper(dirArray, PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setDirArray(int i, CTDirContentRun dir) {
        generatedSetterHelperImpl(dir, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTDirContentRun insertNewDir(int i) {
        CTDirContentRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDirContentRun) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTDirContentRun addNewDir() {
        CTDirContentRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDirContentRun) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeDir(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTBdoContentRun> getBdoList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda161
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getBdoArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda162
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setBdoArray(((Integer) obj).intValue(), (CTBdoContentRun) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda163
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewBdo(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda164
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeBdo(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda165
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfBdoArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBdoContentRun[] getBdoArray() {
        return (CTBdoContentRun[]) getXmlObjectArray(PROPERTY_QNAME[4], new CTBdoContentRun[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBdoContentRun getBdoArray(int i) {
        CTBdoContentRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBdoContentRun) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfBdoArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setBdoArray(CTBdoContentRun[] bdoArray) {
        check_orphaned();
        arraySetterHelper(bdoArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setBdoArray(int i, CTBdoContentRun bdo) {
        generatedSetterHelperImpl(bdo, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBdoContentRun insertNewBdo(int i) {
        CTBdoContentRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBdoContentRun) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBdoContentRun addNewBdo() {
        CTBdoContentRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBdoContentRun) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeBdo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTR> getRList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getRArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setRArray(((Integer) obj).intValue(), (CTR) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewR(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeR(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfRArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTR[] getRArray() {
        return (CTR[]) getXmlObjectArray(PROPERTY_QNAME[5], new CTR[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTR getRArray(int i) {
        CTR target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTR) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfRArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setRArray(CTR[] rArray) {
        check_orphaned();
        arraySetterHelper(rArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setRArray(int i, CTR r) {
        generatedSetterHelperImpl(r, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTR insertNewR(int i) {
        CTR target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTR) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTR addNewR() {
        CTR target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTR) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeR(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTProofErr> getProofErrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getProofErrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setProofErrArray(((Integer) obj).intValue(), (CTProofErr) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewProofErr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeProofErr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda60
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfProofErrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTProofErr[] getProofErrArray() {
        return (CTProofErr[]) getXmlObjectArray(PROPERTY_QNAME[6], new CTProofErr[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTProofErr getProofErrArray(int i) {
        CTProofErr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTProofErr) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfProofErrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setProofErrArray(CTProofErr[] proofErrArray) {
        check_orphaned();
        arraySetterHelper(proofErrArray, PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setProofErrArray(int i, CTProofErr proofErr) {
        generatedSetterHelperImpl(proofErr, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTProofErr insertNewProofErr(int i) {
        CTProofErr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTProofErr) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTProofErr addNewProofErr() {
        CTProofErr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTProofErr) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeProofErr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTPermStart> getPermStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda89
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getPermStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda90
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setPermStartArray(((Integer) obj).intValue(), (CTPermStart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda91
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewPermStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda92
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removePermStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda93
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfPermStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTPermStart[] getPermStartArray() {
        return (CTPermStart[]) getXmlObjectArray(PROPERTY_QNAME[7], new CTPermStart[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTPermStart getPermStartArray(int i) {
        CTPermStart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPermStart) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfPermStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setPermStartArray(CTPermStart[] permStartArray) {
        check_orphaned();
        arraySetterHelper(permStartArray, PROPERTY_QNAME[7]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setPermStartArray(int i, CTPermStart permStart) {
        generatedSetterHelperImpl(permStart, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTPermStart insertNewPermStart(int i) {
        CTPermStart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPermStart) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTPermStart addNewPermStart() {
        CTPermStart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPermStart) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removePermStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTPerm> getPermEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getPermEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setPermEndArray(((Integer) obj).intValue(), (CTPerm) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewPermEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removePermEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfPermEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTPerm[] getPermEndArray() {
        return (CTPerm[]) getXmlObjectArray(PROPERTY_QNAME[8], new CTPerm[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTPerm getPermEndArray(int i) {
        CTPerm target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPerm) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfPermEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setPermEndArray(CTPerm[] permEndArray) {
        check_orphaned();
        arraySetterHelper(permEndArray, PROPERTY_QNAME[8]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setPermEndArray(int i, CTPerm permEnd) {
        generatedSetterHelperImpl(permEnd, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTPerm insertNewPermEnd(int i) {
        CTPerm target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPerm) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTPerm addNewPermEnd() {
        CTPerm target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPerm) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removePermEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTBookmark> getBookmarkStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getBookmarkStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setBookmarkStartArray(((Integer) obj).intValue(), (CTBookmark) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewBookmarkStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeBookmarkStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfBookmarkStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBookmark[] getBookmarkStartArray() {
        return (CTBookmark[]) getXmlObjectArray(PROPERTY_QNAME[9], new CTBookmark[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBookmark getBookmarkStartArray(int i) {
        CTBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBookmark) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfBookmarkStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setBookmarkStartArray(CTBookmark[] bookmarkStartArray) {
        check_orphaned();
        arraySetterHelper(bookmarkStartArray, PROPERTY_QNAME[9]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setBookmarkStartArray(int i, CTBookmark bookmarkStart) {
        generatedSetterHelperImpl(bookmarkStart, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBookmark insertNewBookmarkStart(int i) {
        CTBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBookmark) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBookmark addNewBookmarkStart() {
        CTBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBookmark) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeBookmarkStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTMarkupRange> getBookmarkEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda210
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getBookmarkEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda221
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setBookmarkEndArray(((Integer) obj).intValue(), (CTMarkupRange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda232
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewBookmarkEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda243
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeBookmarkEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda254
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfBookmarkEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange[] getBookmarkEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[10], new CTMarkupRange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange getBookmarkEndArray(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfBookmarkEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setBookmarkEndArray(CTMarkupRange[] bookmarkEndArray) {
        check_orphaned();
        arraySetterHelper(bookmarkEndArray, PROPERTY_QNAME[10]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setBookmarkEndArray(int i, CTMarkupRange bookmarkEnd) {
        generatedSetterHelperImpl(bookmarkEnd, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange insertNewBookmarkEnd(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange addNewBookmarkEnd() {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeBookmarkEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTMoveBookmark> getMoveFromRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda123
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getMoveFromRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda124
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setMoveFromRangeStartArray(((Integer) obj).intValue(), (CTMoveBookmark) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda125
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewMoveFromRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda126
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeMoveFromRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda127
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfMoveFromRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMoveBookmark[] getMoveFromRangeStartArray() {
        return (CTMoveBookmark[]) getXmlObjectArray(PROPERTY_QNAME[11], new CTMoveBookmark[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMoveBookmark getMoveFromRangeStartArray(int i) {
        CTMoveBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMoveBookmark) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfMoveFromRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setMoveFromRangeStartArray(CTMoveBookmark[] moveFromRangeStartArray) {
        check_orphaned();
        arraySetterHelper(moveFromRangeStartArray, PROPERTY_QNAME[11]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setMoveFromRangeStartArray(int i, CTMoveBookmark moveFromRangeStart) {
        generatedSetterHelperImpl(moveFromRangeStart, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMoveBookmark insertNewMoveFromRangeStart(int i) {
        CTMoveBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMoveBookmark) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMoveBookmark addNewMoveFromRangeStart() {
        CTMoveBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMoveBookmark) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeMoveFromRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTMarkupRange> getMoveFromRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda145
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getMoveFromRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda146
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setMoveFromRangeEndArray(((Integer) obj).intValue(), (CTMarkupRange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda147
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewMoveFromRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda148
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeMoveFromRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda149
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfMoveFromRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange[] getMoveFromRangeEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[12], new CTMarkupRange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange getMoveFromRangeEndArray(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfMoveFromRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setMoveFromRangeEndArray(CTMarkupRange[] moveFromRangeEndArray) {
        check_orphaned();
        arraySetterHelper(moveFromRangeEndArray, PROPERTY_QNAME[12]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setMoveFromRangeEndArray(int i, CTMarkupRange moveFromRangeEnd) {
        generatedSetterHelperImpl(moveFromRangeEnd, PROPERTY_QNAME[12], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange insertNewMoveFromRangeEnd(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange addNewMoveFromRangeEnd() {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeMoveFromRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTMoveBookmark> getMoveToRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getMoveToRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setMoveToRangeStartArray(((Integer) obj).intValue(), (CTMoveBookmark) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewMoveToRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeMoveToRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfMoveToRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMoveBookmark[] getMoveToRangeStartArray() {
        return (CTMoveBookmark[]) getXmlObjectArray(PROPERTY_QNAME[13], new CTMoveBookmark[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMoveBookmark getMoveToRangeStartArray(int i) {
        CTMoveBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMoveBookmark) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfMoveToRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setMoveToRangeStartArray(CTMoveBookmark[] moveToRangeStartArray) {
        check_orphaned();
        arraySetterHelper(moveToRangeStartArray, PROPERTY_QNAME[13]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setMoveToRangeStartArray(int i, CTMoveBookmark moveToRangeStart) {
        generatedSetterHelperImpl(moveToRangeStart, PROPERTY_QNAME[13], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMoveBookmark insertNewMoveToRangeStart(int i) {
        CTMoveBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMoveBookmark) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMoveBookmark addNewMoveToRangeStart() {
        CTMoveBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMoveBookmark) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeMoveToRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTMarkupRange> getMoveToRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getMoveToRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setMoveToRangeEndArray(((Integer) obj).intValue(), (CTMarkupRange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewMoveToRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeMoveToRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfMoveToRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange[] getMoveToRangeEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[14], new CTMarkupRange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange getMoveToRangeEndArray(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfMoveToRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setMoveToRangeEndArray(CTMarkupRange[] moveToRangeEndArray) {
        check_orphaned();
        arraySetterHelper(moveToRangeEndArray, PROPERTY_QNAME[14]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setMoveToRangeEndArray(int i, CTMarkupRange moveToRangeEnd) {
        generatedSetterHelperImpl(moveToRangeEnd, PROPERTY_QNAME[14], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange insertNewMoveToRangeEnd(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange addNewMoveToRangeEnd() {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeMoveToRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTMarkupRange> getCommentRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda216
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getCommentRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda217
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setCommentRangeStartArray(((Integer) obj).intValue(), (CTMarkupRange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda218
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewCommentRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda219
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeCommentRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda220
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfCommentRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange[] getCommentRangeStartArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[15], new CTMarkupRange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange getCommentRangeStartArray(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfCommentRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCommentRangeStartArray(CTMarkupRange[] commentRangeStartArray) {
        check_orphaned();
        arraySetterHelper(commentRangeStartArray, PROPERTY_QNAME[15]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCommentRangeStartArray(int i, CTMarkupRange commentRangeStart) {
        generatedSetterHelperImpl(commentRangeStart, PROPERTY_QNAME[15], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange insertNewCommentRangeStart(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange addNewCommentRangeStart() {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeCommentRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTMarkupRange> getCommentRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda211
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getCommentRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda212
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setCommentRangeEndArray(((Integer) obj).intValue(), (CTMarkupRange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda213
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewCommentRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda214
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeCommentRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda215
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfCommentRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange[] getCommentRangeEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[16], new CTMarkupRange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange getCommentRangeEndArray(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfCommentRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCommentRangeEndArray(CTMarkupRange[] commentRangeEndArray) {
        check_orphaned();
        arraySetterHelper(commentRangeEndArray, PROPERTY_QNAME[16]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCommentRangeEndArray(int i, CTMarkupRange commentRangeEnd) {
        generatedSetterHelperImpl(commentRangeEnd, PROPERTY_QNAME[16], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange insertNewCommentRangeEnd(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkupRange addNewCommentRangeEnd() {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeCommentRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTTrackChange> getCustomXmlInsRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda233
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getCustomXmlInsRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda234
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setCustomXmlInsRangeStartArray(((Integer) obj).intValue(), (CTTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda235
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewCustomXmlInsRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda236
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeCustomXmlInsRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda237
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfCustomXmlInsRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTTrackChange[] getCustomXmlInsRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[17], new CTTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTTrackChange getCustomXmlInsRangeStartArray(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfCustomXmlInsRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCustomXmlInsRangeStartArray(CTTrackChange[] customXmlInsRangeStartArray) {
        check_orphaned();
        arraySetterHelper(customXmlInsRangeStartArray, PROPERTY_QNAME[17]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCustomXmlInsRangeStartArray(int i, CTTrackChange customXmlInsRangeStart) {
        generatedSetterHelperImpl(customXmlInsRangeStart, PROPERTY_QNAME[17], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTTrackChange insertNewCustomXmlInsRangeStart(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTTrackChange addNewCustomXmlInsRangeStart() {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeCustomXmlInsRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTMarkup> getCustomXmlInsRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda200
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getCustomXmlInsRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda201
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setCustomXmlInsRangeEndArray(((Integer) obj).intValue(), (CTMarkup) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda202
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewCustomXmlInsRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda203
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeCustomXmlInsRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda204
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfCustomXmlInsRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkup[] getCustomXmlInsRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[18], new CTMarkup[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkup getCustomXmlInsRangeEndArray(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfCustomXmlInsRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCustomXmlInsRangeEndArray(CTMarkup[] customXmlInsRangeEndArray) {
        check_orphaned();
        arraySetterHelper(customXmlInsRangeEndArray, PROPERTY_QNAME[18]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCustomXmlInsRangeEndArray(int i, CTMarkup customXmlInsRangeEnd) {
        generatedSetterHelperImpl(customXmlInsRangeEnd, PROPERTY_QNAME[18], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkup insertNewCustomXmlInsRangeEnd(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkup addNewCustomXmlInsRangeEnd() {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeCustomXmlInsRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTTrackChange> getCustomXmlDelRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getCustomXmlDelRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setCustomXmlDelRangeStartArray(((Integer) obj).intValue(), (CTTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewCustomXmlDelRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeCustomXmlDelRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfCustomXmlDelRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTTrackChange[] getCustomXmlDelRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[19], new CTTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTTrackChange getCustomXmlDelRangeStartArray(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfCustomXmlDelRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCustomXmlDelRangeStartArray(CTTrackChange[] customXmlDelRangeStartArray) {
        check_orphaned();
        arraySetterHelper(customXmlDelRangeStartArray, PROPERTY_QNAME[19]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCustomXmlDelRangeStartArray(int i, CTTrackChange customXmlDelRangeStart) {
        generatedSetterHelperImpl(customXmlDelRangeStart, PROPERTY_QNAME[19], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTTrackChange insertNewCustomXmlDelRangeStart(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTTrackChange addNewCustomXmlDelRangeStart() {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeCustomXmlDelRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTMarkup> getCustomXmlDelRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda78
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getCustomXmlDelRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda79
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setCustomXmlDelRangeEndArray(((Integer) obj).intValue(), (CTMarkup) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda80
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewCustomXmlDelRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda81
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeCustomXmlDelRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda82
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfCustomXmlDelRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkup[] getCustomXmlDelRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[20], new CTMarkup[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkup getCustomXmlDelRangeEndArray(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[20], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfCustomXmlDelRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCustomXmlDelRangeEndArray(CTMarkup[] customXmlDelRangeEndArray) {
        check_orphaned();
        arraySetterHelper(customXmlDelRangeEndArray, PROPERTY_QNAME[20]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCustomXmlDelRangeEndArray(int i, CTMarkup customXmlDelRangeEnd) {
        generatedSetterHelperImpl(customXmlDelRangeEnd, PROPERTY_QNAME[20], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkup insertNewCustomXmlDelRangeEnd(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkup addNewCustomXmlDelRangeEnd() {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeCustomXmlDelRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTTrackChange> getCustomXmlMoveFromRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getCustomXmlMoveFromRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setCustomXmlMoveFromRangeStartArray(((Integer) obj).intValue(), (CTTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewCustomXmlMoveFromRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeCustomXmlMoveFromRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfCustomXmlMoveFromRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTTrackChange[] getCustomXmlMoveFromRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[21], new CTTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTTrackChange getCustomXmlMoveFromRangeStartArray(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfCustomXmlMoveFromRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCustomXmlMoveFromRangeStartArray(CTTrackChange[] customXmlMoveFromRangeStartArray) {
        check_orphaned();
        arraySetterHelper(customXmlMoveFromRangeStartArray, PROPERTY_QNAME[21]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCustomXmlMoveFromRangeStartArray(int i, CTTrackChange customXmlMoveFromRangeStart) {
        generatedSetterHelperImpl(customXmlMoveFromRangeStart, PROPERTY_QNAME[21], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTTrackChange insertNewCustomXmlMoveFromRangeStart(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTTrackChange addNewCustomXmlMoveFromRangeStart() {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeCustomXmlMoveFromRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTMarkup> getCustomXmlMoveFromRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda194
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getCustomXmlMoveFromRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda195
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setCustomXmlMoveFromRangeEndArray(((Integer) obj).intValue(), (CTMarkup) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda196
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewCustomXmlMoveFromRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda197
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeCustomXmlMoveFromRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda198
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfCustomXmlMoveFromRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkup[] getCustomXmlMoveFromRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[22], new CTMarkup[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkup getCustomXmlMoveFromRangeEndArray(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[22], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfCustomXmlMoveFromRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCustomXmlMoveFromRangeEndArray(CTMarkup[] customXmlMoveFromRangeEndArray) {
        check_orphaned();
        arraySetterHelper(customXmlMoveFromRangeEndArray, PROPERTY_QNAME[22]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCustomXmlMoveFromRangeEndArray(int i, CTMarkup customXmlMoveFromRangeEnd) {
        generatedSetterHelperImpl(customXmlMoveFromRangeEnd, PROPERTY_QNAME[22], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkup insertNewCustomXmlMoveFromRangeEnd(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkup addNewCustomXmlMoveFromRangeEnd() {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeCustomXmlMoveFromRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTTrackChange> getCustomXmlMoveToRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda249
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getCustomXmlMoveToRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda250
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setCustomXmlMoveToRangeStartArray(((Integer) obj).intValue(), (CTTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda251
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewCustomXmlMoveToRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda252
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeCustomXmlMoveToRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda253
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfCustomXmlMoveToRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTTrackChange[] getCustomXmlMoveToRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[23], new CTTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTTrackChange getCustomXmlMoveToRangeStartArray(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[23], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfCustomXmlMoveToRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCustomXmlMoveToRangeStartArray(CTTrackChange[] customXmlMoveToRangeStartArray) {
        check_orphaned();
        arraySetterHelper(customXmlMoveToRangeStartArray, PROPERTY_QNAME[23]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCustomXmlMoveToRangeStartArray(int i, CTTrackChange customXmlMoveToRangeStart) {
        generatedSetterHelperImpl(customXmlMoveToRangeStart, PROPERTY_QNAME[23], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTTrackChange insertNewCustomXmlMoveToRangeStart(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTTrackChange addNewCustomXmlMoveToRangeStart() {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeCustomXmlMoveToRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTMarkup> getCustomXmlMoveToRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda67
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getCustomXmlMoveToRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda68
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setCustomXmlMoveToRangeEndArray(((Integer) obj).intValue(), (CTMarkup) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda69
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewCustomXmlMoveToRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda70
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeCustomXmlMoveToRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda71
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfCustomXmlMoveToRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkup[] getCustomXmlMoveToRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[24], new CTMarkup[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkup getCustomXmlMoveToRangeEndArray(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[24], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfCustomXmlMoveToRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCustomXmlMoveToRangeEndArray(CTMarkup[] customXmlMoveToRangeEndArray) {
        check_orphaned();
        arraySetterHelper(customXmlMoveToRangeEndArray, PROPERTY_QNAME[24]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setCustomXmlMoveToRangeEndArray(int i, CTMarkup customXmlMoveToRangeEnd) {
        generatedSetterHelperImpl(customXmlMoveToRangeEnd, PROPERTY_QNAME[24], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkup insertNewCustomXmlMoveToRangeEnd(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTMarkup addNewCustomXmlMoveToRangeEnd() {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeCustomXmlMoveToRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTRunTrackChange> getInsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda156
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getInsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda157
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setInsArray(((Integer) obj).intValue(), (CTRunTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda158
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewIns(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda159
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeIns(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda160
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfInsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRunTrackChange[] getInsArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[25], new CTRunTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRunTrackChange getInsArray(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[25], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfInsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setInsArray(CTRunTrackChange[] insArray) {
        check_orphaned();
        arraySetterHelper(insArray, PROPERTY_QNAME[25]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setInsArray(int i, CTRunTrackChange ins) {
        generatedSetterHelperImpl(ins, PROPERTY_QNAME[25], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRunTrackChange insertNewIns(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRunTrackChange addNewIns() {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeIns(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTRunTrackChange> getDelList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda150
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getDelArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda151
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setDelArray(((Integer) obj).intValue(), (CTRunTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda152
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewDel(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda153
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeDel(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda154
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfDelArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRunTrackChange[] getDelArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[26], new CTRunTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRunTrackChange getDelArray(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[26], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfDelArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[26]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setDelArray(CTRunTrackChange[] delArray) {
        check_orphaned();
        arraySetterHelper(delArray, PROPERTY_QNAME[26]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setDelArray(int i, CTRunTrackChange del) {
        generatedSetterHelperImpl(del, PROPERTY_QNAME[26], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRunTrackChange insertNewDel(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[26], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRunTrackChange addNewDel() {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeDel(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTRunTrackChange> getMoveFromList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda167
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getMoveFromArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda168
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setMoveFromArray(((Integer) obj).intValue(), (CTRunTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda169
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewMoveFrom(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda170
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeMoveFrom(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda171
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfMoveFromArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRunTrackChange[] getMoveFromArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[27], new CTRunTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRunTrackChange getMoveFromArray(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[27], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfMoveFromArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[27]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setMoveFromArray(CTRunTrackChange[] moveFromArray) {
        check_orphaned();
        arraySetterHelper(moveFromArray, PROPERTY_QNAME[27]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setMoveFromArray(int i, CTRunTrackChange moveFrom) {
        generatedSetterHelperImpl(moveFrom, PROPERTY_QNAME[27], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRunTrackChange insertNewMoveFrom(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[27], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRunTrackChange addNewMoveFrom() {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeMoveFrom(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTRunTrackChange> getMoveToList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda128
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getMoveToArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda129
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setMoveToArray(((Integer) obj).intValue(), (CTRunTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda130
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewMoveTo(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda131
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeMoveTo(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda132
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfMoveToArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRunTrackChange[] getMoveToArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[28], new CTRunTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRunTrackChange getMoveToArray(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[28], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfMoveToArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[28]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setMoveToArray(CTRunTrackChange[] moveToArray) {
        check_orphaned();
        arraySetterHelper(moveToArray, PROPERTY_QNAME[28]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setMoveToArray(int i, CTRunTrackChange moveTo) {
        generatedSetterHelperImpl(moveTo, PROPERTY_QNAME[28], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRunTrackChange insertNewMoveTo(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[28], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRunTrackChange addNewMoveTo() {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeMoveTo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTOMathPara> getOMathParaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getOMathParaArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setOMathParaArray(((Integer) obj).intValue(), (CTOMathPara) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewOMathPara(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeOMathPara(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfOMathParaArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTOMathPara[] getOMathParaArray() {
        return (CTOMathPara[]) getXmlObjectArray(PROPERTY_QNAME[29], new CTOMathPara[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTOMathPara getOMathParaArray(int i) {
        CTOMathPara target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMathPara) get_store().find_element_user(PROPERTY_QNAME[29], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfOMathParaArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[29]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setOMathParaArray(CTOMathPara[] oMathParaArray) {
        check_orphaned();
        arraySetterHelper(oMathParaArray, PROPERTY_QNAME[29]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setOMathParaArray(int i, CTOMathPara oMathPara) {
        generatedSetterHelperImpl(oMathPara, PROPERTY_QNAME[29], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTOMathPara insertNewOMathPara(int i) {
        CTOMathPara target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMathPara) get_store().insert_element_user(PROPERTY_QNAME[29], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTOMathPara addNewOMathPara() {
        CTOMathPara target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMathPara) get_store().add_element_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeOMathPara(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTOMath> getOMathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getOMathArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda111
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setOMathArray(((Integer) obj).intValue(), (CTOMath) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda177
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewOMath(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda188
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeOMath(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda199
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfOMathArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTOMath[] getOMathArray() {
        return (CTOMath[]) getXmlObjectArray(PROPERTY_QNAME[30], new CTOMath[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTOMath getOMathArray(int i) {
        CTOMath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMath) get_store().find_element_user(PROPERTY_QNAME[30], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfOMathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[30]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setOMathArray(CTOMath[] oMathArray) {
        check_orphaned();
        arraySetterHelper(oMathArray, PROPERTY_QNAME[30]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setOMathArray(int i, CTOMath oMath) {
        generatedSetterHelperImpl(oMath, PROPERTY_QNAME[30], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTOMath insertNewOMath(int i) {
        CTOMath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMath) get_store().insert_element_user(PROPERTY_QNAME[30], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTOMath addNewOMath() {
        CTOMath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMath) get_store().add_element_user(PROPERTY_QNAME[30]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeOMath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[30], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTAcc> getAccList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda139
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getAccArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda140
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setAccArray(((Integer) obj).intValue(), (CTAcc) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda141
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewAcc(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda142
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeAcc(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda143
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfAccArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTAcc[] getAccArray() {
        return getXmlObjectArray(PROPERTY_QNAME[31], (XmlObject[]) new CTAcc[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTAcc getAccArray(int i) {
        CTAcc target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[31], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfAccArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[31]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setAccArray(CTAcc[] accArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) accArray, PROPERTY_QNAME[31]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setAccArray(int i, CTAcc acc) {
        generatedSetterHelperImpl(acc, PROPERTY_QNAME[31], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTAcc insertNewAcc(int i) {
        CTAcc target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[31], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTAcc addNewAcc() {
        CTAcc target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[31]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeAcc(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[31], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTBar> getBarList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda112
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getBarArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda113
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setBarArray(((Integer) obj).intValue(), (CTBar) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda114
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewBar(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda115
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeBar(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda116
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfBarArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBar[] getBarArray() {
        return getXmlObjectArray(PROPERTY_QNAME[32], (XmlObject[]) new CTBar[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBar getBarArray(int i) {
        CTBar target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[32], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfBarArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[32]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setBarArray(CTBar[] barArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) barArray, PROPERTY_QNAME[32]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setBarArray(int i, CTBar bar) {
        generatedSetterHelperImpl(bar, PROPERTY_QNAME[32], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBar insertNewBar(int i) {
        CTBar target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[32], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBar addNewBar() {
        CTBar target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[32]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeBar(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[32], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTBox> getBoxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda72
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getBoxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda73
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setBoxArray(((Integer) obj).intValue(), (CTBox) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda74
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewBox(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda75
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeBox(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda76
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfBoxArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBox[] getBoxArray() {
        return getXmlObjectArray(PROPERTY_QNAME[33], (XmlObject[]) new CTBox[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBox getBoxArray(int i) {
        CTBox target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[33], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfBoxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[33]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setBoxArray(CTBox[] boxArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) boxArray, PROPERTY_QNAME[33]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setBoxArray(int i, CTBox box) {
        generatedSetterHelperImpl(box, PROPERTY_QNAME[33], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBox insertNewBox(int i) {
        CTBox target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[33], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBox addNewBox() {
        CTBox target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[33]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeBox(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[33], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTBorderBox> getBorderBoxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda227
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getBorderBoxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda228
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setBorderBoxArray(((Integer) obj).intValue(), (CTBorderBox) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda229
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewBorderBox(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda230
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeBorderBox(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda231
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfBorderBoxArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBorderBox[] getBorderBoxArray() {
        return getXmlObjectArray(PROPERTY_QNAME[34], (XmlObject[]) new CTBorderBox[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBorderBox getBorderBoxArray(int i) {
        CTBorderBox target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[34], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfBorderBoxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[34]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setBorderBoxArray(CTBorderBox[] borderBoxArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) borderBoxArray, PROPERTY_QNAME[34]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setBorderBoxArray(int i, CTBorderBox borderBox) {
        generatedSetterHelperImpl(borderBox, PROPERTY_QNAME[34], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBorderBox insertNewBorderBox(int i) {
        CTBorderBox target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[34], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTBorderBox addNewBorderBox() {
        CTBorderBox target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[34]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeBorderBox(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[34], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTD> getDList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda105
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getDArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda106
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setDArray(((Integer) obj).intValue(), (CTD) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda107
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewD(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda108
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeD(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda109
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfDArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTD[] getDArray() {
        return (CTD[]) getXmlObjectArray(PROPERTY_QNAME[35], new CTD[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTD getDArray(int i) {
        CTD target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTD) get_store().find_element_user(PROPERTY_QNAME[35], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfDArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[35]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setDArray(CTD[] dArray) {
        check_orphaned();
        arraySetterHelper(dArray, PROPERTY_QNAME[35]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setDArray(int i, CTD d) {
        generatedSetterHelperImpl(d, PROPERTY_QNAME[35], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTD insertNewD(int i) {
        CTD target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTD) get_store().insert_element_user(PROPERTY_QNAME[35], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTD addNewD() {
        CTD target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTD) get_store().add_element_user(PROPERTY_QNAME[35]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeD(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[35], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTEqArr> getEqArrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getEqArrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setEqArrArray(((Integer) obj).intValue(), (CTEqArr) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewEqArr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeEqArr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfEqArrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTEqArr[] getEqArrArray() {
        return getXmlObjectArray(PROPERTY_QNAME[36], (XmlObject[]) new CTEqArr[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTEqArr getEqArrArray(int i) {
        CTEqArr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[36], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfEqArrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[36]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setEqArrArray(CTEqArr[] eqArrArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) eqArrArray, PROPERTY_QNAME[36]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setEqArrArray(int i, CTEqArr eqArr) {
        generatedSetterHelperImpl(eqArr, PROPERTY_QNAME[36], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTEqArr insertNewEqArr(int i) {
        CTEqArr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[36], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTEqArr addNewEqArr() {
        CTEqArr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[36]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeEqArr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[36], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTF> getFList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda178
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getFArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda179
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setFArray(((Integer) obj).intValue(), (CTF) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda180
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewF(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda181
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeF(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda182
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfFArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTF[] getFArray() {
        return getXmlObjectArray(PROPERTY_QNAME[37], (XmlObject[]) new CTF[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTF getFArray(int i) {
        CTF target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[37], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfFArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[37]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setFArray(CTF[] fArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) fArray, PROPERTY_QNAME[37]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setFArray(int i, CTF f) {
        generatedSetterHelperImpl(f, PROPERTY_QNAME[37], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTF insertNewF(int i) {
        CTF target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[37], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTF addNewF() {
        CTF target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[37]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeF(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[37], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTFunc> getFuncList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda122
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getFuncArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda133
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setFuncArray(((Integer) obj).intValue(), (CTFunc) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda144
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewFunc(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda155
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeFunc(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda166
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfFuncArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTFunc[] getFuncArray() {
        return getXmlObjectArray(PROPERTY_QNAME[38], (XmlObject[]) new CTFunc[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTFunc getFuncArray(int i) {
        CTFunc target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[38], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfFuncArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[38]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setFuncArray(CTFunc[] funcArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) funcArray, PROPERTY_QNAME[38]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setFuncArray(int i, CTFunc func) {
        generatedSetterHelperImpl(func, PROPERTY_QNAME[38], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTFunc insertNewFunc(int i) {
        CTFunc target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[38], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTFunc addNewFunc() {
        CTFunc target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[38]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeFunc(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[38], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTGroupChr> getGroupChrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda222
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getGroupChrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda223
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setGroupChrArray(((Integer) obj).intValue(), (CTGroupChr) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda224
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewGroupChr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda225
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeGroupChr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda226
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfGroupChrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTGroupChr[] getGroupChrArray() {
        return getXmlObjectArray(PROPERTY_QNAME[39], (XmlObject[]) new CTGroupChr[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTGroupChr getGroupChrArray(int i) {
        CTGroupChr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[39], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfGroupChrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[39]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setGroupChrArray(CTGroupChr[] groupChrArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) groupChrArray, PROPERTY_QNAME[39]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setGroupChrArray(int i, CTGroupChr groupChr) {
        generatedSetterHelperImpl(groupChr, PROPERTY_QNAME[39], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTGroupChr insertNewGroupChr(int i) {
        CTGroupChr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[39], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTGroupChr addNewGroupChr() {
        CTGroupChr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[39]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeGroupChr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[39], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTLimLow> getLimLowList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda244
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getLimLowArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda245
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setLimLowArray(((Integer) obj).intValue(), (CTLimLow) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda246
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewLimLow(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda247
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeLimLow(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda248
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfLimLowArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTLimLow[] getLimLowArray() {
        return getXmlObjectArray(PROPERTY_QNAME[40], (XmlObject[]) new CTLimLow[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTLimLow getLimLowArray(int i) {
        CTLimLow target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[40], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfLimLowArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[40]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setLimLowArray(CTLimLow[] limLowArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) limLowArray, PROPERTY_QNAME[40]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setLimLowArray(int i, CTLimLow limLow) {
        generatedSetterHelperImpl(limLow, PROPERTY_QNAME[40], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTLimLow insertNewLimLow(int i) {
        CTLimLow target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[40], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTLimLow addNewLimLow() {
        CTLimLow target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[40]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeLimLow(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[40], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTLimUpp> getLimUppList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda238
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getLimUppArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda239
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setLimUppArray(((Integer) obj).intValue(), (CTLimUpp) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda240
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewLimUpp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda241
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeLimUpp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda242
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfLimUppArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTLimUpp[] getLimUppArray() {
        return getXmlObjectArray(PROPERTY_QNAME[41], (XmlObject[]) new CTLimUpp[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTLimUpp getLimUppArray(int i) {
        CTLimUpp target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[41], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfLimUppArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[41]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setLimUppArray(CTLimUpp[] limUppArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) limUppArray, PROPERTY_QNAME[41]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setLimUppArray(int i, CTLimUpp limUpp) {
        generatedSetterHelperImpl(limUpp, PROPERTY_QNAME[41], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTLimUpp insertNewLimUpp(int i) {
        CTLimUpp target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[41], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTLimUpp addNewLimUpp() {
        CTLimUpp target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[41]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeLimUpp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[41], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTM> getMList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda117
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getMArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda118
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setMArray(((Integer) obj).intValue(), (CTM) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda119
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewM(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda120
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeM(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda121
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfMArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTM[] getMArray() {
        return (CTM[]) getXmlObjectArray(PROPERTY_QNAME[42], new CTM[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTM getMArray(int i) {
        CTM target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTM) get_store().find_element_user(PROPERTY_QNAME[42], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfMArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[42]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setMArray(CTM[] mArray) {
        check_orphaned();
        arraySetterHelper(mArray, PROPERTY_QNAME[42]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setMArray(int i, CTM m) {
        generatedSetterHelperImpl(m, PROPERTY_QNAME[42], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTM insertNewM(int i) {
        CTM target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTM) get_store().insert_element_user(PROPERTY_QNAME[42], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTM addNewM() {
        CTM target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTM) get_store().add_element_user(PROPERTY_QNAME[42]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeM(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[42], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTNary> getNaryList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda100
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getNaryArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda101
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setNaryArray(((Integer) obj).intValue(), (CTNary) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda102
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewNary(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda103
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeNary(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda104
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfNaryArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTNary[] getNaryArray() {
        return getXmlObjectArray(PROPERTY_QNAME[43], (XmlObject[]) new CTNary[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTNary getNaryArray(int i) {
        CTNary target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[43], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfNaryArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[43]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setNaryArray(CTNary[] naryArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) naryArray, PROPERTY_QNAME[43]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setNaryArray(int i, CTNary nary) {
        generatedSetterHelperImpl(nary, PROPERTY_QNAME[43], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTNary insertNewNary(int i) {
        CTNary target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[43], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTNary addNewNary() {
        CTNary target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[43]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeNary(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[43], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTPhant> getPhantList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda66
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getPhantArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda77
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setPhantArray(((Integer) obj).intValue(), (CTPhant) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda88
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewPhant(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda99
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removePhant(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda110
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfPhantArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTPhant[] getPhantArray() {
        return getXmlObjectArray(PROPERTY_QNAME[44], (XmlObject[]) new CTPhant[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTPhant getPhantArray(int i) {
        CTPhant target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[44], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfPhantArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[44]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setPhantArray(CTPhant[] phantArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) phantArray, PROPERTY_QNAME[44]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setPhantArray(int i, CTPhant phant) {
        generatedSetterHelperImpl(phant, PROPERTY_QNAME[44], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTPhant insertNewPhant(int i) {
        CTPhant target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[44], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTPhant addNewPhant() {
        CTPhant target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[44]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removePhant(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[44], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTRad> getRadList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda172
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getRadArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda173
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setRadArray(((Integer) obj).intValue(), (CTRad) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda174
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewRad(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda175
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeRad(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda176
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfRadArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRad[] getRadArray() {
        return getXmlObjectArray(PROPERTY_QNAME[45], (XmlObject[]) new CTRad[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRad getRadArray(int i) {
        CTRad target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[45], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfRadArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[45]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setRadArray(CTRad[] radArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) radArray, PROPERTY_QNAME[45]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setRadArray(int i, CTRad rad) {
        generatedSetterHelperImpl(rad, PROPERTY_QNAME[45], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRad insertNewRad(int i) {
        CTRad target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[45], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTRad addNewRad() {
        CTRad target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[45]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeRad(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[45], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTSPre> getSPreList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda94
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getSPreArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda95
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setSPreArray(((Integer) obj).intValue(), (CTSPre) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda96
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewSPre(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda97
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeSPre(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda98
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfSPreArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSPre[] getSPreArray() {
        return getXmlObjectArray(PROPERTY_QNAME[46], (XmlObject[]) new CTSPre[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSPre getSPreArray(int i) {
        CTSPre target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[46], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfSPreArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[46]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setSPreArray(CTSPre[] sPreArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) sPreArray, PROPERTY_QNAME[46]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setSPreArray(int i, CTSPre sPre) {
        generatedSetterHelperImpl(sPre, PROPERTY_QNAME[46], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSPre insertNewSPre(int i) {
        CTSPre target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[46], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSPre addNewSPre() {
        CTSPre target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[46]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeSPre(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[46], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTSSub> getSSubList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda205
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getSSubArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda206
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setSSubArray(((Integer) obj).intValue(), (CTSSub) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda207
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewSSub(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda208
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeSSub(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda209
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfSSubArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSSub[] getSSubArray() {
        return (CTSSub[]) getXmlObjectArray(PROPERTY_QNAME[47], new CTSSub[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSSub getSSubArray(int i) {
        CTSSub target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSSub) get_store().find_element_user(PROPERTY_QNAME[47], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfSSubArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[47]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setSSubArray(CTSSub[] sSubArray) {
        check_orphaned();
        arraySetterHelper(sSubArray, PROPERTY_QNAME[47]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setSSubArray(int i, CTSSub sSub) {
        generatedSetterHelperImpl(sSub, PROPERTY_QNAME[47], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSSub insertNewSSub(int i) {
        CTSSub target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSSub) get_store().insert_element_user(PROPERTY_QNAME[47], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSSub addNewSSub() {
        CTSSub target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSSub) get_store().add_element_user(PROPERTY_QNAME[47]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeSSub(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[47], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTSSubSup> getSSubSupList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda83
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getSSubSupArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda84
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setSSubSupArray(((Integer) obj).intValue(), (CTSSubSup) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda85
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewSSubSup(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda86
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeSSubSup(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda87
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfSSubSupArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSSubSup[] getSSubSupArray() {
        return getXmlObjectArray(PROPERTY_QNAME[48], (XmlObject[]) new CTSSubSup[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSSubSup getSSubSupArray(int i) {
        CTSSubSup target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[48], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfSSubSupArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[48]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setSSubSupArray(CTSSubSup[] sSubSupArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) sSubSupArray, PROPERTY_QNAME[48]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setSSubSupArray(int i, CTSSubSup sSubSup) {
        generatedSetterHelperImpl(sSubSup, PROPERTY_QNAME[48], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSSubSup insertNewSSubSup(int i) {
        CTSSubSup target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[48], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSSubSup addNewSSubSup() {
        CTSSubSup target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[48]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeSSubSup(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[48], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<CTSSup> getSSupList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda183
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getSSupArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda184
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setSSupArray(((Integer) obj).intValue(), (CTSSup) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda185
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewSSup(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda186
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeSSup(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda187
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfSSupArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSSup[] getSSupArray() {
        return getXmlObjectArray(PROPERTY_QNAME[49], (XmlObject[]) new CTSSup[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSSup getSSupArray(int i) {
        CTSSup target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[49], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfSSupArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[49]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setSSupArray(CTSSup[] sSupArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) sSupArray, PROPERTY_QNAME[49]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setSSupArray(int i, CTSSup sSup) {
        generatedSetterHelperImpl(sSup, PROPERTY_QNAME[49], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSSup insertNewSSup(int i) {
        CTSSup target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[49], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public CTSSup addNewSSup() {
        CTSSup target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[49]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeSSup(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[49], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public List<org.openxmlformats.schemas.officeDocument.x2006.math.CTR> getR2List() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda189
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.getR2Array(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda190
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRunTrackChangeImpl.this.setR2Array(((Integer) obj).intValue(), (org.openxmlformats.schemas.officeDocument.x2006.math.CTR) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda191
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRunTrackChangeImpl.this.insertNewR2(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda192
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRunTrackChangeImpl.this.removeR2(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRunTrackChangeImpl$$ExternalSyntheticLambda193
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRunTrackChangeImpl.this.sizeOfR2Array());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public org.openxmlformats.schemas.officeDocument.x2006.math.CTR[] getR2Array() {
        return (org.openxmlformats.schemas.officeDocument.x2006.math.CTR[]) getXmlObjectArray(PROPERTY_QNAME[50], new org.openxmlformats.schemas.officeDocument.x2006.math.CTR[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public org.openxmlformats.schemas.officeDocument.x2006.math.CTR getR2Array(int i) {
        org.openxmlformats.schemas.officeDocument.x2006.math.CTR target;
        synchronized (monitor()) {
            check_orphaned();
            target = (org.openxmlformats.schemas.officeDocument.x2006.math.CTR) get_store().find_element_user(PROPERTY_QNAME[50], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public int sizeOfR2Array() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[50]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setR2Array(org.openxmlformats.schemas.officeDocument.x2006.math.CTR[] r2Array) {
        check_orphaned();
        arraySetterHelper(r2Array, PROPERTY_QNAME[50]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void setR2Array(int i, org.openxmlformats.schemas.officeDocument.x2006.math.CTR r2) {
        generatedSetterHelperImpl(r2, PROPERTY_QNAME[50], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public org.openxmlformats.schemas.officeDocument.x2006.math.CTR insertNewR2(int i) {
        org.openxmlformats.schemas.officeDocument.x2006.math.CTR target;
        synchronized (monitor()) {
            check_orphaned();
            target = (org.openxmlformats.schemas.officeDocument.x2006.math.CTR) get_store().insert_element_user(PROPERTY_QNAME[50], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public org.openxmlformats.schemas.officeDocument.x2006.math.CTR addNewR2() {
        org.openxmlformats.schemas.officeDocument.x2006.math.CTR target;
        synchronized (monitor()) {
            check_orphaned();
            target = (org.openxmlformats.schemas.officeDocument.x2006.math.CTR) get_store().add_element_user(PROPERTY_QNAME[50]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange
    public void removeR2(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[50], i);
        }
    }
}
