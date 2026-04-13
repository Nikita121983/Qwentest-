package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCache;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches;

/* loaded from: classes12.dex */
public class CTPivotCachesImpl extends XmlComplexContentImpl implements CTPivotCaches {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "pivotCache")};
    private static final long serialVersionUID = 1;

    public CTPivotCachesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches
    public List<CTPivotCache> getPivotCacheList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTPivotCachesImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPivotCachesImpl.this.getPivotCacheArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTPivotCachesImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPivotCachesImpl.this.setPivotCacheArray(((Integer) obj).intValue(), (CTPivotCache) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTPivotCachesImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPivotCachesImpl.this.insertNewPivotCache(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTPivotCachesImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPivotCachesImpl.this.removePivotCache(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTPivotCachesImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPivotCachesImpl.this.sizeOfPivotCacheArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches
    public CTPivotCache[] getPivotCacheArray() {
        return (CTPivotCache[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTPivotCache[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches
    public CTPivotCache getPivotCacheArray(int i) {
        CTPivotCache target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPivotCache) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches
    public int sizeOfPivotCacheArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches
    public void setPivotCacheArray(CTPivotCache[] pivotCacheArray) {
        check_orphaned();
        arraySetterHelper(pivotCacheArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches
    public void setPivotCacheArray(int i, CTPivotCache pivotCache) {
        generatedSetterHelperImpl(pivotCache, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches
    public CTPivotCache insertNewPivotCache(int i) {
        CTPivotCache target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPivotCache) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches
    public CTPivotCache addNewPivotCache() {
        CTPivotCache target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPivotCache) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCaches
    public void removePivotCache(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
