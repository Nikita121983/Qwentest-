package org.apache.xmlbeans.impl.schema;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaLocalAttribute;

/* loaded from: classes11.dex */
public class SchemaAttributeModelImpl implements SchemaAttributeModel {
    private static final SchemaLocalAttribute[] EMPTY_SLA_ARRAY = new SchemaLocalAttribute[0];
    private Map<QName, SchemaLocalAttribute> attrMap;
    private int wcProcess;
    private QNameSet wcSet;

    public SchemaAttributeModelImpl() {
        this.attrMap = new LinkedHashMap();
        this.wcSet = null;
        this.wcProcess = 0;
    }

    public SchemaAttributeModelImpl(SchemaAttributeModel sam) {
        this.attrMap = new LinkedHashMap();
        if (sam == null) {
            this.wcSet = null;
            this.wcProcess = 0;
            return;
        }
        SchemaLocalAttribute[] attrs = sam.getAttributes();
        for (int i = 0; i < attrs.length; i++) {
            this.attrMap.put(attrs[i].getName(), attrs[i]);
        }
        int i2 = sam.getWildcardProcess();
        if (i2 != 0) {
            this.wcSet = sam.getWildcardSet();
            this.wcProcess = sam.getWildcardProcess();
        }
    }

    @Override // org.apache.xmlbeans.SchemaAttributeModel
    public SchemaLocalAttribute[] getAttributes() {
        return (SchemaLocalAttribute[]) this.attrMap.values().toArray(EMPTY_SLA_ARRAY);
    }

    @Override // org.apache.xmlbeans.SchemaAttributeModel
    public SchemaLocalAttribute getAttribute(QName name) {
        return this.attrMap.get(name);
    }

    public void addAttribute(SchemaLocalAttribute attruse) {
        this.attrMap.put(attruse.getName(), attruse);
    }

    public void removeProhibitedAttribute(QName name) {
        this.attrMap.remove(name);
    }

    @Override // org.apache.xmlbeans.SchemaAttributeModel
    public QNameSet getWildcardSet() {
        return this.wcSet == null ? QNameSet.EMPTY : this.wcSet;
    }

    public void setWildcardSet(QNameSet set) {
        this.wcSet = set;
    }

    @Override // org.apache.xmlbeans.SchemaAttributeModel
    public int getWildcardProcess() {
        return this.wcProcess;
    }

    public void setWildcardProcess(int proc) {
        this.wcProcess = proc;
    }
}
