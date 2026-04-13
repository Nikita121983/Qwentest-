package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJcTable;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase;

/* loaded from: classes12.dex */
public class CTTrPrBaseImpl extends XmlComplexContentImpl implements CTTrPrBase {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "cnfStyle"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "divId"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "gridBefore"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "gridAfter"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "wBefore"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "wAfter"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "cantSplit"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "trHeight"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblHeader"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblCellSpacing"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "jc"), new QName(XSSFRelation.NS_WORDPROCESSINGML, CellUtil.HIDDEN)};
    private static final long serialVersionUID = 1;

    public CTTrPrBaseImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public List<CTCnf> getCnfStyleList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.getCnfStyleArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTrPrBaseImpl.this.setCnfStyleArray(((Integer) obj).intValue(), (CTCnf) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.insertNewCnfStyle(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTrPrBaseImpl.this.removeCnfStyle(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTrPrBaseImpl.this.sizeOfCnfStyleArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTCnf[] getCnfStyleArray() {
        return (CTCnf[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTCnf[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTCnf getCnfStyleArray(int i) {
        CTCnf target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCnf) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public int sizeOfCnfStyleArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setCnfStyleArray(CTCnf[] cnfStyleArray) {
        check_orphaned();
        arraySetterHelper(cnfStyleArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setCnfStyleArray(int i, CTCnf cnfStyle) {
        generatedSetterHelperImpl(cnfStyle, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTCnf insertNewCnfStyle(int i) {
        CTCnf target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCnf) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTCnf addNewCnfStyle() {
        CTCnf target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCnf) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void removeCnfStyle(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public List<CTDecimalNumber> getDivIdList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.getDivIdArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTrPrBaseImpl.this.setDivIdArray(((Integer) obj).intValue(), (CTDecimalNumber) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.insertNewDivId(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTrPrBaseImpl.this.removeDivId(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTrPrBaseImpl.this.sizeOfDivIdArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTDecimalNumber[] getDivIdArray() {
        return (CTDecimalNumber[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTDecimalNumber[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTDecimalNumber getDivIdArray(int i) {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public int sizeOfDivIdArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setDivIdArray(CTDecimalNumber[] divIdArray) {
        check_orphaned();
        arraySetterHelper(divIdArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setDivIdArray(int i, CTDecimalNumber divId) {
        generatedSetterHelperImpl(divId, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTDecimalNumber insertNewDivId(int i) {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTDecimalNumber addNewDivId() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void removeDivId(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public List<CTDecimalNumber> getGridBeforeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.getGridBeforeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTrPrBaseImpl.this.setGridBeforeArray(((Integer) obj).intValue(), (CTDecimalNumber) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.insertNewGridBefore(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTrPrBaseImpl.this.removeGridBefore(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTrPrBaseImpl.this.sizeOfGridBeforeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTDecimalNumber[] getGridBeforeArray() {
        return (CTDecimalNumber[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTDecimalNumber[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTDecimalNumber getGridBeforeArray(int i) {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public int sizeOfGridBeforeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setGridBeforeArray(CTDecimalNumber[] gridBeforeArray) {
        check_orphaned();
        arraySetterHelper(gridBeforeArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setGridBeforeArray(int i, CTDecimalNumber gridBefore) {
        generatedSetterHelperImpl(gridBefore, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTDecimalNumber insertNewGridBefore(int i) {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTDecimalNumber addNewGridBefore() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void removeGridBefore(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public List<CTDecimalNumber> getGridAfterList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.getGridAfterArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTrPrBaseImpl.this.setGridAfterArray(((Integer) obj).intValue(), (CTDecimalNumber) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.insertNewGridAfter(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTrPrBaseImpl.this.removeGridAfter(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTrPrBaseImpl.this.sizeOfGridAfterArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTDecimalNumber[] getGridAfterArray() {
        return (CTDecimalNumber[]) getXmlObjectArray(PROPERTY_QNAME[3], new CTDecimalNumber[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTDecimalNumber getGridAfterArray(int i) {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public int sizeOfGridAfterArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setGridAfterArray(CTDecimalNumber[] gridAfterArray) {
        check_orphaned();
        arraySetterHelper(gridAfterArray, PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setGridAfterArray(int i, CTDecimalNumber gridAfter) {
        generatedSetterHelperImpl(gridAfter, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTDecimalNumber insertNewGridAfter(int i) {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTDecimalNumber addNewGridAfter() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void removeGridAfter(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public List<CTTblWidth> getWBeforeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.getWBeforeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTrPrBaseImpl.this.setWBeforeArray(((Integer) obj).intValue(), (CTTblWidth) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.insertNewWBefore(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTrPrBaseImpl.this.removeWBefore(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTrPrBaseImpl.this.sizeOfWBeforeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTTblWidth[] getWBeforeArray() {
        return (CTTblWidth[]) getXmlObjectArray(PROPERTY_QNAME[4], new CTTblWidth[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTTblWidth getWBeforeArray(int i) {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public int sizeOfWBeforeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setWBeforeArray(CTTblWidth[] wBeforeArray) {
        check_orphaned();
        arraySetterHelper(wBeforeArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setWBeforeArray(int i, CTTblWidth wBefore) {
        generatedSetterHelperImpl(wBefore, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTTblWidth insertNewWBefore(int i) {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTTblWidth addNewWBefore() {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void removeWBefore(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public List<CTTblWidth> getWAfterList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.getWAfterArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTrPrBaseImpl.this.setWAfterArray(((Integer) obj).intValue(), (CTTblWidth) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.insertNewWAfter(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTrPrBaseImpl.this.removeWAfter(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTrPrBaseImpl.this.sizeOfWAfterArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTTblWidth[] getWAfterArray() {
        return (CTTblWidth[]) getXmlObjectArray(PROPERTY_QNAME[5], new CTTblWidth[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTTblWidth getWAfterArray(int i) {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public int sizeOfWAfterArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setWAfterArray(CTTblWidth[] wAfterArray) {
        check_orphaned();
        arraySetterHelper(wAfterArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setWAfterArray(int i, CTTblWidth wAfter) {
        generatedSetterHelperImpl(wAfter, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTTblWidth insertNewWAfter(int i) {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTTblWidth addNewWAfter() {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void removeWAfter(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public List<CTOnOff> getCantSplitList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.getCantSplitArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTrPrBaseImpl.this.setCantSplitArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.insertNewCantSplit(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTrPrBaseImpl.this.removeCantSplit(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTrPrBaseImpl.this.sizeOfCantSplitArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTOnOff[] getCantSplitArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[6], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTOnOff getCantSplitArray(int i) {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public int sizeOfCantSplitArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setCantSplitArray(CTOnOff[] cantSplitArray) {
        check_orphaned();
        arraySetterHelper(cantSplitArray, PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setCantSplitArray(int i, CTOnOff cantSplit) {
        generatedSetterHelperImpl(cantSplit, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTOnOff insertNewCantSplit(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTOnOff addNewCantSplit() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void removeCantSplit(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public List<CTHeight> getTrHeightList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.getTrHeightArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTrPrBaseImpl.this.setTrHeightArray(((Integer) obj).intValue(), (CTHeight) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.insertNewTrHeight(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTrPrBaseImpl.this.removeTrHeight(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTrPrBaseImpl.this.sizeOfTrHeightArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTHeight[] getTrHeightArray() {
        return (CTHeight[]) getXmlObjectArray(PROPERTY_QNAME[7], new CTHeight[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTHeight getTrHeightArray(int i) {
        CTHeight target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHeight) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public int sizeOfTrHeightArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setTrHeightArray(CTHeight[] trHeightArray) {
        check_orphaned();
        arraySetterHelper(trHeightArray, PROPERTY_QNAME[7]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setTrHeightArray(int i, CTHeight trHeight) {
        generatedSetterHelperImpl(trHeight, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTHeight insertNewTrHeight(int i) {
        CTHeight target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHeight) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTHeight addNewTrHeight() {
        CTHeight target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHeight) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void removeTrHeight(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public List<CTOnOff> getTblHeaderList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.getTblHeaderArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTrPrBaseImpl.this.setTblHeaderArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.insertNewTblHeader(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTrPrBaseImpl.this.removeTblHeader(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTrPrBaseImpl.this.sizeOfTblHeaderArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTOnOff[] getTblHeaderArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[8], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTOnOff getTblHeaderArray(int i) {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public int sizeOfTblHeaderArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setTblHeaderArray(CTOnOff[] tblHeaderArray) {
        check_orphaned();
        arraySetterHelper(tblHeaderArray, PROPERTY_QNAME[8]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setTblHeaderArray(int i, CTOnOff tblHeader) {
        generatedSetterHelperImpl(tblHeader, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTOnOff insertNewTblHeader(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTOnOff addNewTblHeader() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void removeTblHeader(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public List<CTTblWidth> getTblCellSpacingList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.getTblCellSpacingArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTrPrBaseImpl.this.setTblCellSpacingArray(((Integer) obj).intValue(), (CTTblWidth) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.insertNewTblCellSpacing(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTrPrBaseImpl.this.removeTblCellSpacing(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTrPrBaseImpl.this.sizeOfTblCellSpacingArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTTblWidth[] getTblCellSpacingArray() {
        return (CTTblWidth[]) getXmlObjectArray(PROPERTY_QNAME[9], new CTTblWidth[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTTblWidth getTblCellSpacingArray(int i) {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public int sizeOfTblCellSpacingArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setTblCellSpacingArray(CTTblWidth[] tblCellSpacingArray) {
        check_orphaned();
        arraySetterHelper(tblCellSpacingArray, PROPERTY_QNAME[9]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setTblCellSpacingArray(int i, CTTblWidth tblCellSpacing) {
        generatedSetterHelperImpl(tblCellSpacing, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTTblWidth insertNewTblCellSpacing(int i) {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTTblWidth addNewTblCellSpacing() {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void removeTblCellSpacing(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public List<CTJcTable> getJcList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.getJcArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTrPrBaseImpl.this.setJcArray(((Integer) obj).intValue(), (CTJcTable) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.insertNewJc(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTrPrBaseImpl.this.removeJc(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTrPrBaseImpl.this.sizeOfJcArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTJcTable[] getJcArray() {
        return (CTJcTable[]) getXmlObjectArray(PROPERTY_QNAME[10], new CTJcTable[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTJcTable getJcArray(int i) {
        CTJcTable target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTJcTable) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public int sizeOfJcArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setJcArray(CTJcTable[] jcArray) {
        check_orphaned();
        arraySetterHelper(jcArray, PROPERTY_QNAME[10]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setJcArray(int i, CTJcTable jc) {
        generatedSetterHelperImpl(jc, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTJcTable insertNewJc(int i) {
        CTJcTable target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTJcTable) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTJcTable addNewJc() {
        CTJcTable target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTJcTable) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void removeJc(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public List<CTOnOff> getHiddenList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.getHiddenArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTrPrBaseImpl.this.setHiddenArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTrPrBaseImpl.this.insertNewHidden(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTrPrBaseImpl.this.removeHidden(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTrPrBaseImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTrPrBaseImpl.this.sizeOfHiddenArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTOnOff[] getHiddenArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[11], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTOnOff getHiddenArray(int i) {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public int sizeOfHiddenArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setHiddenArray(CTOnOff[] hiddenArray) {
        check_orphaned();
        arraySetterHelper(hiddenArray, PROPERTY_QNAME[11]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void setHiddenArray(int i, CTOnOff hidden) {
        generatedSetterHelperImpl(hidden, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTOnOff insertNewHidden(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public CTOnOff addNewHidden() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase
    public void removeHidden(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }
}
