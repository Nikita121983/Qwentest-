package org.apache.xmlbeans;

import javax.xml.namespace.QName;

/* loaded from: classes.dex */
public interface SchemaComponent {
    public static final int ANNOTATION = 8;
    public static final int ATTRIBUTE = 3;
    public static final int ATTRIBUTE_GROUP = 4;
    public static final int ELEMENT = 1;
    public static final int IDENTITY_CONSTRAINT = 5;
    public static final int MODEL_GROUP = 6;
    public static final int NOTATION = 7;
    public static final int TYPE = 0;

    Ref getComponentRef();

    int getComponentType();

    QName getName();

    String getSourceName();

    SchemaTypeSystem getTypeSystem();

    /* loaded from: classes.dex */
    public static abstract class Ref {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        public String _handle;
        private volatile SchemaComponent _schemaComponent;
        private SchemaTypeSystem _schemaTypeSystem;

        public abstract int getComponentType();

        /* JADX INFO: Access modifiers changed from: protected */
        public Ref(SchemaComponent schemaComponent) {
            this._schemaComponent = schemaComponent;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Ref(SchemaTypeSystem schemaTypeSystem, String handle) {
            if (handle == null) {
                throw new AssertionError();
            }
            this._schemaTypeSystem = schemaTypeSystem;
            this._handle = handle;
        }

        public final SchemaTypeSystem getTypeSystem() {
            return this._schemaTypeSystem;
        }

        public final SchemaComponent getComponent() {
            if (this._schemaComponent == null && this._handle != null) {
                synchronized (this) {
                    if (this._schemaComponent == null && this._handle != null) {
                        this._schemaComponent = this._schemaTypeSystem.resolveHandle(this._handle);
                        this._schemaTypeSystem = null;
                    }
                }
            }
            return this._schemaComponent;
        }
    }
}
