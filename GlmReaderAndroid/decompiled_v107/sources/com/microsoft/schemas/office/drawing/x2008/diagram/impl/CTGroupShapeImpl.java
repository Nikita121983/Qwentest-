package com.microsoft.schemas.office.drawing.x2008.diagram.impl;

import com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape;
import com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShapeNonVisual;
import com.microsoft.schemas.office.drawing.x2008.diagram.CTShape;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;

/* loaded from: classes.dex */
public class CTGroupShapeImpl extends XmlComplexContentImpl implements CTGroupShape {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/drawing/2008/diagram", "nvGrpSpPr"), new QName("http://schemas.microsoft.com/office/drawing/2008/diagram", "grpSpPr"), new QName("http://schemas.microsoft.com/office/drawing/2008/diagram", "sp"), new QName("http://schemas.microsoft.com/office/drawing/2008/diagram", "grpSp"), new QName("http://schemas.microsoft.com/office/drawing/2008/diagram", "extLst")};
    private static final long serialVersionUID = 1;

    public CTGroupShapeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public CTGroupShapeNonVisual getNvGrpSpPr() {
        CTGroupShapeNonVisual cTGroupShapeNonVisual;
        synchronized (monitor()) {
            check_orphaned();
            CTGroupShapeNonVisual target = (CTGroupShapeNonVisual) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTGroupShapeNonVisual = target == null ? null : target;
        }
        return cTGroupShapeNonVisual;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public void setNvGrpSpPr(CTGroupShapeNonVisual nvGrpSpPr) {
        generatedSetterHelperImpl(nvGrpSpPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public CTGroupShapeNonVisual addNewNvGrpSpPr() {
        CTGroupShapeNonVisual target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupShapeNonVisual) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public CTGroupShapeProperties getGrpSpPr() {
        CTGroupShapeProperties cTGroupShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTGroupShapeProperties target = (CTGroupShapeProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTGroupShapeProperties = target == null ? null : target;
        }
        return cTGroupShapeProperties;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public void setGrpSpPr(CTGroupShapeProperties grpSpPr) {
        generatedSetterHelperImpl(grpSpPr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public CTGroupShapeProperties addNewGrpSpPr() {
        CTGroupShapeProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupShapeProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public List<CTShape> getSpList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.drawing.x2008.diagram.impl.CTGroupShapeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.getSpArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.drawing.x2008.diagram.impl.CTGroupShapeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupShapeImpl.this.setSpArray(((Integer) obj).intValue(), (CTShape) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.drawing.x2008.diagram.impl.CTGroupShapeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.insertNewSp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.office.drawing.x2008.diagram.impl.CTGroupShapeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupShapeImpl.this.removeSp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.office.drawing.x2008.diagram.impl.CTGroupShapeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupShapeImpl.this.sizeOfSpArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public CTShape[] getSpArray() {
        return (CTShape[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTShape[0]);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public CTShape getSpArray(int i) {
        CTShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShape) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public int sizeOfSpArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public void setSpArray(CTShape[] spArray) {
        check_orphaned();
        arraySetterHelper(spArray, PROPERTY_QNAME[2]);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public void setSpArray(int i, CTShape sp) {
        generatedSetterHelperImpl(sp, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public CTShape insertNewSp(int i) {
        CTShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShape) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public CTShape addNewSp() {
        CTShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShape) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public void removeSp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public List<CTGroupShape> getGrpSpList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.drawing.x2008.diagram.impl.CTGroupShapeImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.getGrpSpArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.drawing.x2008.diagram.impl.CTGroupShapeImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupShapeImpl.this.setGrpSpArray(((Integer) obj).intValue(), (CTGroupShape) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.drawing.x2008.diagram.impl.CTGroupShapeImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.insertNewGrpSp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.office.drawing.x2008.diagram.impl.CTGroupShapeImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupShapeImpl.this.removeGrpSp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.office.drawing.x2008.diagram.impl.CTGroupShapeImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupShapeImpl.this.sizeOfGrpSpArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public CTGroupShape[] getGrpSpArray() {
        return (CTGroupShape[]) getXmlObjectArray(PROPERTY_QNAME[3], new CTGroupShape[0]);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public CTGroupShape getGrpSpArray(int i) {
        CTGroupShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupShape) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public int sizeOfGrpSpArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public void setGrpSpArray(CTGroupShape[] grpSpArray) {
        check_orphaned();
        arraySetterHelper(grpSpArray, PROPERTY_QNAME[3]);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public void setGrpSpArray(int i, CTGroupShape grpSp) {
        generatedSetterHelperImpl(grpSp, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public CTGroupShape insertNewGrpSp(int i) {
        CTGroupShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupShape) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public CTGroupShape addNewGrpSp() {
        CTGroupShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupShape) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public void removeGrpSp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }
}
