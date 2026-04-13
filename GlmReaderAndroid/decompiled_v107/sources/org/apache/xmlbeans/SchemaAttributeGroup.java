package org.apache.xmlbeans;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaComponent;

/* loaded from: classes.dex */
public interface SchemaAttributeGroup extends SchemaComponent, SchemaAnnotated {
    @Override // org.apache.xmlbeans.SchemaComponent
    int getComponentType();

    @Override // org.apache.xmlbeans.SchemaComponent
    QName getName();

    Object getUserData();

    /* loaded from: classes.dex */
    public static final class Ref extends SchemaComponent.Ref {
        public Ref(SchemaAttributeGroup attributeGroup) {
            super(attributeGroup);
        }

        public Ref(SchemaTypeSystem system, String handle) {
            super(system, handle);
        }

        @Override // org.apache.xmlbeans.SchemaComponent.Ref
        public final int getComponentType() {
            return 4;
        }

        public final SchemaAttributeGroup get() {
            return (SchemaAttributeGroup) getComponent();
        }
    }
}
