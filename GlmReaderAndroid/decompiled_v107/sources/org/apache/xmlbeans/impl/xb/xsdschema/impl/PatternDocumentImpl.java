package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument;

/* loaded from: classes11.dex */
public class PatternDocumentImpl extends XmlComplexContentImpl implements PatternDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "pattern")};
    private static final long serialVersionUID = 1;

    public PatternDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument
    public PatternDocument.Pattern getPattern() {
        PatternDocument.Pattern pattern;
        synchronized (monitor()) {
            check_orphaned();
            PatternDocument.Pattern target = (PatternDocument.Pattern) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            pattern = target == null ? null : target;
        }
        return pattern;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument
    public void setPattern(PatternDocument.Pattern pattern) {
        generatedSetterHelperImpl(pattern, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument
    public PatternDocument.Pattern addNewPattern() {
        PatternDocument.Pattern target;
        synchronized (monitor()) {
            check_orphaned();
            target = (PatternDocument.Pattern) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class PatternImpl extends NoFixedFacetImpl implements PatternDocument.Pattern {
        private static final long serialVersionUID = 1;

        public PatternImpl(SchemaType sType) {
            super(sType);
        }
    }
}
