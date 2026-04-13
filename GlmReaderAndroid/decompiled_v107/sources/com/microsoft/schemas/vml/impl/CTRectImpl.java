package com.microsoft.schemas.vml.impl;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.microsoft.schemas.office.excel.CTClientData;
import com.microsoft.schemas.office.office.CTCallout;
import com.microsoft.schemas.office.office.CTClipPath;
import com.microsoft.schemas.office.office.CTExtrusion;
import com.microsoft.schemas.office.office.CTLock;
import com.microsoft.schemas.office.office.CTSignatureLine;
import com.microsoft.schemas.office.office.CTSkew;
import com.microsoft.schemas.office.office.STBWMode;
import com.microsoft.schemas.office.office.STConnectorType;
import com.microsoft.schemas.office.office.STDiagramLayout;
import com.microsoft.schemas.office.office.STHrAlign;
import com.microsoft.schemas.office.office.STInsetMode;
import com.microsoft.schemas.office.powerpoint.CTRel;
import com.microsoft.schemas.office.word.CTAnchorLock;
import com.microsoft.schemas.office.word.CTBorder;
import com.microsoft.schemas.office.word.CTWrap;
import com.microsoft.schemas.vml.CTFill;
import com.microsoft.schemas.vml.CTFormulas;
import com.microsoft.schemas.vml.CTHandles;
import com.microsoft.schemas.vml.CTImageData;
import com.microsoft.schemas.vml.CTPath;
import com.microsoft.schemas.vml.CTRect;
import com.microsoft.schemas.vml.CTShadow;
import com.microsoft.schemas.vml.CTStroke;
import com.microsoft.schemas.vml.CTTextPath;
import com.microsoft.schemas.vml.CTTextbox;
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
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalseBlank;

/* loaded from: classes9.dex */
public class CTRectImpl extends XmlComplexContentImpl implements CTRect {
    private static final QName[] PROPERTY_QNAME = {new QName("urn:schemas-microsoft-com:vml", "path"), new QName("urn:schemas-microsoft-com:vml", "formulas"), new QName("urn:schemas-microsoft-com:vml", "handles"), new QName("urn:schemas-microsoft-com:vml", "fill"), new QName("urn:schemas-microsoft-com:vml", "stroke"), new QName("urn:schemas-microsoft-com:vml", "shadow"), new QName("urn:schemas-microsoft-com:vml", "textbox"), new QName("urn:schemas-microsoft-com:vml", "textpath"), new QName("urn:schemas-microsoft-com:vml", "imagedata"), new QName("urn:schemas-microsoft-com:office:office", "skew"), new QName("urn:schemas-microsoft-com:office:office", "extrusion"), new QName("urn:schemas-microsoft-com:office:office", "callout"), new QName("urn:schemas-microsoft-com:office:office", "lock"), new QName("urn:schemas-microsoft-com:office:office", "clippath"), new QName("urn:schemas-microsoft-com:office:office", "signatureline"), new QName("urn:schemas-microsoft-com:office:word", "wrap"), new QName("urn:schemas-microsoft-com:office:word", "anchorlock"), new QName("urn:schemas-microsoft-com:office:word", "bordertop"), new QName("urn:schemas-microsoft-com:office:word", "borderbottom"), new QName("urn:schemas-microsoft-com:office:word", "borderleft"), new QName("urn:schemas-microsoft-com:office:word", "borderright"), new QName("urn:schemas-microsoft-com:office:excel", "ClientData"), new QName("urn:schemas-microsoft-com:office:powerpoint", "textdata"), new QName("", "id"), new QName("", "style"), new QName("", "href"), new QName("", TypedValues.AttributesType.S_TARGET), new QName("", "class"), new QName("", "title"), new QName("", "alt"), new QName("", "coordsize"), new QName("", "coordorigin"), new QName("", "wrapcoords"), new QName("", "print"), new QName("urn:schemas-microsoft-com:office:office", "spid"), new QName("urn:schemas-microsoft-com:office:office", "oned"), new QName("urn:schemas-microsoft-com:office:office", "regroupid"), new QName("urn:schemas-microsoft-com:office:office", "doubleclicknotify"), new QName("urn:schemas-microsoft-com:office:office", "button"), new QName("urn:schemas-microsoft-com:office:office", "userhidden"), new QName("urn:schemas-microsoft-com:office:office", "bullet"), new QName("urn:schemas-microsoft-com:office:office", "hr"), new QName("urn:schemas-microsoft-com:office:office", "hrstd"), new QName("urn:schemas-microsoft-com:office:office", "hrnoshade"), new QName("urn:schemas-microsoft-com:office:office", "hrpct"), new QName("urn:schemas-microsoft-com:office:office", "hralign"), new QName("urn:schemas-microsoft-com:office:office", "allowincell"), new QName("urn:schemas-microsoft-com:office:office", "allowoverlap"), new QName("urn:schemas-microsoft-com:office:office", "userdrawn"), new QName("urn:schemas-microsoft-com:office:office", "bordertopcolor"), new QName("urn:schemas-microsoft-com:office:office", "borderleftcolor"), new QName("urn:schemas-microsoft-com:office:office", "borderbottomcolor"), new QName("urn:schemas-microsoft-com:office:office", "borderrightcolor"), new QName("urn:schemas-microsoft-com:office:office", "dgmlayout"), new QName("urn:schemas-microsoft-com:office:office", "dgmnodekind"), new QName("urn:schemas-microsoft-com:office:office", "dgmlayoutmru"), new QName("urn:schemas-microsoft-com:office:office", "insetmode"), new QName("", "chromakey"), new QName("", "filled"), new QName("", "fillcolor"), new QName("", "opacity"), new QName("", "stroked"), new QName("", "strokecolor"), new QName("", "strokeweight"), new QName("", "insetpen"), new QName("urn:schemas-microsoft-com:office:office", "spt"), new QName("urn:schemas-microsoft-com:office:office", "connectortype"), new QName("urn:schemas-microsoft-com:office:office", "bwmode"), new QName("urn:schemas-microsoft-com:office:office", "bwpure"), new QName("urn:schemas-microsoft-com:office:office", "bwnormal"), new QName("urn:schemas-microsoft-com:office:office", "forcedash"), new QName("urn:schemas-microsoft-com:office:office", "oleicon"), new QName("urn:schemas-microsoft-com:office:office", "ole"), new QName("urn:schemas-microsoft-com:office:office", "preferrelative"), new QName("urn:schemas-microsoft-com:office:office", "cliptowrap"), new QName("urn:schemas-microsoft-com:office:office", "clip")};
    private static final long serialVersionUID = 1;

