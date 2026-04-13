package com.microsoft.schemas.vml.impl;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import com.microsoft.schemas.office.excel.CTClientData;
import com.microsoft.schemas.office.office.CTCallout;
import com.microsoft.schemas.office.office.CTClipPath;
import com.microsoft.schemas.office.office.CTEquationXml;
import com.microsoft.schemas.office.office.CTExtrusion;
import com.microsoft.schemas.office.office.CTInk;
import com.microsoft.schemas.office.office.CTLock;
import com.microsoft.schemas.office.office.CTSignatureLine;
import com.microsoft.schemas.office.office.CTSkew;
import com.microsoft.schemas.office.office.STBWMode;
import com.microsoft.schemas.office.office.STConnectorType;
import com.microsoft.schemas.office.office.STDiagramLayout;
import com.microsoft.schemas.office.office.STHrAlign;
import com.microsoft.schemas.office.office.STInsetMode;
import com.microsoft.schemas.office.powerpoint.CTEmpty;
import com.microsoft.schemas.office.powerpoint.CTRel;
import com.microsoft.schemas.office.word.CTAnchorLock;
import com.microsoft.schemas.office.word.CTBorder;
import com.microsoft.schemas.office.word.CTWrap;
import com.microsoft.schemas.vml.CTFill;
import com.microsoft.schemas.vml.CTFormulas;
import com.microsoft.schemas.vml.CTHandles;
import com.microsoft.schemas.vml.CTImageData;
import com.microsoft.schemas.vml.CTPath;
import com.microsoft.schemas.vml.CTShadow;
import com.microsoft.schemas.vml.CTShape;
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
import org.apache.xmlbeans.XmlBase64Binary;
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
public class CTShapeImpl extends XmlComplexContentImpl implements CTShape {
    private static final QName[] PROPERTY_QNAME = {new QName("urn:schemas-microsoft-com:vml", "path"), new QName("urn:schemas-microsoft-com:vml", "formulas"), new QName("urn:schemas-microsoft-com:vml", "handles"), new QName("urn:schemas-microsoft-com:vml", "fill"), new QName("urn:schemas-microsoft-com:vml", "stroke"), new QName("urn:schemas-microsoft-com:vml", "shadow"), new QName("urn:schemas-microsoft-com:vml", "textbox"), new QName("urn:schemas-microsoft-com:vml", "textpath"), new QName("urn:schemas-microsoft-com:vml", "imagedata"), new QName("urn:schemas-microsoft-com:office:office", "skew"), new QName("urn:schemas-microsoft-com:office:office", "extrusion"), new QName("urn:schemas-microsoft-com:office:office", "callout"), new QName("urn:schemas-microsoft-com:office:office", "lock"), new QName("urn:schemas-microsoft-com:office:office", "clippath"), new QName("urn:schemas-microsoft-com:office:office", "signatureline"), new QName("urn:schemas-microsoft-com:office:word", "wrap"), new QName("urn:schemas-microsoft-com:office:word", "anchorlock"), new QName("urn:schemas-microsoft-com:office:word", "bordertop"), new QName("urn:schemas-microsoft-com:office:word", "borderbottom"), new QName("urn:schemas-microsoft-com:office:word", "borderleft"), new QName("urn:schemas-microsoft-com:office:word", "borderright"), new QName("urn:schemas-microsoft-com:office:excel", "ClientData"), new QName("urn:schemas-microsoft-com:office:powerpoint", "textdata"), new QName("urn:schemas-microsoft-com:office:office", "ink"), new QName("urn:schemas-microsoft-com:office:powerpoint", "iscomment"), new QName("urn:schemas-microsoft-com:office:office", "equationxml"), new QName("", "id"), new QName("", "style"), new QName("", "href"), new QName("", TypedValues.AttributesType.S_TARGET), new QName("", "class"), new QName("", "title"), new QName("", "alt"), new QName("", "coordsize"), new QName("", "coordorigin"), new QName("", "wrapcoords"), new QName("", "print"), new QName("urn:schemas-microsoft-com:office:office", "spid"), new QName("urn:schemas-microsoft-com:office:office", "oned"), new QName("urn:schemas-microsoft-com:office:office", "regroupid"), new QName("urn:schemas-microsoft-com:office:office", "doubleclicknotify"), new QName("urn:schemas-microsoft-com:office:office", "button"), new QName("urn:schemas-microsoft-com:office:office", "userhidden"), new QName("urn:schemas-microsoft-com:office:office", "bullet"), new QName("urn:schemas-microsoft-com:office:office", "hr"), new QName("urn:schemas-microsoft-com:office:office", "hrstd"), new QName("urn:schemas-microsoft-com:office:office", "hrnoshade"), new QName("urn:schemas-microsoft-com:office:office", "hrpct"), new QName("urn:schemas-microsoft-com:office:office", "hralign"), new QName("urn:schemas-microsoft-com:office:office", "allowincell"), new QName("urn:schemas-microsoft-com:office:office", "allowoverlap"), new QName("urn:schemas-microsoft-com:office:office", "userdrawn"), new QName("urn:schemas-microsoft-com:office:office", "bordertopcolor"), new QName("urn:schemas-microsoft-com:office:office", "borderleftcolor"), new QName("urn:schemas-microsoft-com:office:office", "borderbottomcolor"), new QName("urn:schemas-microsoft-com:office:office", "borderrightcolor"), new QName("urn:schemas-microsoft-com:office:office", "dgmlayout"), new QName("urn:schemas-microsoft-com:office:office", "dgmnodekind"), new QName("urn:schemas-microsoft-com:office:office", "dgmlayoutmru"), new QName("urn:schemas-microsoft-com:office:office", "insetmode"), new QName("", "chromakey"), new QName("", "filled"), new QName("", "fillcolor"), new QName("", "opacity"), new QName("", "stroked"), new QName("", "strokecolor"), new QName("", "strokeweight"), new QName("", "insetpen"), new QName("urn:schemas-microsoft-com:office:office", "spt"), new QName("urn:schemas-microsoft-com:office:office", "connectortype"), new QName("urn:schemas-microsoft-com:office:office", "bwmode"), new QName("urn:schemas-microsoft-com:office:office", "bwpure"), new QName("urn:schemas-microsoft-com:office:office", "bwnormal"), new QName("urn:schemas-microsoft-com:office:office", "forcedash"), new QName("urn:schemas-microsoft-com:office:office", "oleicon"), new QName("urn:schemas-microsoft-com:office:office", "ole"), new QName("urn:schemas-microsoft-com:office:office", "preferrelative"), new QName("urn:schemas-microsoft-com:office:office", "cliptowrap"), new QName("urn:schemas-microsoft-com:office:office", "clip"), new QName("", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY), new QName("", "adj"), new QName("", "path"), new QName("urn:schemas-microsoft-com:office:office", "gfxdata"), new QName("", "equationxml")};
    private static final long serialVersionUID = 1;

