package org.apache.xmlbeans;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaComponent;

/* loaded from: classes.dex */
public interface SchemaGlobalElement extends SchemaLocalElement, SchemaComponent {
    boolean finalExtension();

    boolean finalRestriction();

    Ref getRef();

    SchemaGlobalElement substitutionGroup();

    QName[] substitutionGroupMembers();

    /* loaded from: classes.dex */
    public static final class Ref extends SchemaComponent.Ref {
        public Ref(SchemaGlobalElement element) {
            super(element);
        }

        public Ref(SchemaTypeSystem system, String handle) {
            super(system, handle);
        }

        @Override // org.apache.xmlbeans.SchemaComponent.Ref
        public final int getComponentType() {
            return 1;
        }

        public final SchemaGlobalElement get() {
            return (SchemaGlobalElement) getComponent();
        }
    }
}
