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
import com.microsoft.schemas.vml.CTRoundRect;
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
public class CTRoundRectImpl extends XmlComplexContentImpl implements CTRoundRect {
    private static final QName[] PROPERTY_QNAME = {new QName("urn:schemas-microsoft-com:vml", "path"), new QName("urn:schemas-microsoft-com:vml", "formulas"), new QName("urn:schemas-microsoft-com:vml", "handles"), new QName("urn:schemas-microsoft-com:vml", "fill"), new QName("urn:schemas-microsoft-com:vml", "stroke"), new QName("urn:schemas-microsoft-com:vml", "shadow"), new QName("urn:schemas-microsoft-com:vml", "textbox"), new QName("urn:schemas-microsoft-com:vml", "textpath"), new QName("urn:schemas-microsoft-com:vml", "imagedata"), new QName("urn:schemas-microsoft-com:office:office", "skew"), new QName("urn:schemas-microsoft-com:office:office", "extrusion"), new QName("urn:schemas-microsoft-com:office:office", "callout"), new QName("urn:schemas-microsoft-com:office:office", "lock"), new QName("urn:schemas-microsoft-com:office:office", "clippath"), new QName("urn:schemas-microsoft-com:office:office", "signatureline"), new QName("urn:schemas-microsoft-com:office:word", "wrap"), new QName("urn:schemas-microsoft-com:office:word", "anchorlock"), new QName("urn:schemas-microsoft-com:office:word", "bordertop"), new QName("urn:schemas-microsoft-com:office:word", "borderbottom"), new QName("urn:schemas-microsoft-com:office:word", "borderleft"), new QName("urn:schemas-microsoft-com:office:word", "borderright"), new QName("urn:schemas-microsoft-com:office:excel", "ClientData"), new QName("urn:schemas-microsoft-com:office:powerpoint", "textdata"), new QName("", "id"), new QName("", "style"), new QName("", "href"), new QName("", TypedValues.AttributesType.S_TARGET), new QName("", "class"), new QName("", "title"), new QName("", "alt"), new QName("", "coordsize"), new QName("", "coordorigin"), new QName("", "wrapcoords"), new QName("", "print"), new QName("urn:schemas-microsoft-com:office:office", "spid"), new QName("urn:schemas-microsoft-com:office:office", "oned"), new QName("urn:schemas-microsoft-com:office:office", "regroupid"), new QName("urn:schemas-microsoft-com:office:office", "doubleclicknotify"), new QName("urn:schemas-microsoft-com:office:office", "button"), new QName("urn:schemas-microsoft-com:office:office", "userhidden"), new QName("urn:schemas-microsoft-com:office:office", "bullet"), new QName("urn:schemas-microsoft-com:office:office", "hr"), new QName("urn:schemas-microsoft-com:office:office", "hrstd"), new QName("urn:schemas-microsoft-com:office:office", "hrnoshade"), new QName("urn:schemas-microsoft-com:office:office", "hrpct"), new QName("urn:schemas-microsoft-com:office:office", "hralign"), new QName("urn:schemas-microsoft-com:office:office", "allowincell"), new QName("urn:schemas-microsoft-com:office:office", "allowoverlap"), new QName("urn:schemas-microsoft-com:office:office", "userdrawn"), new QName("urn:schemas-microsoft-com:office:office", "bordertopcolor"), new QName("urn:schemas-microsoft-com:office:office", "borderleftcolor"), new QName("urn:schemas-microsoft-com:office:office", "borderbottomcolor"), new QName("urn:schemas-microsoft-com:office:office", "borderrightcolor"), new QName("urn:schemas-microsoft-com:office:office", "dgmlayout"), new QName("urn:schemas-microsoft-com:office:office", "dgmnodekind"), new QName("urn:schemas-microsoft-com:office:office", "dgmlayoutmru"), new QName("urn:schemas-microsoft-com:office:office", "insetmode"), new QName("", "chromakey"), new QName("", "filled"), new QName("", "fillcolor"), new QName("", "opacity"), new QName("", "stroked"), new QName("", "strokecolor"), new QName("", "strokeweight"), new QName("", "insetpen"), new QName("urn:schemas-microsoft-com:office:office", "spt"), new QName("urn:schemas-microsoft-com:office:office", "connectortype"), new QName("urn:schemas-microsoft-com:office:office", "bwmode"), new QName("urn:schemas-microsoft-com:office:office", "bwpure"), new QName("urn:schemas-microsoft-com:office:office", "bwnormal"), new QName("urn:schemas-microsoft-com:office:office", "forcedash"), new QName("urn:schemas-microsoft-com:office:office", "oleicon"), new QName("urn:schemas-microsoft-com:office:office", "ole"), new QName("urn:schemas-microsoft-com:office:office", "preferrelative"), new QName("urn:schemas-microsoft-com:office:office", "cliptowrap"), new QName("urn:schemas-microsoft-com:office:office", "clip"), new QName("", "arcsize")};
    private static final long serialVersionUID = 1;

