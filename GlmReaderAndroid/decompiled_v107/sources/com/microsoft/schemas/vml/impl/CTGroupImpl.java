package com.microsoft.schemas.vml.impl;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.microsoft.schemas.office.excel.CTClientData;
import com.microsoft.schemas.office.office.CTCallout;
import com.microsoft.schemas.office.office.CTClipPath;
import com.microsoft.schemas.office.office.CTDiagram;
import com.microsoft.schemas.office.office.CTExtrusion;
import com.microsoft.schemas.office.office.CTLock;
import com.microsoft.schemas.office.office.CTSignatureLine;
import com.microsoft.schemas.office.office.CTSkew;
import com.microsoft.schemas.office.office.STDiagramLayout;
import com.microsoft.schemas.office.office.STHrAlign;
import com.microsoft.schemas.office.office.STInsetMode;
import com.microsoft.schemas.office.powerpoint.CTRel;
import com.microsoft.schemas.office.word.CTAnchorLock;
import com.microsoft.schemas.office.word.CTBorder;
import com.microsoft.schemas.office.word.CTWrap;
import com.microsoft.schemas.vml.CTArc;
import com.microsoft.schemas.vml.CTCurve;
import com.microsoft.schemas.vml.CTFill;
import com.microsoft.schemas.vml.CTFormulas;
import com.microsoft.schemas.vml.CTGroup;
import com.microsoft.schemas.vml.CTHandles;
import com.microsoft.schemas.vml.CTImage;
import com.microsoft.schemas.vml.CTImageData;
import com.microsoft.schemas.vml.CTLine;
import com.microsoft.schemas.vml.CTOval;
import com.microsoft.schemas.vml.CTPath;
import com.microsoft.schemas.vml.CTPolyLine;
import com.microsoft.schemas.vml.CTRect;
import com.microsoft.schemas.vml.CTRoundRect;
import com.microsoft.schemas.vml.CTShadow;
import com.microsoft.schemas.vml.CTShape;
import com.microsoft.schemas.vml.CTShapetype;
import com.microsoft.schemas.vml.CTStroke;
import com.microsoft.schemas.vml.CTTextPath;
import com.microsoft.schemas.vml.CTTextbox;
import com.microsoft.schemas.vml.STEditAs;
import java.math.BigInteger;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlFloat;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STColorType;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;

/* loaded from: classes9.dex */
public class CTGroupImpl extends XmlComplexContentImpl implements CTGroup {
    private static final QName[] PROPERTY_QNAME = {new QName("urn:schemas-microsoft-com:vml", "path"), new QName("urn:schemas-microsoft-com:vml", "formulas"), new QName("urn:schemas-microsoft-com:vml", "handles"), new QName("urn:schemas-microsoft-com:vml", "fill"), new QName("urn:schemas-microsoft-com:vml", "stroke"), new QName("urn:schemas-microsoft-com:vml", "shadow"), new QName("urn:schemas-microsoft-com:vml", "textbox"), new QName("urn:schemas-microsoft-com:vml", "textpath"), new QName("urn:schemas-microsoft-com:vml", "imagedata"), new QName("urn:schemas-microsoft-com:office:office", "skew"), new QName("urn:schemas-microsoft-com:office:office", "extrusion"), new QName("urn:schemas-microsoft-com:office:office", "callout"), new QName("urn:schemas-microsoft-com:office:office", "lock"), new QName("urn:schemas-microsoft-com:office:office", "clippath"), new QName("urn:schemas-microsoft-com:office:office", "signatureline"), new QName("urn:schemas-microsoft-com:office:word", "wrap"), new QName("urn:schemas-microsoft-com:office:word", "anchorlock"), new QName("urn:schemas-microsoft-com:office:word", "bordertop"), new QName("urn:schemas-microsoft-com:office:word", "borderbottom"), new QName("urn:schemas-microsoft-com:office:word", "borderleft"), new QName("urn:schemas-microsoft-com:office:word", "borderright"), new QName("urn:schemas-microsoft-com:office:excel", "ClientData"), new QName("urn:schemas-microsoft-com:office:powerpoint", "textdata"), new QName("urn:schemas-microsoft-com:vml", "group"), new QName("urn:schemas-microsoft-com:vml", "shape"), new QName("urn:schemas-microsoft-com:vml", "shapetype"), new QName("urn:schemas-microsoft-com:vml", "arc"), new QName("urn:schemas-microsoft-com:vml", "curve"), new QName("urn:schemas-microsoft-com:vml", "image"), new QName("urn:schemas-microsoft-com:vml", "line"), new QName("urn:schemas-microsoft-com:vml", "oval"), new QName("urn:schemas-microsoft-com:vml", "polyline"), new QName("urn:schemas-microsoft-com:vml", "rect"), new QName("urn:schemas-microsoft-com:vml", "roundrect"), new QName("urn:schemas-microsoft-com:office:office", "diagram"), new QName("", "id"), new QName("", "style"), new QName("", "href"), new QName("", TypedValues.AttributesType.S_TARGET), new QName("", "class"), new QName("", "title"), new QName("", "alt"), new QName("", "coordsize"), new QName("", "coordorigin"), new QName("", "wrapcoords"), new QName("", "print"), new QName("urn:schemas-microsoft-com:office:office", "spid"), new QName("urn:schemas-microsoft-com:office:office", "oned"), new QName("urn:schemas-microsoft-com:office:office", "regroupid"), new QName("urn:schemas-microsoft-com:office:office", "doubleclicknotify"), new QName("urn:schemas-microsoft-com:office:office", "button"), new QName("urn:schemas-microsoft-com:office:office", "userhidden"), new QName("urn:schemas-microsoft-com:office:office", "bullet"), new QName("urn:schemas-microsoft-com:office:office", "hr"), new QName("urn:schemas-microsoft-com:office:office", "hrstd"), new QName("urn:schemas-microsoft-com:office:office", "hrnoshade"), new QName("urn:schemas-microsoft-com:office:office", "hrpct"), new QName("urn:schemas-microsoft-com:office:office", "hralign"), new QName("urn:schemas-microsoft-com:office:office", "allowincell"), new QName("urn:schemas-microsoft-com:office:office", "allowoverlap"), new QName("urn:schemas-microsoft-com:office:office", "userdrawn"), new QName("urn:schemas-microsoft-com:office:office", "bordertopcolor"), new QName("urn:schemas-microsoft-com:office:office", "borderleftcolor"), new QName("urn:schemas-microsoft-com:office:office", "borderbottomcolor"), new QName("urn:schemas-microsoft-com:office:office", "borderrightcolor"), new QName("urn:schemas-microsoft-com:office:office", "dgmlayout"), new QName("urn:schemas-microsoft-com:office:office", "dgmnodekind"), new QName("urn:schemas-microsoft-com:office:office", "dgmlayoutmru"), new QName("urn:schemas-microsoft-com:office:office", "insetmode"), new QName("", "filled"), new QName("", "fillcolor"), new QName("", "editas"), new QName("urn:schemas-microsoft-com:office:office", "tableproperties"), new QName("urn:schemas-microsoft-com:office:office", "tablelimits")};
    private static final long serialVersionUID = 1;

