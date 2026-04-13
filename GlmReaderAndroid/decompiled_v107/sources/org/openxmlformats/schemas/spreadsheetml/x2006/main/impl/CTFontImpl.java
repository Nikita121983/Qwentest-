package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

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
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBooleanProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontFamily;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontScheme;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontSize;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIntProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTUnderlineProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVerticalAlignFontProperty;

/* loaded from: classes12.dex */
public class CTFontImpl extends XmlComplexContentImpl implements CTFont {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "name"), new QName(XSSFRelation.NS_SPREADSHEETML, "charset"), new QName(XSSFRelation.NS_SPREADSHEETML, "family"), new QName(XSSFRelation.NS_SPREADSHEETML, "b"), new QName(XSSFRelation.NS_SPREADSHEETML, Complex.DEFAULT_SUFFIX), new QName(XSSFRelation.NS_SPREADSHEETML, "strike"), new QName(XSSFRelation.NS_SPREADSHEETML, "outline"), new QName(XSSFRelation.NS_SPREADSHEETML, "shadow"), new QName(XSSFRelation.NS_SPREADSHEETML, "condense"), new QName(XSSFRelation.NS_SPREADSHEETML, "extend"), new QName(XSSFRelation.NS_SPREADSHEETML, TypedValues.Custom.S_COLOR), new QName(XSSFRelation.NS_SPREADSHEETML, "sz"), new QName(XSSFRelation.NS_SPREADSHEETML, "u"), new QName(XSSFRelation.NS_SPREADSHEETML, "vertAlign"), new QName(XSSFRelation.NS_SPREADSHEETML, "scheme")};
    private static final long serialVersionUID = 1;

    public CTFontImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public List<CTFontName> getNameList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.getNameArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFontImpl.this.setNameArray(((Integer) obj).intValue(), (CTFontName) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.insertNewName(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFontImpl.this.removeName(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFontImpl.this.sizeOfNameArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTFontName[] getNameArray() {
        return (CTFontName[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTFontName[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTFontName getNameArray(int i) {
        CTFontName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFontName) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public int sizeOfNameArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setNameArray(CTFontName[] nameArray) {
        check_orphaned();
        arraySetterHelper(nameArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setNameArray(int i, CTFontName name) {
        generatedSetterHelperImpl(name, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTFontName insertNewName(int i) {
        CTFontName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFontName) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTFontName addNewName() {
        CTFontName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFontName) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void removeName(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public List<CTIntProperty> getCharsetList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.getCharsetArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFontImpl.this.setCharsetArray(((Integer) obj).intValue(), (CTIntProperty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.insertNewCharset(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFontImpl.this.removeCharset(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda60
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFontImpl.this.sizeOfCharsetArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTIntProperty[] getCharsetArray() {
        return (CTIntProperty[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTIntProperty[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTIntProperty getCharsetArray(int i) {
        CTIntProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTIntProperty) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public int sizeOfCharsetArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setCharsetArray(CTIntProperty[] charsetArray) {
        check_orphaned();
        arraySetterHelper(charsetArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setCharsetArray(int i, CTIntProperty charset) {
        generatedSetterHelperImpl(charset, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTIntProperty insertNewCharset(int i) {
        CTIntProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTIntProperty) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTIntProperty addNewCharset() {
        CTIntProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTIntProperty) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void removeCharset(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public List<CTFontFamily> getFamilyList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.getFamilyArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFontImpl.this.setFamilyArray(((Integer) obj).intValue(), (CTFontFamily) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.insertNewFamily(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFontImpl.this.removeFamily(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFontImpl.this.sizeOfFamilyArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTFontFamily[] getFamilyArray() {
        return (CTFontFamily[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTFontFamily[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTFontFamily getFamilyArray(int i) {
        CTFontFamily target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFontFamily) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public int sizeOfFamilyArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setFamilyArray(CTFontFamily[] familyArray) {
        check_orphaned();
        arraySetterHelper(familyArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setFamilyArray(int i, CTFontFamily family) {
        generatedSetterHelperImpl(family, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTFontFamily insertNewFamily(int i) {
        CTFontFamily target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFontFamily) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTFontFamily addNewFamily() {
        CTFontFamily target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFontFamily) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void removeFamily(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public List<CTBooleanProperty> getBList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.getBArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFontImpl.this.setBArray(((Integer) obj).intValue(), (CTBooleanProperty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.insertNewB(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFontImpl.this.removeB(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFontImpl.this.sizeOfBArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty[] getBArray() {
        return (CTBooleanProperty[]) getXmlObjectArray(PROPERTY_QNAME[3], new CTBooleanProperty[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty getBArray(int i) {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public int sizeOfBArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setBArray(CTBooleanProperty[] bArray) {
        check_orphaned();
        arraySetterHelper(bArray, PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setBArray(int i, CTBooleanProperty b) {
        generatedSetterHelperImpl(b, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty insertNewB(int i) {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty addNewB() {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void removeB(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public List<CTBooleanProperty> getIList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.getIArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFontImpl.this.setIArray(((Integer) obj).intValue(), (CTBooleanProperty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.insertNewI(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFontImpl.this.removeI(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFontImpl.this.sizeOfIArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty[] getIArray() {
        return (CTBooleanProperty[]) getXmlObjectArray(PROPERTY_QNAME[4], new CTBooleanProperty[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty getIArray(int i) {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public int sizeOfIArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setIArray(CTBooleanProperty[] iValueArray) {
        check_orphaned();
        arraySetterHelper(iValueArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setIArray(int i, CTBooleanProperty iValue) {
        generatedSetterHelperImpl(iValue, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty insertNewI(int i) {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty addNewI() {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void removeI(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public List<CTBooleanProperty> getStrikeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.getStrikeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFontImpl.this.setStrikeArray(((Integer) obj).intValue(), (CTBooleanProperty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.insertNewStrike(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFontImpl.this.removeStrike(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFontImpl.this.sizeOfStrikeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty[] getStrikeArray() {
        return (CTBooleanProperty[]) getXmlObjectArray(PROPERTY_QNAME[5], new CTBooleanProperty[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty getStrikeArray(int i) {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public int sizeOfStrikeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setStrikeArray(CTBooleanProperty[] strikeArray) {
        check_orphaned();
        arraySetterHelper(strikeArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setStrikeArray(int i, CTBooleanProperty strike) {
        generatedSetterHelperImpl(strike, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty insertNewStrike(int i) {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty addNewStrike() {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void removeStrike(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public List<CTBooleanProperty> getOutlineList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.getOutlineArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFontImpl.this.setOutlineArray(((Integer) obj).intValue(), (CTBooleanProperty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.insertNewOutline(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFontImpl.this.removeOutline(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda65
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFontImpl.this.sizeOfOutlineArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty[] getOutlineArray() {
        return (CTBooleanProperty[]) getXmlObjectArray(PROPERTY_QNAME[6], new CTBooleanProperty[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty getOutlineArray(int i) {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public int sizeOfOutlineArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setOutlineArray(CTBooleanProperty[] outlineArray) {
        check_orphaned();
        arraySetterHelper(outlineArray, PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setOutlineArray(int i, CTBooleanProperty outline) {
        generatedSetterHelperImpl(outline, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty insertNewOutline(int i) {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty addNewOutline() {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void removeOutline(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public List<CTBooleanProperty> getShadowList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda67
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.getShadowArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda68
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFontImpl.this.setShadowArray(((Integer) obj).intValue(), (CTBooleanProperty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda69
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.insertNewShadow(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda70
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFontImpl.this.removeShadow(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda71
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFontImpl.this.sizeOfShadowArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty[] getShadowArray() {
        return (CTBooleanProperty[]) getXmlObjectArray(PROPERTY_QNAME[7], new CTBooleanProperty[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty getShadowArray(int i) {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public int sizeOfShadowArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setShadowArray(CTBooleanProperty[] shadowArray) {
        check_orphaned();
        arraySetterHelper(shadowArray, PROPERTY_QNAME[7]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setShadowArray(int i, CTBooleanProperty shadow) {
        generatedSetterHelperImpl(shadow, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty insertNewShadow(int i) {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty addNewShadow() {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void removeShadow(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public List<CTBooleanProperty> getCondenseList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.getCondenseArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFontImpl.this.setCondenseArray(((Integer) obj).intValue(), (CTBooleanProperty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.insertNewCondense(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFontImpl.this.removeCondense(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFontImpl.this.sizeOfCondenseArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty[] getCondenseArray() {
        return (CTBooleanProperty[]) getXmlObjectArray(PROPERTY_QNAME[8], new CTBooleanProperty[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty getCondenseArray(int i) {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public int sizeOfCondenseArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setCondenseArray(CTBooleanProperty[] condenseArray) {
        check_orphaned();
        arraySetterHelper(condenseArray, PROPERTY_QNAME[8]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setCondenseArray(int i, CTBooleanProperty condense) {
        generatedSetterHelperImpl(condense, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty insertNewCondense(int i) {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty addNewCondense() {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void removeCondense(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public List<CTBooleanProperty> getExtendList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.getExtendArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda66
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFontImpl.this.setExtendArray(((Integer) obj).intValue(), (CTBooleanProperty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda72
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.insertNewExtend(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda73
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFontImpl.this.removeExtend(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda74
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFontImpl.this.sizeOfExtendArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty[] getExtendArray() {
        return (CTBooleanProperty[]) getXmlObjectArray(PROPERTY_QNAME[9], new CTBooleanProperty[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty getExtendArray(int i) {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public int sizeOfExtendArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setExtendArray(CTBooleanProperty[] extendArray) {
        check_orphaned();
        arraySetterHelper(extendArray, PROPERTY_QNAME[9]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setExtendArray(int i, CTBooleanProperty extend) {
        generatedSetterHelperImpl(extend, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty insertNewExtend(int i) {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTBooleanProperty addNewExtend() {
        CTBooleanProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBooleanProperty) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void removeExtend(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public List<CTColor> getColorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.getColorArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFontImpl.this.setColorArray(((Integer) obj).intValue(), (CTColor) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.insertNewColor(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFontImpl.this.removeColor(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFontImpl.this.sizeOfColorArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTColor[] getColorArray() {
        return (CTColor[]) getXmlObjectArray(PROPERTY_QNAME[10], new CTColor[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTColor getColorArray(int i) {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public int sizeOfColorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setColorArray(CTColor[] colorArray) {
        check_orphaned();
        arraySetterHelper(colorArray, PROPERTY_QNAME[10]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setColorArray(int i, CTColor color) {
        generatedSetterHelperImpl(color, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTColor insertNewColor(int i) {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTColor addNewColor() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void removeColor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public List<CTFontSize> getSzList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.getSzArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFontImpl.this.setSzArray(((Integer) obj).intValue(), (CTFontSize) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.insertNewSz(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFontImpl.this.removeSz(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFontImpl.this.sizeOfSzArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTFontSize[] getSzArray() {
        return (CTFontSize[]) getXmlObjectArray(PROPERTY_QNAME[11], new CTFontSize[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTFontSize getSzArray(int i) {
        CTFontSize target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFontSize) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public int sizeOfSzArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setSzArray(CTFontSize[] szArray) {
        check_orphaned();
        arraySetterHelper(szArray, PROPERTY_QNAME[11]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setSzArray(int i, CTFontSize sz) {
        generatedSetterHelperImpl(sz, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTFontSize insertNewSz(int i) {
        CTFontSize target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFontSize) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTFontSize addNewSz() {
        CTFontSize target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFontSize) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void removeSz(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public List<CTUnderlineProperty> getUList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.getUArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFontImpl.this.setUArray(((Integer) obj).intValue(), (CTUnderlineProperty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.insertNewU(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFontImpl.this.removeU(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFontImpl.this.sizeOfUArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTUnderlineProperty[] getUArray() {
        return (CTUnderlineProperty[]) getXmlObjectArray(PROPERTY_QNAME[12], new CTUnderlineProperty[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTUnderlineProperty getUArray(int i) {
        CTUnderlineProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTUnderlineProperty) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public int sizeOfUArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setUArray(CTUnderlineProperty[] uArray) {
        check_orphaned();
        arraySetterHelper(uArray, PROPERTY_QNAME[12]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setUArray(int i, CTUnderlineProperty u) {
        generatedSetterHelperImpl(u, PROPERTY_QNAME[12], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTUnderlineProperty insertNewU(int i) {
        CTUnderlineProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTUnderlineProperty) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTUnderlineProperty addNewU() {
        CTUnderlineProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTUnderlineProperty) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void removeU(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public List<CTVerticalAlignFontProperty> getVertAlignList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.getVertAlignArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFontImpl.this.setVertAlignArray(((Integer) obj).intValue(), (CTVerticalAlignFontProperty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.insertNewVertAlign(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFontImpl.this.removeVertAlign(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFontImpl.this.sizeOfVertAlignArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTVerticalAlignFontProperty[] getVertAlignArray() {
        return (CTVerticalAlignFontProperty[]) getXmlObjectArray(PROPERTY_QNAME[13], new CTVerticalAlignFontProperty[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTVerticalAlignFontProperty getVertAlignArray(int i) {
        CTVerticalAlignFontProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTVerticalAlignFontProperty) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public int sizeOfVertAlignArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setVertAlignArray(CTVerticalAlignFontProperty[] vertAlignArray) {
        check_orphaned();
        arraySetterHelper(vertAlignArray, PROPERTY_QNAME[13]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setVertAlignArray(int i, CTVerticalAlignFontProperty vertAlign) {
        generatedSetterHelperImpl(vertAlign, PROPERTY_QNAME[13], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTVerticalAlignFontProperty insertNewVertAlign(int i) {
        CTVerticalAlignFontProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTVerticalAlignFontProperty) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTVerticalAlignFontProperty addNewVertAlign() {
        CTVerticalAlignFontProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTVerticalAlignFontProperty) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void removeVertAlign(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public List<CTFontScheme> getSchemeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.getSchemeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFontImpl.this.setSchemeArray(((Integer) obj).intValue(), (CTFontScheme) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFontImpl.this.insertNewScheme(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFontImpl.this.removeScheme(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFontImpl.this.sizeOfSchemeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTFontScheme[] getSchemeArray() {
        return (CTFontScheme[]) getXmlObjectArray(PROPERTY_QNAME[14], new CTFontScheme[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTFontScheme getSchemeArray(int i) {
        CTFontScheme target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFontScheme) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public int sizeOfSchemeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setSchemeArray(CTFontScheme[] schemeArray) {
        check_orphaned();
        arraySetterHelper(schemeArray, PROPERTY_QNAME[14]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void setSchemeArray(int i, CTFontScheme scheme) {
        generatedSetterHelperImpl(scheme, PROPERTY_QNAME[14], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTFontScheme insertNewScheme(int i) {
        CTFontScheme target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFontScheme) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public CTFontScheme addNewScheme() {
        CTFontScheme target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFontScheme) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont
    public void removeScheme(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }
}