    public CTRoundRectImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTPath> getPathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getPathArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setPathArray(((Integer) obj).intValue(), (CTPath) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewPath(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removePath(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfPathArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTPath[] getPathArray() {
        return (CTPath[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTPath[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfPathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setPathArray(CTPath[] pathArray) {
        check_orphaned();
        arraySetterHelper(pathArray, PROPERTY_QNAME[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setPathArray(int i, CTPath path) {
        generatedSetterHelperImpl(path, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTPath insertNewPath(int i) {
        CTPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTPath addNewPath() {
        CTPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removePath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTFormulas> getFormulasList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getFormulasArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setFormulasArray(((Integer) obj).intValue(), (CTFormulas) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewFormulas(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeFormulas(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfFormulasArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTFormulas[] getFormulasArray() {
        return (CTFormulas[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTFormulas[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfFormulasArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setFormulasArray(CTFormulas[] formulasArray) {
        check_orphaned();
        arraySetterHelper(formulasArray, PROPERTY_QNAME[1]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setFormulasArray(int i, CTFormulas formulas) {
        generatedSetterHelperImpl(formulas, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTFormulas insertNewFormulas(int i) {
        CTFormulas target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFormulas) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTFormulas addNewFormulas() {
        CTFormulas target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFormulas) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeFormulas(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTHandles> getHandlesList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getHandlesArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setHandlesArray(((Integer) obj).intValue(), (CTHandles) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewHandles(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeHandles(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfHandlesArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTHandles[] getHandlesArray() {
        return (CTHandles[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTHandles[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfHandlesArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setHandlesArray(CTHandles[] handlesArray) {
        check_orphaned();
        arraySetterHelper(handlesArray, PROPERTY_QNAME[2]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setHandlesArray(int i, CTHandles handles) {
        generatedSetterHelperImpl(handles, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTHandles insertNewHandles(int i) {
        CTHandles target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHandles) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTHandles addNewHandles() {
        CTHandles target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHandles) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeHandles(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTFill> getFillList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getFillArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setFillArray(((Integer) obj).intValue(), (CTFill) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewFill(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeFill(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfFillArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTFill[] getFillArray() {
        return (CTFill[]) getXmlObjectArray(PROPERTY_QNAME[3], new CTFill[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfFillArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setFillArray(CTFill[] fillArray) {
        check_orphaned();
        arraySetterHelper(fillArray, PROPERTY_QNAME[3]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setFillArray(int i, CTFill fill) {
        generatedSetterHelperImpl(fill, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTFill insertNewFill(int i) {
        CTFill target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFill) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTFill addNewFill() {
        CTFill target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFill) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeFill(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTStroke> getStrokeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda98
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getStrokeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda99
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setStrokeArray(((Integer) obj).intValue(), (CTStroke) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda100
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewStroke(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda101
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeStroke(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda102
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfStrokeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTStroke[] getStrokeArray() {
        return (CTStroke[]) getXmlObjectArray(PROPERTY_QNAME[4], new CTStroke[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfStrokeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setStrokeArray(CTStroke[] strokeArray) {
        check_orphaned();
        arraySetterHelper(strokeArray, PROPERTY_QNAME[4]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setStrokeArray(int i, CTStroke stroke) {
        generatedSetterHelperImpl(stroke, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTStroke insertNewStroke(int i) {
        CTStroke target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStroke) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTStroke addNewStroke() {
        CTStroke target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStroke) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeStroke(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTShadow> getShadowList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getShadowArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setShadowArray(((Integer) obj).intValue(), (CTShadow) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewShadow(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeShadow(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfShadowArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTShadow[] getShadowArray() {
        return (CTShadow[]) getXmlObjectArray(PROPERTY_QNAME[5], new CTShadow[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfShadowArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setShadowArray(CTShadow[] shadowArray) {
        check_orphaned();
        arraySetterHelper(shadowArray, PROPERTY_QNAME[5]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setShadowArray(int i, CTShadow shadow) {
        generatedSetterHelperImpl(shadow, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTShadow insertNewShadow(int i) {
        CTShadow target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShadow) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTShadow addNewShadow() {
        CTShadow target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShadow) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeShadow(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTTextbox> getTextboxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda60
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getTextboxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setTextboxArray(((Integer) obj).intValue(), (CTTextbox) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewTextbox(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeTextbox(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfTextboxArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTTextbox[] getTextboxArray() {
        return (CTTextbox[]) getXmlObjectArray(PROPERTY_QNAME[6], new CTTextbox[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfTextboxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setTextboxArray(CTTextbox[] textboxArray) {
        check_orphaned();
        arraySetterHelper(textboxArray, PROPERTY_QNAME[6]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setTextboxArray(int i, CTTextbox textbox) {
        generatedSetterHelperImpl(textbox, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTTextbox insertNewTextbox(int i) {
        CTTextbox target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextbox) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTTextbox addNewTextbox() {
        CTTextbox target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextbox) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeTextbox(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTTextPath> getTextpathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda82
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getTextpathArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda83
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setTextpathArray(((Integer) obj).intValue(), (CTTextPath) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda84
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewTextpath(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda85
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeTextpath(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda86
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfTextpathArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTTextPath[] getTextpathArray() {
        return (CTTextPath[]) getXmlObjectArray(PROPERTY_QNAME[7], new CTTextPath[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfTextpathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setTextpathArray(CTTextPath[] textpathArray) {
        check_orphaned();
        arraySetterHelper(textpathArray, PROPERTY_QNAME[7]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setTextpathArray(int i, CTTextPath textpath) {
        generatedSetterHelperImpl(textpath, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTTextPath insertNewTextpath(int i) {
        CTTextPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextPath) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTTextPath addNewTextpath() {
        CTTextPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextPath) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeTextpath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTImageData> getImagedataList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda71
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getImagedataArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda72
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setImagedataArray(((Integer) obj).intValue(), (CTImageData) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda73
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewImagedata(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda74
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeImagedata(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda75
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfImagedataArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTImageData[] getImagedataArray() {
        return (CTImageData[]) getXmlObjectArray(PROPERTY_QNAME[8], new CTImageData[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfImagedataArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setImagedataArray(CTImageData[] imagedataArray) {
        check_orphaned();
        arraySetterHelper(imagedataArray, PROPERTY_QNAME[8]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setImagedataArray(int i, CTImageData imagedata) {
        generatedSetterHelperImpl(imagedata, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTImageData insertNewImagedata(int i) {
        CTImageData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTImageData) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTImageData addNewImagedata() {
        CTImageData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTImageData) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeImagedata(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTSkew> getSkewList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getSkewArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setSkewArray(((Integer) obj).intValue(), (CTSkew) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewSkew(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeSkew(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfSkewArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTSkew[] getSkewArray() {
        return getXmlObjectArray(PROPERTY_QNAME[9], (XmlObject[]) new CTSkew[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfSkewArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setSkewArray(CTSkew[] skewArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) skewArray, PROPERTY_QNAME[9]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setSkewArray(int i, CTSkew skew) {
        generatedSetterHelperImpl(skew, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTSkew insertNewSkew(int i) {
        CTSkew target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTSkew addNewSkew() {
        CTSkew target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeSkew(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTExtrusion> getExtrusionList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda93
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getExtrusionArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda94
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setExtrusionArray(((Integer) obj).intValue(), (CTExtrusion) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda95
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewExtrusion(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda96
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeExtrusion(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda97
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfExtrusionArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTExtrusion[] getExtrusionArray() {
        return getXmlObjectArray(PROPERTY_QNAME[10], (XmlObject[]) new CTExtrusion[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfExtrusionArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setExtrusionArray(CTExtrusion[] extrusionArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) extrusionArray, PROPERTY_QNAME[10]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setExtrusionArray(int i, CTExtrusion extrusion) {
        generatedSetterHelperImpl(extrusion, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTExtrusion insertNewExtrusion(int i) {
        CTExtrusion target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTExtrusion addNewExtrusion() {
        CTExtrusion target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeExtrusion(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTCallout> getCalloutList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getCalloutArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setCalloutArray(((Integer) obj).intValue(), (CTCallout) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewCallout(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeCallout(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfCalloutArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTCallout[] getCalloutArray() {
        return getXmlObjectArray(PROPERTY_QNAME[11], (XmlObject[]) new CTCallout[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfCalloutArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setCalloutArray(CTCallout[] calloutArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) calloutArray, PROPERTY_QNAME[11]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setCalloutArray(int i, CTCallout callout) {
        generatedSetterHelperImpl(callout, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTCallout insertNewCallout(int i) {
        CTCallout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTCallout addNewCallout() {
        CTCallout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeCallout(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTLock> getLockList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda87
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getLockArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda88
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setLockArray(((Integer) obj).intValue(), (CTLock) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda89
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewLock(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda90
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeLock(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda91
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfLockArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTLock[] getLockArray() {
        return (CTLock[]) getXmlObjectArray(PROPERTY_QNAME[12], new CTLock[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfLockArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setLockArray(CTLock[] lockArray) {
        check_orphaned();
        arraySetterHelper(lockArray, PROPERTY_QNAME[12]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setLockArray(int i, CTLock lock) {
        generatedSetterHelperImpl(lock, PROPERTY_QNAME[12], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTLock insertNewLock(int i) {
        CTLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLock) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTLock addNewLock() {
        CTLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLock) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeLock(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTClipPath> getClippathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getClippathArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setClippathArray(((Integer) obj).intValue(), (CTClipPath) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewClippath(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeClippath(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfClippathArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTClipPath[] getClippathArray() {
        return getXmlObjectArray(PROPERTY_QNAME[13], (XmlObject[]) new CTClipPath[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfClippathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setClippathArray(CTClipPath[] clippathArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) clippathArray, PROPERTY_QNAME[13]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setClippathArray(int i, CTClipPath clippath) {
        generatedSetterHelperImpl(clippath, PROPERTY_QNAME[13], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTClipPath insertNewClippath(int i) {
        CTClipPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTClipPath addNewClippath() {
        CTClipPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeClippath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTSignatureLine> getSignaturelineList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda76
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getSignaturelineArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda77
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setSignaturelineArray(((Integer) obj).intValue(), (CTSignatureLine) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda78
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewSignatureline(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda79
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeSignatureline(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda80
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfSignaturelineArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTSignatureLine[] getSignaturelineArray() {
        return (CTSignatureLine[]) getXmlObjectArray(PROPERTY_QNAME[14], new CTSignatureLine[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfSignaturelineArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setSignaturelineArray(CTSignatureLine[] signaturelineArray) {
        check_orphaned();
        arraySetterHelper(signaturelineArray, PROPERTY_QNAME[14]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setSignaturelineArray(int i, CTSignatureLine signatureline) {
        generatedSetterHelperImpl(signatureline, PROPERTY_QNAME[14], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTSignatureLine insertNewSignatureline(int i) {
        CTSignatureLine target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSignatureLine) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTSignatureLine addNewSignatureline() {
        CTSignatureLine target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSignatureLine) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeSignatureline(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTWrap> getWrapList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda65
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getWrapArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda66
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setWrapArray(((Integer) obj).intValue(), (CTWrap) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda67
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewWrap(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda68
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeWrap(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda69
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfWrapArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTWrap[] getWrapArray() {
        return (CTWrap[]) getXmlObjectArray(PROPERTY_QNAME[15], new CTWrap[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfWrapArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setWrapArray(CTWrap[] wrapArray) {
        check_orphaned();
        arraySetterHelper(wrapArray, PROPERTY_QNAME[15]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setWrapArray(int i, CTWrap wrap) {
        generatedSetterHelperImpl(wrap, PROPERTY_QNAME[15], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTWrap insertNewWrap(int i) {
        CTWrap target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTWrap) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTWrap addNewWrap() {
        CTWrap target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTWrap) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeWrap(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTAnchorLock> getAnchorlockList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda104
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getAnchorlockArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda105
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setAnchorlockArray(((Integer) obj).intValue(), (CTAnchorLock) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda106
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewAnchorlock(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda107
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeAnchorlock(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda108
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfAnchorlockArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTAnchorLock[] getAnchorlockArray() {
        return (CTAnchorLock[]) getXmlObjectArray(PROPERTY_QNAME[16], new CTAnchorLock[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfAnchorlockArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setAnchorlockArray(CTAnchorLock[] anchorlockArray) {
        check_orphaned();
        arraySetterHelper(anchorlockArray, PROPERTY_QNAME[16]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setAnchorlockArray(int i, CTAnchorLock anchorlock) {
        generatedSetterHelperImpl(anchorlock, PROPERTY_QNAME[16], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTAnchorLock insertNewAnchorlock(int i) {
        CTAnchorLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAnchorLock) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTAnchorLock addNewAnchorlock() {
        CTAnchorLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAnchorLock) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeAnchorlock(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTBorder> getBordertopList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda109
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getBordertopArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda110
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setBordertopArray(((Integer) obj).intValue(), (CTBorder) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda111
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewBordertop(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda112
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeBordertop(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda113
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfBordertopArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTBorder[] getBordertopArray() {
        return getXmlObjectArray(PROPERTY_QNAME[17], (XmlObject[]) new CTBorder[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfBordertopArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setBordertopArray(CTBorder[] bordertopArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) bordertopArray, PROPERTY_QNAME[17]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setBordertopArray(int i, CTBorder bordertop) {
        generatedSetterHelperImpl(bordertop, PROPERTY_QNAME[17], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTBorder insertNewBordertop(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTBorder addNewBordertop() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeBordertop(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTBorder> getBorderbottomList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getBorderbottomArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setBorderbottomArray(((Integer) obj).intValue(), (CTBorder) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewBorderbottom(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeBorderbottom(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfBorderbottomArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTBorder[] getBorderbottomArray() {
        return getXmlObjectArray(PROPERTY_QNAME[18], (XmlObject[]) new CTBorder[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfBorderbottomArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setBorderbottomArray(CTBorder[] borderbottomArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) borderbottomArray, PROPERTY_QNAME[18]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setBorderbottomArray(int i, CTBorder borderbottom) {
        generatedSetterHelperImpl(borderbottom, PROPERTY_QNAME[18], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTBorder insertNewBorderbottom(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTBorder addNewBorderbottom() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeBorderbottom(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTBorder> getBorderleftList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getBorderleftArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setBorderleftArray(((Integer) obj).intValue(), (CTBorder) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewBorderleft(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeBorderleft(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfBorderleftArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTBorder[] getBorderleftArray() {
        return getXmlObjectArray(PROPERTY_QNAME[19], (XmlObject[]) new CTBorder[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfBorderleftArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setBorderleftArray(CTBorder[] borderleftArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) borderleftArray, PROPERTY_QNAME[19]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setBorderleftArray(int i, CTBorder borderleft) {
        generatedSetterHelperImpl(borderleft, PROPERTY_QNAME[19], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTBorder insertNewBorderleft(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTBorder addNewBorderleft() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeBorderleft(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTBorder> getBorderrightList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda70
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getBorderrightArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda81
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setBorderrightArray(((Integer) obj).intValue(), (CTBorder) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda92
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewBorderright(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda103
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeBorderright(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda114
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfBorderrightArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTBorder[] getBorderrightArray() {
        return getXmlObjectArray(PROPERTY_QNAME[20], (XmlObject[]) new CTBorder[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfBorderrightArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setBorderrightArray(CTBorder[] borderrightArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) borderrightArray, PROPERTY_QNAME[20]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setBorderrightArray(int i, CTBorder borderright) {
        generatedSetterHelperImpl(borderright, PROPERTY_QNAME[20], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTBorder insertNewBorderright(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTBorder addNewBorderright() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeBorderright(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTClientData> getClientDataList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getClientDataArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setClientDataArray(((Integer) obj).intValue(), (CTClientData) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewClientData(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeClientData(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfClientDataArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTClientData[] getClientDataArray() {
        return (CTClientData[]) getXmlObjectArray(PROPERTY_QNAME[21], new CTClientData[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfClientDataArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setClientDataArray(CTClientData[] clientDataArray) {
        check_orphaned();
        arraySetterHelper(clientDataArray, PROPERTY_QNAME[21]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setClientDataArray(int i, CTClientData clientData) {
        generatedSetterHelperImpl(clientData, PROPERTY_QNAME[21], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTClientData insertNewClientData(int i) {
        CTClientData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTClientData) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTClientData addNewClientData() {
        CTClientData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTClientData) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeClientData(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public List<CTRel> getTextdataList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.getTextdataArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRoundRectImpl.this.setTextdataArray(((Integer) obj).intValue(), (CTRel) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRoundRectImpl.this.insertNewTextdata(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRoundRectImpl.this.removeTextdata(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTRoundRectImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRoundRectImpl.this.sizeOfTextdataArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTRel[] getTextdataArray() {
        return getXmlObjectArray(PROPERTY_QNAME[22], (XmlObject[]) new CTRel[0]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public int sizeOfTextdataArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setTextdataArray(CTRel[] textdataArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) textdataArray, PROPERTY_QNAME[22]);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setTextdataArray(int i, CTRel textdata) {
        generatedSetterHelperImpl(textdata, PROPERTY_QNAME[22], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTRel insertNewTextdata(int i) {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public CTRel addNewTextdata() {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void removeTextdata(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlString xgetId() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[23]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[23]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getStyle() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlString xgetStyle() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[24]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[24]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getHref() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlString xgetHref() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetHref() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[25]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetHref() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[25]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getTarget() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlString xgetTarget() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetTarget() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[26]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetTarget() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[26]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getClass1() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlString xgetClass1() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetClass1() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[27]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetClass1() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[27]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getTitle() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlString xgetTitle() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetTitle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[28]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetTitle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[28]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getAlt() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlString xgetAlt() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetAlt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[29]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetAlt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[29]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getCoordsize() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlString xgetCoordsize() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[30]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetCoordsize() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[30]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetCoordsize() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[30]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getCoordorigin() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlString xgetCoordorigin() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[31]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetCoordorigin() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[31]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetCoordorigin() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[31]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getWrapcoords() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[32]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlString xgetWrapcoords() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[32]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetWrapcoords() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[32]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetWrapcoords() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[32]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getPrint() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetPrint() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[33]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetPrint() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[33]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetPrint() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[33]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getSpid() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlString xgetSpid() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[34]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetSpid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[34]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetSpid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[34]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getOned() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetOned() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[35]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetOned() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[35]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetOned() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[35]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public BigInteger getRegroupid() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlInteger xgetRegroupid() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_attribute_user(PROPERTY_QNAME[36]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetRegroupid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[36]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetRegroupid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[36]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getDoubleclicknotify() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[37]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetDoubleclicknotify() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[37]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetDoubleclicknotify() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[37]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetDoubleclicknotify() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[37]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getButton() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[38]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetButton() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[38]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetButton() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[38]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetButton() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[38]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getUserhidden() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[39]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetUserhidden() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[39]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetUserhidden() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[39]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetUserhidden() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[39]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getBullet() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[40]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetBullet() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[40]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetBullet() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[40]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetBullet() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[40]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getHr() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[41]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetHr() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[41]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetHr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[41]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetHr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[41]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getHrstd() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[42]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetHrstd() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[42]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetHrstd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[42]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetHrstd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[42]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getHrnoshade() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[43]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetHrnoshade() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[43]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetHrnoshade() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[43]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetHrnoshade() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[43]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public float getHrpct() {
        float floatValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[44]);
            floatValue = target == null ? 0.0f : target.getFloatValue();
        }
        return floatValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlFloat xgetHrpct() {
        XmlFloat target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlFloat) get_store().find_attribute_user(PROPERTY_QNAME[44]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetHrpct() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[44]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetHrpct() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[44]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetHralign() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[45]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetHralign() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[45]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getAllowincell() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[46]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetAllowincell() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[46]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetAllowincell() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[46]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetAllowincell() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[46]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getAllowoverlap() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[47]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetAllowoverlap() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[47]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetAllowoverlap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[47]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetAllowoverlap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[47]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getUserdrawn() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[48]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetUserdrawn() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[48]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetUserdrawn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[48]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetUserdrawn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[48]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getBordertopcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[49]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlString xgetBordertopcolor() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[49]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetBordertopcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[49]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetBordertopcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[49]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getBorderleftcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[50]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlString xgetBorderleftcolor() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[50]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetBorderleftcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[50]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetBorderleftcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[50]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getBorderbottomcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[51]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlString xgetBorderbottomcolor() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[51]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetBorderbottomcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[51]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetBorderbottomcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[51]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getBorderrightcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[52]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlString xgetBorderrightcolor() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[52]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetBorderrightcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[52]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetBorderrightcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[52]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public BigInteger getDgmlayout() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[53]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STDiagramLayout xgetDgmlayout() {
        STDiagramLayout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[53]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetDgmlayout() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[53]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetDgmlayout() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[53]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public BigInteger getDgmnodekind() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[54]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlInteger xgetDgmnodekind() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_attribute_user(PROPERTY_QNAME[54]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetDgmnodekind() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[54]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetDgmnodekind() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[54]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public BigInteger getDgmlayoutmru() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[55]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STDiagramLayout xgetDgmlayoutmru() {
        STDiagramLayout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[55]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetDgmlayoutmru() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[55]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetDgmlayoutmru() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[55]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetInsetmode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[56]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetInsetmode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[56]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getChromakey() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[57]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STColorType xgetChromakey() {
        STColorType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[57]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetChromakey() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[57]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetChromakey() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[57]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getFilled() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[58]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetFilled() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[58]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetFilled() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[58]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetFilled() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[58]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getFillcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[59]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STColorType xgetFillcolor() {
        STColorType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[59]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetFillcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[59]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetFillcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[59]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getOpacity() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[60]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlString xgetOpacity() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[60]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetOpacity() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[60]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetOpacity() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[60]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getStroked() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[61]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetStroked() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[61]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetStroked() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[61]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetStroked() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[61]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getStrokecolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[62]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STColorType xgetStrokecolor() {
        STColorType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[62]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetStrokecolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[62]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetStrokecolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[62]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getStrokeweight() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[63]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlString xgetStrokeweight() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[63]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetStrokeweight() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[63]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetStrokeweight() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[63]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getInsetpen() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[64]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetInsetpen() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[64]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetInsetpen() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[64]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetInsetpen() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[64]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public float getSpt() {
        float floatValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[65]);
            floatValue = target == null ? 0.0f : target.getFloatValue();
        }
        return floatValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlFloat xgetSpt() {
        XmlFloat target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlFloat) get_store().find_attribute_user(PROPERTY_QNAME[65]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetSpt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[65]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetSpt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[65]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetConnectortype() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[66]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetConnectortype() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[66]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STBWMode.Enum getBwmode() {
        STBWMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[67]);
            r1 = target == null ? null : (STBWMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STBWMode xgetBwmode() {
        STBWMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[67]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetBwmode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[67]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetBwmode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[67]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STBWMode.Enum getBwpure() {
        STBWMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[68]);
            r1 = target == null ? null : (STBWMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STBWMode xgetBwpure() {
        STBWMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[68]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetBwpure() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[68]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetBwpure() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[68]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STBWMode.Enum getBwnormal() {
        STBWMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[69]);
            r1 = target == null ? null : (STBWMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STBWMode xgetBwnormal() {
        STBWMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[69]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetBwnormal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[69]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetBwnormal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[69]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getForcedash() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[70]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetForcedash() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[70]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetForcedash() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[70]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetForcedash() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[70]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getOleicon() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[71]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetOleicon() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[71]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetOleicon() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[71]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetOleicon() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[71]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalseBlank.Enum getOle() {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[72]);
            r1 = target == null ? null : (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalseBlank xgetOle() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_attribute_user(PROPERTY_QNAME[72]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetOle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[72]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetOle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[72]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getPreferrelative() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[73]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetPreferrelative() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[73]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetPreferrelative() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[73]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetPreferrelative() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[73]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getCliptowrap() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[74]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetCliptowrap() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[74]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetCliptowrap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[74]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetCliptowrap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[74]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse.Enum getClip() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[75]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public STTrueFalse xgetClip() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[75]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetClip() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[75]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
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

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetClip() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[75]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public String getArcsize() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[76]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public XmlString xgetArcsize() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[76]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public boolean isSetArcsize() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[76]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void setArcsize(String arcsize) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[76]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[76]);
            }
            target.setStringValue(arcsize);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void xsetArcsize(XmlString arcsize) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[76]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[76]);
            }
            target.set(arcsize);
        }
    }

    @Override // com.microsoft.schemas.vml.CTRoundRect
    public void unsetArcsize() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[76]);
        }
    }
}
