package org.openxmlformats.schemas.presentationml.x2006.main.impl;

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
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupShapeProperties;
import org.openxmlformats.schemas.presentationml.x2006.main.CTConnector;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTRel;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;

/* loaded from: classes11.dex */
public class CTGroupShapeImpl extends XmlComplexContentImpl implements CTGroupShape {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "nvGrpSpPr"), new QName(XSSFRelation.NS_PRESENTATIONML, "grpSpPr"), new QName(XSSFRelation.NS_PRESENTATIONML, "sp"), new QName(XSSFRelation.NS_PRESENTATIONML, "grpSp"), new QName(XSSFRelation.NS_PRESENTATIONML, "graphicFrame"), new QName(XSSFRelation.NS_PRESENTATIONML, "cxnSp"), new QName(XSSFRelation.NS_PRESENTATIONML, "pic"), new QName(XSSFRelation.NS_PRESENTATIONML, "contentPart"), new QName(XSSFRelation.NS_PRESENTATIONML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTGroupShapeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTGroupShapeNonVisual getNvGrpSpPr() {
        CTGroupShapeNonVisual cTGroupShapeNonVisual;
        synchronized (monitor()) {
            check_orphaned();
            CTGroupShapeNonVisual target = (CTGroupShapeNonVisual) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTGroupShapeNonVisual = target == null ? null : target;
        }
        return cTGroupShapeNonVisual;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void setNvGrpSpPr(CTGroupShapeNonVisual nvGrpSpPr) {
        generatedSetterHelperImpl(nvGrpSpPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTGroupShapeNonVisual addNewNvGrpSpPr() {
        CTGroupShapeNonVisual target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupShapeNonVisual) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTGroupShapeProperties getGrpSpPr() {
        CTGroupShapeProperties cTGroupShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTGroupShapeProperties target = (CTGroupShapeProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTGroupShapeProperties = target == null ? null : target;
        }
        return cTGroupShapeProperties;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void setGrpSpPr(CTGroupShapeProperties grpSpPr) {
        generatedSetterHelperImpl(grpSpPr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTGroupShapeProperties addNewGrpSpPr() {
        CTGroupShapeProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupShapeProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public List<CTShape> getSpList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.getSpArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupShapeImpl.this.setSpArray(((Integer) obj).intValue(), (CTShape) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.insertNewSp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupShapeImpl.this.removeSp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupShapeImpl.this.sizeOfSpArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTShape[] getSpArray() {
        return (CTShape[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTShape[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public int sizeOfSpArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void setSpArray(CTShape[] spArray) {
        check_orphaned();
        arraySetterHelper(spArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void setSpArray(int i, CTShape sp) {
        generatedSetterHelperImpl(sp, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTShape insertNewSp(int i) {
        CTShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShape) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTShape addNewSp() {
        CTShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShape) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void removeSp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public List<CTGroupShape> getGrpSpList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.getGrpSpArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupShapeImpl.this.setGrpSpArray(((Integer) obj).intValue(), (CTGroupShape) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.insertNewGrpSp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupShapeImpl.this.removeGrpSp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupShapeImpl.this.sizeOfGrpSpArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTGroupShape[] getGrpSpArray() {
        return (CTGroupShape[]) getXmlObjectArray(PROPERTY_QNAME[3], new CTGroupShape[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public int sizeOfGrpSpArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void setGrpSpArray(CTGroupShape[] grpSpArray) {
        check_orphaned();
        arraySetterHelper(grpSpArray, PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void setGrpSpArray(int i, CTGroupShape grpSp) {
        generatedSetterHelperImpl(grpSp, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTGroupShape insertNewGrpSp(int i) {
        CTGroupShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupShape) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTGroupShape addNewGrpSp() {
        CTGroupShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupShape) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void removeGrpSp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public List<CTGraphicalObjectFrame> getGraphicFrameList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.getGraphicFrameArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupShapeImpl.this.setGraphicFrameArray(((Integer) obj).intValue(), (CTGraphicalObjectFrame) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.insertNewGraphicFrame(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupShapeImpl.this.removeGraphicFrame(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupShapeImpl.this.sizeOfGraphicFrameArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTGraphicalObjectFrame[] getGraphicFrameArray() {
        return (CTGraphicalObjectFrame[]) getXmlObjectArray(PROPERTY_QNAME[4], new CTGraphicalObjectFrame[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public int sizeOfGraphicFrameArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void setGraphicFrameArray(CTGraphicalObjectFrame[] graphicFrameArray) {
        check_orphaned();
        arraySetterHelper(graphicFrameArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void setGraphicFrameArray(int i, CTGraphicalObjectFrame graphicFrame) {
        generatedSetterHelperImpl(graphicFrame, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTGraphicalObjectFrame insertNewGraphicFrame(int i) {
        CTGraphicalObjectFrame target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGraphicalObjectFrame) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTGraphicalObjectFrame addNewGraphicFrame() {
        CTGraphicalObjectFrame target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGraphicalObjectFrame) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void removeGraphicFrame(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public List<CTConnector> getCxnSpList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.getCxnSpArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupShapeImpl.this.setCxnSpArray(((Integer) obj).intValue(), (CTConnector) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.insertNewCxnSp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupShapeImpl.this.removeCxnSp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupShapeImpl.this.sizeOfCxnSpArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTConnector[] getCxnSpArray() {
        return (CTConnector[]) getXmlObjectArray(PROPERTY_QNAME[5], new CTConnector[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public int sizeOfCxnSpArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void setCxnSpArray(CTConnector[] cxnSpArray) {
        check_orphaned();
        arraySetterHelper(cxnSpArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void setCxnSpArray(int i, CTConnector cxnSp) {
        generatedSetterHelperImpl(cxnSp, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTConnector insertNewCxnSp(int i) {
        CTConnector target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTConnector) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTConnector addNewCxnSp() {
        CTConnector target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTConnector) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void removeCxnSp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public List<CTPicture> getPicList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.getPicArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupShapeImpl.this.setPicArray(((Integer) obj).intValue(), (CTPicture) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.insertNewPic(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupShapeImpl.this.removePic(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupShapeImpl.this.sizeOfPicArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTPicture[] getPicArray() {
        return (CTPicture[]) getXmlObjectArray(PROPERTY_QNAME[6], new CTPicture[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public int sizeOfPicArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void setPicArray(CTPicture[] picArray) {
        check_orphaned();
        arraySetterHelper(picArray, PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void setPicArray(int i, CTPicture pic) {
        generatedSetterHelperImpl(pic, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTPicture insertNewPic(int i) {
        CTPicture target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPicture) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTPicture addNewPic() {
        CTPicture target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPicture) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void removePic(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public List<CTRel> getContentPartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.getContentPartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupShapeImpl.this.setContentPartArray(((Integer) obj).intValue(), (CTRel) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupShapeImpl.this.insertNewContentPart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupShapeImpl.this.removeContentPart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTGroupShapeImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupShapeImpl.this.sizeOfContentPartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTRel[] getContentPartArray() {
        return (CTRel[]) getXmlObjectArray(PROPERTY_QNAME[7], new CTRel[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTRel getContentPartArray(int i) {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRel) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public int sizeOfContentPartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void setContentPartArray(CTRel[] contentPartArray) {
        check_orphaned();
        arraySetterHelper(contentPartArray, PROPERTY_QNAME[7]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void setContentPartArray(int i, CTRel contentPart) {
        generatedSetterHelperImpl(contentPart, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTRel insertNewContentPart(int i) {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRel) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTRel addNewContentPart() {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRel) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void removeContentPart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTExtensionListModify getExtLst() {
        CTExtensionListModify cTExtensionListModify;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionListModify target = get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTExtensionListModify = target == null ? null : target;
        }
        return cTExtensionListModify;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void setExtLst(CTExtensionListModify extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public CTExtensionListModify addNewExtLst() {
        CTExtensionListModify target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }
}
