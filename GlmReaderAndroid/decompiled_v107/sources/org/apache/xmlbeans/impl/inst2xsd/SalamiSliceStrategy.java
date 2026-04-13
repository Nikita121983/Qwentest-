package org.apache.xmlbeans.impl.inst2xsd;

import org.apache.xmlbeans.impl.inst2xsd.util.Element;
import org.apache.xmlbeans.impl.inst2xsd.util.TypeSystemHolder;

/* loaded from: classes11.dex */
public class SalamiSliceStrategy extends RussianDollStrategy implements XsdGenStrategy {
    @Override // org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy
    protected void checkIfElementReferenceIsNeeded(Element child, String parentNamespace, TypeSystemHolder typeSystemHolder, Inst2XsdOptions options) {
        Element referencedElem = new Element();
        referencedElem.setGlobal(true);
        referencedElem.setName(child.getName());
        referencedElem.setType(child.getType());
        if (child.isNillable()) {
            referencedElem.setNillable(true);
            child.setNillable(false);
        }
        child.setRef(addGlobalElement(referencedElem, typeSystemHolder, options));
    }
}
