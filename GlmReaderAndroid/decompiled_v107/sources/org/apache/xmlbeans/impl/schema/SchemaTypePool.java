package org.apache.xmlbeans.impl.schema;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiConsumer;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoaderException;
import org.apache.xmlbeans.impl.common.NameUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class SchemaTypePool {
    private boolean _started;
    private final SchemaTypeSystemImpl typeSystem;
    private final Map<String, SchemaComponent.Ref> _handlesToRefs = new LinkedHashMap();
    private final Map<SchemaComponent, String> _componentsToHandles = new LinkedHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaTypePool(SchemaTypeSystemImpl typeSystem) {
        this.typeSystem = typeSystem;
    }

    private String addUniqueHandle(SchemaComponent obj, String base) {
        String base2 = base.toLowerCase(Locale.ROOT);
        String handle = base2;
        int index = 2;
        while (this._handlesToRefs.containsKey(handle)) {
            handle = base2 + index;
            index++;
        }
        this._handlesToRefs.put(handle, obj.getComponentRef());
        this._componentsToHandles.put(obj, handle);
        return handle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String handleForComponent(SchemaComponent comp) {
        if (comp == null) {
            return null;
        }
        if (comp.getTypeSystem() != this.typeSystem) {
            throw new IllegalArgumentException("Cannot supply handles for types from another type system");
        }
        if (comp instanceof SchemaType) {
            return handleForType((SchemaType) comp);
        }
        if (comp instanceof SchemaGlobalElement) {
            return handleForElement((SchemaGlobalElement) comp);
        }
        if (comp instanceof SchemaGlobalAttribute) {
            return handleForAttribute((SchemaGlobalAttribute) comp);
        }
        if (comp instanceof SchemaModelGroup) {
            return handleForModelGroup((SchemaModelGroup) comp);
        }
        if (comp instanceof SchemaAttributeGroup) {
            return handleForAttributeGroup((SchemaAttributeGroup) comp);
        }
        if (comp instanceof SchemaIdentityConstraint) {
            return handleForIdentityConstraint((SchemaIdentityConstraint) comp);
        }
        throw new IllegalStateException("Component type cannot have a handle");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String handleForElement(SchemaGlobalElement element) {
        if (element == null) {
            return null;
        }
        if (element.getTypeSystem() != this.typeSystem) {
            throw new IllegalArgumentException("Cannot supply handles for types from another type system");
        }
        String handle = this._componentsToHandles.get(element);
        if (handle == null) {
            return addUniqueHandle(element, NameUtil.upperCamelCase(element.getName().getLocalPart()) + "Element");
        }
        return handle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String handleForAttribute(SchemaGlobalAttribute attribute) {
        if (attribute == null) {
            return null;
        }
        if (attribute.getTypeSystem() != this.typeSystem) {
            throw new IllegalArgumentException("Cannot supply handles for types from another type system");
        }
        String handle = this._componentsToHandles.get(attribute);
        if (handle == null) {
            return addUniqueHandle(attribute, NameUtil.upperCamelCase(attribute.getName().getLocalPart()) + "Attribute");
        }
        return handle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String handleForModelGroup(SchemaModelGroup group) {
        if (group == null) {
            return null;
        }
        if (group.getTypeSystem() != this.typeSystem) {
            throw new IllegalArgumentException("Cannot supply handles for types from another type system");
        }
        String handle = this._componentsToHandles.get(group);
        if (handle == null) {
            return addUniqueHandle(group, NameUtil.upperCamelCase(group.getName().getLocalPart()) + "ModelGroup");
        }
        return handle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String handleForAttributeGroup(SchemaAttributeGroup group) {
        if (group == null) {
            return null;
        }
        if (group.getTypeSystem() != this.typeSystem) {
            throw new IllegalArgumentException("Cannot supply handles for types from another type system");
        }
        String handle = this._componentsToHandles.get(group);
        if (handle == null) {
            return addUniqueHandle(group, NameUtil.upperCamelCase(group.getName().getLocalPart()) + "AttributeGroup");
        }
        return handle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String handleForIdentityConstraint(SchemaIdentityConstraint idc) {
        if (idc == null) {
            return null;
        }
        if (idc.getTypeSystem() != this.typeSystem) {
            throw new IllegalArgumentException("Cannot supply handles for types from another type system");
        }
        String handle = this._componentsToHandles.get(idc);
        if (handle == null) {
            return addUniqueHandle(idc, NameUtil.upperCamelCase(idc.getName().getLocalPart()) + "IdentityConstraint");
        }
        return handle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String handleForType(SchemaType type) {
        if (type == null) {
            return null;
        }
        if (type.getTypeSystem() != this.typeSystem) {
            throw new IllegalArgumentException("Cannot supply handles for types from another type system");
        }
        String handle = this._componentsToHandles.get(type);
        if (handle == null) {
            QName name = type.getName();
            String suffix = "";
            if (name == null) {
                if (type.isDocumentType()) {
                    name = type.getDocumentElementName();
                    suffix = "Doc";
                } else if (type.isAttributeType()) {
                    name = type.getAttributeTypeAttributeName();
                    suffix = "AttrType";
                } else if (type.getContainerField() != null) {
                    name = type.getContainerField().getName();
                    suffix = type.getContainerField().isAttribute() ? "Attr" : "Elem";
                }
            }
            String uniq = Integer.toHexString(type.toString().hashCode() | Integer.MIN_VALUE).substring(4).toUpperCase(Locale.ROOT);
            String baseName = name == null ? "Anon" + uniq + PackageRelationship.TYPE_ATTRIBUTE_NAME : NameUtil.upperCamelCase(name.getLocalPart()) + uniq + suffix + PackageRelationship.TYPE_ATTRIBUTE_NAME;
            return addUniqueHandle(type, baseName);
        }
        return handle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaComponent.Ref refForHandle(String handle) {
        if (handle == null) {
            return null;
        }
        return this._handlesToRefs.get(handle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startWriteMode() {
        this._started = true;
        this._componentsToHandles.clear();
        for (String handle : this._handlesToRefs.keySet()) {
            SchemaComponent comp = this._handlesToRefs.get(handle).getComponent();
            this._componentsToHandles.put(comp, handle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeHandlePool(final XsbReader reader) {
        reader.writeShort(this._componentsToHandles.size());
        this._componentsToHandles.forEach(new BiConsumer() { // from class: org.apache.xmlbeans.impl.schema.SchemaTypePool$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                SchemaTypePool.this.m2602x3d4aa713(reader, (SchemaComponent) obj, (String) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$writeHandlePool$0$org-apache-xmlbeans-impl-schema-SchemaTypePool, reason: not valid java name */
    public /* synthetic */ void m2602x3d4aa713(XsbReader reader, SchemaComponent comp, String handle) {
        reader.writeString(handle);
        reader.writeShort(fileTypeFromComponentType(comp.getComponentType()));
    }

    int fileTypeFromComponentType(int componentType) {
        switch (componentType) {
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
            default:
                throw new IllegalStateException("Unexpected component type");
            case 3:
                return 4;
            case 4:
                return 7;
            case 5:
                return 8;
            case 6:
                return 6;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void readHandlePool(XsbReader reader) {
        SchemaComponent.Ref result;
        if (!this._handlesToRefs.isEmpty() || this._started) {
            throw new IllegalStateException("Nonempty handle set before read");
        }
        int size = reader.readShort();
        for (int i = 0; i < size; i++) {
            String handle = reader.readString();
            int code = reader.readShort();
            switch (code) {
                case 2:
                    result = new SchemaType.Ref(this.typeSystem, handle);
                    break;
                case 3:
                    result = new SchemaGlobalElement.Ref(this.typeSystem, handle);
                    break;
                case 4:
                    result = new SchemaGlobalAttribute.Ref(this.typeSystem, handle);
                    break;
                case 5:
                default:
                    throw new SchemaTypeLoaderException("Schema index has an unrecognized entry of type " + code, this.typeSystem.getName(), handle, 5);
                case 6:
                    result = new SchemaModelGroup.Ref(this.typeSystem, handle);
                    break;
                case 7:
                    result = new SchemaAttributeGroup.Ref(this.typeSystem, handle);
                    break;
                case 8:
                    result = new SchemaIdentityConstraint.Ref(this.typeSystem, handle);
                    break;
            }
            this._handlesToRefs.put(handle, result);
        }
    }
}
