package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl;

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
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnector;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShapeNonVisual;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;

/* loaded from: classes11.dex */
public class CTGroupShapeImpl extends XmlComplexContentImpl implements CTGroupShape {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "nvGrpSpPr"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "grpSpPr"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "sp"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "grpSp"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "graphicFrame"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "cxnSp"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "pic")};
    private static final long serialVersionUID = 1;

    public CTGroupShapeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTGroupShapeNonVisual getNvGrpSpPr() {
        CTGroupShapeNonVisual cTGroupShapeNonVisual;
        synchronized (monitor()) {
            check_orphaned();
            CTGroupShapeNonVisual target = (CTGroupShapeNonVisual) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTGroupShapeNonVisual = target == null ? null : target;
        }
        return cTGroupShapeNonVisual;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public void setNvGrpSpPr(CTGroupShapeNonVisual nvGrpSpPr) {
        generatedSetterHelperImpl(nvGrpSpPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTGroupShapeNonVisual addNewNvGrpSpPr() {
        CTGroupShapeNonVisual target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupShapeNonVisual) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTGroupShapeProperties getGrpSpPr() {
        CTGroupShapeProperties cTGroupShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTGroupShapeProperties target = (CTGroupShapeProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTGroupShapeProperties = target == null ? null : target;
        }
        return cTGroupShapeProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public void setGrpSpPr(CTGroupShapeProperties grpSpPr) {
        generatedSetterHelperImpl(grpSpPr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTGroupShapeProperties addNewGrpSpPr() {
        CTGroupShapeProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupShapeProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public List<CTShape> getSpList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.getSpArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupShapeImpl.this.setSpArray(((Integer) obj).intValue(), (CTShape) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.insertNewSp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupShapeImpl.this.removeSp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupShapeImpl.this.sizeOfSpArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTShape[] getSpArray() {
        return (CTShape[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTShape[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public int sizeOfSpArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public void setSpArray(CTShape[] spArray) {
        check_orphaned();
        arraySetterHelper(spArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public void setSpArray(int i, CTShape sp) {
        generatedSetterHelperImpl(sp, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTShape insertNewSp(int i) {
        CTShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShape) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTShape addNewSp() {
        CTShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShape) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public void removeSp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public List<CTGroupShape> getGrpSpList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.getGrpSpArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupShapeImpl.this.setGrpSpArray(((Integer) obj).intValue(), (CTGroupShape) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.insertNewGrpSp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupShapeImpl.this.removeGrpSp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupShapeImpl.this.sizeOfGrpSpArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTGroupShape[] getGrpSpArray() {
        return (CTGroupShape[]) getXmlObjectArray(PROPERTY_QNAME[3], new CTGroupShape[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public int sizeOfGrpSpArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public void setGrpSpArray(CTGroupShape[] grpSpArray) {
        check_orphaned();
        arraySetterHelper(grpSpArray, PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public void setGrpSpArray(int i, CTGroupShape grpSp) {
        generatedSetterHelperImpl(grpSp, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTGroupShape insertNewGrpSp(int i) {
        CTGroupShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupShape) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTGroupShape addNewGrpSp() {
        CTGroupShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupShape) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public void removeGrpSp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public List<CTGraphicalObjectFrame> getGraphicFrameList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.getGraphicFrameArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupShapeImpl.this.setGraphicFrameArray(((Integer) obj).intValue(), (CTGraphicalObjectFrame) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.insertNewGraphicFrame(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupShapeImpl.this.removeGraphicFrame(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupShapeImpl.this.sizeOfGraphicFrameArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTGraphicalObjectFrame[] getGraphicFrameArray() {
        return (CTGraphicalObjectFrame[]) getXmlObjectArray(PROPERTY_QNAME[4], new CTGraphicalObjectFrame[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTGraphicalObjectFrame getGraphicFrameArray(int i) {
        CTGraphicalObjectFrame target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGraphicalObjectFrame) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public int sizeOfGraphicFrameArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public void setGraphicFrameArray(CTGraphicalObjectFrame[] graphicFrameArray) {
        check_orphaned();
        arraySetterHelper(graphicFrameArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public void setGraphicFrameArray(int i, CTGraphicalObjectFrame graphicFrame) {
        generatedSetterHelperImpl(graphicFrame, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTGraphicalObjectFrame insertNewGraphicFrame(int i) {
        CTGraphicalObjectFrame target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGraphicalObjectFrame) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTGraphicalObjectFrame addNewGraphicFrame() {
        CTGraphicalObjectFrame target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGraphicalObjectFrame) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public void removeGraphicFrame(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public List<CTConnector> getCxnSpList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.getCxnSpArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupShapeImpl.this.setCxnSpArray(((Integer) obj).intValue(), (CTConnector) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.insertNewCxnSp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupShapeImpl.this.removeCxnSp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupShapeImpl.this.sizeOfCxnSpArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTConnector[] getCxnSpArray() {
        return (CTConnector[]) getXmlObjectArray(PROPERTY_QNAME[5], new CTConnector[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTConnector getCxnSpArray(int i) {
        CTConnector target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTConnector) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public int sizeOfCxnSpArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public void setCxnSpArray(CTConnector[] cxnSpArray) {
        check_orphaned();
        arraySetterHelper(cxnSpArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public void setCxnSpArray(int i, CTConnector cxnSp) {
        generatedSetterHelperImpl(cxnSp, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTConnector insertNewCxnSp(int i) {
        CTConnector target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTConnector) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTConnector addNewCxnSp() {
        CTConnector target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTConnector) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public void removeCxnSp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public List<CTPicture> getPicList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.getPicArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupShapeImpl.this.setPicArray(((Integer) obj).intValue(), (CTPicture) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.insertNewPic(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupShapeImpl.this.removePic(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTGroupShapeImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupShapeImpl.this.sizeOfPicArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTPicture[] getPicArray() {
        return (CTPicture[]) getXmlObjectArray(PROPERTY_QNAME[6], new CTPicture[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTPicture getPicArray(int i) {
        CTPicture target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPicture) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public int sizeOfPicArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public void setPicArray(CTPicture[] picArray) {
        check_orphaned();
        arraySetterHelper(picArray, PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public void setPicArray(int i, CTPicture pic) {
        generatedSetterHelperImpl(pic, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTPicture insertNewPic(int i) {
        CTPicture target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPicture) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public CTPicture addNewPic() {
        CTPicture target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPicture) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
    public void removePic(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }
}
