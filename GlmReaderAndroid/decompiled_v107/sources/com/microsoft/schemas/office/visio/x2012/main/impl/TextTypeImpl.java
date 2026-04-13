package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.CpType;
import com.microsoft.schemas.office.visio.x2012.main.FldType;
import com.microsoft.schemas.office.visio.x2012.main.PpType;
import com.microsoft.schemas.office.visio.x2012.main.TextType;
import com.microsoft.schemas.office.visio.x2012.main.TpType;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class TextTypeImpl extends XmlComplexContentImpl implements TextType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "cp"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "pp"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "tp"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "fld")};
    private static final long serialVersionUID = 1;

    public TextTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public List<CpType> getCpList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TextTypeImpl.this.getCpArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    TextTypeImpl.this.setCpArray(((Integer) obj).intValue(), (CpType) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TextTypeImpl.this.insertNewCp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    TextTypeImpl.this.removeCp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(TextTypeImpl.this.sizeOfCpArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public CpType[] getCpArray() {
        return getXmlObjectArray(PROPERTY_QNAME[0], (XmlObject[]) new CpType[0]);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public CpType getCpArray(int i) {
        CpType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public int sizeOfCpArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public void setCpArray(CpType[] cpArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cpArray, PROPERTY_QNAME[0]);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public void setCpArray(int i, CpType cp) {
        generatedSetterHelperImpl(cp, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public CpType insertNewCp(int i) {
        CpType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public CpType addNewCp() {
        CpType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public void removeCp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public List<PpType> getPpList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TextTypeImpl.this.getPpArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    TextTypeImpl.this.setPpArray(((Integer) obj).intValue(), (PpType) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TextTypeImpl.this.insertNewPp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    TextTypeImpl.this.removePp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(TextTypeImpl.this.sizeOfPpArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public PpType[] getPpArray() {
        return getXmlObjectArray(PROPERTY_QNAME[1], (XmlObject[]) new PpType[0]);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public PpType getPpArray(int i) {
        PpType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public int sizeOfPpArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public void setPpArray(PpType[] ppArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) ppArray, PROPERTY_QNAME[1]);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public void setPpArray(int i, PpType pp) {
        generatedSetterHelperImpl(pp, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public PpType insertNewPp(int i) {
        PpType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public PpType addNewPp() {
        PpType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public void removePp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public List<TpType> getTpList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TextTypeImpl.this.getTpArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    TextTypeImpl.this.setTpArray(((Integer) obj).intValue(), (TpType) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TextTypeImpl.this.insertNewTp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    TextTypeImpl.this.removeTp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(TextTypeImpl.this.sizeOfTpArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public TpType[] getTpArray() {
        return getXmlObjectArray(PROPERTY_QNAME[2], (XmlObject[]) new TpType[0]);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public TpType getTpArray(int i) {
        TpType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public int sizeOfTpArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public void setTpArray(TpType[] tpArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) tpArray, PROPERTY_QNAME[2]);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public void setTpArray(int i, TpType tp) {
        generatedSetterHelperImpl(tp, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public TpType insertNewTp(int i) {
        TpType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public TpType addNewTp() {
        TpType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public void removeTp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public List<FldType> getFldList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TextTypeImpl.this.getFldArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    TextTypeImpl.this.setFldArray(((Integer) obj).intValue(), (FldType) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TextTypeImpl.this.insertNewFld(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    TextTypeImpl.this.removeFld(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.office.visio.x2012.main.impl.TextTypeImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(TextTypeImpl.this.sizeOfFldArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public FldType[] getFldArray() {
        return getXmlObjectArray(PROPERTY_QNAME[3], (XmlObject[]) new FldType[0]);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public FldType getFldArray(int i) {
        FldType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public int sizeOfFldArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public void setFldArray(FldType[] fldArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) fldArray, PROPERTY_QNAME[3]);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public void setFldArray(int i, FldType fld) {
        generatedSetterHelperImpl(fld, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public FldType insertNewFld(int i) {
        FldType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public FldType addNewFld() {
        FldType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.TextType
    public void removeFld(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }
}