    public CTShapeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTPath> getPathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda119
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getPathArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda120
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setPathArray(((Integer) obj).intValue(), (CTPath) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda121
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewPath(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda122
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removePath(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda123
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfPathArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTPath[] getPathArray() {
        return (CTPath[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTPath[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfPathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setPathArray(CTPath[] pathArray) {
        check_orphaned();
        arraySetterHelper(pathArray, PROPERTY_QNAME[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setPathArray(int i, CTPath path) {
        generatedSetterHelperImpl(path, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTPath insertNewPath(int i) {
        CTPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTPath addNewPath() {
        CTPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removePath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTFormulas> getFormulasList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getFormulasArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setFormulasArray(((Integer) obj).intValue(), (CTFormulas) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewFormulas(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeFormulas(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfFormulasArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTFormulas[] getFormulasArray() {
        return (CTFormulas[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTFormulas[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfFormulasArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setFormulasArray(CTFormulas[] formulasArray) {
        check_orphaned();
        arraySetterHelper(formulasArray, PROPERTY_QNAME[1]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setFormulasArray(int i, CTFormulas formulas) {
        generatedSetterHelperImpl(formulas, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTFormulas insertNewFormulas(int i) {
        CTFormulas target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFormulas) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTFormulas addNewFormulas() {
        CTFormulas target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFormulas) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeFormulas(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTHandles> getHandlesList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getHandlesArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setHandlesArray(((Integer) obj).intValue(), (CTHandles) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewHandles(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeHandles(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfHandlesArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTHandles[] getHandlesArray() {
        return (CTHandles[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTHandles[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfHandlesArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setHandlesArray(CTHandles[] handlesArray) {
        check_orphaned();
        arraySetterHelper(handlesArray, PROPERTY_QNAME[2]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setHandlesArray(int i, CTHandles handles) {
        generatedSetterHelperImpl(handles, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTHandles insertNewHandles(int i) {
        CTHandles target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHandles) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTHandles addNewHandles() {
        CTHandles target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHandles) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeHandles(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTFill> getFillList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda86
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getFillArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda87
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setFillArray(((Integer) obj).intValue(), (CTFill) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda88
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewFill(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda89
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeFill(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda90
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfFillArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTFill[] getFillArray() {
        return (CTFill[]) getXmlObjectArray(PROPERTY_QNAME[3], new CTFill[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfFillArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setFillArray(CTFill[] fillArray) {
        check_orphaned();
        arraySetterHelper(fillArray, PROPERTY_QNAME[3]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setFillArray(int i, CTFill fill) {
        generatedSetterHelperImpl(fill, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTFill insertNewFill(int i) {
        CTFill target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFill) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTFill addNewFill() {
        CTFill target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFill) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeFill(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTStroke> getStrokeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda102
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getStrokeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda103
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setStrokeArray(((Integer) obj).intValue(), (CTStroke) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda104
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewStroke(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda105
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeStroke(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda106
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfStrokeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTStroke[] getStrokeArray() {
        return (CTStroke[]) getXmlObjectArray(PROPERTY_QNAME[4], new CTStroke[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfStrokeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setStrokeArray(CTStroke[] strokeArray) {
        check_orphaned();
        arraySetterHelper(strokeArray, PROPERTY_QNAME[4]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setStrokeArray(int i, CTStroke stroke) {
        generatedSetterHelperImpl(stroke, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTStroke insertNewStroke(int i) {
        CTStroke target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStroke) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTStroke addNewStroke() {
        CTStroke target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStroke) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeStroke(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTShadow> getShadowList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda113
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getShadowArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda114
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setShadowArray(((Integer) obj).intValue(), (CTShadow) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda115
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewShadow(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda116
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeShadow(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda117
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfShadowArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTShadow[] getShadowArray() {
        return (CTShadow[]) getXmlObjectArray(PROPERTY_QNAME[5], new CTShadow[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfShadowArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setShadowArray(CTShadow[] shadowArray) {
        check_orphaned();
        arraySetterHelper(shadowArray, PROPERTY_QNAME[5]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setShadowArray(int i, CTShadow shadow) {
        generatedSetterHelperImpl(shadow, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTShadow insertNewShadow(int i) {
        CTShadow target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShadow) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTShadow addNewShadow() {
        CTShadow target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShadow) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeShadow(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTTextbox> getTextboxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getTextboxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setTextboxArray(((Integer) obj).intValue(), (CTTextbox) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda60
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewTextbox(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeTextbox(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfTextboxArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTTextbox[] getTextboxArray() {
        return (CTTextbox[]) getXmlObjectArray(PROPERTY_QNAME[6], new CTTextbox[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfTextboxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setTextboxArray(CTTextbox[] textboxArray) {
        check_orphaned();
        arraySetterHelper(textboxArray, PROPERTY_QNAME[6]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setTextboxArray(int i, CTTextbox textbox) {
        generatedSetterHelperImpl(textbox, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTTextbox insertNewTextbox(int i) {
        CTTextbox target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextbox) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTTextbox addNewTextbox() {
        CTTextbox target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextbox) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeTextbox(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTTextPath> getTextpathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getTextpathArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setTextpathArray(((Integer) obj).intValue(), (CTTextPath) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewTextpath(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeTextpath(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfTextpathArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTTextPath[] getTextpathArray() {
        return (CTTextPath[]) getXmlObjectArray(PROPERTY_QNAME[7], new CTTextPath[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfTextpathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setTextpathArray(CTTextPath[] textpathArray) {
        check_orphaned();
        arraySetterHelper(textpathArray, PROPERTY_QNAME[7]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setTextpathArray(int i, CTTextPath textpath) {
        generatedSetterHelperImpl(textpath, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTTextPath insertNewTextpath(int i) {
        CTTextPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextPath) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTTextPath addNewTextpath() {
        CTTextPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextPath) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeTextpath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTImageData> getImagedataList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getImagedataArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setImagedataArray(((Integer) obj).intValue(), (CTImageData) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewImagedata(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeImagedata(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfImagedataArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTImageData[] getImagedataArray() {
        return (CTImageData[]) getXmlObjectArray(PROPERTY_QNAME[8], new CTImageData[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfImagedataArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setImagedataArray(CTImageData[] imagedataArray) {
        check_orphaned();
        arraySetterHelper(imagedataArray, PROPERTY_QNAME[8]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setImagedataArray(int i, CTImageData imagedata) {
        generatedSetterHelperImpl(imagedata, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTImageData insertNewImagedata(int i) {
        CTImageData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTImageData) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTImageData addNewImagedata() {
        CTImageData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTImageData) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeImagedata(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTSkew> getSkewList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getSkewArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setSkewArray(((Integer) obj).intValue(), (CTSkew) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewSkew(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeSkew(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda74
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfSkewArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTSkew[] getSkewArray() {
        return getXmlObjectArray(PROPERTY_QNAME[9], (XmlObject[]) new CTSkew[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfSkewArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setSkewArray(CTSkew[] skewArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) skewArray, PROPERTY_QNAME[9]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setSkewArray(int i, CTSkew skew) {
        generatedSetterHelperImpl(skew, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTSkew insertNewSkew(int i) {
        CTSkew target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTSkew addNewSkew() {
        CTSkew target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeSkew(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTExtrusion> getExtrusionList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getExtrusionArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setExtrusionArray(((Integer) obj).intValue(), (CTExtrusion) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewExtrusion(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeExtrusion(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfExtrusionArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTExtrusion[] getExtrusionArray() {
        return getXmlObjectArray(PROPERTY_QNAME[10], (XmlObject[]) new CTExtrusion[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfExtrusionArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setExtrusionArray(CTExtrusion[] extrusionArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) extrusionArray, PROPERTY_QNAME[10]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setExtrusionArray(int i, CTExtrusion extrusion) {
        generatedSetterHelperImpl(extrusion, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTExtrusion insertNewExtrusion(int i) {
        CTExtrusion target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTExtrusion addNewExtrusion() {
        CTExtrusion target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeExtrusion(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTCallout> getCalloutList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getCalloutArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setCalloutArray(((Integer) obj).intValue(), (CTCallout) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewCallout(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeCallout(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfCalloutArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTCallout[] getCalloutArray() {
        return getXmlObjectArray(PROPERTY_QNAME[11], (XmlObject[]) new CTCallout[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfCalloutArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setCalloutArray(CTCallout[] calloutArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) calloutArray, PROPERTY_QNAME[11]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setCalloutArray(int i, CTCallout callout) {
        generatedSetterHelperImpl(callout, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTCallout insertNewCallout(int i) {
        CTCallout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTCallout addNewCallout() {
        CTCallout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeCallout(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTLock> getLockList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getLockArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setLockArray(((Integer) obj).intValue(), (CTLock) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewLock(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeLock(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfLockArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTLock[] getLockArray() {
        return (CTLock[]) getXmlObjectArray(PROPERTY_QNAME[12], new CTLock[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfLockArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setLockArray(CTLock[] lockArray) {
        check_orphaned();
        arraySetterHelper(lockArray, PROPERTY_QNAME[12]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setLockArray(int i, CTLock lock) {
        generatedSetterHelperImpl(lock, PROPERTY_QNAME[12], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTLock insertNewLock(int i) {
        CTLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLock) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTLock addNewLock() {
        CTLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLock) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeLock(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTClipPath> getClippathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda75
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getClippathArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda76
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setClippathArray(((Integer) obj).intValue(), (CTClipPath) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda77
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewClippath(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda78
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeClippath(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda79
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfClippathArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTClipPath[] getClippathArray() {
        return getXmlObjectArray(PROPERTY_QNAME[13], (XmlObject[]) new CTClipPath[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfClippathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setClippathArray(CTClipPath[] clippathArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) clippathArray, PROPERTY_QNAME[13]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setClippathArray(int i, CTClipPath clippath) {
        generatedSetterHelperImpl(clippath, PROPERTY_QNAME[13], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTClipPath insertNewClippath(int i) {
        CTClipPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTClipPath addNewClippath() {
        CTClipPath target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeClippath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTSignatureLine> getSignaturelineList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda97
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getSignaturelineArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda98
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setSignaturelineArray(((Integer) obj).intValue(), (CTSignatureLine) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda99
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewSignatureline(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda100
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeSignatureline(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda101
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfSignaturelineArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTSignatureLine[] getSignaturelineArray() {
        return (CTSignatureLine[]) getXmlObjectArray(PROPERTY_QNAME[14], new CTSignatureLine[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfSignaturelineArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setSignaturelineArray(CTSignatureLine[] signaturelineArray) {
        check_orphaned();
        arraySetterHelper(signaturelineArray, PROPERTY_QNAME[14]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setSignaturelineArray(int i, CTSignatureLine signatureline) {
        generatedSetterHelperImpl(signatureline, PROPERTY_QNAME[14], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTSignatureLine insertNewSignatureline(int i) {
        CTSignatureLine target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSignatureLine) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTSignatureLine addNewSignatureline() {
        CTSignatureLine target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSignatureLine) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeSignatureline(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTWrap> getWrapList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getWrapArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setWrapArray(((Integer) obj).intValue(), (CTWrap) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewWrap(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeWrap(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfWrapArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTWrap[] getWrapArray() {
        return (CTWrap[]) getXmlObjectArray(PROPERTY_QNAME[15], new CTWrap[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfWrapArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setWrapArray(CTWrap[] wrapArray) {
        check_orphaned();
        arraySetterHelper(wrapArray, PROPERTY_QNAME[15]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setWrapArray(int i, CTWrap wrap) {
        generatedSetterHelperImpl(wrap, PROPERTY_QNAME[15], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTWrap insertNewWrap(int i) {
        CTWrap target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTWrap) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTWrap addNewWrap() {
        CTWrap target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTWrap) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeWrap(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTAnchorLock> getAnchorlockList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda91
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getAnchorlockArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda92
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setAnchorlockArray(((Integer) obj).intValue(), (CTAnchorLock) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda93
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewAnchorlock(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda94
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeAnchorlock(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda95
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfAnchorlockArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTAnchorLock[] getAnchorlockArray() {
        return (CTAnchorLock[]) getXmlObjectArray(PROPERTY_QNAME[16], new CTAnchorLock[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfAnchorlockArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setAnchorlockArray(CTAnchorLock[] anchorlockArray) {
        check_orphaned();
        arraySetterHelper(anchorlockArray, PROPERTY_QNAME[16]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setAnchorlockArray(int i, CTAnchorLock anchorlock) {
        generatedSetterHelperImpl(anchorlock, PROPERTY_QNAME[16], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTAnchorLock insertNewAnchorlock(int i) {
        CTAnchorLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAnchorLock) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTAnchorLock addNewAnchorlock() {
        CTAnchorLock target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAnchorLock) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeAnchorlock(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTBorder> getBordertopList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getBordertopArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setBordertopArray(((Integer) obj).intValue(), (CTBorder) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewBordertop(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeBordertop(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfBordertopArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTBorder[] getBordertopArray() {
        return getXmlObjectArray(PROPERTY_QNAME[17], (XmlObject[]) new CTBorder[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfBordertopArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setBordertopArray(CTBorder[] bordertopArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) bordertopArray, PROPERTY_QNAME[17]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setBordertopArray(int i, CTBorder bordertop) {
        generatedSetterHelperImpl(bordertop, PROPERTY_QNAME[17], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTBorder insertNewBordertop(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTBorder addNewBordertop() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeBordertop(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTBorder> getBorderbottomList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda80
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getBorderbottomArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda81
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setBorderbottomArray(((Integer) obj).intValue(), (CTBorder) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda82
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewBorderbottom(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda83
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeBorderbottom(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda84
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfBorderbottomArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTBorder[] getBorderbottomArray() {
        return getXmlObjectArray(PROPERTY_QNAME[18], (XmlObject[]) new CTBorder[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfBorderbottomArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setBorderbottomArray(CTBorder[] borderbottomArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) borderbottomArray, PROPERTY_QNAME[18]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setBorderbottomArray(int i, CTBorder borderbottom) {
        generatedSetterHelperImpl(borderbottom, PROPERTY_QNAME[18], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTBorder insertNewBorderbottom(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTBorder addNewBorderbottom() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeBorderbottom(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTBorder> getBorderleftList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getBorderleftArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda65
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setBorderleftArray(((Integer) obj).intValue(), (CTBorder) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda66
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewBorderleft(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda67
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeBorderleft(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda68
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfBorderleftArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTBorder[] getBorderleftArray() {
        return getXmlObjectArray(PROPERTY_QNAME[19], (XmlObject[]) new CTBorder[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfBorderleftArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setBorderleftArray(CTBorder[] borderleftArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) borderleftArray, PROPERTY_QNAME[19]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setBorderleftArray(int i, CTBorder borderleft) {
        generatedSetterHelperImpl(borderleft, PROPERTY_QNAME[19], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTBorder insertNewBorderleft(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTBorder addNewBorderleft() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeBorderleft(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTBorder> getBorderrightList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda108
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getBorderrightArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda109
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setBorderrightArray(((Integer) obj).intValue(), (CTBorder) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda110
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewBorderright(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda111
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeBorderright(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda112
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfBorderrightArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTBorder[] getBorderrightArray() {
        return getXmlObjectArray(PROPERTY_QNAME[20], (XmlObject[]) new CTBorder[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfBorderrightArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setBorderrightArray(CTBorder[] borderrightArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) borderrightArray, PROPERTY_QNAME[20]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setBorderrightArray(int i, CTBorder borderright) {
        generatedSetterHelperImpl(borderright, PROPERTY_QNAME[20], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTBorder insertNewBorderright(int i) {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTBorder addNewBorderright() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeBorderright(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTClientData> getClientDataList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda124
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getClientDataArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda125
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setClientDataArray(((Integer) obj).intValue(), (CTClientData) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda126
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewClientData(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda127
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeClientData(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda128
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfClientDataArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTClientData[] getClientDataArray() {
        return (CTClientData[]) getXmlObjectArray(PROPERTY_QNAME[21], new CTClientData[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfClientDataArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setClientDataArray(CTClientData[] clientDataArray) {
        check_orphaned();
        arraySetterHelper(clientDataArray, PROPERTY_QNAME[21]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setClientDataArray(int i, CTClientData clientData) {
        generatedSetterHelperImpl(clientData, PROPERTY_QNAME[21], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTClientData insertNewClientData(int i) {
        CTClientData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTClientData) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTClientData addNewClientData() {
        CTClientData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTClientData) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeClientData(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTRel> getTextdataList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda85
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getTextdataArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda96
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setTextdataArray(((Integer) obj).intValue(), (CTRel) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda107
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewTextdata(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda118
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeTextdata(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda129
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfTextdataArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTRel[] getTextdataArray() {
        return getXmlObjectArray(PROPERTY_QNAME[22], (XmlObject[]) new CTRel[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
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

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfTextdataArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setTextdataArray(CTRel[] textdataArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) textdataArray, PROPERTY_QNAME[22]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setTextdataArray(int i, CTRel textdata) {
        generatedSetterHelperImpl(textdata, PROPERTY_QNAME[22], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTRel insertNewTextdata(int i) {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTRel addNewTextdata() {
        CTRel target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeTextdata(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTInk> getInkList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda69
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getInkArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda70
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setInkArray(((Integer) obj).intValue(), (CTInk) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda71
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewInk(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda72
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeInk(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda73
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfInkArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTInk[] getInkArray() {
        return getXmlObjectArray(PROPERTY_QNAME[23], (XmlObject[]) new CTInk[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTInk getInkArray(int i) {
        CTInk target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[23], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfInkArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setInkArray(CTInk[] inkArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) inkArray, PROPERTY_QNAME[23]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setInkArray(int i, CTInk ink) {
        generatedSetterHelperImpl(ink, PROPERTY_QNAME[23], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTInk insertNewInk(int i) {
        CTInk target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTInk addNewInk() {
        CTInk target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeInk(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTEmpty> getIscommentList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getIscommentArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setIscommentArray(((Integer) obj).intValue(), (CTEmpty) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewIscomment(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeIscomment(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfIscommentArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTEmpty[] getIscommentArray() {
        return getXmlObjectArray(PROPERTY_QNAME[24], (XmlObject[]) new CTEmpty[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTEmpty getIscommentArray(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[24], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfIscommentArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setIscommentArray(CTEmpty[] iscommentArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) iscommentArray, PROPERTY_QNAME[24]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setIscommentArray(int i, CTEmpty iscomment) {
        generatedSetterHelperImpl(iscomment, PROPERTY_QNAME[24], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTEmpty insertNewIscomment(int i) {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTEmpty addNewIscomment() {
        CTEmpty target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeIscomment(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public List<CTEquationXml> getEquationxmlList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.getEquationxmlArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTShapeImpl.this.setEquationxmlArray(((Integer) obj).intValue(), (CTEquationXml) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTShapeImpl.this.insertNewEquationxml(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTShapeImpl.this.removeEquationxml(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: com.microsoft.schemas.vml.impl.CTShapeImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTShapeImpl.this.sizeOfEquationxmlArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTEquationXml[] getEquationxmlArray() {
        return getXmlObjectArray(PROPERTY_QNAME[25], (XmlObject[]) new CTEquationXml[0]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTEquationXml getEquationxmlArray(int i) {
        CTEquationXml target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[25], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public int sizeOfEquationxmlArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setEquationxmlArray(CTEquationXml[] equationxmlArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) equationxmlArray, PROPERTY_QNAME[25]);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setEquationxmlArray(int i, CTEquationXml equationxml) {
        generatedSetterHelperImpl(equationxml, PROPERTY_QNAME[25], i, (short) 2);
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTEquationXml insertNewEquationxml(int i) {
        CTEquationXml target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public CTEquationXml addNewEquationxml() {
        CTEquationXml target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void removeEquationxml(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetId() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[26]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.setStringValue(id);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetId(XmlString id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.set(id);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[26]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getStyle() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetStyle() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[27]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setStyle(String style) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[27]);
            }
            target.setStringValue(style);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetStyle(XmlString style) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[27]);
            }
            target.set(style);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[27]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getHref() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetHref() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetHref() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[28]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setHref(String href) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.setStringValue(href);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetHref(XmlString href) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.set(href);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetHref() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[28]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getTarget() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetTarget() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetTarget() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[29]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setTarget(String targetValue) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[29]);
            }
            target.setStringValue(targetValue);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetTarget(XmlString targetValue) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[29]);
            }
            target.set(targetValue);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetTarget() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[29]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getClass1() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetClass1() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[30]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetClass1() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[30]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setClass1(String class1) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[30]);
            }
            target.setStringValue(class1);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetClass1(XmlString class1) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[30]);
            }
            target.set(class1);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetClass1() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[30]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getTitle() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetTitle() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[31]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetTitle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[31]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setTitle(String title) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[31]);
            }
            target.setStringValue(title);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetTitle(XmlString title) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[31]);
            }
            target.set(title);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetTitle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[31]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getAlt() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[32]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetAlt() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[32]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetAlt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[32]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setAlt(String alt) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[32]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[32]);
            }
            target.setStringValue(alt);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetAlt(XmlString alt) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[32]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[32]);
            }
            target.set(alt);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetAlt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[32]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getCoordsize() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetCoordsize() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[33]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetCoordsize() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[33]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setCoordsize(String coordsize) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[33]);
            }
            target.setStringValue(coordsize);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetCoordsize(XmlString coordsize) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[33]);
            }
            target.set(coordsize);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetCoordsize() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[33]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getCoordorigin() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetCoordorigin() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[34]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetCoordorigin() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[34]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setCoordorigin(String coordorigin) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[34]);
            }
            target.setStringValue(coordorigin);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetCoordorigin(XmlString coordorigin) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[34]);
            }
            target.set(coordorigin);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetCoordorigin() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[34]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getWrapcoords() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetWrapcoords() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[35]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetWrapcoords() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[35]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setWrapcoords(String wrapcoords) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[35]);
            }
            target.setStringValue(wrapcoords);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetWrapcoords(XmlString wrapcoords) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[35]);
            }
            target.set(wrapcoords);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetWrapcoords() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[35]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getPrint() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetPrint() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[36]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetPrint() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[36]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setPrint(STTrueFalse.Enum print) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[36]);
            }
            target.setEnumValue(print);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetPrint(STTrueFalse print) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[36]);
            }
            target.set(print);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetPrint() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[36]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getSpid() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[37]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetSpid() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[37]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetSpid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[37]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setSpid(String spid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[37]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[37]);
            }
            target.setStringValue(spid);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetSpid(XmlString spid) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[37]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[37]);
            }
            target.set(spid);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetSpid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[37]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getOned() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[38]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetOned() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[38]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetOned() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[38]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setOned(STTrueFalse.Enum oned) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[38]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[38]);
            }
            target.setEnumValue(oned);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetOned(STTrueFalse oned) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[38]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[38]);
            }
            target.set(oned);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetOned() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[38]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public BigInteger getRegroupid() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[39]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlInteger xgetRegroupid() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_attribute_user(PROPERTY_QNAME[39]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetRegroupid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[39]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setRegroupid(BigInteger regroupid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[39]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[39]);
            }
            target.setBigIntegerValue(regroupid);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetRegroupid(XmlInteger regroupid) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_attribute_user(PROPERTY_QNAME[39]);
            if (target == null) {
                target = (XmlInteger) get_store().add_attribute_user(PROPERTY_QNAME[39]);
            }
            target.set(regroupid);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetRegroupid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[39]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getDoubleclicknotify() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[40]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetDoubleclicknotify() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[40]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetDoubleclicknotify() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[40]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setDoubleclicknotify(STTrueFalse.Enum doubleclicknotify) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[40]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[40]);
            }
            target.setEnumValue(doubleclicknotify);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetDoubleclicknotify(STTrueFalse doubleclicknotify) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[40]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[40]);
            }
            target.set(doubleclicknotify);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetDoubleclicknotify() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[40]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getButton() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[41]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetButton() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[41]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetButton() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[41]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setButton(STTrueFalse.Enum button) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[41]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[41]);
            }
            target.setEnumValue(button);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetButton(STTrueFalse button) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[41]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[41]);
            }
            target.set(button);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetButton() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[41]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getUserhidden() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[42]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetUserhidden() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[42]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetUserhidden() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[42]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setUserhidden(STTrueFalse.Enum userhidden) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[42]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[42]);
            }
            target.setEnumValue(userhidden);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetUserhidden(STTrueFalse userhidden) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[42]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[42]);
            }
            target.set(userhidden);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetUserhidden() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[42]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getBullet() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[43]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetBullet() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[43]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetBullet() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[43]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setBullet(STTrueFalse.Enum bullet) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[43]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[43]);
            }
            target.setEnumValue(bullet);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetBullet(STTrueFalse bullet) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[43]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[43]);
            }
            target.set(bullet);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetBullet() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[43]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getHr() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[44]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetHr() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[44]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetHr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[44]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setHr(STTrueFalse.Enum hr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[44]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[44]);
            }
            target.setEnumValue(hr);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetHr(STTrueFalse hr) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[44]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[44]);
            }
            target.set(hr);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetHr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[44]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getHrstd() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[45]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetHrstd() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[45]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetHrstd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[45]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setHrstd(STTrueFalse.Enum hrstd) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[45]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[45]);
            }
            target.setEnumValue(hrstd);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetHrstd(STTrueFalse hrstd) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[45]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[45]);
            }
            target.set(hrstd);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetHrstd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[45]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getHrnoshade() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[46]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetHrnoshade() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[46]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetHrnoshade() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[46]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setHrnoshade(STTrueFalse.Enum hrnoshade) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[46]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[46]);
            }
            target.setEnumValue(hrnoshade);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetHrnoshade(STTrueFalse hrnoshade) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[46]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[46]);
            }
            target.set(hrnoshade);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetHrnoshade() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[46]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public float getHrpct() {
        float floatValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[47]);
            floatValue = target == null ? 0.0f : target.getFloatValue();
        }
        return floatValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlFloat xgetHrpct() {
        XmlFloat target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlFloat) get_store().find_attribute_user(PROPERTY_QNAME[47]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetHrpct() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[47]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setHrpct(float hrpct) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[47]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[47]);
            }
            target.setFloatValue(hrpct);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetHrpct(XmlFloat hrpct) {
        synchronized (monitor()) {
            check_orphaned();
            XmlFloat target = (XmlFloat) get_store().find_attribute_user(PROPERTY_QNAME[47]);
            if (target == null) {
                target = (XmlFloat) get_store().add_attribute_user(PROPERTY_QNAME[47]);
            }
            target.set(hrpct);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetHrpct() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[47]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STHrAlign.Enum getHralign() {
        STHrAlign.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[48]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[48]);
            }
            r1 = target == null ? null : (STHrAlign.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STHrAlign xgetHralign() {
        STHrAlign target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STHrAlign) get_store().find_attribute_user(PROPERTY_QNAME[48]);
            if (target == null) {
                target = (STHrAlign) get_default_attribute_value(PROPERTY_QNAME[48]);
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetHralign() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[48]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setHralign(STHrAlign.Enum hralign) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[48]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[48]);
            }
            target.setEnumValue(hralign);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetHralign(STHrAlign hralign) {
        synchronized (monitor()) {
            check_orphaned();
            STHrAlign target = (STHrAlign) get_store().find_attribute_user(PROPERTY_QNAME[48]);
            if (target == null) {
                target = (STHrAlign) get_store().add_attribute_user(PROPERTY_QNAME[48]);
            }
            target.set(hralign);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetHralign() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[48]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getAllowincell() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[49]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetAllowincell() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[49]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetAllowincell() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[49]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setAllowincell(STTrueFalse.Enum allowincell) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[49]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[49]);
            }
            target.setEnumValue(allowincell);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetAllowincell(STTrueFalse allowincell) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[49]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[49]);
            }
            target.set(allowincell);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetAllowincell() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[49]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getAllowoverlap() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[50]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetAllowoverlap() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[50]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetAllowoverlap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[50]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setAllowoverlap(STTrueFalse.Enum allowoverlap) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[50]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[50]);
            }
            target.setEnumValue(allowoverlap);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetAllowoverlap(STTrueFalse allowoverlap) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[50]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[50]);
            }
            target.set(allowoverlap);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetAllowoverlap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[50]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getUserdrawn() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[51]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetUserdrawn() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[51]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetUserdrawn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[51]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setUserdrawn(STTrueFalse.Enum userdrawn) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[51]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[51]);
            }
            target.setEnumValue(userdrawn);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetUserdrawn(STTrueFalse userdrawn) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[51]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[51]);
            }
            target.set(userdrawn);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetUserdrawn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[51]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getBordertopcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[52]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetBordertopcolor() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[52]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetBordertopcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[52]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setBordertopcolor(String bordertopcolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[52]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[52]);
            }
            target.setStringValue(bordertopcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetBordertopcolor(XmlString bordertopcolor) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[52]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[52]);
            }
            target.set(bordertopcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetBordertopcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[52]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getBorderleftcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[53]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetBorderleftcolor() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[53]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetBorderleftcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[53]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setBorderleftcolor(String borderleftcolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[53]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[53]);
            }
            target.setStringValue(borderleftcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetBorderleftcolor(XmlString borderleftcolor) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[53]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[53]);
            }
            target.set(borderleftcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetBorderleftcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[53]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getBorderbottomcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[54]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetBorderbottomcolor() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[54]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetBorderbottomcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[54]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setBorderbottomcolor(String borderbottomcolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[54]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[54]);
            }
            target.setStringValue(borderbottomcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetBorderbottomcolor(XmlString borderbottomcolor) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[54]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[54]);
            }
            target.set(borderbottomcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetBorderbottomcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[54]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getBorderrightcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[55]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetBorderrightcolor() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[55]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetBorderrightcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[55]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setBorderrightcolor(String borderrightcolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[55]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[55]);
            }
            target.setStringValue(borderrightcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetBorderrightcolor(XmlString borderrightcolor) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[55]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[55]);
            }
            target.set(borderrightcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetBorderrightcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[55]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public BigInteger getDgmlayout() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[56]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STDiagramLayout xgetDgmlayout() {
        STDiagramLayout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[56]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetDgmlayout() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[56]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setDgmlayout(BigInteger dgmlayout) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[56]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[56]);
            }
            target.setBigIntegerValue(dgmlayout);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetDgmlayout(STDiagramLayout dgmlayout) {
        synchronized (monitor()) {
            check_orphaned();
            STDiagramLayout target = get_store().find_attribute_user(PROPERTY_QNAME[56]);
            if (target == null) {
                target = (STDiagramLayout) get_store().add_attribute_user(PROPERTY_QNAME[56]);
            }
            target.set(dgmlayout);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetDgmlayout() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[56]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public BigInteger getDgmnodekind() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[57]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlInteger xgetDgmnodekind() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_attribute_user(PROPERTY_QNAME[57]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetDgmnodekind() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[57]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setDgmnodekind(BigInteger dgmnodekind) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[57]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[57]);
            }
            target.setBigIntegerValue(dgmnodekind);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetDgmnodekind(XmlInteger dgmnodekind) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_attribute_user(PROPERTY_QNAME[57]);
            if (target == null) {
                target = (XmlInteger) get_store().add_attribute_user(PROPERTY_QNAME[57]);
            }
            target.set(dgmnodekind);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetDgmnodekind() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[57]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public BigInteger getDgmlayoutmru() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[58]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STDiagramLayout xgetDgmlayoutmru() {
        STDiagramLayout target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[58]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetDgmlayoutmru() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[58]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setDgmlayoutmru(BigInteger dgmlayoutmru) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[58]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[58]);
            }
            target.setBigIntegerValue(dgmlayoutmru);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetDgmlayoutmru(STDiagramLayout dgmlayoutmru) {
        synchronized (monitor()) {
            check_orphaned();
            STDiagramLayout target = get_store().find_attribute_user(PROPERTY_QNAME[58]);
            if (target == null) {
                target = (STDiagramLayout) get_store().add_attribute_user(PROPERTY_QNAME[58]);
            }
            target.set(dgmlayoutmru);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetDgmlayoutmru() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[58]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STInsetMode.Enum getInsetmode() {
        STInsetMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[59]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[59]);
            }
            r1 = target == null ? null : (STInsetMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STInsetMode xgetInsetmode() {
        STInsetMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STInsetMode) get_store().find_attribute_user(PROPERTY_QNAME[59]);
            if (target == null) {
                target = (STInsetMode) get_default_attribute_value(PROPERTY_QNAME[59]);
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetInsetmode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[59]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setInsetmode(STInsetMode.Enum insetmode) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[59]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[59]);
            }
            target.setEnumValue(insetmode);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetInsetmode(STInsetMode insetmode) {
        synchronized (monitor()) {
            check_orphaned();
            STInsetMode target = (STInsetMode) get_store().find_attribute_user(PROPERTY_QNAME[59]);
            if (target == null) {
                target = (STInsetMode) get_store().add_attribute_user(PROPERTY_QNAME[59]);
            }
            target.set(insetmode);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetInsetmode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[59]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getChromakey() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[60]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STColorType xgetChromakey() {
        STColorType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[60]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetChromakey() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[60]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setChromakey(String chromakey) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[60]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[60]);
            }
            target.setStringValue(chromakey);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetChromakey(STColorType chromakey) {
        synchronized (monitor()) {
            check_orphaned();
            STColorType target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[60]);
            if (target == null) {
                target = (STColorType) get_store().add_attribute_user(PROPERTY_QNAME[60]);
            }
            target.set(chromakey);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetChromakey() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[60]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getFilled() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[61]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetFilled() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[61]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetFilled() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[61]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setFilled(STTrueFalse.Enum filled) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[61]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[61]);
            }
            target.setEnumValue(filled);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetFilled(STTrueFalse filled) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[61]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[61]);
            }
            target.set(filled);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetFilled() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[61]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getFillcolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[62]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STColorType xgetFillcolor() {
        STColorType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[62]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetFillcolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[62]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setFillcolor(String fillcolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[62]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[62]);
            }
            target.setStringValue(fillcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetFillcolor(STColorType fillcolor) {
        synchronized (monitor()) {
            check_orphaned();
            STColorType target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[62]);
            if (target == null) {
                target = (STColorType) get_store().add_attribute_user(PROPERTY_QNAME[62]);
            }
            target.set(fillcolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetFillcolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[62]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getOpacity() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[63]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetOpacity() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[63]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetOpacity() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[63]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setOpacity(String opacity) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[63]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[63]);
            }
            target.setStringValue(opacity);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetOpacity(XmlString opacity) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[63]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[63]);
            }
            target.set(opacity);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetOpacity() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[63]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getStroked() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[64]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetStroked() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[64]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetStroked() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[64]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setStroked(STTrueFalse.Enum stroked) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[64]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[64]);
            }
            target.setEnumValue(stroked);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetStroked(STTrueFalse stroked) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[64]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[64]);
            }
            target.set(stroked);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetStroked() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[64]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getStrokecolor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[65]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STColorType xgetStrokecolor() {
        STColorType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[65]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetStrokecolor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[65]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setStrokecolor(String strokecolor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[65]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[65]);
            }
            target.setStringValue(strokecolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetStrokecolor(STColorType strokecolor) {
        synchronized (monitor()) {
            check_orphaned();
            STColorType target = (STColorType) get_store().find_attribute_user(PROPERTY_QNAME[65]);
            if (target == null) {
                target = (STColorType) get_store().add_attribute_user(PROPERTY_QNAME[65]);
            }
            target.set(strokecolor);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetStrokecolor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[65]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getStrokeweight() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[66]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetStrokeweight() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[66]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetStrokeweight() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[66]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setStrokeweight(String strokeweight) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[66]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[66]);
            }
            target.setStringValue(strokeweight);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetStrokeweight(XmlString strokeweight) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[66]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[66]);
            }
            target.set(strokeweight);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetStrokeweight() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[66]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getInsetpen() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[67]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetInsetpen() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[67]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetInsetpen() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[67]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setInsetpen(STTrueFalse.Enum insetpen) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[67]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[67]);
            }
            target.setEnumValue(insetpen);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetInsetpen(STTrueFalse insetpen) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[67]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[67]);
            }
            target.set(insetpen);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetInsetpen() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[67]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public float getSpt() {
        float floatValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[68]);
            floatValue = target == null ? 0.0f : target.getFloatValue();
        }
        return floatValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlFloat xgetSpt() {
        XmlFloat target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlFloat) get_store().find_attribute_user(PROPERTY_QNAME[68]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetSpt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[68]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setSpt(float spt) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[68]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[68]);
            }
            target.setFloatValue(spt);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetSpt(XmlFloat spt) {
        synchronized (monitor()) {
            check_orphaned();
            XmlFloat target = (XmlFloat) get_store().find_attribute_user(PROPERTY_QNAME[68]);
            if (target == null) {
                target = (XmlFloat) get_store().add_attribute_user(PROPERTY_QNAME[68]);
            }
            target.set(spt);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetSpt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[68]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STConnectorType.Enum getConnectortype() {
        STConnectorType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[69]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[69]);
            }
            r1 = target == null ? null : (STConnectorType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STConnectorType xgetConnectortype() {
        STConnectorType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STConnectorType) get_store().find_attribute_user(PROPERTY_QNAME[69]);
            if (target == null) {
                target = (STConnectorType) get_default_attribute_value(PROPERTY_QNAME[69]);
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetConnectortype() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[69]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setConnectortype(STConnectorType.Enum connectortype) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[69]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[69]);
            }
            target.setEnumValue(connectortype);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetConnectortype(STConnectorType connectortype) {
        synchronized (monitor()) {
            check_orphaned();
            STConnectorType target = (STConnectorType) get_store().find_attribute_user(PROPERTY_QNAME[69]);
            if (target == null) {
                target = (STConnectorType) get_store().add_attribute_user(PROPERTY_QNAME[69]);
            }
            target.set(connectortype);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetConnectortype() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[69]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STBWMode.Enum getBwmode() {
        STBWMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[70]);
            r1 = target == null ? null : (STBWMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STBWMode xgetBwmode() {
        STBWMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[70]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetBwmode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[70]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setBwmode(STBWMode.Enum bwmode) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[70]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[70]);
            }
            target.setEnumValue(bwmode);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetBwmode(STBWMode bwmode) {
        synchronized (monitor()) {
            check_orphaned();
            STBWMode target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[70]);
            if (target == null) {
                target = (STBWMode) get_store().add_attribute_user(PROPERTY_QNAME[70]);
            }
            target.set(bwmode);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetBwmode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[70]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STBWMode.Enum getBwpure() {
        STBWMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[71]);
            r1 = target == null ? null : (STBWMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STBWMode xgetBwpure() {
        STBWMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[71]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetBwpure() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[71]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setBwpure(STBWMode.Enum bwpure) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[71]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[71]);
            }
            target.setEnumValue(bwpure);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetBwpure(STBWMode bwpure) {
        synchronized (monitor()) {
            check_orphaned();
            STBWMode target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[71]);
            if (target == null) {
                target = (STBWMode) get_store().add_attribute_user(PROPERTY_QNAME[71]);
            }
            target.set(bwpure);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetBwpure() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[71]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STBWMode.Enum getBwnormal() {
        STBWMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[72]);
            r1 = target == null ? null : (STBWMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STBWMode xgetBwnormal() {
        STBWMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[72]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetBwnormal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[72]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setBwnormal(STBWMode.Enum bwnormal) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[72]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[72]);
            }
            target.setEnumValue(bwnormal);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetBwnormal(STBWMode bwnormal) {
        synchronized (monitor()) {
            check_orphaned();
            STBWMode target = (STBWMode) get_store().find_attribute_user(PROPERTY_QNAME[72]);
            if (target == null) {
                target = (STBWMode) get_store().add_attribute_user(PROPERTY_QNAME[72]);
            }
            target.set(bwnormal);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetBwnormal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[72]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getForcedash() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[73]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetForcedash() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[73]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetForcedash() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[73]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setForcedash(STTrueFalse.Enum forcedash) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[73]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[73]);
            }
            target.setEnumValue(forcedash);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetForcedash(STTrueFalse forcedash) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[73]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[73]);
            }
            target.set(forcedash);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetForcedash() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[73]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getOleicon() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[74]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetOleicon() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[74]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetOleicon() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[74]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setOleicon(STTrueFalse.Enum oleicon) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[74]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[74]);
            }
            target.setEnumValue(oleicon);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetOleicon(STTrueFalse oleicon) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[74]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[74]);
            }
            target.set(oleicon);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetOleicon() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[74]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalseBlank.Enum getOle() {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[75]);
            r1 = target == null ? null : (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalseBlank xgetOle() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_attribute_user(PROPERTY_QNAME[75]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetOle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[75]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setOle(STTrueFalseBlank.Enum ole) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[75]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[75]);
            }
            target.setEnumValue(ole);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetOle(STTrueFalseBlank ole) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_attribute_user(PROPERTY_QNAME[75]);
            if (target == null) {
                target = (STTrueFalseBlank) get_store().add_attribute_user(PROPERTY_QNAME[75]);
            }
            target.set(ole);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetOle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[75]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getPreferrelative() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[76]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetPreferrelative() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[76]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetPreferrelative() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[76]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setPreferrelative(STTrueFalse.Enum preferrelative) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[76]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[76]);
            }
            target.setEnumValue(preferrelative);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetPreferrelative(STTrueFalse preferrelative) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[76]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[76]);
            }
            target.set(preferrelative);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetPreferrelative() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[76]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getCliptowrap() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[77]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetCliptowrap() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[77]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetCliptowrap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[77]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setCliptowrap(STTrueFalse.Enum cliptowrap) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[77]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[77]);
            }
            target.setEnumValue(cliptowrap);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetCliptowrap(STTrueFalse cliptowrap) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[77]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[77]);
            }
            target.set(cliptowrap);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetCliptowrap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[77]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse.Enum getClip() {
        STTrueFalse.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[78]);
            r1 = target == null ? null : (STTrueFalse.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public STTrueFalse xgetClip() {
        STTrueFalse target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[78]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetClip() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[78]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setClip(STTrueFalse.Enum clip) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[78]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[78]);
            }
            target.setEnumValue(clip);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetClip(STTrueFalse clip) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalse target = (STTrueFalse) get_store().find_attribute_user(PROPERTY_QNAME[78]);
            if (target == null) {
                target = (STTrueFalse) get_store().add_attribute_user(PROPERTY_QNAME[78]);
            }
            target.set(clip);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetClip() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[78]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getType() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[79]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetType() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[79]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[79]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setType(String type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[79]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[79]);
            }
            target.setStringValue(type);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetType(XmlString type) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[79]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[79]);
            }
            target.set(type);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[79]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getAdj() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[80]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetAdj() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[80]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetAdj() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[80]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setAdj(String adj) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[80]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[80]);
            }
            target.setStringValue(adj);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetAdj(XmlString adj) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[80]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[80]);
            }
            target.set(adj);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetAdj() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[80]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getPath2() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[81]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetPath2() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[81]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetPath2() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[81]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setPath2(String path2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[81]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[81]);
            }
            target.setStringValue(path2);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetPath2(XmlString path2) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[81]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[81]);
            }
            target.set(path2);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetPath2() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[81]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public byte[] getGfxdata() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[82]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlBase64Binary xgetGfxdata() {
        XmlBase64Binary target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBase64Binary) get_store().find_attribute_user(PROPERTY_QNAME[82]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetGfxdata() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[82]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setGfxdata(byte[] gfxdata) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[82]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[82]);
            }
            target.setByteArrayValue(gfxdata);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetGfxdata(XmlBase64Binary gfxdata) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBase64Binary target = (XmlBase64Binary) get_store().find_attribute_user(PROPERTY_QNAME[82]);
            if (target == null) {
                target = (XmlBase64Binary) get_store().add_attribute_user(PROPERTY_QNAME[82]);
            }
            target.set(gfxdata);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetGfxdata() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[82]);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public String getEquationxml2() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[83]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public XmlString xgetEquationxml2() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[83]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public boolean isSetEquationxml2() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[83]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void setEquationxml2(String equationxml2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[83]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[83]);
            }
            target.setStringValue(equationxml2);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void xsetEquationxml2(XmlString equationxml2) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[83]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[83]);
            }
            target.set(equationxml2);
        }
    }

    @Override // com.microsoft.schemas.vml.CTShape
    public void unsetEquationxml2() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[83]);
        }
    }
}
