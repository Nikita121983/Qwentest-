package org.apache.xmlbeans.impl.inst2xsd;

import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.xmlbeans.impl.inst2xsd.util.Element;
import org.apache.xmlbeans.impl.inst2xsd.util.Type;
import org.apache.xmlbeans.impl.inst2xsd.util.TypeSystemHolder;

/* loaded from: classes11.dex */
public class VenetianBlindStrategy extends RussianDollStrategy implements XsdGenStrategy {
    @Override // org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy
    protected void checkIfReferenceToGlobalTypeIsNeeded(Element elem, TypeSystemHolder typeSystemHolder, Inst2XsdOptions options) {
        Type elemType = elem.getType();
        QName elemName = elem.getName();
        if (!elemType.isGlobal() && elemType.isComplexType()) {
            int i = 0;
            while (true) {
                elemType.setName(new QName(elemName.getNamespaceURI(), elemName.getLocalPart() + PackageRelationship.TYPE_ATTRIBUTE_NAME + (i != 0 ? "" + i : "")));
                Type candidate = typeSystemHolder.getGlobalType(elemType.getName());
                if (candidate == null) {
                    elemType.setGlobal(true);
                    typeSystemHolder.addGlobalType(elemType);
                    return;
                } else if (!compatibleTypes(candidate, elemType)) {
                    i++;
                } else {
                    combineTypes(candidate, elemType, options);
                    elem.setType(candidate);
                    return;
                }
            }
        }
    }

    private boolean compatibleTypes(Type elemType, Type candidate) {
        return elemType == candidate ? true : true;
    }
}
