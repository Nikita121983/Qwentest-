package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLatentStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;

/* loaded from: classes12.dex */
public class CTStylesImpl extends XmlComplexContentImpl implements CTStyles {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "docDefaults"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "latentStyles"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "style")};
    private static final long serialVersionUID = 1;

    public CTStylesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public CTDocDefaults getDocDefaults() {
        CTDocDefaults cTDocDefaults;
        synchronized (monitor()) {
            check_orphaned();
            CTDocDefaults target = (CTDocDefaults) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTDocDefaults = target == null ? null : target;
        }
        return cTDocDefaults;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public boolean isSetDocDefaults() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public void setDocDefaults(CTDocDefaults docDefaults) {
        generatedSetterHelperImpl(docDefaults, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public CTDocDefaults addNewDocDefaults() {
        CTDocDefaults target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDocDefaults) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public void unsetDocDefaults() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public CTLatentStyles getLatentStyles() {
        CTLatentStyles cTLatentStyles;
        synchronized (monitor()) {
            check_orphaned();
            CTLatentStyles target = (CTLatentStyles) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTLatentStyles = target == null ? null : target;
        }
        return cTLatentStyles;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public boolean isSetLatentStyles() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public void setLatentStyles(CTLatentStyles latentStyles) {
        generatedSetterHelperImpl(latentStyles, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public CTLatentStyles addNewLatentStyles() {
        CTLatentStyles target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLatentStyles) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public void unsetLatentStyles() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public List<CTStyle> getStyleList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTStylesImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTStylesImpl.this.getStyleArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTStylesImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTStylesImpl.this.setStyleArray(((Integer) obj).intValue(), (CTStyle) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTStylesImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTStylesImpl.this.insertNewStyle(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTStylesImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTStylesImpl.this.removeStyle(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTStylesImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTStylesImpl.this.sizeOfStyleArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public CTStyle[] getStyleArray() {
        return (CTStyle[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTStyle[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public CTStyle getStyleArray(int i) {
        CTStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStyle) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public int sizeOfStyleArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public void setStyleArray(CTStyle[] styleArray) {
        check_orphaned();
        arraySetterHelper(styleArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public void setStyleArray(int i, CTStyle style) {
        generatedSetterHelperImpl(style, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public CTStyle insertNewStyle(int i) {
        CTStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStyle) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public CTStyle addNewStyle() {
        CTStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStyle) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles
    public void removeStyle(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }
}
