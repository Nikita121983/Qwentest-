package org.apache.xmlbeans.impl.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl;

/* loaded from: classes11.dex */
public class XmlComplexContentImpl extends XmlObjectBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final SchemaTypeImpl _schemaType;

    public XmlComplexContentImpl(SchemaType type) {
        this._schemaType = (SchemaTypeImpl) type;
        initComplexType(true, true);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.XmlObject
    public SchemaType schemaType() {
        return this._schemaType;
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    public String compute_text(NamespaceManager nsm) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    public final void set_String(String v) {
        if (this._schemaType.getContentType() == 2) {
            throw new AssertionError();
        }
        if (this._schemaType.getContentType() != 4 && !this._schemaType.isNoType()) {
            throw new IllegalArgumentException("Type does not allow for textual content: " + this._schemaType);
        }
        super.set_String(v);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    public void set_text(String str) {
        if (this._schemaType.getContentType() != 4 && !this._schemaType.isNoType()) {
            throw new AssertionError();
        }
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void update_from_complex_content() {
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    public void set_nil() {
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    public boolean equal_to(XmlObject complexObject) {
        if (!this._schemaType.equals(complexObject.schemaType())) {
            return false;
        }
        return true;
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected int value_hash_code() {
        throw new IllegalStateException("Complex types cannot be used as hash keys");
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.impl.values.TypeStoreUser
    public TypeStoreVisitor new_visitor() {
        return new SchemaTypeVisitorImpl(this._schemaType.getContentModel());
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.impl.values.TypeStoreUser
    public boolean is_child_element_order_sensitive() {
        return schemaType().isOrderSensitive();
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.impl.values.TypeStoreUser
    public int get_elementflags(QName eltName) {
        SchemaProperty prop = schemaType().getElementProperty(eltName);
        if (prop == null) {
            return 0;
        }
        if (prop.hasDefault() == 1 || prop.hasFixed() == 1 || prop.hasNillable() == 1) {
            return -1;
        }
        return (prop.hasNillable() != 0 ? 1 : 0) | (prop.hasDefault() == 0 ? 0 : 2) | (prop.hasFixed() == 0 ? 0 : 4);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.impl.values.TypeStoreUser
    public String get_default_attribute_text(QName attrName) {
        return super.get_default_attribute_text(attrName);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.impl.values.TypeStoreUser
    public String get_default_element_text(QName eltName) {
        SchemaProperty prop = schemaType().getElementProperty(eltName);
        if (prop == null) {
            return "";
        }
        return prop.getDefaultText();
    }

    protected void unionArraySetterHelper(Object[] sources, QName elemName) {
        commonSetterHelper2(elemName, null, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda0());
    }

    protected SimpleValue[] arraySetterHelper(int sourcesLength, QName elemName) {
        final SimpleValue[] sources = new SimpleValue[sourcesLength];
        commonSetterHelper(elemName, (QNameSet) null, sources, new BiConsumer() { // from class: org.apache.xmlbeans.impl.values.XmlComplexContentImpl$$ExternalSyntheticLambda19
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                XmlComplexContentImpl.lambda$arraySetterHelper$0(sources, (XmlObjectBase) obj, (Integer) obj2);
            }
        });
        return sources;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$arraySetterHelper$0(SimpleValue[] sources, XmlObjectBase u, Integer i) {
        sources[i.intValue()] = u;
    }

    protected SimpleValue[] arraySetterHelper(int sourcesLength, QName elemName, QNameSet set) {
        final SimpleValue[] sources = new SimpleValue[sourcesLength];
        commonSetterHelper(elemName, set, sources, new BiConsumer() { // from class: org.apache.xmlbeans.impl.values.XmlComplexContentImpl$$ExternalSyntheticLambda12
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                XmlComplexContentImpl.lambda$arraySetterHelper$1(sources, (XmlObjectBase) obj, (Integer) obj2);
            }
        });
        return sources;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$arraySetterHelper$1(SimpleValue[] sources, XmlObjectBase u, Integer i) {
        sources[i.intValue()] = u;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void arraySetterHelper(final boolean[] sources, QName elemName) {
        commonSetterHelper(elemName, (QNameSet) null, sources == null ? 0 : sources.length, new BiConsumer() { // from class: org.apache.xmlbeans.impl.values.XmlComplexContentImpl$$ExternalSyntheticLambda21
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((XmlObjectBase) obj).setBooleanValue(sources[((Integer) obj2).intValue()]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void arraySetterHelper(final float[] sources, QName elemName) {
        commonSetterHelper(elemName, (QNameSet) null, sources == null ? 0 : sources.length, new BiConsumer() { // from class: org.apache.xmlbeans.impl.values.XmlComplexContentImpl$$ExternalSyntheticLambda18
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((XmlObjectBase) obj).setFloatValue(sources[((Integer) obj2).intValue()]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void arraySetterHelper(final double[] sources, QName elemName) {
        commonSetterHelper(elemName, (QNameSet) null, sources == null ? 0 : sources.length, new BiConsumer() { // from class: org.apache.xmlbeans.impl.values.XmlComplexContentImpl$$ExternalSyntheticLambda23
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((XmlObjectBase) obj).setDoubleValue(sources[((Integer) obj2).intValue()]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void arraySetterHelper(final byte[] sources, QName elemName) {
        commonSetterHelper(elemName, (QNameSet) null, sources == null ? 0 : sources.length, new BiConsumer() { // from class: org.apache.xmlbeans.impl.values.XmlComplexContentImpl$$ExternalSyntheticLambda5
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((XmlObjectBase) obj).setByteValue(sources[((Integer) obj2).intValue()]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void arraySetterHelper(final short[] sources, QName elemName) {
        commonSetterHelper(elemName, (QNameSet) null, sources == null ? 0 : sources.length, new BiConsumer() { // from class: org.apache.xmlbeans.impl.values.XmlComplexContentImpl$$ExternalSyntheticLambda17
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((XmlObjectBase) obj).setShortValue(sources[((Integer) obj2).intValue()]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void arraySetterHelper(final int[] sources, QName elemName) {
        commonSetterHelper(elemName, (QNameSet) null, sources == null ? 0 : sources.length, new BiConsumer() { // from class: org.apache.xmlbeans.impl.values.XmlComplexContentImpl$$ExternalSyntheticLambda24
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((XmlObjectBase) obj).setIntValue(sources[((Integer) obj2).intValue()]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void arraySetterHelper(final long[] sources, QName elemName) {
        commonSetterHelper(elemName, (QNameSet) null, sources == null ? 0 : sources.length, new BiConsumer() { // from class: org.apache.xmlbeans.impl.values.XmlComplexContentImpl$$ExternalSyntheticLambda22
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((XmlObjectBase) obj).setLongValue(sources[((Integer) obj2).intValue()]);
            }
        });
    }

    protected void arraySetterHelper(BigDecimal[] sources, QName elemName) {
        commonSetterHelper2(elemName, null, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda1());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void arraySetterHelper(BigInteger[] sources, QName elemName) {
        commonSetterHelper2(elemName, null, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda3());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void arraySetterHelper(String[] sources, QName elemName) {
        commonSetterHelper2(elemName, null, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda15());
    }

    protected void arraySetterHelper(byte[][] sources, QName elemName) {
        commonSetterHelper2(elemName, null, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda11());
    }

    protected void arraySetterHelper(GDate[] sources, QName elemName) {
        commonSetterHelper2(elemName, null, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda7());
    }

    protected void arraySetterHelper(GDuration[] sources, QName elemName) {
        commonSetterHelper2(elemName, null, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda4());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void arraySetterHelper(Calendar[] sources, QName elemName) {
        commonSetterHelper2(elemName, null, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda25());
    }

    protected void arraySetterHelper(Date[] sources, QName elemName) {
        commonSetterHelper2(elemName, null, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda6());
    }

    protected void arraySetterHelper(QName[] sources, QName elemName) {
        commonSetterHelper2(elemName, null, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda27());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void arraySetterHelper(StringEnumAbstractBase[] sources, QName elemName) {
        commonSetterHelper2(elemName, null, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda13());
    }

    protected void arraySetterHelper(List<?>[] sources, QName elemName) {
        commonSetterHelper2(elemName, null, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda9());
    }

    protected void unionArraySetterHelper(Object[] sources, QName elemName, QNameSet set) {
        commonSetterHelper2(elemName, set, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda0());
    }

    protected void arraySetterHelper(final boolean[] sources, QName elemName, QNameSet set) {
        commonSetterHelper(elemName, set, sources == null ? 0 : sources.length, new BiConsumer() { // from class: org.apache.xmlbeans.impl.values.XmlComplexContentImpl$$ExternalSyntheticLambda26
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((XmlObjectBase) obj).setBooleanValue(sources[((Integer) obj2).intValue()]);
            }
        });
    }

    protected void arraySetterHelper(final float[] sources, QName elemName, QNameSet set) {
        commonSetterHelper(elemName, set, sources == null ? 0 : sources.length, new BiConsumer() { // from class: org.apache.xmlbeans.impl.values.XmlComplexContentImpl$$ExternalSyntheticLambda8
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((XmlObjectBase) obj).setFloatValue(sources[((Integer) obj2).intValue()]);
            }
        });
    }

    protected void arraySetterHelper(final double[] sources, QName elemName, QNameSet set) {
        commonSetterHelper(elemName, set, sources == null ? 0 : sources.length, new BiConsumer() { // from class: org.apache.xmlbeans.impl.values.XmlComplexContentImpl$$ExternalSyntheticLambda14
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((XmlObjectBase) obj).setDoubleValue(sources[((Integer) obj2).intValue()]);
            }
        });
    }

    protected void arraySetterHelper(final byte[] sources, QName elemName, QNameSet set) {
        commonSetterHelper(elemName, set, sources == null ? 0 : sources.length, new BiConsumer() { // from class: org.apache.xmlbeans.impl.values.XmlComplexContentImpl$$ExternalSyntheticLambda2
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((XmlObjectBase) obj).setByteValue(sources[((Integer) obj2).intValue()]);
            }
        });
    }

    protected void arraySetterHelper(final short[] sources, QName elemName, QNameSet set) {
        commonSetterHelper(elemName, set, sources == null ? 0 : sources.length, new BiConsumer() { // from class: org.apache.xmlbeans.impl.values.XmlComplexContentImpl$$ExternalSyntheticLambda16
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((XmlObjectBase) obj).setShortValue(sources[((Integer) obj2).intValue()]);
            }
        });
    }

    protected void arraySetterHelper(final int[] sources, QName elemName, QNameSet set) {
        commonSetterHelper(elemName, set, sources == null ? 0 : sources.length, new BiConsumer() { // from class: org.apache.xmlbeans.impl.values.XmlComplexContentImpl$$ExternalSyntheticLambda10
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((XmlObjectBase) obj).setIntValue(sources[((Integer) obj2).intValue()]);
            }
        });
    }

    protected void arraySetterHelper(final long[] sources, QName elemName, QNameSet set) {
        commonSetterHelper(elemName, set, sources == null ? 0 : sources.length, new BiConsumer() { // from class: org.apache.xmlbeans.impl.values.XmlComplexContentImpl$$ExternalSyntheticLambda20
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((XmlObjectBase) obj).setLongValue(sources[((Integer) obj2).intValue()]);
            }
        });
    }

    protected void arraySetterHelper(BigDecimal[] sources, QName elemName, QNameSet set) {
        commonSetterHelper2(elemName, set, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda1());
    }

    protected void arraySetterHelper(BigInteger[] sources, QName elemName, QNameSet set) {
        commonSetterHelper2(elemName, set, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda3());
    }

    protected void arraySetterHelper(String[] sources, QName elemName, QNameSet set) {
        commonSetterHelper2(elemName, set, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda15());
    }

    protected void arraySetterHelper(byte[][] sources, QName elemName, QNameSet set) {
        commonSetterHelper2(elemName, set, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda11());
    }

    protected void arraySetterHelper(GDate[] sources, QName elemName, QNameSet set) {
        commonSetterHelper2(elemName, set, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda7());
    }

    protected void arraySetterHelper(GDuration[] sources, QName elemName, QNameSet set) {
        commonSetterHelper2(elemName, set, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda4());
    }

    protected void arraySetterHelper(Calendar[] sources, QName elemName, QNameSet set) {
        commonSetterHelper2(elemName, set, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda25());
    }

    protected void arraySetterHelper(Date[] sources, QName elemName, QNameSet set) {
        commonSetterHelper2(elemName, set, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda6());
    }

    protected void arraySetterHelper(QName[] sources, QName elemName, QNameSet set) {
        commonSetterHelper2(elemName, set, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda27());
    }

    protected void arraySetterHelper(StringEnumAbstractBase[] sources, QName elemName, QNameSet set) {
        commonSetterHelper2(elemName, set, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda13());
    }

    protected void arraySetterHelper(List<?>[] sources, QName elemName, QNameSet set) {
        commonSetterHelper2(elemName, set, sources, new XmlComplexContentImpl$$ExternalSyntheticLambda9());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void arraySetterHelper(XmlObject[] sources, QName elemName) {
        arraySetterHelper(sources, elemName, (QNameSet) null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00ba, code lost:
    
        r7.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void arraySetterHelper(org.apache.xmlbeans.XmlObject[] r12, javax.xml.namespace.QName r13, org.apache.xmlbeans.QNameSet r14) {
        /*
            Method dump skipped, instructions count: 318
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.values.XmlComplexContentImpl.arraySetterHelper(org.apache.xmlbeans.XmlObject[], javax.xml.namespace.QName, org.apache.xmlbeans.QNameSet):void");
    }

    private <T> void commonSetterHelper(QName elemName, QNameSet set, T[] sources, BiConsumer<XmlObjectBase, Integer> fun) {
        commonSetterHelper(elemName, set, sources == null ? 0 : sources.length, fun);
    }

    private void commonSetterHelper(QName elemName, QNameSet set, int n, BiConsumer<XmlObjectBase, Integer> fun) {
        TypeStoreUser user;
        TypeStore store = get_store();
        int m = set == null ? store.count_elements(elemName) : store.count_elements(set);
        while (m > n) {
            if (set == null) {
                store.remove_element(elemName, m - 1);
            } else {
                store.remove_element(set, m - 1);
            }
            m--;
        }
        for (int i = 0; i < n; i++) {
            if (i >= m) {
                user = store.add_element_user(elemName);
            } else if (set == null) {
                user = store.find_element_user(elemName, i);
            } else {
                user = store.find_element_user(set, i);
            }
            fun.accept((XmlObjectBase) user, Integer.valueOf(i));
        }
    }

    private <T> void commonSetterHelper2(QName elemName, QNameSet set, T[] sources, BiConsumer<XmlObjectBase, T> c) {
        TypeStoreUser user;
        int n = sources == null ? 0 : sources.length;
        TypeStore store = get_store();
        int m = set == null ? store.count_elements(elemName) : store.count_elements(set);
        while (m > n) {
            if (set == null) {
                store.remove_element(elemName, m - 1);
            } else {
                store.remove_element(set, m - 1);
            }
            m--;
        }
        for (int i = 0; i < n; i++) {
            if (i >= m) {
                user = store.add_element_user(elemName);
            } else if (set == null) {
                user = store.find_element_user(elemName, i);
            } else {
                user = store.find_element_user(set, i);
            }
            c.accept((XmlObjectBase) user, sources[i]);
        }
    }
}