    public CTGroupImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTPath> getPathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda67
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getPathArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda68
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setPathArray(((Integer) obj).intValue(), (CTPath) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda69
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewPath(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda70
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removePath(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda71
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfPathArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTPath[] getPathArray() {
        return (CTPath[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTPath[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTPath getPathArray(int i) {
        CTPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfPathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setPathArray(CTPath[] pathArray) {
        check_orphaned();
        arraySetterHelper(pathArray, PROPERTY_QNAME[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setPathArray(int i, CTPath path) {
        generatedSetterHelperImpl(path, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTPath insertNewPath(int i) {
        CTPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTPath addNewPath() {
        CTPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removePath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTFormulas> getFormulasList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda92
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getFormulasArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda93
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setFormulasArray(((Integer) obj).intValue(), (CTFormulas) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda94
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewFormulas(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda95
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeFormulas(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda96
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfFormulasArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTFormulas[] getFormulasArray() {
        return (CTFormulas[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTFormulas[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTFormulas getFormulasArray(int i) {
        CTFormulas target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFormulas) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfFormulasArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setFormulasArray(CTFormulas[] formulasArray) {
        check_orphaned();
        arraySetterHelper(formulasArray, PROPERTY_QNAME[1]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setFormulasArray(int i, CTFormulas formulas) {
        generatedSetterHelperImpl(formulas, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTFormulas insertNewFormulas(int i) {
        CTFormulas target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFormulas) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTFormulas addNewFormulas() {
        CTFormulas target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFormulas) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeFormulas(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTHandles> getHandlesList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getHandlesArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setHandlesArray(((Integer) obj).intValue(), (CTHandles) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewHandles(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeHandles(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfHandlesArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTHandles[] getHandlesArray() {
        return (CTHandles[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTHandles[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTHandles getHandlesArray(int i) {
        CTHandles target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHandles) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfHandlesArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setHandlesArray(CTHandles[] handlesArray) {
        check_orphaned();
        arraySetterHelper(handlesArray, PROPERTY_QNAME[2]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setHandlesArray(int i, CTHandles handles) {
        generatedSetterHelperImpl(handles, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTHandles insertNewHandles(int i) {
        CTHandles target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHandles) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTHandles addNewHandles() {
        CTHandles target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHandles) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeHandles(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTFill> getFillList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda131
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getFillArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda132
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setFillArray(((Integer) obj).intValue(), (CTFill) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda133
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewFill(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda134
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeFill(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda135
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfFillArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTFill[] getFillArray() {
        return (CTFill[]) getXmlObjectArray(PROPERTY_QNAME[3], new CTFill[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTFill getFillArray(int i) {
        CTFill target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFill) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfFillArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setFillArray(CTFill[] fillArray) {
        check_orphaned();
        arraySetterHelper(fillArray, PROPERTY_QNAME[3]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setFillArray(int i, CTFill fill) {
        generatedSetterHelperImpl(fill, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTFill insertNewFill(int i) {
        CTFill target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFill) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTFill addNewFill() {
        CTFill target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFill) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeFill(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTStroke> getStrokeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda125
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getStrokeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda126
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setStrokeArray(((Integer) obj).intValue(), (CTStroke) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda127
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewStroke(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda128
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeStroke(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda129
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfStrokeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTStroke[] getStrokeArray() {
        return (CTStroke[]) getXmlObjectArray(PROPERTY_QNAME[4], new CTStroke[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTStroke getStrokeArray(int i) {
        CTStroke target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStroke) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfStrokeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setStrokeArray(CTStroke[] strokeArray) {
        check_orphaned();
        arraySetterHelper(strokeArray, PROPERTY_QNAME[4]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setStrokeArray(int i, CTStroke stroke) {
        generatedSetterHelperImpl(stroke, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTStroke insertNewStroke(int i) {
        CTStroke target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStroke) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTStroke addNewStroke() {
        CTStroke target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStroke) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeStroke(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTShadow> getShadowList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getShadowArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setShadowArray(((Integer) obj).intValue(), (CTShadow) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewShadow(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeShadow(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda65
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfShadowArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTShadow[] getShadowArray() {
        return (CTShadow[]) getXmlObjectArray(PROPERTY_QNAME[5], new CTShadow[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTShadow getShadowArray(int i) {
        CTShadow target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShadow) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfShadowArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setShadowArray(CTShadow[] shadowArray) {
        check_orphaned();
        arraySetterHelper(shadowArray, PROPERTY_QNAME[5]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setShadowArray(int i, CTShadow shadow) {
        generatedSetterHelperImpl(shadow, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTShadow insertNewShadow(int i) {
        CTShadow target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShadow) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTShadow addNewShadow() {
        CTShadow target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShadow) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeShadow(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTTextbox> getTextboxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getTextboxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setTextboxArray(((Integer) obj).intValue(), (CTTextbox) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewTextbox(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeTextbox(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfTextboxArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTTextbox[] getTextboxArray() {
        return (CTTextbox[]) getXmlObjectArray(PROPERTY_QNAME[6], new CTTextbox[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTTextbox getTextboxArray(int i) {
        CTTextbox target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextbox) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfTextboxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setTextboxArray(CTTextbox[] textboxArray) {
        check_orphaned();
        arraySetterHelper(textboxArray, PROPERTY_QNAME[6]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setTextboxArray(int i, CTTextbox textbox) {
        generatedSetterHelperImpl(textbox, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTTextbox insertNewTextbox(int i) {
        CTTextbox target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextbox) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTTextbox addNewTextbox() {
        CTTextbox target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextbox) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeTextbox(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTTextPath> getTextpathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getTextpathArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setTextpathArray(((Integer) obj).intValue(), (CTTextPath) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewTextpath(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeTextpath(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfTextpathArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTTextPath[] getTextpathArray() {
        return (CTTextPath[]) getXmlObjectArray(PROPERTY_QNAME[7], new CTTextPath[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTTextPath getTextpathArray(int i) {
        CTTextPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextPath) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfTextpathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setTextpathArray(CTTextPath[] textpathArray) {
        check_orphaned();
        arraySetterHelper(textpathArray, PROPERTY_QNAME[7]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setTextpathArray(int i, CTTextPath textpath) {
        generatedSetterHelperImpl(textpath, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTTextPath insertNewTextpath(int i) {
        CTTextPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextPath) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTTextPath addNewTextpath() {
        CTTextPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextPath) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeTextpath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTImageData> getImagedataList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda147
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getImagedataArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda148
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setImagedataArray(((Integer) obj).intValue(), (CTImageData) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda149
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewImagedata(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda150
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeImagedata(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda151
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfImagedataArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTImageData[] getImagedataArray() {
        return (CTImageData[]) getXmlObjectArray(PROPERTY_QNAME[8], new CTImageData[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTImageData getImagedataArray(int i) {
        CTImageData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTImageData) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfImagedataArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setImagedataArray(CTImageData[] imagedataArray) {
        check_orphaned();
        arraySetterHelper(imagedataArray, PROPERTY_QNAME[8]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setImagedataArray(int i, CTImageData imagedata) {
        generatedSetterHelperImpl(imagedata, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTImageData insertNewImagedata(int i) {
        CTImageData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTImageData) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTImageData addNewImagedata() {
        CTImageData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTImageData) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeImagedata(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTSkew> getSkewList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda153
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getSkewArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda154
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setSkewArray(((Integer) obj).intValue(), (CTSkew) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda155
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewSkew(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda156
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeSkew(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda157
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfSkewArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTSkew[] getSkewArray() {
        return getXmlObjectArray(PROPERTY_QNAME[9], (XmlObject[]) new CTSkew[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTSkew getSkewArray(int i) {
        CTSkew target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfSkewArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setSkewArray(CTSkew[] skewArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) skewArray, PROPERTY_QNAME[9]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setSkewArray(int i, CTSkew skew) {
        generatedSetterHelperImpl(skew, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTSkew insertNewSkew(int i) {
        CTSkew target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTSkew addNewSkew() {
        CTSkew target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeSkew(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTExtrusion> getExtrusionList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getExtrusionArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda86
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setExtrusionArray(((Integer) obj).intValue(), (CTExtrusion) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda97
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewExtrusion(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda108
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeExtrusion(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda119
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfExtrusionArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTExtrusion[] getExtrusionArray() {
        return getXmlObjectArray(PROPERTY_QNAME[10], (XmlObject[]) new CTExtrusion[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTExtrusion getExtrusionArray(int i) {
        CTExtrusion target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfExtrusionArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setExtrusionArray(CTExtrusion[] extrusionArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) extrusionArray, PROPERTY_QNAME[10]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setExtrusionArray(int i, CTExtrusion extrusion) {
        generatedSetterHelperImpl(extrusion, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTExtrusion insertNewExtrusion(int i) {
        CTExtrusion target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTExtrusion addNewExtrusion() {
        CTExtrusion target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeExtrusion(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTCallout> getCalloutList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getCalloutArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setCalloutArray(((Integer) obj).intValue(), (CTCallout) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewCallout(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeCallout(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfCalloutArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTCallout[] getCalloutArray() {
        return getXmlObjectArray(PROPERTY_QNAME[11], (XmlObject[]) new CTCallout[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTCallout getCalloutArray(int i) {
        CTCallout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfCalloutArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setCalloutArray(CTCallout[] calloutArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) calloutArray, PROPERTY_QNAME[11]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setCalloutArray(int i, CTCallout callout) {
        generatedSetterHelperImpl(callout, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTCallout insertNewCallout(int i) {
        CTCallout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTCallout addNewCallout() {
        CTCallout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeCallout(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTLock> getLockList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda72
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getLockArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda73
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setLockArray(((Integer) obj).intValue(), (CTLock) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda74
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewLock(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda75
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeLock(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda76
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfLockArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTLock[] getLockArray() {
        return (CTLock[]) getXmlObjectArray(PROPERTY_QNAME[12], new CTLock[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTLock getLockArray(int i) {
        CTLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLock) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfLockArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setLockArray(CTLock[] lockArray) {
        check_orphaned();
        arraySetterHelper(lockArray, PROPERTY_QNAME[12]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setLockArray(int i, CTLock lock) {
        generatedSetterHelperImpl(lock, PROPERTY_QNAME[12], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTLock insertNewLock(int i) {
        CTLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLock) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTLock addNewLock() {
        CTLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLock) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeLock(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTClipPath> getClippathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getClippathArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setClippathArray(((Integer) obj).intValue(), (CTClipPath) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewClippath(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeClippath(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfClippathArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTClipPath[] getClippathArray() {
        return getXmlObjectArray(PROPERTY_QNAME[13], (XmlObject[]) new CTClipPath[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTClipPath getClippathArray(int i) {
        CTClipPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfClippathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setClippathArray(CTClipPath[] clippathArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) clippathArray, PROPERTY_QNAME[13]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setClippathArray(int i, CTClipPath clippath) {
        generatedSetterHelperImpl(clippath, PROPERTY_QNAME[13], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTClipPath insertNewClippath(int i) {
        CTClipPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTClipPath addNewClippath() {
        CTClipPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeClippath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTSignatureLine> getSignaturelineList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getSignaturelineArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setSignaturelineArray(((Integer) obj).intValue(), (CTSignatureLine) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewSignatureline(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeSignatureline(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfSignaturelineArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTSignatureLine[] getSignaturelineArray() {
        return (CTSignatureLine[]) getXmlObjectArray(PROPERTY_QNAME[14], new CTSignatureLine[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTSignatureLine getSignaturelineArray(int i) {
        CTSignatureLine target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSignatureLine) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfSignaturelineArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setSignaturelineArray(CTSignatureLine[] signaturelineArray) {
        check_orphaned();
        arraySetterHelper(signaturelineArray, PROPERTY_QNAME[14]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setSignaturelineArray(int i, CTSignatureLine signatureline) {
        generatedSetterHelperImpl(signatureline, PROPERTY_QNAME[14], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTSignatureLine insertNewSignatureline(int i) {
        CTSignatureLine target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSignatureLine) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTSignatureLine addNewSignatureline() {
        CTSignatureLine target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSignatureLine) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeSignatureline(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTWrap> getWrapList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda87
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getWrapArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda88
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setWrapArray(((Integer) obj).intValue(), (CTWrap) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda89
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewWrap(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda90
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeWrap(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda91
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfWrapArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTWrap[] getWrapArray() {
        return (CTWrap[]) getXmlObjectArray(PROPERTY_QNAME[15], new CTWrap[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTWrap getWrapArray(int i) {
        CTWrap target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTWrap) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfWrapArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setWrapArray(CTWrap[] wrapArray) {
        check_orphaned();
        arraySetterHelper(wrapArray, PROPERTY_QNAME[15]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setWrapArray(int i, CTWrap wrap) {
        generatedSetterHelperImpl(wrap, PROPERTY_QNAME[15], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTWrap insertNewWrap(int i) {
        CTWrap target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTWrap) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTWrap addNewWrap() {
        CTWrap target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTWrap) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeWrap(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTAnchorLock> getAnchorlockList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda120
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getAnchorlockArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda121
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setAnchorlockArray(((Integer) obj).intValue(), (CTAnchorLock) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda122
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewAnchorlock(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda123
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeAnchorlock(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda124
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfAnchorlockArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTAnchorLock[] getAnchorlockArray() {
        return (CTAnchorLock[]) getXmlObjectArray(PROPERTY_QNAME[16], new CTAnchorLock[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTAnchorLock getAnchorlockArray(int i) {
        CTAnchorLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAnchorLock) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfAnchorlockArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setAnchorlockArray(CTAnchorLock[] anchorlockArray) {
        check_orphaned();
        arraySetterHelper(anchorlockArray, PROPERTY_QNAME[16]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setAnchorlockArray(int i, CTAnchorLock anchorlock) {
        generatedSetterHelperImpl(anchorlock, PROPERTY_QNAME[16], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTAnchorLock insertNewAnchorlock(int i) {
        CTAnchorLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAnchorLock) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTAnchorLock addNewAnchorlock() {
        CTAnchorLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAnchorLock) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeAnchorlock(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTBorder> getBordertopList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda130
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getBordertopArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda141
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setBordertopArray(((Integer) obj).intValue(), (CTBorder) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda152
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewBordertop(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda163
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeBordertop(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda174
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfBordertopArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTBorder[] getBordertopArray() {
        return getXmlObjectArray(PROPERTY_QNAME[17], (XmlObject[]) new CTBorder[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTBorder getBordertopArray(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfBordertopArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setBordertopArray(CTBorder[] bordertopArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) bordertopArray, PROPERTY_QNAME[17]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setBordertopArray(int i, CTBorder bordertop) {
        generatedSetterHelperImpl(bordertop, PROPERTY_QNAME[17], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTBorder insertNewBordertop(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTBorder addNewBordertop() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeBordertop(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTBorder> getBorderbottomList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda66
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getBorderbottomArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda77
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setBorderbottomArray(((Integer) obj).intValue(), (CTBorder) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda83
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewBorderbottom(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda84
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeBorderbottom(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda85
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfBorderbottomArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTBorder[] getBorderbottomArray() {
        return getXmlObjectArray(PROPERTY_QNAME[18], (XmlObject[]) new CTBorder[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTBorder getBorderbottomArray(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfBorderbottomArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setBorderbottomArray(CTBorder[] borderbottomArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) borderbottomArray, PROPERTY_QNAME[18]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setBorderbottomArray(int i, CTBorder borderbottom) {
        generatedSetterHelperImpl(borderbottom, PROPERTY_QNAME[18], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTBorder insertNewBorderbottom(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTBorder addNewBorderbottom() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeBorderbottom(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTBorder> getBorderleftList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda98
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getBorderleftArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda99
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setBorderleftArray(((Integer) obj).intValue(), (CTBorder) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda100
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewBorderleft(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda101
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeBorderleft(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda102
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfBorderleftArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTBorder[] getBorderleftArray() {
        return getXmlObjectArray(PROPERTY_QNAME[19], (XmlObject[]) new CTBorder[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTBorder getBorderleftArray(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfBorderleftArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setBorderleftArray(CTBorder[] borderleftArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) borderleftArray, PROPERTY_QNAME[19]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setBorderleftArray(int i, CTBorder borderleft) {
        generatedSetterHelperImpl(borderleft, PROPERTY_QNAME[19], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTBorder insertNewBorderleft(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTBorder addNewBorderleft() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeBorderleft(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTBorder> getBorderrightList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda142
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getBorderrightArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda143
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setBorderrightArray(((Integer) obj).intValue(), (CTBorder) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda144
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewBorderright(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda145
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeBorderright(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda146
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfBorderrightArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTBorder[] getBorderrightArray() {
        return getXmlObjectArray(PROPERTY_QNAME[20], (XmlObject[]) new CTBorder[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTBorder getBorderrightArray(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[20], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfBorderrightArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setBorderrightArray(CTBorder[] borderrightArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) borderrightArray, PROPERTY_QNAME[20]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setBorderrightArray(int i, CTBorder borderright) {
        generatedSetterHelperImpl(borderright, PROPERTY_QNAME[20], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTBorder insertNewBorderright(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTBorder addNewBorderright() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeBorderright(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTClientData> getClientDataList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda103
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getClientDataArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda104
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setClientDataArray(((Integer) obj).intValue(), (CTClientData) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda105
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewClientData(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda106
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeClientData(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda107
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfClientDataArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTClientData[] getClientDataArray() {
        return (CTClientData[]) getXmlObjectArray(PROPERTY_QNAME[21], new CTClientData[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTClientData getClientDataArray(int i) {
        CTClientData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTClientData) get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfClientDataArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setClientDataArray(CTClientData[] clientDataArray) {
        check_orphaned();
        arraySetterHelper(clientDataArray, PROPERTY_QNAME[21]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setClientDataArray(int i, CTClientData clientData) {
        generatedSetterHelperImpl(clientData, PROPERTY_QNAME[21], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTClientData insertNewClientData(int i) {
        CTClientData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTClientData) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTClientData addNewClientData() {
        CTClientData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTClientData) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeClientData(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTRel> getTextdataList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getTextdataArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setTextdataArray(((Integer) obj).intValue(), (CTRel) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewTextdata(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeTextdata(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfTextdataArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTRel[] getTextdataArray() {
        return getXmlObjectArray(PROPERTY_QNAME[22], (XmlObject[]) new CTRel[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTRel getTextdataArray(int i) {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[22], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfTextdataArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setTextdataArray(CTRel[] textdataArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) textdataArray, PROPERTY_QNAME[22]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setTextdataArray(int i, CTRel textdata) {
        generatedSetterHelperImpl(textdata, PROPERTY_QNAME[22], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTRel insertNewTextdata(int i) {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTRel addNewTextdata() {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeTextdata(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTGroup> getGroupList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda78
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getGroupArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda79
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setGroupArray(((Integer) obj).intValue(), (CTGroup) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda80
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewGroup(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda81
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeGroup(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda82
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfGroupArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTGroup[] getGroupArray() {
        return (CTGroup[]) getXmlObjectArray(PROPERTY_QNAME[23], new CTGroup[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTGroup getGroupArray(int i) {
        CTGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroup) get_store().find_element_user(PROPERTY_QNAME[23], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfGroupArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setGroupArray(CTGroup[] groupArray) {
        check_orphaned();
        arraySetterHelper(groupArray, PROPERTY_QNAME[23]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setGroupArray(int i, CTGroup group) {
        generatedSetterHelperImpl(group, PROPERTY_QNAME[23], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTGroup insertNewGroup(int i) {
        CTGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroup) get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTGroup addNewGroup() {
        CTGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroup) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeGroup(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTShape> getShapeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda169
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getShapeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda170
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setShapeArray(((Integer) obj).intValue(), (CTShape) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda171
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewShape(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda172
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeShape(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda173
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfShapeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTShape[] getShapeArray() {
        return (CTShape[]) getXmlObjectArray(PROPERTY_QNAME[24], new CTShape[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTShape getShapeArray(int i) {
        CTShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShape) get_store().find_element_user(PROPERTY_QNAME[24], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfShapeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setShapeArray(CTShape[] shapeArray) {
        check_orphaned();
        arraySetterHelper(shapeArray, PROPERTY_QNAME[24]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setShapeArray(int i, CTShape shape) {
        generatedSetterHelperImpl(shape, PROPERTY_QNAME[24], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTShape insertNewShape(int i) {
        CTShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShape) get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTShape addNewShape() {
        CTShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShape) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeShape(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTShapetype> getShapetypeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda164
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getShapetypeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda165
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setShapetypeArray(((Integer) obj).intValue(), (CTShapetype) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda166
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewShapetype(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda167
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeShapetype(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda168
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfShapetypeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTShapetype[] getShapetypeArray() {
        return (CTShapetype[]) getXmlObjectArray(PROPERTY_QNAME[25], new CTShapetype[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTShapetype getShapetypeArray(int i) {
        CTShapetype target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShapetype) get_store().find_element_user(PROPERTY_QNAME[25], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfShapetypeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setShapetypeArray(CTShapetype[] shapetypeArray) {
        check_orphaned();
        arraySetterHelper(shapetypeArray, PROPERTY_QNAME[25]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setShapetypeArray(int i, CTShapetype shapetype) {
        generatedSetterHelperImpl(shapetype, PROPERTY_QNAME[25], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTShapetype insertNewShapetype(int i) {
        CTShapetype target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShapetype) get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTShapetype addNewShapetype() {
        CTShapetype target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShapetype) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeShapetype(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTArc> getArcList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getArcArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setArcArray(((Integer) obj).intValue(), (CTArc) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewArc(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeArc(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfArcArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTArc[] getArcArray() {
        return getXmlObjectArray(PROPERTY_QNAME[26], (XmlObject[]) new CTArc[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTArc getArcArray(int i) {
        CTArc target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[26], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfArcArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[26]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setArcArray(CTArc[] arcArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) arcArray, PROPERTY_QNAME[26]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setArcArray(int i, CTArc arc) {
        generatedSetterHelperImpl(arc, PROPERTY_QNAME[26], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTArc insertNewArc(int i) {
        CTArc target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[26], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTArc addNewArc() {
        CTArc target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeArc(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTCurve> getCurveList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getCurveArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setCurveArray(((Integer) obj).intValue(), (CTCurve) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewCurve(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeCurve(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda60
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfCurveArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTCurve[] getCurveArray() {
        return getXmlObjectArray(PROPERTY_QNAME[27], (XmlObject[]) new CTCurve[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTCurve getCurveArray(int i) {
        CTCurve target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[27], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfCurveArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[27]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setCurveArray(CTCurve[] curveArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) curveArray, PROPERTY_QNAME[27]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setCurveArray(int i, CTCurve curve) {
        generatedSetterHelperImpl(curve, PROPERTY_QNAME[27], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTCurve insertNewCurve(int i) {
        CTCurve target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[27], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTCurve addNewCurve() {
        CTCurve target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeCurve(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTImage> getImageList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda109
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getImageArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda110
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setImageArray(((Integer) obj).intValue(), (CTImage) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda111
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewImage(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda112
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeImage(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda113
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfImageArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTImage[] getImageArray() {
        return getXmlObjectArray(PROPERTY_QNAME[28], (XmlObject[]) new CTImage[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTImage getImageArray(int i) {
        CTImage target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[28], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfImageArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[28]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setImageArray(CTImage[] imageArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) imageArray, PROPERTY_QNAME[28]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setImageArray(int i, CTImage image) {
        generatedSetterHelperImpl(image, PROPERTY_QNAME[28], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTImage insertNewImage(int i) {
        CTImage target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[28], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTImage addNewImage() {
        CTImage target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeImage(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTLine> getLineList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getLineArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setLineArray(((Integer) obj).intValue(), (CTLine) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewLine(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeLine(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfLineArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTLine[] getLineArray() {
        return (CTLine[]) getXmlObjectArray(PROPERTY_QNAME[29], new CTLine[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTLine getLineArray(int i) {
        CTLine target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLine) get_store().find_element_user(PROPERTY_QNAME[29], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfLineArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[29]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setLineArray(CTLine[] lineArray) {
        check_orphaned();
        arraySetterHelper(lineArray, PROPERTY_QNAME[29]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setLineArray(int i, CTLine line) {
        generatedSetterHelperImpl(line, PROPERTY_QNAME[29], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTLine insertNewLine(int i) {
        CTLine target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLine) get_store().insert_element_user(PROPERTY_QNAME[29], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTLine addNewLine() {
        CTLine target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLine) get_store().add_element_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeLine(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTOval> getOvalList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda136
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getOvalArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda137
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setOvalArray(((Integer) obj).intValue(), (CTOval) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda138
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewOval(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda139
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeOval(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda140
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfOvalArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTOval[] getOvalArray() {
        return (CTOval[]) getXmlObjectArray(PROPERTY_QNAME[30], new CTOval[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTOval getOvalArray(int i) {
        CTOval target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOval) get_store().find_element_user(PROPERTY_QNAME[30], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfOvalArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[30]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setOvalArray(CTOval[] ovalArray) {
        check_orphaned();
        arraySetterHelper(ovalArray, PROPERTY_QNAME[30]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setOvalArray(int i, CTOval oval) {
        generatedSetterHelperImpl(oval, PROPERTY_QNAME[30], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTOval insertNewOval(int i) {
        CTOval target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOval) get_store().insert_element_user(PROPERTY_QNAME[30], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTOval addNewOval() {
        CTOval target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOval) get_store().add_element_user(PROPERTY_QNAME[30]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeOval(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[30], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTPolyLine> getPolylineList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda158
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getPolylineArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda159
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setPolylineArray(((Integer) obj).intValue(), (CTPolyLine) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda160
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewPolyline(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda161
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removePolyline(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda162
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfPolylineArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTPolyLine[] getPolylineArray() {
        return getXmlObjectArray(PROPERTY_QNAME[31], (XmlObject[]) new CTPolyLine[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTPolyLine getPolylineArray(int i) {
        CTPolyLine target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[31], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfPolylineArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[31]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setPolylineArray(CTPolyLine[] polylineArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) polylineArray, PROPERTY_QNAME[31]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setPolylineArray(int i, CTPolyLine polyline) {
        generatedSetterHelperImpl(polyline, PROPERTY_QNAME[31], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTPolyLine insertNewPolyline(int i) {
        CTPolyLine target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[31], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTPolyLine addNewPolyline() {
        CTPolyLine target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[31]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removePolyline(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[31], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTRect> getRectList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getRectArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setRectArray(((Integer) obj).intValue(), (CTRect) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewRect(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeRect(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfRectArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTRect[] getRectArray() {
        return (CTRect[]) getXmlObjectArray(PROPERTY_QNAME[32], new CTRect[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTRect getRectArray(int i) {
        CTRect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRect) get_store().find_element_user(PROPERTY_QNAME[32], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfRectArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[32]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setRectArray(CTRect[] rectArray) {
        check_orphaned();
        arraySetterHelper(rectArray, PROPERTY_QNAME[32]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setRectArray(int i, CTRect rect) {
        generatedSetterHelperImpl(rect, PROPERTY_QNAME[32], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTRect insertNewRect(int i) {
        CTRect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRect) get_store().insert_element_user(PROPERTY_QNAME[32], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTRect addNewRect() {
        CTRect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRect) get_store().add_element_user(PROPERTY_QNAME[32]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeRect(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[32], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTRoundRect> getRoundrectList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getRoundrectArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setRoundrectArray(((Integer) obj).intValue(), (CTRoundRect) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewRoundrect(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeRoundrect(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfRoundrectArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTRoundRect[] getRoundrectArray() {
        return (CTRoundRect[]) getXmlObjectArray(PROPERTY_QNAME[33], new CTRoundRect[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTRoundRect getRoundrectArray(int i) {
        CTRoundRect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRoundRect) get_store().find_element_user(PROPERTY_QNAME[33], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfRoundrectArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[33]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setRoundrectArray(CTRoundRect[] roundrectArray) {
        check_orphaned();
        arraySetterHelper(roundrectArray, PROPERTY_QNAME[33]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setRoundrectArray(int i, CTRoundRect roundrect) {
        generatedSetterHelperImpl(roundrect, PROPERTY_QNAME[33], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTRoundRect insertNewRoundrect(int i) {
        CTRoundRect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRoundRect) get_store().insert_element_user(PROPERTY_QNAME[33], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTRoundRect addNewRoundrect() {
        CTRoundRect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRoundRect) get_store().add_element_user(PROPERTY_QNAME[33]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeRoundrect(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[33], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public List<CTDiagram> getDiagramList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda114
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.getDiagramArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda115
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTGroupImpl.this.setDiagramArray(((Integer) obj).intValue(), (CTDiagram) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda116
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTGroupImpl.this.insertNewDiagram(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda117
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTGroupImpl.this.removeDiagram(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTGroupImpl$$ExternalSyntheticLambda118
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTGroupImpl.this.sizeOfDiagramArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTDiagram[] getDiagramArray() {
        return getXmlObjectArray(PROPERTY_QNAME[34], (XmlObject[]) new CTDiagram[0]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTDiagram getDiagramArray(int i) {
        CTDiagram target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[34], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public int sizeOfDiagramArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[34]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setDiagramArray(CTDiagram[] diagramArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) diagramArray, PROPERTY_QNAME[34]);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setDiagramArray(int i, CTDiagram diagram) {
        generatedSetterHelperImpl(diagram, PROPERTY_QNAME[34], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTDiagram insertNewDiagram(int i) {
        CTDiagram target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[34], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public CTDiagram addNewDiagram() {
        CTDiagram target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[34]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void removeDiagram(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[34], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlString xgetId() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[35]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[35]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[35]);
            }
            target.setStringValue(id);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetId(XmlString id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[35]);
            }
            target.set(id);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[35]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public String getStyle() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlString xgetStyle() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[36]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[36]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setStyle(String style) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[36]);
            }
            target.setStringValue(style);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetStyle(XmlString style) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[36]);
            }
            target.set(style);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[36]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public String getHref() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[37]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlString xgetHref() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[37]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetHref() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[37]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setHref(String href) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[37]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[37]);
            }
            target.setStringValue(href);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetHref(XmlString href) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[37]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[37]);
            }
            target.set(href);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetHref() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[37]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public String getTarget() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[38]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlString xgetTarget() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[38]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetTarget() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[38]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setTarget(String targetValue) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[38]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[38]);
            }
            target.setStringValue(targetValue);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetTarget(XmlString targetValue) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[38]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[38]);
            }
            target.set(targetValue);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetTarget() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[38]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public String getClass1() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[39]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlString xgetClass1() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[39]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetClass1() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[39]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setClass1(String class1) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[39]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[39]);
            }
            target.setStringValue(class1);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetClass1(XmlString class1) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[39]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[39]);
            }
            target.set(class1);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetClass1() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[39]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public String getTitle() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[40]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlString xgetTitle() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[40]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetTitle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[40]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setTitle(String title) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[40]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[40]);
            }
            target.setStringValue(title);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetTitle(XmlString title) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[40]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[40]);
            }
            target.set(title);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetTitle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[40]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public String getAlt() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[41]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlString xgetAlt() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[41]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetAlt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[41]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setAlt(String alt) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[41]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[41]);
            }
            target.setStringValue(alt);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetAlt(XmlString alt) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[41]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[41]);
            }
            target.set(alt);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetAlt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[41]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public String getCoordsize() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[42]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlString xgetCoordsize() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[42]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetCoordsize() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[42]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setCoordsize(String coordsize) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[42]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[42]);
            }
            target.setStringValue(coordsize);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetCoordsize(XmlString coordsize) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[42]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[42]);
            }
            target.set(coordsize);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetCoordsize() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[42]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public String getCoordorigin() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[43]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlString xgetCoordorigin() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[43]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetCoordorigin() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[43]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setCoordorigin(String coordorigin) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[43]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[43]);
            }
            target.setStringValue(coordorigin);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetCoordorigin(XmlString coordorigin) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[43]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[43]);
            }
            target.set(coordorigin);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetCoordorigin() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[43]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public String getWrapcoords() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[44]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlString xgetWrapcoords() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[44]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetWrapcoords() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[44]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setWrapcoords(String wrapcoords) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[44]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[44]);
            }
            target.setStringValue(wrapcoords);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetWrapcoords(XmlString wrapcoords) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[44]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[44]);
            }
            target.set(wrapcoords);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetWrapcoords() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[44]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse.Enum getPrint() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[45]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse xgetPrint() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[45]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetPrint() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[45]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setPrint(STTrueFalse.Enum print) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[45]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[45]);
            }
            target.setEnumValue(print);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetPrint(STTrueFalse print) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[45]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[45]);
            }
            target.set(print);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetPrint() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[45]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public String getSpid() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[46]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlString xgetSpid() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[46]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetSpid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[46]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setSpid(String spid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[46]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[46]);
            }
            target.setStringValue(spid);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetSpid(XmlString spid) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[46]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[46]);
            }
            target.set(spid);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetSpid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[46]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse.Enum getOned() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[47]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse xgetOned() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[47]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetOned() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[47]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setOned(STTrueFalse.Enum oned) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[47]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[47]);
            }
            target.setEnumValue(oned);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetOned(STTrueFalse oned) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[47]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[47]);
            }
            target.set(oned);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetOned() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[47]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public BigInteger getRegroupid() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[48]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlInteger xgetRegroupid() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_attribute_user(PROPERTY_QNAME[48]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetRegroupid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[48]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setRegroupid(BigInteger regroupid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[48]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[48]);
            }
            target.setBigIntegerValue(regroupid);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetRegroupid(XmlInteger regroupid) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_attribute_user(PROPERTY_QNAME[48]);
            if (target == null) {
                target = (XmlInteger) get_store().add_attribute_user(PROPERTY_QNAME[48]);
            }
            target.set(regroupid);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetRegroupid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[48]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse.Enum getDoubleclicknotify() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[49]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse xgetDoubleclicknotify() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[49]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetDoubleclicknotify() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[49]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setDoubleclicknotify(STTrueFalse.Enum doubleclicknotify) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[49]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[49]);
            }
            target.setEnumValue(doubleclicknotify);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetDoubleclicknotify(STTrueFalse doubleclicknotify) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[49]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[49]);
            }
            target.set(doubleclicknotify);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetDoubleclicknotify() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[49]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse.Enum getButton() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[50]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse xgetButton() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[50]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetButton() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[50]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setButton(STTrueFalse.Enum button) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[50]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[50]);
            }
            target.setEnumValue(button);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetButton(STTrueFalse button) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[50]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[50]);
            }
            target.set(button);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetButton() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[50]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse.Enum getUserhidden() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[51]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse xgetUserhidden() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[51]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetUserhidden() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[51]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setUserhidden(STTrueFalse.Enum userhidden) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[51]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[51]);
            }
            target.setEnumValue(userhidden);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetUserhidden(STTrueFalse userhidden) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[51]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[51]);
            }
            target.set(userhidden);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetUserhidden() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[51]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse.Enum getBullet() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[52]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse xgetBullet() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[52]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetBullet() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[52]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setBullet(STTrueFalse.Enum bullet) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[52]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[52]);
            }
            target.setEnumValue(bullet);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetBullet(STTrueFalse bullet) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[52]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[52]);
            }
            target.set(bullet);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetBullet() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[52]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse.Enum getHr() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[53]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse xgetHr() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[53]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetHr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[53]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setHr(STTrueFalse.Enum hr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[53]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[53]);
            }
            target.setEnumValue(hr);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetHr(STTrueFalse hr) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[53]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[53]);
            }
            target.set(hr);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetHr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[53]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse.Enum getHrstd() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[54]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse xgetHrstd() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[54]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetHrstd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[54]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setHrstd(STTrueFalse.Enum hrstd) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[54]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[54]);
            }
            target.setEnumValue(hrstd);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetHrstd(STTrueFalse hrstd) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[54]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[54]);
            }
            target.set(hrstd);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetHrstd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[54]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse.Enum getHrnoshade() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[55]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse xgetHrnoshade() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[55]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetHrnoshade() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[55]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setHrnoshade(STTrueFalse.Enum hrnoshade) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[55]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[55]);
            }
            target.setEnumValue(hrnoshade);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetHrnoshade(STTrueFalse hrnoshade) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[55]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[55]);
            }
            target.set(hrnoshade);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetHrnoshade() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[55]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public float getHrpct() {
        float floatValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[56]);
            floatValue = target == null ? 0.0f : target.getFloatValue();
        }
        return floatValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlFloat xgetHrpct() {
        XmlFloat target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlFloat) get_store().find_attribute_user(PROPERTY_QNAME[56]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetHrpct() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[56]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setHrpct(float hrpct) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[56]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[56]);
            }
            target.setFloatValue(hrpct);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetHrpct(XmlFloat hrpct) {
        synchronized (monitor()) {
            check_orphaned();
            XmlFloat target = (XmlFloat) get_store().find_attribute_user(PROPERTY_QNAME[56]);
            if (target == null) {
                target = (XmlFloat) get_store().add_attribute_user(PROPERTY_QNAME[56]);
            }
            target.set(hrpct);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetHrpct() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[56]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STHrAlign.Enum getHralign() {
        STHrAlign.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[57]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[57]);
            }
            r1 = target == null ? null : (STHrAlign.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STHrAlign xgetHralign() {
        STHrAlign target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STHrAlign) get_store().find_attribute_user(PROPERTY_QNAME[57]);
            if (target == null) {
                target = (STHrAlign) get_default_attribute_value(PROPERTY_QNAME[57]);
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetHralign() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[57]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setHralign(STHrAlign.Enum hralign) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[57]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[57]);
            }
            target.setEnumValue(hralign);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetHralign(STHrAlign hralign) {
        synchronized (monitor()) {
            check_orphaned();
            STHrAlign target = (STHrAlign) get_store().find_attribute_user(PROPERTY_QNAME[57]);
            if (target == null) {
                target = (STHrAlign) get_store().add_attribute_user(PROPERTY_QNAME[57]);
            }
            target.set(hralign);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetHralign() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[57]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse.Enum getAllowincell() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[58]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse xgetAllowincell() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[58]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetAllowincell() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[58]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setAllowincell(STTrueFalse.Enum allowincell) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[58]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[58]);
            }
            target.setEnumValue(allowincell);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetAllowincell(STTrueFalse allowincell) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[58]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[58]);
            }
            target.set(allowincell);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetAllowincell() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[58]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse.Enum getAllowoverlap() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[59]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse xgetAllowoverlap() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[59]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetAllowoverlap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[59]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setAllowoverlap(STTrueFalse.Enum allowoverlap) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[59]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[59]);
            }
            target.setEnumValue(allowoverlap);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetAllowoverlap(STTrueFalse allowoverlap) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[59]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[59]);
            }
            target.set(allowoverlap);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetAllowoverlap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[59]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse.Enum getUserdrawn() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[60]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse xgetUserdrawn() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[60]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetUserdrawn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[60]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setUserdrawn(STTrueFalse.Enum userdrawn) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[60]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[60]);
            }
            target.setEnumValue(userdrawn);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetUserdrawn(STTrueFalse userdrawn) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[60]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[60]);
            }
            target.set(userdrawn);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetUserdrawn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[60]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public String getBordertopcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[61]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlString xgetBordertopcolor() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[61]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetBordertopcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[61]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setBordertopcolor(String bordertopcolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[61]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[61]);
            }
            target.setStringValue(bordertopcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetBordertopcolor(XmlString bordertopcolor) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[61]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[61]);
            }
            target.set(bordertopcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetBordertopcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[61]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public String getBorderleftcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[62]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlString xgetBorderleftcolor() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[62]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetBorderleftcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[62]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setBorderleftcolor(String borderleftcolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[62]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[62]);
            }
            target.setStringValue(borderleftcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetBorderleftcolor(XmlString borderleftcolor) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[62]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[62]);
            }
            target.set(borderleftcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetBorderleftcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[62]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public String getBorderbottomcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[63]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlString xgetBorderbottomcolor() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[63]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetBorderbottomcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[63]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setBorderbottomcolor(String borderbottomcolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[63]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[63]);
            }
            target.setStringValue(borderbottomcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetBorderbottomcolor(XmlString borderbottomcolor) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[63]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[63]);
            }
            target.set(borderbottomcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetBorderbottomcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[63]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public String getBorderrightcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[64]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlString xgetBorderrightcolor() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[64]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetBorderrightcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[64]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setBorderrightcolor(String borderrightcolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[64]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[64]);
            }
            target.setStringValue(borderrightcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetBorderrightcolor(XmlString borderrightcolor) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[64]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[64]);
            }
            target.set(borderrightcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetBorderrightcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[64]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public BigInteger getDgmlayout() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[65]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STDiagramLayout xgetDgmlayout() {
        STDiagramLayout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[65]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetDgmlayout() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[65]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setDgmlayout(BigInteger dgmlayout) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[65]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[65]);
            }
            target.setBigIntegerValue(dgmlayout);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetDgmlayout(STDiagramLayout dgmlayout) {
        synchronized (monitor()) {
            check_orphaned();
            STDiagramLayout target = get_store().find_attribute_user(PROPERTY_QNAME[65]);
            if (target == null) {
                target = (STDiagramLayout) get_store().add_attribute_user(PROPERTY_QNAME[65]);
            }
            target.set(dgmlayout);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetDgmlayout() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[65]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public BigInteger getDgmnodekind() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[66]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlInteger xgetDgmnodekind() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_attribute_user(PROPERTY_QNAME[66]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetDgmnodekind() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[66]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setDgmnodekind(BigInteger dgmnodekind) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[66]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[66]);
            }
            target.setBigIntegerValue(dgmnodekind);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetDgmnodekind(XmlInteger dgmnodekind) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_attribute_user(PROPERTY_QNAME[66]);
            if (target == null) {
                target = (XmlInteger) get_store().add_attribute_user(PROPERTY_QNAME[66]);
            }
            target.set(dgmnodekind);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetDgmnodekind() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[66]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public BigInteger getDgmlayoutmru() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[67]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STDiagramLayout xgetDgmlayoutmru() {
        STDiagramLayout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[67]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetDgmlayoutmru() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[67]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setDgmlayoutmru(BigInteger dgmlayoutmru) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[67]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[67]);
            }
            target.setBigIntegerValue(dgmlayoutmru);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetDgmlayoutmru(STDiagramLayout dgmlayoutmru) {
        synchronized (monitor()) {
            check_orphaned();
            STDiagramLayout target = get_store().find_attribute_user(PROPERTY_QNAME[67]);
            if (target == null) {
                target = (STDiagramLayout) get_store().add_attribute_user(PROPERTY_QNAME[67]);
            }
            target.set(dgmlayoutmru);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetDgmlayoutmru() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[67]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STInsetMode.Enum getInsetmode() {
        STInsetMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[68]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[68]);
            }
            r1 = target == null ? null : (STInsetMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STInsetMode xgetInsetmode() {
        STInsetMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STInsetMode) get_store().find_attribute_user(PROPERTY_QNAME[68]);
            if (target == null) {
                target = (STInsetMode) get_default_attribute_value(PROPERTY_QNAME[68]);
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetInsetmode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[68]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setInsetmode(STInsetMode.Enum insetmode) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[68]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[68]);
            }
            target.setEnumValue(insetmode);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetInsetmode(STInsetMode insetmode) {
        synchronized (monitor()) {
            check_orphaned();
            STInsetMode target = (STInsetMode) get_store().find_attribute_user(PROPERTY_QNAME[68]);
            if (target == null) {
                target = (STInsetMode) get_store().add_attribute_user(PROPERTY_QNAME[68]);
            }
            target.set(insetmode);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetInsetmode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[68]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse.Enum getFilled() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[69]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STTrueFalse xgetFilled() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[69]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetFilled() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[69]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setFilled(STTrueFalse.Enum filled) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[69]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[69]);
            }
            target.setEnumValue(filled);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetFilled(STTrueFalse filled) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[69]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[69]);
            }
            target.set(filled);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetFilled() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[69]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public String getFillcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[70]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STColorType xgetFillcolor() {
        STColorType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[70]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetFillcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[70]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setFillcolor(String fillcolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[70]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[70]);
            }
            target.setStringValue(fillcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetFillcolor(STColorType fillcolor) {
        synchronized (monitor()) {
            check_orphaned();
            STColorType target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[70]);
            if (target == null) {
                target = (STColorType) get_store().add_attribute_user(PROPERTY_QNAME[70]);
            }
            target.set(fillcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetFillcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[70]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STEditAs.Enum getEditas() {
        STEditAs.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[71]);
            r1 = target == null ? null : (STEditAs.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public STEditAs xgetEditas() {
        STEditAs target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STEditAs) get_store().find_attribute_user(PROPERTY_QNAME[71]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetEditas() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[71]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setEditas(STEditAs.Enum editas) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[71]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[71]);
            }
            target.setEnumValue(editas);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetEditas(STEditAs editas) {
        synchronized (monitor()) {
            check_orphaned();
            STEditAs target = (STEditAs) get_store().find_attribute_user(PROPERTY_QNAME[71]);
            if (target == null) {
                target = (STEditAs) get_store().add_attribute_user(PROPERTY_QNAME[71]);
            }
            target.set(editas);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetEditas() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[71]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public String getTableproperties() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[72]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlString xgetTableproperties() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[72]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetTableproperties() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[72]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setTableproperties(String tableproperties) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[72]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[72]);
            }
            target.setStringValue(tableproperties);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetTableproperties(XmlString tableproperties) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[72]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[72]);
            }
            target.set(tableproperties);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetTableproperties() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[72]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public String getTablelimits() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[73]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public XmlString xgetTablelimits() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[73]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public boolean isSetTablelimits() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[73]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void setTablelimits(String tablelimits) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[73]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[73]);
            }
            target.setStringValue(tablelimits);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void xsetTablelimits(XmlString tablelimits) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[73]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[73]);
            }
            target.set(tablelimits);
        }
    }

    @Override // com.microsoft.schemas.vml.CTGroup
    public void unsetTablelimits() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[73]);
        }
    }
}
