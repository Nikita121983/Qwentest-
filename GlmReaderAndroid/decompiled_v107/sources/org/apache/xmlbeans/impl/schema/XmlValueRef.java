package org.apache.xmlbeans.impl.schema;

import java.util.ArrayList;
import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;

/* loaded from: classes11.dex */
public class XmlValueRef {
    Object _initVal;
    XmlAnySimpleType _obj;
    SchemaType.Ref _typeref;

    public XmlValueRef(XmlAnySimpleType xobj) {
        if (xobj == null) {
            throw new IllegalArgumentException();
        }
        this._obj = xobj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XmlValueRef(SchemaType.Ref typeref, Object initVal) {
        if (typeref == null) {
            throw new IllegalArgumentException();
        }
        this._typeref = typeref;
        this._initVal = initVal;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized XmlAnySimpleType get() {
        if (this._obj == null) {
            SchemaType type = this._typeref.get();
            if (type.getSimpleVariety() != 3) {
                this._obj = type.newValue(this._initVal);
            } else {
                List<XmlAnySimpleType> actualVals = new ArrayList<>();
                for (XmlValueRef ref : (List) this._initVal) {
                    actualVals.add(ref.get());
                }
                this._obj = type.newValue(actualVals);
            }
        }
        return this._obj;
    }
}
