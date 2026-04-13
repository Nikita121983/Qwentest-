package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateBehavior;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateEffectBehavior;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateRotationBehavior;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateScaleBehavior;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommandBehavior;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLMediaNodeAudio;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLMediaNodeVideo;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLSetBehavior;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeExclusive;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeParallel;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList;

/* loaded from: classes11.dex */
public class CTTimeNodeListImpl extends XmlComplexContentImpl implements CTTimeNodeList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "par"), new QName(XSSFRelation.NS_PRESENTATIONML, "seq"), new QName(XSSFRelation.NS_PRESENTATIONML, "excl"), new QName(XSSFRelation.NS_PRESENTATIONML, "anim"), new QName(XSSFRelation.NS_PRESENTATIONML, "animClr"), new QName(XSSFRelation.NS_PRESENTATIONML, "animEffect"), new QName(XSSFRelation.NS_PRESENTATIONML, "animMotion"), new QName(XSSFRelation.NS_PRESENTATIONML, "animRot"), new QName(XSSFRelation.NS_PRESENTATIONML, "animScale"), new QName(XSSFRelation.NS_PRESENTATIONML, "cmd"), new QName(XSSFRelation.NS_PRESENTATIONML, "set"), new QName(XSSFRelation.NS_PRESENTATIONML, "audio"), new QName(XSSFRelation.NS_PRESENTATIONML, "video")};
    private static final long serialVersionUID = 1;

    public CTTimeNodeListImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public List<CTTLTimeNodeParallel> getParList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.getParArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTimeNodeListImpl.this.setParArray(((Integer) obj).intValue(), (CTTLTimeNodeParallel) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.insertNewPar(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTimeNodeListImpl.this.removePar(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTimeNodeListImpl.this.sizeOfParArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLTimeNodeParallel[] getParArray() {
        return (CTTLTimeNodeParallel[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTTLTimeNodeParallel[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLTimeNodeParallel getParArray(int i) {
        CTTLTimeNodeParallel target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTLTimeNodeParallel) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public int sizeOfParArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setParArray(CTTLTimeNodeParallel[] parArray) {
        check_orphaned();
        arraySetterHelper(parArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setParArray(int i, CTTLTimeNodeParallel par) {
        generatedSetterHelperImpl(par, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLTimeNodeParallel insertNewPar(int i) {
        CTTLTimeNodeParallel target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTLTimeNodeParallel) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLTimeNodeParallel addNewPar() {
        CTTLTimeNodeParallel target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTLTimeNodeParallel) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void removePar(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public List<CTTLTimeNodeSequence> getSeqList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.getSeqArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTimeNodeListImpl.this.setSeqArray(((Integer) obj).intValue(), (CTTLTimeNodeSequence) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.insertNewSeq(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTimeNodeListImpl.this.removeSeq(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTimeNodeListImpl.this.sizeOfSeqArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLTimeNodeSequence[] getSeqArray() {
        return getXmlObjectArray(PROPERTY_QNAME[1], (XmlObject[]) new CTTLTimeNodeSequence[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLTimeNodeSequence getSeqArray(int i) {
        CTTLTimeNodeSequence target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public int sizeOfSeqArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setSeqArray(CTTLTimeNodeSequence[] seqArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) seqArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setSeqArray(int i, CTTLTimeNodeSequence seq) {
        generatedSetterHelperImpl(seq, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLTimeNodeSequence insertNewSeq(int i) {
        CTTLTimeNodeSequence target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLTimeNodeSequence addNewSeq() {
        CTTLTimeNodeSequence target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void removeSeq(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public List<CTTLTimeNodeExclusive> getExclList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.getExclArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTimeNodeListImpl.this.setExclArray(((Integer) obj).intValue(), (CTTLTimeNodeExclusive) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.insertNewExcl(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTimeNodeListImpl.this.removeExcl(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTimeNodeListImpl.this.sizeOfExclArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLTimeNodeExclusive[] getExclArray() {
        return getXmlObjectArray(PROPERTY_QNAME[2], (XmlObject[]) new CTTLTimeNodeExclusive[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLTimeNodeExclusive getExclArray(int i) {
        CTTLTimeNodeExclusive target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public int sizeOfExclArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setExclArray(CTTLTimeNodeExclusive[] exclArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) exclArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setExclArray(int i, CTTLTimeNodeExclusive excl) {
        generatedSetterHelperImpl(excl, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLTimeNodeExclusive insertNewExcl(int i) {
        CTTLTimeNodeExclusive target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLTimeNodeExclusive addNewExcl() {
        CTTLTimeNodeExclusive target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void removeExcl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public List<CTTLAnimateBehavior> getAnimList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.getAnimArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTimeNodeListImpl.this.setAnimArray(((Integer) obj).intValue(), (CTTLAnimateBehavior) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.insertNewAnim(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTimeNodeListImpl.this.removeAnim(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTimeNodeListImpl.this.sizeOfAnimArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateBehavior[] getAnimArray() {
        return getXmlObjectArray(PROPERTY_QNAME[3], (XmlObject[]) new CTTLAnimateBehavior[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateBehavior getAnimArray(int i) {
        CTTLAnimateBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public int sizeOfAnimArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setAnimArray(CTTLAnimateBehavior[] animArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) animArray, PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setAnimArray(int i, CTTLAnimateBehavior anim) {
        generatedSetterHelperImpl(anim, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateBehavior insertNewAnim(int i) {
        CTTLAnimateBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateBehavior addNewAnim() {
        CTTLAnimateBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void removeAnim(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public List<CTTLAnimateColorBehavior> getAnimClrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.getAnimClrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTimeNodeListImpl.this.setAnimClrArray(((Integer) obj).intValue(), (CTTLAnimateColorBehavior) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.insertNewAnimClr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTimeNodeListImpl.this.removeAnimClr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTimeNodeListImpl.this.sizeOfAnimClrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateColorBehavior[] getAnimClrArray() {
        return getXmlObjectArray(PROPERTY_QNAME[4], (XmlObject[]) new CTTLAnimateColorBehavior[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateColorBehavior getAnimClrArray(int i) {
        CTTLAnimateColorBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public int sizeOfAnimClrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setAnimClrArray(CTTLAnimateColorBehavior[] animClrArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) animClrArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setAnimClrArray(int i, CTTLAnimateColorBehavior animClr) {
        generatedSetterHelperImpl(animClr, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateColorBehavior insertNewAnimClr(int i) {
        CTTLAnimateColorBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateColorBehavior addNewAnimClr() {
        CTTLAnimateColorBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void removeAnimClr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public List<CTTLAnimateEffectBehavior> getAnimEffectList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.getAnimEffectArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTimeNodeListImpl.this.setAnimEffectArray(((Integer) obj).intValue(), (CTTLAnimateEffectBehavior) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.insertNewAnimEffect(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTimeNodeListImpl.this.removeAnimEffect(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTimeNodeListImpl.this.sizeOfAnimEffectArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateEffectBehavior[] getAnimEffectArray() {
        return getXmlObjectArray(PROPERTY_QNAME[5], (XmlObject[]) new CTTLAnimateEffectBehavior[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateEffectBehavior getAnimEffectArray(int i) {
        CTTLAnimateEffectBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public int sizeOfAnimEffectArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setAnimEffectArray(CTTLAnimateEffectBehavior[] animEffectArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) animEffectArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setAnimEffectArray(int i, CTTLAnimateEffectBehavior animEffect) {
        generatedSetterHelperImpl(animEffect, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateEffectBehavior insertNewAnimEffect(int i) {
        CTTLAnimateEffectBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateEffectBehavior addNewAnimEffect() {
        CTTLAnimateEffectBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void removeAnimEffect(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public List<CTTLAnimateMotionBehavior> getAnimMotionList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.getAnimMotionArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTimeNodeListImpl.this.setAnimMotionArray(((Integer) obj).intValue(), (CTTLAnimateMotionBehavior) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.insertNewAnimMotion(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTimeNodeListImpl.this.removeAnimMotion(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTimeNodeListImpl.this.sizeOfAnimMotionArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateMotionBehavior[] getAnimMotionArray() {
        return getXmlObjectArray(PROPERTY_QNAME[6], (XmlObject[]) new CTTLAnimateMotionBehavior[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateMotionBehavior getAnimMotionArray(int i) {
        CTTLAnimateMotionBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public int sizeOfAnimMotionArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setAnimMotionArray(CTTLAnimateMotionBehavior[] animMotionArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) animMotionArray, PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setAnimMotionArray(int i, CTTLAnimateMotionBehavior animMotion) {
        generatedSetterHelperImpl(animMotion, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateMotionBehavior insertNewAnimMotion(int i) {
        CTTLAnimateMotionBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateMotionBehavior addNewAnimMotion() {
        CTTLAnimateMotionBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void removeAnimMotion(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public List<CTTLAnimateRotationBehavior> getAnimRotList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.getAnimRotArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTimeNodeListImpl.this.setAnimRotArray(((Integer) obj).intValue(), (CTTLAnimateRotationBehavior) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.insertNewAnimRot(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTimeNodeListImpl.this.removeAnimRot(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTimeNodeListImpl.this.sizeOfAnimRotArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateRotationBehavior[] getAnimRotArray() {
        return getXmlObjectArray(PROPERTY_QNAME[7], (XmlObject[]) new CTTLAnimateRotationBehavior[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateRotationBehavior getAnimRotArray(int i) {
        CTTLAnimateRotationBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public int sizeOfAnimRotArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setAnimRotArray(CTTLAnimateRotationBehavior[] animRotArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) animRotArray, PROPERTY_QNAME[7]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setAnimRotArray(int i, CTTLAnimateRotationBehavior animRot) {
        generatedSetterHelperImpl(animRot, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateRotationBehavior insertNewAnimRot(int i) {
        CTTLAnimateRotationBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateRotationBehavior addNewAnimRot() {
        CTTLAnimateRotationBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void removeAnimRot(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public List<CTTLAnimateScaleBehavior> getAnimScaleList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.getAnimScaleArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTimeNodeListImpl.this.setAnimScaleArray(((Integer) obj).intValue(), (CTTLAnimateScaleBehavior) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.insertNewAnimScale(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTimeNodeListImpl.this.removeAnimScale(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTimeNodeListImpl.this.sizeOfAnimScaleArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateScaleBehavior[] getAnimScaleArray() {
        return getXmlObjectArray(PROPERTY_QNAME[8], (XmlObject[]) new CTTLAnimateScaleBehavior[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateScaleBehavior getAnimScaleArray(int i) {
        CTTLAnimateScaleBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public int sizeOfAnimScaleArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setAnimScaleArray(CTTLAnimateScaleBehavior[] animScaleArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) animScaleArray, PROPERTY_QNAME[8]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setAnimScaleArray(int i, CTTLAnimateScaleBehavior animScale) {
        generatedSetterHelperImpl(animScale, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateScaleBehavior insertNewAnimScale(int i) {
        CTTLAnimateScaleBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLAnimateScaleBehavior addNewAnimScale() {
        CTTLAnimateScaleBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void removeAnimScale(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public List<CTTLCommandBehavior> getCmdList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.getCmdArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTimeNodeListImpl.this.setCmdArray(((Integer) obj).intValue(), (CTTLCommandBehavior) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.insertNewCmd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTimeNodeListImpl.this.removeCmd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda60
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTimeNodeListImpl.this.sizeOfCmdArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLCommandBehavior[] getCmdArray() {
        return getXmlObjectArray(PROPERTY_QNAME[9], (XmlObject[]) new CTTLCommandBehavior[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLCommandBehavior getCmdArray(int i) {
        CTTLCommandBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public int sizeOfCmdArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setCmdArray(CTTLCommandBehavior[] cmdArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cmdArray, PROPERTY_QNAME[9]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setCmdArray(int i, CTTLCommandBehavior cmd) {
        generatedSetterHelperImpl(cmd, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLCommandBehavior insertNewCmd(int i) {
        CTTLCommandBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLCommandBehavior addNewCmd() {
        CTTLCommandBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void removeCmd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public List<CTTLSetBehavior> getSetList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.getSetArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTimeNodeListImpl.this.setSetArray(((Integer) obj).intValue(), (CTTLSetBehavior) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.insertNewSet(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTimeNodeListImpl.this.removeSet(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTimeNodeListImpl.this.sizeOfSetArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLSetBehavior[] getSetArray() {
        return getXmlObjectArray(PROPERTY_QNAME[10], (XmlObject[]) new CTTLSetBehavior[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLSetBehavior getSetArray(int i) {
        CTTLSetBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public int sizeOfSetArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setSetArray(CTTLSetBehavior[] setArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) setArray, PROPERTY_QNAME[10]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setSetArray(int i, CTTLSetBehavior set) {
        generatedSetterHelperImpl(set, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLSetBehavior insertNewSet(int i) {
        CTTLSetBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLSetBehavior addNewSet() {
        CTTLSetBehavior target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void removeSet(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public List<CTTLMediaNodeAudio> getAudioList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.getAudioArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTimeNodeListImpl.this.setAudioArray(((Integer) obj).intValue(), (CTTLMediaNodeAudio) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.insertNewAudio(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTimeNodeListImpl.this.removeAudio(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTimeNodeListImpl.this.sizeOfAudioArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLMediaNodeAudio[] getAudioArray() {
        return getXmlObjectArray(PROPERTY_QNAME[11], (XmlObject[]) new CTTLMediaNodeAudio[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLMediaNodeAudio getAudioArray(int i) {
        CTTLMediaNodeAudio target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public int sizeOfAudioArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setAudioArray(CTTLMediaNodeAudio[] audioArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) audioArray, PROPERTY_QNAME[11]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setAudioArray(int i, CTTLMediaNodeAudio audio) {
        generatedSetterHelperImpl(audio, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLMediaNodeAudio insertNewAudio(int i) {
        CTTLMediaNodeAudio target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLMediaNodeAudio addNewAudio() {
        CTTLMediaNodeAudio target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void removeAudio(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public List<CTTLMediaNodeVideo> getVideoList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.getVideoArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTimeNodeListImpl.this.setVideoArray(((Integer) obj).intValue(), (CTTLMediaNodeVideo) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTimeNodeListImpl.this.insertNewVideo(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTimeNodeListImpl.this.removeVideo(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTimeNodeListImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTimeNodeListImpl.this.sizeOfVideoArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLMediaNodeVideo[] getVideoArray() {
        return (CTTLMediaNodeVideo[]) getXmlObjectArray(PROPERTY_QNAME[12], new CTTLMediaNodeVideo[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLMediaNodeVideo getVideoArray(int i) {
        CTTLMediaNodeVideo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTLMediaNodeVideo) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public int sizeOfVideoArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setVideoArray(CTTLMediaNodeVideo[] videoArray) {
        check_orphaned();
        arraySetterHelper(videoArray, PROPERTY_QNAME[12]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void setVideoArray(int i, CTTLMediaNodeVideo video) {
        generatedSetterHelperImpl(video, PROPERTY_QNAME[12], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLMediaNodeVideo insertNewVideo(int i) {
        CTTLMediaNodeVideo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTLMediaNodeVideo) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public CTTLMediaNodeVideo addNewVideo() {
        CTTLMediaNodeVideo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTLMediaNodeVideo) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList
    public void removeVideo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }
}
