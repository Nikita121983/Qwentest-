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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFCheckBox;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFDDList;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFHelpText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFName;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFStatusText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFTextInput;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMacroName;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnsignedDecimalNumber;

/* loaded from: classes12.dex */
public class CTFFDataImpl extends XmlComplexContentImpl implements CTFFData {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "name"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "label"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tabIndex"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "enabled"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "calcOnExit"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "entryMacro"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "exitMacro"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "helpText"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "statusText"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "checkBox"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "ddList"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "textInput")};
    private static final long serialVersionUID = 1;

    public CTFFDataImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public List<CTFFName> getNameList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.getNameArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFFDataImpl.this.setNameArray(((Integer) obj).intValue(), (CTFFName) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.insertNewName(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFFDataImpl.this.removeName(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFFDataImpl.this.sizeOfNameArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFName[] getNameArray() {
        return (CTFFName[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTFFName[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFName getNameArray(int i) {
        CTFFName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFFName) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public int sizeOfNameArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setNameArray(CTFFName[] nameArray) {
        check_orphaned();
        arraySetterHelper(nameArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setNameArray(int i, CTFFName name) {
        generatedSetterHelperImpl(name, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFName insertNewName(int i) {
        CTFFName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFFName) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFName addNewName() {
        CTFFName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFFName) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void removeName(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public List<CTDecimalNumber> getLabelList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.getLabelArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFFDataImpl.this.setLabelArray(((Integer) obj).intValue(), (CTDecimalNumber) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.insertNewLabel(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFFDataImpl.this.removeLabel(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFFDataImpl.this.sizeOfLabelArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTDecimalNumber[] getLabelArray() {
        return (CTDecimalNumber[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTDecimalNumber[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTDecimalNumber getLabelArray(int i) {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public int sizeOfLabelArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setLabelArray(CTDecimalNumber[] labelArray) {
        check_orphaned();
        arraySetterHelper(labelArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setLabelArray(int i, CTDecimalNumber label) {
        generatedSetterHelperImpl(label, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTDecimalNumber insertNewLabel(int i) {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTDecimalNumber addNewLabel() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void removeLabel(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public List<CTUnsignedDecimalNumber> getTabIndexList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.getTabIndexArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFFDataImpl.this.setTabIndexArray(((Integer) obj).intValue(), (CTUnsignedDecimalNumber) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.insertNewTabIndex(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFFDataImpl.this.removeTabIndex(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFFDataImpl.this.sizeOfTabIndexArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTUnsignedDecimalNumber[] getTabIndexArray() {
        return getXmlObjectArray(PROPERTY_QNAME[2], (XmlObject[]) new CTUnsignedDecimalNumber[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTUnsignedDecimalNumber getTabIndexArray(int i) {
        CTUnsignedDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public int sizeOfTabIndexArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setTabIndexArray(CTUnsignedDecimalNumber[] tabIndexArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) tabIndexArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setTabIndexArray(int i, CTUnsignedDecimalNumber tabIndex) {
        generatedSetterHelperImpl(tabIndex, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTUnsignedDecimalNumber insertNewTabIndex(int i) {
        CTUnsignedDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTUnsignedDecimalNumber addNewTabIndex() {
        CTUnsignedDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void removeTabIndex(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public List<CTOnOff> getEnabledList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.getEnabledArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFFDataImpl.this.setEnabledArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.insertNewEnabled(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFFDataImpl.this.removeEnabled(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFFDataImpl.this.sizeOfEnabledArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTOnOff[] getEnabledArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[3], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTOnOff getEnabledArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public int sizeOfEnabledArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setEnabledArray(CTOnOff[] enabledArray) {
        check_orphaned();
        arraySetterHelper(enabledArray, PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setEnabledArray(int i, CTOnOff enabled) {
        generatedSetterHelperImpl(enabled, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTOnOff insertNewEnabled(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTOnOff addNewEnabled() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void removeEnabled(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public List<CTOnOff> getCalcOnExitList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.getCalcOnExitArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFFDataImpl.this.setCalcOnExitArray(((Integer) obj).intValue(), (CTOnOff) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.insertNewCalcOnExit(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFFDataImpl.this.removeCalcOnExit(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFFDataImpl.this.sizeOfCalcOnExitArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTOnOff[] getCalcOnExitArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[4], new CTOnOff[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTOnOff getCalcOnExitArray(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public int sizeOfCalcOnExitArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setCalcOnExitArray(CTOnOff[] calcOnExitArray) {
        check_orphaned();
        arraySetterHelper(calcOnExitArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setCalcOnExitArray(int i, CTOnOff calcOnExit) {
        generatedSetterHelperImpl(calcOnExit, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTOnOff insertNewCalcOnExit(int i) {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTOnOff addNewCalcOnExit() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void removeCalcOnExit(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public List<CTMacroName> getEntryMacroList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.getEntryMacroArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFFDataImpl.this.setEntryMacroArray(((Integer) obj).intValue(), (CTMacroName) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.insertNewEntryMacro(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFFDataImpl.this.removeEntryMacro(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFFDataImpl.this.sizeOfEntryMacroArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTMacroName[] getEntryMacroArray() {
        return getXmlObjectArray(PROPERTY_QNAME[5], (XmlObject[]) new CTMacroName[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTMacroName getEntryMacroArray(int i) {
        CTMacroName target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public int sizeOfEntryMacroArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setEntryMacroArray(CTMacroName[] entryMacroArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) entryMacroArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setEntryMacroArray(int i, CTMacroName entryMacro) {
        generatedSetterHelperImpl(entryMacro, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTMacroName insertNewEntryMacro(int i) {
        CTMacroName target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTMacroName addNewEntryMacro() {
        CTMacroName target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void removeEntryMacro(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public List<CTMacroName> getExitMacroList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.getExitMacroArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFFDataImpl.this.setExitMacroArray(((Integer) obj).intValue(), (CTMacroName) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.insertNewExitMacro(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFFDataImpl.this.removeExitMacro(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFFDataImpl.this.sizeOfExitMacroArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTMacroName[] getExitMacroArray() {
        return getXmlObjectArray(PROPERTY_QNAME[6], (XmlObject[]) new CTMacroName[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTMacroName getExitMacroArray(int i) {
        CTMacroName target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public int sizeOfExitMacroArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setExitMacroArray(CTMacroName[] exitMacroArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) exitMacroArray, PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setExitMacroArray(int i, CTMacroName exitMacro) {
        generatedSetterHelperImpl(exitMacro, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTMacroName insertNewExitMacro(int i) {
        CTMacroName target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTMacroName addNewExitMacro() {
        CTMacroName target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void removeExitMacro(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public List<CTFFHelpText> getHelpTextList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.getHelpTextArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFFDataImpl.this.setHelpTextArray(((Integer) obj).intValue(), (CTFFHelpText) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.insertNewHelpText(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFFDataImpl.this.removeHelpText(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFFDataImpl.this.sizeOfHelpTextArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFHelpText[] getHelpTextArray() {
        return getXmlObjectArray(PROPERTY_QNAME[7], (XmlObject[]) new CTFFHelpText[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFHelpText getHelpTextArray(int i) {
        CTFFHelpText target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public int sizeOfHelpTextArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setHelpTextArray(CTFFHelpText[] helpTextArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) helpTextArray, PROPERTY_QNAME[7]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setHelpTextArray(int i, CTFFHelpText helpText) {
        generatedSetterHelperImpl(helpText, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFHelpText insertNewHelpText(int i) {
        CTFFHelpText target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFHelpText addNewHelpText() {
        CTFFHelpText target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void removeHelpText(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public List<CTFFStatusText> getStatusTextList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.getStatusTextArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFFDataImpl.this.setStatusTextArray(((Integer) obj).intValue(), (CTFFStatusText) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.insertNewStatusText(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFFDataImpl.this.removeStatusText(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFFDataImpl.this.sizeOfStatusTextArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFStatusText[] getStatusTextArray() {
        return getXmlObjectArray(PROPERTY_QNAME[8], (XmlObject[]) new CTFFStatusText[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFStatusText getStatusTextArray(int i) {
        CTFFStatusText target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public int sizeOfStatusTextArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setStatusTextArray(CTFFStatusText[] statusTextArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) statusTextArray, PROPERTY_QNAME[8]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setStatusTextArray(int i, CTFFStatusText statusText) {
        generatedSetterHelperImpl(statusText, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFStatusText insertNewStatusText(int i) {
        CTFFStatusText target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFStatusText addNewStatusText() {
        CTFFStatusText target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void removeStatusText(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public List<CTFFCheckBox> getCheckBoxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.getCheckBoxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFFDataImpl.this.setCheckBoxArray(((Integer) obj).intValue(), (CTFFCheckBox) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.insertNewCheckBox(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFFDataImpl.this.removeCheckBox(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFFDataImpl.this.sizeOfCheckBoxArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFCheckBox[] getCheckBoxArray() {
        return (CTFFCheckBox[]) getXmlObjectArray(PROPERTY_QNAME[9], new CTFFCheckBox[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFCheckBox getCheckBoxArray(int i) {
        CTFFCheckBox target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFFCheckBox) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public int sizeOfCheckBoxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setCheckBoxArray(CTFFCheckBox[] checkBoxArray) {
        check_orphaned();
        arraySetterHelper(checkBoxArray, PROPERTY_QNAME[9]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setCheckBoxArray(int i, CTFFCheckBox checkBox) {
        generatedSetterHelperImpl(checkBox, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFCheckBox insertNewCheckBox(int i) {
        CTFFCheckBox target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFFCheckBox) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFCheckBox addNewCheckBox() {
        CTFFCheckBox target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFFCheckBox) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void removeCheckBox(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public List<CTFFDDList> getDdListList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.getDdListArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFFDataImpl.this.setDdListArray(((Integer) obj).intValue(), (CTFFDDList) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.insertNewDdList(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFFDataImpl.this.removeDdList(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFFDataImpl.this.sizeOfDdListArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFDDList[] getDdListArray() {
        return getXmlObjectArray(PROPERTY_QNAME[10], (XmlObject[]) new CTFFDDList[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFDDList getDdListArray(int i) {
        CTFFDDList target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public int sizeOfDdListArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setDdListArray(CTFFDDList[] ddListArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) ddListArray, PROPERTY_QNAME[10]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setDdListArray(int i, CTFFDDList ddList) {
        generatedSetterHelperImpl(ddList, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFDDList insertNewDdList(int i) {
        CTFFDDList target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFDDList addNewDdList() {
        CTFFDDList target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void removeDdList(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public List<CTFFTextInput> getTextInputList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.getTextInputArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFFDataImpl.this.setTextInputArray(((Integer) obj).intValue(), (CTFFTextInput) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFFDataImpl.this.insertNewTextInput(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFFDataImpl.this.removeTextInput(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFFDataImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFFDataImpl.this.sizeOfTextInputArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFTextInput[] getTextInputArray() {
        return (CTFFTextInput[]) getXmlObjectArray(PROPERTY_QNAME[11], new CTFFTextInput[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFTextInput getTextInputArray(int i) {
        CTFFTextInput target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFFTextInput) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public int sizeOfTextInputArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setTextInputArray(CTFFTextInput[] textInputArray) {
        check_orphaned();
        arraySetterHelper(textInputArray, PROPERTY_QNAME[11]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void setTextInputArray(int i, CTFFTextInput textInput) {
        generatedSetterHelperImpl(textInput, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFTextInput insertNewTextInput(int i) {
        CTFFTextInput target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFFTextInput) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public CTFFTextInput addNewTextInput() {
        CTFFTextInput target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFFTextInput) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData
    public void removeTextInput(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }
}
