package org.apache.xmlbeans.impl.schema;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.xml.namespace.QName;
import org.apache.poi.hssf.record.DimensionsRecord;
import org.apache.poi.hssf.record.pivottable.ExtendedPivotTableViewFieldsRecord;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaLocalAttribute;
import org.apache.xmlbeans.SchemaLocalElement;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaStringEnumEntry;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoaderException;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.regex.RegularExpression;
import org.apache.xmlbeans.impl.schema.SchemaAnnotationImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl;
import org.apache.xmlbeans.impl.util.LongUTFDataInputStream;
import org.apache.xmlbeans.impl.util.LongUTFDataOutputStream;
import org.apache.xmlbeans.impl.values.XmlObjectBase;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.GroupDocument;
import org.apache.xmlbeans.soap.SOAPArrayType;
import org.apache.xmlbeans.soap.SchemaWSDLArrayType;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class XsbReader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MAX_UNSIGNED_SHORT = 65535;
    int _actualfiletype;
    private String _handle;
    private LongUTFDataInputStream _input;
    private int _majorver;
    private int _minorver;
    private LongUTFDataOutputStream _output;
    private int _releaseno;
    private SchemaTypeSystemImpl.StringPool _stringPool;
    private final SchemaTypeSystemImpl typeSystem;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XsbReader(SchemaTypeSystemImpl typeSystem, String handle) {
        this.typeSystem = typeSystem;
        this._handle = handle;
        this._stringPool = new SchemaTypeSystemImpl.StringPool(this._handle, typeSystem.getName());
    }

    public XsbReader(SchemaTypeSystemImpl typeSystem, String handle, int filetype) {
        this.typeSystem = typeSystem;
        String resourcename = typeSystem.getBasePackage() + handle + ".xsb";
        InputStream rawinput = typeSystem.getLoaderStream(resourcename);
        if (rawinput == null) {
            throw new SchemaTypeLoaderException("XML-BEANS compiled schema: Could not locate compiled schema resource " + resourcename, typeSystem.getName(), handle, 0);
        }
        this._input = new LongUTFDataInputStream(rawinput);
        this._handle = handle;
        int magic = readInt();
        if (magic != -629491010) {
            throw new SchemaTypeLoaderException("XML-BEANS compiled schema: Wrong magic cookie", typeSystem.getName(), handle, 1);
        }
        this._majorver = readShort();
        this._minorver = readShort();
        if (this._majorver != 2) {
            throw new SchemaTypeLoaderException("XML-BEANS compiled schema: Wrong major version - expecting 2, got " + this._majorver, typeSystem.getName(), handle, 2);
        }
        if (this._minorver > 24) {
            throw new SchemaTypeLoaderException("XML-BEANS compiled schema: Incompatible minor version - expecting up to 24, got " + this._minorver, typeSystem.getName(), handle, 3);
        }
        if (this._minorver >= 14) {
            if (atLeast(2, 18, 0)) {
                this._releaseno = readShort();
            }
            int actualfiletype = readShort();
            if (actualfiletype != filetype && filetype != 65535) {
                throw new SchemaTypeLoaderException("XML-BEANS compiled schema: File has the wrong type - expecting type " + filetype + ", got type " + actualfiletype, typeSystem.getName(), handle, 4);
            }
            this._stringPool = new SchemaTypeSystemImpl.StringPool(this._handle, typeSystem.getName());
            this._stringPool.readFrom(this._input);
            this._actualfiletype = actualfiletype;
            return;
        }
        throw new SchemaTypeLoaderException("XML-BEANS compiled schema: Incompatible minor version - expecting at least 14, got " + this._minorver, typeSystem.getName(), handle, 3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean atLeast(int majorver, int minorver, int releaseno) {
        if (this._majorver > majorver) {
            return true;
        }
        if (this._majorver < majorver) {
            return false;
        }
        if (this._minorver > minorver) {
            return true;
        }
        return this._minorver >= minorver && this._releaseno >= releaseno;
    }

    protected boolean atMost(int majorver, int minorver, int releaseno) {
        if (this._majorver > majorver) {
            return false;
        }
        if (this._majorver < majorver) {
            return true;
        }
        if (this._minorver > minorver) {
            return false;
        }
        return this._minorver < minorver || this._releaseno <= releaseno;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getActualFiletype() {
        return this._actualfiletype;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeRealHeader(String handle, int filetype) {
        String resourcename;
        if (handle.indexOf(47) >= 0) {
            resourcename = handle + ".xsb";
        } else {
            resourcename = this.typeSystem.getBasePackage() + handle + ".xsb";
        }
        OutputStream rawoutput = this.typeSystem.getSaverStream(resourcename, this._handle);
        if (rawoutput == null) {
            throw new SchemaTypeLoaderException("Could not write compiled schema resource " + resourcename, this.typeSystem.getName(), handle, 12);
        }
        this._output = new LongUTFDataOutputStream(rawoutput);
        this._handle = handle;
        writeInt(-629491010);
        writeShort(2);
        writeShort(24);
        writeShort(0);
        writeShort(filetype);
        this._stringPool.writeTo(this._output);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void readEnd() {
        try {
            if (this._input != null) {
                this._input.close();
            }
        } catch (IOException e) {
        }
        this._input = null;
        this._stringPool = null;
        this._handle = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeEnd() {
        try {
            if (this._output != null) {
                this._output.flush();
                this._output.close();
            }
            this._output = null;
            this._stringPool = null;
            this._handle = null;
        } catch (IOException e) {
            throw new SchemaTypeLoaderException(e.getMessage(), this.typeSystem.getName(), this._handle, 9, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeIndexData() {
        this.typeSystem.getTypePool().writeHandlePool(this);
        writeQNameMap(this.typeSystem.globalElements());
        writeQNameMap(this.typeSystem.globalAttributes());
        writeQNameMap(this.typeSystem.modelGroups());
        writeQNameMap(this.typeSystem.attributeGroups());
        writeQNameMap(this.typeSystem.identityConstraints());
        writeQNameMap(this.typeSystem.globalTypes());
        writeDocumentTypeMap(this.typeSystem.documentTypes());
        writeAttributeTypeMap(this.typeSystem.attributeTypes());
        writeClassnameMap(this.typeSystem.getTypeRefsByClassname());
        writeNamespaces(this.typeSystem.getNamespaces());
        writeQNameMap(this.typeSystem.redefinedGlobalTypes());
        writeQNameMap(this.typeSystem.redefinedModelGroups());
        writeQNameMap(this.typeSystem.redefinedAttributeGroups());
        writeAnnotations(this.typeSystem.annotations());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int readShort() {
        try {
            return this._input.readUnsignedShort();
        } catch (IOException e) {
            throw new SchemaTypeLoaderException(e.getMessage(), this.typeSystem.getName(), this._handle, 9, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeShort(int s) {
        if (s >= 65535 || s < -1) {
            throw new SchemaTypeLoaderException("Value " + s + " out of range: must fit in a 16-bit unsigned short.", this.typeSystem.getName(), this._handle, 10);
        }
        if (this._output != null) {
            try {
                this._output.writeShort(s);
            } catch (IOException e) {
                throw new SchemaTypeLoaderException(e.getMessage(), this.typeSystem.getName(), this._handle, 9, e);
            }
        }
    }

    int readUnsignedShortOrInt() {
        try {
            return this._input.readUnsignedShortOrInt();
        } catch (IOException e) {
            throw new SchemaTypeLoaderException(e.getMessage(), this.typeSystem.getName(), this._handle, 9, e);
        }
    }

    void writeShortOrInt(int s) {
        if (this._output != null) {
            try {
                this._output.writeShortOrInt(s);
            } catch (IOException e) {
                throw new SchemaTypeLoaderException(e.getMessage(), this.typeSystem.getName(), this._handle, 9, e);
            }
        }
    }

    int readInt() {
        try {
            return this._input.readInt();
        } catch (IOException e) {
            throw new SchemaTypeLoaderException(e.getMessage(), this.typeSystem.getName(), this._handle, 9, e);
        }
    }

    void writeInt(int i) {
        if (this._output != null) {
            try {
                this._output.writeInt(i);
            } catch (IOException e) {
                throw new SchemaTypeLoaderException(e.getMessage(), this.typeSystem.getName(), this._handle, 9, e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String readString() {
        int code = readUnsignedShortOrInt();
        return this._stringPool.stringForCode(code);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeString(String str) {
        int code = this._stringPool.codeForString(str);
        writeShortOrInt(code);
    }

    QName readQName() {
        String namespace = readString();
        String localname = readString();
        if (localname == null) {
            return null;
        }
        return new QName(namespace, localname);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeQName(QName qname) {
        if (qname == null) {
            writeString(null);
            writeString(null);
        } else {
            writeString(qname.getNamespaceURI());
            writeString(qname.getLocalPart());
        }
    }

    SOAPArrayType readSOAPArrayType() {
        QName qName = readQName();
        String dimensions = readString();
        if (qName == null) {
            return null;
        }
        return new SOAPArrayType(qName, dimensions);
    }

    void writeSOAPArrayType(SOAPArrayType arrayType) {
        if (arrayType == null) {
            writeQName(null);
            writeString(null);
        } else {
            writeQName(arrayType.getQName());
            writeString(arrayType.soap11DimensionString());
        }
    }

    void writeAnnotation(SchemaAnnotation a) {
        if (a == null) {
            writeInt(-1);
            return;
        }
        SchemaAnnotation.Attribute[] attributes = a.getAttributes();
        writeInt(attributes.length);
        for (SchemaAnnotation.Attribute attribute : attributes) {
            QName name = attribute.getName();
            String value = attribute.getValue();
            String valueURI = attribute.getValueUri();
            writeQName(name);
            writeString(value);
            writeString(valueURI);
        }
        XmlObject[] documentationItems = a.getUserInformation();
        writeInt(documentationItems.length);
        XmlOptions opt = new XmlOptions().setSaveOuter().setSaveAggressiveNamespaces();
        for (XmlObject doc : documentationItems) {
            writeString(doc.xmlText(opt));
        }
        XmlObject[] appInfoItems = a.getApplicationInformation();
        writeInt(appInfoItems.length);
        for (XmlObject doc2 : appInfoItems) {
            writeString(doc2.xmlText(opt));
        }
    }

    SchemaAnnotation readAnnotation(SchemaContainer c) {
        int n;
        if (!atLeast(2, 19, 0) || (n = readInt()) == -1) {
            return null;
        }
        SchemaAnnotation.Attribute[] attributes = new SchemaAnnotation.Attribute[n];
        for (int i = 0; i < n; i++) {
            QName name = readQName();
            String value = readString();
            String valueUri = null;
            if (atLeast(2, 24, 0)) {
                valueUri = readString();
            }
            attributes[i] = new SchemaAnnotationImpl.AttributeImpl(name, value, valueUri);
        }
        int n2 = readInt();
        String[] docStrings = new String[n2];
        for (int i2 = 0; i2 < n2; i2++) {
            docStrings[i2] = readString();
        }
        int n3 = readInt();
        String[] appInfoStrings = new String[n3];
        for (int i3 = 0; i3 < n3; i3++) {
            appInfoStrings[i3] = readString();
        }
        return new SchemaAnnotationImpl(c, appInfoStrings, docStrings, attributes);
    }

    void writeAnnotations(SchemaAnnotation[] anns) {
        writeInt(anns.length);
        for (SchemaAnnotation ann : anns) {
            writeAnnotation(ann);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<SchemaAnnotation> readAnnotations() {
        int n = readInt();
        List<SchemaAnnotation> result = new ArrayList<>(n);
        SchemaContainer container = this.typeSystem.getContainerNonNull("");
        for (int i = 0; i < n; i++) {
            result.add(readAnnotation(container));
        }
        return result;
    }

    SchemaComponent.Ref readHandle() {
        String handle = readString();
        if (handle == null) {
            return null;
        }
        if (handle.charAt(0) != '_') {
            return this.typeSystem.getTypePool().refForHandle(handle);
        }
        switch (handle.charAt(2)) {
            case 'A':
                return this.typeSystem.getLinker().findAttributeRef(QNameHelper.forPretty(handle, 4));
            case 'D':
                return this.typeSystem.getLinker().findIdentityConstraintRef(QNameHelper.forPretty(handle, 4));
            case 'E':
                return this.typeSystem.getLinker().findElementRef(QNameHelper.forPretty(handle, 4));
            case 'I':
                SchemaType st = (SchemaType) BuiltinSchemaTypeSystem.get().resolveHandle(handle);
                if (st != null) {
                    return st.getRef();
                }
                return ((SchemaType) XQuerySchemaTypeSystem.get().resolveHandle(handle)).getRef();
            case 'M':
                return this.typeSystem.getLinker().findModelGroupRef(QNameHelper.forPretty(handle, 4));
            case 'N':
                return this.typeSystem.getLinker().findAttributeGroupRef(QNameHelper.forPretty(handle, 4));
            case 'O':
                return this.typeSystem.getLinker().findDocumentTypeRef(QNameHelper.forPretty(handle, 4));
            case 'R':
                SchemaGlobalAttribute attr = this.typeSystem.getLinker().findAttribute(QNameHelper.forPretty(handle, 4));
                if (attr == null) {
                    throw new SchemaTypeLoaderException("Cannot resolve attribute for handle " + handle, this.typeSystem.getName(), this._handle, 13);
                }
                return attr.getType().getRef();
            case 'S':
                SchemaGlobalElement elem = this.typeSystem.getLinker().findElement(QNameHelper.forPretty(handle, 4));
                if (elem == null) {
                    throw new SchemaTypeLoaderException("Cannot resolve element for handle " + handle, this.typeSystem.getName(), this._handle, 13);
                }
                return elem.getType().getRef();
            case 'T':
                return this.typeSystem.getLinker().findTypeRef(QNameHelper.forPretty(handle, 4));
            case 'Y':
                SchemaType type = this.typeSystem.getLinker().typeForSignature(handle.substring(4));
                if (type == null) {
                    throw new SchemaTypeLoaderException("Cannot resolve type for handle " + handle, this.typeSystem.getName(), this._handle, 13);
                }
                return type.getRef();
            default:
                throw new SchemaTypeLoaderException("Cannot resolve handle " + handle, this.typeSystem.getName(), this._handle, 13);
        }
    }

    void writeHandle(SchemaComponent comp) {
        if (comp == null || comp.getTypeSystem() == this.typeSystem) {
            writeString(this.typeSystem.getTypePool().handleForComponent(comp));
            return;
        }
        switch (comp.getComponentType()) {
            case 0:
                SchemaType type = (SchemaType) comp;
                if (type.isBuiltinType()) {
                    writeString("_BI_" + type.getName().getLocalPart());
                    return;
                }
                if (type.getName() != null) {
                    writeString("_XT_" + QNameHelper.pretty(type.getName()));
                    return;
                } else if (type.isDocumentType()) {
                    writeString("_XO_" + QNameHelper.pretty(type.getDocumentElementName()));
                    return;
                } else {
                    writeString("_XY_" + type);
                    return;
                }
            case 1:
                writeString("_XE_" + QNameHelper.pretty(comp.getName()));
                return;
            case 2:
            default:
                throw new AssertionError();
            case 3:
                writeString("_XA_" + QNameHelper.pretty(comp.getName()));
                return;
            case 4:
                writeString("_XN_" + QNameHelper.pretty(comp.getName()));
                return;
            case 5:
                writeString("_XD_" + QNameHelper.pretty(comp.getName()));
                return;
            case 6:
                writeString("_XM_" + QNameHelper.pretty(comp.getName()));
                return;
        }
    }

    SchemaType.Ref readTypeRef() {
        return (SchemaType.Ref) readHandle();
    }

    void writeType(SchemaType type) {
        writeHandle(type);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<QName, SchemaComponent.Ref> readQNameRefMap() {
        Map<QName, SchemaComponent.Ref> result = new HashMap<>();
        int size = readShort();
        for (int i = 0; i < size; i++) {
            QName name = readQName();
            SchemaComponent.Ref obj = readHandle();
            result.put(name, obj);
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<SchemaComponent.Ref> readQNameRefMapAsList(List<QName> names) {
        int size = readShort();
        List<SchemaComponent.Ref> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            QName name = readQName();
            SchemaComponent.Ref obj = readHandle();
            result.add(obj);
            names.add(name);
        }
        return result;
    }

    void writeQNameMap(SchemaComponent[] components) {
        writeShort(components.length);
        for (SchemaComponent component : components) {
            writeQName(component.getName());
            writeHandle(component);
        }
    }

    void writeDocumentTypeMap(SchemaType[] doctypes) {
        writeShort(doctypes.length);
        for (SchemaType doctype : doctypes) {
            writeQName(doctype.getDocumentElementName());
            writeHandle(doctype);
        }
    }

    void writeAttributeTypeMap(SchemaType[] attrtypes) {
        writeShort(attrtypes.length);
        for (SchemaType attrtype : attrtypes) {
            writeQName(attrtype.getAttributeTypeAttributeName());
            writeHandle(attrtype);
        }
    }

    SchemaType.Ref[] readTypeRefArray() {
        int size = readShort();
        SchemaType.Ref[] result = new SchemaType.Ref[size];
        for (int i = 0; i < size; i++) {
            result[i] = readTypeRef();
        }
        return result;
    }

    void writeTypeArray(SchemaType[] array) {
        writeShort(array.length);
        for (SchemaType schemaType : array) {
            writeHandle(schemaType);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, SchemaComponent.Ref> readClassnameRefMap() {
        Map<String, SchemaComponent.Ref> result = new HashMap<>();
        int size = readShort();
        for (int i = 0; i < size; i++) {
            String name = readString();
            SchemaComponent.Ref obj = readHandle();
            result.put(name, obj);
        }
        return result;
    }

    void writeClassnameMap(Map<String, SchemaComponent.Ref> typesByClass) {
        writeShort(typesByClass.size());
        typesByClass.forEach(new BiConsumer() { // from class: org.apache.xmlbeans.impl.schema.XsbReader$$ExternalSyntheticLambda3
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                XsbReader.this.m2612xcebaeb03((String) obj, (SchemaComponent.Ref) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$writeClassnameMap$0$org-apache-xmlbeans-impl-schema-XsbReader, reason: not valid java name */
    public /* synthetic */ void m2612xcebaeb03(String className, SchemaComponent.Ref ref) {
        writeString(className);
        writeHandle(((SchemaType.Ref) ref).get());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<String> readNamespaces() {
        Set<String> result = new HashSet<>();
        int size = readShort();
        for (int i = 0; i < size; i++) {
            String ns = readString();
            result.add(ns);
        }
        return result;
    }

    void writeNamespaces(Set<String> namespaces) {
        writeShort(namespaces.size());
        namespaces.forEach(new XsbReader$$ExternalSyntheticLambda0(this));
    }

    void checkContainerNotNull(SchemaContainer container, QName name) {
        if (container == null) {
            throw new LinkageError("Loading of resource " + name + '.' + this._handle + "failed, information from " + name + ".index.xsb is  out of sync (or conflicting index files found)");
        }
    }

    public SchemaGlobalElement finishLoadingElement() {
        try {
            try {
                int particleType = readShort();
                if (particleType != 4) {
                    throw new SchemaTypeLoaderException("Wrong particle type ", this.typeSystem.getName(), this._handle, 11);
                }
                int particleFlags = readShort();
                BigInteger minOccurs = readBigInteger();
                BigInteger maxOccurs = readBigInteger();
                QNameSet transitionRules = readQNameSet();
                QName name = readQName();
                SchemaContainer container = this.typeSystem.getContainer(name.getNamespaceURI());
                checkContainerNotNull(container, name);
                SchemaGlobalElementImpl impl = new SchemaGlobalElementImpl(container);
                impl.setParticleType(particleType);
                impl.setMinOccurs(minOccurs);
                impl.setMaxOccurs(maxOccurs);
                boolean z = true;
                impl.setTransitionRules(transitionRules, (particleFlags & 1) != 0);
                impl.setNameAndTypeRef(name, readTypeRef());
                impl.setDefault(readString(), (particleFlags & 4) != 0, null);
                if (atLeast(2, 16, 0)) {
                    impl.setDefaultValue(readXmlValueObject());
                }
                impl.setNillable((particleFlags & 8) != 0);
                impl.setBlock((particleFlags & 16) != 0, (particleFlags & 32) != 0, (particleFlags & 64) != 0);
                impl.setWsdlArrayType(readSOAPArrayType());
                impl.setAbstract((particleFlags & 128) != 0);
                impl.setAnnotation(readAnnotation(container));
                boolean z2 = (particleFlags & 256) != 0;
                if ((particleFlags & 512) == 0) {
                    z = false;
                }
                impl.setFinal(z2, z);
                if (atLeast(2, 17, 0)) {
                    impl.setSubstitutionGroup((SchemaGlobalElement.Ref) readHandle());
                }
                int substGroupCount = readShort();
                for (int i = 0; i < substGroupCount; i++) {
                    impl.addSubstitutionGroupMember(readQName());
                }
                int i2 = readShort();
                SchemaIdentityConstraint.Ref[] idcs = new SchemaIdentityConstraint.Ref[i2];
                for (int i3 = 0; i3 < idcs.length; i3++) {
                    idcs[i3] = (SchemaIdentityConstraint.Ref) readHandle();
                }
                impl.setIdentityConstraints(idcs);
                impl.setFilename(readString());
                return impl;
            } catch (SchemaTypeLoaderException e) {
                throw e;
            } catch (Exception e2) {
                throw new SchemaTypeLoaderException("Cannot load type from typesystem", this.typeSystem.getName(), null, 14, e2);
            }
        } finally {
            readEnd();
        }
    }

    public SchemaGlobalAttribute finishLoadingAttribute() {
        try {
            try {
                QName name = readQName();
                SchemaContainer container = this.typeSystem.getContainer(name.getNamespaceURI());
                checkContainerNotNull(container, name);
                SchemaGlobalAttributeImpl impl = new SchemaGlobalAttributeImpl(container);
                loadAttribute(impl, name, container);
                impl.setFilename(readString());
                return impl;
            } catch (SchemaTypeLoaderException e) {
                throw e;
            } catch (Exception e2) {
                throw new SchemaTypeLoaderException("Cannot load type from typesystem", this.typeSystem.getName(), this._handle, 14, e2);
            }
        } finally {
            readEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public SchemaModelGroup finishLoadingModelGroup() {
        int i;
        String str;
        String str2;
        boolean z;
        QName name = readQName();
        SchemaContainer container = this.typeSystem.getContainer(name.getNamespaceURI());
        checkContainerNotNull(container, name);
        SchemaModelGroupImpl schemaModelGroupImpl = new SchemaModelGroupImpl(container);
        try {
            try {
                try {
                    String readString = readString();
                    boolean z2 = true;
                    if (readShort() == 1) {
                        i = 1;
                    } else {
                        i = 1;
                        z2 = false;
                    }
                    String readString2 = atLeast(2, 22, 0) ? readString() : null;
                    String readString3 = atLeast(2, 22, 0) ? readString() : null;
                    if (atLeast(2, 15, 0) && readShort() == i) {
                        str = readString2;
                        str2 = readString3;
                        z = i;
                    } else {
                        str = readString2;
                        str2 = readString3;
                        z = 0;
                    }
                    schemaModelGroupImpl.init(name, readString, z2, str, str2, z, GroupDocument.Factory.parse(readString()).getGroup(), readAnnotation(container), null);
                    if (atLeast(2, 21, 0)) {
                        schemaModelGroupImpl.setFilename(readString());
                    }
                    return schemaModelGroupImpl;
                } catch (SchemaTypeLoaderException e) {
                    throw e;
                }
            } catch (Exception e2) {
                throw new SchemaTypeLoaderException("Cannot load type from typesystem", this.typeSystem.getName(), this._handle, 14, e2);
            }
        } finally {
            readEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchemaIdentityConstraint finishLoadingIdentityConstraint() {
        try {
            try {
                QName name = readQName();
                SchemaContainer container = this.typeSystem.getContainer(name.getNamespaceURI());
                checkContainerNotNull(container, name);
                SchemaIdentityConstraintImpl impl = new SchemaIdentityConstraintImpl(container);
                impl.setName(name);
                impl.setConstraintCategory(readShort());
                impl.setSelector(readString());
                impl.setAnnotation(readAnnotation(container));
                String[] fields = new String[readShort()];
                for (int i = 0; i < fields.length; i++) {
                    fields[i] = readString();
                }
                impl.setFields(fields);
                if (impl.getConstraintCategory() == 2) {
                    impl.setReferencedKey((SchemaIdentityConstraint.Ref) readHandle());
                }
                int mapCount = readShort();
                Map<String, String> nsMappings = new HashMap<>();
                for (int i2 = 0; i2 < mapCount; i2++) {
                    String prefix = readString();
                    String uri = readString();
                    nsMappings.put(prefix, uri);
                }
                impl.setNSMap(nsMappings);
                if (atLeast(2, 21, 0)) {
                    impl.setFilename(readString());
                }
                return impl;
            } catch (SchemaTypeLoaderException e) {
                throw e;
            } catch (Exception e2) {
                throw new SchemaTypeLoaderException("Cannot load type from typesystem", this.typeSystem.getName(), this._handle, 14, e2);
            }
        } finally {
            readEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public SchemaAttributeGroup finishLoadingAttributeGroup() {
        int i;
        QName name = readQName();
        SchemaContainer container = this.typeSystem.getContainer(name.getNamespaceURI());
        checkContainerNotNull(container, name);
        SchemaAttributeGroupImpl schemaAttributeGroupImpl = new SchemaAttributeGroupImpl(container);
        try {
            try {
                try {
                    String readString = readString();
                    boolean z = true;
                    if (readShort() == 1) {
                        i = 1;
                    } else {
                        i = 1;
                        z = false;
                    }
                    schemaAttributeGroupImpl.init(name, readString, z, atLeast(2, 22, 0) ? readString() : null, (atLeast(2, 15, 0) && readShort() == i) ? i : 0, AttributeGroupDocument.Factory.parse(readString()).getAttributeGroup(), readAnnotation(container), null);
                    if (atLeast(2, 21, 0)) {
                        schemaAttributeGroupImpl.setFilename(readString());
                    }
                    return schemaAttributeGroupImpl;
                } catch (Exception e) {
                    throw new SchemaTypeLoaderException("Cannot load type from typesystem", this.typeSystem.getName(), this._handle, 14, e);
                }
            } catch (SchemaTypeLoaderException e2) {
                throw e2;
            }
        } finally {
            readEnd();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:165:0x03ec A[Catch: all -> 0x0455, Exception -> 0x0457, SchemaTypeLoaderException -> 0x046b, TryCatch #3 {SchemaTypeLoaderException -> 0x046b, Exception -> 0x0457, blocks: (B:3:0x0004, B:4:0x0041, B:6:0x005d, B:9:0x0066, B:12:0x0072, B:15:0x008f, B:18:0x0097, B:21:0x00a1, B:24:0x00ac, B:27:0x00b4, B:29:0x00c1, B:32:0x00c9, B:35:0x00d0, B:38:0x00d7, B:41:0x00de, B:44:0x00e5, B:47:0x00ef, B:50:0x00f6, B:53:0x0100, B:55:0x0113, B:56:0x011a, B:58:0x0126, B:60:0x0130, B:62:0x014a, B:64:0x0156, B:66:0x0168, B:67:0x0195, B:79:0x0235, B:84:0x0279, B:87:0x0288, B:90:0x0296, B:93:0x02a1, B:96:0x02ac, B:99:0x02b7, B:102:0x02c2, B:105:0x02cd, B:108:0x02d5, B:111:0x02dd, B:113:0x02ed, B:117:0x0302, B:121:0x0307, B:124:0x0319, B:126:0x0325, B:128:0x0339, B:130:0x0347, B:134:0x0355, B:136:0x0361, B:138:0x036f, B:140:0x0393, B:141:0x03a7, B:142:0x03aa, B:143:0x0445, B:144:0x0454, B:145:0x03ae, B:146:0x03bf, B:147:0x03d0, B:158:0x028f, B:163:0x03df, B:165:0x03ec, B:168:0x0405, B:170:0x040b, B:172:0x0411, B:173:0x0422, B:175:0x0428, B:177:0x042e, B:180:0x01ac, B:182:0x01b8, B:183:0x01c0, B:185:0x01d0, B:187:0x01dc, B:189:0x01ee, B:190:0x0221, B:193:0x01bc, B:196:0x0254, B:197:0x026d, B:214:0x0045, B:215:0x004d, B:216:0x0055), top: B:2:0x0004, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0405 A[Catch: all -> 0x0455, Exception -> 0x0457, SchemaTypeLoaderException -> 0x046b, TryCatch #3 {SchemaTypeLoaderException -> 0x046b, Exception -> 0x0457, blocks: (B:3:0x0004, B:4:0x0041, B:6:0x005d, B:9:0x0066, B:12:0x0072, B:15:0x008f, B:18:0x0097, B:21:0x00a1, B:24:0x00ac, B:27:0x00b4, B:29:0x00c1, B:32:0x00c9, B:35:0x00d0, B:38:0x00d7, B:41:0x00de, B:44:0x00e5, B:47:0x00ef, B:50:0x00f6, B:53:0x0100, B:55:0x0113, B:56:0x011a, B:58:0x0126, B:60:0x0130, B:62:0x014a, B:64:0x0156, B:66:0x0168, B:67:0x0195, B:79:0x0235, B:84:0x0279, B:87:0x0288, B:90:0x0296, B:93:0x02a1, B:96:0x02ac, B:99:0x02b7, B:102:0x02c2, B:105:0x02cd, B:108:0x02d5, B:111:0x02dd, B:113:0x02ed, B:117:0x0302, B:121:0x0307, B:124:0x0319, B:126:0x0325, B:128:0x0339, B:130:0x0347, B:134:0x0355, B:136:0x0361, B:138:0x036f, B:140:0x0393, B:141:0x03a7, B:142:0x03aa, B:143:0x0445, B:144:0x0454, B:145:0x03ae, B:146:0x03bf, B:147:0x03d0, B:158:0x028f, B:163:0x03df, B:165:0x03ec, B:168:0x0405, B:170:0x040b, B:172:0x0411, B:173:0x0422, B:175:0x0428, B:177:0x042e, B:180:0x01ac, B:182:0x01b8, B:183:0x01c0, B:185:0x01d0, B:187:0x01dc, B:189:0x01ee, B:190:0x0221, B:193:0x01bc, B:196:0x0254, B:197:0x026d, B:214:0x0045, B:215:0x004d, B:216:0x0055), top: B:2:0x0004, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0231  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.xmlbeans.SchemaType finishLoadingType() {
        /*
            Method dump skipped, instructions count: 1158
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.XsbReader.finishLoadingType():org.apache.xmlbeans.SchemaType");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeTypeData(SchemaType schemaType) {
        SchemaParticle[] schemaParticleArr;
        writeQName(schemaType.getName());
        writeType(schemaType.getOuterType());
        writeShort(((SchemaTypeImpl) schemaType).getBaseDepth());
        writeType(schemaType.getBaseType());
        writeShort(schemaType.getDerivationType());
        writeAnnotation(schemaType.getAnnotation());
        if (schemaType.getContainerField() == null) {
            writeShort(0);
        } else if (schemaType.getOuterType().isAttributeType() || schemaType.getOuterType().isDocumentType()) {
            writeShort(1);
            writeHandle((SchemaComponent) schemaType.getContainerField());
        } else if (schemaType.getContainerField().isAttribute()) {
            writeShort(2);
            writeShort(((SchemaTypeImpl) schemaType.getOuterType()).getIndexForLocalAttribute((SchemaLocalAttribute) schemaType.getContainerField()));
        } else {
            writeShort(3);
            writeShort(((SchemaTypeImpl) schemaType.getOuterType()).getIndexForLocalElement((SchemaLocalElement) schemaType.getContainerField()));
        }
        writeString(schemaType.getFullJavaName());
        writeString(schemaType.getFullJavaImplName());
        writeTypeArray(schemaType.getAnonymousTypes());
        writeShort(schemaType.getAnonymousUnionMemberOrdinal());
        int i = 0;
        if (schemaType.isSimpleType()) {
            i = 0 | 1;
        }
        if (schemaType.isDocumentType()) {
            i |= 2;
        }
        if (schemaType.isAttributeType()) {
            i |= 524288;
        }
        if (schemaType.ordered() != 0) {
            i |= 4;
        }
        if (schemaType.ordered() == 2) {
            i |= 1024;
        }
        if (schemaType.isBounded()) {
            i |= 8;
        }
        if (schemaType.isFinite()) {
            i |= 16;
        }
        if (schemaType.isNumeric()) {
            i |= 32;
        }
        if (schemaType.hasStringEnumValues()) {
            i |= 64;
        }
        if (((SchemaTypeImpl) schemaType).isUnionOfLists()) {
            i |= 128;
        }
        if (schemaType.hasPatternFacet()) {
            i |= 256;
        }
        if (schemaType.isOrderSensitive()) {
            i |= 512;
        }
        if (schemaType.blockExtension()) {
            i |= 4096;
        }
        if (schemaType.blockRestriction()) {
            i |= 8192;
        }
        if (schemaType.finalExtension()) {
            i |= 16384;
        }
        if (schemaType.finalRestriction()) {
            i |= 16384;
        }
        if (schemaType.finalList()) {
            i |= 131072;
        }
        if (schemaType.finalUnion()) {
            i |= 65536;
        }
        if (schemaType.isAbstract()) {
            i |= 262144;
        }
        writeInt(i);
        if (!schemaType.isSimpleType()) {
            writeShort(schemaType.getContentType());
            writeType(schemaType.getContentBasedOnType());
            SchemaAttributeModel attributeModel = schemaType.getAttributeModel();
            SchemaLocalAttribute[] attributes = attributeModel.getAttributes();
            writeShort(attributes.length);
            for (SchemaLocalAttribute schemaLocalAttribute : attributes) {
                writeAttributeData(schemaLocalAttribute);
            }
            writeQNameSet(attributeModel.getWildcardSet());
            writeShort(attributeModel.getWildcardProcess());
            SchemaProperty[] attributeProperties = schemaType.getAttributeProperties();
            writeShort(attributeProperties.length);
            for (SchemaProperty schemaProperty : attributeProperties) {
                writePropertyData(schemaProperty);
            }
            if (schemaType.getContentType() == 3 || schemaType.getContentType() == 4) {
                writeShort(schemaType.hasAllContent() ? 1 : 0);
                if (schemaType.getContentModel() != null) {
                    schemaParticleArr = new SchemaParticle[]{schemaType.getContentModel()};
                } else {
                    schemaParticleArr = new SchemaParticle[0];
                }
                writeParticleArray(schemaParticleArr);
                SchemaProperty[] elementProperties = schemaType.getElementProperties();
                writeShort(elementProperties.length);
                for (SchemaProperty schemaProperty2 : elementProperties) {
                    writePropertyData(schemaProperty2);
                }
            }
        }
        if (schemaType.isSimpleType() || schemaType.getContentType() == 2) {
            writeShort(schemaType.getSimpleVariety());
            int i2 = 0;
            for (int i3 = 0; i3 <= 11; i3++) {
                if (schemaType.getFacet(i3) != null) {
                    i2++;
                }
            }
            writeShort(i2);
            for (int i4 = 0; i4 <= 11; i4++) {
                XmlAnySimpleType facet = schemaType.getFacet(i4);
                if (facet != null) {
                    writeShort(i4);
                    writeXmlValueObject(facet);
                    writeShort(schemaType.isFacetFixed(i4) ? 1 : 0);
                }
            }
            writeShort(schemaType.getWhiteSpaceRule());
            RegularExpression[] patternExpressions = ((SchemaTypeImpl) schemaType).getPatternExpressions();
            writeShort(patternExpressions.length);
            for (RegularExpression regularExpression : patternExpressions) {
                writeString(regularExpression.getPattern());
            }
            XmlAnySimpleType[] enumerationValues = schemaType.getEnumerationValues();
            if (enumerationValues == null) {
                writeShort(0);
            } else {
                writeShortOrInt(enumerationValues.length);
                for (XmlAnySimpleType xmlAnySimpleType : enumerationValues) {
                    writeXmlValueObject(xmlAnySimpleType);
                }
            }
            writeType(schemaType.getBaseEnumType());
            if (schemaType.hasStringEnumValues()) {
                SchemaStringEnumEntry[] stringEnumEntries = schemaType.getStringEnumEntries();
                writeShort(stringEnumEntries.length);
                for (SchemaStringEnumEntry schemaStringEnumEntry : stringEnumEntries) {
                    writeString(schemaStringEnumEntry.getString());
                    writeShort(schemaStringEnumEntry.getIntValue());
                    writeString(schemaStringEnumEntry.getEnumName());
                }
            }
            switch (schemaType.getSimpleVariety()) {
                case 1:
                    writeType(schemaType.getPrimitiveType());
                    writeInt(schemaType.getDecimalSize());
                    break;
                case 2:
                    writeTypeArray(schemaType.getUnionMemberTypes());
                    break;
                case 3:
                    writeType(schemaType.getListItemType());
                    break;
            }
        }
        writeString(schemaType.getSourceName());
    }

    SchemaLocalAttribute readAttributeData() {
        SchemaLocalAttributeImpl result = new SchemaLocalAttributeImpl();
        loadAttribute(result, readQName(), null);
        return result;
    }

    void loadAttribute(SchemaLocalAttributeImpl result, QName name, SchemaContainer container) {
        result.init(name, readTypeRef(), readShort(), readString(), null, atLeast(2, 16, 0) ? readXmlValueObject() : null, readShort() == 1, readSOAPArrayType(), readAnnotation(container), null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeAttributeData(SchemaLocalAttribute schemaLocalAttribute) {
        writeQName(schemaLocalAttribute.getName());
        writeType(schemaLocalAttribute.getType());
        writeShort(schemaLocalAttribute.getUse());
        writeString(schemaLocalAttribute.getDefaultText());
        writeXmlValueObject(schemaLocalAttribute.getDefaultValue());
        writeShort(schemaLocalAttribute.isFixed() ? 1 : 0);
        writeSOAPArrayType(((SchemaWSDLArrayType) schemaLocalAttribute).getWSDLArrayType());
        writeAnnotation(schemaLocalAttribute.getAnnotation());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeIdConstraintData(SchemaIdentityConstraint idc) {
        writeQName(idc.getName());
        writeShort(idc.getConstraintCategory());
        writeString(idc.getSelector());
        writeAnnotation(idc.getAnnotation());
        String[] fields = idc.getFields();
        writeShort(fields.length);
        for (String field : fields) {
            writeString(field);
        }
        if (idc.getConstraintCategory() == 2) {
            writeHandle(idc.getReferencedKey());
        }
        Map<String, String> mappings = idc.getNSMap();
        writeShort(mappings.size());
        mappings.forEach(new BiConsumer() { // from class: org.apache.xmlbeans.impl.schema.XsbReader$$ExternalSyntheticLambda4
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                XsbReader.this.m2613x5d5b7e0d((String) obj, (String) obj2);
            }
        });
        writeString(idc.getSourceName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$writeIdConstraintData$1$org-apache-xmlbeans-impl-schema-XsbReader, reason: not valid java name */
    public /* synthetic */ void m2613x5d5b7e0d(String prefix, String uri) {
        writeString(prefix);
        writeString(uri);
    }

    SchemaParticle[] readParticleArray() {
        SchemaParticle[] result = new SchemaParticle[readShort()];
        for (int i = 0; i < result.length; i++) {
            result[i] = readParticleData();
        }
        return result;
    }

    void writeParticleArray(SchemaParticle[] spa) {
        writeShort(spa.length);
        for (SchemaParticle schemaParticle : spa) {
            writeParticleData(schemaParticle);
        }
    }

    SchemaParticle readParticleData() {
        SchemaParticleImpl result;
        int particleType = readShort();
        if (particleType != 4) {
            result = new SchemaParticleImpl();
        } else {
            result = new SchemaLocalElementImpl();
        }
        loadParticle(result, particleType);
        return result;
    }

    void loadParticle(SchemaParticleImpl result, int particleType) {
        int particleFlags = readShort();
        result.setParticleType(particleType);
        result.setMinOccurs(readBigInteger());
        result.setMaxOccurs(readBigInteger());
        result.setTransitionRules(readQNameSet(), (particleFlags & 1) != 0);
        switch (particleType) {
            case 1:
            case 2:
            case 3:
                result.setParticleChildren(readParticleArray());
                return;
            case 4:
                SchemaLocalElementImpl lresult = (SchemaLocalElementImpl) result;
                lresult.setNameAndTypeRef(readQName(), readTypeRef());
                lresult.setDefault(readString(), (particleFlags & 4) != 0, null);
                if (atLeast(2, 16, 0)) {
                    lresult.setDefaultValue(readXmlValueObject());
                }
                lresult.setNillable((particleFlags & 8) != 0);
                lresult.setBlock((particleFlags & 16) != 0, (particleFlags & 32) != 0, (particleFlags & 64) != 0);
                lresult.setWsdlArrayType(readSOAPArrayType());
                lresult.setAbstract((particleFlags & 128) != 0);
                lresult.setAnnotation(readAnnotation(null));
                SchemaIdentityConstraint.Ref[] idcs = new SchemaIdentityConstraint.Ref[readShort()];
                for (int i = 0; i < idcs.length; i++) {
                    idcs[i] = (SchemaIdentityConstraint.Ref) readHandle();
                }
                lresult.setIdentityConstraints(idcs);
                return;
            case 5:
                result.setWildcardSet(readQNameSet());
                result.setWildcardProcess(readShort());
                return;
            default:
                throw new SchemaTypeLoaderException("Unrecognized particle type ", this.typeSystem.getName(), this._handle, 11);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeParticleData(SchemaParticle part) {
        writeShort(part.getParticleType());
        short flags = 0;
        if (part.isSkippable()) {
            flags = (short) (0 | 1);
        }
        if (part.getParticleType() == 4) {
            SchemaLocalElement lpart = (SchemaLocalElement) part;
            if (lpart.isFixed()) {
                flags = (short) (flags | 4);
            }
            if (lpart.isNillable()) {
                flags = (short) (flags | 8);
            }
            if (lpart.blockExtension()) {
                flags = (short) (flags | 16);
            }
            if (lpart.blockRestriction()) {
                flags = (short) (flags | 32);
            }
            if (lpart.blockSubstitution()) {
                flags = (short) (flags | 64);
            }
            if (lpart.isAbstract()) {
                flags = (short) (flags | 128);
            }
            if (lpart instanceof SchemaGlobalElement) {
                SchemaGlobalElement gpart = (SchemaGlobalElement) lpart;
                if (gpart.finalExtension()) {
                    flags = (short) (flags | ExtendedPivotTableViewFieldsRecord.sid);
                }
                if (gpart.finalRestriction()) {
                    flags = (short) (flags | DimensionsRecord.sid);
                }
            }
        }
        writeShort(flags);
        writeBigInteger(part.getMinOccurs());
        writeBigInteger(part.getMaxOccurs());
        writeQNameSet(part.acceptedStartNames());
        switch (part.getParticleType()) {
            case 1:
            case 2:
            case 3:
                writeParticleArray(part.getParticleChildren());
                return;
            case 4:
                SchemaLocalElement lpart2 = (SchemaLocalElement) part;
                writeQName(lpart2.getName());
                writeType(lpart2.getType());
                writeString(lpart2.getDefaultText());
                writeXmlValueObject(lpart2.getDefaultValue());
                writeSOAPArrayType(((SchemaWSDLArrayType) lpart2).getWSDLArrayType());
                writeAnnotation(lpart2.getAnnotation());
                if (lpart2 instanceof SchemaGlobalElement) {
                    SchemaGlobalElement gpart2 = (SchemaGlobalElement) lpart2;
                    writeHandle(gpart2.substitutionGroup());
                    QName[] substGroupMembers = gpart2.substitutionGroupMembers();
                    writeShort(substGroupMembers.length);
                    for (QName substGroupMember : substGroupMembers) {
                        writeQName(substGroupMember);
                    }
                }
                SchemaIdentityConstraint[] idcs = lpart2.getIdentityConstraints();
                writeShort(idcs.length);
                for (SchemaIdentityConstraint idc : idcs) {
                    writeHandle(idc);
                }
                return;
            case 5:
                writeQNameSet(part.getWildcardSet());
                writeShort(part.getWildcardProcess());
                return;
            default:
                throw new SchemaTypeLoaderException("Unrecognized particle type ", this.typeSystem.getName(), this._handle, 11);
        }
    }

    SchemaProperty readPropertyData() {
        SchemaPropertyImpl prop = new SchemaPropertyImpl();
        prop.setName(readQName());
        prop.setTypeRef(readTypeRef());
        int propflags = readShort();
        prop.setAttribute((propflags & 1) != 0);
        prop.setContainerTypeRef(readTypeRef());
        prop.setMinOccurs(readBigInteger());
        prop.setMaxOccurs(readBigInteger());
        prop.setNillable(readShort());
        prop.setDefault(readShort());
        prop.setFixed(readShort());
        prop.setDefaultText(readString());
        prop.setJavaPropertyName(readString());
        prop.setJavaTypeCode(readShort());
        prop.setExtendsJava(readTypeRef(), (propflags & 2) != 0, (propflags & 4) != 0, (propflags & 8) != 0);
        if (atMost(2, 19, 0)) {
            prop.setJavaSetterDelimiter(readQNameSet());
        }
        if (atLeast(2, 16, 0)) {
            prop.setDefaultValue(readXmlValueObject());
        }
        if (!prop.isAttribute() && atLeast(2, 17, 0)) {
            int size = readShort();
            Set<QName> qnames = new LinkedHashSet<>(size);
            for (int i = 0; i < size; i++) {
                qnames.add(readQName());
            }
            prop.setAcceptedNames(qnames);
        }
        prop.setImmutable();
        return prop;
    }

    void writePropertyData(SchemaProperty schemaProperty) {
        writeQName(schemaProperty.getName());
        writeType(schemaProperty.getType());
        writeShort((schemaProperty.isAttribute() ? 1 : 0) | (schemaProperty.extendsJavaSingleton() ? 2 : 0) | (schemaProperty.extendsJavaOption() ? 4 : 0) | (schemaProperty.extendsJavaArray() ? 8 : 0));
        writeType(schemaProperty.getContainerType());
        writeBigInteger(schemaProperty.getMinOccurs());
        writeBigInteger(schemaProperty.getMaxOccurs());
        writeShort(schemaProperty.hasNillable());
        writeShort(schemaProperty.hasDefault());
        writeShort(schemaProperty.hasFixed());
        writeString(schemaProperty.getDefaultText());
        writeString(schemaProperty.getJavaPropertyName());
        writeShort(schemaProperty.getJavaTypeCode());
        writeType(schemaProperty.javaBasedOnType());
        writeXmlValueObject(schemaProperty.getDefaultValue());
        if (!schemaProperty.isAttribute()) {
            QName[] acceptedNames = schemaProperty.acceptedNames();
            writeShort(acceptedNames.length);
            for (QName qName : acceptedNames) {
                writeQName(qName);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeModelGroupData(SchemaModelGroup schemaModelGroup) {
        SchemaModelGroupImpl schemaModelGroupImpl = (SchemaModelGroupImpl) schemaModelGroup;
        writeQName(schemaModelGroupImpl.getName());
        writeString(schemaModelGroupImpl.getTargetNamespace());
        writeShort(schemaModelGroupImpl.getChameleonNamespace() != null ? 1 : 0);
        writeString(schemaModelGroupImpl.getElemFormDefault());
        writeString(schemaModelGroupImpl.getAttFormDefault());
        writeShort(schemaModelGroupImpl.isRedefinition() ? 1 : 0);
        writeString(schemaModelGroupImpl.getParseObject().xmlText(new XmlOptions().setSaveOuter()));
        writeAnnotation(schemaModelGroupImpl.getAnnotation());
        writeString(schemaModelGroupImpl.getSourceName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeAttributeGroupData(SchemaAttributeGroup schemaAttributeGroup) {
        SchemaAttributeGroupImpl schemaAttributeGroupImpl = (SchemaAttributeGroupImpl) schemaAttributeGroup;
        writeQName(schemaAttributeGroupImpl.getName());
        writeString(schemaAttributeGroupImpl.getTargetNamespace());
        writeShort(schemaAttributeGroupImpl.getChameleonNamespace() != null ? 1 : 0);
        writeString(schemaAttributeGroupImpl.getFormDefault());
        writeShort(schemaAttributeGroupImpl.isRedefinition() ? 1 : 0);
        writeString(schemaAttributeGroupImpl.getParseObject().xmlText(new XmlOptions().setSaveOuter()));
        writeAnnotation(schemaAttributeGroupImpl.getAnnotation());
        writeString(schemaAttributeGroupImpl.getSourceName());
    }

    XmlValueRef readXmlValueObject() {
        SchemaType.Ref typeref = readTypeRef();
        if (typeref == null) {
            return null;
        }
        int btc = readShort();
        switch (btc) {
            case 0:
                return new XmlValueRef(typeref, null);
            case 2:
            case 3:
            case 6:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return new XmlValueRef(typeref, readString());
            case 4:
            case 5:
                return new XmlValueRef(typeref, readByteArray());
            case 7:
            case 8:
                return new XmlValueRef(typeref, readQName());
            case 9:
            case 10:
                return new XmlValueRef(typeref, Double.valueOf(readDouble()));
            case 65535:
                int size = readShort();
                List<XmlValueRef> values = new ArrayList<>();
                writeShort(size);
                for (int i = 0; i < size; i++) {
                    values.add(readXmlValueObject());
                }
                return new XmlValueRef(typeref, values);
            default:
                throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public void writeXmlValueObject(XmlAnySimpleType xmlAnySimpleType) {
        SchemaType type = xmlAnySimpleType == 0 ? null : xmlAnySimpleType.schemaType();
        writeType(type);
        if (type == null) {
            return;
        }
        SchemaType iType = ((SimpleValue) xmlAnySimpleType).instanceType();
        if (iType == null) {
            writeShort(0);
            return;
        }
        if (iType.getSimpleVariety() == 3) {
            writeShort(-1);
            List<? extends XmlAnySimpleType> values = ((XmlObjectBase) xmlAnySimpleType).xgetListValue();
            writeShort(values.size());
            values.forEach(new Consumer() { // from class: org.apache.xmlbeans.impl.schema.XsbReader$$ExternalSyntheticLambda2
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    XsbReader.this.writeXmlValueObject((XmlAnySimpleType) obj);
                }
            });
            return;
        }
        int btc = iType.getPrimitiveType().getBuiltinTypeCode();
        writeShort(btc);
        switch (btc) {
            case 2:
            case 3:
            case 6:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                writeString(xmlAnySimpleType.getStringValue());
                return;
            case 4:
            case 5:
                writeByteArray(((SimpleValue) xmlAnySimpleType).getByteArrayValue());
                return;
            case 7:
            case 8:
                writeQName(((SimpleValue) xmlAnySimpleType).getQNameValue());
                return;
            case 9:
                writeDouble(((SimpleValue) xmlAnySimpleType).getFloatValue());
                return;
            case 10:
                writeDouble(((SimpleValue) xmlAnySimpleType).getDoubleValue());
                return;
            default:
                return;
        }
    }

    double readDouble() {
        try {
            return this._input.readDouble();
        } catch (IOException e) {
            throw new SchemaTypeLoaderException(e.getMessage(), this.typeSystem.getName(), this._handle, 9, e);
        }
    }

    void writeDouble(double d) {
        if (this._output != null) {
            try {
                this._output.writeDouble(d);
            } catch (IOException e) {
                throw new SchemaTypeLoaderException(e.getMessage(), this.typeSystem.getName(), this._handle, 9, e);
            }
        }
    }

    QNameSet readQNameSet() {
        int flag = readShort();
        Set<String> uriSet = new HashSet<>();
        int uriCount = readShort();
        for (int i = 0; i < uriCount; i++) {
            uriSet.add(readString());
        }
        Set<QName> qnameSet1 = new HashSet<>();
        int qncount1 = readShort();
        for (int i2 = 0; i2 < qncount1; i2++) {
            qnameSet1.add(readQName());
        }
        Set<QName> qnameSet2 = new HashSet<>();
        int qncount2 = readShort();
        for (int i3 = 0; i3 < qncount2; i3++) {
            qnameSet2.add(readQName());
        }
        if (flag == 1) {
            return QNameSet.forSets(uriSet, null, qnameSet1, qnameSet2);
        }
        return QNameSet.forSets(null, uriSet, qnameSet2, qnameSet1);
    }

    void writeQNameSet(QNameSet set) {
        boolean invert = set.excludedURIs() != null;
        writeShort(invert ? 1 : 0);
        Set<String> uriSet = invert ? set.excludedURIs() : set.includedURIs();
        if (uriSet == null) {
            throw new AssertionError();
        }
        writeShort(uriSet.size());
        uriSet.forEach(new XsbReader$$ExternalSyntheticLambda0(this));
        Set<QName> qnameSet1 = invert ? set.excludedQNamesInIncludedURIs() : set.includedQNamesInExcludedURIs();
        writeShort(qnameSet1.size());
        qnameSet1.forEach(new Consumer() { // from class: org.apache.xmlbeans.impl.schema.XsbReader$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                XsbReader.this.writeQName((QName) obj);
            }
        });
        Set<QName> qnameSet2 = invert ? set.includedQNamesInExcludedURIs() : set.excludedQNamesInIncludedURIs();
        writeShort(qnameSet2.size());
        qnameSet2.forEach(new Consumer() { // from class: org.apache.xmlbeans.impl.schema.XsbReader$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                XsbReader.this.writeQName((QName) obj);
            }
        });
    }

    byte[] readByteArray() {
        try {
            int len = this._input.readShort();
            byte[] result = new byte[len];
            this._input.readFully(result);
            return result;
        } catch (IOException e) {
            throw new SchemaTypeLoaderException(e.getMessage(), this.typeSystem.getName(), this._handle, 9, e);
        }
    }

    void writeByteArray(byte[] ba) {
        try {
            writeShort(ba.length);
            if (this._output != null) {
                this._output.write(ba);
            }
        } catch (IOException e) {
            throw new SchemaTypeLoaderException(e.getMessage(), this.typeSystem.getName(), this._handle, 9, e);
        }
    }

    BigInteger readBigInteger() {
        byte[] result = readByteArray();
        if (result.length == 0) {
            return null;
        }
        if (result.length == 1 && result[0] == 0) {
            return BigInteger.ZERO;
        }
        if (result.length == 1 && result[0] == 1) {
            return BigInteger.ONE;
        }
        return new BigInteger(result);
    }

    void writeBigInteger(BigInteger bi) {
        if (bi == null) {
            writeShort(0);
        } else if (bi.signum() == 0) {
            writeByteArray(SchemaTypeSystemImpl.SINGLE_ZERO_BYTE);
        } else {
            writeByteArray(bi.toByteArray());
        }
    }
}
