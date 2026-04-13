package org.openxmlformats.schemas.drawingml.x2006.main.impl;

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
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtension;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;

/* loaded from: classes11.dex */
public class CTOfficeArtExtensionListImpl extends XmlComplexContentImpl implements CTOfficeArtExtensionList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "ext")};
    private static final long serialVersionUID = 1;

    public CTOfficeArtExtensionListImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList
    public List<CTOfficeArtExtension> getExtList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTOfficeArtExtensionListImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOfficeArtExtensionListImpl.this.getExtArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTOfficeArtExtensionListImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOfficeArtExtensionListImpl.this.setExtArray(((Integer) obj).intValue(), (CTOfficeArtExtension) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTOfficeArtExtensionListImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOfficeArtExtensionListImpl.this.insertNewExt(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTOfficeArtExtensionListImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOfficeArtExtensionListImpl.this.removeExt(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTOfficeArtExtensionListImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOfficeArtExtensionListImpl.this.sizeOfExtArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList
    public CTOfficeArtExtension[] getExtArray() {
        return (CTOfficeArtExtension[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTOfficeArtExtension[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList
    public CTOfficeArtExtension getExtArray(int i) {
        CTOfficeArtExtension target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtension) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList
    public int sizeOfExtArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList
    public void setExtArray(CTOfficeArtExtension[] extArray) {
        check_orphaned();
        arraySetterHelper(extArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList
    public void setExtArray(int i, CTOfficeArtExtension ext) {
        generatedSetterHelperImpl(ext, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList
    public CTOfficeArtExtension insertNewExt(int i) {
        CTOfficeArtExtension target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtension) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList
    public CTOfficeArtExtension addNewExt() {
        CTOfficeArtExtension target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtension) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList
    public void removeExt(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
