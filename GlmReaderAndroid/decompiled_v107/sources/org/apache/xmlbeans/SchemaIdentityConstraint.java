package org.apache.xmlbeans;

import java.util.Map;
import org.apache.xmlbeans.SchemaComponent;

/* loaded from: classes.dex */
public interface SchemaIdentityConstraint extends SchemaComponent, SchemaAnnotated {
    public static final int CC_KEY = 1;
    public static final int CC_KEYREF = 2;
    public static final int CC_UNIQUE = 3;

    int getConstraintCategory();

    Object getFieldPath(int i);

    String[] getFields();

    Map<String, String> getNSMap();

    SchemaIdentityConstraint getReferencedKey();

    String getSelector();

    Object getSelectorPath();

    Object getUserData();

    /* loaded from: classes.dex */
    public static final class Ref extends SchemaComponent.Ref {
        public Ref(SchemaIdentityConstraint idc) {
            super(idc);
        }

        public Ref(SchemaTypeSystem system, String handle) {
            super(system, handle);
        }

        @Override // org.apache.xmlbeans.SchemaComponent.Ref
        public final int getComponentType() {
            return 5;
        }

        public final SchemaIdentityConstraint get() {
            return (SchemaIdentityConstraint) getComponent();
        }
    }
}