    public CTRectImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTPath> getPathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda76
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getPathArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda77
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setPathArray(((Integer) obj).intValue(), (CTPath) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda78
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewPath(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda79
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removePath(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda80
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfPathArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTPath[] getPathArray() {
        return (CTPath[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTPath[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfPathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setPathArray(CTPath[] pathArray) {
        check_orphaned();
        arraySetterHelper(pathArray, PROPERTY_QNAME[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setPathArray(int i, CTPath path) {
        generatedSetterHelperImpl(path, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTPath insertNewPath(int i) {
        CTPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTPath addNewPath() {
        CTPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removePath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTFormulas> getFormulasList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda93
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getFormulasArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda94
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setFormulasArray(((Integer) obj).intValue(), (CTFormulas) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda95
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewFormulas(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda96
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeFormulas(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda97
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfFormulasArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTFormulas[] getFormulasArray() {
        return (CTFormulas[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTFormulas[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfFormulasArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setFormulasArray(CTFormulas[] formulasArray) {
        check_orphaned();
        arraySetterHelper(formulasArray, PROPERTY_QNAME[1]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setFormulasArray(int i, CTFormulas formulas) {
        generatedSetterHelperImpl(formulas, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTFormulas insertNewFormulas(int i) {
        CTFormulas target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFormulas) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTFormulas addNewFormulas() {
        CTFormulas target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFormulas) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeFormulas(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTHandles> getHandlesList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda104
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getHandlesArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda105
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setHandlesArray(((Integer) obj).intValue(), (CTHandles) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda106
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewHandles(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda107
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeHandles(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda108
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfHandlesArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTHandles[] getHandlesArray() {
        return (CTHandles[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTHandles[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfHandlesArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setHandlesArray(CTHandles[] handlesArray) {
        check_orphaned();
        arraySetterHelper(handlesArray, PROPERTY_QNAME[2]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setHandlesArray(int i, CTHandles handles) {
        generatedSetterHelperImpl(handles, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTHandles insertNewHandles(int i) {
        CTHandles target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHandles) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTHandles addNewHandles() {
        CTHandles target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHandles) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeHandles(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTFill> getFillList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda109
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getFillArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda110
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setFillArray(((Integer) obj).intValue(), (CTFill) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda111
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewFill(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda112
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeFill(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda113
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfFillArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTFill[] getFillArray() {
        return (CTFill[]) getXmlObjectArray(PROPERTY_QNAME[3], new CTFill[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfFillArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setFillArray(CTFill[] fillArray) {
        check_orphaned();
        arraySetterHelper(fillArray, PROPERTY_QNAME[3]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setFillArray(int i, CTFill fill) {
        generatedSetterHelperImpl(fill, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTFill insertNewFill(int i) {
        CTFill target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFill) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTFill addNewFill() {
        CTFill target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFill) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeFill(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTStroke> getStrokeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda82
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getStrokeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda83
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setStrokeArray(((Integer) obj).intValue(), (CTStroke) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda84
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewStroke(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda85
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeStroke(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda86
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfStrokeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTStroke[] getStrokeArray() {
        return (CTStroke[]) getXmlObjectArray(PROPERTY_QNAME[4], new CTStroke[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfStrokeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setStrokeArray(CTStroke[] strokeArray) {
        check_orphaned();
        arraySetterHelper(strokeArray, PROPERTY_QNAME[4]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setStrokeArray(int i, CTStroke stroke) {
        generatedSetterHelperImpl(stroke, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTStroke insertNewStroke(int i) {
        CTStroke target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStroke) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTStroke addNewStroke() {
        CTStroke target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStroke) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeStroke(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTShadow> getShadowList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getShadowArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setShadowArray(((Integer) obj).intValue(), (CTShadow) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewShadow(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeShadow(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfShadowArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTShadow[] getShadowArray() {
        return (CTShadow[]) getXmlObjectArray(PROPERTY_QNAME[5], new CTShadow[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfShadowArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setShadowArray(CTShadow[] shadowArray) {
        check_orphaned();
        arraySetterHelper(shadowArray, PROPERTY_QNAME[5]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setShadowArray(int i, CTShadow shadow) {
        generatedSetterHelperImpl(shadow, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTShadow insertNewShadow(int i) {
        CTShadow target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShadow) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTShadow addNewShadow() {
        CTShadow target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShadow) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeShadow(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTTextbox> getTextboxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getTextboxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setTextboxArray(((Integer) obj).intValue(), (CTTextbox) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewTextbox(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeTextbox(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfTextboxArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTTextbox[] getTextboxArray() {
        return (CTTextbox[]) getXmlObjectArray(PROPERTY_QNAME[6], new CTTextbox[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfTextboxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setTextboxArray(CTTextbox[] textboxArray) {
        check_orphaned();
        arraySetterHelper(textboxArray, PROPERTY_QNAME[6]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setTextboxArray(int i, CTTextbox textbox) {
        generatedSetterHelperImpl(textbox, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTTextbox insertNewTextbox(int i) {
        CTTextbox target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextbox) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTTextbox addNewTextbox() {
        CTTextbox target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextbox) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeTextbox(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTTextPath> getTextpathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getTextpathArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setTextpathArray(((Integer) obj).intValue(), (CTTextPath) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewTextpath(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeTextpath(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfTextpathArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTTextPath[] getTextpathArray() {
        return (CTTextPath[]) getXmlObjectArray(PROPERTY_QNAME[7], new CTTextPath[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfTextpathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setTextpathArray(CTTextPath[] textpathArray) {
        check_orphaned();
        arraySetterHelper(textpathArray, PROPERTY_QNAME[7]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setTextpathArray(int i, CTTextPath textpath) {
        generatedSetterHelperImpl(textpath, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTTextPath insertNewTextpath(int i) {
        CTTextPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextPath) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTTextPath addNewTextpath() {
        CTTextPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextPath) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeTextpath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTImageData> getImagedataList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getImagedataArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setImagedataArray(((Integer) obj).intValue(), (CTImageData) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewImagedata(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeImagedata(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfImagedataArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTImageData[] getImagedataArray() {
        return (CTImageData[]) getXmlObjectArray(PROPERTY_QNAME[8], new CTImageData[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfImagedataArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setImagedataArray(CTImageData[] imagedataArray) {
        check_orphaned();
        arraySetterHelper(imagedataArray, PROPERTY_QNAME[8]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setImagedataArray(int i, CTImageData imagedata) {
        generatedSetterHelperImpl(imagedata, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTImageData insertNewImagedata(int i) {
        CTImageData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTImageData) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTImageData addNewImagedata() {
        CTImageData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTImageData) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeImagedata(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTSkew> getSkewList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda98
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getSkewArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda99
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setSkewArray(((Integer) obj).intValue(), (CTSkew) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda100
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewSkew(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda101
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeSkew(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda102
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfSkewArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTSkew[] getSkewArray() {
        return getXmlObjectArray(PROPERTY_QNAME[9], (XmlObject[]) new CTSkew[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfSkewArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setSkewArray(CTSkew[] skewArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) skewArray, PROPERTY_QNAME[9]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setSkewArray(int i, CTSkew skew) {
        generatedSetterHelperImpl(skew, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTSkew insertNewSkew(int i) {
        CTSkew target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTSkew addNewSkew() {
        CTSkew target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeSkew(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTExtrusion> getExtrusionList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getExtrusionArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setExtrusionArray(((Integer) obj).intValue(), (CTExtrusion) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewExtrusion(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeExtrusion(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfExtrusionArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTExtrusion[] getExtrusionArray() {
        return getXmlObjectArray(PROPERTY_QNAME[10], (XmlObject[]) new CTExtrusion[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfExtrusionArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setExtrusionArray(CTExtrusion[] extrusionArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) extrusionArray, PROPERTY_QNAME[10]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setExtrusionArray(int i, CTExtrusion extrusion) {
        generatedSetterHelperImpl(extrusion, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTExtrusion insertNewExtrusion(int i) {
        CTExtrusion target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTExtrusion addNewExtrusion() {
        CTExtrusion target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeExtrusion(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTCallout> getCalloutList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda87
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getCalloutArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda88
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setCalloutArray(((Integer) obj).intValue(), (CTCallout) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda89
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewCallout(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda90
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeCallout(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda91
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfCalloutArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTCallout[] getCalloutArray() {
        return getXmlObjectArray(PROPERTY_QNAME[11], (XmlObject[]) new CTCallout[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfCalloutArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setCalloutArray(CTCallout[] calloutArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) calloutArray, PROPERTY_QNAME[11]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setCalloutArray(int i, CTCallout callout) {
        generatedSetterHelperImpl(callout, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTCallout insertNewCallout(int i) {
        CTCallout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTCallout addNewCallout() {
        CTCallout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeCallout(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTLock> getLockList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getLockArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setLockArray(((Integer) obj).intValue(), (CTLock) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewLock(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeLock(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfLockArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTLock[] getLockArray() {
        return (CTLock[]) getXmlObjectArray(PROPERTY_QNAME[12], new CTLock[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfLockArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setLockArray(CTLock[] lockArray) {
        check_orphaned();
        arraySetterHelper(lockArray, PROPERTY_QNAME[12]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setLockArray(int i, CTLock lock) {
        generatedSetterHelperImpl(lock, PROPERTY_QNAME[12], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTLock insertNewLock(int i) {
        CTLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLock) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTLock addNewLock() {
        CTLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLock) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeLock(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTClipPath> getClippathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getClippathArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setClippathArray(((Integer) obj).intValue(), (CTClipPath) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewClippath(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeClippath(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfClippathArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTClipPath[] getClippathArray() {
        return getXmlObjectArray(PROPERTY_QNAME[13], (XmlObject[]) new CTClipPath[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfClippathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setClippathArray(CTClipPath[] clippathArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) clippathArray, PROPERTY_QNAME[13]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setClippathArray(int i, CTClipPath clippath) {
        generatedSetterHelperImpl(clippath, PROPERTY_QNAME[13], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTClipPath insertNewClippath(int i) {
        CTClipPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTClipPath addNewClippath() {
        CTClipPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeClippath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTSignatureLine> getSignaturelineList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda60
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getSignaturelineArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setSignaturelineArray(((Integer) obj).intValue(), (CTSignatureLine) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewSignatureline(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeSignatureline(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfSignaturelineArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTSignatureLine[] getSignaturelineArray() {
        return (CTSignatureLine[]) getXmlObjectArray(PROPERTY_QNAME[14], new CTSignatureLine[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfSignaturelineArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setSignaturelineArray(CTSignatureLine[] signaturelineArray) {
        check_orphaned();
        arraySetterHelper(signaturelineArray, PROPERTY_QNAME[14]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setSignaturelineArray(int i, CTSignatureLine signatureline) {
        generatedSetterHelperImpl(signatureline, PROPERTY_QNAME[14], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTSignatureLine insertNewSignatureline(int i) {
        CTSignatureLine target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSignatureLine) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTSignatureLine addNewSignatureline() {
        CTSignatureLine target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSignatureLine) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeSignatureline(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTWrap> getWrapList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getWrapArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setWrapArray(((Integer) obj).intValue(), (CTWrap) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewWrap(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeWrap(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfWrapArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTWrap[] getWrapArray() {
        return (CTWrap[]) getXmlObjectArray(PROPERTY_QNAME[15], new CTWrap[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfWrapArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setWrapArray(CTWrap[] wrapArray) {
        check_orphaned();
        arraySetterHelper(wrapArray, PROPERTY_QNAME[15]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setWrapArray(int i, CTWrap wrap) {
        generatedSetterHelperImpl(wrap, PROPERTY_QNAME[15], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTWrap insertNewWrap(int i) {
        CTWrap target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTWrap) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTWrap addNewWrap() {
        CTWrap target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTWrap) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeWrap(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTAnchorLock> getAnchorlockList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getAnchorlockArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setAnchorlockArray(((Integer) obj).intValue(), (CTAnchorLock) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewAnchorlock(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeAnchorlock(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfAnchorlockArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTAnchorLock[] getAnchorlockArray() {
        return (CTAnchorLock[]) getXmlObjectArray(PROPERTY_QNAME[16], new CTAnchorLock[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfAnchorlockArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setAnchorlockArray(CTAnchorLock[] anchorlockArray) {
        check_orphaned();
        arraySetterHelper(anchorlockArray, PROPERTY_QNAME[16]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setAnchorlockArray(int i, CTAnchorLock anchorlock) {
        generatedSetterHelperImpl(anchorlock, PROPERTY_QNAME[16], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTAnchorLock insertNewAnchorlock(int i) {
        CTAnchorLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAnchorLock) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTAnchorLock addNewAnchorlock() {
        CTAnchorLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAnchorLock) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeAnchorlock(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTBorder> getBordertopList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getBordertopArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setBordertopArray(((Integer) obj).intValue(), (CTBorder) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewBordertop(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeBordertop(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfBordertopArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTBorder[] getBordertopArray() {
        return getXmlObjectArray(PROPERTY_QNAME[17], (XmlObject[]) new CTBorder[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfBordertopArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setBordertopArray(CTBorder[] bordertopArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) bordertopArray, PROPERTY_QNAME[17]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setBordertopArray(int i, CTBorder bordertop) {
        generatedSetterHelperImpl(bordertop, PROPERTY_QNAME[17], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTBorder insertNewBordertop(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTBorder addNewBordertop() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeBordertop(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTBorder> getBorderbottomList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda70
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getBorderbottomArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda81
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setBorderbottomArray(((Integer) obj).intValue(), (CTBorder) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda92
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewBorderbottom(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda103
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeBorderbottom(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda114
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfBorderbottomArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTBorder[] getBorderbottomArray() {
        return getXmlObjectArray(PROPERTY_QNAME[18], (XmlObject[]) new CTBorder[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfBorderbottomArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setBorderbottomArray(CTBorder[] borderbottomArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) borderbottomArray, PROPERTY_QNAME[18]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setBorderbottomArray(int i, CTBorder borderbottom) {
        generatedSetterHelperImpl(borderbottom, PROPERTY_QNAME[18], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTBorder insertNewBorderbottom(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTBorder addNewBorderbottom() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeBorderbottom(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTBorder> getBorderleftList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getBorderleftArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setBorderleftArray(((Integer) obj).intValue(), (CTBorder) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewBorderleft(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeBorderleft(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfBorderleftArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTBorder[] getBorderleftArray() {
        return getXmlObjectArray(PROPERTY_QNAME[19], (XmlObject[]) new CTBorder[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfBorderleftArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setBorderleftArray(CTBorder[] borderleftArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) borderleftArray, PROPERTY_QNAME[19]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setBorderleftArray(int i, CTBorder borderleft) {
        generatedSetterHelperImpl(borderleft, PROPERTY_QNAME[19], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTBorder insertNewBorderleft(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTBorder addNewBorderleft() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeBorderleft(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTBorder> getBorderrightList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda71
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getBorderrightArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda72
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setBorderrightArray(((Integer) obj).intValue(), (CTBorder) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda73
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewBorderright(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda74
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeBorderright(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda75
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfBorderrightArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTBorder[] getBorderrightArray() {
        return getXmlObjectArray(PROPERTY_QNAME[20], (XmlObject[]) new CTBorder[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfBorderrightArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setBorderrightArray(CTBorder[] borderrightArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) borderrightArray, PROPERTY_QNAME[20]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setBorderrightArray(int i, CTBorder borderright) {
        generatedSetterHelperImpl(borderright, PROPERTY_QNAME[20], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTBorder insertNewBorderright(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTBorder addNewBorderright() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeBorderright(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTClientData> getClientDataList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda65
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getClientDataArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda66
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setClientDataArray(((Integer) obj).intValue(), (CTClientData) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda67
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewClientData(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda68
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeClientData(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda69
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfClientDataArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTClientData[] getClientDataArray() {
        return (CTClientData[]) getXmlObjectArray(PROPERTY_QNAME[21], new CTClientData[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfClientDataArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setClientDataArray(CTClientData[] clientDataArray) {
        check_orphaned();
        arraySetterHelper(clientDataArray, PROPERTY_QNAME[21]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setClientDataArray(int i, CTClientData clientData) {
        generatedSetterHelperImpl(clientData, PROPERTY_QNAME[21], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTClientData insertNewClientData(int i) {
        CTClientData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTClientData) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTClientData addNewClientData() {
        CTClientData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTClientData) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeClientData(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public List<CTRel> getTextdataList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.getTextdataArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRectImpl.this.setTextdataArray(((Integer) obj).intValue(), (CTRel) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRectImpl.this.insertNewTextdata(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRectImpl.this.removeTextdata(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRectImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRectImpl.this.sizeOfTextdataArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTRel[] getTextdataArray() {
        return getXmlObjectArray(PROPERTY_QNAME[22], (XmlObject[]) new CTRel[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
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

    @Override // com.microsoft.schemas.vml.CTRect
    public int sizeOfTextdataArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setTextdataArray(CTRel[] textdataArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) textdataArray, PROPERTY_QNAME[22]);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setTextdataArray(int i, CTRel textdata) {
        generatedSetterHelperImpl(textdata, PROPERTY_QNAME[22], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTRel insertNewTextdata(int i) {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public CTRel addNewTextdata() {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void removeTextdata(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlString xgetId() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[23]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.setStringValue(id);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetId(XmlString id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.set(id);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[23]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getStyle() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlString xgetStyle() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[24]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setStyle(String style) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.setStringValue(style);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetStyle(XmlString style) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.set(style);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[24]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getHref() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlString xgetHref() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetHref() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[25]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setHref(String href) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.setStringValue(href);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetHref(XmlString href) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.set(href);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetHref() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[25]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getTarget() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlString xgetTarget() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetTarget() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[26]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setTarget(String targetValue) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.setStringValue(targetValue);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetTarget(XmlString targetValue) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.set(targetValue);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetTarget() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[26]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getClass1() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlString xgetClass1() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetClass1() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[27]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setClass1(String class1) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[27]);
            }
            target.setStringValue(class1);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetClass1(XmlString class1) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[27]);
            }
            target.set(class1);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetClass1() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[27]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getTitle() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlString xgetTitle() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetTitle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[28]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setTitle(String title) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.setStringValue(title);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetTitle(XmlString title) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.set(title);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetTitle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[28]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getAlt() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlString xgetAlt() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetAlt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[29]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setAlt(String alt) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[29]);
            }
            target.setStringValue(alt);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetAlt(XmlString alt) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[29]);
            }
            target.set(alt);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetAlt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[29]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getCoordsize() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlString xgetCoordsize() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[30]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetCoordsize() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[30]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setCoordsize(String coordsize) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[30]);
            }
            target.setStringValue(coordsize);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetCoordsize(XmlString coordsize) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[30]);
            }
            target.set(coordsize);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetCoordsize() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[30]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getCoordorigin() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlString xgetCoordorigin() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[31]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetCoordorigin() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[31]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setCoordorigin(String coordorigin) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[31]);
            }
            target.setStringValue(coordorigin);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetCoordorigin(XmlString coordorigin) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[31]);
            }
            target.set(coordorigin);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetCoordorigin() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[31]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getWrapcoords() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[32]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlString xgetWrapcoords() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[32]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetWrapcoords() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[32]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setWrapcoords(String wrapcoords) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[32]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[32]);
            }
            target.setStringValue(wrapcoords);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetWrapcoords(XmlString wrapcoords) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[32]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[32]);
            }
            target.set(wrapcoords);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetWrapcoords() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[32]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getPrint() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetPrint() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[33]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetPrint() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[33]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setPrint(STTrueFalse.Enum print) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[33]);
            }
            target.setEnumValue(print);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetPrint(STTrueFalse print) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[33]);
            }
            target.set(print);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetPrint() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[33]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getSpid() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlString xgetSpid() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[34]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetSpid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[34]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setSpid(String spid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[34]);
            }
            target.setStringValue(spid);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetSpid(XmlString spid) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[34]);
            }
            target.set(spid);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetSpid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[34]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getOned() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetOned() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[35]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetOned() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[35]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setOned(STTrueFalse.Enum oned) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[35]);
            }
            target.setEnumValue(oned);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetOned(STTrueFalse oned) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[35]);
            }
            target.set(oned);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetOned() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[35]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public BigInteger getRegroupid() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlInteger xgetRegroupid() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_attribute_user(PROPERTY_QNAME[36]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetRegroupid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[36]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setRegroupid(BigInteger regroupid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[36]);
            }
            target.setBigIntegerValue(regroupid);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetRegroupid(XmlInteger regroupid) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            if (target == null) {
                target = (XmlInteger) get_store().add_attribute_user(PROPERTY_QNAME[36]);
            }
            target.set(regroupid);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetRegroupid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[36]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getDoubleclicknotify() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[37]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetDoubleclicknotify() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[37]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetDoubleclicknotify() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[37]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setDoubleclicknotify(STTrueFalse.Enum doubleclicknotify) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[37]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[37]);
            }
            target.setEnumValue(doubleclicknotify);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetDoubleclicknotify(STTrueFalse doubleclicknotify) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[37]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[37]);
            }
            target.set(doubleclicknotify);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetDoubleclicknotify() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[37]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getButton() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[38]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetButton() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[38]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetButton() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[38]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setButton(STTrueFalse.Enum button) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[38]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[38]);
            }
            target.setEnumValue(button);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetButton(STTrueFalse button) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[38]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[38]);
            }
            target.set(button);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetButton() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[38]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getUserhidden() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[39]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetUserhidden() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[39]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetUserhidden() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[39]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setUserhidden(STTrueFalse.Enum userhidden) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[39]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[39]);
            }
            target.setEnumValue(userhidden);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetUserhidden(STTrueFalse userhidden) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[39]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[39]);
            }
            target.set(userhidden);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetUserhidden() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[39]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getBullet() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[40]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetBullet() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[40]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetBullet() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[40]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setBullet(STTrueFalse.Enum bullet) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[40]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[40]);
            }
            target.setEnumValue(bullet);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetBullet(STTrueFalse bullet) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[40]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[40]);
            }
            target.set(bullet);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetBullet() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[40]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getHr() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[41]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetHr() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[41]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetHr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[41]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setHr(STTrueFalse.Enum hr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[41]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[41]);
            }
            target.setEnumValue(hr);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetHr(STTrueFalse hr) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[41]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[41]);
            }
            target.set(hr);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetHr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[41]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getHrstd() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[42]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetHrstd() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[42]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetHrstd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[42]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setHrstd(STTrueFalse.Enum hrstd) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[42]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[42]);
            }
            target.setEnumValue(hrstd);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetHrstd(STTrueFalse hrstd) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[42]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[42]);
            }
            target.set(hrstd);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetHrstd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[42]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getHrnoshade() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[43]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetHrnoshade() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[43]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetHrnoshade() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[43]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setHrnoshade(STTrueFalse.Enum hrnoshade) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[43]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[43]);
            }
            target.setEnumValue(hrnoshade);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetHrnoshade(STTrueFalse hrnoshade) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[43]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[43]);
            }
            target.set(hrnoshade);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetHrnoshade() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[43]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public float getHrpct() {
        float floatValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[44]);
            floatValue = target == null ? 0.0f : target.getFloatValue();
        }
        return floatValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlFloat xgetHrpct() {
        XmlFloat target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlFloat) get_store().find_attribute_user(PROPERTY_QNAME[44]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetHrpct() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[44]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setHrpct(float hrpct) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[44]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[44]);
            }
            target.setFloatValue(hrpct);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetHrpct(XmlFloat hrpct) {
        synchronized (monitor()) {
            check_orphaned();
            XmlFloat target = (XmlFloat) get_store().find_attribute_user(PROPERTY_QNAME[44]);
            if (target == null) {
                target = (XmlFloat) get_store().add_attribute_user(PROPERTY_QNAME[44]);
            }
            target.set(hrpct);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetHrpct() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[44]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STHrAlign.Enum getHralign() {
        STHrAlign.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[45]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[45]);
            }
            r1 = target == null ? null : (STHrAlign.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STHrAlign xgetHralign() {
        STHrAlign target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STHrAlign) get_store().find_attribute_user(PROPERTY_QNAME[45]);
            if (target == null) {
                target = (STHrAlign) get_default_attribute_value(PROPERTY_QNAME[45]);
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetHralign() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[45]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setHralign(STHrAlign.Enum hralign) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[45]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[45]);
            }
            target.setEnumValue(hralign);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetHralign(STHrAlign hralign) {
        synchronized (monitor()) {
            check_orphaned();
            STHrAlign target = (STHrAlign) get_store().find_attribute_user(PROPERTY_QNAME[45]);
            if (target == null) {
                target = (STHrAlign) get_store().add_attribute_user(PROPERTY_QNAME[45]);
            }
            target.set(hralign);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetHralign() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[45]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getAllowincell() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[46]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetAllowincell() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[46]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetAllowincell() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[46]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setAllowincell(STTrueFalse.Enum allowincell) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[46]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[46]);
            }
            target.setEnumValue(allowincell);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetAllowincell(STTrueFalse allowincell) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[46]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[46]);
            }
            target.set(allowincell);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetAllowincell() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[46]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getAllowoverlap() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[47]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetAllowoverlap() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[47]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetAllowoverlap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[47]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setAllowoverlap(STTrueFalse.Enum allowoverlap) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[47]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[47]);
            }
            target.setEnumValue(allowoverlap);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetAllowoverlap(STTrueFalse allowoverlap) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[47]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[47]);
            }
            target.set(allowoverlap);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetAllowoverlap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[47]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getUserdrawn() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[48]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetUserdrawn() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[48]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetUserdrawn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[48]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setUserdrawn(STTrueFalse.Enum userdrawn) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[48]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[48]);
            }
            target.setEnumValue(userdrawn);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetUserdrawn(STTrueFalse userdrawn) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[48]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[48]);
            }
            target.set(userdrawn);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetUserdrawn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[48]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getBordertopcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[49]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlString xgetBordertopcolor() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[49]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetBordertopcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[49]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setBordertopcolor(String bordertopcolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[49]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[49]);
            }
            target.setStringValue(bordertopcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetBordertopcolor(XmlString bordertopcolor) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[49]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[49]);
            }
            target.set(bordertopcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetBordertopcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[49]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getBorderleftcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[50]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlString xgetBorderleftcolor() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[50]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetBorderleftcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[50]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setBorderleftcolor(String borderleftcolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[50]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[50]);
            }
            target.setStringValue(borderleftcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetBorderleftcolor(XmlString borderleftcolor) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[50]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[50]);
            }
            target.set(borderleftcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetBorderleftcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[50]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getBorderbottomcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[51]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlString xgetBorderbottomcolor() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[51]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetBorderbottomcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[51]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setBorderbottomcolor(String borderbottomcolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[51]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[51]);
            }
            target.setStringValue(borderbottomcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetBorderbottomcolor(XmlString borderbottomcolor) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[51]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[51]);
            }
            target.set(borderbottomcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetBorderbottomcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[51]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getBorderrightcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[52]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlString xgetBorderrightcolor() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[52]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetBorderrightcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[52]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setBorderrightcolor(String borderrightcolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[52]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[52]);
            }
            target.setStringValue(borderrightcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetBorderrightcolor(XmlString borderrightcolor) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[52]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[52]);
            }
            target.set(borderrightcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetBorderrightcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[52]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public BigInteger getDgmlayout() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[53]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STDiagramLayout xgetDgmlayout() {
        STDiagramLayout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[53]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetDgmlayout() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[53]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setDgmlayout(BigInteger dgmlayout) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[53]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[53]);
            }
            target.setBigIntegerValue(dgmlayout);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetDgmlayout(STDiagramLayout dgmlayout) {
        synchronized (monitor()) {
            check_orphaned();
            STDiagramLayout target = get_store().find_attribute_user(PROPERTY_QNAME[53]);
            if (target == null) {
                target = (STDiagramLayout) get_store().add_attribute_user(PROPERTY_QNAME[53]);
            }
            target.set(dgmlayout);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetDgmlayout() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[53]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public BigInteger getDgmnodekind() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[54]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlInteger xgetDgmnodekind() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_attribute_user(PROPERTY_QNAME[54]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetDgmnodekind() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[54]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setDgmnodekind(BigInteger dgmnodekind) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[54]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[54]);
            }
            target.setBigIntegerValue(dgmnodekind);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetDgmnodekind(XmlInteger dgmnodekind) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_attribute_user(PROPERTY_QNAME[54]);
            if (target == null) {
                target = (XmlInteger) get_store().add_attribute_user(PROPERTY_QNAME[54]);
            }
            target.set(dgmnodekind);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetDgmnodekind() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[54]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public BigInteger getDgmlayoutmru() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[55]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STDiagramLayout xgetDgmlayoutmru() {
        STDiagramLayout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[55]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetDgmlayoutmru() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[55]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setDgmlayoutmru(BigInteger dgmlayoutmru) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[55]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[55]);
            }
            target.setBigIntegerValue(dgmlayoutmru);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetDgmlayoutmru(STDiagramLayout dgmlayoutmru) {
        synchronized (monitor()) {
            check_orphaned();
            STDiagramLayout target = get_store().find_attribute_user(PROPERTY_QNAME[55]);
            if (target == null) {
                target = (STDiagramLayout) get_store().add_attribute_user(PROPERTY_QNAME[55]);
            }
            target.set(dgmlayoutmru);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetDgmlayoutmru() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[55]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STInsetMode.Enum getInsetmode() {
        STInsetMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[56]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[56]);
            }
            r1 = target == null ? null : (STInsetMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STInsetMode xgetInsetmode() {
        STInsetMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STInsetMode) get_store().find_attribute_user(PROPERTY_QNAME[56]);
            if (target == null) {
                target = (STInsetMode) get_default_attribute_value(PROPERTY_QNAME[56]);
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetInsetmode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[56]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setInsetmode(STInsetMode.Enum insetmode) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[56]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[56]);
            }
            target.setEnumValue(insetmode);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetInsetmode(STInsetMode insetmode) {
        synchronized (monitor()) {
            check_orphaned();
            STInsetMode target = (STInsetMode) get_store().find_attribute_user(PROPERTY_QNAME[56]);
            if (target == null) {
                target = (STInsetMode) get_store().add_attribute_user(PROPERTY_QNAME[56]);
            }
            target.set(insetmode);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetInsetmode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[56]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getChromakey() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[57]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STColorType xgetChromakey() {
        STColorType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[57]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetChromakey() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[57]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setChromakey(String chromakey) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[57]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[57]);
            }
            target.setStringValue(chromakey);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetChromakey(STColorType chromakey) {
        synchronized (monitor()) {
            check_orphaned();
            STColorType target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[57]);
            if (target == null) {
                target = (STColorType) get_store().add_attribute_user(PROPERTY_QNAME[57]);
            }
            target.set(chromakey);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetChromakey() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[57]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getFilled() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[58]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetFilled() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[58]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetFilled() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[58]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setFilled(STTrueFalse.Enum filled) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[58]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[58]);
            }
            target.setEnumValue(filled);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetFilled(STTrueFalse filled) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[58]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[58]);
            }
            target.set(filled);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetFilled() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[58]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getFillcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[59]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STColorType xgetFillcolor() {
        STColorType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[59]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetFillcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[59]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setFillcolor(String fillcolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[59]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[59]);
            }
            target.setStringValue(fillcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetFillcolor(STColorType fillcolor) {
        synchronized (monitor()) {
            check_orphaned();
            STColorType target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[59]);
            if (target == null) {
                target = (STColorType) get_store().add_attribute_user(PROPERTY_QNAME[59]);
            }
            target.set(fillcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetFillcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[59]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getOpacity() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[60]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlString xgetOpacity() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[60]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetOpacity() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[60]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setOpacity(String opacity) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[60]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[60]);
            }
            target.setStringValue(opacity);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetOpacity(XmlString opacity) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[60]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[60]);
            }
            target.set(opacity);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetOpacity() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[60]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getStroked() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[61]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetStroked() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[61]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetStroked() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[61]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setStroked(STTrueFalse.Enum stroked) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[61]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[61]);
            }
            target.setEnumValue(stroked);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetStroked(STTrueFalse stroked) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[61]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[61]);
            }
            target.set(stroked);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetStroked() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[61]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getStrokecolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[62]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STColorType xgetStrokecolor() {
        STColorType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[62]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetStrokecolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[62]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setStrokecolor(String strokecolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[62]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[62]);
            }
            target.setStringValue(strokecolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetStrokecolor(STColorType strokecolor) {
        synchronized (monitor()) {
            check_orphaned();
            STColorType target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[62]);
            if (target == null) {
                target = (STColorType) get_store().add_attribute_user(PROPERTY_QNAME[62]);
            }
            target.set(strokecolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetStrokecolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[62]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public String getStrokeweight() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[63]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlString xgetStrokeweight() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[63]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetStrokeweight() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[63]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setStrokeweight(String strokeweight) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[63]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[63]);
            }
            target.setStringValue(strokeweight);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetStrokeweight(XmlString strokeweight) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[63]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[63]);
            }
            target.set(strokeweight);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetStrokeweight() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[63]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getInsetpen() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[64]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetInsetpen() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[64]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetInsetpen() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[64]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setInsetpen(STTrueFalse.Enum insetpen) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[64]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[64]);
            }
            target.setEnumValue(insetpen);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetInsetpen(STTrueFalse insetpen) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[64]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[64]);
            }
            target.set(insetpen);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetInsetpen() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[64]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public float getSpt() {
        float floatValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[65]);
            floatValue = target == null ? 0.0f : target.getFloatValue();
        }
        return floatValue;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public XmlFloat xgetSpt() {
        XmlFloat target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlFloat) get_store().find_attribute_user(PROPERTY_QNAME[65]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetSpt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[65]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setSpt(float spt) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[65]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[65]);
            }
            target.setFloatValue(spt);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetSpt(XmlFloat spt) {
        synchronized (monitor()) {
            check_orphaned();
            XmlFloat target = (XmlFloat) get_store().find_attribute_user(PROPERTY_QNAME[65]);
            if (target == null) {
                target = (XmlFloat) get_store().add_attribute_user(PROPERTY_QNAME[65]);
            }
            target.set(spt);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetSpt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[65]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STConnectorType.Enum getConnectortype() {
        STConnectorType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[66]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[66]);
            }
            r1 = target == null ? null : (STConnectorType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STConnectorType xgetConnectortype() {
        STConnectorType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STConnectorType) get_store().find_attribute_user(PROPERTY_QNAME[66]);
            if (target == null) {
                target = (STConnectorType) get_default_attribute_value(PROPERTY_QNAME[66]);
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetConnectortype() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[66]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setConnectortype(STConnectorType.Enum connectortype) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[66]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[66]);
            }
            target.setEnumValue(connectortype);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetConnectortype(STConnectorType connectortype) {
        synchronized (monitor()) {
            check_orphaned();
            STConnectorType target = (STConnectorType) get_store().find_attribute_user(PROPERTY_QNAME[66]);
            if (target == null) {
                target = (STConnectorType) get_store().add_attribute_user(PROPERTY_QNAME[66]);
            }
            target.set(connectortype);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetConnectortype() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[66]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STBWMode.Enum getBwmode() {
        STBWMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[67]);
            r1 = target == null ? null : (STBWMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STBWMode xgetBwmode() {
        STBWMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[67]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetBwmode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[67]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setBwmode(STBWMode.Enum bwmode) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[67]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[67]);
            }
            target.setEnumValue(bwmode);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetBwmode(STBWMode bwmode) {
        synchronized (monitor()) {
            check_orphaned();
            STBWMode target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[67]);
            if (target == null) {
                target = (STBWMode) get_store().add_attribute_user(PROPERTY_QNAME[67]);
            }
            target.set(bwmode);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetBwmode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[67]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STBWMode.Enum getBwpure() {
        STBWMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[68]);
            r1 = target == null ? null : (STBWMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STBWMode xgetBwpure() {
        STBWMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[68]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetBwpure() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[68]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setBwpure(STBWMode.Enum bwpure) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[68]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[68]);
            }
            target.setEnumValue(bwpure);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetBwpure(STBWMode bwpure) {
        synchronized (monitor()) {
            check_orphaned();
            STBWMode target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[68]);
            if (target == null) {
                target = (STBWMode) get_store().add_attribute_user(PROPERTY_QNAME[68]);
            }
            target.set(bwpure);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetBwpure() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[68]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STBWMode.Enum getBwnormal() {
        STBWMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[69]);
            r1 = target == null ? null : (STBWMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STBWMode xgetBwnormal() {
        STBWMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[69]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetBwnormal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[69]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setBwnormal(STBWMode.Enum bwnormal) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[69]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[69]);
            }
            target.setEnumValue(bwnormal);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetBwnormal(STBWMode bwnormal) {
        synchronized (monitor()) {
            check_orphaned();
            STBWMode target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[69]);
            if (target == null) {
                target = (STBWMode) get_store().add_attribute_user(PROPERTY_QNAME[69]);
            }
            target.set(bwnormal);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetBwnormal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[69]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getForcedash() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[70]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetForcedash() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[70]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetForcedash() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[70]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setForcedash(STTrueFalse.Enum forcedash) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[70]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[70]);
            }
            target.setEnumValue(forcedash);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetForcedash(STTrueFalse forcedash) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[70]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[70]);
            }
            target.set(forcedash);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetForcedash() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[70]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getOleicon() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[71]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetOleicon() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[71]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetOleicon() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[71]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setOleicon(STTrueFalse.Enum oleicon) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[71]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[71]);
            }
            target.setEnumValue(oleicon);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetOleicon(STTrueFalse oleicon) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[71]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[71]);
            }
            target.set(oleicon);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetOleicon() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[71]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalseBlank.Enum getOle() {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[72]);
            r1 = target == null ? null : (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalseBlank xgetOle() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_attribute_user(PROPERTY_QNAME[72]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetOle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[72]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setOle(STTrueFalseBlank.Enum ole) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[72]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[72]);
            }
            target.setEnumValue(ole);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetOle(STTrueFalseBlank ole) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_attribute_user(PROPERTY_QNAME[72]);
            if (target == null) {
                target = (STTrueFalseBlank) get_store().add_attribute_user(PROPERTY_QNAME[72]);
            }
            target.set(ole);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetOle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[72]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getPreferrelative() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[73]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetPreferrelative() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[73]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetPreferrelative() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[73]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setPreferrelative(STTrueFalse.Enum preferrelative) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[73]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[73]);
            }
            target.setEnumValue(preferrelative);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetPreferrelative(STTrueFalse preferrelative) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[73]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[73]);
            }
            target.set(preferrelative);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetPreferrelative() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[73]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getCliptowrap() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[74]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetCliptowrap() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[74]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetCliptowrap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[74]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setCliptowrap(STTrueFalse.Enum cliptowrap) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[74]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[74]);
            }
            target.setEnumValue(cliptowrap);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetCliptowrap(STTrueFalse cliptowrap) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[74]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[74]);
            }
            target.set(cliptowrap);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetCliptowrap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[74]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse.Enum getClip() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[75]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public STTrueFalse xgetClip() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[75]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public boolean isSetClip() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[75]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void setClip(STTrueFalse.Enum clip) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[75]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[75]);
            }
            target.setEnumValue(clip);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void xsetClip(STTrueFalse clip) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[75]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[75]);
            }
            target.set(clip);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRect
    public void unsetClip() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[75]);
        }
    }
}
